package com.carouseleffectdemo;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CoverFlowAdapter extends BaseAdapter {

    private ArrayList<Model> data;
    private AppCompatActivity activity;

    public CoverFlowAdapter(AppCompatActivity context, ArrayList<Model> objects) {
        this.activity = context;
        this.data = objects;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Model getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_flow_simple_view, null, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.gameImage.setImageResource(data.get(position).getImagesource());
//        viewHolder.gameName.setText(data.get(position).getName());

        convertView.setOnClickListener(onClickListener(position));

        return convertView;
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialog_game_info);
                dialog.setCancelable(true); // dimiss when touching outside
                dialog.setTitle("Model Details");

                TextView text = (TextView) dialog.findViewById(R.id.name);
                text.setText(getItem(position).getTimezone());
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(getItem(position).getImagesource());

                dialog.show();
            }
        };
    }


    private static class ViewHolder {
        private TextView gameName;
        private ImageView gameImage;

        public ViewHolder(View v) {
            gameImage = (ImageView) v.findViewById(R.id.image);
            gameName = (TextView) v.findViewById(R.id.name);
        }
    }
}
