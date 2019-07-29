package com.example.andy.temp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class Canvas extends LinearLayout {
    private Paint paint;
    public void initPaint(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(30);
    }
    public Canvas(Context context) {
        super(context);
        initPaint();
    }

    public Canvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Canvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(100,100,200,200,paint);

    }

}
