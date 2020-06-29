package com.lucas.restaurante.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lucas.restaurante.R;
import com.lucas.restaurante.adapters.OptionAdapter;
import com.lucas.restaurante.dao.SingletonPurchaseList;

public class Option extends AppCompatActivity {

    private RecyclerView recOpt;
    TextView total;
    Button cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        Integer position = getIntent().getIntExtra("category",1);

        recOpt = this.findViewById(R.id.optionRecycler);
        OptionAdapter optionAdapter = new OptionAdapter(this, position);
        recOpt.setAdapter(optionAdapter);
        recOpt.setLayoutManager(new LinearLayoutManager(this));

        cart = findViewById(R.id.cart);
        total = findViewById(R.id.total);

        cart.setText("See Cart "+"("+SingletonPurchaseList.getInstance(this).getCount()+")");
        total.setText("Total: "+ SingletonPurchaseList.getInstance(this).getSum());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Option.this, Entry.class);
        this.startActivity(intent);
    }


    public void onClickCart(View view) {
        super.onBackPressed();
        Intent intent = new Intent(Option.this, Purchase.class);
        this.startActivity(intent);
    }
}
