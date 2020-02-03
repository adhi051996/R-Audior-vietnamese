package com.rentokil.pci.rauditor_sg.PCI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.MULTI_IMAGE_SELECTION.TedRxBottomPicker;
import com.rentokil.pci.rauditor_sg.Photo_Editor.EditImageActivity;
import com.rentokil.pci.rauditor_sg.Photo_Editor.EmojiBSFragment;
import com.rentokil.pci.rauditor_sg.Photo_Editor.PropertiesBSFragment;
import com.rentokil.pci.rauditor_sg.Photo_Editor.StickerBSFragment;
import com.rentokil.pci.rauditor_sg.Photo_Editor.TextEditorDialogFragment;
import com.rentokil.pci.rauditor_sg.Photo_Editor.filters.FilterListener;
import com.rentokil.pci.rauditor_sg.Photo_Editor.filters.FilterViewAdapter;
import com.rentokil.pci.rauditor_sg.Photo_Editor.tools.EditingToolsAdapter;
import com.rentokil.pci.rauditor_sg.Photo_Editor.tools.ToolType;
import com.rentokil.pci.rauditor_sg.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;
import ja.burhanrashid52.photoeditor.OnPhotoEditorListener;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;
import ja.burhanrashid52.photoeditor.PhotoFilter;
import ja.burhanrashid52.photoeditor.SaveSettings;
import ja.burhanrashid52.photoeditor.TextStyleBuilder;
import ja.burhanrashid52.photoeditor.ViewType;



