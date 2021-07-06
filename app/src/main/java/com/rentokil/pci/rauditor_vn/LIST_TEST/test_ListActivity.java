package com.rentokil.pci.rauditor_vn.LIST_TEST;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import com.rentokil.pci.rauditor_vn.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_vn.Photo_Editor.EditImageActivity;
import com.rentokil.pci.rauditor_vn.Photo_Editor.EmojiBSFragment;
import com.rentokil.pci.rauditor_vn.Photo_Editor.PropertiesBSFragment;
import com.rentokil.pci.rauditor_vn.Photo_Editor.StickerBSFragment;
import com.rentokil.pci.rauditor_vn.Photo_Editor.TextEditorDialogFragment;
import com.rentokil.pci.rauditor_vn.Photo_Editor.filters.FilterListener;
import com.rentokil.pci.rauditor_vn.Photo_Editor.filters.FilterViewAdapter;
import com.rentokil.pci.rauditor_vn.Photo_Editor.tools.EditingToolsAdapter;
import com.rentokil.pci.rauditor_vn.Photo_Editor.tools.ToolType;
import com.rentokil.pci.rauditor_vn.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ja.burhanrashid52.photoeditor.OnPhotoEditorListener;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;
import ja.burhanrashid52.photoeditor.PhotoFilter;
import ja.burhanrashid52.photoeditor.SaveSettings;
import ja.burhanrashid52.photoeditor.TextStyleBuilder;
import ja.burhanrashid52.photoeditor.ViewType;

