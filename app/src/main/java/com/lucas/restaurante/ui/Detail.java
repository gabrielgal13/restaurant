package com.lucas.restaurante.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.textfield.TextInputEditText;
import com.lucas.restaurante.MainActivity;
import com.lucas.restaurante.R;
import com.lucas.restaurante.dao.Food;
import com.lucas.restaurante.dao.SingletonPurchaseList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Detail extends AppCompatActivity {

    private ImageView img;
    private TextView title;
    private TextView description;
    private TextView price;
    private EditText quantity;

    private Food food;
    private Integer foodPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        food = (Food)getIntent().getSerializableExtra("food");
        foodPos = getIntent().getIntExtra("foodPos", 1);




        img = findViewById(R.id.thumbnail);
        title = findViewById(R.id.title);
        description = findViewById(R.id.descDetail);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.zero);


        Context context = this;
        AssetManager assetManager = context.getAssets();
        InputStream is = null;
        try {
            is = assetManager.open(food.getPic());
        } catch (IOException e) {
            Toast.makeText(this, "Problems with the image", Toast.LENGTH_SHORT).show();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);


        img.setImageBitmap(bitmap);

        title.setText(food.getTitle());
        description.setText(food.getDescription());
        price.setText(String.valueOf(food.getPrice()));

    }

    public void onClickAdd(View view) {
            Integer number = 0;
        try {
            number = Integer.parseInt(quantity.getText().toString());
            Food newFood = new Food();
            newFood.setId(this.food.getId());
            newFood.setTitle(this.food.getTitle());
            newFood.setDescription(this.food.getDescription());
            newFood.setPrice(this.food.getPrice());
            newFood.setPic(this.food.getPic());
            newFood.setCategory(this.food.getCategory());
            newFood.setQuantity(number);
            SingletonPurchaseList.getInstance().addToArray(newFood);
        }catch (Exception ex) {
            Toast.makeText(Detail.this, "Remember lower numbers", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, number + " items has been added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Detail.this, Option.class);
        intent.putExtra("category", foodPos);
        this.startActivity(intent);
    }


}
