package com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleActivity;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleParams;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleView;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.models.ImageItem;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.models.ImageListContent;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.utilities.DraweeUtils;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Multiple_IMAGE_SELECTOR.utilities.FileUtils;
import com.rentokil.pci.rauditor_vn.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder> {

    private final List<ImageItem> mValues;
    private final OnImageRecyclerViewInteractionListener mListener;
    private static final String TAG = "ImageAdapter";
    Context mContext;
    ArrayList<String> arrayList;
    ArrayList<String> selected1;
    public static final int REQ_CODE_DOODLE = 101;



    String get_key_id="",get_et1="",get_et2="",get_et3="",get_counter="",get_page="";

    public ImageRecyclerViewAdapter(List<ImageItem> items, OnImageRecyclerViewInteractionListener listener, ImagesSelectorActivity_pick context, ArrayList selected,String key_id,String et1,String et2,String et3,String counter,String page) {
       mContext=context;
        mValues = items;
        mListener = listener;
        selected1 = selected;
        get_key_id = key_id;
        get_et1 = et1;
        get_et2 = et2;
        get_et3 = et3;
        get_counter = counter;
        get_page = page;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_image_item, parent, false);


        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ImageItem imageItem = mValues.get(position);
        holder.mItem = imageItem;

        Uri newURI;
        if (!imageItem.isCamera()) {
            // draw image first
            File imageFile = new File(imageItem.path);
            if (imageFile.exists()) {
                newURI = Uri.fromFile(imageFile);
            } else {
                newURI = FileUtils.getUriByResId(R.drawable.default_image);
            }
            DraweeUtils.showThumb(newURI, holder.mDrawee);

            holder.mImageName.setVisibility(View.GONE);
            holder.mChecked.setVisibility(View.VISIBLE);
            if (ImageListContent.isImageSelected(imageItem.path)) {

                holder.mMask.setVisibility(View.VISIBLE);
                holder.mChecked.setImageResource(R.drawable.image_selected);
            } else {


                holder.mMask.setVisibility(View.GONE);
                holder.mChecked.setImageResource(R.drawable.image_unselected);
            }
        } else {
            // camera icon, not normal image
            newURI = FileUtils.getUriByResId(R.drawable.ic_photo_camera_white_48dp);
            DraweeUtils.showThumb(newURI, holder.mDrawee);

            holder.mImageName.setVisibility(View.VISIBLE);
            holder.mChecked.setVisibility(View.GONE);
            holder.mMask.setVisibility(View.GONE);
        }

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Intent intent = null;
                try {


                    String selectedItem = imageItem.path;

                    share.store_data=selectedItem ;


                    Log.e("MMJAJAJXHXX", "SELECTED_IMAGESSSS = " + ImageListContent.SELECTED_IMAGES);



//                    Intent i = new Intent(mContext, ImageSelectorActivity_doodle.class);
//                    i.putExtra("image",imageItem.path);
//                    i.putExtra("image_all",ImageListContent.SELECTED_IMAGES);
//                    i.putStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS, ImageListContent.SELECTED_IMAGES);
//                    mContext.startActivity(i);
//                    ((Activity)mContext).finish();
                    Log.e("AABAXBBXXVF","get_key_id = "+get_key_id);


                    DoodleParams params = new DoodleParams();
                    params.mIsFullScreen = true;
                    // 图片路径
                    params.mImagePath = imageItem.path;
                    // 初始画笔大小
                    params.mPaintUnitSize = DoodleView.DEFAULT_SIZE;
                    // 画笔颜色
                    params.mPaintColor = Color.RED;
                    // 是否支持缩放item
                    params.mSupportScaleItem = true;
                    // 启动涂鸦页面

                    params.mimageAll= String.valueOf(ImageListContent.SELECTED_IMAGES);
                    params.mkey_id= String.valueOf(get_key_id);
                    params.met1= String.valueOf(get_et1);
                    params.met2= String.valueOf(get_et2);
                    params.met3= String.valueOf(get_et3);
                    params.mcounter= String.valueOf(get_counter);
                    params.mpage= String.valueOf(get_page);

                    Log.e("AABAXBBXXVF","get_counter = "+get_counter);
                    Log.e("AABAXBBXXVF","params.mkey_id = "+params.mkey_id);
                    DoodleActivity.startActivityForResult((Activity) mContext, params, REQ_CODE_DOODLE);

                } catch (Exception e) {
                    Log.e("MMJAJAJXHXX", "Error = " + e.getMessage());
                    e.printStackTrace();
                }

                Log.e("MMJAJAJXHXX", "long pressed = " + imageItem.path);
                return true;
            }
        });


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.d(TAG, "onClick: " + holder.mItem.toString());

                Log.e("MMJAJAJXHXX","imageItem= "+imageItem.path);
                if(!holder.mItem.isCamera()) {
                    if(!ImageListContent.isImageSelected(imageItem.path)) {
                        // just select one new image, make sure total number is ok
                        if(ImageListContent.SELECTED_IMAGES.size() < SelectorSettings.mMaxImageNumber) {
                            ImageListContent.toggleImageSelected(imageItem.path);
                            notifyItemChanged(position);
                        } else {
                            // set flag
                            ImageListContent.bReachMaxNumber = true;
                        }
                    } else {
                        // deselect
                        ImageListContent.toggleImageSelected(imageItem.path);
                        notifyItemChanged(position);
                    }
                } else {
                    // do nothing here, listener will launch camera to capture image
                }
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onImageItemInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final SimpleDraweeView mDrawee;
        public final ImageView mChecked;
        public final View mMask;
        public ImageItem mItem;
        public TextView mImageName;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mDrawee = (SimpleDraweeView) view.findViewById(R.id.image_drawee);
            assert mDrawee != null;
            mMask = view.findViewById(R.id.image_mask);
            assert mMask != null;
            mChecked = (ImageView) view.findViewById(R.id.image_checked);
            assert mChecked != null;
            mImageName = (TextView) view.findViewById(R.id.image_name);
            assert mImageName != null;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }


}
