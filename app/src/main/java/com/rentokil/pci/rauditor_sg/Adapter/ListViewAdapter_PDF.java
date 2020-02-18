package com.rentokil.pci.rauditor_sg.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.rentokil.pci.rauditor_sg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter_PDF  extends BaseAdapter {



    String gettxtcolor;
    Context mContext;
    static int half_visible;
    ArrayList<Integer> poss;
    LayoutInflater inflater;
    private List<com.rentokil.pci.rauditor_sg.Adapter.List_Item_PDF_Methodes> List_Item_PDF_Methodes = null;
    private ArrayList<com.rentokil.pci.rauditor_sg.Adapter.List_Item_PDF_Methodes> arraylist;
    public ListViewAdapter_PDF(Context context, ArrayList<List_Item_PDF_Methodes> List_Item_PDF_Methodes) {
        mContext = context;
        this.List_Item_PDF_Methodes = List_Item_PDF_Methodes;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<com.rentokil.pci.rauditor_sg.Adapter.List_Item_PDF_Methodes>();
        this.arraylist.addAll(List_Item_PDF_Methodes);
        //poss = new ArrayList<Integer>();
    }


/* //   public int getCount() {
        return customer_name.length;
    }*/

    @Override
    public int getCount() {
        return List_Item_PDF_Methodes.size();
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
        TextView txtcusid;
        TextView txtpaystat;
        TextView txtpaydate;
        Button View_Bt;


    }
    @SuppressLint("ResourceAsColor")
    public View getView(final int position, View view, final ViewGroup parent) {
        // Declare Variables
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item_completed_pdf, null);

            holder.Tx_Audi_Name = (TextView) view.findViewById(R.id.audi_name);
            holder.Tx_Cus_Name = (TextView) view.findViewById(R.id.cus_name);
            holder.Tx_Audi_Date = (TextView) view.findViewById(R.id.audi_date);
            holder.txtcusid = (TextView) view.findViewById(R.id.txtcusid);
            holder.Tx_Doc_No = (TextView) view.findViewById(R.id.doc_no);
            holder.View_Bt = (Button) view.findViewById(R.id.view_bt);

            gettxtcolor=holder.txtcusid.getText().toString();

//





            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Locate the TextViews in listview_item.xml
        // Capture position and set to the TextViews
        holder.Tx_Cus_Name.setText(List_Item_PDF_Methodes.get(position).getCus_name());
        holder.Tx_Audi_Name.setText(List_Item_PDF_Methodes.get(position).getAudi_name());
        holder.Tx_Audi_Date.setText(List_Item_PDF_Methodes.get(position).getAudi_date());
        holder.txtcusid.setText(List_Item_PDF_Methodes.get(position).getcus_id());
        holder.Tx_Doc_No.setText(List_Item_PDF_Methodes.get(position).getDoc_no());



        holder.View_Bt.setText("View");

        holder.View_Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(List_Item_PDF_Methodes.get(position).getCus_name().equalsIgnoreCase("PCI")){

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://rauditor-sg.riflows.com/rAuditor/Android/downloads/PCI/PCI_"+List_Item_PDF_Methodes.get(position).getDoc_no()+".pdf"));
                    mContext.startActivity(browserIntent);
                }
                else if(List_Item_PDF_Methodes.get(position).getCus_name().equalsIgnoreCase("PTI")){

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://rauditor-sg.riflows.com/rAuditor/Android/downloads/PTI/PTI_"+List_Item_PDF_Methodes.get(position).getDoc_no()+".pdf"));
                    mContext.startActivity(browserIntent);

                }
                else if(List_Item_PDF_Methodes.get(position).getCus_name().equalsIgnoreCase("VIR")){

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://rauditor-sg.riflows.com/rAuditor/Android/downloads/PC_VEH/pc_veh_"+List_Item_PDF_Methodes.get(position).getDoc_no()+".pdf"));
                    mContext.startActivity(browserIntent);

                }


            }
        });


        if(List_Item_PDF_Methodes.get(position).getcus_id().equalsIgnoreCase("Success")){



            Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

            holder.txtcusid.setTypeface(boldTypeface);

            holder.txtcusid.setTextColor(Color.parseColor("#196F3D"));
        }

        else {

            holder.txtcusid.setTextColor(Color.parseColor("#D35400"));

        }

        return view;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        List_Item_PDF_Methodes.clear();
        if (charText.length() == 0) {
            List_Item_PDF_Methodes.addAll(arraylist);
        } else {
            for (com.rentokil.pci.rauditor_sg.Adapter.List_Item_PDF_Methodes wp : arraylist) {
                if (wp.getCus_name().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    List_Item_PDF_Methodes.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}