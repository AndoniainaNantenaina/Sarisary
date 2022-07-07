package com.example.paint1.view;

import android.app.Notification;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;

import java.sql.Array;
import java.util.ArrayList;

public class DrawingView extends View
{
    Paint paint = new Paint();

    ArrayList<Integer> tabX = new ArrayList<Integer>();
    ArrayList<Integer> tabY = new ArrayList<Integer>();

    int touchX = -1;
    int touchY = -1;

    public static final int DRAWING_TYPE_RECT=1;
    public static final int DRAWING_TYPE_ELLIPSE=2;

    //  Création du constructeur
    public DrawingView(Context context) {
        super(context);
    }

    public DrawingView(
            Context context,
            @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (touchX != -1 && touchY != -1) {
            for (int i = 0; i < tabX.size(); i++) {
                paint.setColor(Color.rgb(i*15, i*150, i*84));
                canvas.drawRect(
                        tabX.get(i) - 75,
                        tabY.get(i) - 75,
                        tabX.get(i) + 75,
                        tabY.get(i) + 75,
                        paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        touchX = (int)event.getX();
        touchY = (int)event.getY();


        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            tabX.add(touchX);
            tabY.add(touchY);
        }

        invalidate();

        return true;
    }
}