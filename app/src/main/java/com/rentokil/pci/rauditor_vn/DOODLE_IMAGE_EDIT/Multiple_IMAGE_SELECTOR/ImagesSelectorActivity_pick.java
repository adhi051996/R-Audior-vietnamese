package com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleParams;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.models.FolderItem;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.models.FolderListContent;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.models.ImageItem;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.models.ImageListContent;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.utilities.FileUtils;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.utilities.StringUtils;
import com.rentokil.pci.rauditor_vn.FSV.FSV_BODY_2;
import com.rentokil.pci.rauditor_vn.PCI.PCI_Audit_Page_2;
import com.rentokil.pci.rauditor_vn.PC_VIR.PC_VIR_BODY_2;
import com.rentokil.pci.rauditor_vn.PTI.PTI_Audit_Page_2;
import com.rentokil.pci.rauditor_vn.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller;

public class ImagesSelectorActivity_pick extends AppCompatActivity
        implements OnImageRecyclerViewInteractionListener, OnFolderRecyclerViewInteractionListener, View.OnClickListener{
    public static final int RequestPermissionCode = 1;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final String TAG = "ImageSelector";
    private static final String ARG_COLUMN_COUNT = "column-count";

    private static final int MY_PERMISSIONS_REQUEST_STORAGE_CODE = 197;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA_CODE = 341;

    private int mColumnCount = 3;

    // custom action bars
    private ImageView mButtonBack;
    private Button mButtonConfirm;

    private RecyclerView recyclerView;

    // folder selecting related
    private View mPopupAnchorView;
    private TextView mFolderSelectButton;
    private FolderPopupWindow mFolderPopupWindow;

    private String currentFolderPath;
    private ContentResolver contentResolver;

    private File mTempImageFile;
    private static final int CAMERA_REQUEST_CODE = 694;
    String test = "2";

    ArrayList<String> aListNumbers;
    String image_all="",get_area_1="",get_sum_1="",get_sum_2="",counter="",page="";
    String key_id="";
    public static String key_id_1="";
    ArrayList<String> Check_image;
    ArrayList<String> Check_image1;
    ArrayList<String> Check_image2;
    ArrayList<String> Check_image3;
    ArrayList<String> Check_image4;
    ArrayList<String> selected;
    ArrayList<String> selected1;
    private android.app.AlertDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_selector_multiple);


        Intent intent2 = getIntent();
        image_all = intent2.getStringExtra("image_all");
        key_id = intent2.getStringExtra("key_id");
        get_area_1 = intent2.getStringExtra("get_area_1");
        get_sum_1 = intent2.getStringExtra("get_sum_1");
        get_sum_2 = intent2.getStringExtra("get_sum_2");
        counter = intent2.getStringExtra("counter");
        page = intent2.getStringExtra("page");


        if(key_id!=null||key_id!=""){

            key_id_1=key_id;

            Log.e("HAAGXGXBBXA","key_id_1 = "+key_id_1);
        }

        pd = new SpotsDialog(ImagesSelectorActivity_pick.this, R.style.Custom);

        Bundle extras = getIntent().getExtras();
        Check_image=extras.getStringArrayList("Check_image");

        try {
            if(page.equalsIgnoreCase("pc_vir_1")){

                Check_image1=extras.getStringArrayList("Check_image1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            if(page.equalsIgnoreCase("pc_vir_2")){

                Check_image2=extras.getStringArrayList("Check_image2");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(page.equalsIgnoreCase("pc_vir_3")){

                Check_image3=extras.getStringArrayList("Check_image3");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(page.equalsIgnoreCase("pc_vir_4")){

                Check_image4=extras.getStringArrayList("Check_image4");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            if(page.equalsIgnoreCase("pc_fsv_1")){

                Check_image1=extras.getStringArrayList("Check_image1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            if(page.equalsIgnoreCase("pc_fsv_2")){

                Check_image2=extras.getStringArrayList("Check_image2");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(page.equalsIgnoreCase("pc_fsv_3")){

                Check_image3=extras.getStringArrayList("Check_image3");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(page.equalsIgnoreCase("pc_fsv_4")){

                Check_image4=extras.getStringArrayList("Check_image4");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


//        Log.e("KJJHHHBBBBB","Check_image. = "+Check_image);
//        Log.e("KJJHHHBBBBB","image_all = "+image_all);


        // hide actionbar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }


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

        // get parameters from bundle
        Intent intent = getIntent();
        SelectorSettings.mMaxImageNumber = intent.getIntExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, SelectorSettings.mMaxImageNumber);
        SelectorSettings.isShowCamera = intent.getBooleanExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, SelectorSettings.isShowCamera);
        SelectorSettings.mMinImageSize = intent.getIntExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, SelectorSettings.mMinImageSize);

        selected = intent.getStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST);



        Log.e("GAGAGXVVXXA","selected = "+selected);
        Log.e("GAGAGXVVXXA","SELECTED_IMAGES = "+ ImageListContent.SELECTED_IMAGES);
        Log.e("GAGAGXVVXXA","Check_image 2 = "+ Check_image);

        String strNew_1 = null;
        try {
            String strNew = image_all.replace("[", "");
            strNew_1 = strNew.replace("]", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (image_all!=null) {

            String strNew = image_all.replace("[", "");
            strNew_1 = strNew.replace("]", "");
            aListNumbers = new ArrayList<String>(Arrays.asList(strNew_1));
            ImageListContent.SELECTED_IMAGES.clear();
            if(aListNumbers != null && aListNumbers.size() > 0) {
                Log.e("KJJHHHBBBBB","enter = " +aListNumbers);

                ImageListContent.SELECTED_IMAGES.addAll(aListNumbers);
            }
        }
        else{

            ImageListContent.SELECTED_IMAGES.clear();
        }


//        String[] strArray = new String[] {image_all};
//
//        selected = new ArrayList<String>(Arrays.asList(strArray));

        try {
            if (page.equalsIgnoreCase("pc_vir_1")) {

                if (Check_image1!=null) {

                    Log.e("KJJHHHBBBBB","enterssss");
                    ImageListContent.SELECTED_IMAGES.clear();
                    if(Check_image1 != null && Check_image1.size() > 0) {


                        ImageListContent.SELECTED_IMAGES.addAll(Check_image1);
                    }
                }


            }
            else if  (page.equalsIgnoreCase("pc_vir_2")){
                if (Check_image2!=null) {

                    Log.e("KJJHHHBBBBB","enterssss");
                    ImageListContent.SELECTED_IMAGES.clear();
                    if(Check_image2 != null && Check_image2.size() > 0) {


                        ImageListContent.SELECTED_IMAGES.addAll(Check_image2);
                    }
                }

            }

            else if  (page.equalsIgnoreCase("pc_vir_3")){
                if (Check_image3!=null) {

                    Log.e("KJJHHHBBBBB","enterssss");
                    ImageListContent.SELECTED_IMAGES.clear();
                    if(Check_image3 != null && Check_image3.size() > 0) {


                        ImageListContent.SELECTED_IMAGES.addAll(Check_image3);
                    }
                }

            }

            else if  (page.equalsIgnoreCase("pc_vir_4")){
                if (Check_image4!=null) {

                    Log.e("KJJHHHBBBBB","enterssss");
                    ImageListContent.SELECTED_IMAGES.clear();
                    if(Check_image4 != null && Check_image4.size() > 0) {


                        ImageListContent.SELECTED_IMAGES.addAll(Check_image4);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (page.equalsIgnoreCase("pc_fsv_1")) {

                if (Check_image1!=null) {

                    Log.e("KJJHHHBBBBB","enterssss");
                    ImageListContent.SELECTED_IMAGES.clear();
                    if(Check_image1 != null && Check_image1.size() > 0) {


                        ImageListContent.SELECTED_IMAGES.addAll(Check_image1);
                    }
                }


            }
            else if  (page.equalsIgnoreCase("pc_fsv_2")){
                if (Check_image2!=null) {

                    Log.e("KJJHHHBBBBB","enterssss");
                    ImageListContent.SELECTED_IMAGES.clear();
                    if(Check_image2 != null && Check_image2.size() > 0) {


                        ImageListContent.SELECTED_IMAGES.addAll(Check_image2);
                    }
                }

            }

            else if  (page.equalsIgnoreCase("pc_fsv_3")){
                if (Check_image3!=null) {

                    Log.e("KJJHHHBBBBB","enterssss");
                    ImageListContent.SELECTED_IMAGES.clear();
                    if(Check_image3 != null && Check_image3.size() > 0) {


                        ImageListContent.SELECTED_IMAGES.addAll(Check_image3);
                    }
                }

            }

            else if  (page.equalsIgnoreCase("pc_fsv_4")){
                if (Check_image4!=null) {

                    Log.e("KJJHHHBBBBB","enterssss");
                    ImageListContent.SELECTED_IMAGES.clear();
                    if(Check_image4 != null && Check_image4.size() > 0) {


                        ImageListContent.SELECTED_IMAGES.addAll(Check_image4);
                    }
                }

            }

            else {

                if (Check_image!=null) {

                    Log.e("KJJHHHBBBBB","enterssss");
                    ImageListContent.SELECTED_IMAGES.clear();
                    if(Check_image != null && Check_image.size() > 0) {


                        ImageListContent.SELECTED_IMAGES.addAll(Check_image);
                    }
                } else {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // https://stackoverflow.com/questions/41144898/android-camera-intent-fileuriexposedexception-for-sdk-24
        StrictMode.VmPolicy.Builder newbuilder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(newbuilder.build());

        // initialize widgets in custom actionbar
        mButtonBack = (ImageView) findViewById(R.id.selector_button_back);
        mButtonBack.setOnClickListener(this);

        mButtonConfirm = (Button) findViewById(R.id.selector_button_confirm);
        mButtonConfirm.setOnClickListener(this);

        // initialize recyclerview
        View rview = findViewById(R.id.image_recycerview);
        // Set the adapter





        if (rview instanceof RecyclerView) {
            Context context = rview.getContext();
            recyclerView = (RecyclerView) rview;
            if (mColumnCount <= 1) {

                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            Log.e("HAXBXBXBAHAX","key_id= "+key_id);

            DoodleParams params = new DoodleParams();
            params.mkey_id=String.valueOf(key_id);
            recyclerView.setAdapter(new ImageRecyclerViewAdapter(ImageListContent.IMAGES, this,this,selected,key_id,get_area_1,get_sum_1,get_sum_2,counter,page));

            VerticalRecyclerViewFastScroller fastScroller = (VerticalRecyclerViewFastScroller) findViewById(R.id.recyclerview_fast_scroller);
            // Connect the recycler to the scroller (to let the scroller scroll the list)
            fastScroller.setRecyclerView(recyclerView);
            // Connect the scroller to the recycler (to let the recycler scroll the scroller's handle)
            recyclerView.addOnScrollListener(fastScroller.getOnScrollListener());
        }

        // popup windows will be anchored to this view
        mPopupAnchorView = findViewById(R.id.selector_footer);

        // initialize buttons in footer
        mFolderSelectButton = (TextView) findViewById(R.id.selector_image_folder_button);
        mFolderSelectButton.setText(R.string.selector_folder_all);
        mFolderSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (mFolderPopupWindow == null) {
                    mFolderPopupWindow = new FolderPopupWindow();
                    mFolderPopupWindow.initPopupWindow(ImagesSelectorActivity_pick.this);
                }

                if (mFolderPopupWindow.isShowing()) {
                    mFolderPopupWindow.dismiss();
                } else {
                    mFolderPopupWindow.showAtLocation(mPopupAnchorView, Gravity.BOTTOM, 10, 150);
                }
            }
        });

        currentFolderPath = "";
        FolderListContent.clear();
        ImageListContent.clear();

        updateDoneButton();

        requestReadStorageRuntimePermission();
    }

    public void requestReadStorageRuntimePermission() {
        if (ContextCompat.checkSelfPermission(ImagesSelectorActivity_pick.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ImagesSelectorActivity_pick.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_STORAGE_CODE);
        } else {
            LoadFolderAndImages();
        }
    }


    public void requestCameraRuntimePermissions() {
        if (ContextCompat.checkSelfPermission(ImagesSelectorActivity_pick.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(ImagesSelectorActivity_pick.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(ImagesSelectorActivity_pick.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA_CODE);
        } else {
            launchCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_STORAGE_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    LoadFolderAndImages();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(ImagesSelectorActivity_pick.this, getString(R.string.selector_permission_error), Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_CAMERA_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    Log.e("AAAGSGSGGXXX","camera 1");
                    launchCamera();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(ImagesSelectorActivity_pick.this, getString(R.string.selector_permission_error), Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private final String[] projections = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media._ID};

    // this method is to load images and folders for all
    public void LoadFolderAndImages() {
        Log.d(TAG, "Load Folder And Images...");

        Observable.just("")
                .flatMap(new Function<String, Observable<ImageItem>>() {
                    @Override
                    public Observable<ImageItem> apply(String folder) throws Exception {
                        List<ImageItem> results = new ArrayList<>();

                        Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

                        Log.e("VVDSDDVVDDV","contentUri = "+contentUri);

                        String where = MediaStore.Images.Media.SIZE + " > " + SelectorSettings.mMinImageSize;

                        Log.e("VVDSDDVVDAXV","projections = "+projections);
                        String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC";

                        contentResolver = getContentResolver();
                        Cursor cursor = contentResolver.query(contentUri, projections, where, null, sortOrder);
                        if (cursor == null) {
                            Log.d(TAG, "call: " + "Empty images");
                        } else if (cursor.moveToFirst()) {
                            FolderItem allImagesFolderItem = null;
                            int pathCol = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                            int nameCol = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
                            int DateCol = cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED);


                            do {
                                String path = cursor.getString(pathCol);
                                String name = cursor.getString(nameCol);
                                long dateTime = cursor.getLong(DateCol);

                                Log.e("AHHCHCH","path = "+path);
                                ImageItem item = new ImageItem(name, path, dateTime);

                                // if FolderListContent is still empty, add "All Images" option
                                if(FolderListContent.FOLDERS.size() == 0) {
                                    // add folder for all image
                                    FolderListContent.selectedFolderIndex = 0;

                                    // use first image's path as cover image path

                                    Log.e("CNCNNCCSD","path = "+path);
                                    allImagesFolderItem = new FolderItem(getString(R.string.selector_folder_all), "", path);


                                    FolderListContent.addItem(allImagesFolderItem);

                                    // show camera icon ?
                                    if(SelectorSettings.isShowCamera) {
                                        results.add(ImageListContent.cameraItem);
                                        allImagesFolderItem.addImageItem(ImageListContent.cameraItem);
                                    }
                                }

                                // add image item here, make sure it appears after the camera icon
                                results.add(item);

                                // add current image item to all
                                allImagesFolderItem.addImageItem(item);

                                // find the parent folder for this image, and add path to folderList if not existed
                                String folderPath = new File(path).getParentFile().getAbsolutePath();

                                Log.e("HCHGCGSGGSCC","folderPath = "+folderPath);
                                FolderItem folderItem = FolderListContent.getItem(folderPath);

                                Log.e("NCNHCGXGAGA","folderItem = "+folderItem);
                                if (folderItem == null) {
                                    // does not exist, create it
                                    folderItem = new FolderItem(StringUtils.getLastPathSegment(folderPath), folderPath, path);

                                    Log.e("CCNCNNCNCNS","folderItem = "+folderItem);
                                    FolderListContent.addItem(folderItem);
                                }
                                folderItem.addImageItem(item);
                            } while (cursor.moveToNext());
                            cursor.close();
                        } // } else if (cursor.moveToFirst()) {
                        return Observable.fromIterable(results);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageItem>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImageItem imageItem) {
                        // Log.d(TAG, "onNext: " + imageItem.toString());
                        ImageListContent.addItem(imageItem);
                        recyclerView.getAdapter().notifyItemChanged(ImageListContent.IMAGES.size()-1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + Log.getStackTraceString(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updateDoneButton() {
        if(ImageListContent.SELECTED_IMAGES.size() == 0) {
            mButtonConfirm.setEnabled(true);
        } else {
            mButtonConfirm.setEnabled(true);
        }

        Log.e("ANANXHXHXHXHA","SELECTED_IMAGES = "+ImageListContent.SELECTED_IMAGES.size());
        Log.e("ANANXHXHXHXAX","mMaxImageNumber = "+SelectorSettings.mMaxImageNumber);

        String caption = getResources().getString(R.string.selector_action_done, ImageListContent.SELECTED_IMAGES.size(), SelectorSettings.mMaxImageNumber);
        mButtonConfirm.setText(caption);
    }

    public void OnFolderChange() {
        mFolderPopupWindow.dismiss();

        FolderItem folder = FolderListContent.getSelectedFolder();
        if( !TextUtils.equals(folder.path, this.currentFolderPath) ) {
            this.currentFolderPath = folder.path;
            mFolderSelectButton.setText(folder.name);

            ImageListContent.IMAGES.clear();
            ImageListContent.IMAGES.addAll(folder.mImages);

            Log.e("AAXHXBXBXB","folder.mImages = "+folder.mImages);
            Log.e("AAXHXBXBBG","folder.name = "+folder.name);
            Log.e("AAXHXBXBLU","folder.path = "+folder.path);
            Log.e("AAXHXBXBKJ","mImages.size() = "+folder.mImages.size());

            recyclerView.getAdapter().notifyDataSetChanged();
        } else {
            Log.d(TAG, "OnFolderChange: " + "Same folder selected, skip loading.");
        }
    }


    @Override
    public void onFolderItemInteraction(FolderItem item) {
        // dismiss popup, and update image list if necessary
        OnFolderChange();
    }

    @Override
    public void onImageItemInteraction(ImageItem item) {
        if(ImageListContent.bReachMaxNumber) {
            String hint = getResources().getString(R.string.selector_reach_max_image_hint, SelectorSettings.mMaxImageNumber);
            Toast.makeText(ImagesSelectorActivity_pick.this, hint, Toast.LENGTH_SHORT).show();
            ImageListContent.bReachMaxNumber = false;
        }

        if(item.isCamera()) {
            requestCameraRuntimePermissions();
        }

        updateDoneButton();
    }


    public void launchCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            // set the output file of camera

            try {
                mTempImageFile = FileUtils.createTmpFile(this);
            } catch (IOException e) {
                e.printStackTrace();
            }


            Log.e("AAAGSGSGGXXX","camera 3 = "+mTempImageFile);

            if (mTempImageFile != null && mTempImageFile.exists()) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTempImageFile));
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            } else {
                Log.e("AAAGSGSGGXXX","camera 4");
                Toast.makeText(this, R.string.camera_temp_file_error, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.msg_no_camera, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // after capturing image, return the image path as selected result
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (mTempImageFile != null) {
                    // notify system
                    this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(mTempImageFile)));
                    pd.show();
                    new Handler().postDelayed(new Runnable() {


                        @Override
                        public void run() {
                            // This method will be executed once the timer is over
                            // Start your app main activity
                            pd.dismiss();

                            Intent resultIntent = new Intent(ImagesSelectorActivity_pick.this,ImagesSelectorActivity_pick.class);
                            ImageListContent.clear();
                            ImageListContent.SELECTED_IMAGES.add(mTempImageFile.getAbsolutePath());
                            resultIntent.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
                            resultIntent.putExtra("key_id",key_id);
                            resultIntent.putExtra("get_area_1",get_area_1);
                            resultIntent.putExtra("get_sum_1",get_sum_1);
                            resultIntent.putExtra("get_sum_2",get_sum_2);
                            resultIntent.putExtra("page",page);
                            resultIntent.putExtra("counter",counter);
                            setResult(RESULT_OK, resultIntent);

                            startActivity(resultIntent);


                        }
                    },1300);


                }
            } else {
                // if user click cancel, delete the temp file
                while (mTempImageFile != null && mTempImageFile.exists()) {
                    boolean success = mTempImageFile.delete();
                    if (success) {
                        mTempImageFile = null;
                    }
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        if( v == mButtonBack) {
            setResult(Activity.RESULT_CANCELED);
            finish();
        } else if(v == mButtonConfirm) {


            Log.e("AAGXGFXFXFX", "SELECTED_IMAGES = " + ImageListContent.SELECTED_IMAGES);

//
//            for(int i=0; i < ImageListContent.SELECTED_IMAGES.size(); i++){
//
//
//
//
//                ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();
//                String[] array = {ImageListContent.SELECTED_IMAGES.get(i)};
//                Log.e("HXGGXXVXX","array = "+array);
//                for (String string : array) {
//                    CustomGallery item = new CustomGallery();
//                    item.sdcardPath = string;
//
//                    dataT.add(item);
//
//                    Log.e("HXGGXXVXX","item = "+item.sdcardPath);
//
//                }
//
//
//            }
//
//            Intent data = new Intent(ImagesSelectorActivity_pick.this, Display_Main.class).putExtra("all_path", ImageListContent.SELECTED_IMAGES);
//            setResult(RESULT_OK, data);
//            startActivity(data);

            Log.e("AAGAXHXHXB", "counters = " + counter);




            try {
                if (page.equalsIgnoreCase("pci")){
                    Intent data = new Intent(ImagesSelectorActivity_pick.this, PCI_Audit_Page_2.class);
                data.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
                data.putExtra("select_image", ImageListContent.SELECTED_IMAGES);
                data.putExtra("key_id", key_id);
                data.putExtra("get_area_1", get_area_1);
                data.putExtra("get_sum_1", get_sum_1);
                data.putExtra("get_sum_2", get_sum_2);
                data.putExtra("counter", counter);
                data.putExtra("pass_value", "2");
                setResult(Activity.RESULT_OK, data);
                startActivity(data);
            }
                else if(page.equalsIgnoreCase("pti")){


                    Intent data = new Intent(ImagesSelectorActivity_pick.this, PTI_Audit_Page_2.class);
                    data.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
                    data.putExtra("select_image", ImageListContent.SELECTED_IMAGES);
                    data.putExtra("key_id", key_id);
                    data.putExtra("get_area_1", get_area_1);
                    data.putExtra("get_sum_1", get_sum_1);
                    data.putExtra("get_sum_2", get_sum_2);
                    data.putExtra("counter", counter);
                    data.putExtra("pass_value", "2");
                    setResult(Activity.RESULT_OK, data);
                    startActivity(data);

                }
                else if(page.equalsIgnoreCase("pc_vir_1")){

                    Intent data = new Intent(ImagesSelectorActivity_pick.this, PC_VIR_BODY_2.class);
                    data.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
                    data.putExtra("select_image1", ImageListContent.SELECTED_IMAGES);
                    data.putExtra("key_id", key_id);
                    data.putExtra("get_area_1", get_area_1);
                    data.putExtra("pass_value", "2");
                    setResult(Activity.RESULT_OK, data);
                    startActivity(data);
                }

                else if(page.equalsIgnoreCase("pc_vir_2")){

                    Intent data = new Intent(ImagesSelectorActivity_pick.this, PC_VIR_BODY_2.class);
                    data.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
                    data.putExtra("select_image2", ImageListContent.SELECTED_IMAGES);
                    data.putExtra("key_id", key_id);
                    data.putExtra("get_area_1", get_area_1);
                    data.putExtra("pass_value", "2");
                    setResult(Activity.RESULT_OK, data);
                    startActivity(data);
                }

                else if(page.equalsIgnoreCase("pc_vir_3")){

                    Intent data = new Intent(ImagesSelectorActivity_pick.this, PC_VIR_BODY_2.class);
                    data.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
                    data.putExtra("select_image3", ImageListContent.SELECTED_IMAGES);
                    data.putExtra("key_id", key_id);
                    data.putExtra("get_area_1", get_area_1);
                    data.putExtra("pass_value", "2");
                    setResult(Activity.RESULT_OK, data);
                    startActivity(data);
                }

                else if(page.equalsIgnoreCase("pc_vir_4")){

                    Intent data = new Intent(ImagesSelectorActivity_pick.this, PC_VIR_BODY_2.class);
                    data.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
                    data.putExtra("select_image4", ImageListContent.SELECTED_IMAGES);
                    data.putExtra("key_id", key_id);
                    data.putExtra("get_area_1", get_area_1);
                    data.putExtra("pass_value", "2");
                    setResult(Activity.RESULT_OK, data);
                    startActivity(data);
                }

                else if(page.equalsIgnoreCase("pc_fsv_1")){

                    Intent data = new Intent(ImagesSelectorActivity_pick.this, FSV_BODY_2.class);
                    data.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
                    data.putExtra("select_image1", ImageListContent.SELECTED_IMAGES);
                    data.putExtra("key_id", key_id);
                    data.putExtra("get_area_1", get_area_1);
                    data.putExtra("pass_value", "2");
                    setResult(Activity.RESULT_OK, data);
                    startActivity(data);
                }

                else if(page.equalsIgnoreCase("pc_fsv_2")){

                    Intent data = new Intent(ImagesSelectorActivity_pick.this, FSV_BODY_2.class);
                    data.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
                    data.putExtra("select_image2", ImageListContent.SELECTED_IMAGES);
                    data.putExtra("key_id", key_id);
                    data.putExtra("get_area_1", get_area_1);
                    data.putExtra("pass_value", "2");
                    setResult(Activity.RESULT_OK, data);
                    startActivity(data);
                }

                else if(page.equalsIgnoreCase("pc_fsv_3")){

                    Intent data = new Intent(ImagesSelectorActivity_pick.this, FSV_BODY_2.class);
                    data.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
                    data.putExtra("select_image3", ImageListContent.SELECTED_IMAGES);
                    data.putExtra("key_id", key_id);
                    data.putExtra("get_area_1", get_area_1);
                    data.putExtra("pass_value", "2");
                    setResult(Activity.RESULT_OK, data);
                    startActivity(data);
                }

                else if(page.equalsIgnoreCase("pc_fsv_4")){

                    Intent data = new Intent(ImagesSelectorActivity_pick.this, FSV_BODY_2.class);
                    data.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
                    data.putExtra("select_image4", ImageListContent.SELECTED_IMAGES);
                    data.putExtra("key_id", key_id);
                    data.putExtra("get_area_1", get_area_1);
                    data.putExtra("pass_value", "2");
                    setResult(Activity.RESULT_OK, data);
                    startActivity(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


//            Log.e("AAGAXHXHXB","IMAGE = "+ImageListContent.SELECTED_IMAGES);
//            Log.e("AAGAXHXHXB","RESULTS = "+SelectorSettings.SELECTOR_RESULTS);
//
//            String test = String.valueOf(ImageListContent.SELECTED_IMAGES);
//            Log.e("AAGAXHXHXB","test = "+test);
//
//            try {
//                Class<?> clazz = Class.forName("com.rentokil.pci.testing2.DemoActivity");
//                Intent i = new Intent(this, clazz);
//                i.putExtra("select_image",test);
//                startActivity(i);
//                finish();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }

//            Intent data = new Intent();
//            data.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
//            setResult(Activity.RESULT_OK, data);
//            finish();
        }
    }

    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(ImagesSelectorActivity_pick.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(ImagesSelectorActivity_pick.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(ImagesSelectorActivity_pick.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(ImagesSelectorActivity_pick.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(ImagesSelectorActivity_pick.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, RequestPermissionCode);

        }
    }
}
