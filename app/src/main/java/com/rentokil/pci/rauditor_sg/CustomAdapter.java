package com.rentokil.pci.rauditor_sg;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 11/29/17.
 */

public class CustomAdapter extends BaseAdapter {

    String [] id;
    String [] title;
    String [] description;
    String [] image;
    LayoutInflater inflater;
    Activity context;
    Context mContext;
    private List<com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes> List_Item_Methodes = null;
    private ArrayList<com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes> arraylist;



    public CustomAdapter(Activity context, ArrayList<List_Item_Methodes> List_Item_Methodes) {
        mContext = context;
        this.List_Item_Methodes = List_Item_Methodes;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes>();
        this.arraylist.addAll(List_Item_Methodes);
        this.context = context;



    }

    public class ViewHolder {
        TextView txttitle;
        TextView txtdescrip;
        ImageView imgview;

    }
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


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.card, null);


            holder.txttitle = (TextView)view.findViewById(R.id.title);
            holder.txtdescrip = (TextView)view.findViewById(R.id.description);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }




        Log.e("FFJJFJDJAMAM","cus = "+List_Item_Methodes.get(position).getCus_name());
        Log.e("FFJJFJDJAMAM","aud = "+List_Item_Methodes.get(position).getAudi_date());

        holder.txttitle.setText(List_Item_Methodes.get(position).getCus_name());
        holder.txtdescrip.setText(List_Item_Methodes.get(position).getAudi_date());


//        Glide
//                .with(context)
//                .load(image[position])
//                .into(imgview);




        return view;
    }





}
