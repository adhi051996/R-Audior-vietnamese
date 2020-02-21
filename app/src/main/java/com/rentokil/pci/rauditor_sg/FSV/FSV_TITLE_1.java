package com.rentokil.pci.rauditor_sg.FSV;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.rentokil.pci.rauditor_sg.BuildConfig;
import com.rentokil.pci.rauditor_sg.Category_Type_Activity;
import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.GPSTracker;
import com.rentokil.pci.rauditor_sg.PC_VIR.PC_VIR_BODY_2;
import com.rentokil.pci.rauditor_sg.PC_VIR.PC_VIR_FUNCTION_3;
import com.rentokil.pci.rauditor_sg.PC_VIR.PC_VIR_GENERAL_4;
import com.rentokil.pci.rauditor_sg.PC_VIR.PC_VIR_OTHER_7;
import com.rentokil.pci.rauditor_sg.PC_VIR.PC_VIR_PPE_5;
import com.rentokil.pci.rauditor_sg.PC_VIR.PC_VIR_STANDARD_6;
import com.rentokil.pci.rauditor_sg.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FSV_TITLE_1 extends AppCompatActivity {


    private SimpleDateFormat dateFormatter;
    private long mLastClickTime = 0;
    DatePickerDialog picker;
    TimePickerDialog timePickerDialog;
    int follow = 0;
    private int year;
    private int monthOfYear;
    String value;
    private int dayOfMonth;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    Calendar cal = Calendar.getInstance();
    int startYear = cal.get(Calendar.YEAR);
    int startMonth = cal.get(Calendar.MONTH);
    int startDay = cal.get(Calendar.DAY_OF_MONTH);
    public static final int RequestPermissionCode = 1;
    EditText et_loc;
    Boolean isInternetPresent = false;
    TextView getlocations;
    String lati,longi;
    Geocoder geocoder;
    List<Address> addressList;
    public static String Main_ID="0";
    GPSTracker gps;
    SpannableStringBuilder ssb;
    Context mContext;
    private android.app.AlertDialog pd;
    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;
    double latitude,longitude;

    String key_id;

    Button sub_title_1,back_title_1;

    String get_cond_date_1,get_vehicle_2,get_driver_3,get_team_4;
String get_prepared_by;
    String db_user_name="";
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2,cv_off;
    EditText et_cond_date_1,et_vehicle_2,et_driver_3,et_team_4,et_prepared_5;
    String versionName = BuildConfig.VERSION_NAME;

    private SimpleDateFormat mSimpleDateFormat;
    private Calendar mCalendar;
    private Activity mActivity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fsv_title_1);

        Toolbar mTopToolbar;

        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTopToolbar.setTitle("");
        mTopToolbar.setSubtitle("");
        setSupportActionBar(mTopToolbar);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.menuicon);
        mTopToolbar.setOverflowIcon(drawable);
        mActivity = this;
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        et_prepared_5 =(EditText) findViewById(R.id.et_prepared_5);

        db = new DatabaseHelper(FSV_TITLE_1.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv_off = new ContentValues();

        this.setFinishOnTouchOutside(true);
        // Todo Location Already on  ... start
        final LocationManager manager = (LocationManager) FSV_TITLE_1.this.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(FSV_TITLE_1.this)) {
//            Toast.makeText(PC_VIR_TITLE_1.this,"Gps already enabled",Toast.LENGTH_SHORT).show();

        }
        // Todo Location Already on  ... end

        if(!hasGPSDevice(FSV_TITLE_1.this)){
            Toast.makeText(FSV_TITLE_1.this,"Gps not Supported",Toast.LENGTH_SHORT).show();
        }

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(FSV_TITLE_1.this)) {
            Log.e("keshav","Gps already enabled");
            Toast.makeText(FSV_TITLE_1.this,"Gps not enabled",Toast.LENGTH_SHORT).show();
            enableLoc();
        }else{
            Log.e("keshav","Gps already enabled");
//            Toast.makeText(PC_VIR_TITLE_1.this,"Gps already enabled",Toast.LENGTH_SHORT).show();
        }

        get_profile_db();



        et_cond_date_1 =(EditText) findViewById(R.id.et_cond_date_1);
        et_vehicle_2 =(EditText) findViewById(R.id.et_vehicle_2);
        et_driver_3 =(EditText) findViewById(R.id.et_driver_3);

        et_cond_date_1.setFocusable(false);
        et_cond_date_1.setClickable(true);


        et_team_4 =(EditText) findViewById(R.id.et_team_4);

        back_title_1 =(Button) findViewById(R.id.back_title_1);
        sub_title_1 =(Button) findViewById(R.id.sub_title_1);


        et_cond_date_1.setSingleLine(false);
        et_vehicle_2.setSingleLine(false);
        et_driver_3.setSingleLine(false);
        et_team_4.setSingleLine(false);

        Intent intent1=getIntent();
        key_id=intent1.getStringExtra("key_id");
        Log.e("DDDDDDX"," id 1 = "+key_id);


        et_prepared_5.setText(db_user_name);

        sub_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validation()) {

                    Log.e("FFFGGGHHHTT","cat = "+ Category_Type_Activity.Entry_Status.equalsIgnoreCase("New"));

                    if (Category_Type_Activity.Entry_Status.equalsIgnoreCase("New")) {
                        Log.e("FFFGGGHHHTT","if");
                        insert_offline(Category_Type_Activity.Entry_Status);
                    }else {

                        Log.e("FFFGGGHHHTT","else");
                        Log.e("FFFGGGHHHTT","id " +key_id);
                        insert_offline(key_id);
                    }


                }
                else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT ).show();
                }

            }
        });
        back_title_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FSV_TITLE_1.this, Category_Type_Activity.class);
                startActivity(i);

            }
        });


        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        et_cond_date_1.setText(formattedDate);
        setDateTimeField();
        mContext = this;
        EnableRuntimePermission();

        et_cond_date_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendar = Calendar.getInstance();
                new DatePickerDialog(mActivity, mDateDataSet, mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        if (key_id!=null) {

            Log.e("DDDDDDX"," id 2 = "+key_id);
            get_offline(key_id);
        }

        else
        {
            et_cond_date_1.setText(formattedDate);

        }

    }


    private final DatePickerDialog.OnDateSetListener mDateDataSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, monthOfYear);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            new TimePickerDialog(mActivity, mTimeDataSet, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), false).show();
        }
    };

    /* After user decided on a time, save them into our calendar instance, and now parse what our calendar has into the TextView */
    private final TimePickerDialog.OnTimeSetListener mTimeDataSet = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendar.set(Calendar.MINUTE, minute);
            et_cond_date_1.setText(mSimpleDateFormat.format(mCalendar.getTime()));
        }
    };

    @Override
    public void onBackPressed(){
    }
    private void insert_offline(String status){

        Log.e("MMMMMMCC","status = "+status);


        get_cond_date_1 = et_cond_date_1.getText().toString();
        get_vehicle_2 = et_vehicle_2.getText().toString();
        get_driver_3 = et_driver_3.getText().toString();
        get_team_4 = et_team_4.getText().toString();
        get_prepared_by = et_prepared_5.getText().toString();

        cv_off.put(db.MAIN_ID, "" + Main_ID);
        cv_off.put(db.et1, "" + get_cond_date_1);
        cv_off.put(db.et2, "" + get_vehicle_2);
        cv_off.put(db.et3, "" + get_driver_3);
        cv_off.put(db.et4, "" + get_team_4);
        cv_off.put(db.et5, "" + get_prepared_by);
        cv_off.put(db.VERSION_NAME, "" + versionName);


        if(check_data(key_id)==0) {
            cv_off.put(db.STATUS, "Pending");

            Log.e("HHHHHH","status"+status);
            sd.insert(db.FSV_DB_TITLE_1, null, cv_off);
            Intent intent = new Intent(getApplicationContext(), FSV_BODY_2.class);
            intent.putExtra("key_id", ""+db.get_last_id_FSV(sd));
            startActivity(intent);
            Log.e("MMMMMMH","last id = "+db.get_last_id_FSV(sd));
        }else {
            Log.e("JJJJJJN","status = "+status);
            sd.update(db.FSV_DB_TITLE_1,  cv_off,"KEY_ID = '" + key_id + "'",null);
            Intent intent = new Intent(getApplicationContext(), FSV_BODY_2.class);
            intent.putExtra("key_id", "" + key_id);
            startActivity(intent);

        }



    }

    public int check_data(String key_id){
        String Query="select * from "+db.FSV_DB_TITLE_1 +" where KEY_ID = '"+key_id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        return cursor.getCount();
    }


    public int get_last_id(){
        Cursor c8;
        c8 = sd.rawQuery("Select * from " + db.FSV_DB_TITLE_1, null);
        c8.moveToLast();

        return c8.getInt(c8.getColumnIndex(db.KEY_ID));

    }

    public void get_offline(String id){

        Log.e("GGGRRRR","E2"  + id);
        String Query="select * from "+db.FSV_DB_TITLE_1 +" where KEY_ID = '"+id+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        Log.e("KKKKKKKGG","E3 = "+cursor.getCount());

        if (cursor.getCount()!=0) {

            Log.e("YYYYRRR","E9");
            try {


                String met1 = cursor.getString(cursor.getColumnIndex(db.et1));
                String met2 = cursor.getString(cursor.getColumnIndex(db.et2));
                String met3 = cursor.getString(cursor.getColumnIndex(db.et3));
                String met4 = cursor.getString(cursor.getColumnIndex(db.et4));
                String met5 = cursor.getString(cursor.getColumnIndex(db.et5));


                et_cond_date_1.setText(met1);
                et_vehicle_2.setText(met2);
                et_driver_3.setText(met3);
                et_team_4.setText(met4);
                et_prepared_5.setText(met5);


            } catch (Exception e) {

                Log.e("FFFFVV","error ="+e.getMessage());
                e.printStackTrace();
            }
        } else {

            Log.e("OOPPP","NNNN Ess");
        }
    }



    private boolean hasGPSDevice(Context context) {
        final LocationManager mgr = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null)
            return false;
        final List<String> providers = mgr.getAllProviders();
        if (providers == null)
            return false;
        return providers.contains(LocationManager.GPS_PROVIDER);
    }

    private void enableLoc() {

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(FSV_TITLE_1.this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(Bundle bundle) {

                        }

                        @Override
                        public void onConnectionSuspended(int i) {
                            googleApiClient.connect();
                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult connectionResult) {

                            Log.d("Location error","Location error " + connectionResult.getErrorCode());
                        }
                    }).build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            builder.setAlwaysShow(true);

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(FSV_TITLE_1.this, REQUEST_LOCATION);

                                finish();
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                    }
                }
            });
        }
    }


    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(FSV_TITLE_1.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(FSV_TITLE_1.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(FSV_TITLE_1.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(FSV_TITLE_1.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(FSV_TITLE_1.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,

                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.

                if (grantResults.length > 0

                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    gps = new GPSTracker(mContext, FSV_TITLE_1.this);

                    // Check if GPS enabled

                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        // \n is for new line


                    } else {
                        gps.showSettingsAlert();
                    }

                } else {

                    // permission denied, boo! Disable the

                    // functionality that depends on this permission.

                }
                return;
            }
        }
    }




    private void setDateTimeField() {

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        monthOfYear = c.get(Calendar.MONTH);
        dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        fromDatePickerDialog = new DatePickerDialog



                (this,R.style.Theme_AppCompat_DayNight_DarkActionBar, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int

                            year, int monthOfYear, int dayOfMonth) {
                        if (year == 0 || monthOfYear == 0 || dayOfMonth == 0) {

                        }
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        if (true) {
                            et_cond_date_1.setText

                                    (dateFormatter.format(newDate.getTime()));
                        }

                    }

                }, year, monthOfYear, dayOfMonth);





        toDatePickerDialog = new DatePickerDialog(this,R.style.Theme_AppCompat_DayNight_DarkActionBar,

                new DatePickerDialog.OnDateSetListener() {


                    public void onDateSet(DatePicker view, int

                            year, int monthOfYear, int dayOfMonth) {

                        startYear = year;
                        startMonth = monthOfYear;
                        startDay = dayOfMonth;

                        cal.set(startYear, startMonth,

                                startDay);
                        if (follow == 1) {
                            et_cond_date_1.setText


                                    (dateFormatter.format(cal.getTime()));


                        }
                        if (true) {

                        }

                    }

                }, year, monthOfYear, dayOfMonth);






        toDatePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });
    }


    public boolean validation(){


        get_cond_date_1 = et_cond_date_1.getText().toString();
        get_vehicle_2 = et_vehicle_2.getText().toString();
        get_driver_3 = et_driver_3.getText().toString();
        get_team_4 = et_team_4.getText().toString();
        get_prepared_by = et_prepared_5.getText().toString();


        if(TextUtils.isEmpty(get_cond_date_1 ) || TextUtils.isEmpty(get_vehicle_2) ||TextUtils.isEmpty(get_driver_3)
                ||TextUtils.isEmpty(get_team_4) ||TextUtils.isEmpty(get_prepared_by)) {

            if(TextUtils.isEmpty(get_cond_date_1 )){
                et_cond_date_1.setError("Required");
            }

            if(TextUtils.isEmpty(get_vehicle_2)) {
                et_vehicle_2.setError("Required");


            }
            if(TextUtils.isEmpty(get_driver_3)) {
                et_driver_3.setError("Required");

            }
            if(TextUtils.isEmpty(get_team_4)) {
                et_team_4.setError("Required");

            }

            if(TextUtils.isEmpty(get_prepared_by)) {
                et_prepared_5.setError("Required");

            }


            return false;
        }



        if(get_cond_date_1.length()==0||get_vehicle_2.length()==0||get_driver_3.length()==0||get_prepared_by.length()==0||
                get_team_4.length()==0){
            return false;
        }else {
            return  true;
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
                Intent sir_home = new Intent(FSV_TITLE_1.this, Category_Type_Activity.class);
                sir_home.putExtra("key_id", key_id);
                startActivity(sir_home);
                break;
            case R.id.vir_title_page_1:
                Toast.makeText(getApplicationContext(),"Already,You Are in Same Page",Toast.LENGTH_SHORT).show();
                break;
            case R.id.vir_body_2:
                if (key_id != null) {
                    Intent sir_customer = new Intent(FSV_TITLE_1.this, PC_VIR_BODY_2.class);
                    sir_customer.putExtra("key_id", key_id);
                    startActivity(sir_customer);
                } else {
                }
                break;
            case R.id.vir_funct_3:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_TITLE_1.this, PC_VIR_FUNCTION_3.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);

                } else {
                }
                break;

                case R.id.vir_gen_4:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_TITLE_1.this, PC_VIR_GENERAL_4.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {

                }
                break;

                case R.id.vir_ppe_5:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_TITLE_1.this, PC_VIR_PPE_5.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

                case R.id.vir_stan_6:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_TITLE_1.this, PC_VIR_STANDARD_6.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

                case R.id.vir_other_7:
                if (key_id != null) {
                    Intent sir_observation = new Intent(FSV_TITLE_1.this, PC_VIR_OTHER_7.class);
                    sir_observation.putExtra("key_id", key_id);
                    startActivity(sir_observation);
                } else {
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void get_profile_db() {
        Cursor c5;
        c5 = sd.rawQuery("Select * from " + db.USER_PROFILE_TABLE, null);
        c5.moveToFirst();

        if (c5 != null) {

            db_user_name = c5.getString(c5.getColumnIndex(db.USER_NAME));


        }
        c5.close();

    }


}
