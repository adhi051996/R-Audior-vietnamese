package com.rentokil.pci.rauditor_sg;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes;
import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.PCI.PCI_Audit_Page_2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CardJson extends AppCompatActivity {
    JSONObject jsonObject;
    JSONArray jsonArray;
    String[] id;
    String[] title;
    String[] description;
    String[] image;
    SQLiteDatabase sd;
    DatabaseHelper db;
    ListView lst;
    String post_pt_B1, post_pt_B2, post_pt_B3;
    ContentValues cv;
    String key_id = "";
    ArrayList<List_Item_Methodes> arraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_json);

        Intent intent2 = getIntent();
        key_id = intent2.getStringExtra("key_id");

        Log.e("ADADXCXZ", "key = " + key_id);
        arraylist = new ArrayList<List_Item_Methodes>();

        db = new DatabaseHelper(CardJson.this);
        cv = new ContentValues();
        sd = db.getReadableDatabase();

        lst = (ListView) findViewById(R.id.lst);


        request();


    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(CardJson.this, PCI_Audit_Page_2.class);
        i.putExtra("key_id", key_id);
        startActivity(i);
    }

    public void request() {
        String selectQuery = "select * from (select KEY_ID as KEY_ID,et1 as Customer_name,et2 as Conducted_date,et3 as report_no,COALESCE(" +
                "  ('PCI')," +
                "  'PCI'" +
                " ) AS Type from PCI_INSPEC_2 where MAIN_ID = '" + key_id + "'" +
                " )tbl order by KEY_ID desc";


        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Log.e("HHHHHH count",""+cursor.getCount());
        List_Item_Methodes wp;
//        if(cursor.getCount()!=0){
//            for (int i=0;i<cursor.getCount();i++) {
//                wp = new List_Item_Methodes( Integer.parseInt(cursor.getString(cursor.getColumnIndex("KEY_ID"))),
//                        cursor.getString(cursor.getColumnIndex("Type")),
//                        cursor.getString(cursor.getColumnIndex("Customer_name")) ,
//                        cursor.getString(cursor.getColumnIndex("Conducted_date")),
//                        "","");
//                arraylist.add( wp );
//                cursor.moveToNext();
//            }
//
//            CustomAdapter customAdapter = new CustomAdapter(CardJson.this, arraylist);
//            lst.setAdapter(customAdapter);
//            customAdapter.notifyDataSetChanged();
//
//
//        }

//        String selectQuery_tir_4 = "SELECT * FROM " + db.PTI_INSPEC_2 + " where MAIN_ID ='" + key_id + "'";
//        Cursor cursor_tir_4 = sd.rawQuery(selectQuery_tir_4, null);
//        cursor_tir_4.moveToFirst();
//        byte[] byteArray_pt_5_1 = null, byteArray_pt_5_1_1 = null, byteArray_pt_5_1_2 = null;
//
//        Log.e("HHNNVINAASN", "count = " + cursor_tir_4.getCount());
//        if (cursor_tir_4.getCount() != 0) {
//
//            byte[] byteArray_4_1, byteArray_4_2, byteArray_4_3;
//            for (int m = 0; m < cursor_tir_4.getCount(); m++) {
//pt_B1 = cursor_tir_4.getString(cursor_tir_4.getColumnIndex(db.et1));
//                post_pt_B2 = cursor_tir_4.getString(cursor_tir_4.getColumnIndex(db.et2));
//                post_pt_B3 = cursor_tir_4.getString(cursor_tir_4.getColumnIndex(db.et3));
//
//                post_
//                Log.e("AACCSDS","B1 = "+post_pt_B1);
//                Log.e("AACCSDS","B2 = "+post_pt_B2);
//                Log.e("AACCSDS","B3 = "+post_pt_B3);
//
//
//                Bundle b=this.getIntent().getExtras();
//                id[m]= String.valueOf(b.getStringArray(post_pt_B1));
//                title[m]= String.valueOf(b.getStringArray(post_pt_B2));
//                description[m]= String.valueOf(b.getStringArray(post_pt_B3));
//
//
//
//
//
//
//
//                CustomAdapter customAdapter = new CustomAdapter(CardJson.this, id, title, description, image);
//                lst.setAdapter(customAdapter);
//                customAdapter.notifyDataSetChanged();
//
//
//                cursor_tir_4.moveToNext();
//
//            }
//        }

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://legalnote.org/forouruse/democard.php",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //hiding the progressbar after completion
//                        try {
//                            jsonObject=new JSONObject(response);
//                            jsonArray=jsonObject.getJSONArray("result");
//
//                            id=new String[jsonArray.length()];
//                            title=new String[jsonArray.length()];
//                            description=new String[jsonArray.length()];
//                            image=new String[jsonArray.length()];
//
//                            for(int i=0;i<jsonArray.length();i++){
//
//                             JSONObject ob = jsonArray.getJSONObject(i);
//
//
//
//                                id[i] = ob.getString("id");
//                                title[i] = ob.getString("title");
//                                description[i] = ob.getString("description");
//                                image[i] = ob.getString("image");
//
//
//
//
//                            }
//
//                            CustomAdapter customAdapter=new CustomAdapter(CardJson.this,id,title,description,image);
//                            lst.setAdapter(customAdapter);
//                            customAdapter.notifyDataSetChanged();
//
//
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //displaying the error in toast if occurrs
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        //creating a request queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        //adding the string request to request queue
//        requestQueue.add(stringRequest);
//    }

    }

}
