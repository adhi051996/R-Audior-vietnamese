package com.rentokil.pci.rauditor_sg.Fragments;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
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

import androidx.fragment.app.Fragment;

import com.rentokil.pci.rauditor_sg.Adapter.ListViewAdapter_Completed;
import com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes;
import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Completed_Fragment extends Fragment {


    private View view;

    JSONObject jsonObject_get;
    ContentValues cv;
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;

    String db_user_mail;
    JSONArray jsonArray_get;
    Context mContext;
    String myJSON;
    JSONArray json_arr_cus_result = null;
    ArrayList<List_Item_Methodes> arraylist;
    ListViewAdapter_Completed adapter;
    ListView List_View;
    Activity context;
    ConnectivityManager cManager;
    NetworkInfo nInfo;



    ContentValues cv2;
    ContentValues cv3;
    ContentValues cv4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //   super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_expandable_list_view);
        //  return inflater.inflate(R.layout.activity_expandable_list_view, container, false);
        view = inflater.inflate(R.layout.content_main, container, false);
        List_View = (ListView) view.findViewById( R.id.listview_main );
        mContext = context;
        db = new DatabaseHelper(getActivity());
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv = new ContentValues();

        cManager = (ConnectivityManager) getActivity().getSystemService(context.CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();
        arraylist = new ArrayList<List_Item_Methodes>();
        try {
           // get_profile_db();
            get_offline();
        } catch (Exception e) {
            Log.e("JJJJK Erro",""+e.getMessage());
            e.printStackTrace();
        }

        List_View.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final List_Item_Methodes A_m = arraylist.get(position);
                final  String key_id=""+A_m.getId();
                Log.e("LLLLLL",""+A_m.getId());
                Log.e("LLLLLL",""+A_m.getAudi_name());

                return true;
            }
        });

        return view;

    }
    public void get_offline(){
        String selectQuery = "select * from (select KEY_ID as KEY_ID,et1 as Customer_name,et2 as Conducted_date,et3 as report_no,STATUS,COALESCE(" +
                "  ('PCI')," +
                "  'PCI'" +
                " ) AS Type from PCI_TITLE_1 where STATUS = 'Completed' or STATUS='SENT'" +
                "UNION ALL\n" +

                "select KEY_ID as KEY_ID,et1 as Customer_name,et2 as Conducted_date,et3 as report_no,STATUS,COALESCE(" +
                "  ('VIR')," +
                "  'VIR'" +
                " ) AS Type from PC_VIR_DB_TITLE_1 where STATUS = 'Completed' or STATUS='SENT'" +
                "UNION ALL\n" +

                "select KEY_ID as KEY_ID,et1 as Customer_name,et2 as Conducted_date,et3 as report_no,STATUS,COALESCE(" +
                "  ('PTI')," +
                "  'PTI'" +
                " ) AS Type from PTI_TITLE_1 where STATUS = 'Completed' or STATUS='SENT'" +
                " )tbl order by KEY_ID desc";
        Cursor cursor = sd.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Log.e("HHHHHH count",""+cursor.getCount());
        List_Item_Methodes wp;
        if(cursor.getCount()!=0){
            for (int i=0;i<cursor.getCount();i++) {
                   wp = new List_Item_Methodes( Integer.parseInt(cursor.getString(cursor.getColumnIndex("KEY_ID"))),
                        cursor.getString(cursor.getColumnIndex("Type")),
                        cursor.getString(cursor.getColumnIndex("Customer_name")) ,
                        cursor.getString(cursor.getColumnIndex("Conducted_date")),
                        "",cursor.getString(cursor.getColumnIndex("STATUS")));
                arraylist.add( wp );
                cursor.moveToNext();
            }
            adapter = new ListViewAdapter_Completed( getActivity(), arraylist );
            // Binds the Adapter to the ListView
            List_View.setAdapter( adapter );
        }
    }
}
