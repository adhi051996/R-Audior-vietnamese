package com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.ImagesSelectorActivity_pick;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.SelectorSettings;
import com.rentokil.pci.rauditor_vn.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {
    public static final int RequestPermissionCode = 1;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_CODE = 732;

    String listString = "";
    String listString_new = "";
    private TextView tvResults;
    private ArrayList<String> mResults = new ArrayList<>();
    ArrayList<String> select_image;
    List<Uri> select_image_new;

    ArrayList<String> aListNumbers;
    ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,
            image13,image14,image15;
    String picked_img;
    private ViewGroup mSelectedImagesContainer1;
    private RequestManager requestManager1;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        EnableRuntimePermission();

        Toast.makeText(getApplicationContext(),"Working Perfect", Toast.LENGTH_SHORT).show();
        image1=(ImageView)  findViewById(R.id.image1);
        image2=(ImageView)  findViewById(R.id.image2);
        image3=(ImageView)  findViewById(R.id.image3);
        image4=(ImageView)  findViewById(R.id.image4);
        image5=(ImageView)  findViewById(R.id.image5);
        image6=(ImageView)  findViewById(R.id.image6);
        image7=(ImageView)  findViewById(R.id.image7);
        image8=(ImageView)  findViewById(R.id.image8);
        image9=(ImageView)  findViewById(R.id.image9);
        image10=(ImageView)  findViewById(R.id.image10);
        image11=(ImageView)  findViewById(R.id.image11);
        image12=(ImageView)  findViewById(R.id.image12);
        image13=(ImageView)  findViewById(R.id.image13);
        image14=(ImageView)  findViewById(R.id.image14);
        image15=(ImageView)  findViewById(R.id.image15);
        Log.e("KJJHHHBBBBB","Works ");
        requestManager1 = Glide.with(this);

        mSelectedImagesContainer1 = findViewById(R.id.selected_photos_container1);


        try {
            Bundle extras = getIntent().getExtras();
            select_image=extras.getStringArrayList("select_image");



            Log.e("AAHHXBXBAGWR","select_image= "+select_image);

            String result = String.join(",", select_image);

            Log.e("ABABBXBXBXBAXAAX","result = "+result);

            result=result.replaceAll(",", "\n");




//            for(String picked_img : select_image) {
//
//                Log.e("AAXGGXFXFX","sss = "+picked_img);
//
//
//                File imgFile = new  File(picked_img);
//                image1.setImageURI(Uri.fromFile(imgFile));
//
//
//                picked_img = picked_img.replaceAll("/storage","file:///storage");
//
//                Log.e("AAXGGXFXFX","after = "+picked_img);
//
//                showUriList_1(picked_img);
//            }



            for(int i=0; i < select_image.size(); i++){


                File imgFile1 = null;
                try {
                    imgFile1 = new File(select_image.get(0));
                    image1.setImageURI(Uri.fromFile(imgFile1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile2 = null;
                try {
                    imgFile2 = new File(select_image.get(1));
                    image2.setImageURI(Uri.fromFile(imgFile2));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile3 = null;
                try {
                    imgFile3 = new File(select_image.get(2));
                    image3.setImageURI(Uri.fromFile(imgFile3));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile4 = null;
                try {
                    imgFile4 = new File(select_image.get(3));
                    image4.setImageURI(Uri.fromFile(imgFile4));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile5 = null;
                try {
                    imgFile5 = new File(select_image.get(4));
                    image5.setImageURI(Uri.fromFile(imgFile5));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile6 = null;
                try {
                    imgFile6 = new File(select_image.get(5));
                    image6.setImageURI(Uri.fromFile(imgFile6));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile7 = null;
                try {
                    imgFile7 = new File(select_image.get(6));
                    image7.setImageURI(Uri.fromFile(imgFile7));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile8 = null;
                try {
                    imgFile8 = new File(select_image.get(7));
                    image8.setImageURI(Uri.fromFile(imgFile8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile9 = null;
                try {
                    imgFile9 = new File(select_image.get(8));
                    image9.setImageURI(Uri.fromFile(imgFile9));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile10 = null;
                try {
                    imgFile10 = new File(select_image.get(9));
                    image10.setImageURI(Uri.fromFile(imgFile10));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile11 = null;
                try {
                    imgFile11 = new File(select_image.get(10));
                    image11.setImageURI(Uri.fromFile(imgFile11));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile12 = null;
                try {
                    imgFile12 = new File(select_image.get(11));
                    image12.setImageURI(Uri.fromFile(imgFile12));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile13 = null;
                try {
                    imgFile13 = new File(select_image.get(12));
                    image13.setImageURI(Uri.fromFile(imgFile13));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile14 = null;
                try {
                    imgFile14 = new File(select_image.get(13));
                    image14.setImageURI(Uri.fromFile(imgFile14));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File imgFile15 = null;
                try {
                    imgFile15 = new File(select_image.get(14));
                    image15.setImageURI(Uri.fromFile(imgFile15));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.e("AAXGGXFXFX","imgFile = "+imgFile1);
                Log.e("AAXGGXFXFX","select_image.get(0) = "+select_image.get(0));















            }



            for (String s : select_image)
            {
                listString += s + "\t";
            }


            Log.e("KJJHHHBBBBB","listString = "+listString);
        } catch (Exception e) {
            e.printStackTrace();
        }



//        aListNumbers = new ArrayList<String>(Arrays.asList(select_image));

        if (Build.VERSION.SDK_INT >= 23){
// Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                    // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
        }
        Button bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // start multiple photos selector
//                Intent intent = new Intent(DemoActivity.this, ImagesSelectorActivity_pick.class);
//                // max number of images to be selected
//                intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 15);
//                // min size of image which will be shown; to filter tiny images (mainly icons)
//                intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 0);
//                // show camera or not
//                intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
//                // pass current selected images as the initial value
//                intent.putExtra("Check_image", select_image);
//                // start the selector
//                intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
//                Log.e("KJJHHHBBBBB","select_images = "+select_image);
//
//
//                startActivityForResult(intent, REQUEST_CODE);


                // start multiple photos selector
                Intent intent = new Intent(DemoActivity.this, ImagesSelectorActivity_pick.class);
                // max number of images to be selected
                intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 15);
                // min size of image which will be shown; to filter tiny images (mainly icons)
                intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 0);
                // show camera or not
                intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
                // pass current selected images as the initial value
                intent.putExtra("Check_image", select_image);
                // start the selector
                intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
                Log.e("KJJHHHBBBBB","select_images = "+select_image);


                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        tvResults = (TextView) findViewById(R.id.results);

        if (listString!=null) {
            tvResults.setText(listString);


            try {


                Uri myUri = Uri.parse(String.valueOf(select_image));


//                showUriList_1(Collections.singletonList(myUri));
            } catch (Exception e) {

                Log.e("AGGCTSTASCCA","error = "+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // get selected images from selector
        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                mResults = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);

                Log.e("AAGCGGXABCXBC","mResults = "+mResults);
                assert mResults != null;

                // show results in textview
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("Totally %d images selected:", mResults.size())).append("\n");
                for(String result : mResults) {
                    sb.append(result).append("\n");
                }
                tvResults.setText(sb.toString());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(DemoActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(DemoActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(DemoActivity.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(DemoActivity.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(DemoActivity.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, RequestPermissionCode);

        }
    }


    private void showUriList_1(String uri) {
        // Remove all views before
        // adding the new ones.
        Log.e("HAGATQRRQXX", "uriList = " + uri);
        mSelectedImagesContainer1.removeAllViews();

        //  iv_image.setVisibility(View.GONE);
        mSelectedImagesContainer1.setVisibility(View.VISIBLE);

        int widthPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int heightPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());



            Log.e("LLKKMMMJHG", "in = \t" + uri);
            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item_doodle, null);
            ImageView thumbnail = imageHolder.findViewById(R.id.media_image);

            requestManager1
                    .load(uri.toString())
                    .apply(new RequestOptions().fitCenter())
                    .into(thumbnail);

            mSelectedImagesContainer1.addView(imageHolder);


            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(widthPixel, heightPixel));



    }
}
