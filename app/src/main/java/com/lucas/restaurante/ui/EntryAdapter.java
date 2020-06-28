package com.lucas.restaurante.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lucas.restaurante.R;
import com.lucas.restaurante.dao.Category;
import com.lucas.restaurante.storage.StateElementsManager;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.*;

public  class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder>  {

    private Context ct;
    private List<Category> categoryList;


    public EntryAdapter(Context ct) {
        this.ct = ct;
        this.categoryList = (List<Category>) StateElementsManager.loadState(ct, "catList");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.food_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.description.setText((categoryList.get(position)).getCategoryName());
        AssetManager assetManager = ct.getAssets();
        InputStream is = null;
        try {
            is = assetManager.open(Objects.requireNonNull((categoryList.get(position)).getPic()));
        } catch (IOException e) {
            Toast.makeText(this.ct, "Problems with the image", Toast.LENGTH_SHORT).show();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        holder.thumbnail.setImageBitmap(bitmap);

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ct, Option.class);
                intent.putExtra("category", position);
                ct.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return categoryList.size();
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
