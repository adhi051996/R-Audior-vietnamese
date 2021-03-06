package com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleColor;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleOnTouchGestureListener;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleShape;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleTouchDetector;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.DoodleView;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.IDoodleListener;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.core.IDoodle;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.core.IDoodleItem;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.core.IDoodlePen;
import com.rentokil.pci.rauditor_vn.R;


/**
 * Mosaic effect
 */
public class MosaicDemo extends Activity {
    private DoodleView doodleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosaic);

        // step 1
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.thelittleprince2);
        doodleView = new DoodleView(this, bitmap, new IDoodleListener() {
            @Override
            public void onSaved(IDoodle doodle, Bitmap doodleBitmap, Runnable callback) {
                Toast.makeText(MosaicDemo.this, "onSaved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReady(IDoodle doodle) {
                doodle.setSize(30 * doodle.getUnitSize());
            }
        });

        // step 2
        DoodleOnTouchGestureListener touchGestureListener = new DoodleOnTouchGestureListener(doodleView, null);
        DoodleTouchDetector touchDetector = new DoodleTouchDetector(this, touchGestureListener);
        doodleView.setDefaultTouchDetector(touchDetector);

        // step 3
        doodleView.setPen(new MosaicPen());
        doodleView.setShape(DoodleShape.HAND_WRITE);
        // setColor
        findViewById(R.id.btn_mosaic_x3).performClick(); // see setMosaicLevel(View view)

        // step 4
        ViewGroup container = (ViewGroup) findViewById(R.id.doodle_container);
        container.addView(doodleView);
    }

    private DoodleColor getMosaicColor(int level) {
        Matrix matrix = new Matrix();
        matrix.setScale(1f / level, 1f / level);
        Bitmap mosaicBitmap = Bitmap.createBitmap(doodleView.getBitmap(),
                0, 0, doodleView.getBitmap().getWidth(), doodleView.getBitmap().getHeight(), matrix, true);
        matrix.reset();
        matrix.setScale(level, level);
        DoodleColor doodleColor = new DoodleColor(mosaicBitmap, matrix);
        doodleColor.setLevel(level);
        return doodleColor;
    }

    public void setMosaicLevel(View view) {
        if (view.getId() == R.id.btn_mosaic_x1) {
            doodleView.setColor(getMosaicColor(5));
        } else if (view.getId() == R.id.btn_mosaic_x2) {
            doodleView.setColor(getMosaicColor(20));
        } else if (view.getId() == R.id.btn_mosaic_x3) {
            doodleView.setColor(getMosaicColor(50));
        }
    }

    /*
     Though setting a new pen here is not necessary, the design-based specification should do this.
     ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     */
    private static class MosaicPen implements IDoodlePen {
        @Override
        public void config(IDoodleItem doodleItem, Paint paint) {
        }

        @Override
        public void drawHelpers(Canvas canvas, IDoodle doodle) {
        }

        @Override
        public IDoodlePen copy() {
            return this;
        }
    }
}
