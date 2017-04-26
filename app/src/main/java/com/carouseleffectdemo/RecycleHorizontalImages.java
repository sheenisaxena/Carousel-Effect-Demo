package com.carouseleffectdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecycleHorizontalImages extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Model> imagesModelList;

    public RecycleHorizontalImages(Context context, List<Model> imagesModelList) {
        this.context = context;
        this.imagesModelList = imagesModelList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_timezone, txt_time, tv_dummy_view;
        private ImageView MapImage;


        public MyViewHolder(View view) {
            super(view);
            MapImage = (ImageView) view.findViewById(R.id.image);
            txt_timezone = (TextView) view.findViewById(R.id.tv_timezone);
            txt_time = (TextView) view.findViewById(R.id.tv_time);
            tv_dummy_view = (TextView) view.findViewById(R.id.tv_dummy_view);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_flow_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;

        // belwo condition code added to set position of array (imagesModelList for now)
        // let say total 10 size array, if position = 11 then it will throw ArrayOutofIndex exception so to neglect that we need to set again position of arr to 0
        if (position >= imagesModelList.size()) {
            position = position % imagesModelList.size();
        }

        viewHolder.MapImage.setImageResource(imagesModelList.get(position).getImagesource());
        viewHolder.txt_timezone.setText(imagesModelList.get(position).getTimezone());
        viewHolder.txt_time.setText(imagesModelList.get(position).getTime());
//        viewHolder.txt_timezone.setText(String.valueOf(position));

        viewHolder.tv_dummy_view.setText(String.valueOf(position));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
//        return imagesModelList.size();
        return Integer.MAX_VALUE;
    }

}
