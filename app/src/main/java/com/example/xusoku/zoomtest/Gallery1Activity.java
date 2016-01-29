package com.example.xusoku.zoomtest;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.xusoku.util.CommonBaseAdapter;
import com.example.xusoku.util.ViewHolder;
import com.example.xusoku.view.MovieGallery1;
import com.example.xusoku.viewpagertest.R;

import java.util.ArrayList;

public class Gallery1Activity extends AppCompatActivity {

    private ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_main);

        supportBigSizeFont();
        MovieGallery1 moga = (MovieGallery1) findViewById(R.id.movieGallery1);

        list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        moga.setAdapter(new CommonBaseAdapter<String>(this, list, R.layout.gallery1_item) {
            @Override
            public void convert(ViewHolder holder, String itemData, int position) {

            }
        });



    }
    public  void supportBigSizeFont()
    {
        Resources localResources =getApplication().getResources();
        Configuration localConfiguration = new Configuration();
        localConfiguration.setToDefaults();
        localResources.updateConfiguration(localConfiguration, localResources.getDisplayMetrics());
    }
}
