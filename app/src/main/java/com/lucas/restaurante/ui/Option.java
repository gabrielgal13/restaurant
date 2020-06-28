package com.lucas.restaurante.ui;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lucas.restaurante.R;

import com.lucas.restaurante.dao.Food;




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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Option.this, Entry.class);
        this.startActivity(intent);
    }



}
