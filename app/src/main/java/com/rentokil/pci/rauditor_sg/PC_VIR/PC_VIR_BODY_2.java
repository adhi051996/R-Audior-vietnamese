package com.rentokil.pci.rauditor_sg.PC_VIR;

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
import com.rentokil.pci.rauditor_sg.Category_Type_Activity;
import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.MULTI_IMAGE_SELECTION.TedRxBottomPicker;
import com.rentokil.pci.rauditor_sg.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class PC_VIR_BODY_2 extends AppCompatActivity {

    TextView image_Q1,image_Q2,image_Q3,image_Q4;

    private List<Uri> selectedUriList1,selectedUriList2,selectedUriList3,selectedUriList4;
    private Disposable multiImageDisposable1,multiImageDisposable2,multiImageDisposable3,multiImageDisposable4;
    private ViewGroup mSelectedImagesContainer1,mSelectedImagesContainer2,mSelectedImagesContainer3,mSelectedImagesContainer4;
    private RequestManager requestManager1,requestManager2,requestManager3,requestManager4;


    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1,cv_off;
EditText et_remarks;

String get_remarks;
    String key_id;

    customfonts.Button_SF_Pro_Display_Semibold back_title_1,sub_title_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pc_vir_body_2);

        Toolbar mTopToolbar;

        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTopToolbar.setTitle("");
        mTopToolbar.setSubtitle("");
        setSupportActionBar(mTopToolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.menuicon);
        mTopToolbar.setOverflowIcon(drawable);

        db = new DatabaseHelper(PC_VIR_BODY_2.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv_off = new ContentValues();

        Intent intent1=getIntent();
        key_id=intent1.getStringExtra("key_id");
        Log.e("DDDDDDX"," id 1 = "+key_id);




        et_remarks = (EditText) findViewById(R.id.et_remarks);

        image_Q1 = (TextView) findViewById(R.id.image_Q1);
        image_Q2 = (TextView) findViewById(R.id.image_Q2);
        image_Q3 = (TextView) findViewById(R.id.image_Q3);
        image_Q4 = (TextView) findViewById(R.id.image_Q4);
        back_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.back_title_1);
        sub_title_1 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.sub_title_1);

        mSelectedImagesContainer1 = findViewById(R.id.selected_photos_container1);
        mSelectedImagesContainer2 = findViewById(R.id.selected_photos_container2);
        mSelectedImagesContainer3 = findViewById(R.id.selected_photos_container3);
        mSelectedImagesContainer4 = findViewById(R.id.selected_photos_container4);

        requestManager1 = Glide.with(this);
        requestManager2 = Glide.with(this);
        requestManager3 = Glide.with(this);
        requestManager4 = Glide.with(this);

        back_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(PC_VIR_BODY_2.this,PC_VIR_TITLE_1.class);
                i.putExtra("key_id", key_id);
                startActivity(i);
            }
        });

        sub_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               insert_offline("New");
            }
        });

        mSelectedImagesContainer1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position  = (Integer) v.getTag();
                Toast.makeText(PC_VIR_BODY_2.this, ""+position, Toast.LENGTH_SHORT).show();

                return false;
            }


        });

        mSelectedImagesContainer2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position  = (Integer) v.getTag();
                Toast.makeText(PC_VIR_BODY_2.this, ""+position, Toast.LENGTH_SHORT).show();

                return false;
            }


        });

        mSelectedImagesContainer3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position  = (Integer) v.getTag();
                Toast.makeText(PC_VIR_BODY_2.this, ""+position, Toast.LENGTH_SHORT).show();

                return false;
            }


        });

        mSelectedImagesContainer4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position  = (Integer) v.getTag();
                Toast.makeText(PC_VIR_BODY_2.this, ""+position, Toast.LENGTH_SHORT).show();

                return false;
            }


        });

        image_Q1.setOnClickListener(view -> {
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {

                    multiImageDisposable1 = TedRxBottomPicker.with(PC_VIR_BODY_2.this)
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
                    Toast.makeText(PC_VIR_BODY_2.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                }


            };

            checkPermission(permissionlistener);
        });

        image_Q2.setOnClickListener(view -> {
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {

                    multiImageDisposable2 = TedRxBottomPicker.with(PC_VIR_BODY_2.this)
                            //.setPeekHeight(getResources().getDisplayMetrics().heightPixels/2)
                            .setPeekHeight(1600)
                            .showTitle(false)
                            .setCompleteButtonText("Done")
                            .setEmptySelectionText("No Select")

                            .setSelectedUriList(selectedUriList2)
                            .setSelectMaxCount(5)
                            .showMultiImage()
                            .subscribe(uris -> {
                                selectedUriList2 = uris;
                                Log.e("HJHJHJH URL", "" + selectedUriList2.size());
                                Log.e("HJHJHJH URL", "" + selectedUriList2);
                                showUriList_2(uris);
                            }, Throwable::printStackTrace);


                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    Toast.makeText(PC_VIR_BODY_2.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                }


            };

            checkPermission(permissionlistener);
        });

        image_Q3.setOnClickListener(view -> {
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {

                    multiImageDisposable3 = TedRxBottomPicker.with(PC_VIR_BODY_2.this)
                            //.setPeekHeight(getResources().getDisplayMetrics().heightPixels/2)
                            .setPeekHeight(1600)
                            .showTitle(false)
                            .setCompleteButtonText("Done")
                            .setEmptySelectionText("No Select")

                            .setSelectedUriList(selectedUriList3)
                            .setSelectMaxCount(5)
                            .showMultiImage()
                            .subscribe(uris -> {
                                selectedUriList3 = uris;
                                Log.e("HJHJHJH URL", "" + selectedUriList3.size());
                                Log.e("HJHJHJH URL", "" + selectedUriList3);
                                showUriList_3(uris);
                            }, Throwable::printStackTrace);


                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    Toast.makeText(PC_VIR_BODY_2.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                }


            };

            checkPermission(permissionlistener);
        });

        image_Q4.setOnClickListener(view -> {
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {

                    multiImageDisposable4 = TedRxBottomPicker.with(PC_VIR_BODY_2.this)
                            //.setPeekHeight(getResources().getDisplayMetrics().heightPixels/2)
                            .setPeekHeight(1600)
                            .showTitle(false)
                            .setCompleteButtonText("Done")
                            .setEmptySelectionText("No Select")

                            .setSelectedUriList(selectedUriList4)
                            .setSelectMaxCount(5)
                            .showMultiImage()
                            .subscribe(uris -> {
                                selectedUriList4 = uris;
                                Log.e("HJHJHJH URL", "" + selectedUriList4.size());
                                Log.e("HJHJHJH URL", "" + selectedUriList4);
                                showUriList_4(uris);
                            }, Throwable::printStackTrace);


                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    Toast.makeText(PC_VIR_BODY_2.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                }


            };

            checkPermission(permissionlistener);
        });

        if(key_id!=null){
            get_offline(key_id);
        }

    }

    private void checkPermission(PermissionListener permissionlistener) {
        TedPermission.with(PC_VIR_BODY_2.this)
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

    private void showUriList_2(List<Uri> uriList) {
        // Remove all views before
        // adding the new ones.
        Log.e("HJHJH900 JJJ0", "in" + uriList.size());
        mSelectedImagesContainer2.removeAllViews();

        //  iv_image.setVisibility(View.GONE);
        mSelectedImagesContainer2.setVisibility(View.VISIBLE);

        int widthPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int heightPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());


        for (Uri uri : uriList) {
            Log.e("HJHJH900 JJJ0", "in\t" + uri.toString());
            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item, null);
            ImageView thumbnail = imageHolder.findViewById(R.id.media_image);

            requestManager2
                    .load(uri.toString())
                    .apply(new RequestOptions().fitCenter())
                    .into(thumbnail);

            mSelectedImagesContainer2.addView(imageHolder);

            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(widthPixel, heightPixel));

        }

    }

    private void showUriList_3(List<Uri> uriList) {
        // Remove all views before
        // adding the new ones.
        Log.e("HJHJH900 JJJ0", "in" + uriList.size());
        mSelectedImagesContainer3.removeAllViews();

        //  iv_image.setVisibility(View.GONE);
        mSelectedImagesContainer3.setVisibility(View.VISIBLE);

        int widthPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int heightPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());


        for (Uri uri : uriList) {
            Log.e("HJHJH900 JJJ0", "in\t" + uri.toString());
            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item, null);
            ImageView thumbnail = imageHolder.findViewById(R.id.media_image);

            requestManager3
                    .load(uri.toString())
                    .apply(new RequestOptions().fitCenter())
                    .into(thumbnail);

            mSelectedImagesContainer3.addView(imageHolder);

            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(widthPixel, heightPixel));

        }

    }

    private void showUriList_4(List<Uri> uriList) {
        // Remove all views before
        // adding the new ones.
        Log.e("HJHJH900 JJJ0", "in" + uriList.size());
        mSelectedImagesContainer4.removeAllViews();

        //  iv_image.setVisibility(View.GONE);
        mSelectedImagesContainer4.setVisibility(View.VISIBLE);

        int widthPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int heightPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());


        for (Uri uri : uriList) {
            Log.e("HJHJH900 JJJ0", "in\t" + uri.toString());
            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item, null);
            ImageView thumbnail = imageHolder.findViewById(R.id.media_image);

            requestManager4
                    .load(uri.toString())
                    .apply(new RequestOptions().fitCenter())
                    .into(thumbnail);

            mSelectedImagesContainer4.addView(imageHolder);

            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(widthPixel, heightPixel));

        }

    }


    private void insert_offline(String Status) {

        get_remarks=et_remarks.getText().toString();

        if (selectedUriList1 != null) {
            if (selectedUriList1.size() != 0) {
                cv_off.put(db.IMG_URL_1, "" + selectedUriList1);
            }
        }

        if (selectedUriList2 != null) {
            if (selectedUriList2.size() != 0) {
                cv_off.put(db.IMG_URL_2, "" + selectedUriList2);
            }
        }

        if (selectedUriList3 != null) {
            if (selectedUriList3.size() != 0) {
                cv_off.put(db.IMG_URL_3, "" + selectedUriList3);
            }
        }

        if (selectedUriList4 != null) {
            if (selectedUriList4.size() != 0) {
                cv_off.put(db.IMG_URL_4, "" + selectedUriList4);
            }
        }

        cv_off.put(db.et1,""+get_remarks);


        if (check_data(key_id) == 0) {

            sd.insert(db.PC_VIR_DB_BODY_2, null, cv_off);
            cv_off.clear();

                Intent intent = new Intent(PC_VIR_BODY_2.this, PC_VIR_FUNCTION_3.class);
                intent.putExtra("key_id", key_id);
                startActivity(intent);


        } else {
            Log.e("JJJJDDD", "e2");
            sd.update(db.PC_VIR_DB_BODY_2,  cv_off,"KEY_ID = '" + key_id + "'",null);
            cv_off.clear();

            Intent intent = new Intent(PC_VIR_BODY_2.this, PC_VIR_FUNCTION_3.class);
            intent.putExtra("key_id", key_id);
            startActivity(intent);

        }

    }


    public int check_data(String key_id){
        String Query="select * from "+db.PC_VIR_DB_BODY_2 +" where KEY_ID = '"+key_id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        return cursor.getCount();
    }

    public void get_offline(String id){

        Log.e("GGGRRRR","E2"  + id);
        String Query="select * from "+db.PC_VIR_DB_BODY_2 +" where KEY_ID = '"+id+"'";
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


                et_remarks.setText(met5);

                if (met1 != null) {

                    String strNew = met1.replace("[", "");
                    String strNew_1 = strNew.replace("]", "");
                    List<String> myOld = new ArrayList<String>(Arrays.asList(strNew_1.split(", ")));
                    List<Uri> newList = new ArrayList<Uri>(myOld.size());

                    for (String uri : myOld) {
                        newList.add(Uri.parse(uri));
                    }

                    selectedUriList1 = newList;
                    showUriList_1(newList);
                }


                if (met2 != null) {

                    String strNew = met2.replace("[", "");
                    String strNew_1 = strNew.replace("]", "");
                    List<String> myOld = new ArrayList<String>(Arrays.asList(strNew_1.split(", ")));
                    List<Uri> newList = new ArrayList<Uri>(myOld.size());

                    for (String uri : myOld) {
                        newList.add(Uri.parse(uri));
                    }

                    selectedUriList2 = newList;
                    showUriList_2(newList);
                }


                if (met3 != null) {

                    String strNew = met3.replace("[", "");
                    String strNew_1 = strNew.replace("]", "");
                    List<String> myOld = new ArrayList<String>(Arrays.asList(strNew_1.split(", ")));
                    List<Uri> newList = new ArrayList<Uri>(myOld.size());

                    for (String uri : myOld) {
                        newList.add(Uri.parse(uri));
                    }

                    selectedUriList3 = newList;
                    showUriList_3(newList);
                }

                if (met4 != null) {

                    String strNew = met4.replace("[", "");
                    String strNew_1 = strNew.replace("]", "");
                    List<String> myOld = new ArrayList<String>(Arrays.asList(strNew_1.split(", ")));
                    List<Uri> newList = new ArrayList<Uri>(myOld.size());

                    for (String uri : myOld) {
                        newList.add(Uri.parse(uri));
                    }

                    selectedUriList4 = newList;
                    showUriList_4(newList);
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
        inflater.inflate(R.menu.popup_menu_vir, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.vir_home:
                Intent sir_home = new Intent(PC_VIR_BODY_2.this, Category_Type_Activity.class);
                sir_home.putExtra("key_id", key_id);
                startActivity(sir_home);
                break;
            case R.id.vir_title_page_1:
                if (key_id != null) {
                    Intent sir_customer = new Intent(PC_VIR_BODY_2.this, PC_VIR_TITLE_1.class);
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
                    Intent sir_observation = new Intent(PC_VIR_BODY_2.this, PC_VIR_FUNCTION_3.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_gen_4:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_BODY_2.this, PC_VIR_GENERAL_4.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_ppe_5:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_BODY_2.this, PC_VIR_PPE_5.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_stan_6:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_BODY_2.this, PC_VIR_STANDARD_6.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

            case R.id.vir_other_7:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PC_VIR_BODY_2.this, PC_VIR_OTHER_7.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
