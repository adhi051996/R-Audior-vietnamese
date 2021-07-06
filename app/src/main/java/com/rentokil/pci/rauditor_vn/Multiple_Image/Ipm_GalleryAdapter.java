package com.rentokil.pci.rauditor_vn.Multiple_Image;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.rentokil.pci.rauditor_vn.Database.DatabaseHelper;
import com.rentokil.pci.rauditor_vn.R;

import java.util.ArrayList;

public class Ipm_GalleryAdapter extends BaseAdapter {

    private Context ctx;
    private int pos;
    private LayoutInflater inflater;
    public static ImageView ivGallery;
    ArrayList<Uri> mArrayUri;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;

    public Ipm_GalleryAdapter(Context ctx, ArrayList<Uri> mArrayUri) {
        this.ctx = ctx;
        this.mArrayUri = mArrayUri;
    }

    @Override
    public int getCount() {
        return mArrayUri.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayUri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        pos = position;
        inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.gv_item, parent, false);

        ivGallery = (ImageView) itemView.findViewById(R.id.ivGallery);

        ivGallery.setImageURI(mArrayUri.get(position));


        ivGallery.setTag(Integer.valueOf(position));

        db = new DatabaseHelper(ctx);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();

//        if(db.get_mobile_model(sd).startsWith("samsung")) {
//
//            ivGallery.setRotation(90);
//        }





        return itemView;


    }
}
