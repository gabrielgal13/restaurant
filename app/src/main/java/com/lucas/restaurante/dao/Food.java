package com.lucas.restaurante.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    private Integer id;
    private String category;
    private String title;
    private String pic;
    private double price;
    private String description;
}
