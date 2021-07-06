package com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_Display;


import androidx.appcompat.app.AppCompatActivity;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_Display.utils.Utils;


/**
 * Created by Kartum Infotech (Bhavesh Hirpara) on 09-Jul-18.
 */
public class BaseActivity extends AppCompatActivity {

    ImageLoader imageLoader;;

    public void initImageLoader() {
        try {
            imageLoader = Utils.initImageLoader(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaseActivity getActivity() {
        return this;
    }


}
