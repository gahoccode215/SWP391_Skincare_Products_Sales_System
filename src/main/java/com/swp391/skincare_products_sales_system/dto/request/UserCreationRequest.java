package com.swp391.skincare_products_sales_system.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swp391.skincare_products_sales_system.dto.validator.EnumPattern;
import com.swp391.skincare_products_sales_system.dto.validator.GenderSubset;
import com.swp391.skincare_products_sales_system.dto.validator.PhoneNumber;
import com.swp391.skincare_products_sales_system.util.Gender;
import com.swp391.skincare_products_sales_system.util.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Email(message = "Email invalid format")
    String email;
    @NotNull(message = "Password must be not null")
    String password;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    Date dateOfBirth;
    @GenderSubset(anyOf = {Gender.MALE, Gender.FEMALE, Gender.OTHER})
    Gender gender;

    @EnumPattern(name = "status", regexp = "ACTIVE|INACTIVE|NONE")
    UserStatus userStatus;

    String firstName;
    String lastName;
    //@Pattern(regexp = "^\\d{10}$", message = "phone invalid format")
    @PhoneNumber(message = "Phone invalid format")
    String phone;
    String address;
}