public class PCI_Audit_Page_2 extends AppCompatActivity implements OnPhotoEditorListener,
        View.OnClickListener,
        PropertiesBSFragment.Properties,
        EmojiBSFragment.EmojiListener,
        StickerBSFragment.StickerListener, EditingToolsAdapter.OnItemSelected, FilterListener {


    Button sub_info_4, back_info_4;
    String gettt_img_1_2;
    String key_id = "";
    byte[] bytes_1;


    EditText et_area_1;

    TextView add_observation,txt_area_count;

    TextView image_Q1;
    private List<Uri> selectedUriList1;
    private ViewGroup mSelectedImagesContainer1;
    private RequestManager requestManager1;
    Bitmap bitmap_1,bitmap_2,bitmap_3,bitmap_4,bitmap_5;


    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1,cv_off;
    private  int counter=1;
    private  int img_count_1=1;




    public static final int RequestPermissionCode = 1;
    String Image_1;
    Bitmap bitmap_et1,bitmap_et1_1,bitmap_et1_2;


    Bitmap ins_bitmap_et1,ins_bitmap_et1_1,ins_bitmap_et1_2;


    Bitmap get_bitmap_et1,get_bitmap_et1_1,get_bitmap_et1_2;

    byte [] bytes_et1=null,bytes_et1_1=null,bytes_et1_2=null;




    String ImageCheck = "";
    Uri uri;

    String mCurrentPhotoPath;

    byte [] bytes_e1=null,bytes_e2=null,bytes_e3=null,bytes_e4=null,bytes_e5=null;

    public static final String EXTRA_IMAGE_PATHS = "extra_image_paths";
    private static final int CAMERA_REQUEST = 52;
    private static final int PICK_REQUEST = 53;
    private PhotoEditor mPhotoEditor;
    private PhotoEditorView mPhotoEditorView;
    private PropertiesBSFragment mPropertiesBSFragment;
    private EmojiBSFragment mEmojiBSFragment;
    private StickerBSFragment mStickerBSFragment;
    private TextView mTxtCurrentTool;
    private Typeface mWonderFont;
    private RecyclerView mRvTools, mRvFilters;
    private EditingToolsAdapter mEditingToolsAdapter = new EditingToolsAdapter(this);
    private FilterViewAdapter mFilterViewAdapter = new FilterViewAdapter(this);
    private ConstraintLayout mRootView;
    private ConstraintSet mConstraintSet = new ConstraintSet();
    private boolean mIsFilterVisible;
    private static final String TAG = EditImageActivity.class.getSimpleName();
    ImageView img_bitmap,img_send;
    Dialog dialog;

    EditText input1,input2;
    String get_input1,get_input2;

    ImageView imgUndo;
    ImageView imgRedo;
    ImageView imgCamera;
    ImageView imgGallery;
    ImageView imgSave;
    ImageView imgClose;
    private Disposable multiImageDisposable1;

    EditText et_sum_1,et_sum_2;

    String get_area_1,get_txt2,get_txt2_1,get_txt3,get_txt3_2,get_sum_1,get_sum_2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pci__audit__page_2);


        EnableRuntimePermission();
        db = new DatabaseHelper(PCI_Audit_Page_2.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv_off = new ContentValues();

        Intent intent2 = getIntent();
        key_id = intent2.getStringExtra("key_id");

        Log.e("KKKMMMM",""+key_id);

        dialog= new Dialog(PCI_Audit_Page_2.this,R.style.MyAlertDialogTheme);


        image_Q1 = (TextView) findViewById(R.id.image_Q1);

        sub_info_4 = (Button) findViewById(R.id.sub_info_4);
        back_info_4 = (Button) findViewById(R.id.back_info_4);

        requestManager1 = Glide.with(this);



        mSelectedImagesContainer1 = findViewById(R.id.selected_photos_container1);



        et_area_1 = (EditText) findViewById(R.id.et_area_1);

        et_sum_1 = (EditText) findViewById(R.id.et_sum_1);

        et_sum_2 = (EditText) findViewById(R.id.et_sum_2);


        et_area_1.setSingleLine(false);
        et_sum_1.setSingleLine(false);
        et_sum_2.setSingleLine(false);



        image_Q1.setOnClickListener(view -> {
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {

                    multiImageDisposable1 = TedRxBottomPicker.with(PCI_Audit_Page_2.this)
                            //.setPeekHeight(getResources().getDisplayMetrics().heightPixels/2)
                            .setPeekHeight(1600)
                            .showTitle(false)
                            .setCompleteButtonText("Done")
                            .setEmptySelectionText("No Select")

                            .setSelectedUriList(selectedUriList1)
                            .setSelectMaxCount(5)
                            .showMultiImage()
                            .subscribe(uris -> {
                                selectedUriList1 = uris;
                                Log.e("HJHJHJH URL", "" + selectedUriList1.size());
                                Log.e("HJHJHJH URL", "" + selectedUriList1);
                                showUriList_1(uris);
                            }, Throwable::printStackTrace);


                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    Toast.makeText(PCI_Audit_Page_2.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                }


            };

            checkPermission(permissionlistener);
        });


        mSelectedImagesContainer1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position  = (Integer) v.getTag();
                Toast.makeText(PCI_Audit_Page_2.this, ""+position, Toast.LENGTH_SHORT).show();

                return false;
            }


        });


        add_observation = (TextView) findViewById(R.id.add_observation);
        txt_area_count = (TextView) findViewById(R.id.txt_area_count);



        sub_info_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validation()) {
                    insert_offline("final");
                }
                else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT ).show();
                }

            }
        });
        back_info_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(PCI_Audit_Page_2.this, PCI_Title_Page_1.class);
                i.putExtra("key_id", key_id);
                startActivity(i);

            }
        });






        add_observation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (validation()) {
                    insert_offline("add");
                }
                else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT ).show();
                }


            }
        });

        if(key_id!=null){
            Log.e("KKKMMMM In",""+key_id);
            Log.e("KKKMMMM In",""+counter);
            get_offline(key_id,counter);
        }


    }

    private void checkPermission(PermissionListener permissionlistener) {
        TedPermission.with(PCI_Audit_Page_2.this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private void showUriList_1(List<Uri> uriList) {
        // Remove all views before
        // adding the new ones.
        Log.e("HJHJH900 JJJ0", "in" + uriList.size());
        mSelectedImagesContainer1.removeAllViews();

        //  iv_image.setVisibility(View.GONE);
        mSelectedImagesContainer1.setVisibility(View.VISIBLE);

        int widthPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int heightPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());


        for (Uri uri : uriList) {
            Log.e("HJHJH900 JJJ0", "in\t" + uri.toString());
            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item, null);
            ImageView thumbnail = imageHolder.findViewById(R.id.media_image);

            requestManager1
                    .load(uri.toString())
                    .apply(new RequestOptions().fitCenter())
                    .into(thumbnail);

            mSelectedImagesContainer1.addView(imageHolder);

            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(widthPixel, heightPixel));

        }

    }



    private void alert_instruction(){

        //dialog.setContentView(R.layout.activity_edit_image);
        dialog.setContentView(R.layout.activity_edit_image);
        dialog.setCancelable(false);



        mPhotoEditorView = (PhotoEditorView)dialog.findViewById(R.id.photoEditorView);
        mTxtCurrentTool = (TextView)dialog.findViewById(R.id.txtCurrentTool);
        mRvTools = (RecyclerView)dialog.findViewById(R.id.rvConstraintTools);
        mRvFilters = (RecyclerView)dialog.findViewById(R.id.rvFilterView);
        mRootView = (ConstraintLayout)dialog.findViewById(R.id.rootView);

        imgUndo = (ImageView)dialog.findViewById(R.id.imgUndo);
        imgUndo.setOnClickListener(this);

        imgRedo = (ImageView)dialog.findViewById(R.id.imgRedo);
        imgRedo.setOnClickListener(this);

        imgCamera = (ImageView)dialog.findViewById(R.id.imgCamera);
        imgCamera.setOnClickListener(this);

        imgGallery = (ImageView)dialog.findViewById(R.id.imgGallery);
        imgGallery.setOnClickListener(this);

        imgSave = (ImageView)dialog.findViewById(R.id.imgSave);
        imgSave.setOnClickListener(this);

        imgClose = (ImageView)dialog.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(this);

        mWonderFont = Typeface.createFromAsset(getAssets(), "beyond_wonderland.ttf");

        img_bitmap=(ImageView) findViewById(R.id.img_bitmap);
        img_send=(ImageView) findViewById(R.id.img_send);

        mPropertiesBSFragment = new PropertiesBSFragment();
        mEmojiBSFragment = new EmojiBSFragment();
        mStickerBSFragment = new StickerBSFragment();
        mStickerBSFragment.setStickerListener(this);
        mEmojiBSFragment.setEmojiListener(this);
        mPropertiesBSFragment.setPropertiesChangeListener(this);

        LinearLayoutManager llmTools = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRvTools.setLayoutManager(llmTools);
        mRvTools.setAdapter(mEditingToolsAdapter);

        LinearLayoutManager llmFilters = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRvFilters.setLayoutManager(llmFilters);
        mRvFilters.setAdapter(mFilterViewAdapter);


        //Typeface mTextRobotoTf = ResourcesCompat.getFont(this, R.font.roboto_medium);
        //Typeface mEmojiTypeFace = Typeface.createFromAsset(getAssets(), "emojione-android.ttf");

        mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
                .setPinchTextScalable(true) // set flag to make text scalable when pinch
                //.setDefaultTextTypeface(mTextRobotoTf)
                //.setDefaultEmojiTypeface(mEmojiTypeFace)
                .build(); // build photo editor sdk

        mPhotoEditor.setOnPhotoEditorListener(this);




        dialog.show();

    }


    private void insert_offline(String Status) {



        get_area_1=et_area_1.getText().toString();



        get_sum_1=et_sum_1.getText().toString();
        get_sum_2=et_sum_2.getText().toString();


        cv_off.put(db.MAIN_ID, ""+key_id);
        cv_off.put(db.et1, ""+get_area_1);
        cv_off.put(db.et2, ""+get_sum_1);
        cv_off.put(db.et3, ""+get_sum_2);


        if (selectedUriList1 != null) {
            if (selectedUriList1.size() != 0) {
                cv_off.put(db.IMAGE_1, "" + selectedUriList1);
            }
        }



        if(check_insert_a_main(key_id,counter)==0) {

            cv_off.put(db.COUNT, ""+counter);
            sd.insert(db.PCI_INSPEC_2, null, cv_off);
            cv_off.clear();


            if (!Status.equalsIgnoreCase("add")) {
                Intent intent =new Intent(PCI_Audit_Page_2.this, PCI_SIGN_3.class);
                intent.putExtra("key_id",key_id);
                startActivity(intent);
            }else {
                ++counter;
                txt_area_count.setText(" "+counter);
                empty();
                get_offline(key_id,counter);

            }


        }else {
            Log.e("SSSSSSCCCVV","id up = "+key_id);
            Log.e("SSSSSSCCCVV","pos up = "+counter);

            sd.update(db.PCI_INSPEC_2,  cv_off,"MAIN_ID = '" + key_id + "' AND COUNT='"+counter+"'",null);
            cv_off.clear();
            if (!Status.equalsIgnoreCase("add")) {
                Intent intent =new Intent(PCI_Audit_Page_2.this, PCI_SIGN_3.class);
                intent.putExtra("key_id",key_id);
                startActivity(intent);
            }else {
                ++counter;
                txt_area_count.setText(" "+counter);
                empty();
                get_offline(key_id,counter);

            }
        }
//
//        Cursor c6;
//        c6 = sd.rawQuery("Select * from " + db.IR_HASIL_3, null);
//        c6.moveToFirst();
//
//        String get_1 = c6.getString(c6.getColumnIndex(db.et1));
//        byte[] get_2 = c6.getBlob(c6.getColumnIndex(db.IMAGE_1));
//
//
//        Log.e("QQQQEEEE","et1 = "+get_1);
//        Log.e("QQQQEEEE","image = "+get_2);
    }

    public void empty(){
        et_area_1.getText().clear();


        et_sum_1.getText().clear();
        et_sum_2.getText().clear();



        if (selectedUriList1 != null) {
            selectedUriList1.clear();
        }
        mSelectedImagesContainer1.removeAllViews();

        //  iv_image.setVisibility(View.GONE);
        mSelectedImagesContainer1.setVisibility(View.GONE);


    }


    public int check_insert_a_main(String key_id,int count){
        String selectQuery = "SELECT * FROM "+db.PCI_INSPEC_2+" where MAIN_ID ='"+key_id+"' AND COUNT='"+count+"'";
        Cursor cursor1 = sd.rawQuery(selectQuery, null);
        cursor1.moveToFirst();
        cursor1.close();
        return cursor1.getCount();
    }

    public boolean validation() {

        get_area_1=et_area_1.getText().toString();



        get_sum_1=et_sum_1.getText().toString();
        get_sum_2=et_sum_2.getText().toString();


        if(TextUtils.isEmpty(get_area_1 ) ||TextUtils.isEmpty(get_sum_1)||TextUtils.isEmpty(get_sum_2)) {

            if(TextUtils.isEmpty(get_area_1 )){
                et_area_1.setError("Required");
            }



            if(TextUtils.isEmpty(get_sum_1)) {
                et_sum_1.setError("Required");

            }

            if(TextUtils.isEmpty(get_sum_2)) {
                et_sum_2.setError("Required");

            }



            return false;
        }


        if (get_area_1.length() == 0 ||get_sum_1.length() == 0 ||get_sum_2.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void get_offline(String id,int pos){
        Log.e("GGSSSS","id = "+id);
        Log.e("GGSSSS","pos = "+pos);

        String selectQuery = "SELECT * FROM "+db.PCI_INSPEC_2+" where MAIN_ID ='"+key_id+"' AND COUNT='"+pos+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Log.e("SSSSXXXX","count"+cursor.getCount());
        if (cursor.getCount()!=0) {
            Log.e("HHHBBB","E3");
            try {




                String count_get = cursor.getString(cursor.getColumnIndex(db.COUNT));
                String met1 = cursor.getString(cursor.getColumnIndex(db.et1));
                String met2 = cursor.getString(cursor.getColumnIndex(db.et2));
                String met3 = cursor.getString(cursor.getColumnIndex(db.et3));

                String met4 = cursor.getString(cursor.getColumnIndex(db.IMAGE_1));


                if (met4 != null) {

                    String strNew = met4.replace("[", "");
                    String strNew_1 = strNew.replace("]", "");
                    List<String> myOld = new ArrayList<String>(Arrays.asList(strNew_1.split(", ")));
                    List<Uri> newList = new ArrayList<Uri>(myOld.size());

                    for (String uri : myOld) {
                        newList.add(Uri.parse(uri));
                    }

                    selectedUriList1 = newList;
                    showUriList_1(newList);
                }


                txt_area_count = (TextView) findViewById(R.id.txt_area_count);

                txt_area_count.setText(count_get);


                et_area_1.setText(met1);

                et_sum_1.setText(met2);
                et_sum_2.setText(met3);




            } catch (Exception e) {

                Log.e("KKKKKVV","error ="+e.getMessage());
                e.printStackTrace();
            }
        } else {

            Log.e("KKKKKK","NNNN Ess");
        }
        cursor.close();
    }



    public String getStringImage (Bitmap bmp){
        String encodedImage = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bmp.setPixel(20, 100, Color.BLUE);
            byte[] imageBytes = baos.toByteArray();
            encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return encodedImage;
    }

    private void selectImage () {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(PCI_Audit_Page_2.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    ClickImageFromCamera();
                } else if (options[item].equals("Choose from Gallery")) {
                    GetImageFromGallery();
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void GetImageFromGallery () {

        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, 1);
        }

    }

    public void ClickImageFromCamera () {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.rentokil.pci.rauditor_sg.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 1);
            }
        }

    }


    private File createImageFile () throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        Log.e("onActivityResultC1", "" + data);
        Log.e("onActivityResultC2", "" + resultCode);
        Log.e("onActivityResultC3", "" + requestCode);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST:
                    mPhotoEditor.clearAllViews();
                    File f = new File(mCurrentPhotoPath);
                    try {
                        ExifInterface exif = new ExifInterface(f.getPath());
                        int orientation = exif.getAttributeInt(
                                ExifInterface.TAG_ORIENTATION,
                                ExifInterface.ORIENTATION_NORMAL);
                        int angle = 0;
                        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                            angle = 90;
                        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                            angle = 180;
                        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                            angle = 270;
                        }
                        Matrix mat = new Matrix();
                        mat.postRotate(angle);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 2;


                        Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(f),
                                null, options);
                        Bitmap bitmap1 = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
                                bmp.getHeight(), mat, true);
                        ByteArrayOutputStream outstudentstreamOutputStream = new ByteArrayOutputStream();

                        mPhotoEditorView.getSource().setImageBitmap(bitmap1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;
                case PICK_REQUEST:
                    try {
                        mPhotoEditor.clearAllViews();
                        Uri uri = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        mPhotoEditorView.getSource().setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

    }

    @Override
    public void onRequestPermissionsResult ( int RC, String per[],int[] PResult){

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {


                } else {

                }
                break;
        }

    }



    @Override
    public void onEditTextChangeListener(final View rootView, String text, int colorCode) {
        TextEditorDialogFragment textEditorDialogFragment =
                TextEditorDialogFragment.show(this, text, colorCode);
        textEditorDialogFragment.setOnTextEditorListener(new TextEditorDialogFragment.TextEditor() {
            @Override
            public void onDone(String inputText, int colorCode) {
                final TextStyleBuilder styleBuilder = new TextStyleBuilder();
                styleBuilder.withTextColor(colorCode);

                mPhotoEditor.editText(rootView, inputText, styleBuilder);
                mTxtCurrentTool.setText(R.string.label_text);
            }
        });
    }

    @Override
    public void onAddViewListener(ViewType viewType, int numberOfAddedViews) {
        Log.d(TAG, "onAddViewListener() called with: viewType = [" + viewType + "], numberOfAddedViews = [" + numberOfAddedViews + "]");
    }

    @Override
    public void onRemoveViewListener(ViewType viewType, int numberOfAddedViews) {
        Log.d(TAG, "onRemoveViewListener() called with: viewType = [" + viewType + "], numberOfAddedViews = [" + numberOfAddedViews + "]");
    }

    @Override
    public void onStartViewChangeListener(ViewType viewType) {
        Log.d(TAG, "onStartViewChangeListener() called with: viewType = [" + viewType + "]");
    }

    @Override
    public void onStopViewChangeListener(ViewType viewType) {
        Log.d(TAG, "onStopViewChangeListener() called with: viewType = [" + viewType + "]");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imgUndo:
                mPhotoEditor.undo();
                break;

            case R.id.imgRedo:
                mPhotoEditor.redo();
                break;

            case R.id.imgSave:
                saveImage();
                break;

            case R.id.imgClose:
                onBackPressed();
//
                break;

            case R.id.imgCamera:
                EnableRuntimePermission();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
// Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
// Error occurred while creating the File
                    }// Continue only if the File was successfully created
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(this,
                                "com.rentokil.pci.rauditor_sg.fileprovider", photoFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                }
                break;

            case R.id.imgGallery:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_REQUEST);
                break;
        }
    }

    @SuppressLint("MissingPermission")
    private void saveImage() {


        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator + ""
                + System.currentTimeMillis() + ".png");
        try {
            file.createNewFile();

            SaveSettings saveSettings = new SaveSettings.Builder()
                    .setClearViewsEnabled(true)
                    .setTransparencyEnabled(true)
                    .build();

            mPhotoEditor.saveAsFile(file.getAbsolutePath(), saveSettings, new PhotoEditor.OnSaveListener() {
                @Override
                public void onSuccess(@NonNull String imagePath) {

                    mPhotoEditorView.getSource().setImageURI(Uri.fromFile(new File(imagePath)));

                    try {
                        bitmap_et1 = ((BitmapDrawable)mPhotoEditorView.getSource().getDrawable()).getBitmap();
                        //   mPhotoEditorView.getSource().setImageBitmap(bitmap_et1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    try {
                        bitmap_et1_1 = ((BitmapDrawable)mPhotoEditorView.getSource().getDrawable()).getBitmap();
                        //   mPhotoEditorView.getSource().setImageBitmap(bitmap_et1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        bitmap_et1_2 = ((BitmapDrawable)mPhotoEditorView.getSource().getDrawable()).getBitmap();
                        //   mPhotoEditorView.getSource().setImageBitmap(bitmap_et1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }




                    Log.e("LLKKKKK","no = "+ImageCheck);


                }

                @Override
                public void onFailure(@NonNull Exception exception) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(PCI_Audit_Page_2.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(PCI_Audit_Page_2.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(PCI_Audit_Page_2.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(PCI_Audit_Page_2.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(PCI_Audit_Page_2.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestPermissionCode);

        }
    }

    @Override
    public void onColorChanged(int colorCode) {
        mPhotoEditor.setBrushColor(colorCode);
        mTxtCurrentTool.setText(R.string.label_brush);
    }

    @Override
    public void onOpacityChanged(int opacity) {
        mPhotoEditor.setOpacity(opacity);
        mTxtCurrentTool.setText(R.string.label_brush);
    }

    @Override
    public void onBrushSizeChanged(int brushSize) {
        mPhotoEditor.setBrushSize(brushSize);
        mTxtCurrentTool.setText(R.string.label_brush);
    }

    @Override
    public void onEmojiClick(String emojiUnicode) {
        mPhotoEditor.addEmoji(emojiUnicode);
        mTxtCurrentTool.setText(R.string.label_emoji);

    }

    @Override
    public void onStickerClick(Bitmap bitmap) {
        mPhotoEditor.addImage(bitmap);
        mTxtCurrentTool.setText(R.string.label_sticker);
    }

    private void showSaveDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you want to exit without saving image ?");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveImage();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create().show();

    }

    @Override
    public void onFilterSelected(PhotoFilter photoFilter) {
        mPhotoEditor.setFilterEffect(photoFilter);
    }

    @Override
    public void onToolSelected(ToolType toolType) {
        switch (toolType) {
            case BRUSH:
                mPhotoEditor.setBrushDrawingMode(true);
                mTxtCurrentTool.setText(R.string.label_brush);
                mPropertiesBSFragment.show(getSupportFragmentManager(), mPropertiesBSFragment.getTag());
                break;
            case TEXT:
                TextEditorDialogFragment textEditorDialogFragment = TextEditorDialogFragment.show(this);
                textEditorDialogFragment.setOnTextEditorListener(new TextEditorDialogFragment.TextEditor() {
                    @Override
                    public void onDone(String inputText, int colorCode) {
                        final TextStyleBuilder styleBuilder = new TextStyleBuilder();
                        styleBuilder.withTextColor(colorCode);

                        mPhotoEditor.addText(inputText, styleBuilder);
                        mTxtCurrentTool.setText(R.string.label_text);
                    }
                });
                break;
            case ERASER:
                mPhotoEditor.brushEraser();
                mTxtCurrentTool.setText(R.string.label_eraser);
                break;
            case FILTER:
                mTxtCurrentTool.setText(R.string.label_filter);
                showFilter(true);
                break;
            case EMOJI:
                mEmojiBSFragment.show(getSupportFragmentManager(), mEmojiBSFragment.getTag());
                break;
            case STICKER:
                mStickerBSFragment.show(getSupportFragmentManager(), mStickerBSFragment.getTag());
                break;
        }
    }


    void showFilter(boolean isVisible) {
        mIsFilterVisible = isVisible;
        mConstraintSet.clone(mRootView);

        if (isVisible) {
            mConstraintSet.clear(mRvFilters.getId(), ConstraintSet.START);
            mConstraintSet.connect(mRvFilters.getId(), ConstraintSet.START,
                    ConstraintSet.PARENT_ID, ConstraintSet.START);
            mConstraintSet.connect(mRvFilters.getId(), ConstraintSet.END,
                    ConstraintSet.PARENT_ID, ConstraintSet.END);
        } else {
            mConstraintSet.connect(mRvFilters.getId(), ConstraintSet.START,
                    ConstraintSet.PARENT_ID, ConstraintSet.END);
            mConstraintSet.clear(mRvFilters.getId(), ConstraintSet.END);
        }

        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(350);
        changeBounds.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        TransitionManager.beginDelayedTransition(mRootView, changeBounds);

        mConstraintSet.applyTo(mRootView);
    }

    @Override
    public void onBackPressed() {
        try {
            if (mIsFilterVisible) {
                showFilter(false);
                mTxtCurrentTool.setText(R.string.app_name);
            } else if (!mPhotoEditor.isCacheEmpty()) {
                showSaveDialog();
            } else {
                //            super.onBackPressed();




                dialog.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}






