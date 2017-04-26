package com.carouseleffectdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_simple_carousel, btn_recycler_carousel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_simple_carousel = (Button) findViewById(R.id.btn_simple_carousel);
        btn_recycler_carousel = (Button) findViewById(R.id.btn_recycler_carousel);

        btn_simple_carousel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SimpleCarouselEffect.class));
            }
        });

        btn_recycler_carousel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecyclerHorizontalCarouselEffect.class));
            }
        });
    }
}
