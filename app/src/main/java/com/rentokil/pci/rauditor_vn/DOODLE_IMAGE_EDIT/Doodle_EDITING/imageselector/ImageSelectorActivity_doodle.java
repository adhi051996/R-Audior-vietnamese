package com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.imageselector;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleActivity;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleParams;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleView;
import com.rentokil.pci.rauditor_vn.R;

import java.io.File;
import java.util.ArrayList;


/**
 * 图片选择
 * Created by huangziwei on 16-3-14.
 */
public class ImageSelectorActivity_doodle extends Activity implements View.OnClickListener {

    public static final String KEY_PATH_LIST = "key_path";
    public static final int WHAT_REFRESH_PATH_LIST = 1;
    public static final String KEY_IS_MULTIPLE_CHOICE = "key_is_multiple_choice";
    public static final String KEY_MAX_COUNT = "key_max_count";

    private GridView mGridView;
    private int mCursorPosition = -1; // 当前在数据库查找位置
    private static final int CURSOR_COUNT = 100; //每次查询数据库的数量
    private ArrayList<String> mPathList;
    private Handler mHandler;
    private ImageSelectorAdapter mAdapter;
    private boolean mIsFinishSearchImage = false; // 是否扫描完了所有图片
    private boolean mIsScanning = false; // 正在扫描
    public static final int REQ_CODE_DOODLE = 101;
    String image_name;
    ArrayList<String> image_all;

    private boolean mIsMultipleChoice = false; // 是否多选
    private int mMaxCount = Integer.MAX_VALUE; // 最多可选数量,超过最大数时点击不会选中.多选时次变量才生效
    private TextView mBtnEnter;

    public static void startActivityForResult(int requestCode, Activity activity, ArrayList<String> pathList, boolean multipleChoice) {
        startActivityForResult(requestCode, activity, pathList, multipleChoice, Integer.MAX_VALUE);
    }

    public static void startActivityForResult(int requestCode, Activity activity, ArrayList<String> pathList, boolean multipleChoice, int maxCount) {
        Intent intent = new Intent(activity, ImageSelectorActivity_doodle.class);
        intent.putExtra(ImageSelectorActivity_doodle.KEY_IS_MULTIPLE_CHOICE, multipleChoice);
        intent.putExtra(ImageSelectorActivity_doodle.KEY_MAX_COUNT, maxCount);
        intent.putExtra(ImageSelectorActivity_doodle.KEY_PATH_LIST, pathList);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_selector);



        Toast.makeText(getApplicationContext(),"Works Fine", Toast.LENGTH_LONG).show();

        Bundle extras = getIntent().getExtras();
        image_name=extras.getString("image");
        image_all=extras.getStringArrayList("image_all");

        Log.e("AGGAXFFXFXC","image_all =  "+image_all);
        Log.e("AGGAXFFXFXC","image =  "+image_name);


//        try {
//            ArrayList<String> list = new ArrayList<String>();
//
//            list.add(image_name);
//            Class<?> clazz = Class.forName("com.rentokil.pci.testing2.Doodle.MainActivity");
//            Intent i = new Intent(this, clazz);
//            i.putExtra(KEY_PATH_LIST,list);
//            startActivity(i);
//
//        } catch (Exception e) {
//
//            Log.e("AAHXHNXNXZ","EERRRRR = "+e.getMessage());
//            e.printStackTrace();
//        }




