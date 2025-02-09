package com.swp391.skincare_products_sales_system.dto.request;

import com.swp391.skincare_products_sales_system.enums.Gender;
import com.swp391.skincare_products_sales_system.validator.BirthdayConstraint;
import com.swp391.skincare_products_sales_system.validator.GenderConstraint;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class RegisterRequest implements Serializable {

    @Size(min = 6, message = "USERNAME_INVALID")
    String username;

    @Size(min = 6, message = "PASSWORD_INVALID")
    String password;

    @BirthdayConstraint(min = 6, message = "BIRTHDAY_INVALID")
    LocalDate birthday;

    @GenderConstraint(message = "GENDER_INVALID")
    Gender gender;
}
