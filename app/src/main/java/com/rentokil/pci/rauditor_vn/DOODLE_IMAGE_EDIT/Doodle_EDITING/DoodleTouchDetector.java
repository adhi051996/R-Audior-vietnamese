package com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING;

import android.content.Context;

import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.core.IDoodleTouchDetector;

import cn.forward.androids.TouchGestureDetector;


public class DoodleTouchDetector extends TouchGestureDetector implements IDoodleTouchDetector {
    public DoodleTouchDetector(Context context, IOnTouchGestureListener listener) {
        super(context, listener);
        // 下面两行绘画场景下应该设置间距为大于等于1，否则设为0双指缩放后抬起其中一个手指仍然可以移动
        this.setScaleSpanSlop(1); // 手势前识别为缩放手势的双指滑动最小距离值
        this.setScaleMinSpan(1); // 缩放过程中识别为缩放手势的双指最小距离值

        this.setIsLongpressEnabled(false);
        this.setIsScrollAfterScaled(false);
    }
}
