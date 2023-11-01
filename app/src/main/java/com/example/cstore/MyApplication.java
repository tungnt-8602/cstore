package com.example.cstore;

import android.app.Application;

import com.example.cstore.control.LocalControl;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initControl();
    }

    /* **********************************************************************
     * Function
     ********************************************************************** */
    private void initControl() {
        LocalControl.getInstance().setContext(getApplicationContext());
    }
}
