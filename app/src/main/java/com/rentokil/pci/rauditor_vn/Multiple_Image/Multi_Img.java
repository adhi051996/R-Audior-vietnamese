package com.rentokil.pci.rauditor_vn.Multiple_Image;

import android.net.Uri;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.rentokil.pci.rauditor_vn.R;

import java.util.ArrayList;


public class Multi_Img extends AppCompatActivity {

    private ImageView ivImage1, ivImage2, ivImage3, ivImage4, ivImage5, ivImage6;
    private GridView gvGallery;
    private Sir_GalleryAdapter sirGalleryAdapter;
    ArrayList<Uri> mArrayUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_img);
    }


}
