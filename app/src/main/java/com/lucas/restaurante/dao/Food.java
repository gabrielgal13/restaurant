package com.lucas.restaurante.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Food implements Serializable {
    private Integer id;
    private String category;
    private String title;
    private String pic;
    private double price;
    private String description;
    private Integer quantity = 0;


}
