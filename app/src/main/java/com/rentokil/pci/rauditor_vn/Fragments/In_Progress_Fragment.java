package com.rentokil.pci.rauditor_vn.Fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor_vn.Adapter.ListViewAdapter_Inprogress;
import com.rentokil.pci.rauditor_vn.Adapter.List_Item_Methodes_incomplete;
import com.rentokil.pci.rauditor_vn.Category_Type_Activity;
import com.rentokil.pci.rauditor_vn.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_vn.R;
import com.rentokil.pci.rauditor_vn.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class In_Progress_Fragment extends Fragment {
    private View view;
    String myJSON;
    JSONArray json_arr_cus_result = null;
    ArrayList<List_Item_Methodes_incomplete> arraylist;
    ListViewAdapter_Inprogress adapter;
    ListView List_View;

    Activity context;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    TextView audittest;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;
    ContentValues cv4;
    ContentValues cv5;
    ContentValues cv7;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //   super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_expandable_list_view);
        //  return inflater.inflate(R.layout.activity_expandable_list_view, container, false);
        view = inflater.inflate(R.layout.activity_incomplete__listview, container, false);
        List_View = (ListView) view.findViewById( R.id.listview_main );

        audittest =(TextView) view.findViewById(R.id.audi_name);

        cManager = (ConnectivityManager) getActivity().getSystemService(context.CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();


        db = new DatabaseHelper(getActivity());
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();
        cv4 = new ContentValues();
        cv5 = new ContentValues();
        cv7 = new ContentValues();



        arraylist = new ArrayList<List_Item_Methodes_incomplete>();
        try {
           // getData();
            get_offline();
        } catch (Exception e) {
            Log.e("JJJJK Erro",""+e.getMessage());
            e.printStackTrace();
        }
        return view;


    }

    private void getData(){

        Log.e("SSSWQW","DDD = "+ Category_Type_Activity.User_Login_Mail);

        String  url="https://rauditor-id.riflows.com/rauditor/Android/ia_inprogress.php?mail_id=" + Category_Type_Activity.User_Login_Mail + "";


        Log.e("SSSWQW","url = "+url);
        VolleyDataRequester.withDefaultHttps( getActivity() )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.e("FFFFFF inprogress",""+response);

                            List_Item_Methodes_incomplete wp;
                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );
                                String cus_name = c.getString( "cus_name" );
                                String audi_name = c.getString( "audi_name" );
                                String audi_date = c.getString( "audi_date" );
                                String doc_no = c.getString( "doc_no" );
                                String getcount = c.getString( "count_t" );
                                String audit_tech = c.getString( "audit_tech" );
                                String  audit_branch = c.getString( "audit_branch" );
                                int id_1=Integer.parseInt( id );

                                Log.e("SDSDSD","count value = "+audit_tech);
                                Log.e("SDSDSD","audi_name = "+audit_branch);


                                wp = new List_Item_Methodes_incomplete( id_1, audi_name, cus_name ,audi_date,doc_no,getcount,audit_tech,audit_branch);
                                arraylist.add( wp );
                            }

                            adapter = new ListViewAdapter_Inprogress( getActivity(), arraylist );
                            // Binds the Adapter to the ListView
                            List_View.setAdapter( adapter );

                            List_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    // Get the selected item text from ListView
                                    String selectedItem = (String) parent.getItemAtPosition(position);
                                    List_Item_Methodes_incomplete methodes=arraylist.get(position);
                                    //  Toast.makeText(getContext(),"Success"+methodes.getCus_name(),Toast.LENGTH_SHORT).show();
                                    Log.e("AAAAAA","Audi name\t"+methodes.getAudi_name());
                                    String Auditor_Tite=methodes.getAudi_name();


                                }
                            });
                }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("DDD","inprog error = "+error.getMessage());
                        //mobile.setText( error.getMessage() );
                    }
                } )
                .requestJson();
    }

public void get_offline(){
    String selectQuery = "select * from (select KEY_ID as KEY_ID,et1 as Customer_name,et2 as Conducted_date,et3 as report_no,COALESCE(" +
            "  ('PCI')," +
            "  'PCI'" +
            " ) AS Type from PCI_TITLE_1 where STATUS = 'Pending'" +"UNION ALL\n" +

            "select KEY_ID as KEY_ID,et2 as Customer_name,et1    as Conducted_date,et3 as report_no,COALESCE(" +
            "  ('VIR')," +
            "  'VIR'" +
            " ) AS Type from PC_VIR_DB_TITLE_1 where STATUS = 'Pending'" +"UNION ALL\n" +

            "select KEY_ID as KEY_ID,et2 as Customer_name,et3 as Conducted_date,KEY_ID as report_no,COALESCE(" +
            "  ('SI')," +
            "  'SI'" +
            " ) AS Type from SI_TITLE_1 where STATUS = 'Pending'" +"UNION ALL\n" +

            "select KEY_ID as KEY_ID,et2 as Customer_name,et1 as Conducted_date,et3 as report_no,COALESCE(" +
            "  ('FSV')," +
            "  'FSV'" +
            " ) AS Type from FSV_DB_TITLE_1 where STATUS = 'Pending'" +"UNION ALL\n" +

            "select KEY_ID as KEY_ID,et1 as Customer_name,et2 as Conducted_date,et3 as report_no,COALESCE(" +
            "  ('PTI')," +
            "  'PTI'" +
            " ) AS Type from PTI_TITLE_1 where STATUS = 'Pending'" +






            " )tbl order by KEY_ID desc";
    Cursor cursor = sd.rawQuery(selectQuery, null);
    cursor.moveToFirst();
    Log.e("HHHHHH count",""+cursor.getCount());
    List_Item_Methodes_incomplete wp;
    if(cursor.getCount()!=0){
        for (int i=0;i<cursor.getCount();i++) {
            Log.e("HHHHHH In",""+cursor.getCount());
            Log.e("HHHHHH In TYPE",""+cursor.getString(cursor.getColumnIndex("Type")));
            Log.e("HHHHHH In 1",""+cursor.getString(cursor.getColumnIndex("Customer_name")));
            Log.e("HHHHHH In 2",""+cursor.getString(cursor.getColumnIndex("Conducted_date")));
            Log.e("HHHHHH In 3",""+cursor.getString(cursor.getColumnIndex("report_no")));
            wp = new List_Item_Methodes_incomplete( Integer.parseInt(cursor.getString(cursor.getColumnIndex("KEY_ID"))),
                    cursor.getString(cursor.getColumnIndex("Type")),
                    cursor.getString(cursor.getColumnIndex("Customer_name")) ,
                    cursor.getString(cursor.getColumnIndex("Conducted_date")),
                    "","0","","");
            arraylist.add( wp );
            cursor.moveToNext();
        }
        adapter = new ListViewAdapter_Inprogress( getActivity(), arraylist );
        // Binds the Adapter to the ListView
        List_View.setAdapter( adapter );
    }
}
}

