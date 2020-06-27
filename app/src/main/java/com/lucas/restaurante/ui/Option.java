package com.lucas.restaurante.ui;

import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import com.lucas.restaurante.R;
import com.lucas.restaurante.dao.Food;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class Option extends AppCompatActivity {

    private RecyclerView recOpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        ArrayList<Food> foodList = (ArrayList<Food>)getIntent().getSerializableExtra("foodList");

        recOpt = this.findViewById(R.id.optionRecycler);
        OptionAdapter optionAdapter = new OptionAdapter(this, foodList);
        recOpt.setAdapter(optionAdapter);
        recOpt.setLayoutManager(new LinearLayoutManager(this));
    }




}
