package com.rentokil.pci.rauditor_sg;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes_PDF;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List<com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes_PDF> List_Item_Methodes_PDF = null;
    private ArrayList<com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes_PDF> arraylist;
    private List<Uri> selectedUriList1;
    private RequestManager requestManager1;

    public CustomAdapter(Activity context, ArrayList<List_Item_Methodes_PDF> List_Item_Methodes_PDF) {
        mContext = context;
        this.List_Item_Methodes_PDF = List_Item_Methodes_PDF;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<com.rentokil.pci.rauditor_sg.Adapter.List_Item_Methodes_PDF>();
        this.arraylist.addAll(List_Item_Methodes_PDF);
        this.context = context;
        requestManager1 = Glide.with(context);


    }

    public class ViewHolder {
        TextView txttitle;
        TextView txtdescrip;
        TextView Obs_Count;
        TextView Reoomm;
        ImageView image_1;
         ViewGroup mSelectedImagesContainer1;


    }
    @Override
    public int getCount() {
        return List_Item_Methodes_PDF.size();
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
            holder.Obs_Count = (TextView)view.findViewById(R.id.Obs_Count);
            holder.Reoomm = (TextView)view.findViewById(R.id.Reoomm);
            holder.mSelectedImagesContainer1 = (ViewGroup)view.findViewById(R.id.selected_photos_container1);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }




        Log.e("FFJJFJDJAMAM","cus = "+List_Item_Methodes_PDF.get(position).getCus_name());
        Log.e("FFJJFJDJAMAM","aud = "+List_Item_Methodes_PDF.get(position).getAudi_date());

        holder.txttitle.setText(List_Item_Methodes_PDF.get(position).getCus_name());
        holder.txtdescrip.setText(List_Item_Methodes_PDF.get(position).getAudi_date());
        holder.Obs_Count.setText(List_Item_Methodes_PDF.get(position).getDoc_no());
        holder.Reoomm.setText(List_Item_Methodes_PDF.get(position).getStatus());


        requestManager1 = Glide.with(context);

        if (List_Item_Methodes_PDF.get(position).getimage() != null) {

            String strNew = List_Item_Methodes_PDF.get(position).getimage().replace("[", "");
            String strNew_1 = strNew.replace("]", "");
            List<String> myOld = new ArrayList<String>(Arrays.asList(strNew_1.split(", ")));
            List<Uri> newList = new ArrayList<Uri>(myOld.size());

            for (String uri : myOld) {
                newList.add(Uri.parse(uri));
            }

            selectedUriList1 = newList;

            holder.mSelectedImagesContainer1.removeAllViews();

            //  iv_image.setVisibility(View.GONE);
            holder.mSelectedImagesContainer1.setVisibility(View.VISIBLE);

            int widthPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, context.getResources().getDisplayMetrics());
            int heightPixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, context.getResources().getDisplayMetrics());


            for (Uri uri : newList) {
                Log.e("HJHJH900 JJJ0", "in\t" + uri.toString());
                View imageHolder = LayoutInflater.from(context).inflate(R.layout.image_item, null);
                ImageView thumbnail = imageHolder.findViewById(R.id.media_image);

                requestManager1
                        .load(uri.toString())
                        .apply(new RequestOptions().fitCenter())
                        .into(thumbnail);

                holder.mSelectedImagesContainer1.addView(imageHolder);

                thumbnail.setLayoutParams(new FrameLayout.LayoutParams(widthPixel, heightPixel));

            }
        }


//
//        File file = new File(List_Item_Methodes_PDF.get(position).getimage());
//
//        Log.e("FFJJFJDJAMAM","image = "+List_Item_Methodes_PDF.get(position).getimage());
//
//        Uri imageUri = Uri.fromFile(file);
//
//        Glide.with(context)
//                .load(imageUri)
//                .into(holder.image_1);


//        Glide
//                .with(context)
//                .load(image[position])
//                .into(imgview);




        return view;
    }







}
