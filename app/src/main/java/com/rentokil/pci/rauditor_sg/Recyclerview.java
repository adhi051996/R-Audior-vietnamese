

package com.rentokil.pci.rauditor_sg;

import android.app.Activity;
import android.content.ContentValues;
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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chootdev.recycleclick.RecycleClick;
import com.rentokil.pci.rauditor_sg.Adapter.recyclerAdapter;
import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.PCI.PCI_Title_Page_1;
import com.rentokil.pci.rauditor_sg.PC_VIR.PC_VIR_TITLE_1;
import com.rentokil.pci.rauditor_sg.PTI.PTI_Title_Page_1;

import java.util.ArrayList;
import java.util.List;

public class Recyclerview extends Fragment {

    //a list to store all the products
    List<recyclercons> productList;

    //the recyclerview
    RecyclerView recyclerView;

    private View view;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;

    Activity context;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //   super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_expandable_list_view);
        //  return inflater.inflate(R.layout.activity_expandable_list_view, container, false);
        view = inflater.inflate(R.layout.activity_recyclerview, container, false);

        //getting the recyclerview from xml

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = new DatabaseHelper(getActivity());
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();

        cManager = (ConnectivityManager) getActivity().getSystemService(context.CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        RecycleClick.addTo(recyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                sd.delete(db.CHECK_STATUS_TABLE,null,null);


                    String Name=productList.get(position).getTitle();

                    if(Name.equalsIgnoreCase("PC-INSPECTION REPORT")){
                        Intent i = new Intent(getActivity(), PCI_Title_Page_1.class);
                        startActivity(i);
                }

                if(Name.equalsIgnoreCase("PT-INSPECTION REPORT")){
                    Intent i = new Intent(getActivity(), PTI_Title_Page_1.class);
                    startActivity(i);
                }

                if(Name.equalsIgnoreCase("PC-VEHICLE INSPECTION REPORT")){
                    Intent i = new Intent(getActivity(), PC_VIR_TITLE_1.class);
                    startActivity(i);
                }
            }
        });
        productList = new ArrayList<>();


        String get_country = Country();

        Log.e("FFFRRSSSSS","audit = "+db.get_check_audit_test(sd));


            productList.add(
                    new recyclercons(

                            "PC-INSPECTION REPORT",
                            "All Customers",

                            R.drawable.rentokilpest));

        productList.add(
                new recyclercons(

                        "PT-INSPECTION REPORT",
                        "All Customers",

                        R.drawable.rentokilpest));

        productList.add(
                new recyclercons(

                        "PC-VEHICLE INSPECTION REPORT",
                        "All Customers",

                        R.drawable.rentokilpest));





        //creating recyclerview adapter
        recyclerAdapter adapter = new recyclerAdapter(getActivity(), productList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        return view;

    }
    public String Country(){
        Cursor c1;
        String Country="";
        try {
            c1 = sd.rawQuery("Select * from " + db.USER_PROFILE_TABLE, null);
            c1.moveToFirst();
            Country = c1.getString(c1.getColumnIndex(db.COUNTRY));

            Log.e("UUU","COUNTRY = "+c1.getString(c1.getColumnIndex(db.COUNTRY)));
        } catch (Exception e) {

        }
        return Country;
    }



}