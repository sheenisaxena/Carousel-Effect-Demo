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

    ArrayList<String> convertedTimeArrFromTimeZoneValue;
    String[] timeZoneArr = {"UTC -11", "UTC -10", "UTC -09", "UTC -08", "UTC -07", "UTC -06", "UTC -05", "UTC -04", "UTC -03", "UTC -02", "UTC -01", "UTC 00", "UTC +01", "UTC +02", "UTC +03", "UTC +04", "UTC +05", "UTC +06", "UTC +07", "UTC +08", "UTC +09", "UTC +10", "UTC +11", "UTC +12"};
    String[] timeZoneValueArr = {"Pacific/Niue", "Pacific/Honolulu", "America/Adak", "America/Anchorage", "America/Los_Angeles", "America/Guatemala", "America/Chicago", "America/Antigua", "America/Araguaina", "America/Noronha", "Atlantic/Cape_Verde", "Africa/Abidjan", "Africa/Addis_Ababa", "Africa/Algiers", "Africa/Blantyre", "Asia/Baku", "Asia/Aqtau", "Asia/Dhaka", "Asia/Bangkok", "Australia/Perth", "Australia/Adelaide", "Australia/Currie", "Pacific/Bougainville", "Pacific/Auckland"};
    String[][] cityArr = new String[][]{
            {"Alofi", "Aitutaki", "American Samoa", "Kiritimati", "Rarotonga"},
            {"Honolulu", "Kahului", "French Polynesia", "Rarotonga", "Papeete", "Cook Islands"},
            {"Adak"},
            {"Anchorage", "Fairbanks", "Juneau", "Gambier Islands"},
            {"Vancouver", "Seattle", "San Francisco", "Las Vegas", "Los Angeles"},
            {"Edmonton", "Calgary", "Salt Lake City", "Galapagos Islands", "San Salvador", "San Jose", "Guatemala City", "Managua"},
            {"Winnepeg", "Chicago", "New Orleans", "Mexico City", "Bogota", "Lima", "Cancun", "Panama"},
            {"Montreal", "Boston", "New York", "Miami", "Nassau", "Havana", "Caracas", "La Paz"},
            {"Halifax", "Paramaribo", "Fortaleza", "Rio de Janeiro", "Sao Paulo", "Buenos Aires", "Punta Arenas", "Santiago"},
            {"Qaanaaq", "Nuuk", "King Edward Point"},
            {"Praia"},
            {"Reykjavik", "Bissau", "Monrovia", "Jamestown", "Timbuktu", "Dakar"},
            {"Algiers", "Banguui", "Malabo", "Dublin", "Casablanca", "Windhoek", "Lisbon", "London"},
            {"Oslo", "Amsterdam", "Paris", "Madrid", "Vienna", "Rome", "Cairo", "Cape Town"},
            {"Bucharest", "Sofia", "Athens", "Istanbul", "Jerusalem", "Riyadh", "Nairobi", "Dar es Salaam"},
            {"Samara", "Tehran", "Kabul", "Doha", "Dubai", "Muscat", "Victoria", "Abu Dhabi"},
            {"Oral", "Karachi", "Islamabad", "Lahore", "Mali", "Amsterdam Island", "Port-aux-Francais", "Dushanbe"},
            {"Omsk", "Astana", "Thimpu", "Dhaka", "Astana"},
            {"Khatanga", "Hovd", "Jakarta", "Bangkok", "Hanoi"},
            {"Irkustk", "Lhasa", "Chongqing", "Shanghai", "Hong Kong", "Taipei", "Manila", "Perth"},
            {"Yakutsk", "Seoul", "Tokyo", "Osaka"},
            {"Hagatna", "Port Moresby", "Cairns", "Brisbane", "Sydney", "Melbourne", "Hobart"},
            {"Palikir", "Honiara", "Port Vila", "Magadan", "Port Vila"},
            {"Anadyr", "Majuro", "Tarawa", "Funafuti", "Suva", "Auckland", "Wellington", "Chatham Islands"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclercarouselview);

        convertedTimeArrFromTimeZoneValue = new ArrayList<>();

        // below code to get date/time of timeZoneValueArr (ex: America/Chicago) according to current date/time
        for (int i = 0; i < timeZoneValueArr.length; i++) {
            TimeZone tz = TimeZone.getTimeZone(timeZoneValueArr[i]);
            SimpleDateFormat destFormat = new SimpleDateFormat("HH:mm");
            destFormat.setTimeZone(tz);

            String result = destFormat.format(Calendar.getInstance().getTime());
            convertedTimeArrFromTimeZoneValue.add(result);
        }

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

                // belwo code to get view from adapter and accordingly its position bcoz direct properties are not working
                // as that property returns position acc to Integer.MAX_VALUE instead of size from array passed.
                TextView t = (TextView) v.findViewById(R.id.tv_dummy_view);
                int pos = Integer.parseInt(t.getText().toString());

                int length = cityArr[pos].length;

                // belwo code to set city array according to position getting from adapter
                ll_city.removeAllViews();

                for (int j = 0; j < length; j++) {
                    TextView view = new TextView(RecyclerHorizontalCarouselEffect.this);
                    view.setSingleLine(false);
                    view.setTextColor(getResources().getColor(R.color.col_white));
                    view.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.city_text_size));
                    view.setText(cityArr[pos][j]);
                    ll_city.addView(view);
                }
            }
        });
    }

    private void settingDummyData() {
        modelsArr = new ArrayList<>();
        modelsArr.add(new Model(R.drawable.image_part_001, timeZoneArr[0], convertedTimeArrFromTimeZoneValue.get(0)));
        modelsArr.add(new Model(R.drawable.image_part_002, timeZoneArr[1], convertedTimeArrFromTimeZoneValue.get(1)));
        modelsArr.add(new Model(R.drawable.image_part_003, timeZoneArr[2], convertedTimeArrFromTimeZoneValue.get(2)));
        modelsArr.add(new Model(R.drawable.image_part_004, timeZoneArr[3], convertedTimeArrFromTimeZoneValue.get(3)));
        modelsArr.add(new Model(R.drawable.image_part_005, timeZoneArr[4], convertedTimeArrFromTimeZoneValue.get(4)));
        modelsArr.add(new Model(R.drawable.image_part_006, timeZoneArr[5], convertedTimeArrFromTimeZoneValue.get(5)));
        modelsArr.add(new Model(R.drawable.image_part_007, timeZoneArr[6], convertedTimeArrFromTimeZoneValue.get(6)));
        modelsArr.add(new Model(R.drawable.image_part_008, timeZoneArr[7], convertedTimeArrFromTimeZoneValue.get(7)));
        modelsArr.add(new Model(R.drawable.image_part_009, timeZoneArr[8], convertedTimeArrFromTimeZoneValue.get(8)));
        modelsArr.add(new Model(R.drawable.image_part_010, timeZoneArr[9], convertedTimeArrFromTimeZoneValue.get(9)));
        modelsArr.add(new Model(R.drawable.image_part_011, timeZoneArr[10], convertedTimeArrFromTimeZoneValue.get(10)));
        modelsArr.add(new Model(R.drawable.image_part_012, timeZoneArr[11], convertedTimeArrFromTimeZoneValue.get(11)));
        modelsArr.add(new Model(R.drawable.image_part_013, timeZoneArr[12], convertedTimeArrFromTimeZoneValue.get(12)));
        modelsArr.add(new Model(R.drawable.image_part_014, timeZoneArr[13], convertedTimeArrFromTimeZoneValue.get(13)));
        modelsArr.add(new Model(R.drawable.image_part_015, timeZoneArr[14], convertedTimeArrFromTimeZoneValue.get(14)));
        modelsArr.add(new Model(R.drawable.image_part_016, timeZoneArr[15], convertedTimeArrFromTimeZoneValue.get(15)));
        modelsArr.add(new Model(R.drawable.image_part_017, timeZoneArr[16], convertedTimeArrFromTimeZoneValue.get(16)));
        modelsArr.add(new Model(R.drawable.image_part_018, timeZoneArr[17], convertedTimeArrFromTimeZoneValue.get(17)));
        modelsArr.add(new Model(R.drawable.image_part_019, timeZoneArr[18], convertedTimeArrFromTimeZoneValue.get(18)));
        modelsArr.add(new Model(R.drawable.image_part_020, timeZoneArr[19], convertedTimeArrFromTimeZoneValue.get(19)));
        modelsArr.add(new Model(R.drawable.image_part_021, timeZoneArr[20], convertedTimeArrFromTimeZoneValue.get(20)));
        modelsArr.add(new Model(R.drawable.image_part_022, timeZoneArr[21], convertedTimeArrFromTimeZoneValue.get(21)));
        modelsArr.add(new Model(R.drawable.image_part_023, timeZoneArr[22], convertedTimeArrFromTimeZoneValue.get(22)));
        modelsArr.add(new Model(R.drawable.image_part_024, timeZoneArr[23], convertedTimeArrFromTimeZoneValue.get(23)));
    }
}
