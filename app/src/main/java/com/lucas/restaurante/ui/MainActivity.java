package com.lucas.restaurante.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.lucas.restaurante.R;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 6500;

    //Variables
    Animation topAnim, bottomAnim;
    ImageView logo;
    TextView slogan, tag;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_main);

        //Animation
        Context context;
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation );
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation );


        //Hooks
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);
        tag = findViewById(R.id.tag);

        logo.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);
        tag.setAnimation(bottomAnim);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Entry.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}

