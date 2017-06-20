package com.example.huhanghao.slingtabtrip;

import android.app.Application;
import android.content.res.Resources;

/**
 * Created by huhanghao on 2017/6/20.
 */

public class MApplication extends Application {


    private static MApplication INSTANCE;
    public Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

    }

    public static MApplication getInstance() {
        return INSTANCE;
    }
}
