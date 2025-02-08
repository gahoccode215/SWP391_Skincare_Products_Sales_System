package com.swp391.skincare_products_sales_system.service.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import com.swp391.skincare_products_sales_system.constant.PredefinedRole;
import com.swp391.skincare_products_sales_system.dto.request.LoginRequest;
import com.swp391.skincare_products_sales_system.dto.request.LogoutRequest;
import com.swp391.skincare_products_sales_system.dto.request.RefreshTokenRequest;
import com.swp391.skincare_products_sales_system.dto.request.RegisterRequest;
import com.swp391.skincare_products_sales_system.dto.response.LoginResponse;
import com.swp391.skincare_products_sales_system.dto.response.RefreshTokenResponse;
import com.swp391.skincare_products_sales_system.dto.response.RegisterResponse;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.exception.AppException;
import com.swp391.skincare_products_sales_system.model.InvalidatedToken;
import com.swp391.skincare_products_sales_system.model.Role;
import com.swp391.skincare_products_sales_system.model.User;
import com.swp391.skincare_products_sales_system.repository.InvalidatedTokenRepository;
import com.swp391.skincare_products_sales_system.repository.RoleRepository;
import com.swp391.skincare_products_sales_system.repository.UserRepository;
import com.swp391.skincare_products_sales_system.service.AuthenticationService;
import com.swp391.skincare_products_sales_system.util.JwtUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtUtil jwtUtil;
    InvalidatedTokenRepository invalidatedTokenRepository;
    private final RoleRepository roleRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_LOGIN));
        // Kiểm tra mật khẩu có khớp không
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_LOGIN);
        }
        // Trả về Token
        return LoginResponse.builder()
                .token(jwtUtil.generateToken(user)) //Token được generate
                .authenticated(true)
                .build();
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        SignedJWT signedJWT;
        try {
            signedJWT = jwtUtil.verifyToken(request.getToken(), true);
        } catch (ParseException | JOSEException e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        // Lấy thông tin JWT từ token đã xác thực
        String tokenId = null;
        Date expiryTime = null;
        String username = null;

        try {
            tokenId = signedJWT.getJWTClaimsSet().getJWTID();
            expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            username = signedJWT.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        // Kiểm tra nếu token đã hết hạn
        if (expiryTime.before(new Date())) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        // Lưu token vào bảng invalidated_token để ngừng sử dụng
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .token(tokenId)
                .expiryTime(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
        // Tạo lại token mới
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        String newToken = jwtUtil.generateToken(user);
        return RefreshTokenResponse.builder()
                .token(newToken)
                .authenticated(true)
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        // Lấy Role từ Database gắn vào
        Role userRole = roleRepository.findByName(PredefinedRole.USER_ROLE)
                .orElseThrow(() -> new AppException(ErrorCode.REGISTER_ERROR));
        user.setRoles(Set.of(userRole));
        userRepository.save(user);
        return RegisterResponse.builder()
                .username(user.getUsername())
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logout(LogoutRequest request) {
        SignedJWT signedJWT;
        try {
            signedJWT = jwtUtil.verifyToken(request.getToken(), false);
        } catch (ParseException | JOSEException e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        // Lấy thông tin từ token
        String tokenId = null;
        Date expiryTime = null;

        try {
            tokenId = signedJWT.getJWTClaimsSet().getJWTID();
            expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        } catch (ParseException e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        // Kiểm tra nếu token đã hết hạn
        if (expiryTime.before(new Date())) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        // Lưu token vào danh sách invalidated token
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .token(tokenId)
                .expiryTime(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
    }
}
