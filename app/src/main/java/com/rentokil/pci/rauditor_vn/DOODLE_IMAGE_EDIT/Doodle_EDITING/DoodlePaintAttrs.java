package com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING;

import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.core.IDoodleColor;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.core.IDoodlePen;
import com.rentokil.pci.rauditor_vn.DOODLE_IMAGE_EDIT.Doodle_EDITING.core.IDoodleShape;


/**
 * 画笔属性
 * Created on 29/06/2018.
 */

public class DoodlePaintAttrs {
    private IDoodlePen mPen; // 画笔类型
    private IDoodleShape mShape; // 画笔形状
    private float mSize; // 大小
    private IDoodleColor mColor; // 颜色

    public IDoodlePen pen() {
        return mPen;
    }

    public DoodlePaintAttrs pen(IDoodlePen pen) {
        mPen = pen;
        return this;
    }

    public IDoodleShape shape() {
        return mShape;
    }

    public DoodlePaintAttrs shape(IDoodleShape shape) {
        mShape = shape;
        return this;
    }

    public float size() {
        return mSize;
    }

    public DoodlePaintAttrs size(float size) {
        mSize = size;
        return this;
    }

    public IDoodleColor color() {
        return mColor;
    }

    public DoodlePaintAttrs color(IDoodleColor color) {
        mColor = color;
        return this;
    }

    public static DoodlePaintAttrs create() {
        return new DoodlePaintAttrs();
    }
}
