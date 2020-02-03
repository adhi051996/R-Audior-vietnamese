package com.rentokil.pci.rauditor_sg;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.Fragments.About_Us_Fragment;
import com.rentokil.pci.rauditor_sg.Fragments.Completed_Fragment;
import com.rentokil.pci.rauditor_sg.Fragments.Dashboard;
import com.rentokil.pci.rauditor_sg.Fragments.In_Progress_Fragment;
import com.rentokil.pci.rauditor_sg.Fragments.Profile_Fragment;
import com.rentokil.pci.rauditor_sg.volley.VolleyDataRequester;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;


public class Category_Type_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ProgressDialog progressDialog_vir;
    private Toolbar toolbar;
    Boolean isInternetPresent = false;
    private android.app.AlertDialog pd;
    private ViewPager viewPager;
    public static String Entry_Status = "New";
    Typeface mTypeface;
    NavigationView navigationView;
    SQLiteDatabase sd;
    public static String User_Login_Mail = "";
    DatabaseHelper db;
    ContentValues cv, cv2, cv3, cv4, cv5, cv6, cv7, cv_send,cv_nka_send,cv_send_VIR;
    String db_user_name, db_user_mail, db_branch, db_country;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    TabLayout tabLayout;
    String post_pc_B1, post_pc_B2, post_pc_B3;
    String post_pt_B1, post_pt_B2, post_pt_B3;
    ByteArrayInputStream imageStream_pc_5_1,imageStream_pc_5_1_1,imageStream_pc_5_1_2;
    ByteArrayInputStream imageStream_pt_5_1,imageStream_pt_5_1_1,imageStream_pt_5_1_2;
    Bitmap bitmap_pt_5_1,bitmap_pt_5_1_1,bitmap_pt_5_1_2;
    Bitmap bitmap_pc_5_1,bitmap_pc_5_1_1,bitmap_pc_5_1_2;
    String pos_img_pc_5_1,pos_img_pc_5_1_1,pos_img_pc_5_1_2;
    String pos_img_pt_5_1,pos_img_pt_5_1_1,pos_img_pt_5_1_2;
    byte[] byteArray_man_pc=null,byteArray_man_pt=null;
    String post_pc_q_complete_DATE = "",post_pt_q_complete_DATE = "";
    String post_pc_q_CUS_SIGN_2,post_pc_q_reason;
    String post_pt_q_CUS_SIGN_2,post_pt_q_reason;
    ByteArrayInputStream imageStream_man_pc,imageStream_man_pt;
    Bitmap bitmap_man_tir_q;
    Bitmap bitmap_man_pt_q;
    String post_pc_q_cus_sign,post_pt_q_cus_sign;

    String IMG_URL_1,IMG_URL_2,IMG_URL_3,IMG_URL_4;
    String pc_vir_body_e1;
    int VIR_POST_KEY = 0;
    String versionname = BuildConfig.VERSION_NAME;
    Map<String, String> params_vir = new HashMap<String, String>();
    String VIR_FUNC_3_1,VIR_FUNC_3_2,VIR_FUNC_3_3,VIR_FUNC_3_4,VIR_FUNC_3_5,VIR_FUNC_3_6,VIR_FUNC_3_7,VIR_FUNC_3_8,VIR_FUNC_3_9,VIR_FUNC_3_10,
            VIR_FUNC_3_11,VIR_FUNC_3_12,VIR_FUNC_3_13,VIR_FUNC_3_14;


    String VIR_GENERAL_4_1,VIR_GENERAL_4_2,VIR_GENERAL_4_3,VIR_GENERAL_4_4,VIR_GENERAL_4_5,VIR_GENERAL_4_6,VIR_GENERAL_4_7;

    String VIR_PPE_5_1,VIR_PPE_5_2,VIR_PPE_5_3,VIR_PPE_5_4,VIR_PPE_5_5,VIR_PPE_5_6,VIR_PPE_5_7,VIR_PPE_5_8,VIR_PPE_5_9,VIR_PPE_5_10,
            VIR_PPE_5_11,VIR_PPE_5_12,VIR_PPE_5_13,VIR_PPE_5_14,VIR_PPE_5_15,VIR_PPE_5_16,VIR_PPE_5_17,VIR_PPE_5_18,VIR_PPE_5_19,VIR_PPE_5_20,
            VIR_PPE_5_21,VIR_PPE_5_22,VIR_PPE_5_23,VIR_PPE_5_24,VIR_PPE_5_25,VIR_PPE_5_26,VIR_PPE_5_27,VIR_PPE_5_28,VIR_PPE_5_29,VIR_PPE_5_30,
            VIR_PPE_5_31,VIR_PPE_5_32,VIR_PPE_5_33,VIR_PPE_5_34,VIR_PPE_5_35,VIR_PPE_5_36,VIR_PPE_5_37,VIR_PPE_5_38,VIR_PPE_5_39,VIR_PPE_5_40,VIR_PPE_5_41;



    String VIR_STAND_6_1,VIR_STAND_6_2,VIR_STAND_6_3,VIR_STAND_6_4,VIR_STAND_6_5,VIR_STAND_6_6,VIR_STAND_6_7,VIR_STAND_6_8,VIR_STAND_6_9,VIR_STAND_6_10,
            VIR_STAND_6_11,VIR_STAND_6_12,VIR_STAND_6_13,VIR_STAND_6_14,VIR_STAND_6_15,VIR_STAND_6_16,VIR_STAND_6_17,VIR_STAND_6_18,VIR_STAND_6_19,VIR_STAND_6_20,
            VIR_STAND_6_21,VIR_STAND_6_22;

    String VIR_OTHER_7_1,VIR_OTHER_7_2,VIR_OTHER_7_3,VIR_OTHER_7_4,VIR_OTHER_7_5,VIR_OTHER_7_6,VIR_OTHER_7_7,VIR_OTHER_7_8,VIR_OTHER_7_9;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setToolbar();

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();
        pd = new SpotsDialog(Category_Type_Activity.this, R.style.Custom);
        isInternetPresent = haveNetworkConnection(Category_Type_Activity.this);
        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        db = new DatabaseHelper(Category_Type_Activity.this);
        cv = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();
        cv4 = new ContentValues();
        cv5 = new ContentValues();
        cv6 = new ContentValues();
        cv7 = new ContentValues();
        cv_send = new ContentValues();
        cv_send_VIR = new ContentValues();
        cv_nka_send = new ContentValues();
        sd = db.getReadableDatabase();

        viewPager = (ViewPager) findViewById(R.id.pager);
        try {
            new Load_Layout().onPreExecute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);



        if (ContextCompat.checkSelfPermission(Category_Type_Activity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)

                != PackageManager.PERMISSION_GRANTED

                && ActivityCompat.checkSelfPermission(Category_Type_Activity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)

                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Category_Type_Activity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(Category_Type_Activity.this);
        Menu menu = navigationView.getMenu();
        Log.e("MMMMMMMMQ", "" + db.get_check_audit(sd, "MSOT"));


//        if (isInternetPresent) {
//
//
//
//                get_profile_db();
//
//                Log.e("ACXZASC","Entering");
//
//                if (db.get_pci_completed_count(sd) != 0) {
//
//                    pd.show();
//                    sync_pci();
//
//                }
//                else if (db.get_pti_completed_count(sd) != 0) {
//                    pd.show();
//                    sync_pti();
//                }
//
//                else if (db.get_vir_completed_count(sd) != 0) {
//
//                    sync_vir();
//                }
//                else  {
//                    pd.dismiss();
//                    Toast.makeText(getApplicationContext(), "No Pending Records", Toast.LENGTH_SHORT).show();
//                }
//
//
//        }


        /*if(isInternetPresent){
            if (db.get_completed_count(sd)!=0) {
                sync_meth();
            }
        }*/
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //   viewPager.getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }


    private class Load_Layout extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {

            return "call";
        }


        protected void onPostExecute() {
            // showDialog("Downloaded " + result + " bytes");
        }

        protected void onPreExecute() {
            setupViewPager(viewPager);
            // showDialog("Downloaded " + result + " bytes");
        }
    }


    private void setToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(false);

        actionBar.setTitle("");

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Dashboard(), "Dashboard");
        adapter.addFrag(new Recyclerview(), "Inspection");
        adapter.addFrag(new Completed_Fragment(), "COMPLETE");
//        adapter.addFrag(new Completed_Fragment_MSOT(), "COMPLETE MSOT");
        adapter.addFrag(new In_Progress_Fragment(), "INCOMPLETE");
