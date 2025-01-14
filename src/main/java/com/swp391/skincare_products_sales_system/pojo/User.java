package com.swp391.skincare_products_sales_system.pojo;

import com.swp391.skincare_products_sales_system.util.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends Base{
    String email;
    String password;
    UserStatus userStatus;
    String firstName;
    String lastName;
    String phone;
    String address;
}
