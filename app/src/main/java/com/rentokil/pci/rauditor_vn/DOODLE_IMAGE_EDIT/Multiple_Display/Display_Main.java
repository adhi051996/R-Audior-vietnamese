package com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_Display;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_Display.Adapter.ImageListRecyclerAdapter;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_Display.utils.Utils;
import com.rentokil.pci.rauditor_vn.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Display_Main extends BaseActivity {


    @BindView(R.id.imgSinglePick)
    ImageView imgSinglePick;

    @BindView(R.id.btnGalleryPick)
    Button btnGalleryPick;

    @BindView(R.id.btnGalleryPickMul)
    Button btnGalleryPickMul;

    @BindView(R.id.viewSwitcher)
    ViewSwitcher viewSwitcher;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    ArrayList<String> select_image;
    String action;
    Handler handler;
    ImageListRecyclerAdapter imageListRecyclerAdapter;
    //	GalleryAdapter adapter;
    ImageLoader imageLoader;
    private HashMap<String, CustomGallery> imagesUri;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.multiple_display);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        select_image=extras.getStringArrayList("all_path");
        init();
        Log.e("ACANCNACCA","all_path = "+select_image);

        String result = String.join(",", select_image);
        result=result.replaceAll(",", "\n");


        for(int i=0; i < select_image.size(); i++){




            ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();
            String[] array = {select_image.get(i)};
            Log.e("ACANCNACCA","array= "+array);
            for (String string : array) {
                CustomGallery item = new CustomGallery();
                item.sdcardPath = string;

                Log.e("AANXNHAHHXC","item = "+item.sdcardPath);

                dataT.add(item);
            }

            viewSwitcher.setDisplayedChild(0);
            imageListRecyclerAdapter.addAll(dataT);
        }


        initImageLoader();


    }

    public void initImageLoader() {

        imageLoader = Utils.initImageLoader(getActivity());



    }


    private void init() {

        handler = new Handler();
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
//		gridGallery.setFastScrollEnabled(true);
        imageListRecyclerAdapter = new ImageListRecyclerAdapter(getApplicationContext());
        imageListRecyclerAdapter.setMultiplePick(false);
        recyclerView.setAdapter(imageListRecyclerAdapter);





        viewSwitcher.setDisplayedChild(1);

        btnGalleryPick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(Action.ACTION_PICK);
                startActivityForResult(i, 100);

            }
        });

//		btnGalleryPickMul = (Button) findViewById(R.id.btnGalleryPickMul);
        btnGalleryPickMul.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
                startActivityForResult(i, 200);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("AANXNHAHHXC","works = ");


        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            imageListRecyclerAdapter.clear();

            viewSwitcher.setDisplayedChild(1);
            String single_path = data.getStringExtra("single_path");
            imageLoader.displayImage("file://" + single_path, imgSinglePick);

        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            String[] all_path = data.getStringArrayExtra("all_path");

            ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();

            for (String string : all_path) {
                CustomGallery item = new CustomGallery();
                item.sdcardPath = string;

                Log.e("AANXNHAHHXC","item.sdcardPaths = "+item.sdcardPath);

                dataT.add(item);
            }

            viewSwitcher.setDisplayedChild(0);
            imageListRecyclerAdapter.addAll(dataT);
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
