package com.rentokil.pci.rauditor_vn;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor_vn.Adapter.ListViewAdapter_PDF;
import com.rentokil.pci.rauditor_vn.Adapter.List_Item_PDF_Methodes;
import com.rentokil.pci.rauditor_vn.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_vn.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class PDF_VIEWER extends AppCompatActivity   {


    private View view;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    String myJSON;
    JSONArray json_arr_cus_result = null;
    ArrayList<List_Item_PDF_Methodes> arraylist;
    ListViewAdapter_PDF adapter;
    ListView List_View;
    Activity context;
    ConnectivityManager cManager;
    NetworkInfo nInfo;

    private AlertDialog pd;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;





    Boolean isInternetPresent = false;


    public static String User_Details="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf__viewer2);

        List_View = (ListView) findViewById( R.id.listview_main );

        db = new DatabaseHelper(PDF_VIEWER.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();

        pd = new SpotsDialog(PDF_VIEWER.this, R.style.Custom);

        User_Details=get_form_details();

        Log.e("AJJHHCCBB","Details = "+User_Details);
        pd.show();
        arraylist = new ArrayList<List_Item_PDF_Methodes>();
        isInternetPresent = haveNetworkConnection(PDF_VIEWER.this);

        if (isInternetPresent) {

            try {
                pd.show();
//                getData();


            } catch (Exception e) {
                Log.e("JJJJK Erro",""+e.getMessage());
                e.printStackTrace();
            }

        }

        else {
            pd.dismiss();
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();

        }
        //if(isNetworkConnected(    )){

        //}


        get_all_pdf();



//        List<String> categories = new ArrayList<String>();
//        categories.add("Not Paid");
//        categories.add("Paid");
//
//
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        myspin10.setAdapter(dataAdapter);



    }




    private void get_all_pdf(){

        String  url="https://rauditor.riflows.com/Android/PDF/ia_completed.php?mail_id=" + User_Details + "" ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        List_Item_PDF_Methodes wp;

                        try {

                            String res1 = response.toString();

                            Log.e("EEEE","nothing"+res1);

                            if(res1.equalsIgnoreCase("{\"result\":[]}")){
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(),"No Submission Data",Toast.LENGTH_SHORT).show();
                            }
                            else {


                                jsonObject_get = (JSONObject) response;
                                jsonArray_get = jsonObject_get.getJSONArray("result");


                                for (int i = 0; i < jsonArray_get.length(); i++) {

                                    JSONObject c = jsonArray_get.getJSONObject(i);

                                    String status = c.getString("doc_no");
                                    String id = c.getString("id");
                                    String cust_name = c.getString("cus_name");
                                    String amount = c.getString("audi_name");
                                    String check_sum = c.getString("audi_date");
                                    String trans_type = c.getString("id");






                                        wp = new List_Item_PDF_Methodes(cust_name, amount, check_sum, status, trans_type);
                                        arraylist.add(wp);

                                        pd.dismiss();


                                }

                                adapter = new ListViewAdapter_PDF(PDF_VIEWER.this, arraylist);
                                // Binds the Adapter to the ListView
                                List_View.setAdapter(adapter);

                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();
                        //mobile.setText( error.getMessage() );
                    }
                } )
                .requestJson();
    }





    public String get_form_details(){
        Cursor c1;
        String Mail_Name="";

        try {
            c1 = sd.rawQuery("Select * from " + db.USER_PROFILE_TABLE, null);
            c1.moveToFirst();
            Mail_Name = c1.getString(c1.getColumnIndex(db.USER_MAIL));
        } catch (Exception e) {

        }
        return Mail_Name;
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
