package com.carouseleffectdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class RecyclerHorizontalCarouselEffect extends AppCompatActivity {

    private ArrayList<Model> modelsArr;

    RecyclerView recycleViewImages;
    RecycleHorizontalImages recycleHorizontalImages;
    LinearLayoutManager layoutManager;
    int visibleItemCount, totalItemCount, pastVisiblesItems;
    LinearLayout ll_city;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclercarouselview);



        // set model data
        settingDummyData();

        ll_city = (LinearLayout) findViewById(R.id.ll_city);
        recycleViewImages = (RecyclerView) findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(RecyclerHorizontalCarouselEffect.this, LinearLayoutManager.HORIZONTAL, false);
        recycleViewImages.setLayoutManager(layoutManager);
        recycleHorizontalImages = new RecycleHorizontalImages(RecyclerHorizontalCarouselEffect.this, modelsArr);
        recycleViewImages.setAdapter(recycleHorizontalImages);

        // belwo line added to make recyclerview scroll on left side also with infinite loop
        layoutManager.scrollToPosition(Integer.MAX_VALUE / 2);

        recycleViewImages.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // belwo code to display selected effect on middle recyclerview item
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                // below for loop added to remove selected effect on previous recyclerview item.
                for (int i = 0; i < recycleViewImages.getChildCount(); i++) {
                    recycleViewImages.getChildAt(i).setAlpha(1);
                }
                View v = recycleViewImages.getChildAt(visibleItemCount / 2);
                v.setAlpha((float) 0.6);

            }
        });
    }

    private void settingDummyData() {
        modelsArr = new ArrayList<>();
        modelsArr.add(new Model(R.mipmap.assassins_creed, "", ""));
        modelsArr.add(new Model(R.mipmap.avatar_3d, "", ""));
        modelsArr.add(new Model(R.mipmap.call_of_duty_black_ops_3, "", ""));
        modelsArr.add(new Model(R.mipmap.dota_2, "", ""));
        modelsArr.add(new Model(R.mipmap.halo_5, "", ""));
        modelsArr.add(new Model(R.mipmap.left_4_dead_2, "", ""));
        modelsArr.add(new Model(R.mipmap.starcraft, "", ""));
        modelsArr.add(new Model(R.mipmap.the_witcher_3, "", ""));
        modelsArr.add(new Model(R.mipmap.tomb_raider, "", ""));
        modelsArr.add(new Model(R.mipmap.need_for_speed_most_wanted, "", ""));
    }
}
