package com.swp391.skincare_products_sales_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<CartItem> items = new HashSet<>();

    Double totalPrice = 0.0;

    public void updateTotalPrice() {
        double totalPrice = 0;
        for (CartItem cartItem : items) {
            if (cartItem != null && cartItem.getProduct() != null) {
                totalPrice += cartItem.getPrice() * cartItem.getQuantity();
            }
        }
        this.totalPrice = totalPrice;
    }
}
