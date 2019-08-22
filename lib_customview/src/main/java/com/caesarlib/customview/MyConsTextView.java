package com.caesarlib.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.caesarlib.res_tools.CaesarStringDealTool;

/**
 * created by Caesar on 2019/6/3
 * email : 15757855271@163.com
 */
public class MyConsTextView extends ConstraintLayout {
    private int lineHight;
    private String titleTxt;
    private String contextTxt;
    private String contexthintText;

    private TextView tvTitle;
    private TextView tvContext;


    public MyConsTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyConsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.customview_MyConsTextView, defStyleAttr, 0);
        lineHight = array.getInteger(R.styleable.customview_MyConsTextView_customview_line_hight, 1);
        titleTxt = array.getString(R.styleable.customview_MyConsTextView_customview_title);
        contextTxt = array.getString(R.styleable.customview_MyConsTextView_customview_context);
        contexthintText = array.getString(R.styleable.customview_MyConsTextView_customview_context_hint);
        array.recycle();
        initView(context);
    }


    private void initView(Context mContext) {
        LayoutInflater layout = (LayoutInflater) mContext.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        if (layout != null) {
            layout.inflate(R.layout.customview_cons_text_view_layout, this);
        }
        tvTitle = findViewById(R.id.customview_title);
        tvContext = findViewById(R.id.customview_context);
        if (!CaesarStringDealTool.StringIsNull(titleTxt)) {
            tvTitle.setText(titleTxt);
        }
        if (!CaesarStringDealTool.StringIsNull(contexthintText)) {
            tvContext.setHint(contexthintText);
        }
        if (!CaesarStringDealTool.StringIsNull(contextTxt)) {
            tvContext.setText(contextTxt);
        }
    }

    public void upDataContxt(String str) {
        if (tvContext != null) {
            tvContext.setText(str);
        }
    }

}
