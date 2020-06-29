package com.lucas.restaurante.ui;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lucas.restaurante.R;
import com.lucas.restaurante.adapters.PurchaseAdapter;
import com.lucas.restaurante.dao.SingletonPurchaseList;

public class Purchase extends AppCompatActivity {

    RecyclerView recPur;
    private Integer foodPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        foodPos = getIntent().getIntExtra("foodPos", 1);

        recPur = this.findViewById(R.id.purchaseRecycler);
        PurchaseAdapter purchaseAdapter = new PurchaseAdapter(this, SingletonPurchaseList.getInstance(this).getArray());
        recPur.setAdapter(purchaseAdapter);
        recPur.setLayoutManager(new LinearLayoutManager(this));

    }


    public void onClickClear(View view) {

        SingletonPurchaseList.getInstance(this).cleanArray(this);
        PurchaseAdapter purchaseAdapter = new PurchaseAdapter(this, SingletonPurchaseList.getInstance(this).getArray());
        recPur.setAdapter(purchaseAdapter);
        recPur.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Purchase.this, Option.class);
        intent.putExtra("category", foodPos);
        this.startActivity(intent);
        super.onBackPressed();
    }
}
