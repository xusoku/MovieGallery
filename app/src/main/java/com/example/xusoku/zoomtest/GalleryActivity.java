package com.example.xusoku.zoomtest;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.xusoku.util.CommonBaseAdapter;
import com.example.xusoku.util.ViewHolder;
import com.example.xusoku.view.MovieGallery;
import com.example.xusoku.viewpagertest.R;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private ArrayList<String> list;

    private Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources localResources =getApplication().getResources();
        Configuration localConfiguration = new Configuration();
        localConfiguration.setToDefaults();
        localResources.updateConfiguration(localConfiguration, localResources.getDisplayMetrics());

        btn1= (Button) findViewById(R.id.btn1);
        btn2= (Button) findViewById(R.id.btn2);
        MovieGallery moga = (MovieGallery) findViewById(R.id.movieGallery);
        moga.setZoomRatio(0.33F);

        list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        moga.setAdapter(new CommonBaseAdapter<String>(this, list, R.layout.gallery_item) {
            @Override
            public void convert(ViewHolder holder, String itemData, int position) {

            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalleryActivity.this,Gallery1Activity.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalleryActivity.this,ViewPagerActivity.class));
            }
        });
    }

}