//        adapter.addFrag(new In_Progress_Fragment_MSOT(), "INCOMPLETE MSOT");
        adapter.addFrag(new Profile_Fragment(), "Profile");
        adapter.addFrag(new About_Us_Fragment(), "About Us");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public String get_mail() {
        Cursor c1;
        String Mail_Name = "";
        Log.e("UUU", "entry12");
        try {
            c1 = sd.rawQuery("Select * from " + db.USER_PROFILE_TABLE, null);
            c1.moveToFirst();
            Mail_Name = c1.getString(c1.getColumnIndex(db.USER_MAIL));

            Log.e("UUU", "mail = " + c1.getString(c1.getColumnIndex(db.USER_MAIL)));
        } catch (Exception e) {

        }
        return Mail_Name;
    }


    @Override
    protected void onResume() {


        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.sync_menu) {
            isInternetPresent = haveNetworkConnection(Category_Type_Activity.this);
            if (isInternetPresent) {

                disableConnectionReuseIfNecessary();
                enableHttpResponseCache();
                get_profile_db();


                if (db.get_pci_completed_count(sd) != 0) {
                    pd.show();
                    sync_pci();

                }
                else if (db.get_pti_completed_count(sd) != 0) {
                    pd.show();
                    sync_pti();
                }

                else if (db.get_vir_completed_count(sd) != 0) {
                    sync_vir();
                }
                else  {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), "No Pending Records", Toast.LENGTH_SHORT).show();
                }


            }
            else {
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            }


    }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void sync_vir() {
        //pd.show();
        String Status = "Completed";
        String selectQuery = "SELECT * FROM " + db.PC_VIR_DB_TITLE_1 + " where STATUS ='" + Status + "'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Toast.makeText(getApplicationContext(), "Syncing Data ....", Toast.LENGTH_LONG).show();
        if (cursor.getCount() != 0) {
            //    sync_off_sir("" + cursor.getInt(cursor.getColumnIndex(db.KEY_ID)));

            progressDialog_vir = new ProgressDialog(Category_Type_Activity.this);
            progressDialog_vir.setTitle("Posting Data - VIR");
            progressDialog_vir.setMessage("Syncing in Progress...");
            progressDialog_vir.setProgressStyle(progressDialog_vir.STYLE_SPINNER);
            progressDialog_vir.setCancelable(false);
            progressDialog_vir.show();


            VIR_POST_KEY = cursor.getInt(cursor.getColumnIndex(db.KEY_ID));
            AsyncTaskRunner_vir runner = new AsyncTaskRunner_vir();
            runner.execute("pos");
        } else {
            pd.dismiss();
        }
        cursor.close();
    }

    private class AsyncTaskRunner_vir extends AsyncTask<String, String, String> {

        private String resp = "Update";

        @Override
        protected String doInBackground(String... params1) {
            String Query = "select * from " + db.PC_VIR_DB_TITLE_1 + " where KEY_ID = '" + VIR_POST_KEY + "'";
            Cursor cursor = sd.rawQuery(Query, null);
            cursor.moveToFirst();

            if (cursor.getCount() != 0) {
                params_vir.put("conducted_on",""+ cursor.getString(cursor.getColumnIndex(db.et1)));
                params_vir.put("checked_by", ""+db_user_name);
                params_vir.put("vehicle_no", ""+cursor.getString(cursor.getColumnIndex(db.et2)));
                params_vir.put("driver_name", ""+cursor.getString(cursor.getColumnIndex(db.et3)));
                params_vir.put("team", ""+cursor.getString(cursor.getColumnIndex(db.et4)));
                params_vir.put("comp_status", ""+cursor.getString(cursor.getColumnIndex(db.STATUS)));
                params_vir.put("date_complete", ""+cursor.getString(cursor.getColumnIndex(db.COMPLETED_DATE)));
                params_vir.put("version", ""+versionname);
                Log.e("AAXXCCSAA","vehicle = "+cursor.getString(cursor.getColumnIndex(db.et2)));
            }

            get_profile_db();

            cursor.close();
            //SIR TABLE 22



            Log.e("AAXXCCSAA","mail = "+db_user_mail);

            Log.e("AAXXCCSAA","post key = "+VIR_POST_KEY);

            params_vir.put("user_mail", db_user_mail);


            try {
                get_vir_function_3("" + VIR_POST_KEY);
                get_vir_general_4("" + VIR_POST_KEY);
                get_vir_ppe_5("" + VIR_POST_KEY);
                get_vir_standard_6("" + VIR_POST_KEY);
                get_vir_other_7("" + VIR_POST_KEY);


            } catch (Exception e) {

                Log.e("AAGSGSHHDD", "error = " + e.getMessage());
                progressDialog_vir.dismiss();
                e.printStackTrace();
            }




            try {
                String Query2 = "select * from " + db.PC_VIR_DB_BODY_2 + " where KEY_ID = '" + VIR_POST_KEY + "'";
                Cursor cursor2 = sd.rawQuery(Query2, null);
                cursor2.moveToFirst();


                if (cursor2.getCount() != 0) {
                    IMG_URL_1 = cursor2.getString(cursor2.getColumnIndex(db.IMG_URL_1));
                    IMG_URL_2 = cursor2.getString(cursor2.getColumnIndex(db.IMG_URL_2));
                    IMG_URL_3 = cursor2.getString(cursor2.getColumnIndex(db.IMG_URL_3));
                    IMG_URL_4 = cursor2.getString(cursor2.getColumnIndex(db.IMG_URL_4));
                    pc_vir_body_e1 = cursor2.getString(cursor2.getColumnIndex(db.et1));



                    if (pc_vir_body_e1 == null) {
                        pc_vir_body_e1 = "";
                    }

                    params_vir.put("remarks_2", ""+pc_vir_body_e1);


                    if (IMG_URL_1 != null) {
                        String strNew = IMG_URL_1.replace("[", "");
                        String strNew_1 = strNew.replace("]", "");
                        List<String> myOld = new ArrayList<String>(Arrays.asList(strNew_1.split(", ")));


                        if (myOld.size() != 0) {
                            for (int p = 0; p < myOld.size(); p++) {
                                try {
                                    if (Uri.parse(myOld.get(p)) != null) {
                                        Context c;
                                        Bitmap bitmap_x = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),
                                                Uri.parse(myOld.get(p)));
                                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                                        bitmap_x.compress(Bitmap.CompressFormat.PNG, 50, bs);
                                        //Log.e("JKJKJOPO", "33 bit\t" + myOld.get(p)));
                                        params_vir.put("front_c_"+p, "" + getStringImage(bitmap_x));

                                        Log.e("AAAAASSDFFVF",+p+" image = "+getStringImage(bitmap_x));


                                    }
                                } catch (Exception e) {
                                    Log.e("LLLLLLLLL", "else\t" + e.getMessage());
                                    //handle exception
                                }
                            }
                        }

                    }


/*
                    if (IMG_URL_2 != null) {
                        String strNew2 = IMG_URL_2.replace("[", "");
                        String strNew_2 = strNew2.replace("]", "");
                        List<String> myOld2 = new ArrayList<String>(Arrays.asList(strNew_2.split(", ")));


                        if (myOld2.size() != 0) {
                            for (int p = 0; p < myOld2.size(); p++) {
                                try {
                                    if (Uri.parse(myOld2.get(p)) != null) {
                                        Context c;
                                        Bitmap bitmap_x = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),
                                                Uri.parse(myOld2.get(p)));
                                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                                        bitmap_x.compress(Bitmap.CompressFormat.PNG, 50, bs);
                                        //Log.e("JKJKJOPO", "33 bit\t" + myOld.get(p)));
                                        params_vir.put("side_1_c_"+p, "" + getStringImage(bitmap_x));


                                    }
                                } catch (Exception e) {
                                    Log.e("LLLLLLLLL", "else\t" + e.getMessage());
                                    //handle exception
                                }
                            }
                        }

                    }
*/



                    if (IMG_URL_3 != null) {
                        String strNew3 = IMG_URL_3.replace("[", "");
                        String strNew_3 = strNew3.replace("]", "");
                        List<String> myOld3 = new ArrayList<String>(Arrays.asList(strNew_3.split(", ")));


                        if (myOld3.size() != 0) {
                            for (int p = 0; p < myOld3.size(); p++) {
                                try {
                                    if (Uri.parse(myOld3.get(p)) != null) {
                                        Context c;
                                        Bitmap bitmap_x = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),
                                                Uri.parse(myOld3.get(p)));
                                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                                        bitmap_x.compress(Bitmap.CompressFormat.PNG, 50, bs);
                                        //Log.e("JKJKJOPO", "33 bit\t" + myOld.get(p)));
                                        params_vir.put("side_2_c_"+p, "" + getStringImage(bitmap_x));


                                    }
                                } catch (Exception e) {
                                    Log.e("LLLLLLLLL", "else\t" + e.getMessage());
                                    //handle exception
                                }
                            }
                        }

                    }




                    if (IMG_URL_4 != null) {
                        String strNew4 = IMG_URL_4.replace("[", "");
                        String strNew_4 = strNew4.replace("]", "");
                        List<String> myOld4 = new ArrayList<String>(Arrays.asList(strNew_4.split(", ")));


                        if (myOld4.size() != 0) {
                            for (int p = 0; p < myOld4.size(); p++) {
                                try {
                                    if (Uri.parse(myOld4.get(p)) != null) {
                                        Context c;
                                        Bitmap bitmap_x = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),
                                                Uri.parse(myOld4.get(p)));
                                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                                        bitmap_x.compress(Bitmap.CompressFormat.PNG, 50, bs);
                                        //Log.e("JKJKJOPO", "33 bit\t" + myOld.get(p)));
                                        params_vir.put("rear_c_"+ p, "" + getStringImage(bitmap_x));


                                    }
                                } catch (Exception e) {
                                    Log.e("LLLLLLLLL", "else\t" + e.getMessage());
                                    //handle exception
                                }
                            }
                        }

                    }


                }
            } catch (Exception e) {

                Log.e("JJHHGAFFAF","func 1 = "+e.getMessage());
                e.printStackTrace();
            }


            Log.e("FFASAASSSSA","func 1 = "+VIR_FUNC_3_1);

            params_vir.put("v_fun_1", ""+ VIR_FUNC_3_1);
            params_vir.put("v_fun_2",""+  VIR_FUNC_3_2);
            params_vir.put("v_fun_3",""+  VIR_FUNC_3_3);
            params_vir.put("v_fun_4",""+  VIR_FUNC_3_4);
            params_vir.put("v_fun_5",""+  VIR_FUNC_3_5);
            params_vir.put("v_fun_6",""+  VIR_FUNC_3_6);
            params_vir.put("v_fun_7",""+  VIR_FUNC_3_7);
            params_vir.put("v_fun_8",""+  VIR_FUNC_3_8);
            params_vir.put("v_fun_10",""+  VIR_FUNC_3_9);
            params_vir.put("v_fun_11",""+  VIR_FUNC_3_10);
            params_vir.put("v_fun_12",""+  VIR_FUNC_3_11);
            params_vir.put("v_fun_13",""+  VIR_FUNC_3_12);
            params_vir.put("remarks_3",""+  VIR_FUNC_3_13);
            params_vir.put("v_fun_9",""+  VIR_FUNC_3_14);



            try {

                params_vir.put("g_check_1", ""+ VIR_GENERAL_4_1);
                params_vir.put("g_check_2",""+  VIR_GENERAL_4_2);
                params_vir.put("g_check_3",""+  VIR_GENERAL_4_3);
                params_vir.put("g_check_4",""+  VIR_GENERAL_4_4);
                params_vir.put("g_check_5",""+  VIR_GENERAL_4_5);
                params_vir.put("g_check_6",""+  VIR_GENERAL_4_6);
                params_vir.put("remarks_4",""+  VIR_GENERAL_4_7);


            } catch (Exception e) {
                Log.e("VVVVVVV", "Sign = " + e.getMessage());
                e.printStackTrace();
            }


            try {

                params_vir.put("ppe_1", ""+ VIR_PPE_5_1);
                params_vir.put("ppe_2",""+  VIR_PPE_5_2);
                params_vir.put("ppe_3",""+  VIR_PPE_5_3);
                params_vir.put("ppe_4",""+  VIR_PPE_5_4);
                params_vir.put("ppe_5",""+  VIR_PPE_5_5);
                params_vir.put("ppe_6",""+  VIR_PPE_5_6);
                params_vir.put("ppe_7",""+  VIR_PPE_5_7);
                params_vir.put("ppe_8",""+  VIR_PPE_5_8);
                params_vir.put("ppe_9",""+  VIR_PPE_5_9);
                params_vir.put("ppe_10",""+  VIR_PPE_5_10);
                params_vir.put("ppe_11",""+  VIR_PPE_5_11);
                params_vir.put("ppe_12",""+  VIR_PPE_5_12);
                params_vir.put("ppe_13",""+  VIR_PPE_5_13);
                params_vir.put("ppe_14",""+  VIR_PPE_5_14);
                params_vir.put("ppe_15",""+  VIR_PPE_5_15);
                params_vir.put("ppe_16",""+  VIR_PPE_5_16);
                params_vir.put("ppe_17",""+  VIR_PPE_5_17);
                params_vir.put("ppe_18",""+  VIR_PPE_5_18);
                params_vir.put("ppe_19",""+  VIR_PPE_5_19);
                params_vir.put("ppe_20",""+  VIR_PPE_5_20);
                params_vir.put("tool_1",""+  VIR_PPE_5_21);
                params_vir.put("tool_2",""+  VIR_PPE_5_22);
                params_vir.put("tool_3",""+  VIR_PPE_5_23);
                params_vir.put("tool_4",""+  VIR_PPE_5_24);
                params_vir.put("tool_5",""+  VIR_PPE_5_25);
                params_vir.put("tool_6",""+  VIR_PPE_5_26);
                params_vir.put("tool_7",""+  VIR_PPE_5_27);
                params_vir.put("tool_8",""+  VIR_PPE_5_28);
                params_vir.put("tool_9",""+  VIR_PPE_5_29);
                params_vir.put("tool_10",""+  VIR_PPE_5_30);
                params_vir.put("tool_11",""+  VIR_PPE_5_31);
                params_vir.put("tool_12",""+  VIR_PPE_5_32);
                params_vir.put("tool_13",""+  VIR_PPE_5_33);
                params_vir.put("tool_14",""+  VIR_PPE_5_34);
                params_vir.put("tool_15",""+  VIR_PPE_5_35);
                params_vir.put("tool_16",""+  VIR_PPE_5_36);
                params_vir.put("tool_17",""+  VIR_PPE_5_37);
                params_vir.put("tool_18",""+  VIR_PPE_5_38);
                params_vir.put("tool_19",""+  VIR_PPE_5_39);
                params_vir.put("tool_20",""+  VIR_PPE_5_40);
                params_vir.put("remarks_5",""+  VIR_PPE_5_41);


            } catch (Exception e) {
                Log.e("VVVVVVV", "Sign = " + e.getMessage());
                e.printStackTrace();
            }


            try {

                params_vir.put("s_item_1", ""+ VIR_STAND_6_1);
                params_vir.put("s_item_2",""+  VIR_STAND_6_2);
                params_vir.put("s_item_3",""+  VIR_STAND_6_3);
                params_vir.put("s_item_4",""+  VIR_STAND_6_4);
                params_vir.put("s_item_5",""+  VIR_STAND_6_5);
                params_vir.put("s_item_6",""+  VIR_STAND_6_6);
                params_vir.put("s_item_7",""+  VIR_STAND_6_7);
                params_vir.put("s_item_8",""+  VIR_STAND_6_8);
                params_vir.put("s_item_9",""+  VIR_STAND_6_9);
                params_vir.put("s_item_10",""+  VIR_STAND_6_10);
                params_vir.put("s_item_11",""+  VIR_STAND_6_11);
                params_vir.put("s_item_12",""+  VIR_STAND_6_12);
                params_vir.put("s_item_13",""+  VIR_STAND_6_13);
                params_vir.put("s_item_14",""+  VIR_STAND_6_14);
                params_vir.put("s_item_15",""+  VIR_STAND_6_15);
                params_vir.put("s_item_16",""+  VIR_STAND_6_16);
                params_vir.put("s_item_17",""+  VIR_STAND_6_17);
                params_vir.put("s_item_18",""+  VIR_STAND_6_18);
                params_vir.put("s_item_19",""+  VIR_STAND_6_19);
                params_vir.put("s_item_20",""+  VIR_STAND_6_20);
                params_vir.put("s_item_21",""+  VIR_STAND_6_21);
                params_vir.put("s_item_22",""+  VIR_STAND_6_22);



            } catch (Exception e) {
                Log.e("VVVVVVV", "Sign = " + e.getMessage());
                e.printStackTrace();
            }


            try {

                params_vir.put("other_item_1", ""+ VIR_OTHER_7_1);
                params_vir.put("other_item_2",""+  VIR_OTHER_7_2);
                params_vir.put("other_item_3",""+  VIR_OTHER_7_3);
                params_vir.put("other_item_4",""+  VIR_OTHER_7_4);
                params_vir.put("other_item_5",""+  VIR_OTHER_7_5);
                params_vir.put("other_item_6",""+  VIR_OTHER_7_6);
                params_vir.put("other_item_7",""+  VIR_OTHER_7_7);
                params_vir.put("other_item_8",""+  VIR_OTHER_7_8);
                params_vir.put("remarks_7",""+  VIR_OTHER_7_9);

                Log.e("AASSDEEFFF", "other = " +VIR_OTHER_7_1);


            } catch (Exception e) {
                Log.e("VVVVVVV", "Sign = " + e.getMessage());
                e.printStackTrace();
            }


            Log.e("AAHHASJSJSX","param = "+params_vir);



            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            sync_off_vir("" + VIR_POST_KEY);
            progressDialog_vir.dismiss();

        }


        @Override
        protected void onPreExecute() {
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //  finalResult.setText(text[0]);
            // progressDialog.setProgressPercentFormat(text[0]);

        }
    }

    private void sync_off_vir(final String key_id) {
        // get_profile_db();


        VolleyDataRequester.withDefaultHttps(this)
                .setUrl("https://rauditor-sg.riflows.com/rAuditor/Android/PC_Vehicle/insert_offline_data.php")
                .setBody(params_vir)
                .setMethod(VolleyDataRequester.Method.POST)
                .setRetryPolicy(new DefaultRetryPolicy(
                        40000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                ))
                .setStringResponseListener(new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog_vir.dismiss();
                        if (response != null && response.length() > 0) {

                        }
                        Log.e("TEST123 RES", "res  = " + response);
                        cv_send.clear();


                        cv_send_VIR.put(db.STATUS, "SENT");
                        sd.update(db.PC_VIR_DB_TITLE_1, cv_send_VIR, "KEY_ID = '" + key_id + "'", null);
                        params_vir.clear();

                    }
                })
                .setResponseErrorListener(new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TEST123 ERR", "error  = " + error.getMessage());
                        cv_send.clear();
                        cv_send_VIR.put(db.STATUS, "SENT");
                        sd.update(db.PC_VIR_DB_TITLE_1, cv_send_VIR, "KEY_ID = '" + key_id + "'", null);
                        if (db.get_vir_completed_count(sd) != 0) {
                            sync_vir();
                        }
                        progressDialog_vir.dismiss();
                        params_vir.clear();

                    }
                })
                .requestString();

    }



    private boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            Log.e("Available ", "" + ni.getTypeName());
            if (ni.getTypeName().equalsIgnoreCase("WIFI")) {
                if (ni.isConnected()) {
                    haveConnectedWifi = true;
                    Log.i("WIFI CONNECTION ", "AVAILABLE");
                } else {
                    Log.i("WIFI CONNECTION ", "NOT AVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("MOBILE NET CONNECTION", "AVAILABLE");
                } else {
                    Log.i("MOBILE NET CONNECTION", "NOTAVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("USBNET")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("USBNET NET CONNECTION", "AVAILABLE");
                } else {
                    Log.i("USBNET NET CONNECTION", "NOTAVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("mobile_internet")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("mobile_net CONNECTION", "AVAILABLE");
                } else {
                    Log.i("mobile_net CONNECTION", "NOTAVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("mobile")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("mobile NET CONNECTION", "AVAILABLE");
                } else {
                    Log.i("mobile NET CONNECTION", "NOTAVAILABLE");
                }
            }
        }
        return haveConnectedWifi || haveConnectedMobile;
    }








    public void get_profile_db() {
        Cursor c5;
        c5 = sd.rawQuery("Select * from " + db.USER_PROFILE_TABLE, null);
        c5.moveToFirst();

        if (c5 != null) {
            db_user_name = c5.getString(c5.getColumnIndex(db.USER_NAME));
            db_user_mail = c5.getString(c5.getColumnIndex(db.USER_MAIL));
            db_branch = c5.getString(c5.getColumnIndex(db.BRANCH));
            db_country = c5.getString(c5.getColumnIndex(db.COUNTRY));

        }
        c5.close();

    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }




    private void disableConnectionReuseIfNecessary() {
        // HTTP connection reuse which was buggy pre-froyo
        if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private void enableHttpResponseCache() {
        try {
            long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
            File httpCacheDir = new File(getCacheDir(), "http");
            Class.forName("android.net.http.HttpResponseCache")
                    .getMethod("install", File.class, long.class)
                    .invoke(null, httpCacheDir, httpCacheSize);
        } catch (Exception httpResponseCacheNotAvailable) {
        }
    }



    public void get_vir_function_3(String key_id) {

        Log.e("JJHHGAFFAF","key ids =  "+key_id);



        String Query = "select * from " + db.PC_VIR_DB_FUNCTION_3 + " where KEY_ID = '" + key_id + "'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        Log.e("JJHHGAFFAF","count =  "+cursor.getCount());
        if (cursor.getCount() != 0) {
            VIR_FUNC_3_1 = cursor.getString(cursor.getColumnIndex(db.et1));
            VIR_FUNC_3_2 = cursor.getString(cursor.getColumnIndex(db.et2));
            VIR_FUNC_3_3 = cursor.getString(cursor.getColumnIndex(db.et3));
            VIR_FUNC_3_4 = cursor.getString(cursor.getColumnIndex(db.et4));
            VIR_FUNC_3_5 = cursor.getString(cursor.getColumnIndex(db.et5));
            VIR_FUNC_3_6 = cursor.getString(cursor.getColumnIndex(db.et6));
            VIR_FUNC_3_7 = cursor.getString(cursor.getColumnIndex(db.et7));
            VIR_FUNC_3_8 = cursor.getString(cursor.getColumnIndex(db.et8));
            VIR_FUNC_3_9 = cursor.getString(cursor.getColumnIndex(db.et9));
            VIR_FUNC_3_10 = cursor.getString(cursor.getColumnIndex(db.et10));
            VIR_FUNC_3_11 = cursor.getString(cursor.getColumnIndex(db.et11));
            VIR_FUNC_3_12 = cursor.getString(cursor.getColumnIndex(db.et12));
            VIR_FUNC_3_13 = cursor.getString(cursor.getColumnIndex(db.et13));
            VIR_FUNC_3_14 = cursor.getString(cursor.getColumnIndex(db.et14));


            Log.e("JJHHGAFFAF","VIR_6=  "+VIR_FUNC_3_6);


            if (VIR_FUNC_3_1 == null) {
                VIR_FUNC_3_1 = "";
            }

            if (VIR_FUNC_3_2 == null) {
                VIR_FUNC_3_2 = "";
            }


            if (VIR_FUNC_3_3 == null) {
                VIR_FUNC_3_3 = "";
            }


            if (VIR_FUNC_3_4 == null) {
                VIR_FUNC_3_4 = "";
            }

            if (VIR_FUNC_3_5 == null) {
                VIR_FUNC_3_5 = "";
            }

            if (VIR_FUNC_3_6 == null) {
                VIR_FUNC_3_6 = "";
            }

            if (VIR_FUNC_3_7 == null) {
                VIR_FUNC_3_7 = "";
            }

            if (VIR_FUNC_3_8 == null) {
                VIR_FUNC_3_8 = "";
            }

            if (VIR_FUNC_3_9 == null) {
                VIR_FUNC_3_9 = "";
            }

            if (VIR_FUNC_3_10 == null) {
                VIR_FUNC_3_10 = "";
            }

            if (VIR_FUNC_3_11 == null) {
                VIR_FUNC_3_11 = "";
            }
            if (VIR_FUNC_3_12 == null) {
                VIR_FUNC_3_12 = "";
            }

            if (VIR_FUNC_3_13 == null) {
                VIR_FUNC_3_13 = "";
            }

            if (VIR_FUNC_3_14 == null) {
                VIR_FUNC_3_14 = "";
            }


        }

    }


    public void get_vir_general_4(String key_id) {
        String Query = "select * from " + db.PC_VIR_DB_GENERAL_4 + " where KEY_ID = '" + key_id + "'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();


        if (cursor.getCount() != 0) {
            VIR_GENERAL_4_1 = cursor.getString(cursor.getColumnIndex(db.et1));
            VIR_GENERAL_4_2 = cursor.getString(cursor.getColumnIndex(db.et2));
            VIR_GENERAL_4_3 = cursor.getString(cursor.getColumnIndex(db.et3));
            VIR_GENERAL_4_4 = cursor.getString(cursor.getColumnIndex(db.et4));
            VIR_GENERAL_4_5 = cursor.getString(cursor.getColumnIndex(db.et5));
            VIR_GENERAL_4_6 = cursor.getString(cursor.getColumnIndex(db.et6));
            VIR_GENERAL_4_7 = cursor.getString(cursor.getColumnIndex(db.et7));



            if (VIR_GENERAL_4_1 == null) {
                VIR_GENERAL_4_1 = "";
            }

            if (VIR_GENERAL_4_2 == null) {
                VIR_GENERAL_4_2 = "";
            }


            if (VIR_GENERAL_4_3 == null) {
                VIR_GENERAL_4_3 = "";
            }


            if (VIR_GENERAL_4_4 == null) {
                VIR_GENERAL_4_4 = "";
            }

            if (VIR_GENERAL_4_5 == null) {
                VIR_GENERAL_4_5 = "";
            }

            if (VIR_GENERAL_4_6 == null) {
                VIR_GENERAL_4_6 = "";
            }

            if (VIR_GENERAL_4_7 == null) {
                VIR_GENERAL_4_7 = "";
            }


        }

    }


    public void get_vir_ppe_5(String key_id) {
        String Query = "select * from " + db.PC_VIR_DB_PPE_5 + " where KEY_ID = '" + key_id + "'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();


        if (cursor.getCount() != 0) {
            VIR_PPE_5_1 = cursor.getString(cursor.getColumnIndex(db.et1));
            VIR_PPE_5_2 = cursor.getString(cursor.getColumnIndex(db.et2));
            VIR_PPE_5_3 = cursor.getString(cursor.getColumnIndex(db.et3));
            VIR_PPE_5_4 = cursor.getString(cursor.getColumnIndex(db.et4));
            VIR_PPE_5_5 = cursor.getString(cursor.getColumnIndex(db.et5));
            VIR_PPE_5_6 = cursor.getString(cursor.getColumnIndex(db.et6));
            VIR_PPE_5_7 = cursor.getString(cursor.getColumnIndex(db.et7));
            VIR_PPE_5_8 = cursor.getString(cursor.getColumnIndex(db.et8));
            VIR_PPE_5_9 = cursor.getString(cursor.getColumnIndex(db.et9));
            VIR_PPE_5_10 = cursor.getString(cursor.getColumnIndex(db.et10));
            VIR_PPE_5_11 = cursor.getString(cursor.getColumnIndex(db.et11));
            VIR_PPE_5_12 = cursor.getString(cursor.getColumnIndex(db.et12));
            VIR_PPE_5_13 = cursor.getString(cursor.getColumnIndex(db.et13));
            VIR_PPE_5_14 = cursor.getString(cursor.getColumnIndex(db.et14));
            VIR_PPE_5_15 = cursor.getString(cursor.getColumnIndex(db.et15));
            VIR_PPE_5_16 = cursor.getString(cursor.getColumnIndex(db.et16));
            VIR_PPE_5_17 = cursor.getString(cursor.getColumnIndex(db.et17));
            VIR_PPE_5_18 = cursor.getString(cursor.getColumnIndex(db.et18));
            VIR_PPE_5_19 = cursor.getString(cursor.getColumnIndex(db.et19));
            VIR_PPE_5_20 = cursor.getString(cursor.getColumnIndex(db.et20));
            VIR_PPE_5_21 = cursor.getString(cursor.getColumnIndex(db.et21));
            VIR_PPE_5_22 = cursor.getString(cursor.getColumnIndex(db.et22));
            VIR_PPE_5_23 = cursor.getString(cursor.getColumnIndex(db.et23));
            VIR_PPE_5_24 = cursor.getString(cursor.getColumnIndex(db.et24));
            VIR_PPE_5_25 = cursor.getString(cursor.getColumnIndex(db.et25));
            VIR_PPE_5_26 = cursor.getString(cursor.getColumnIndex(db.et26));
            VIR_PPE_5_27 = cursor.getString(cursor.getColumnIndex(db.et27));
            VIR_PPE_5_28 = cursor.getString(cursor.getColumnIndex(db.et28));
            VIR_PPE_5_29 = cursor.getString(cursor.getColumnIndex(db.et29));
            VIR_PPE_5_30 = cursor.getString(cursor.getColumnIndex(db.et30));
            VIR_PPE_5_31 = cursor.getString(cursor.getColumnIndex(db.et31));
            VIR_PPE_5_32 = cursor.getString(cursor.getColumnIndex(db.et32));
            VIR_PPE_5_33 = cursor.getString(cursor.getColumnIndex(db.et33));
            VIR_PPE_5_34 = cursor.getString(cursor.getColumnIndex(db.et34));
            VIR_PPE_5_35 = cursor.getString(cursor.getColumnIndex(db.et35));
            VIR_PPE_5_36 = cursor.getString(cursor.getColumnIndex(db.et36));
            VIR_PPE_5_37 = cursor.getString(cursor.getColumnIndex(db.et37));
            VIR_PPE_5_38 = cursor.getString(cursor.getColumnIndex(db.et38));
            VIR_PPE_5_39 = cursor.getString(cursor.getColumnIndex(db.et39));
            VIR_PPE_5_40 = cursor.getString(cursor.getColumnIndex(db.et40));
            VIR_PPE_5_41 = cursor.getString(cursor.getColumnIndex(db.et41));



            if (VIR_PPE_5_1 == null) {
                VIR_PPE_5_1 = "";
            }

            if (VIR_PPE_5_2 == null) {
                VIR_PPE_5_2 = "";
            }


            if (VIR_PPE_5_3 == null) {
                VIR_PPE_5_3 = "";
            }


            if (VIR_PPE_5_4 == null) {
                VIR_PPE_5_4 = "";
            }

            if (VIR_PPE_5_5 == null) {
                VIR_PPE_5_5 = "";
            }

            if (VIR_PPE_5_6 == null) {
                VIR_PPE_5_6 = "";
            }

            if (VIR_PPE_5_7 == null) {
                VIR_PPE_5_7 = "";
            }
            if (VIR_PPE_5_8 == null) {
                VIR_PPE_5_8 = "";
            }
            if (VIR_PPE_5_9 == null) {
                VIR_PPE_5_9 = "";
            }
            if (VIR_PPE_5_10 == null) {
                VIR_PPE_5_10 = "";
            }

            if (VIR_PPE_5_11 == null) {
                VIR_PPE_5_11 = "";
            }

            if (VIR_PPE_5_12 == null) {
                VIR_PPE_5_12 = "";
            }
            if (VIR_PPE_5_13 == null) {
                VIR_PPE_5_13 = "";
            }
            if (VIR_PPE_5_14 == null) {
                VIR_PPE_5_14 = "";
            }
            if (VIR_PPE_5_15 == null) {
                VIR_PPE_5_15 = "";
            }
            if (VIR_PPE_5_16 == null) {
                VIR_PPE_5_16 = "";
            }
            if (VIR_PPE_5_17 == null) {
                VIR_PPE_5_17 = "";
            }
            if (VIR_PPE_5_18 == null) {
                VIR_PPE_5_18 = "";
            }
            if (VIR_PPE_5_19 == null) {
                VIR_PPE_5_19 = "";
            }
            if (VIR_PPE_5_20 == null) {
                VIR_PPE_5_20 = "";
            }
            if (VIR_PPE_5_21 == null) {
                VIR_PPE_5_21 = "";
            }

            if (VIR_PPE_5_22 == null) {
                VIR_PPE_5_22 = "";
            }
            if (VIR_PPE_5_23 == null) {
                VIR_PPE_5_23 = "";
            }
            if (VIR_PPE_5_24 == null) {
                VIR_PPE_5_24 = "";
            }
            if (VIR_PPE_5_25 == null) {
                VIR_PPE_5_25 = "";
            }
            if (VIR_PPE_5_26 == null) {
                VIR_PPE_5_26 = "";
            }
            if (VIR_PPE_5_27 == null) {
                VIR_PPE_5_27 = "";
            }
            if (VIR_PPE_5_28 == null) {
                VIR_PPE_5_28 = "";
            }
            if (VIR_PPE_5_29 == null) {
                VIR_PPE_5_29 = "";
            }
            if (VIR_PPE_5_30 == null) {
                VIR_PPE_5_30 = "";
            }
            if (VIR_PPE_5_31 == null) {
                VIR_PPE_5_31 = "";
            }
            if (VIR_PPE_5_32 == null) {
                VIR_PPE_5_32 = "";
            }
            if (VIR_PPE_5_33 == null) {
                VIR_PPE_5_33 = "";
            }
            if (VIR_PPE_5_34 == null) {
                VIR_PPE_5_34 = "";
            }
            if (VIR_PPE_5_35 == null) {
                VIR_PPE_5_35 = "";
            }
            if (VIR_PPE_5_36 == null) {
                VIR_PPE_5_36 = "";
            }
            if (VIR_PPE_5_37 == null) {
                VIR_PPE_5_37 = "";
            }
            if (VIR_PPE_5_38 == null) {
                VIR_PPE_5_38 = "";
            }
            if (VIR_PPE_5_39 == null) {
                VIR_PPE_5_39 = "";
            }
            if (VIR_PPE_5_40 == null) {
                VIR_PPE_5_40 = "";
            }
            if (VIR_PPE_5_41 == null) {
                VIR_PPE_5_41 = "";
            }



        }

    }

    public void get_vir_standard_6(String key_id) {
        String Query = "select * from " + db.PC_VIR_DB_STANDARD_6 + " where KEY_ID = '" + key_id + "'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();


        if (cursor.getCount() != 0) {
            VIR_STAND_6_1 = cursor.getString(cursor.getColumnIndex(db.et1));
            VIR_STAND_6_2 = cursor.getString(cursor.getColumnIndex(db.et2));
            VIR_STAND_6_3 = cursor.getString(cursor.getColumnIndex(db.et3));
            VIR_STAND_6_4 = cursor.getString(cursor.getColumnIndex(db.et4));
            VIR_STAND_6_5 = cursor.getString(cursor.getColumnIndex(db.et5));
            VIR_STAND_6_6 = cursor.getString(cursor.getColumnIndex(db.et6));
            VIR_STAND_6_7 = cursor.getString(cursor.getColumnIndex(db.et7));
            VIR_STAND_6_8 = cursor.getString(cursor.getColumnIndex(db.et8));
            VIR_STAND_6_9 = cursor.getString(cursor.getColumnIndex(db.et9));
            VIR_STAND_6_10 = cursor.getString(cursor.getColumnIndex(db.et10));
            VIR_STAND_6_11 = cursor.getString(cursor.getColumnIndex(db.et11));
            VIR_STAND_6_12 = cursor.getString(cursor.getColumnIndex(db.et12));
            VIR_STAND_6_13 = cursor.getString(cursor.getColumnIndex(db.et13));
            VIR_STAND_6_14 = cursor.getString(cursor.getColumnIndex(db.et14));
            VIR_STAND_6_15 = cursor.getString(cursor.getColumnIndex(db.et15));
            VIR_STAND_6_16 = cursor.getString(cursor.getColumnIndex(db.et16));
            VIR_STAND_6_17 = cursor.getString(cursor.getColumnIndex(db.et17));
            VIR_STAND_6_18 = cursor.getString(cursor.getColumnIndex(db.et18));
            VIR_STAND_6_19 = cursor.getString(cursor.getColumnIndex(db.et19));
            VIR_STAND_6_20 = cursor.getString(cursor.getColumnIndex(db.et20));
            VIR_STAND_6_21 = cursor.getString(cursor.getColumnIndex(db.et21));
            VIR_STAND_6_22 = cursor.getString(cursor.getColumnIndex(db.et22));


            if (VIR_STAND_6_1 == null) {
                VIR_STAND_6_1 = "";
            }

            if (VIR_STAND_6_2 == null) {
                VIR_STAND_6_2 = "";
            }


            if (VIR_STAND_6_3 == null) {
                VIR_STAND_6_3 = "";
            }


            if (VIR_STAND_6_4 == null) {
                VIR_STAND_6_4 = "";
            }

            if (VIR_STAND_6_5 == null) {
                VIR_STAND_6_5 = "";
            }

            if (VIR_STAND_6_6 == null) {
                VIR_STAND_6_6 = "";
            }

            if (VIR_STAND_6_7 == null) {
                VIR_STAND_6_7 = "";
            }
            if (VIR_STAND_6_8 == null) {
                VIR_STAND_6_8 = "";
            }
            if (VIR_STAND_6_9 == null) {
                VIR_STAND_6_9 = "";
            }
            if (VIR_STAND_6_10 == null) {
                VIR_STAND_6_10 = "";
            }

            if (VIR_STAND_6_11 == null) {
                VIR_STAND_6_11 = "";
            }

            if (VIR_STAND_6_12 == null) {
                VIR_STAND_6_12 = "";
            }
            if (VIR_STAND_6_13 == null) {
                VIR_STAND_6_13 = "";
            }
            if (VIR_STAND_6_14 == null) {
                VIR_STAND_6_14 = "";
            }
            if (VIR_STAND_6_15 == null) {
                VIR_STAND_6_15 = "";
            }
            if (VIR_STAND_6_16 == null) {
                VIR_STAND_6_16 = "";
            }
            if (VIR_STAND_6_17 == null) {
                VIR_STAND_6_17 = "";
            }
            if (VIR_STAND_6_18 == null) {
                VIR_STAND_6_18 = "";
            }
            if (VIR_STAND_6_19 == null) {
                VIR_STAND_6_19 = "";
            }
            if (VIR_STAND_6_20 == null) {
                VIR_STAND_6_20 = "";
            }
            if (VIR_STAND_6_21 == null) {
                VIR_STAND_6_21 = "";
            }

            if (VIR_STAND_6_22 == null) {
                VIR_STAND_6_22 = "";
            }


        }

    }


    public void get_vir_other_7(String key_id) {
        String Query = "select * from " + db.PC_VIR_DB_OTHER_7 + " where KEY_ID = '" + key_id + "'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();


        if (cursor.getCount() != 0) {

            VIR_OTHER_7_1 = cursor.getString(cursor.getColumnIndex(db.et1));
            VIR_OTHER_7_2 = cursor.getString(cursor.getColumnIndex(db.et2));
            VIR_OTHER_7_3 = cursor.getString(cursor.getColumnIndex(db.et3));
            VIR_OTHER_7_4 = cursor.getString(cursor.getColumnIndex(db.et4));
            VIR_OTHER_7_5 = cursor.getString(cursor.getColumnIndex(db.et5));
            VIR_OTHER_7_6 = cursor.getString(cursor.getColumnIndex(db.et6));
            VIR_OTHER_7_7 = cursor.getString(cursor.getColumnIndex(db.et7));
            VIR_OTHER_7_8 = cursor.getString(cursor.getColumnIndex(db.et8));
            VIR_OTHER_7_9 = cursor.getString(cursor.getColumnIndex(db.BRANCH_END_DATE));

            if (VIR_OTHER_7_1 == null) {
                VIR_OTHER_7_1 = "";
            }

            if (VIR_OTHER_7_2 == null) {
                VIR_OTHER_7_2 = "";
            }


            if (VIR_OTHER_7_3 == null) {
                VIR_OTHER_7_3 = "";
            }


            if (VIR_OTHER_7_4 == null) {
                VIR_OTHER_7_4 = "";
            }

            if (VIR_OTHER_7_5 == null) {
                VIR_OTHER_7_5 = "";
            }

            if (VIR_OTHER_7_6 == null) {
                VIR_OTHER_7_6 = "";
            }

            if (VIR_OTHER_7_7 == null) {
                VIR_OTHER_7_7 = "";
            }
            if (VIR_OTHER_7_8 == null) {
                VIR_OTHER_7_8 = "";
            }
            if (VIR_OTHER_7_9 == null) {
                VIR_OTHER_7_9 = "";
            }


        }

    }












    public void get_pci_sign(String key_id) {

        String Query = "select * from " + db.PCI_SIGN_3 + " where MAIN_ID = '" + key_id + "'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();
        Log.e("nnnnnnn count sign", "" + cursor.getCount());
        if (cursor.getCount() != 0) {

            try {
                byteArray_man_pc = cursor.getBlob(cursor.getColumnIndex(db.CUSTOMER_SIGN));
            } catch (Exception e) {
                e.printStackTrace();
            }
            post_pc_q_complete_DATE = cursor.getString(cursor.getColumnIndex(db.BRANCH_END_DATE));
            post_pc_q_CUS_SIGN_2 = cursor.getString(cursor.getColumnIndex(db.CUSTOMER_NAME));
            post_pc_q_reason = cursor.getString(cursor.getColumnIndex(db.et1));

            Log.e("ACMVMVA","date  = "+post_pc_q_complete_DATE);

        }


        if (byteArray_man_pc != null) {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            imageStream_man_pc = new ByteArrayInputStream(byteArray_man_pc);
            bitmap_man_tir_q = BitmapFactory.decodeStream(imageStream_man_pc);
            bitmap_man_tir_q.compress(Bitmap.CompressFormat.PNG, 100, bs);
            post_pc_q_cus_sign = getStringImage(bitmap_man_tir_q);


        }else{


            post_pc_q_cus_sign="";
        }

        if (post_pc_q_complete_DATE == null) {
            post_pc_q_complete_DATE = "";
        }

        if (post_pc_q_cus_sign == null) {
            post_pc_q_cus_sign = "";
        }



        if (post_pc_q_CUS_SIGN_2 == null) {
            post_pc_q_CUS_SIGN_2 = "";
        }

        if (post_pc_q_reason == null) {
            post_pc_q_reason = "";
        }



    }

    public void get_pti_sign(String key_id) {

        String Query = "select * from " + db.PTI_SIGN_3 + " where MAIN_ID = '" + key_id + "'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();
        Log.e("nnnnnnn count sign", "" + cursor.getCount());
        if (cursor.getCount() != 0) {

            try {
                byteArray_man_pt = cursor.getBlob(cursor.getColumnIndex(db.CUSTOMER_SIGN));
            } catch (Exception e) {
                e.printStackTrace();
            }
            post_pt_q_complete_DATE = cursor.getString(cursor.getColumnIndex(db.BRANCH_END_DATE));
            post_pt_q_CUS_SIGN_2 = cursor.getString(cursor.getColumnIndex(db.CUSTOMER_NAME));
            post_pt_q_reason = cursor.getString(cursor.getColumnIndex(db.et1));

            Log.e("ACMVMVA","date  = "+post_pt_q_complete_DATE);

        }


        if (byteArray_man_pt != null) {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            imageStream_man_pt = new ByteArrayInputStream(byteArray_man_pt);
            bitmap_man_pt_q = BitmapFactory.decodeStream(imageStream_man_pt);
            bitmap_man_pt_q.compress(Bitmap.CompressFormat.PNG, 100, bs);
            post_pt_q_cus_sign = getStringImage(bitmap_man_pt_q);


        }else{


            post_pt_q_cus_sign="";
        }

        if (post_pt_q_complete_DATE == null) {
            post_pt_q_complete_DATE = "";
        }

        if (post_pt_q_cus_sign == null) {
            post_pt_q_cus_sign = "";
        }



        if (post_pt_q_CUS_SIGN_2 == null) {
            post_pt_q_CUS_SIGN_2 = "";
        }

        if (post_pt_q_reason == null) {
            post_pt_q_reason = "";
        }



    }






    private void sync_off_line_pci_data(final String key_id) {
          get_profile_db();
        Map<String, String> params = new HashMap<String, String>();
        String Query = "select * from " + db.PCI_TITLE_1 + " where KEY_ID = '" + key_id + "'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

          get_profile_db();
        //   Log.e("UUUUUUU 1", "NNNN Ess" + cursor.getCount());

        if (cursor.getCount() != 0) {
            params.put("title_1",""+ cursor.getString(cursor.getColumnIndex(db.et1)));
            params.put("title_2", ""+cursor.getString(cursor.getColumnIndex(db.et2)));
            params.put("title_3", ""+cursor.getString(cursor.getColumnIndex(db.et3)));
            params.put("title_4", ""+cursor.getString(cursor.getColumnIndex(db.et4)));
            params.put("title_5", ""+cursor.getString(cursor.getColumnIndex(db.et5)));
            params.put("title_6", ""+cursor.getString(cursor.getColumnIndex(db.et6)));
            params.put("title_7", ""+cursor.getString(cursor.getColumnIndex(db.et7)));
            params.put("comp_status", ""+cursor.getString(cursor.getColumnIndex(db.STATUS)));
            params.put("date_complete", ""+cursor.getString(cursor.getColumnIndex(db.COMPLETED_DATE)));
            params.put("version_name", ""+versionname);

        }

        params.put("user_mail", ""+db_user_mail);

        Log.e("KKKLHHHTTT","mail = "+db_user_mail);
        get_pci_sign(key_id);

        String selectQuery_tir_4 = "SELECT * FROM " + db.PCI_INSPEC_2 + " where MAIN_ID ='" + key_id + "'";
        Cursor cursor_tir_4 = sd.rawQuery(selectQuery_tir_4, null);
        cursor_tir_4.moveToFirst();
        params.put("pci_b_count", "" + cursor_tir_4.getCount());
        byte[] byteArray_pc_5_1=null,byteArray_pc_5_1_1=null,byteArray_pc_5_1_2=null;
        if (cursor_tir_4.getCount() != 0) {

            for (int m = 0; m < cursor_tir_4.getCount(); m++) {

                post_pc_B1 = cursor_tir_4.getString(cursor_tir_4.getColumnIndex(db.et1));
                post_pc_B2 = cursor_tir_4.getString(cursor_tir_4.getColumnIndex(db.et2));
                post_pc_B3 = cursor_tir_4.getString(cursor_tir_4.getColumnIndex(db.et3));



                try {
                    byteArray_pc_5_1 = cursor_tir_4.getBlob(cursor_tir_4.getColumnIndex(db.IMAGE_1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    byteArray_pc_5_1_1 = cursor_tir_4.getBlob(cursor_tir_4.getColumnIndex(db.IMAGE_1_1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    byteArray_pc_5_1_2 = cursor_tir_4.getBlob(cursor_tir_4.getColumnIndex(db.IMAGE_1_2));
                } catch (Exception e) {
                    e.printStackTrace();
                }



                if (byteArray_pc_5_1 != null) {
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    imageStream_pc_5_1 = new ByteArrayInputStream(byteArray_pc_5_1);
                    bitmap_pc_5_1 = BitmapFactory.decodeStream(imageStream_pc_5_1);
                    bitmap_pc_5_1.compress(Bitmap.CompressFormat.PNG, 100, bs);

                    Bitmap resized1 = Bitmap.createScaledBitmap(bitmap_pc_5_1, 270, 270, true);
                    pos_img_pc_5_1 = getStringImage(resized1);

                } else {
                    pos_img_pc_5_1 = "";
                }

                Log.e("GGGTTTTTTTTT","byte = "+bitmap_pc_5_1_1);

                if (byteArray_pc_5_1_1 != null) {
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    imageStream_pc_5_1_1 = new ByteArrayInputStream(byteArray_pc_5_1_1);
                    bitmap_pc_5_1_1 = BitmapFactory.decodeStream(imageStream_pc_5_1_1);
                    bitmap_pc_5_1_1.compress(Bitmap.CompressFormat.PNG, 100, bs);

                    Bitmap resized2 = Bitmap.createScaledBitmap(bitmap_pc_5_1_1, 270, 270, true);
                    pos_img_pc_5_1_1 = getStringImage(resized2);



                } else {
                    pos_img_pc_5_1_1 = "";
                }

                if (byteArray_pc_5_1_2 != null) {
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    imageStream_pc_5_1_2 = new ByteArrayInputStream(byteArray_pc_5_1_2);
                    bitmap_pc_5_1_2 = BitmapFactory.decodeStream(imageStream_pc_5_1_2);
                    bitmap_pc_5_1_2.compress(Bitmap.CompressFormat.PNG, 100, bs);

                    Bitmap resized3 = Bitmap.createScaledBitmap(bitmap_pc_5_1_2, 270, 270, true);

                    pos_img_pc_5_1_2 = getStringImage(resized3);

                } else {
                    pos_img_pc_5_1_2 = "";
                }


                if (post_pc_B1 == null) {
                    post_pc_B1 = "";
                }
                if (post_pc_B2 == null) {
                    post_pc_B2 = "";
                }
                if (post_pc_B3 == null) {
                    post_pc_B3 = "";
                }

                params.put("pc_ir_1"+ m,""+ post_pc_B1);
                params.put("pc_ir_2"+ m,""+  post_pc_B2);
                params.put("pc_ir_3"+ m,""+  post_pc_B3);

                params.put("pc_ir_img_4_1" + m, ""+ pos_img_pc_5_1);
                params.put("pc_ir_img_4_2" + m,""+  pos_img_pc_5_1_1);
                params.put("pc_ir_img_4_3" + m, ""+ pos_img_pc_5_1_2);


                cursor_tir_4.moveToNext();

            }
        }





        try {

            params.put("pc_cus_sign", ""+ post_pc_q_cus_sign);
            params.put("pc_comp_date",""+  post_pc_q_complete_DATE);
            params.put("pc_cus_name",""+  post_pc_q_CUS_SIGN_2);
            params.put("pc_reason",""+  post_pc_q_reason);

        } catch (Exception e) {
            Log.e("VVVVVVV", "Sign = " + e.getMessage());
            e.printStackTrace();
        }

        Log.e("VVVVGGGG3", "paramas = " +params);

        VolleyDataRequester.withDefaultHttps(this)
                .setUrl("https://rauditor-sg.riflows.com/rAuditor/Android/PCI/insert_pc_offline.php")
                .setBody(params)
                .setMethod(VolleyDataRequester.Method.POST)
                .setStringResponseListener(new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();

                        Log.e("LLLLPPPV", "response = " +response);

                        cv_send.put(db.STATUS, "SENT");
                        sd.update(db.PCI_TITLE_1, cv_send, "KEY_ID = '" + key_id + "'", null);

                        if (db.get_pci_completed_count(sd) != 0) {
                            sync_pci();
                        }
                        else if (db.get_pti_completed_count(sd) != 0) {
                            sync_pti();
                        }
                        else  {
                            pd.dismiss();
                        }


                        String CHANNEL_ID = "my_channel_01";
                        int notificationId = 1;
                        Intent notificationIntent = new Intent(Category_Type_Activity.this, Category_Type_Activity.class);
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        NotificationManager notificationManager = (NotificationManager) Category_Type_Activity.this.getSystemService(Context.NOTIFICATION_SERVICE);


                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            NotificationChannel mChannel = new NotificationChannel(
                                    CHANNEL_ID, "Ashvin", importance);
                            notificationManager.createNotificationChannel(mChannel);
                        }
                        pd.dismiss();
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Category_Type_Activity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.app_logo)
                                .setContentTitle("Report Synced Successfully")
                                .setContentText("Kindly Check Your Mail");



                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(Category_Type_Activity.this);
                        stackBuilder.addNextIntent(notificationIntent);
                        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                        mBuilder.setContentIntent(resultPendingIntent);

                        notificationManager.notify(notificationId, mBuilder.build());


                        Log.e("DDDDDDSS RIID SUC", "  = " + response);


                    }
                })
                .setResponseErrorListener(new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Log.e("KKKJJJJMMH TIR ERR", "error  = " + error.getMessage());
                        cv_send.put(db.STATUS, "SENT");
                        sd.update(db.PCI_TITLE_1, cv_send, "KEY_ID = '" + key_id + "'", null);

                        if (db.get_pci_completed_count(sd) != 0) {
                            sync_pci();
                        }
                        else if (db.get_pti_completed_count(sd) != 0) {
                            sync_pti();
                        }
                        else  {
                            pd.dismiss();
                        }

                        String CHANNEL_ID = "my_channel_01";
                        int notificationId = 1;
                        Intent notificationIntent = new Intent(Category_Type_Activity.this, Category_Type_Activity.class);
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        NotificationManager notificationManager = (NotificationManager) Category_Type_Activity.this.getSystemService(Context.NOTIFICATION_SERVICE);


                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            NotificationChannel mChannel = new NotificationChannel(
                                    CHANNEL_ID, "Ashvin", importance);
                            notificationManager.createNotificationChannel(mChannel);
                        }
                        pd.dismiss();
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Category_Type_Activity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.app_logo)
                                .setContentTitle("Report Synced Successfully")
                                .setContentText("Kindly Check Your Mail");



                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(Category_Type_Activity.this);
                        stackBuilder.addNextIntent(notificationIntent);
                        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                        mBuilder.setContentIntent(resultPendingIntent);

                        notificationManager.notify(notificationId, mBuilder.build());
                    }
                })
                .requestString();

    }




    private void sync_off_line_pti_data(final String key_id) {
        get_profile_db();
        Map<String, String> params = new HashMap<String, String>();
        String Query = "select * from " + db.PTI_TITLE_1 + " where KEY_ID = '" + key_id + "'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        get_profile_db();
        //   Log.e("UUUUUUU 1", "NNNN Ess" + cursor.getCount());

        if (cursor.getCount() != 0) {
            params.put("title_1",""+ cursor.getString(cursor.getColumnIndex(db.et1)));
            params.put("title_2", ""+cursor.getString(cursor.getColumnIndex(db.et2)));
            params.put("title_3", ""+cursor.getString(cursor.getColumnIndex(db.et3)));
            params.put("title_4", ""+cursor.getString(cursor.getColumnIndex(db.et4)));
            params.put("title_5", ""+cursor.getString(cursor.getColumnIndex(db.et5)));
            params.put("title_6", ""+cursor.getString(cursor.getColumnIndex(db.et6)));
            params.put("title_7", ""+cursor.getString(cursor.getColumnIndex(db.et7)));
            params.put("comp_status", ""+cursor.getString(cursor.getColumnIndex(db.STATUS)));
            params.put("date_complete", ""+cursor.getString(cursor.getColumnIndex(db.COMPLETED_DATE)));
            params.put("version_name", ""+versionname);

            Log.e("AFJFAJFJCAM","version = "+versionname);

        }

        params.put("user_mail", ""+db_user_mail);

        Log.e("KKKLHHHTTT","mail = "+db_user_mail);
        get_pti_sign(key_id);

        String selectQuery_tir_4 = "SELECT * FROM " + db.PTI_INSPEC_2 + " where MAIN_ID ='" + key_id + "'";
        Cursor cursor_tir_4 = sd.rawQuery(selectQuery_tir_4, null);
        cursor_tir_4.moveToFirst();
        params.put("pti_b_count", "" + cursor_tir_4.getCount());
        byte[] byteArray_pt_5_1=null,byteArray_pt_5_1_1=null,byteArray_pt_5_1_2=null;
        if (cursor_tir_4.getCount() != 0) {

            byte[] byteArray_4_1,byteArray_4_2,byteArray_4_3;
            for (int m = 0; m < cursor_tir_4.getCount(); m++) {

                post_pt_B1 = cursor_tir_4.getString(cursor_tir_4.getColumnIndex(db.et1));
                post_pt_B2 = cursor_tir_4.getString(cursor_tir_4.getColumnIndex(db.et2));
                post_pt_B3 = cursor_tir_4.getString(cursor_tir_4.getColumnIndex(db.et3));



                try {
                    byteArray_pt_5_1 = cursor_tir_4.getBlob(cursor_tir_4.getColumnIndex(db.IMAGE_1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    byteArray_pt_5_1_1 = cursor_tir_4.getBlob(cursor_tir_4.getColumnIndex(db.IMAGE_1_1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    byteArray_pt_5_1_2 = cursor_tir_4.getBlob(cursor_tir_4.getColumnIndex(db.IMAGE_1_2));
                } catch (Exception e) {
                    e.printStackTrace();
                }



                if (byteArray_pt_5_1 != null) {
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    imageStream_pt_5_1 = new ByteArrayInputStream(byteArray_pt_5_1);
                    bitmap_pt_5_1 = BitmapFactory.decodeStream(imageStream_pt_5_1);
                    bitmap_pt_5_1.compress(Bitmap.CompressFormat.PNG, 100, bs);

                    Bitmap resized1 = Bitmap.createScaledBitmap(bitmap_pt_5_1, 270, 270, true);
                    pos_img_pt_5_1 = getStringImage(resized1);

                } else {
                    pos_img_pt_5_1 = "";
                }

                Log.e("GGGTTTTTTTTT","byte = "+byteArray_pt_5_1_1);

                if (byteArray_pt_5_1_1 != null) {
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    imageStream_pt_5_1_1 = new ByteArrayInputStream(byteArray_pt_5_1_1);
                    bitmap_pt_5_1_1 = BitmapFactory.decodeStream(imageStream_pt_5_1_1);
                    bitmap_pt_5_1_1.compress(Bitmap.CompressFormat.PNG, 100, bs);

                    Bitmap resized2 = Bitmap.createScaledBitmap(bitmap_pt_5_1_1, 270, 270, true);
                    pos_img_pt_5_1_1 = getStringImage(resized2);



                } else {
                    pos_img_pt_5_1_1 = "";
                }

                if (byteArray_pt_5_1_2 != null) {
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    imageStream_pt_5_1_2 = new ByteArrayInputStream(byteArray_pt_5_1_2);
                    bitmap_pt_5_1_2 = BitmapFactory.decodeStream(imageStream_pt_5_1_2);
                    bitmap_pt_5_1_2.compress(Bitmap.CompressFormat.PNG, 100, bs);

                    Bitmap resized3 = Bitmap.createScaledBitmap(bitmap_pt_5_1_2, 270, 270, true);

                    pos_img_pt_5_1_2 = getStringImage(resized3);

                } else {
                    pos_img_pt_5_1_2 = "";
                }


                if (post_pt_B1 == null) {
                    post_pt_B1 = "";
                }
                if (post_pt_B2 == null) {
                    post_pt_B2 = "";
                }
                if (post_pt_B3 == null) {
                    post_pt_B3 = "";
                }

                params.put("pt_ir_1"+ m,""+ post_pt_B1);
                params.put("pt_ir_2"+ m,""+  post_pt_B2);
                params.put("pt_ir_3"+ m,""+  post_pt_B3);

                params.put("pt_ir_img_4_1" + m, ""+ pos_img_pt_5_1);
                params.put("pt_ir_img_4_2" + m,""+  pos_img_pt_5_1_1);
                params.put("pt_ir_img_4_3" + m, ""+ pos_img_pt_5_1_2);


                cursor_tir_4.moveToNext();

            }
        }





        try {

            params.put("pt_cus_sign", ""+ post_pt_q_cus_sign);
            params.put("pt_comp_date",""+  post_pt_q_complete_DATE);
            params.put("pt_cus_name",""+  post_pt_q_CUS_SIGN_2);
            params.put("pt_reason",""+  post_pt_q_reason);

        } catch (Exception e) {
            Log.e("VVVVVVV", "Sign = " + e.getMessage());
            e.printStackTrace();
        }

        Log.e("VVVVGGGG3", "paramas = " +params);

        VolleyDataRequester.withDefaultHttps(this)
                .setUrl("https://rauditor-sg.riflows.com/rAuditor/Android/PTI/insert_pt_offline.php")
                .setBody(params)
                .setMethod(VolleyDataRequester.Method.POST)
                .setStringResponseListener(new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();

                        Log.e("LLLLPPPV", "response = " +response);

                        cv_send.put(db.STATUS, "SENT");
                        sd.update(db.PTI_TITLE_1, cv_send, "KEY_ID = '" + key_id + "'", null);

                        if (db.get_pti_completed_count(sd) != 0) {
                            sync_pti();
                        }
                        else if (db.get_pci_completed_count(sd) != 0) {
                            sync_pci();
                        }
                        else  {
                            pd.dismiss();
                        }


                        String CHANNEL_ID = "my_channel_01";
                        int notificationId = 1;
                        Intent notificationIntent = new Intent(Category_Type_Activity.this, Category_Type_Activity.class);
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        NotificationManager notificationManager = (NotificationManager) Category_Type_Activity.this.getSystemService(Context.NOTIFICATION_SERVICE);


                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            NotificationChannel mChannel = new NotificationChannel(
                                    CHANNEL_ID, "Ashvin", importance);
                            notificationManager.createNotificationChannel(mChannel);
                        }
                        pd.dismiss();
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Category_Type_Activity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.app_logo)
                                .setContentTitle("Report Synced Successfully")
                                .setContentText("Kindly Check Your Mail");



                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(Category_Type_Activity.this);
                        stackBuilder.addNextIntent(notificationIntent);
                        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                        mBuilder.setContentIntent(resultPendingIntent);

                        notificationManager.notify(notificationId, mBuilder.build());





                        Log.e("DDDDDDSS RIID SUC", "  = " + response);


                    }
                })
                .setResponseErrorListener(new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Log.e("KKKJJJJMMH TIR ERR", "error  = " + error.getMessage());
                        cv_send.put(db.STATUS, "SENT");
                        sd.update(db.PTI_TITLE_1, cv_send, "KEY_ID = '" + key_id + "'", null);


                         if (db.get_pti_completed_count(sd) != 0) {
                            sync_pti();
                        }
                         else if (db.get_pci_completed_count(sd) != 0) {
                            sync_pci();
                        }
                        else  {
                            pd.dismiss();
                        }

                        String CHANNEL_ID = "my_channel_01";
                        int notificationId = 1;
                        Intent notificationIntent = new Intent(Category_Type_Activity.this, Category_Type_Activity.class);
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        NotificationManager notificationManager = (NotificationManager) Category_Type_Activity.this.getSystemService(Context.NOTIFICATION_SERVICE);


                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            NotificationChannel mChannel = new NotificationChannel(
                                    CHANNEL_ID, "Ashvin", importance);
                            notificationManager.createNotificationChannel(mChannel);
                        }
                        pd.dismiss();
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Category_Type_Activity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.app_logo)
                                .setContentTitle("Report Synced Successfully")
                                .setContentText("Kindly Check Your Mail");



                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(Category_Type_Activity.this);
                        stackBuilder.addNextIntent(notificationIntent);
                        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                        mBuilder.setContentIntent(resultPendingIntent);

                        notificationManager.notify(notificationId, mBuilder.build());
                    }
                })
                .requestString();

    }
    

    public void sync_pci() {
        pd.show();
        String Status = "Completed";
        String selectQuery = "SELECT * FROM " + db.PCI_TITLE_1 + " where STATUS ='" + Status + "'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Toast.makeText(getApplicationContext(), "Syncing Data ....", Toast.LENGTH_LONG).show();
        if (cursor.getCount() != 0) {
            sync_off_line_pci_data("" + cursor.getInt(cursor.getColumnIndex(db.KEY_ID)));
        } else {
            pd.dismiss();
        }
        cursor.close();
    }
    public void sync_pti() {
        pd.show();
        String Status = "Completed";
        String selectQuery = "SELECT * FROM " + db.PTI_TITLE_1 + " where STATUS ='" + Status + "'";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Toast.makeText(getApplicationContext(), "Syncing Data ....", Toast.LENGTH_LONG).show();
        if (cursor.getCount() != 0) {
            sync_off_line_pti_data("" + cursor.getInt(cursor.getColumnIndex(db.KEY_ID)));
        } else {
            pd.dismiss();
        }
        cursor.close();
    }

