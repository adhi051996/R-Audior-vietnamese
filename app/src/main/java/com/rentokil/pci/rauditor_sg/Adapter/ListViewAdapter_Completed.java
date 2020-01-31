package com.rentokil.pci.rauditor_sg.Adapter;

/**
 * Created by Murugan Kuppusamy on 23-Oct-17.
 */

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.rentokil.pci.rauditor_sg.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_sg.PCI.PCI_Title_Page_1;
import com.rentokil.pci.rauditor_sg.PC_VIR.PC_VIR_TITLE_1;
import com.rentokil.pci.rauditor_sg.PTI.PTI_Title_Page_1;
import com.rentokil.pci.rauditor_sg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListViewAdapter_Completed extends BaseAdapter {

    // Declare Variables


    Context mContext;
    static int half_visible;
    ArrayList<Integer> poss;
    LayoutInflater inflater;
    ContentValues cv;
    DatabaseHelper db;
    SQLiteDatabase sd;
    private List<com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes> List_Item_Methodes = null;
    private ArrayList<com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes> arraylist;

    public ListViewAdapter_Completed(Context context, ArrayList<com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes> List_Item_Methodes) {
        mContext = context;
        this.List_Item_Methodes = List_Item_Methodes;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes>();
        this.arraylist.addAll(List_Item_Methodes);
        db = new DatabaseHelper(mContext);
        sd = db.getReadableDatabase();
        // cv1 = new ContentValues();
        cv = new ContentValues();


        //poss = new ArrayList<Integer>();
    }


/* //   public int getCount() {
        return customer_name.length;
    }*/

    @Override
    public int getCount() {
        return List_Item_Methodes.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        TextView Tx_Audi_Name;
        TextView Tx_Cus_Name;
        TextView Tx_Audi_Date;
        TextView Tx_Doc_No;
        TextView Tx_status;
        TextView View_Bt;

    }

    @SuppressLint("ResourceAsColor")
    public View getView(final int position, View view, final ViewGroup parent) {
        // Declare Variables
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item_completed, null);

            holder.Tx_Audi_Name = (TextView) view.findViewById(R.id.audi_name);
            holder.Tx_Cus_Name = (TextView) view.findViewById(R.id.cus_name);
            holder.Tx_Audi_Date = (TextView) view.findViewById(R.id.audi_date);
            holder.Tx_Doc_No = (TextView) view.findViewById(R.id.doc_no);
            holder.Tx_status = (TextView) view.findViewById(R.id.complet);
            holder.View_Bt = (Button) view.findViewById(R.id.view_bt);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Locate the TextViews in listview_item.xml
        // Capture position and set to the TextViews
        holder.Tx_Cus_Name.setText(List_Item_Methodes.get(position).getCus_name());
        holder.Tx_Audi_Name.setText(List_Item_Methodes.get(position).getAudi_name());
        holder.Tx_Audi_Date.setText(List_Item_Methodes.get(position).getAudi_date());
        holder.Tx_Doc_No.setText(List_Item_Methodes.get(position).getDoc_no());



        if (List_Item_Methodes.get(position).getStatus().equalsIgnoreCase("Completed")) {
            holder.View_Bt.setText("NOT SEND");
        } else {
            holder.View_Bt.setText(List_Item_Methodes.get(position).getStatus());
        }
        Log.e("JJJJSS", "E1");
        holder.View_Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("JJJJSS", "E1\t"+List_Item_Methodes.get(position).getStatus());
                if (List_Item_Methodes.get(position).getStatus().equalsIgnoreCase("SENT")||
                        List_Item_Methodes.get(position).getStatus().equalsIgnoreCase("Completed")) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Action -(View/Resubmit)");
                    builder.setCancelable(true);
                    if(List_Item_Methodes.get(position).getStatus().equalsIgnoreCase("SENT")) {
                        builder.setPositiveButton("Resubmit",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {



                                        if (List_Item_Methodes.get(position).getAudi_name().equalsIgnoreCase("PCI")) {
                                            holder.View_Bt.setText("NOT SEND");
                                            cv.put(db.STATUS, "Completed");
                                            sd.update(db.PCI_TITLE_1, cv, "KEY_ID = '" + List_Item_Methodes.get(position).getId() + "'", null);
                                        }
                                        if (List_Item_Methodes.get(position).getAudi_name().equalsIgnoreCase("PTI")) {
                                            holder.View_Bt.setText("NOT SEND");
                                            cv.put(db.STATUS, "Completed");
                                            sd.update(db.PTI_TITLE_1, cv, "KEY_ID = '" + List_Item_Methodes.get(position).getId() + "'", null);
                                        }

                                        if (List_Item_Methodes.get(position).getAudi_name().equalsIgnoreCase("VIR")) {
                                            holder.View_Bt.setText("NOT SEND");
                                            cv.put(db.STATUS, "Completed");
                                            sd.update(db.PC_VIR_DB_TITLE_1, cv, "KEY_ID = '" + List_Item_Methodes.get(position).getId() + "'", null);
                                        }


                                        try {
                                            //  get_offline();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    }
                    builder.setNegativeButton("View", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                              if (List_Item_Methodes.get(position).getAudi_name().equalsIgnoreCase("PCI")) {
                                    Intent i = new Intent(mContext, PCI_Title_Page_1.class);
                                    i.putExtra("key_id", "" + List_Item_Methodes.get(position).getId());
                                    mContext.startActivity(i);
                                }

                              if (List_Item_Methodes.get(position).getAudi_name().equalsIgnoreCase("PTI")) {
                                    Intent i = new Intent(mContext, PTI_Title_Page_1.class);
                                    i.putExtra("key_id", "" + List_Item_Methodes.get(position).getId());
                                    mContext.startActivity(i);
                                }

                              if (List_Item_Methodes.get(position).getAudi_name().equalsIgnoreCase("VIR")) {
                                    Intent i = new Intent(mContext, PC_VIR_TITLE_1.class);
                                    i.putExtra("key_id", "" + List_Item_Methodes.get(position).getId());
                                    mContext.startActivity(i);
                                }



                                dialog.dismiss();


                        }
                    });

//                    builder.setNeutralButton("GENERATE PDF", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            if (List_Item_Methodes.get(position).getAudi_name().equalsIgnoreCase("PCI")) {
//
//                                Intent i = new Intent(mContext, Main4Activity.class);
//                                i.putExtra("key_id", "" + List_Item_Methodes.get(position).getId());
//                                mContext.startActivity(i);
//                            }
//
//                            if (List_Item_Methodes.get(position).getAudi_name().equalsIgnoreCase("PTI")) {
//                                Toast.makeText(mContext,"Under Development",Toast.LENGTH_SHORT).show();
//                            }
//
//
//                            dialog.dismiss();
//
//
//                        }
//                    });


                  //  builder.setCancelable(false);
                    builder.show();

                } else {
                    //   Toast.makeText(getActivity(),"Sorry Data in",)
                }


            }
        });


        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        List_Item_Methodes.clear();
        if (charText.length() == 0) {
            List_Item_Methodes.addAll(arraylist);
        } else {
            for (com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes wp : arraylist) {
                if (wp.getCus_name().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    List_Item_Methodes.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


}