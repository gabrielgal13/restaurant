package com.lucas.restaurante.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lucas.restaurante.R;
import com.lucas.restaurante.dao.Category;
import com.lucas.restaurante.dao.Food;
import com.lucas.restaurante.storage.StateElementsManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Entry extends AppCompatActivity implements StateElementsManager {

    private RecyclerView recCat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        organizer(load());
        recCat = this.findViewById(R.id.categoryRecycler);
        EntryAdapter foodAdapter = new EntryAdapter(this);
        recCat.setAdapter(foodAdapter);
        recCat.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBackPressed() {
        finishAffinity();
    }

    public ArrayList<Food>  load() {
        String json;
        ArrayList<Food> foodList = new ArrayList<>();
        try {
            InputStream is = getAssets().open("food.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String (buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i<jsonArray.length(); i++ ) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Food food = new Food();
                food.setId(obj.getInt("id"));
                food.setTitle(obj.getString("title"));
                food.setCategory(obj.getString("category"));
                food.setPic(obj.getString("pic"));
                food.setPrice(obj.getInt("price"));
                food.setDescription(obj.getString("description"));
                foodList.add(food);
            }

        } catch (IOException | JSONException e) {
            System.out.println("Problem loading: " + e);
        }
        return foodList;
    }

    public void organizer (ArrayList<Food> foodList) {
        HashMap<String, Category> categoryHashMap= new HashMap<String, Category>();
        for (Food food : foodList) {
            if (categoryHashMap.get(food.getCategory())==null){
                Category category = new Category();
                category.getCategoryList().add(food);
                category.setCategoryName(food.getCategory());
                category.setPic(food.getPic());
                categoryHashMap.put(food.getCategory(), category);
            }else {
                Objects.requireNonNull(categoryHashMap.get(food.getCategory())).getCategoryList().add(food);
                Objects.requireNonNull(categoryHashMap.get(food.getCategory())).setCategoryName(food.getCategory());
                Objects.requireNonNull(categoryHashMap.get(food.getCategory())).setPic(food.getPic());
            }
        }
        ArrayList<Category> categoryList = new ArrayList<>(categoryHashMap.values());
        StateElementsManager.saveState(this, categoryList, "catList");
    }
}
