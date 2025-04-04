package com.swp391.skincare_products_sales_system.service.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import com.swp391.skincare_products_sales_system.constant.PredefinedRole;
import com.swp391.skincare_products_sales_system.dto.request.*;
import com.swp391.skincare_products_sales_system.dto.response.LoginResponse;
import com.swp391.skincare_products_sales_system.dto.response.RefreshTokenResponse;
import com.swp391.skincare_products_sales_system.dto.response.RegisterResponse;
import com.swp391.skincare_products_sales_system.entity.Otp;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.exception.AppException;
import com.swp391.skincare_products_sales_system.entity.InvalidatedToken;
import com.swp391.skincare_products_sales_system.entity.Role;
import com.swp391.skincare_products_sales_system.entity.User;
import com.swp391.skincare_products_sales_system.repository.InvalidatedTokenRepository;
import com.swp391.skincare_products_sales_system.repository.OtpRepository;
import com.swp391.skincare_products_sales_system.repository.RoleRepository;
import com.swp391.skincare_products_sales_system.repository.UserRepository;
import com.swp391.skincare_products_sales_system.service.AuthenticationService;
import com.swp391.skincare_products_sales_system.service.OtpService;
import com.swp391.skincare_products_sales_system.service.PostmarkService;
import com.swp391.skincare_products_sales_system.util.JwtUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    JwtUtil jwtUtil;
    InvalidatedTokenRepository invalidatedTokenRepository;
    PostmarkService postmarkService;
    OtpService otpService;
    OtpRepository otpRepository;


    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        if(userRepository.existsByEmailAndStatus(request.getEmail(), Status.INACTIVE)){
            userRepository.delete(userRepository.findByEmailAndStatus(request.getEmail(), Status.INACTIVE));
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        if (userRepository.findByUsername(request.getUsername()).isPresent())
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .gender(request.getGender())
                .birthday(request.getBirthday())
                .status(Status.INACTIVE)
                .email(request.getEmail())
                .build();
        Role customRole = roleRepository.findByName(PredefinedRole.CUSTOMER_ROLE)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
        user.setRole(customRole);
        user.setPoint(0);
        user.setFirstName("");
        user.setLastName("");
        user.setAvatar("https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg");
        user.setIsDeleted(false);
        userRepository.save(user);
        Otp otp = otpService.generateAndSaveOtp(user.getId());
        user.addOtp(otp);
        userRepository.save(user);
        try {
            postmarkService.sendVerificationEmailWithOTP(user.getEmail(), user.getUsername(), otp.getOtp());
        } catch (Exception e) {
            throw new AppException(ErrorCode.EMAIL_SEND_FAILED);
        }
        return RegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .gender(user.getGender())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_LOGIN));
        if (user.getRole().getName().equals(PredefinedRole.CUSTOMER_ROLE))
            if (user.getStatus().equals(Status.INACTIVE)) {
                throw new AppException(ErrorCode.ACCOUNT_HAS_NOT_BEEN_ACTIVE);
            }
        if (user.getStatus().equals(Status.INACTIVE)){
            throw new AppException(ErrorCode.ACCOUNT_HAS_BEEN_DISABLE);
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_LOGIN);
        }
        // Trả về Token
        return LoginResponse.builder()
                .token(jwtUtil.generateToken(user))
                .authenticated(true)
                .roleName(user.getRole().getName())
                .build();
    }

    @Override
    @Transactional
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        SignedJWT signedJWT;
        try {
            signedJWT = jwtUtil.verifyToken(request.getToken(), true);
        } catch (ParseException | JOSEException e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

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

        if (expiryTime.before(new Date())) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .token(tokenId)
                .expiryTime(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        String newToken = jwtUtil.generateToken(user);
        return RefreshTokenResponse.builder()
                .token(newToken)
                .authenticated(true)
                .build();
    }

    @Override
    @Transactional
    public void logout(LogoutRequest request) {
        SignedJWT signedJWT;
        try {
            signedJWT = jwtUtil.verifyToken(request.getToken(), false);
        } catch (ParseException | JOSEException e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        String tokenId = null;
        Date expiryTime = null;

        try {
            tokenId = signedJWT.getJWTClaimsSet().getJWTID();
            expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        } catch (ParseException e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        if (expiryTime.before(new Date())) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .token(tokenId)
                .expiryTime(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info("username: {}", username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_CHANGE_PASSWORD);
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new AppException(ErrorCode.INVALID_CONFIRM_PASSWORD);
        }
        String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
    }


    @Override
    @Transactional
    public void forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.EMAIL_NOT_FOUND));
        Otp otp = otpService.generateAndSaveOtp(user.getId());
        try {
            postmarkService.sendForgotPassword(user.getEmail(),user.getUsername() ,otp.getOtp());
        } catch (Exception e) {
            throw new AppException(ErrorCode.EMAIL_SEND_FAILED);
        }
    }

    @Override
    @Transactional
    public void resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Kiểm tra OTP hợp lệ
        Optional<Otp> otpOptional = otpRepository.findByUserAndOtp(user, request.getOtp());
        if (otpOptional.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_OTP); // Nếu không tìm thấy OTP trùng khớp
        }

        Otp otp = otpOptional.get();

        // Kiểm tra thời gian hết hạn của OTP
        if (otp.getExpirationTime().before(new Date())) {
            throw new AppException(ErrorCode.OTP_EXPIRED); // OTP đã hết hạn
        }

        // Cập nhật mật khẩu mới
        user.setPassword(passwordEncoder.encode(request.getNewPassword())); // Mã hóa mật khẩu
        userRepository.save(user);

        // Xóa OTP đã xác thực khỏi cơ sở dữ liệu để không sử dụng lại
        otpRepository.delete(otp);
    }

}

