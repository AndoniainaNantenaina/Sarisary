package com.example.paint1.view;

import android.app.Notification;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;

import java.sql.Array;
import java.util.ArrayList;

public class DrawingView extends View
{
    //  Type de dessin
    public static final int DRAWING_TYPE_SIMPLE = 0;
    public static final int DRAWING_TYPE_ELLIPSE = 1;
    public static final int DRAWING_TYPE_RECT = 2;

    int CURRENT_DRAWING_TYPE = DRAWING_TYPE_SIMPLE;

    //  Couleur de dessin (valeur par défaut BLUE)
    public static int ALPHA = 255;
    public static int RED = 0;
    public static int GREEN = 0;
    public static int BLUE = 255;

    ArrayList<Integer> tabEllipseX = new ArrayList<Integer>();
    ArrayList<Integer> tabEllipseY = new ArrayList<Integer>();
    ArrayList<Integer> tabRectX = new ArrayList<Integer>();
    ArrayList<Integer> tabRectY = new ArrayList<Integer>();

    ArrayList<Rect> tabRect = new ArrayList<Rect>();
    ArrayList<RectF> tabEllipse = new ArrayList<RectF>();
    ArrayList<Paint> tabPaint = new ArrayList<Paint>();

    int touchX = -1;
    int touchY = -1;

    int beginX = -1;
    int endX = -1;
    int beginY = -1;
    int endY = -1;

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

        if (beginX != -1 && beginY != -1 && endX != -1 && endY != -1)
        {
            Paint p = new Paint();

            if (CURRENT_DRAWING_TYPE == DRAWING_TYPE_SIMPLE)
            {
                p.setARGB(ALPHA, RED, GREEN, BLUE);
                canvas.drawLine(beginX, beginY, endX, endY, p);
            }
            else if (CURRENT_DRAWING_TYPE == DRAWING_TYPE_ELLIPSE)
            {
                p.setARGB(ALPHA, RED, GREEN, BLUE);
                canvas.drawOval(beginX, beginY, endX, endY, p);
            }
            else if (CURRENT_DRAWING_TYPE == DRAWING_TYPE_RECT)
            {
                p.setARGB(ALPHA, RED, GREEN, BLUE);
                canvas.drawRect(beginX, beginY, endX, endY, p);
            }

            /*//  Itération sur la liste des points X rectangle
            for (int r=0; r<=tabRectX.size(); r++)
            {
                int beginX = tabRectX.get(r),
                    beginY = tabRectY.get(r),
                    endX = tabRectX.get(r + 1),
                    endY = tabRectY.get(r + 1);

                Paint p = new Paint();
                canvas.drawRect(beginX, beginY, endX, endY, p);
            }*/

            /*for (int r=0; r<=tabEllipseX.size(); r++)
            {
                int beginX = tabEllipseX.get(r),
                        beginY = tabEllipseX.get(r),
                        endX = tabEllipseX.get(r + 1),
                        endY = tabEllipseX.get(r + 1);

                Paint p = new Paint();
                canvas.drawOval(beginX, beginY, endX, endY, p);
            }*/

            //for (int e=0; e<tabEllipseX.size(); e++)
            //{
            //}
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //  Récupération des coords X/Y des touchés
        touchX = (int)event.getX();
        touchY = (int)event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            beginX = (int)event.getX();
            beginY = (int)event.getY();
        }

        if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_MOVE)
        {
            endX = (int)event.getX();
            endY = (int)event.getY();
        }

        /*//  Ajouter dans les tableaux de coords suivant le type de dessin
        if (CURRENT_DRAWING_TYPE == DRAWING_TYPE_SIMPLE)
        {
            //  Ajout des coords X
            tabRectX.add(beginX);
            tabRectX.add(endX);

            //  Ajout des coords Y
            tabRectY.add(beginY);
            tabRectY.add(endY);
        }
        else if (CURRENT_DRAWING_TYPE == DRAWING_TYPE_ELLIPSE)
        {
            //  Ajout des coords X
            tabEllipseX.add(beginX);
            tabEllipseX.add(endX);

            //  Ajout des coords Y
            tabEllipseY.add(beginY);
            tabEllipseY.add(endY);
        }
        else if (CURRENT_DRAWING_TYPE == DRAWING_TYPE_RECT)
        {
            //  Ajout des coords X
            tabRectX.add(beginX);
            tabRectX.add(endX);

            //  Ajout des coords Y
            tabRectY.add(beginY);
            tabRectY.add(endY);
        }*/

        invalidate();

        return true;
    }

    public void setCurrentDrawingType(int currentDrawingType)
    {
        this.CURRENT_DRAWING_TYPE = currentDrawingType;
    }

    public void setColor(int a, int r, int g, int b)
    {
        ALPHA = a;
        RED = r;
        GREEN = g;
        BLUE = b;
    }
}