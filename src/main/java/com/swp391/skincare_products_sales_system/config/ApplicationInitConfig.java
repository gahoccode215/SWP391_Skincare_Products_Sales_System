package com.swp391.skincare_products_sales_system.config;

import com.swp391.skincare_products_sales_system.constant.PredefinedRole;
import com.swp391.skincare_products_sales_system.enums.Gender;
import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.entity.Role;
import com.swp391.skincare_products_sales_system.entity.User;
import com.swp391.skincare_products_sales_system.repository.RoleRepository;
import com.swp391.skincare_products_sales_system.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                List<User> listAccount = new ArrayList<>();
                Role customerRole = initRole(PredefinedRole.CUSTOMER_ROLE);
                roleRepository.save(customerRole);
                Role managerRole = initRole(PredefinedRole.MANAGER_ROLE);
                roleRepository.save(managerRole);
                Role staffRole = initRole(PredefinedRole.STAFF);
                roleRepository.save(staffRole);
                Role deliveryRole = initRole(PredefinedRole.DELIVERY);
                roleRepository.save(deliveryRole);
                Role adminRole = initRole(PredefinedRole.ADMIN_ROLE);
                roleRepository.save(adminRole);

                User admin = initAccount(ADMIN_USER_NAME, ADMIN_PASSWORD, adminRole, "admin@gmail.com");
                listAccount.add(admin);
                User phuocAdmin = initAccount("phuocadmin", "phuocadmin", adminRole, "phuocadmin@gmail.com");
                listAccount.add(phuocAdmin);
                User minhAdmin = initAccount("minhadmin", "minhadmin", adminRole, "minhadmin@gmail.com");
                listAccount.add(minhAdmin);
                User customer = initAccount("customer", "customer", customerRole, "customer@gmail.com");
                listAccount.add(customer);
                User phuocCustomer = initAccount("phuoccustomer", "phuoccustomer", customerRole, "phuoccustomer@gmail.com");
                listAccount.add(phuocCustomer);
                User minhCustomer = initAccount("minhcustomer", "minhcustomer", customerRole, "minhcustomer@gmail.com");
                listAccount.add(minhCustomer);
                User manager = initAccount("manager", "manager", managerRole, "manager@gmail.com");
                listAccount.add(manager);
                User staff = initAccount("staff", "staff", staffRole, "staff@gmail.com");
                listAccount.add(staff);
                User delivery = initAccount("delivery", "delivery", deliveryRole, "delivery@gmail.com");
                listAccount.add(delivery);
                userRepository.saveAll(listAccount);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }

    private Role initRole(String role) {
        Role newRole = Role.builder()
                .name(role)
                .description(role)
                .build();
        newRole.setIsDeleted(false);
        return newRole;
    }

    private User initAccount(String username, String password, Role role, String email) {
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(role)
                .point(0)
                .firstName("")
                .lastName("")
                .birthday(LocalDate.now())
                .email(email)
                .avatar("https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg")
                .status(Status.ACTIVE)
                .gender(Gender.OTHER)
                .build();
        user.setIsDeleted(false);
        return user;
    }
}
