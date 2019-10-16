package com.caesarlib.res_tools

import android.content.Context
import android.graphics.Typeface

//font字体生成器
object FontTool {
    private var typeface: Typeface? = null

    //获取Font的typeface
    fun getTypeface(context:Context): Typeface? {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(
                context.assets,
                "fontawesome-webfont.ttf"
            )
        }
        return typeface
    }

}
