package com.rentokil.pci.rauditor_sg.PTI;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
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


public class PTI_Audit_Page_2 extends AppCompatActivity {


    Button sub_info_4, back_info_4;
    String key_id = "";
    EditText et_area_1;
    TextView txt_area_count;
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1,cv_off;
    private  int counter=1;
    public static final int RequestPermissionCode = 1;
    Dialog dialog;
    EditText et_sum_1,et_sum_2;
    String get_area_1,get_sum_1,get_sum_2;

    TextView image_Q1;
    private List<Uri> selectedUriList1;
    private ViewGroup mSelectedImagesContainer1;
    private RequestManager requestManager1;
    private Disposable multiImageDisposable1;
    Toolbar mTopToolbar;
    customfonts.Button_SF_Pro_Display_Semibold add_location_2,previous_obs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pti__audit__page_2);


        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTopToolbar.setTitle("");
        mTopToolbar.setSubtitle("");
        setSupportActionBar(mTopToolbar);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.menuicon);
        mTopToolbar.setOverflowIcon(drawable);

        EnableRuntimePermission();
        db = new DatabaseHelper(PTI_Audit_Page_2.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv_off = new ContentValues();

        Intent intent2 = getIntent();
        key_id = intent2.getStringExtra("key_id");

        Log.e("KKKMMMM",""+key_id);

        dialog= new Dialog(PTI_Audit_Page_2.this,R.style.MyAlertDialogTheme);

        image_Q1 = (TextView) findViewById(R.id.image_Q1);
        add_location_2 = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.add_location_2);
        previous_obs = (customfonts.Button_SF_Pro_Display_Semibold) findViewById(R.id.previous_obs);

        sub_info_4 = (Button) findViewById(R.id.sub_info_4);
        back_info_4 = (Button) findViewById(R.id.back_info_4);




        requestManager1 = Glide.with(this);



        mSelectedImagesContainer1 = findViewById(R.id.selected_photos_container1);


        image_Q1.setOnClickListener(view -> {
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {

                    multiImageDisposable1 = TedRxBottomPicker.with(PTI_Audit_Page_2.this)
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
                    Toast.makeText(PTI_Audit_Page_2.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                }


            };

            checkPermission(permissionlistener);
        });


        mSelectedImagesContainer1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position  = (Integer) v.getTag();
                Toast.makeText(PTI_Audit_Page_2.this, ""+position, Toast.LENGTH_SHORT).show();

                return false;
            }


        });




        et_area_1 = (EditText) findViewById(R.id.et_area_1);

        et_sum_1 = (EditText) findViewById(R.id.et_sum_1);

        et_sum_2 = (EditText) findViewById(R.id.et_sum_2);


        et_area_1.setSingleLine(false);
        et_sum_1.setSingleLine(false);
        et_sum_2.setSingleLine(false);








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

                Intent i = new Intent(PTI_Audit_Page_2.this, PTI_Title_Page_1.class);
                i.putExtra("key_id", key_id);
                startActivity(i);

            }
        });




        add_location_2.setOnClickListener(new View.OnClickListener() {
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

        previous_obs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (counter > 0) {
                    if (counter != 1) {
                        counter--;
                    }
                    txt_area_count.setText(" " + counter);
                    try {
                        get_offline(key_id, counter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No Observation", Toast.LENGTH_SHORT).show();
                }
            }
        });



        if(key_id!=null){
            Log.e("KKKMMMM In",""+key_id);
            Log.e("KKKMMMM In",""+counter);
            get_offline(key_id,counter);
        }


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


    private void checkPermission(PermissionListener permissionlistener) {
        TedPermission.with(PTI_Audit_Page_2.this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
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
            sd.insert(db.PTI_INSPEC_2, null, cv_off);
            cv_off.clear();


            if (!Status.equalsIgnoreCase("add")) {
                Intent intent =new Intent(PTI_Audit_Page_2.this, PTI_SIGN_3.class);
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

            sd.update(db.PTI_INSPEC_2,  cv_off,"MAIN_ID = '" + key_id + "' AND COUNT='"+counter+"'",null);
            cv_off.clear();
            if (!Status.equalsIgnoreCase("add")) {
                Intent intent =new Intent(PTI_Audit_Page_2.this, PTI_SIGN_3.class);
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
        String selectQuery = "SELECT * FROM "+db.PTI_INSPEC_2+" where MAIN_ID ='"+key_id+"' AND COUNT='"+count+"'";
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

        String selectQuery = "SELECT * FROM "+db.PTI_INSPEC_2+" where MAIN_ID ='"+key_id+"' AND COUNT='"+pos+"'";
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

                txt_area_count = (TextView) findViewById(R.id.txt_area_count);

                txt_area_count.setText(count_get);


                et_area_1.setText(met1);

                et_sum_1.setText(met2);
                et_sum_2.setText(met3);

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





            } catch (Exception e) {

                Log.e("KKKKKVV","error ="+e.getMessage());
                e.printStackTrace();
            }
        } else {

            Log.e("KKKKKK","NNNN Ess");
        }
        cursor.close();
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




    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(PTI_Audit_Page_2.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(PTI_Audit_Page_2.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(PTI_Audit_Page_2.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(PTI_Audit_Page_2.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(PTI_Audit_Page_2.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestPermissionCode);

        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu_pti, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pti_home:
                Intent sir_home = new Intent(PTI_Audit_Page_2.this, Category_Type_Activity.class);
                sir_home.putExtra("key_id", key_id);
                startActivity(sir_home);
                break;
            case R.id.pti_title_page_1:

                if (key_id != null) {
                    Intent sir_customer = new Intent(PTI_Audit_Page_2.this, PTI_Title_Page_1.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }
                break;
            case R.id.pti_obs_2:
                Toast.makeText(getApplicationContext(),"Already,You Are in Same Page",Toast.LENGTH_SHORT).show();
                break;
            case R.id.pti_sign_3:
                if (key_id != null) {
                    Intent sir_observation = new Intent(PTI_Audit_Page_2.this, PTI_SIGN_3.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }




}






