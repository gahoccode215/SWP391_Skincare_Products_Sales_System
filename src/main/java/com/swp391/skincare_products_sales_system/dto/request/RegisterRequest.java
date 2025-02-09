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

    @Size(min = 6, message = "INVALID_USERNAME")
    String username;

    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;

    @BirthdayConstraint(min = 6, message = "INVALID_BIRTHDAY")
    LocalDate birthday;

    @GenderConstraint(message = "INVALID_GENDER")
    Gender gender;
}
