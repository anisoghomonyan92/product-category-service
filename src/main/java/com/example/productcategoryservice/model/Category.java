package com.example.productcategoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="category")
public class Category {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int Id;
    private String name;
}
