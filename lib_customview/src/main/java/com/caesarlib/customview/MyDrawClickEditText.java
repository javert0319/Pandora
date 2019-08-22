package com.caesarlib.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatEditText;

/**
 * describe:修改过的edittext，可以在edittext设置内置图时，有点击事件。
 * author：Caesar
 * date:2017/8/24.
 */

public class MyDrawClickEditText extends AppCompatEditText {
    private boolean isPasswordComfirmShown = false;

    public MyDrawClickEditText(Context context) {
        super(context);
    }

    public MyDrawClickEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDrawClickEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Drawable drawableRight = getCompoundDrawables()[2];
                if (drawableRight != null && event.getRawX() >= (getRight() - drawableRight.getBounds()
                        .width())) {
                    if (isPasswordComfirmShown) {
                        setInputType(InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        Editable etable = getText();
                        // 使光标始终在最后位置
                        Selection.setSelection(etable, etable.length());
                    } else {
                        setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        Editable etable = getText();
                        Selection.setSelection(etable, etable.length());
                    }
                    isPasswordComfirmShown = !isPasswordComfirmShown;

                }
                break;
        }
        return super.onTouchEvent(event);
    }

}
