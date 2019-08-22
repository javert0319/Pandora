package com.caesarlib.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * created by Caesar on 2019/6/11
 * email : 15757855271@163.com
 */
public class MyCCBar extends View {

    private int viewAllWidth;
    private int viewAllHigh;
    private Paint paint;
    private float lineWidth;
    private float lineMinHigh;
    private ArrayList<CCData> myCData;
    private int[] highD = {1, 3, 5, 4, 6, 2, 7, 5, 6, 3, 2, 1, 2, 1, 2, 6, 5, 4, 2, 7, 5, 2, 3, 1, 2, 1, 3, 2, 1};
    private String[] ColorStr = {"#0050dc", "#0650dc", "#0b51dd", "#1151dd", "#1951de", "#2052de", "#2852df", "#3153df", "#3a53e0", "#4454e0", "#4e54e1", "#5855e2", "#6255e2", "#6d56e3", "#7756e3", "#8257e4", "#8c58e5", "#9758e5", "#a159e6", "#ab59e7", "#b45ae7", "#be5ae8", "#c65be8", "#ce5be9", "#d65ce9", "#dd5cea", "#e45cea", "#e95deb", "#ee5deb"};
    private int currentT = -1;

    public MyCCBar(Context context) {
        super(context);
    }

    public MyCCBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        clearData();
    }

    public MyCCBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);
        }
//            canvas.drawColor(Color.parseColor("#000000"));
        if (myCData.isEmpty()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            for (int i = 0; i < 29; i++) {
                if (i <= currentT) {
                    if (currentT > 0) {
                        int selectC = i * 29 / currentT;
                        if (selectC < 0) {
                            selectC = 0;
                        } else if (selectC > 28) {
                            selectC = 28;
                        }
                        paint.setColor(Color.parseColor(ColorStr[selectC]));
                    } else {
                        paint.setColor(Color.parseColor(ColorStr[0]));
                    }
                } else {
                    paint.setColor(Color.parseColor("#ffffff"));
                }
                canvas.drawRoundRect(myCData.get(i).getLeft(), myCData.get(i).getTop(), myCData.get(i).getRight(), myCData.get(i).getBottom(), 5, 5, paint);
            }
        } else {
            for (int i = 0; i < 29; i++) {
                if (i <= currentT) {
                    if (currentT > 0) {
                        int selectC = i * 29 / currentT;
                        if (selectC < 0) {
                            selectC = 0;
                        } else if (selectC > 28) {
                            selectC = 28;
                        }
                        paint.setColor(Color.parseColor(ColorStr[selectC]));
                    } else {
                        paint.setColor(Color.parseColor(ColorStr[0]));
                    }
                } else {
                    paint.setColor(Color.parseColor("#ffffff"));
                }
                canvas.drawRect(myCData.get(i).getLeft(), myCData.get(i).getTop(), myCData.get(i).getRight(), myCData.get(i).getBottom(), paint);
            }

        }


    }


    public void setCurrent(int current) {
        currentT = 29 * current / 100;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (listener != null) {
                    listener.onStartTrackingTouch();
                }
            case MotionEvent.ACTION_MOVE:
                currentT = (int) ((event.getX() / viewAllWidth) * 29);
                invalidate();
                if (listener != null) {
                    float refan = event.getX();
                    if (refan < 0) {
                        refan = 0;
                    } else if (refan > viewAllWidth) {
                        refan = viewAllWidth;
                    }
                    listener.onProgressChanged((int) (refan / viewAllWidth * 100), true);
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (listener != null) {
                    float refan = event.getX();
                    if (refan < 0) {
                        refan = 0;
                    }
                    listener.onStopTrackingTouch((int) (refan / viewAllWidth * 100));
                }

        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewAllWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewAllHigh = MeasureSpec.getSize(heightMeasureSpec);
        lineWidth = ((float) viewAllWidth) / 85;
        lineMinHigh = ((float) viewAllHigh) / 7;
        setData();
    }


    private void setData() {
        if (myCData.isEmpty()) {
            for (int i = 0; i < 29; i++) {
                float lineW = i * 3 * lineWidth;
                float lineH = ((float) viewAllHigh - highD[i] * lineMinHigh) / 2;
                myCData.add(new CCData(lineW, lineW + lineWidth, lineH, lineH + highD[i] * lineMinHigh, ColorStr[i]));
            }
        }
    }

    private void clearData() {
        if (myCData == null) {
            myCData = new ArrayList<>();
        }
        myCData.clear();
    }

    private OnSeekChangeListener listener;

    public void setOnSeekBarChangeListener(OnSeekChangeListener listener) {
        this.listener = listener;
    }

    public interface OnSeekChangeListener {
        void onProgressChanged(int i, boolean b);

        void onStartTrackingTouch();

        void onStopTrackingTouch(int i);
    }


    class CCData {
        private float left;
        private float right;
        private float top;
        private float bottom;
        private String color;

        public CCData(float left, float right, float top, float bottom, String color) {
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
            this.color = color;
        }

        public float getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public float getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public float getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public float getBottom() {
            return bottom;
        }

        public void setBottom(int bottom) {
            this.bottom = bottom;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

}
