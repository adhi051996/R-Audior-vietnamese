package com.rentokil.pci.rauditor_sg.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.LoginActivity;
import com.rentokil.pci.rauditor_sg.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class Profile_Fragment extends Fragment {

    private View view;
    ContentValues cv;
    TextView Emp_Code;
    String myJSON;
    JSONArray json_arr_cus_result;
    TextView Emp_Name,Branch,Country,Divition,Email,country_txt;
    Activity context;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    DatabaseHelper db;
    Boolean isInternetPresent = false;
    SQLiteDatabase sd;
    ContentValues cv1,cv3;
    ImageView banar1;
    String db_user_name,db_user_mail,db_branch,db_country;
    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    private GoogleApiClient mGoogleApiClient;
    Button button123;

    ImageButton edit_img;

    Button button_ok,button_cancel;

    List<String> responseList = new ArrayList<String>();
    List<String> responseList1 = new ArrayList<String>();
    Spinner spinner,spinner_branch;
    Button bt_sync;

    String get_spinner_country,get_spinner_branch;
    private android.app.AlertDialog pd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //   super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_expandable_list_view);
        //  return inflater.inflate(R.layout.activity_expandable_list_view, container, false);
        view = inflater.inflate(R.layout.activity_profile, container, false);
        Emp_Name=(TextView)view.findViewById( R.id.name_et);
        Emp_Code=(TextView) view.findViewById( R.id.emp_code_et);

        Email=(TextView)view.findViewById( R.id.email_et);
        Branch=(TextView)view.findViewById( R.id.branch_et);
        bt_sync=(Button)view.findViewById(R.id.bt_sync);
        country_txt=(TextView)view.findViewById( R.id.country_txt);
        button123=(Button) view.findViewById(R.id.button123);
        banar1=(ImageView) view.findViewById(R.id.banar1);
        edit_img=(ImageButton) view.findViewById(R.id.edit_img);

        cv=new ContentValues(  );
        pd=new SpotsDialog(getActivity(),R.style.Custom);


        isInternetPresent = haveNetworkConnection(getActivity());

//        Glide.with(getActivity()).load(LoginActivity.personPhotoUrl)
//                .thumbnail(0.5f)
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(banar1);


        db = new DatabaseHelper(getActivity());
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv3 = new ContentValues();





        button123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isInternetPresent) {
                    alert_nav_logout();
                } else {
                    Toast.makeText(getActivity(),"No internet connection",Toast.LENGTH_SHORT).show();
                }

            }
        });


        cManager = (ConnectivityManager) getActivity().getSystemService(context.CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

//        edit_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                alert_instruction();
//            }
//        });

        // Country=(MyTextView)view.findViewById( R.id.branch_et);
        try {
            get_profile_db();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


    private void alert_instruction(){


        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.activity_spinner_tech);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);


        spinner = (Spinner)dialog. findViewById(R.id.spinner);
        spinner_branch = (Spinner) dialog.findViewById(R.id.spinner_branch);

        button_ok = (Button)dialog.  findViewById(R.id.button_ok);
        button_cancel = (Button) dialog. findViewById(R.id.button_cancel);





        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                get_spinner_country =spinner.getSelectedItem().toString();

                Log.e("JJJNN","country = "+get_spinner_country);
                responseList1.clear();

                cv1.put(db.COUNTRY, get_spinner_country);


            }



            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spinner_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                get_spinner_branch =spinner_branch.getSelectedItem().toString();

                Log.e("JJJNN","branch = "+get_spinner_branch);
                responseList1.clear();

                cv1.put(db.BRANCH, get_spinner_branch);
                sd.insert(db.UPDATE_PROFILE, null, cv1);
            }



            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                Update_country_branch();


                dialog.dismiss();


            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


        dialog.show();





    }



    public void alert_nav_logout() {
        Button yes, no;
        final Dialog dl = new Dialog(getActivity());

        dl.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dl.setContentView(R.layout.logout);
        yes = (Button) dl.findViewById(R.id.yes_edit);
        no = (Button) dl.findViewById(R.id.no_edit);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                // ...

                                sd.delete(db.USER_PROFILE_TABLE,null,null);
                                db.deleteAll();



                                Toast.makeText(getActivity(),"Logged Out",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getActivity(), LoginActivity.class);
                                startActivity(i);
                                getActivity().finish();


                            }
                        });
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dl.dismiss();

            }
        });
        dl.show();

    }

    @Override
    public void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

    public void get_profile_db(){
        Cursor c5;
        c5 = sd.rawQuery("Select * from " + db.USER_PROFILE_TABLE, null);
        c5.moveToFirst();
        Log.e("NNNNNNNN",""+c5.getCount());

        if(c5!=null)
        {
            if (c5.getCount()!=0) {
                db_user_name=c5.getString(c5.getColumnIndex(db.USER_NAME));
                db_user_mail=c5.getString(c5.getColumnIndex(db.USER_MAIL));
                db_branch=c5.getString(c5.getColumnIndex(db.BRANCH));
                db_country=c5.getString(c5.getColumnIndex(db.COUNTRY));

                Emp_Name.setText(db_user_name);
                Branch.setText( db_branch );
                country_txt.setText( db_country );
                Email.setText(db_user_mail);
            }


        }
        c5.close();

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



}
