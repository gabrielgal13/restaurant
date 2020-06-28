package com.lucas.restaurante.ui;


import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lucas.restaurante.R;
import com.lucas.restaurante.dao.Category;
import com.lucas.restaurante.dao.Food;
import com.lucas.restaurante.storage.StateElementsManager;


import java.lang.reflect.Type;
import java.util.ArrayList;


public class Option extends AppCompatActivity {

    private RecyclerView recOpt;
    private ArrayList<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        Integer position = getIntent().getIntExtra("category",1);

        recOpt = this.findViewById(R.id.optionRecycler);
        OptionAdapter optionAdapter = new OptionAdapter(this, position);
        recOpt.setAdapter(optionAdapter);
        recOpt.setLayoutManager(new LinearLayoutManager(this));
    }



}
