package com.rentokil.pci.rauditor_vn;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by aashish on 8/10/16.
 */
public class CustomEditTextWithBullets extends EditText {

    Context mContext;
    Typeface mTypeface;

    public CustomEditTextWithBullets(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public CustomEditTextWithBullets(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public CustomEditTextWithBullets(Context context) {
        this(context, null, R.attr.editTextStyle);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (lengthAfter > lengthBefore) {
            if (text.toString().length() == 1) {
                text = "• " + text;
                setText(text);
                setSelection(getText().length());
            }
            if (text.toString().endsWith("\n")) {
                text = text.toString().replace("\n", "\n• ");
                text = text.toString().replace("• •", "•");
                setText(text);
                setSelection(getText().length());
            }
        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

}