package com.lucas.restaurante.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.lucas.restaurante.R;
import com.lucas.restaurante.dao.Category;
import com.lucas.restaurante.dao.Food;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public  class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder>  {

    Context ct;
    ArrayList<Food> foodList;


    public OptionAdapter(Context ct, ArrayList<Food> foodList) {
        this.ct = ct;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context;
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.option_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.description.setText(foodList.get(position).getTitle());
        AssetManager assetManager = ct.getAssets();
        InputStream is = null;
        try {
            is = assetManager.open((foodList.get(position)).getPic());
        } catch (IOException e) {
            Toast.makeText(this.ct, "Problems with the image", Toast.LENGTH_SHORT).show();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        holder.thumbnail.setImageBitmap(bitmap);
    }
    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView description;
        ImageView thumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.title);
            thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }
}
