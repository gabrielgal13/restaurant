package com.lucas.restaurante.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.lucas.restaurante.R;
import com.lucas.restaurante.dao.Category;
import com.lucas.restaurante.dao.Food;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public  class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>  {

    Context ct;
    HashMap<String, Category> categoryHashMap;


    public CategoryAdapter(Context ct, HashMap<String, Category> categoryHashMap) {
        this.ct = ct;
        this.categoryHashMap = categoryHashMap;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context;
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.food_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Set entry = categoryHashMap.keySet();
        ArrayList<String> list = new ArrayList<>();
        for (Object o : entry) {
            list.add(o.toString());
        }
        holder.description.setText(Objects.requireNonNull(categoryHashMap.get(list.get(position))).getCategoryName());
        AssetManager assetManager = ct.getAssets();
        InputStream is = null;
        try {
            is = assetManager.open(Objects.requireNonNull(categoryHashMap.get(list.get(position))).getPic());
        } catch (IOException e) {
            Toast.makeText(this.ct, "Problems with the image", Toast.LENGTH_SHORT).show();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        holder.thumbnail.setImageBitmap(bitmap);

        ArrayList foodList = Objects.requireNonNull(Objects.requireNonNull(categoryHashMap.get(list.get(position))).getCategoryList());

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ct, Option.class);
                intent.putExtra("foodList", foodList);
                ct.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return categoryHashMap.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView description;
        ImageView thumbnail;
        LinearLayout rowLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.title);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            rowLayout = itemView.findViewById(R.id.foodRowLayout);

        }
    }
}
