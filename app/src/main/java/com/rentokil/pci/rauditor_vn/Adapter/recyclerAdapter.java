package com.rentokil.pci.rauditor_vn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rentokil.pci.rauditor_vn.R;
import com.rentokil.pci.rauditor_vn.recyclercons;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */


public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;


    //we are storing all the products in a list
    private List<recyclercons> productList;



    //getting the context and product list with constructor
    public recyclerAdapter(Context mCtx, List<recyclercons> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recyclerdesign, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        recyclercons product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());


        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));





    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = (TextView) itemView.findViewById(R.id.textViewShortDesc);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }


}
