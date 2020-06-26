package com.lucas.restaurante.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
public class Category {
    String categoryName;
    Set <Food> categorySet = new HashSet<>();
    String pic;
}