//    public void sync_vir() {
//        pd.show();
//        String Status = "Completed";
//        String selectQuery = "SELECT * FROM " + db.PC_VIR_DB_TITLE_1 + " where STATUS ='" + Status + "'";
//        Cursor cursor = sd.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        Toast.makeText(getApplicationContext(), "Syncing Data ....", Toast.LENGTH_LONG).show();
//        if (cursor.getCount() != 0) {
//            sync_off_line_vir_data("" + cursor.getInt(cursor.getColumnIndex(db.KEY_ID)));
//        } else {
//            pd.dismiss();
//        }
//        cursor.close();
//    }




    public Bitmap getBitmap(String path) {
        try {
            Bitmap bitmap = null;
            File f = new File(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
//            image.setImageBitmap(bitmap);
            bitmap.compress(Bitmap.CompressFormat.PNG, 75, stream);

            return bitmap;
        } catch (Exception e) {

            Log.e("KKKKGGG", "error = " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    private void sync_off_line_vir_data(final String key_id) {
        get_profile_db();
        Map<String, String> params = new HashMap<String, String>();
        String Query = "select * from " + db.PC_VIR_DB_TITLE_1 + " where KEY_ID = '" + key_id + "'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        get_profile_db();
        //   Log.e("UUUUUUU 1", "NNNN Ess" + cursor.getCount());

        if (cursor.getCount() != 0) {
            params.put("conducted_on",""+ cursor.getString(cursor.getColumnIndex(db.et1)));
            params.put("checked_by", ""+db_user_name);
            params.put("vehicle_no", ""+cursor.getString(cursor.getColumnIndex(db.et2)));
            params.put("driver_name", ""+cursor.getString(cursor.getColumnIndex(db.et3)));
            params.put("team", ""+cursor.getString(cursor.getColumnIndex(db.et4)));
            params.put("comp_status", ""+cursor.getString(cursor.getColumnIndex(db.STATUS)));
            params.put("date_complete", ""+cursor.getString(cursor.getColumnIndex(db.COMPLETED_DATE)));
            params.put("version", ""+versionname);

        }

        params.put("user_mail", ""+db_user_mail);

        Log.e("KKKLHHHTTT","mail = "+db_user_mail);
        get_vir_function_3(key_id);
        get_vir_general_4(key_id);
        get_vir_ppe_5(key_id);
        get_vir_standard_6(key_id);
        get_vir_other_7(key_id);


        try {
            String Query2 = "select * from " + db.PC_VIR_DB_BODY_2 + " where KEY_ID = '" + key_id + "'";
            Cursor cursor2 = sd.rawQuery(Query2, null);
            cursor2.moveToFirst();


            if (cursor2.getCount() != 0) {
                IMG_URL_1 = cursor2.getString(cursor2.getColumnIndex(db.IMG_URL_1));
                IMG_URL_2 = cursor2.getString(cursor2.getColumnIndex(db.IMG_URL_2));
                IMG_URL_3 = cursor2.getString(cursor2.getColumnIndex(db.IMG_URL_3));
                IMG_URL_4 = cursor2.getString(cursor2.getColumnIndex(db.IMG_URL_4));
                pc_vir_body_e1 = cursor2.getString(cursor2.getColumnIndex(db.et1));

                if (pc_vir_body_e1 == null) {
                    pc_vir_body_e1 = "";
                }

                params.put("version_name", ""+pc_vir_body_e1);


                if (IMG_URL_1 != null) {
                    String strNew = IMG_URL_1.replace("[", "");
                    String strNew_1 = strNew.replace("]", "");
                    List<String> myOld = new ArrayList<String>(Arrays.asList(strNew_1.split(", ")));


                    if (myOld.size() != 0) {
                        for (int p = 0; p < myOld.size(); p++) {
                            try {
                                if (Uri.parse(myOld.get(p)) != null) {
                                    Context c;
                                    Bitmap bitmap_x = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),
                                            Uri.parse(myOld.get(p)));
                                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                                    bitmap_x.compress(Bitmap.CompressFormat.PNG, 50, bs);
                                    //Log.e("JKJKJOPO", "33 bit\t" + myOld.get(p)));
                                    params.put("front_c_"+p, "" + getStringImage(bitmap_x));


                                }
                            } catch (Exception e) {
                                Log.e("LLLLLLLLL", "else\t" + e.getMessage());
                                //handle exception
                            }
                        }
                    }

                }


                if (IMG_URL_2 != null) {
                    String strNew2 = IMG_URL_2.replace("[", "");
                    String strNew_2 = strNew2.replace("]", "");
                    List<String> myOld2 = new ArrayList<String>(Arrays.asList(strNew_2.split(", ")));


                    if (myOld2.size() != 0) {
                        for (int p = 0; p < myOld2.size(); p++) {
                            try {
                                if (Uri.parse(myOld2.get(p)) != null) {
                                    Context c;
                                    Bitmap bitmap_x = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),
                                            Uri.parse(myOld2.get(p)));
                                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                                    bitmap_x.compress(Bitmap.CompressFormat.PNG, 50, bs);
                                    //Log.e("JKJKJOPO", "33 bit\t" + myOld.get(p)));
                                    params.put("side_1_c_"+p, "" + getStringImage(bitmap_x));


                                }
                            } catch (Exception e) {
                                Log.e("LLLLLLLLL", "else\t" + e.getMessage());
                                //handle exception
                            }
                        }
                    }

                }


                if (IMG_URL_3 != null) {
                    String strNew3 = IMG_URL_3.replace("[", "");
                    String strNew_3 = strNew3.replace("]", "");
                    List<String> myOld3 = new ArrayList<String>(Arrays.asList(strNew_3.split(", ")));


                    if (myOld3.size() != 0) {
                        for (int p = 0; p < myOld3.size(); p++) {
                            try {
                                if (Uri.parse(myOld3.get(p)) != null) {
                                    Context c;
                                    Bitmap bitmap_x = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),
                                            Uri.parse(myOld3.get(p)));
                                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                                    bitmap_x.compress(Bitmap.CompressFormat.PNG, 50, bs);
                                    //Log.e("JKJKJOPO", "33 bit\t" + myOld.get(p)));
                                    params.put("side_2_c_"+p, "" + getStringImage(bitmap_x));


                                }
                            } catch (Exception e) {
                                Log.e("LLLLLLLLL", "else\t" + e.getMessage());
                                //handle exception
                            }
                        }
                    }

                }


                if (IMG_URL_4 != null) {
                    String strNew4 = IMG_URL_4.replace("[", "");
                    String strNew_4 = strNew4.replace("]", "");
                    List<String> myOld4 = new ArrayList<String>(Arrays.asList(strNew_4.split(", ")));


                    if (myOld4.size() != 0) {
                        for (int p = 0; p < myOld4.size(); p++) {
                            try {
                                if (Uri.parse(myOld4.get(p)) != null) {
                                    Context c;
                                    Bitmap bitmap_x = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),
                                            Uri.parse(myOld4.get(p)));
                                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                                    bitmap_x.compress(Bitmap.CompressFormat.PNG, 50, bs);
                                    //Log.e("JKJKJOPO", "33 bit\t" + myOld.get(p)));
                                    params.put("rear_c_"+p, "" + getStringImage(bitmap_x));


                                }
                            } catch (Exception e) {
                                Log.e("LLLLLLLLL", "else\t" + e.getMessage());
                                //handle exception
                            }
                        }
                    }

                }


            }
        } catch (Exception e) {

            Log.e("JJHHGAFFAF","func 1 = "+e.getMessage());
            e.printStackTrace();
        }


        try {


            Log.e("JJHHGAFFAF","func 1 = "+VIR_FUNC_3_1);
            Log.e("JJHHGAFFAF","func 2 = "+VIR_FUNC_3_2);
            Log.e("JJHHGAFFAF","func 3 = "+VIR_FUNC_3_3);
            Log.e("JJHHGAFFAF","func 4 = "+VIR_FUNC_3_4);

            params.put("v_fun_1", ""+ VIR_FUNC_3_1);
            params.put("v_fun_2",""+  VIR_FUNC_3_2);
            params.put("v_fun_3",""+  VIR_FUNC_3_3);
            params.put("v_fun_4",""+  VIR_FUNC_3_4);
            params.put("v_fun_5",""+  VIR_FUNC_3_5);
            params.put("v_fun_6",""+  VIR_FUNC_3_6);
            params.put("v_fun_7",""+  VIR_FUNC_3_7);
            params.put("v_fun_8",""+  VIR_FUNC_3_8);
            params.put("v_fun_10",""+  VIR_FUNC_3_9);
            params.put("v_fun_11",""+  VIR_FUNC_3_10);
            params.put("v_fun_12",""+  VIR_FUNC_3_11);
            params.put("v_fun_13",""+  VIR_FUNC_3_12);
            params.put("remarks_3",""+  VIR_FUNC_3_13);
            params.put("v_fun_9",""+  VIR_FUNC_3_14);

        } catch (Exception e) {
            Log.e("VVVVVVV", "Sign = " + e.getMessage());
            e.printStackTrace();
        }


        try {

            params.put("g_check_1", ""+ VIR_GENERAL_4_1);
            params.put("g_check_2",""+  VIR_GENERAL_4_2);
            params.put("g_check_3",""+  VIR_GENERAL_4_3);
            params.put("g_check_4",""+  VIR_GENERAL_4_4);
            params.put("g_check_5",""+  VIR_GENERAL_4_5);
            params.put("g_check_6",""+  VIR_GENERAL_4_6);
            params.put("remarks_4",""+  VIR_GENERAL_4_7);


        } catch (Exception e) {
            Log.e("VVVVVVV", "Sign = " + e.getMessage());
            e.printStackTrace();
        }


        try {

            params.put("ppe_1", ""+ VIR_PPE_5_1);
            params.put("ppe_2",""+  VIR_PPE_5_2);
            params.put("ppe_3",""+  VIR_PPE_5_3);
            params.put("ppe_4",""+  VIR_PPE_5_4);
            params.put("ppe_5",""+  VIR_PPE_5_5);
            params.put("ppe_6",""+  VIR_PPE_5_6);
            params.put("ppe_7",""+  VIR_PPE_5_7);
            params.put("ppe_8",""+  VIR_PPE_5_8);
            params.put("ppe_9",""+  VIR_PPE_5_9);
            params.put("ppe_10",""+  VIR_PPE_5_10);
            params.put("ppe_11",""+  VIR_PPE_5_11);
            params.put("ppe_12",""+  VIR_PPE_5_12);
            params.put("ppe_13",""+  VIR_PPE_5_13);
            params.put("ppe_14",""+  VIR_PPE_5_14);
            params.put("ppe_15",""+  VIR_PPE_5_15);
            params.put("ppe_16",""+  VIR_PPE_5_16);
            params.put("ppe_17",""+  VIR_PPE_5_17);
            params.put("ppe_18",""+  VIR_PPE_5_18);
            params.put("ppe_19",""+  VIR_PPE_5_19);
            params.put("ppe_20",""+  VIR_PPE_5_20);
            params.put("tool_1",""+  VIR_PPE_5_21);
            params.put("tool_2",""+  VIR_PPE_5_22);
            params.put("tool_3",""+  VIR_PPE_5_23);
            params.put("tool_4",""+  VIR_PPE_5_24);
            params.put("tool_5",""+  VIR_PPE_5_25);
            params.put("tool_6",""+  VIR_PPE_5_26);
            params.put("tool_7",""+  VIR_PPE_5_27);
            params.put("tool_8",""+  VIR_PPE_5_28);
            params.put("tool_9",""+  VIR_PPE_5_29);
            params.put("tool_10",""+  VIR_PPE_5_30);
            params.put("tool_11",""+  VIR_PPE_5_31);
            params.put("tool_12",""+  VIR_PPE_5_32);
            params.put("tool_13",""+  VIR_PPE_5_33);
            params.put("tool_14",""+  VIR_PPE_5_34);
            params.put("tool_15",""+  VIR_PPE_5_35);
            params.put("tool_16",""+  VIR_PPE_5_36);
            params.put("tool_17",""+  VIR_PPE_5_37);
            params.put("tool_18",""+  VIR_PPE_5_38);
            params.put("tool_19",""+  VIR_PPE_5_39);
            params.put("tool_20",""+  VIR_PPE_5_40);
            params.put("remarks_5",""+  VIR_PPE_5_41);


        } catch (Exception e) {
            Log.e("VVVVVVV", "Sign = " + e.getMessage());
            e.printStackTrace();
        }


        try {

            params.put("s_item_1", ""+ VIR_STAND_6_1);
            params.put("s_item_2",""+  VIR_STAND_6_2);
            params.put("s_item_3",""+  VIR_STAND_6_3);
            params.put("s_item_4",""+  VIR_STAND_6_4);
            params.put("s_item_5",""+  VIR_STAND_6_5);
            params.put("s_item_6",""+  VIR_STAND_6_6);
            params.put("s_item_7",""+  VIR_STAND_6_7);
            params.put("s_item_8",""+  VIR_STAND_6_8);
            params.put("s_item_9",""+  VIR_STAND_6_9);
            params.put("s_item_10",""+  VIR_STAND_6_10);
            params.put("s_item_11",""+  VIR_STAND_6_11);
            params.put("s_item_12",""+  VIR_STAND_6_12);
            params.put("s_item_13",""+  VIR_STAND_6_13);
            params.put("s_item_14",""+  VIR_STAND_6_14);
            params.put("s_item_15",""+  VIR_STAND_6_15);
            params.put("s_item_16",""+  VIR_STAND_6_16);
            params.put("s_item_17",""+  VIR_STAND_6_17);
            params.put("s_item_18",""+  VIR_STAND_6_18);
            params.put("s_item_19",""+  VIR_STAND_6_19);
            params.put("s_item_20",""+  VIR_STAND_6_20);
            params.put("s_item_21",""+  VIR_STAND_6_21);
            params.put("s_item_22",""+  VIR_STAND_6_22);



        } catch (Exception e) {
            Log.e("VVVVVVV", "Sign = " + e.getMessage());
            e.printStackTrace();
        }


        try {

            params.put("other_item_1", ""+ VIR_OTHER_7_1);
            params.put("other_item_2",""+  VIR_OTHER_7_2);
            params.put("other_item_3",""+  VIR_OTHER_7_3);
            params.put("other_item_4",""+  VIR_OTHER_7_4);
            params.put("other_item_5",""+  VIR_OTHER_7_5);
            params.put("other_item_6",""+  VIR_OTHER_7_6);
            params.put("other_item_7",""+  VIR_OTHER_7_7);
            params.put("other_item_8",""+  VIR_OTHER_7_8);
            params.put("remarks_7",""+  VIR_OTHER_7_9);

            Log.e("AASSDEEFFF", "other = " +VIR_OTHER_7_1);


        } catch (Exception e) {
            Log.e("VVVVVVV", "Sign = " + e.getMessage());
            e.printStackTrace();
        }

        Log.e("VVVVGGGG3", "paramas = " +params);

        VolleyDataRequester.withDefaultHttps(this)
                .setUrl("https://rauditor-sg.riflows.com/rAuditor/Android/PC_Vehicle/insert_offline_data.php")
                .setBody(params)
                .setMethod(VolleyDataRequester.Method.POST)
                .setStringResponseListener(new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();

                        Log.e("LLLLPPPV", "response = " +response);

                        cv_send_VIR.put(db.STATUS, "SENT");
                        sd.update(db.PC_VIR_DB_TITLE_1, cv_send_VIR, "KEY_ID = '" + key_id + "'", null);

                        if (db.get_pti_completed_count(sd) != 0) {
                            sync_pti();
                        }
                        else if (db.get_pci_completed_count(sd) != 0) {
                            sync_pci();
                        }
                        else if (db.get_vir_completed_count(sd) != 0) {
                            sync_vir();
                        }
                        else  {
                            pd.dismiss();
                        }


                        String CHANNEL_ID = "my_channel_01";
                        int notificationId = 1;
                        Intent notificationIntent = new Intent(Category_Type_Activity.this, Category_Type_Activity.class);
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        NotificationManager notificationManager = (NotificationManager) Category_Type_Activity.this.getSystemService(Context.NOTIFICATION_SERVICE);


                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            NotificationChannel mChannel = new NotificationChannel(
                                    CHANNEL_ID, "Ashvin", importance);
                            notificationManager.createNotificationChannel(mChannel);
                        }
                        pd.dismiss();
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Category_Type_Activity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.app_logo)
                                .setContentTitle("Report Synced Successfully")
                                .setContentText("Kindly Check Your Mail");



                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(Category_Type_Activity.this);
                        stackBuilder.addNextIntent(notificationIntent);
                        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                        mBuilder.setContentIntent(resultPendingIntent);

                        notificationManager.notify(notificationId, mBuilder.build());





                        Log.e("DDDDDDSS RIID SUC", "  = " + response);


                    }
                })
                .setResponseErrorListener(new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Log.e("KKKJJJJMMH TIR ERR", "error  = " + error.getMessage());

                        cv_send_VIR.put(db.STATUS, "SENT");
                        sd.update(db.PC_VIR_DB_TITLE_1, cv_send_VIR, "KEY_ID = '" + key_id + "'", null);


                        if (db.get_pti_completed_count(sd) != 0) {
                            sync_pti();
                        }
                        else if (db.get_pci_completed_count(sd) != 0) {
                            sync_pci();
                        }
                        else if (db.get_vir_completed_count(sd) != 0) {
                            sync_vir();
                        }
                        else  {
                            pd.dismiss();
                        }

                        String CHANNEL_ID = "my_channel_01";
                        int notificationId = 1;
                        Intent notificationIntent = new Intent(Category_Type_Activity.this, Category_Type_Activity.class);
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        NotificationManager notificationManager = (NotificationManager) Category_Type_Activity.this.getSystemService(Context.NOTIFICATION_SERVICE);


                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            NotificationChannel mChannel = new NotificationChannel(
                                    CHANNEL_ID, "Ashvin", importance);
                            notificationManager.createNotificationChannel(mChannel);
                        }
                        pd.dismiss();
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Category_Type_Activity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.app_logo)
                                .setContentTitle("Report Synced Successfully")
                                .setContentText("Kindly Check Your Mail");



                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(Category_Type_Activity.this);
                        stackBuilder.addNextIntent(notificationIntent);
                        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                        mBuilder.setContentIntent(resultPendingIntent);

                        notificationManager.notify(notificationId, mBuilder.build());
                    }
                })
                .requestString();

    }

}