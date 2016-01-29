package com.example.xusoku.zoomtest;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xusoku.util.CommonPagerAdapter;
import com.example.xusoku.util.ViewPagerScroller;
import com.example.xusoku.util.ZoomOutPageTransformer;
import com.example.xusoku.viewpagertest.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<View> viewData;
    private LayoutInflater mInflater;
    private CommonPagerAdapter adapter;
    private RelativeLayout relativeLayout;

    private View view_left;
    private View view_right;

    private  ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        view_left = (View) findViewById(R.id.view_left);
        view_right = (View) findViewById(R.id.view_right);
        mInflater = LayoutInflater.from(this);
        startWelcome();
        view_left.setOnClickListener(this);
        view_right.setOnClickListener(this);
    }

    public void startWelcome() {
         viewPager = (ViewPager) findViewById(R.id.guide_viewPager);
        ZoomOutPageTransformer transformer=new ZoomOutPageTransformer();
        viewData = new ArrayList<View>();
//        View guideView1 = mInflater.inflate(R.layout.guide_item_first, null);
//        viewData.add(guideView1);
//        View guideView2 = mInflater.inflate(R.layout.guide_item_second, null);
//        viewData.add(guideView2);
        for (int i = 0; i < 12; i++) {
           final int y=i;
            View guideView3 = mInflater.inflate(R.layout.guide_item_last, null);
            viewData.add(guideView3);
            guideView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ViewPagerActivity.this,"当前"+y,Toast.LENGTH_SHORT).show();

                }
            });
        }
        adapter = new CommonPagerAdapter(viewData);
        viewPager.setAdapter(adapter);
        transformer.initailItem(viewData);
        viewPager.setPageTransformer(false, transformer);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        // pageCount设置红缓存的页面数
        viewPager.setOffscreenPageLimit(3);
        // 设置2张图之前的间距。
        viewPager.setPageMargin(20);

        ViewPagerScroller scroller = new ViewPagerScroller(this);
        scroller.setScrollDuration(500);
        scroller.initViewPagerScroll(viewPager);  //这个是设置切换过渡时间为毫秒

    }

    private  int mPosition=0;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_left:
                if(mPosition<=0){
                    mPosition=0;
                    return;
                }
                mPosition--;
                viewPager.setCurrentItem(mPosition);
                break;
            case R.id.view_right:


                if(mPosition>=viewData.size()-1){
                    mPosition=viewData.size()-1;
                    return;
                }
                mPosition++;
                viewPager.setCurrentItem(mPosition);
                break;
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            mPosition=position;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // to refresh frameLayout
            if (relativeLayout != null) {
                relativeLayout.invalidate();
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    }
}
