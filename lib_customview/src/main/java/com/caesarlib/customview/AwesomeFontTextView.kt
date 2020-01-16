package com.caesarlib.customview

import android.content.Context
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatTextView

import com.caesarlib.res_tools.FontTool

class AwesomeFontTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {


    init {
        typeface = FontTool.getTypeface(context)
    }
}
