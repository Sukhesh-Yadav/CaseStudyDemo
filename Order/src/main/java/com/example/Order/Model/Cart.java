package com.example.Order.Model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @ElementCollection
    @CollectionTable(name = "productMap",
            joinColumns = @JoinColumn(name = "product"))
    @MapKeyColumn(name = "productId")
    @Column(name = "quantity")
    private Map<String,Integer> cartItems;
}
