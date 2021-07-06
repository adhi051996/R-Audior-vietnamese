package com.rentokil.pci.rauditor_vn.Image;

import android.net.Uri;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rentokil.pci.rauditor_vn.R;

import java.util.ArrayList;

public class Main_PAGE extends AppCompatActivity {

    private GridView gvGallery;
    private GalleryAdapter galleryAdapter;
    ArrayList<Uri> mArrayUri;
    TextView tv_single_selection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


    }
}
