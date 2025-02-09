package com.swp391.skincare_products_sales_system.dto.request;

import com.swp391.skincare_products_sales_system.enums.Gender;
import com.swp391.skincare_products_sales_system.validator.GenderConstraint;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class RegisterRequest implements Serializable {

    @NotBlank(message = "username can not blank")
    String username;
    @NotBlank(message = "password can not blank")
    String password;

    @NotNull(message = "birthday can not null")
    Date birthday;

    @GenderConstraint(message = "Gender must be one of the following: MALE, FEMALE, OTHER")
    Gender gender;
}
