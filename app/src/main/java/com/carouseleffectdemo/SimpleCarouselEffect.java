package com.carouseleffectdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class SimpleCarouselEffect extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Model> modelsArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplecarouselview);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);

        settingDummyData();

        adapter = new CoverFlowAdapter(this, modelsArr);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());

    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("MainActiivty", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }

    private void settingDummyData() {
        modelsArr = new ArrayList<>();
        modelsArr.add(new Model(R.mipmap.assassins_creed, "Assassin Creed 3", ""));
        modelsArr.add(new Model(R.mipmap.avatar_3d, "Avatar 3D", ""));
        modelsArr.add(new Model(R.mipmap.call_of_duty_black_ops_3, "Call Of Duty Black Ops 3", ""));
        modelsArr.add(new Model(R.mipmap.dota_2, "DotA 2", ""));
        modelsArr.add(new Model(R.mipmap.halo_5, "Halo 5", ""));
        modelsArr.add(new Model(R.mipmap.left_4_dead_2, "Left 4 Dead 2", ""));
        modelsArr.add(new Model(R.mipmap.starcraft, "StarCraft", ""));
        modelsArr.add(new Model(R.mipmap.the_witcher_3, "The Witcher 3", ""));
        modelsArr.add(new Model(R.mipmap.tomb_raider, "Tom raider 3", ""));
        modelsArr.add(new Model(R.mipmap.need_for_speed_most_wanted, "Need for Speed Most Wanted", ""));
    }

}
