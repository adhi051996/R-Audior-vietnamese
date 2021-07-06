package com.rentokil.pci.rauditor_vn.FSV;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.rentokil.pci.rauditor_vn.Category_Type_Activity;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.ImagesSelectorActivity_pick;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.SelectorSettings;
import com.rentokil.pci.rauditor_vn.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_vn.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class FSV_BODY_2 extends AppCompatActivity {

    TextView image_Q1,image_Q2,image_Q3,image_Q4;

    private List<Uri> selectedUriList1,selectedUriList2,selectedUriList3,selectedUriList4;
    private Disposable multiImageDisposable1,multiImageDisposable2,multiImageDisposable3,multiImageDisposable4;
    private RequestManager requestManager1,requestManager2,requestManager3,requestManager4;


    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1,cv_off;
EditText et_remarks;
    CheckBox checkbox_1;
String get_remarks;
    String key_id;

    customfonts.Button_SF_Pro_Display_Semibold back_title_1,sub_title_1;
    String get_check1;



    ArrayList<String> select_image1= new ArrayList<String>();
    ArrayList<String> select_image2= new ArrayList<String>();
    ArrayList<String> select_image3= new ArrayList<String>();
    ArrayList<String> select_image4= new ArrayList<String>();
    private ArrayList<String> mResults = new ArrayList<>();
    private static final int REQUEST_CODE = 732;
    String get_area_1="",pass_value="1";

    private ViewGroup mSelectedImagesContainer1,mSelectedImagesContainer2,mSelectedImagesContainer3,mSelectedImagesContainer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fsv_body_2);

        mSelectedImagesContainer1 = findViewById(R.id.selected_photos_container1);
        mSelectedImagesContainer2 = findViewById(R.id.selected_photos_container2);
        mSelectedImagesContainer3 = findViewById(R.id.selected_photos_container3);
        mSelectedImagesContainer4 = findViewById(R.id.selected_photos_container4);

        

        Toolbar mTopToolbar;

        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTopToolbar.setTitle("");
        mTopToolbar.setSubtitle("");
        setSupportActionBar(mTopToolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.menuicon);
        mTopToolbar.setOverflowIcon(drawable);

        db = new DatabaseHelper(FSV_BODY_2.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv_off = new ContentValues();

        Intent intent1=getIntent();
        key_id=intent1.getStringExtra("key_id");
        Log.e("DDDDDDX"," id 1 = "+key_id);
        pass_value = intent1.getStringExtra("pass_value");
        get_area_1 = intent1.getStringExtra("get_area_1");

        if(pass_value==null){

            pass_value="1";
        }



        et_remarks = (EditText) findViewById(R.id.et_remarks);
        checkbox_1=(CheckBox) findViewById(R.id.checkbox_1);

        image_Q1 = (TextView) findViewById(R.id.image_Q1);
        image_Q2 = (TextView) findViewById(R.id.image_Q2);
        image_Q3 = (TextView) findViewById(R.id.image_Q3);
        image_Q4 = (TextView) findViewById(R.id.image_Q4);
        back_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.back_title_1);
        sub_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.sub_title_1);



        requestManager1 = Glide.with(this);
        requestManager2 = Glide.with(this);
        requestManager3 = Glide.with(this);
        requestManager4 = Glide.with(this);

        checkbox_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_1.isChecked()) {
                    get_check1 = "Hazmat Placard is present";
                } else {
                    get_check1 = "";
                }
            }
        });


        back_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(FSV_BODY_2.this,FSV_TITLE_1.class);
                i.putExtra("key_id", key_id);
                startActivity(i);
            }
        });

        sub_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               insert_offline("next");
            }
        });


        image_Q1.setOnClickListener(view -> {
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {

                    insert_offline("New");

                    get_remarks=et_remarks.getText().toString();
                    Intent intent = new Intent(FSV_BODY_2.this, ImagesSelectorActivity_pick.class);
                    // max number of images to be selected
                    intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 15);
                    // min size of image which will be shown; to filter tiny images (mainly icons)
                    intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 0);
                    // show camera or not
                    intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
                    // pass current selected images as the initial value
                    intent.putExtra("Check_image1", select_image1);
                    intent.putExtra("key_id", key_id);

                    intent.putExtra("get_area_1", get_remarks);
                    intent.putExtra("page", "pc_fsv_1");
                    // start the selector
                    intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
                    startActivityForResult(intent, REQUEST_CODE);

                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    Toast.makeText(FSV_BODY_2.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                }


            };

            checkPermission(permissionlistener);
        });

        image_Q2.setOnClickListener(view -> {
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    insert_offline("New");
                    get_remarks=et_remarks.getText().toString();
                    Intent intent = new Intent(FSV_BODY_2.this, ImagesSelectorActivity_pick.class);
                    // max number of images to be selected
                    intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 15);
                    // min size of image which will be shown; to filter tiny images (mainly icons)
                    intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 0);
                    // show camera or not
                    intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
                    // pass current selected images as the initial value
                    intent.putExtra("Check_image2", select_image2);
                    intent.putExtra("key_id", key_id);

                    intent.putExtra("get_area_1", get_remarks);
                    intent.putExtra("page", "pc_fsv_2");
                    // start the selector
                    intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
                    startActivityForResult(intent, REQUEST_CODE);
                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    Toast.makeText(FSV_BODY_2.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                }


            };

            checkPermission(permissionlistener);
        });

        image_Q3.setOnClickListener(view -> {
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    insert_offline("New");
                    get_remarks=et_remarks.getText().toString();
                    Intent intent = new Intent(FSV_BODY_2.this, ImagesSelectorActivity_pick.class);
                    // max number of images to be selected
                    intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 15);
                    // min size of image which will be shown; to filter tiny images (mainly icons)
                    intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 0);
                    // show camera or not
                    intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
                    // pass current selected images as the initial value
                    intent.putExtra("Check_image3", select_image3);
                    intent.putExtra("key_id", key_id);

                    intent.putExtra("get_area_1", get_remarks);
                    intent.putExtra("page", "pc_fsv_3");
                    // start the selector
                    intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
                    startActivityForResult(intent, REQUEST_CODE);

                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    Toast.makeText(FSV_BODY_2.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                }


            };

            checkPermission(permissionlistener);
        });

        image_Q4.setOnClickListener(view -> {
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    insert_offline("New");
                    get_remarks=et_remarks.getText().toString();
                    Intent intent = new Intent(FSV_BODY_2.this, ImagesSelectorActivity_pick.class);
                    // max number of images to be selected
                    intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 15);
                    // min size of image which will be shown; to filter tiny images (mainly icons)
                    intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 0);
                    // show camera or not
                    intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
                    // pass current selected images as the initial value
                    intent.putExtra("Check_image4", select_image4);
                    intent.putExtra("key_id", key_id);

                    intent.putExtra("get_area_1", get_remarks);
                    intent.putExtra("page", "pc_fsv_4");
                    // start the selector
                    intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
                    startActivityForResult(intent, REQUEST_CODE);

                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    Toast.makeText(FSV_BODY_2.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                }


            };

            checkPermission(permissionlistener);
        });


        try {
            Bundle extras = getIntent().getExtras();
            select_image1=extras.getStringArrayList("select_image1");
            select_image2=extras.getStringArrayList("select_image2");
            select_image3=extras.getStringArrayList("select_image3");
            select_image4=extras.getStringArrayList("select_image4");


            Log.e("AAHHXBXBAGWR","select_image 1 = "+select_image1);
            Log.e("AAHHXBXBAGWR","select_image 2 = "+select_image2);
            Log.e("AAHHXBXBAGWR","select_image 3 = "+select_image3);
            Log.e("AAHHXBXBAGWR","select_image 4 = "+select_image4);

//            String result = String.join(",", select_image);
//
//            Log.e("ABABBXBXBXBAXAAX","result = "+result);
//
//            result=result.replaceAll(",", "\n");

            if(select_image1!=null) {
                showUriList1(select_image1);
            }

            if(select_image2!=null) {
                showUriList2(select_image2);
            }

            if(select_image3!=null) {
                showUriList3(select_image3);
            }

            if(select_image4!=null) {
                showUriList4(select_image4);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        if(key_id!=null){
            get_offline(key_id);
        }

    }

    private void showUriList1(ArrayList<String> uriList) {
        // Remove all views before
        // adding the new ones.

        Log.e("MMMMMMMMM","RRRRR"+uriList.size());
        mSelectedImagesContainer1.removeAllViews();
        Log.e("MMMMMMMMM","RRRRR"+uriList.size());
        //  iv_image.setVisibility(View.GONE);
        mSelectedImagesContainer1.setVisibility(View.VISIBLE);

        int widthPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int heightPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

        Log.e("MMMMMMMM JJJ0", "in\t" + uriList.size());
        for (String uri : uriList) {
            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item, null);
            ImageView thumbnail = imageHolder.findViewById(R.id.media_image);
            Log.e("MMMMMMMM JJJ0", "in\t" + uri.toString());
            requestManager1
                    .load(uri.toString())
                    .apply(new RequestOptions().fitCenter())
                    .into(thumbnail);

            mSelectedImagesContainer1.addView(imageHolder);
            Log.e("MMMMMMMM","111 "+uriList.size());
            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(widthPixel, heightPixel));

        }

    }

    private void showUriList2(ArrayList<String> uriList) {
        // Remove all views before
        // adding the new ones.

        Log.e("MMMMMMMMM","RRRRR"+uriList.size());
        mSelectedImagesContainer2.removeAllViews();
        Log.e("MMMMMMMMM","RRRRR"+uriList.size());
        //  iv_image.setVisibility(View.GONE);
        mSelectedImagesContainer2.setVisibility(View.VISIBLE);

        int widthPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int heightPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

        Log.e("MMMMMMMM JJJ0", "in\t" + uriList.size());
        for (String uri : uriList) {
            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item, null);
            ImageView thumbnail = imageHolder.findViewById(R.id.media_image);
            Log.e("MMMMMMMM JJJ0", "in\t" + uri.toString());
            requestManager2
                    .load(uri.toString())
                    .apply(new RequestOptions().fitCenter())
                    .into(thumbnail);

            mSelectedImagesContainer2.addView(imageHolder);
            Log.e("MMMMMMMM","111 "+uriList.size());
            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(widthPixel, heightPixel));

        }

    }

    private void showUriList3(ArrayList<String> uriList) {
        // Remove all views before
        // adding the new ones.

        Log.e("MMMMMMMMM","RRRRR"+uriList.size());
        mSelectedImagesContainer3.removeAllViews();
        Log.e("MMMMMMMMM","RRRRR"+uriList.size());
        //  iv_image.setVisibility(View.GONE);
        mSelectedImagesContainer3.setVisibility(View.VISIBLE);

        int widthPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int heightPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

        Log.e("MMMMMMMM JJJ0", "in\t" + uriList.size());
        for (String uri : uriList) {
            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item, null);
            ImageView thumbnail = imageHolder.findViewById(R.id.media_image);
            Log.e("MMMMMMMM JJJ0", "in\t" + uri.toString());
            requestManager3
                    .load(uri.toString())
                    .apply(new RequestOptions().fitCenter())
                    .into(thumbnail);

            mSelectedImagesContainer3.addView(imageHolder);
            Log.e("MMMMMMMM","111 "+uriList.size());
            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(widthPixel, heightPixel));

        }

    }

    private void showUriList4(ArrayList<String> uriList) {
        // Remove all views before
        // adding the new ones.

        Log.e("MMMMMMMMM","RRRRR"+uriList.size());
        mSelectedImagesContainer4.removeAllViews();
        Log.e("MMMMMMMMM","RRRRR"+uriList.size());
        //  iv_image.setVisibility(View.GONE);
        mSelectedImagesContainer4.setVisibility(View.VISIBLE);

        int widthPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int heightPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

        Log.e("MMMMMMMM JJJ0", "in\t" + uriList.size());
        for (String uri : uriList) {
            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item, null);
            ImageView thumbnail = imageHolder.findViewById(R.id.media_image);
            Log.e("MMMMMMMM JJJ0", "in\t" + uri.toString());
            requestManager4
                    .load(uri.toString())
                    .apply(new RequestOptions().fitCenter())
                    .into(thumbnail);

            mSelectedImagesContainer4.addView(imageHolder);
            Log.e("MMMMMMMM","111 "+uriList.size());
            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(widthPixel, heightPixel));

        }

    }


    private void checkPermission(PermissionListener permissionlistener) {
        TedPermission.with(FSV_BODY_2.this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    private void insert_offline(String Status) {

        get_remarks=et_remarks.getText().toString();

        if (select_image1 != null) {
            if (select_image1.size() != 0) {
                cv_off.put(db.IMG_URL_1, "" + select_image1);
            }
        }

        if (select_image2 != null) {
            if (select_image2.size() != 0) {
                cv_off.put(db.IMG_URL_2, "" + select_image2);
            }
        }

        if (select_image3 != null) {
            if (select_image3.size() != 0) {
                cv_off.put(db.IMG_URL_3, "" + select_image3);
            }
        }
        if (select_image4 != null) {
            if (select_image4.size() != 0) {
                cv_off.put(db.IMG_URL_4, "" + select_image4);
            }
        }

        cv_off.put(db.et1, ""+get_remarks);

        if (checkbox_1.isChecked()) {
            cv_off.put(db.et2, "" + get_check1);
        } else {
            cv_off.put(db.et2, "");
        }


        cv_off.put(db.MAIN_ID, "" + key_id);

        if (check_data(key_id) == 0) {

            Log.e("GGRRWWSSA", "e1 insert ="+key_id);

            sd.insert(db.FSV_DB_BODY_2, null, cv_off);
            cv_off.clear();

            if(Status.equalsIgnoreCase("next")) {
                Intent intent = new Intent(FSV_BODY_2.this, FSV_GENERAL_3.class);
                intent.putExtra("key_id", key_id);
                startActivity(intent);

            }

        } else {
            Log.e("GGRRWWSSA", "e1 update ="+key_id);
            sd.update(db.FSV_DB_BODY_2,  cv_off,"KEY_ID = '" + key_id + "'",null);
            cv_off.clear();
            if(Status.equalsIgnoreCase("next")) {
                Intent intent = new Intent(FSV_BODY_2.this, FSV_GENERAL_3.class);
                intent.putExtra("key_id", key_id);
                startActivity(intent);
            }
        }

    }


    public int check_data(String key_id){
        String Query="select * from "+db.FSV_DB_BODY_2 +" where MAIN_ID = '"+key_id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        return cursor.getCount();
    }

    public void get_offline(String id){

        Log.e("GGGRRRR","E2"  + id);
        String Query="select * from "+db.FSV_DB_BODY_2 +" where MAIN_ID = '"+id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        Log.e("KKKKKKKGG","E3 = "+cursor.getCount());

        if (cursor.getCount()!=0) {

            Log.e("YYYYRRR","E9");
            try {


                String met1 = cursor.getString(cursor.getColumnIndex(db.IMG_URL_1));
                String met2 = cursor.getString(cursor.getColumnIndex(db.IMG_URL_2));
                String met3 = cursor.getString(cursor.getColumnIndex(db.IMG_URL_3));
                String met4 = cursor.getString(cursor.getColumnIndex(db.IMG_URL_4));
                String met5 = cursor.getString(cursor.getColumnIndex(db.et1));
                String met6 = cursor.getString(cursor.getColumnIndex(db.et2));




                et_remarks.setText(met5);
                if (met1 != null) {

                    String strNew = met1.replace("[", "");
                    String strNew_1 = strNew.replace("]", "");
                    select_image1 = new ArrayList<String>(Arrays.asList(strNew_1.split(", ")));


                    showUriList1(select_image1);

                }


                if (met2 != null) {

                    String strNew = met2.replace("[", "");
                    String strNew_1 = strNew.replace("]", "");
                    select_image2 = new ArrayList<String>(Arrays.asList(strNew_1.split(", ")));

                    showUriList2(select_image2);
                }

                if (met3 != null) {

                    String strNew = met3.replace("[", "");
                    String strNew_1 = strNew.replace("]", "");
                    select_image3 = new ArrayList<String>(Arrays.asList(strNew_1.split(", ")));

                    showUriList3(select_image3);

                }

                if (met4 != null) {

                    String strNew = met4.replace("[", "");
                    String strNew_1 = strNew.replace("]", "");
                    select_image4 = new ArrayList<String>(Arrays.asList(strNew_1.split(", ")));



                    showUriList4(select_image4);
                }

                try {
                    if (met6.equalsIgnoreCase("")) {
                    } else {
                        checkbox_1.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } catch (Exception e) {

                Log.e("FFFFVV","error ="+e.getMessage());
                e.printStackTrace();
            }
        } else {

            Log.e("OOPPP","NNNN Ess");
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu_fsv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.vir_home:
                Intent sir_home = new Intent(FSV_BODY_2.this, Category_Type_Activity.class);
                sir_home.putExtra("key_id", key_id);
                startActivity(sir_home);
                break;
            case R.id.vir_title_page_1:
                if (key_id != null) {
                    Intent sir_customer = new Intent(FSV_BODY_2.this, FSV_TITLE_1.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }
                break;
            case R.id.vir_body_2:
                Toast.makeText(getApplicationContext(),"Already,You Are in Same Page",Toast.LENGTH_SHORT).show();
                break;
            case R.id.vir_funct_3:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_BODY_2.this, FSV_GENERAL_3.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_gen_4:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_BODY_2.this, FSV_STANDARD_TOOL_4.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_ppe_5:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_BODY_2.this, FSV_STANDARD_ITEM_5.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_stan_6:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_BODY_2.this, FSV_EMERGENCY_6.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_other_7:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_BODY_2.this, FSV_SIGN_7.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
