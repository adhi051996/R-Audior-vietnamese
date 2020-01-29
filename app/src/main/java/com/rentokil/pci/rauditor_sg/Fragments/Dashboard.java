package com.rentokil.pci.rauditor_sg.Fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class Dashboard extends Fragment {
    android.widget.ExpandableListView expandableListView;
    Spinner search_audit;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    JSONObject jsonObject_get_a;
    JSONArray jsonArray_get_a;
    private android.app.AlertDialog pd;
    private View view;
    TextView Header;
    String db_user_name,db_user_mail,db_branch,db_country;
    TextView Y_INC_T,Y_COM_T,M_INC_T,M_COM_T,W_INC_T,W_COM_T;

    String [] values =
            {"RIID-GPP","RIID-MOM","RIID-INSPECTION REPORT"};
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //   super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_expandable_list_view);
        //  return inflater.inflate(R.layout.activity_expandable_list_view, container, false);
        view = inflater.inflate(R.layout.activity_dashboard, container, false);

        search_audit = (Spinner) view.findViewById(R.id.search_audit);
        Y_COM_T = (TextView) view.findViewById(R.id.y_com_text);
        Y_INC_T = (TextView) view.findViewById(R.id.y_inc_text);
        M_INC_T = (TextView) view.findViewById(R.id.m_inc_text);
        M_COM_T = (TextView) view.findViewById(R.id.m_com_text);
        W_INC_T = (TextView) view.findViewById(R.id.w_inc_text);
        W_COM_T = (TextView) view.findViewById(R.id.w_com_text);
        Header=(TextView) view.findViewById(R.id.header);
        db = new DatabaseHelper(getActivity());
        sd = db.getReadableDatabase();
        cv=new ContentValues();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        search_audit.setAdapter(adapter);
        pd=new SpotsDialog(getActivity(),R.style.Custom);
//        if (db.getLogincount(sd)!=0) {
//            Log.e("KKKKKKGGGGG Cou ",""+db.get_tech_list_count(sd));
//            if(db.get_tech_list_count(sd)==0){
//                pd.show();
//                sd.delete(db.MSOT_TECH_TABLE,null,null);
//                Toast.makeText(getActivity(),"Tech list is updating",Toast.LENGTH_LONG).show();
//                get_tech_list();
//
//            }
//        }

        search_audit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  Header.setText(search_audit.getSelectedItem().toString());
                  String Audit=search_audit.getSelectedItem().toString();


                  Log.e("KKJJJHHH","audit = "+Audit);
                  get_audit_count_offline(Audit);

              }

              @Override
              public void onNothingSelected(AdapterView<?> parent) {

              }
          });





        return view;
    }
    public void get_audit_count_offline(String audit_name){
        String selectQuery = "SELECT  * FROM " + db.DASHBOARD_TB +" where AUDIT_NAME = '" +audit_name+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);

        if(cursor.getCount()!=0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String audit_type=cursor.getString(cursor.getColumnIndex(db.AUDIT_NAME));
                String type=cursor.getString(cursor.getColumnIndex(db.STATE));


                Log.e("KKJJJHHH","audit type = "+audit_type);
                if(audit_type.equalsIgnoreCase("RIID-GPP")){
                    if(type.equalsIgnoreCase("Month")){
                        M_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        M_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));

                    }else if(type.equalsIgnoreCase("Week")) {
                        W_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        W_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }else if(type.equalsIgnoreCase("Year")) {
                        Y_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        Y_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }

                }

                if(audit_type.equalsIgnoreCase("RIID-MOM")){
                    if(type.equalsIgnoreCase("Month")){
                        M_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        M_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));

                    }else if(type.equalsIgnoreCase("Week")) {
                        W_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        W_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }else if(type.equalsIgnoreCase("Year")) {
                        Y_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        Y_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }

                }

                if(audit_type.equalsIgnoreCase("RIID-INSPECTION REPORT")){
                    if(type.equalsIgnoreCase("Month")){
                        M_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        M_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));

                    }else if(type.equalsIgnoreCase("Week")) {
                        W_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        W_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }else if(type.equalsIgnoreCase("Year")) {
                        Y_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        Y_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }

                }
              cursor.moveToNext();
            }

        }

    }
//    private void get_tech_list(){
//
//        try {
//            get_proflie_db();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Log.e("KKKMMMMS","branch = "+db_branch);
//        Log.e("KKKMMMMS","country = "+db_country);
//
//        String  url="https://rauditor-id.riflows.com/rauditor/Android/MSOT/tech_list.php?get_branch="+db_branch+"&get_country="+db_country;
//
//        VolleyDataRequester.withDefaultHttps( getActivity() )
//                .setUrl(url.replace(" ","%20"))
//                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        try {
//                            jsonObject_get_a=(JSONObject) response;
//                            jsonArray_get_a=jsonObject_get_a.getJSONArray("result");
//                            Log.e("FFFFFHHHHH err HH1",""+jsonArray_get_a.length());
//                            for (int i = 0; i < jsonArray_get_a.length(); i++) {
//                                JSONObject c = jsonArray_get_a.getJSONObject(i);
//                                String name = c.getString( "employee_name" );
//                                String code = c.getString( "employee_code" );
//                                String brach = c.getString( "branch" );
//                                String occ = c.getString( "occupation_desc" );
//                                cv.put(db.TECH_NAME,name);
//                                cv.put(db.TECH_CODE,code);
//                                cv.put(db.BRANCH,brach);
//                                cv.put(db.OCC_TECH,occ);
//                                sd.insert(db.MSOT_TECH_TABLE,null,cv);
//
//
//                            }
//                            pd.dismiss();
//                        }catch (Exception e){
//                            pd.dismiss();
//                            Log.e("FFFFFHHHHH err HH",e.getMessage());
//                            e.printStackTrace();
//
//                        }
//      }
//                } )
//                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("FFFFFHHHHHH","error = "+error.getMessage());
//                        pd.dismiss();
//                    }
//                } )
//                .requestJson();
//    }
    public void get_proflie_db(){
        Cursor c5;
        c5 = sd.rawQuery("Select * from " + db.USER_PROFILE_TABLE, null);
        c5.moveToFirst();

        if(c5!=null)
        {
            if (c5.getCount()!=0) {
                db_user_name=c5.getString(c5.getColumnIndex(db.USER_NAME));
                db_user_mail=c5.getString(c5.getColumnIndex(db.USER_MAIL));
                db_branch=c5.getString(c5.getColumnIndex(db.BRANCH));
                db_country=c5.getString(c5.getColumnIndex(db.COUNTRY));
            }
        }
        c5.close();

    }

}
