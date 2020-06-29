package com.lucas.restaurante.adapters;

import android.content.Context;
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
import com.lucas.restaurante.dao.Food;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;



@AllArgsConstructor
public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {

    private  Context ct;
    private ArrayList<Food> purchaseList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.purchase_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.title.setText(purchaseList.get(position).getTitle());
        holder.price.setText(String.valueOf(purchaseList.get(position).getPrice()));
        holder.qty.setText(String.valueOf(purchaseList.get(position).getQuantity()));

        double total = purchaseList.get(position).getQuantity() * purchaseList.get(position).getPrice();
        holder.total.setText(String.valueOf(total));

        AssetManager assetManager = ct.getAssets();
        InputStream is = null;
        try {
            is = assetManager.open(Objects.requireNonNull((purchaseList.get(position)).getPic()));
        } catch (IOException e) {
            Toast.makeText(this.ct, "Problems with the image", Toast.LENGTH_SHORT).show();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        holder.thumbnail.setImageBitmap(bitmap);

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
    @Override
    public int getItemCount() {
        return purchaseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView title;
        TextView qty;
        TextView price;
        TextView total;

        LinearLayout rowLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.titleDin);
            qty = itemView.findViewById(R.id.qtyDin);
            price = itemView.findViewById(R.id.usdDin);
            total = itemView.findViewById(R.id.totalDin);
            rowLayout = itemView.findViewById(R.id.purchaseRowLayout);
        }
    }
}
