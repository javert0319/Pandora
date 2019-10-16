package com.caesarlib.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.caesarlib.res_tools.FontTool;

public class AwesomeFontTextView extends AppCompatTextView {


    public AwesomeFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(FontTool.INSTANCE.getTypeface(context));
    }
}