public class test_ListActivity extends AppCompatActivity implements OnPhotoEditorListener,
        View.OnClickListener,
        PropertiesBSFragment.Properties,
        EmojiBSFragment.EmojiListener,
        StickerBSFragment.StickerListener, EditingToolsAdapter.OnItemSelected, FilterListener {


    Button btnDisplay;
    ImageButton btnAdd;


    EditText input1;

    TextView addcmt1,cmt1;

    String mCurrentPhotoPath;
    Bitmap ins_bitmap_et1;
    Bitmap get_bitmap_et1;
    private FilterViewAdapter mFilterViewAdapter = new FilterViewAdapter(this);
    public static final int RequestPermissionCode = 1;
    Bitmap bitmap_et1;
    byte [] bytes_et1=null;
    ByteArrayInputStream imageStream1;
    Bitmap bitmapImage1;
    String bitmapget1;

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
    //    private EditingToolsAdapter mEditingToolsAdapter = new EditingToolsAdapter(this);
//    private FilterViewAdapter mFilterViewAdapter = new FilterViewAdapter(this);
    private ConstraintLayout mRootView;
    private ConstraintSet mConstraintSet = new ConstraintSet();
    private boolean mIsFilterVisible;
    private static final String TAG = EditImageActivity.class.getSimpleName();
    ImageView img_bitmap,img_send;
    static Context mContext;
    private EditingToolsAdapter mEditingToolsAdapter = new EditingToolsAdapter(this);
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;

    ImageView imgUndo;
    ImageView imgRedo;
    ImageView imgCamera;
    ImageView imgGallery;
    ImageView imgSave;
    ImageView imgClose;
    String ImageCheck = "";
    Uri uri;

    de.hdodenhof.circleimageview.CircleImageView Image_layout_Q1;

    Dialog dialog;
    LinearLayout linearLayoutForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xtest_main);
        dialog= new Dialog(test_ListActivity.this,R.style.MyAlertDialogTheme);

        EnableRuntimePermission();
        linearLayoutForm = (LinearLayout) this.findViewById(R.id.linearLayoutForm);

        Log.e("KKKMMM","enter 1");

        final LinearLayout newView = (LinearLayout) this.getLayoutInflater().inflate(R.layout.xtest_listitem, null);
        newView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Image_layout_Q1 = (de.hdodenhof.circleimageview.CircleImageView)  newView.findViewById(R.id.image_layout_Q1_1);


        addcmt1 = (TextView) newView.findViewById(R.id.addcmt1);
        cmt1 = (TextView) newView.findViewById(R.id.cmt1);


        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        add(this, btnAdd);
        display(this, btnDisplay);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    public  void display(final Activity activity, Button btn)
    {
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String get_rd1 = null;
                //  LinearLayout scrollViewlinerLayout = (LinearLayout) activity.findViewById(R.id.linearLayoutForm);
                final java.util.ArrayList<String> msg = new ArrayList<String>();
                final java.util.ArrayList<String> edit_msg = new ArrayList<String>();
                final java.util.ArrayList<String> cmt_msg = new ArrayList<String>();
                final java.util.ArrayList<Bitmap> bitmap_msg = new ArrayList<Bitmap>();

                Bitmap ins_bitmap_et1;

                byte [] bytes_et1=null;

                for (int i = 0; i < linearLayoutForm.getChildCount(); i++)
                {
                    final LinearLayout innerLayout = (LinearLayout) linearLayoutForm.getChildAt(i);
                    final RadioGroup rd_btn=(RadioGroup) innerLayout.findViewById(R.id.rd1);

                    try {
                        try {
                            get_rd1 = ((RadioButton) innerLayout.findViewById(rd_btn.getCheckedRadioButtonId())).getText().toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        EditText edit = (EditText) innerLayout.findViewById(R.id.ipm_sum_description);
                        TextView gt_cmt = (TextView) innerLayout.findViewById(R.id.cmt1);
                        ImageView image_view_1 = (ImageView) innerLayout.findViewById(R.id.image_layout_Q1_1);

                        ins_bitmap_et1 = ((BitmapDrawable)image_view_1.getDrawable()).getBitmap();



                        bitmap_msg.add(ins_bitmap_et1);


                        Log.e("KDDDFFDS","img = "+bitmap_msg.toString());


                        String get_cmt = gt_cmt.getText().toString();

                        String get_comment = get_cmt.replace("Remarks: ","");

//                        try {
//                            edit_msg.add(edit.getText().toString());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                        try {
                            msg.add(get_rd1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            cmt_msg.add(get_comment);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //  bitmap_msg.add()
                    } catch (Exception e) {

                        Log.e("KDDDFFDS","error = "+e.getMessage());
                        e.printStackTrace();
                    }
                }
                Log.e("KDDDFF","edit = "+edit_msg.toString());
                Log.e("KDDDFF","msg = "+msg.toString());


                Toast t = Toast.makeText(activity.getApplicationContext(), msg.toString(), Toast.LENGTH_SHORT);
                Toast t1 = Toast.makeText(activity.getApplicationContext(), edit_msg.toString(), Toast.LENGTH_SHORT);
                Toast t2 = Toast.makeText(activity.getApplicationContext(), cmt_msg.toString(), Toast.LENGTH_SHORT);

                t.show();
                t1.show();
                t2.show();
            }
        });
    }

    public  void add(final Activity activity, ImageButton btn) {


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final LinearLayout newView = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.xtest_listitem, null);
                newView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ImageButton btnRemove = (ImageButton) newView.findViewById(R.id.btnRemove);

                final TextView addcmt1, cmt1;



                TextView Q1_Imageview = (TextView)  newView.findViewById(R.id.image_Q1);
                Image_layout_Q1 = (de.hdodenhof.circleimageview.CircleImageView)  newView.findViewById(R.id.image_layout_Q1_1);

                Image_layout_Q1.setVisibility(View.GONE);





                Q1_Imageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        ImageCheck = "1111";
                        alert_instruction();
                    }
                });


                Image_layout_Q1.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(test_ListActivity.this);
                        builder.setMessage(getString(R.string.dialog_delete_img));
                        builder.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        Image_layout_Q1.setImageResource(0);

                                        Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                        if (Image_layout_Q1.getDrawable() == null) {
                                            Image_layout_Q1.setVisibility(View.GONE);
                                        } else {
                                            Image_layout_Q1.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });


                        builder.setCancelable(false);
                        builder.show();


                        return false;
                    }
                });


                addcmt1 = (TextView) newView.findViewById(R.id.addcmt1);
                cmt1 = (TextView) newView.findViewById(R.id.cmt1);

                addcmt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final EditText input1;
                        Log.e("KKKMMM", "enter 3");

                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle("Actions/Comments");
                        builder.setIcon(R.drawable.commenticon);


                        input1 = new EditText(activity);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT);
                        input1.setLayoutParams(lp);
                        builder.setView(input1);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                CharSequence contentTitle7 = "Remarks: ";
                                cmt1.setText(Html.fromHtml("" + "<font> <font color='#65096E'><b>" + contentTitle7 + "</font></font> " + "<font> <font color='#00898B'><i>" + input1.getText().toString() + "</font></font> "));

                                String get_comment1 = cmt1.getText().toString();

                                if (get_comment1.equalsIgnoreCase("Remarks: ")) {

                                    cmt1.setText("");

                                }

