package com.rentokil.pci.rauditor_vn.LIST_TEST;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.rentokil.pci.rauditor_vn.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_vn.Photo_Editor.EditImageActivity;
import com.rentokil.pci.rauditor_vn.Photo_Editor.EmojiBSFragment;
import com.rentokil.pci.rauditor_vn.Photo_Editor.PropertiesBSFragment;
import com.rentokil.pci.rauditor_vn.Photo_Editor.StickerBSFragment;
import com.rentokil.pci.rauditor_vn.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class LogRow extends AppCompatActivity  {

    String mCurrentPhotoPath;
    Bitmap ins_bitmap_et1;
    Bitmap get_bitmap_et1;
    public static final int RequestPermissionCode = 1;
    Bitmap bitmap_et1;
    byte [] bytes_et1=null;
    ByteArrayInputStream imageStream1;
    Bitmap bitmapImage1;
    String bitmapget1;
    ImageView Image_layout_Q1;
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


    public static void display(final Activity activity, Button btn)
    {
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String get_rd1 = null;
                LinearLayout scrollViewlinerLayout = (LinearLayout) activity.findViewById(R.id.linearLayoutForm);
                final java.util.ArrayList<String> msg = new ArrayList<String>();
                final java.util.ArrayList<String> edit_msg = new ArrayList<String>();
                final java.util.ArrayList<String> cmt_msg = new ArrayList<String>();



                for (int i = 0; i < scrollViewlinerLayout.getChildCount(); i++)
                {
                    final LinearLayout innerLayout = (LinearLayout) scrollViewlinerLayout.getChildAt(i);
                    final RadioGroup  rd_btn=(RadioGroup) innerLayout.findViewById(R.id.rd1);

                    try {
                        get_rd1 = ((RadioButton) innerLayout.findViewById(rd_btn.getCheckedRadioButtonId())).getText().toString();
//                        EditText edit = (EditText) innerLayout.findViewById(R.id.ipm_sum_description);
                        TextView gt_cmt = (TextView) innerLayout.findViewById(R.id.cmt1);

                        String get_cmt = gt_cmt.getText().toString();

                        String get_comment = get_cmt.replace("Remarks: ","");

//                        edit_msg.add(edit.getText().toString());
                        msg.add(get_rd1);
                        cmt_msg.add(get_comment);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//                Log.e("KDDDFF","edit = "+edit_msg.toString());
                Log.e("KDDDFF","msg = "+msg.toString());
                Log.e("KDDDFF","cmt = "+cmt_msg.toString());

                Toast t = Toast.makeText(activity.getApplicationContext(), msg.toString(), Toast.LENGTH_SHORT);
                Toast t1 = Toast.makeText(activity.getApplicationContext(), edit_msg.toString(), Toast.LENGTH_SHORT);
                Toast t2 = Toast.makeText(activity.getApplicationContext(), cmt_msg.toString(), Toast.LENGTH_SHORT);

                t.show();
                t1.show();
                t2.show();
            }
        });
    }

    public static void add(final Activity activity, ImageButton btn) {
        final LinearLayout linearLayoutForm = (LinearLayout) activity.findViewById(R.id.linearLayoutForm);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final LinearLayout newView = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.xtest_listitem, null);
                newView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ImageButton btnRemove = (ImageButton) newView.findViewById(R.id.btnRemove);

                final TextView addcmt1, cmt1;
                final ImageView Image_layout_Q1;

                TextView Q1_Imageview = (TextView)  newView.findViewById(R.id.image_Q1);
                Image_layout_Q1 = (de.hdodenhof.circleimageview.CircleImageView)  newView.findViewById(R.id.image_layout_Q1);
                Image_layout_Q1.setVisibility(View.GONE);

                Image_layout_Q1.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {



                        Log.e("LLLKKMMMMM","enter 1 ") ;

                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage("Are you sure want to delete this image?");
                        builder.setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        Image_layout_Q1.setImageResource(0);

                                        Toast.makeText(activity, "Image Deleted", Toast.LENGTH_SHORT).show();


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

                Q1_Imageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




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

//    public void alert_instruction(){
//
//
//        Dialog dialog= new Dialog(LogRow.this,R.style.MyAlertDialogTheme);
//        dialog.setContentView(R.layout.activity_edit_image);
//        dialog.setCancelable(false);
//
//
//
//        mPhotoEditorView = dialog.findViewById(R.id.photoEditorView);
//        mTxtCurrentTool = dialog.findViewById(R.id.txtCurrentTool);
//        mRvTools = dialog.findViewById(R.id.rvConstraintTools);
//        mRvFilters = dialog.findViewById(R.id.rvFilterView);
//        mRootView = dialog.findViewById(R.id.rootView);
//
////        imgUndo = dialog.findViewById(R.id.imgUndo);
////        imgUndo.setOnClickListener(this);
////
////        imgRedo = dialog.findViewById(R.id.imgRedo);
////        imgRedo.setOnClickListener(this);
////
////        imgCamera = dialog.findViewById(R.id.imgCamera);
////        imgCamera.setOnClickListener(this);
////
////        imgGallery = dialog.findViewById(R.id.imgGallery);
////        imgGallery.setOnClickListener(this);
////
////        imgSave = dialog.findViewById(R.id.imgSave);
////        imgSave.setOnClickListener(this);
////
////        imgClose = dialog.findViewById(R.id.imgClose);
////        imgClose.setOnClickListener(this);
//
//        mWonderFont = Typeface.createFromAsset(getAssets(), "beyond_wonderland.ttf");
//
//        img_bitmap=(ImageView) findViewById(R.id.img_bitmap);
//        img_send=(ImageView) findViewById(R.id.img_send);
//
//        mPropertiesBSFragment = new PropertiesBSFragment();
//        mEmojiBSFragment = new EmojiBSFragment();
//        mStickerBSFragment = new StickerBSFragment();
////        mStickerBSFragment.setStickerListener(this);
////        mEmojiBSFragment.setEmojiListener(this);
////        mPropertiesBSFragment.setPropertiesChangeListener(this);
////
////        LinearLayoutManager llmTools = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
////        mRvTools.setLayoutManager(llmTools);
////        mRvTools.setAdapter(mEditingToolsAdapter);
////
////        LinearLayoutManager llmFilters = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
////        mRvFilters.setLayoutManager(llmFilters);
////        mRvFilters.setAdapter(mFilterViewAdapter);
////
////
////        //Typeface mTextRobotoTf = ResourcesCompat.getFont(this, R.font.roboto_medium);
////        //Typeface mEmojiTypeFace = Typeface.createFromAsset(getAssets(), "emojione-android.ttf");
////
////        mPhotoEditor = new PhotoEditor.Builder(this, mPhotoEditorView)
////                .setPinchTextScalable(true) // set flag to make text scalable when pinch
////                //.setDefaultTextTypeface(mTextRobotoTf)
////                //.setDefaultEmojiTypeface(mEmojiTypeFace)
////                .build(); // build photo editor sdk
////
////        mPhotoEditor.setOnPhotoEditorListener(this);
//
//
//
//
//        dialog.show();
//
//    }

}