package com.example.huhanghao.slingtabtrip;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.huhanghao.slingtabtrip.scrolltab.ScrollableTabView;
import com.example.huhanghao.slingtabtrip.scrolltab.ScrollingTabsAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrollableTabView scrollTab = (ScrollableTabView) findViewById(R.id.scroll_tab);

        String[] mTitles = new String[8];
        mTitles[0] = "tab1";
        mTitles[1] = "tab2";
        mTitles[2] = "tab3";
        mTitles[3] = "tab4";
        mTitles[4] = "tab5";
        mTitles[5] = "tab6";
        mTitles[6] = "tab7";
        mTitles[7] = "tab8";

        scrollTab.setBackground(getMyDrawable(R.drawable.rectangle_gray_top_4radius_bg));
        ScrollingTabsAdapter scrollingTabsAdapter = new ScrollingTabsAdapter(mTitles,R.layout.app_healthmanager_tabs);
        scrollTab.setAdapter(scrollingTabsAdapter, mTitles.length);
        scrollTab.setViewPage(0);
        scrollTab.setTabCallBack(new ScrollableTabView.TabCallBack() {
            @Override
            public void onPageSelected(int position) {

            }
        });
    }

    private Drawable getMyDrawable(int drawableID){
        Resources sResouces = MApplication.getInstance().getResources();

        if( drawableID < 0 )return null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return sResouces.getDrawable(drawableID, MApplication.getInstance().getTheme());
        } else {
            return sResouces.getDrawable(drawableID);
        }
    }
}
