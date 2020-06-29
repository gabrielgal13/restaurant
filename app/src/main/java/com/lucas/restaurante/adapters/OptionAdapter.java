package com.lucas.restaurante.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.lucas.restaurante.R;
import com.lucas.restaurante.dao.Category;
import com.lucas.restaurante.dao.Food;
import com.lucas.restaurante.storage.StateElementsManager;
import com.lucas.restaurante.ui.Detail;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public  class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder>  {

    Context ct;
    ArrayList<Food> foodList;
    Integer catPos;


    public OptionAdapter(Context ct, Integer position) {
        this.ct = ct;
        ArrayList<Category> catList = (ArrayList<Category>) StateElementsManager.loadState(ct, "catList");
        this.catPos = position;
        this.foodList = catList.get(position).getCategoryList();
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

        Food food = foodList.get(position);

        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ct, Detail.class);
                intent.putExtra("food", food);
                intent.putExtra("foodPos", catPos);
                ct.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView description;
        ImageView thumbnail;
        LinearLayout option;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.title);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            option = itemView.findViewById(R.id.rowOptionLayout);

        }
    }

}
