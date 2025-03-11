package com.swp391.skincare_products_sales_system.dto.request;

import com.swp391.skincare_products_sales_system.validator.ConfirmPasswordConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfirmPasswordConstraint
public class ChangePasswordRequest {

    @NotBlank(message = "Mật khẩu không được để trống")
    String oldPassword;

    @Size(min = 6, message = "INVALID_PASSWORD")
    String newPassword;

    @Size(min = 6, message = "INVALID_PASSWORD")
    String confirmPassword;
}