            // 涂鸦参数
        try {
            if (image_name!="check") {
                DoodleParams params = new DoodleParams();
                params.mIsFullScreen = true;
                // 图片路径
                params.mImagePath = image_name;
                // 初始画笔大小
                params.mPaintUnitSize = DoodleView.DEFAULT_SIZE;
                // 画笔颜色
                params.mPaintColor = Color.RED;
                // 是否支持缩放item
                params.mSupportScaleItem = true;
                // 启动涂鸦页面

                params.mimageAll= String.valueOf(image_all);

                Log.e("AAGXGXHCCAX","params = "+params);
                DoodleActivity.startActivityForResult(this, params, REQ_CODE_DOODLE);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        if (getIntent().getExtras() != null) {
            mIsMultipleChoice = getIntent().getBooleanExtra(KEY_IS_MULTIPLE_CHOICE, mIsMultipleChoice);
            if (mIsMultipleChoice) {
                mMaxCount = getIntent().getIntExtra(KEY_MAX_COUNT, mMaxCount);
            } else { // 单选
                mMaxCount = 1;
            }
        }
        mGridView = (GridView) findViewById(R.id.list_image);
        mBtnEnter = (TextView) findViewById(R.id.btn_enter);
        mBtnEnter.setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);
        mPathList = new ArrayList<String>();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case WHAT_REFRESH_PATH_LIST:
                        if (mAdapter == null) {
                            mAdapter = new ImageSelectorAdapter(getApplicationContext(), mPathList);
                            ArrayList<String> list = getIntent().getStringArrayListExtra(KEY_PATH_LIST);
                            if (list != null) {
                                for (int i = 0; i < list.size() && i < mMaxCount; i++) {
                                    mAdapter.addSelected(list.get(i));
                                }
                            }
                            mBtnEnter.setText("Next(" + mAdapter.getSelectedSet().size() + ")");
                            mGridView.setAdapter(mAdapter);
                        } else {
                            mAdapter.refreshPathList(mPathList);
                        }
                        break;
                    default:
                        break;
                }
            }
        };

        // 监听滚动状态
        mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // 滑动到倒数第10个，继续扫描图片
                if (!mIsFinishSearchImage && !mIsScanning
                        && firstVisibleItem + visibleItemCount + 10 >= mPathList.size()) {
                    scanImageData();
                }
            }
        });
        mGridView.setOnItemClickListener(new ItemClickListener());
        scanImageData();
    }

    // 扫描系统数据库中的图片
    private synchronized void scanImageData() {

        if (mIsFinishSearchImage || mIsScanning) {
            return;
        }
        mIsScanning = true;

        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            showToast("暂无外部存储");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {

                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = ImageSelectorActivity_doodle.this.getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED + " DESC"); // 日期降序排序

                mCursor.moveToPosition(mCursorPosition); // 从上一次的扫描位置继续扫描
                int i = 0;
                String path;
                while (mCursor.moveToNext() && i < CURSOR_COUNT) {
                    i++;
                    // 获取图片的路径
                    path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    if (new File(path).exists()) {
                        mPathList.add(path);
                    }
                }
                mCursor.close();
                mCursorPosition += i;
                mIsScanning = false;
                if (i < CURSOR_COUNT) { // 扫描完了所有图片
                    mIsFinishSearchImage = true;
                }
                mHandler.sendEmptyMessage(WHAT_REFRESH_PATH_LIST);
            }
        }).start();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            this.finish();
        } else if (v.getId() == R.id.btn_enter) { // 确认选择
            if (mAdapter.getSelectedSet().size() > 0) {
                Intent intent = new Intent();
                ArrayList<String> list = new ArrayList<String>();

                Log.e("AAHXHNXNXZ","mAdapter = "+mAdapter.getSelectedSet());
                for (String path : mAdapter.getSelectedSet()) {

                    Log.e("AAHXHNXNXZ","path = "+path);
                    list.add(path);
                }

                Log.e("AAHXHNXNXZ","list = "+list);
                intent.putExtra(KEY_PATH_LIST, list);
                setResult(RESULT_OK, intent);
                finish();
            }
        }

    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {

        private View mLastSelected; // 单选时，记录上次选择的view

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String path = (String) view.getTag(ImageSelectorAdapter.KEY_IMAGE);
            if (mIsMultipleChoice) {
                if (mAdapter.getSelectedSet().size() >= mMaxCount) {
                    showToast("最多只能选择" + mMaxCount + "项");
                    return;
                }
                View selectedView = (View) view.getTag(ImageSelectorAdapter.KEY_SELECTED_VIEW);
                if (mAdapter.getSelectedSet().contains(path)) {
                    mAdapter.removeSelected(path);
                    selectedView.setVisibility(View.GONE);
                } else {
                    mAdapter.addSelected(path);
                    selectedView.setVisibility(View.VISIBLE);
                }
            } else { // 单选
                View selectedView = (View) view.getTag(ImageSelectorAdapter.KEY_SELECTED_VIEW);
                if (mAdapter.getSelectedSet().contains(path)) {
                    mAdapter.removeSelected(path);
                    mLastSelected = null;
                    selectedView.setVisibility(View.GONE);
                } else {
                    mAdapter.getSelectedSet().clear();
                    mAdapter.addSelected(path);
                    if (mLastSelected != null) {
                        mLastSelected.setVisibility(View.GONE);
                    }
                    selectedView.setVisibility(View.VISIBLE);
                    mLastSelected = selectedView;
                }
            }
            mBtnEnter.setText("Next(" + mAdapter.getSelectedSet().size() + ")");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
