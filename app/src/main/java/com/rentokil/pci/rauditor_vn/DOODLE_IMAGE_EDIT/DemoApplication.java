package com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * Created by zfdang on 2016-4-15.
 */
public class DemoApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();

        // the following line is important
        Fresco.initialize(this);
    }
}
