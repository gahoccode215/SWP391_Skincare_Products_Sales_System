package com.swp391.skincare_products_sales_system.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swp391.skincare_products_sales_system.dto.validator.EnumPattern;
import com.swp391.skincare_products_sales_system.dto.validator.EnumValue;
import com.swp391.skincare_products_sales_system.dto.validator.GenderSubset;
import com.swp391.skincare_products_sales_system.dto.validator.PhoneNumber;
import com.swp391.skincare_products_sales_system.util.Gender;
import com.swp391.skincare_products_sales_system.util.Gender.*;
import com.swp391.skincare_products_sales_system.util.UserStatus;
import com.swp391.skincare_products_sales_system.util.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class UserRequest {

    String firstName;

    String lastName;

    @Email(message = "Email invalid format")
    String email;

    //@Pattern(regexp = "^\\d{10}$", message = "phone invalid format")
//    @PhoneNumber(message = "phone invalid format")
    String phone;

    //    @NotNull(message = "dateOfBirth must be not null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    Date dateOfBirth;

    //@Pattern(regexp = "^male|female|other$", message = "gender must be one in {male, female, other}")
    @GenderSubset(anyOf = {Gender.MALE, Gender.FEMALE, Gender.OTHER})
    Gender gender;

    //    @NotNull(message = "username must be not null")
    String username;

    //    @NotNull(message = "password must be not null")
    String password;

//    @NotNull(message = "type must be not null")
//    @EnumValue(name = "type", enumClass = UserType.class)
//    private String type;

    @EnumPattern(name = "status", regexp = "ACTIVE|INACTIVE|NONE")
    UserStatus status;
}
