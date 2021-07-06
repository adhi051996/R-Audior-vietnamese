package com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle.guide.DoodleGuideActivity;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleActivity;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleParams;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleView;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.imageselector.ImageLoader;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.imageselector.ImageSelectorActivity_doodle;
import com.rentokil.pci.rauditor_vn.R;

import java.util.ArrayList;

import cn.forward.androids.utils.LogUtil;
import cn.hzw.imageselector.ImageSelectorActivity;


public class MainActivity extends Activity {

    public static final int REQ_CODE_SELECT_IMAGE = 100;
    public static final int REQ_CODE_DOODLE = 101;
    private TextView mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doodle_main);

        Log.e("AAGXGXHCCAX","entered = ");

        Toast.makeText(getApplicationContext(),"Works", Toast.LENGTH_LONG).show();

        findViewById(R.id.btn_select_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("AAGXGXHCCAX","REQ_CODE_SELECT_IMAGE = "+REQ_CODE_SELECT_IMAGE);

              ImageSelectorActivity_doodle.startActivityForResult(REQ_CODE_SELECT_IMAGE, MainActivity.this, null, false);
            }
        });

        findViewById(R.id.btn_guide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DoodleGuideActivity.class));
            }
        });

        findViewById(R.id.btn_mosaic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MosaicDemo.class));
            }
        });
        findViewById(R.id.btn_scale_gesture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScaleGestureItemDemo.class));
            }
        });
        mPath = (TextView) findViewById(R.id.img_path);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("AAGXGXHCCAX","requestCode = "+requestCode);

        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (data == null) {
                return;
            }

            Log.e("AAGXGXHCCAX","data = "+data.getStringArrayListExtra(ImageSelectorActivity.KEY_PATH_LIST));

            ArrayList<String> list = data.getStringArrayListExtra(ImageSelectorActivity.KEY_PATH_LIST);
            if (list != null && list.size() > 0) {
                LogUtil.d("Doodle", list.get(0));

                // 涂鸦参数
                DoodleParams params = new DoodleParams();
                params.mIsFullScreen = true;
                // 图片路径
                params.mImagePath = list.get(0);
                // 初始画笔大小
                params.mPaintUnitSize = DoodleView.DEFAULT_SIZE;
                // 画笔颜色
                params.mPaintColor = Color.RED;
                // 是否支持缩放item
                params.mSupportScaleItem = true;
                // 启动涂鸦页面

                Log.e("AAGXGXHCCAX","params = "+params);
                DoodleActivity.startActivityForResult(MainActivity.this, params, REQ_CODE_DOODLE);

                finish();
            }
        } else if (requestCode == REQ_CODE_DOODLE) {
            if (data == null) {
                return;
            }
            if (resultCode == DoodleActivity.RESULT_OK) {


                String path = data.getStringExtra(DoodleActivity.KEY_IMAGE_PATH);

                Log.e("AAGXGXHCCAX","path = "+path);
                if (TextUtils.isEmpty(path)) {
                    return;
                }
                ImageLoader.getInstance(this).display(findViewById(R.id.img), path);
                mPath.setText(path);
            } else if (resultCode == DoodleActivity.RESULT_ERROR) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
