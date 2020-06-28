package com.lucas.restaurante.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;


@Setter
@Getter
@NoArgsConstructor
public class Category implements Serializable {
    String categoryName;
    ArrayList <Food> categoryList = new ArrayList<>();
    String pic;
}