//                        Toast.makeText(getApplicationContext(), "Text entered is " + input.getText().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();


                    }
                });


                btnRemove.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        linearLayoutForm.removeView(newView);
                    }
                });
                linearLayoutForm.addView(newView);
            }
        });





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
                        "com.rentokil.pci.rauditor_vn.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 1);
            }
        }

    }


    private File createImageFile () throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
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

        final LinearLayout newView = (LinearLayout) this.getLayoutInflater().inflate(R.layout.xtest_listitem, null);
        newView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        Image_layout_Q1 = (de.hdodenhof.circleimageview.CircleImageView)  newView.findViewById(R.id.image_layout_Q1_1);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST:
                    mPhotoEditor.clearAllViews();
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    mPhotoEditorView.getSource().setImageBitmap(photo);

                    try {
                        bitmap_et1 = ((BitmapDrawable)mPhotoEditorView.getSource().getDrawable()).getBitmap();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    if (bitmap_et1!=null) {
                        ByteArrayOutputStream stream_1 = new ByteArrayOutputStream();
                        bitmap_et1.compress(Bitmap.CompressFormat.PNG, 100, stream_1);
                        bytes_et1 = stream_1.toByteArray();

                        Log.e("KKKMMMMMQQ","second = "+bytes_et1);

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


        if (requestCode == 0 && resultCode == RESULT_OK) {

            //  ImageCropFunction();

        } else if (requestCode == 2) {

            if (data != null) {
                uri = data.getData();
                //     ImageCropFunction();

            }
        } else if (requestCode == 1) {

            if (data == null) {

                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);


                // Decode the image file into a Bitmap sized to fill the View
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inPurgeable = true;

                Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
                if (ImageCheck.equals("1111")) {
                    Log.e("ImageCheck1111", "" + ImageCheck.equals("1111"));
                    if (ImageCheck.equals("1111")) {
                        Image_layout_Q1.setVisibility(View.VISIBLE);
                        try {

                            Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                            bitmapImage1 = ((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
//                            if(db.get_mobile_model(sd).startsWith("samsung")) {
//                                Image_layout_Q1.setRotation(90);
//                            }
                        } catch (NullPointerException e) {

                            e.printStackTrace();
                        }
                        Log.e("IMAGEEEE", "" + Image_layout_Q1);
                    } else {
                        Image_layout_Q1.setVisibility(View.GONE);
                        Image_layout_Q1.setImageBitmap(null);
                    }

                }


            }
        }
        if (data != null) {

            Uri photoUri = data.getData();
            // Do something with the photo based on Uri\
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e1){
                Log.e("EEETT ERR","error = "+e1.getMessage());
            }

            try {
                if (ImageCheck.equals("1111")) {
                    Log.e("ImageCheck11", "" + ImageCheck.equals("1111"));
                    if (ImageCheck.equals("1111")) {

                        try {
                            Image_layout_Q1.setVisibility(View.VISIBLE);
                            Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                            bitmapImage1 = ((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
//                            if(db.get_mobile_model(sd).startsWith("samsung")) {
//                                Image_layout_Q1.setRotation(90);
//                            }
                        } catch (NullPointerException e) {
                            Image_layout_Q1.setVisibility(View.GONE);
                            e.printStackTrace();
                        }
                        Log.e("IMAGEEEEQQQ", "" + Image_layout_Q1);
                    } else {
                        Image_layout_Q1.setVisibility(View.GONE);
                        Image_layout_Q1.setImageBitmap(null);
                    }
                }



            } catch (NullPointerException e) {
                e.printStackTrace();
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
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
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


                    Log.e("LLKKKKK","no = "+ImageCheck);
                    if(ImageCheck.equals("1111")){

                        if (bitmap_et1!=null) {
                            ByteArrayOutputStream stream_1 = new ByteArrayOutputStream();
                            bitmap_et1.compress(Bitmap.CompressFormat.PNG, 100, stream_1);
                            bytes_et1 = stream_1.toByteArray();

                            Bitmap bmp = BitmapFactory.decodeByteArray(bytes_et1, 0, bytes_et1.length);

                            Log.e("LLLMMMMJJ","bit = "+bmp);

                            Image_layout_Q1.setVisibility(View.VISIBLE);
                            Image_layout_Q1.setImageBitmap(bmp);
                            Image_layout_Q1.setVisibility(View.VISIBLE);

                            dialog.cancel();

                        }

                    }



                }

                @Override
                public void onFailure(@NonNull Exception exception) {

                }
            });
        } catch (IOException e) {

            Log.e("HHGGGBB","error = "+e.getMessage());

            dialog.cancel();
            e.printStackTrace();

        }

    }

    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(test_ListActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(test_ListActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(test_ListActivity.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(test_ListActivity.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(test_ListActivity.this, new String[]{
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