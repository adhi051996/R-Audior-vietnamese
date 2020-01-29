package com.rentokil.pci.rauditor_sg;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;


public class Category_Type_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
    ContentValues cv, cv2, cv3, cv4, cv5, cv6, cv7, cv_send,cv_nka_send;
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
    String versionname = BuildConfig.VERSION_NAME;
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


        if (isInternetPresent) {

                 pd.show();

                get_profile_db();

                Log.e("ACXZASC","Entering");

                if (db.get_pci_completed_count(sd) != 0) {
                    sync_pci();

                }
                else if (db.get_pti_completed_count(sd) != 0) {
                    sync_pti();
                }
                else  {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), "No Pending Records", Toast.LENGTH_SHORT).show();
                }


        }


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
                pd.show();

                if (db.get_pci_completed_count(sd) != 0) {
                    sync_pci();

                }
                else if (db.get_pti_completed_count(sd) != 0) {
                    sync_pti();
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


}