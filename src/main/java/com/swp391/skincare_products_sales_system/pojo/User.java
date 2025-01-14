package com.swp391.skincare_products_sales_system.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    String firstName;
    String lastName;
    String phone;
    String address;
}
