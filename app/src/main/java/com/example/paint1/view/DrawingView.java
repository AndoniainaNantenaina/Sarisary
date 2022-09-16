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

import com.example.paint1.data.ObjectToDraw;

import java.sql.Array;
import java.util.ArrayList;

public class DrawingView extends View
{
    //  Type de dessin
    public static final int DRAWING_TYPE_SIMPLE = 0;
    public static final int DRAWING_TYPE_ELLIPSE = 1;
    public static final int DRAWING_TYPE_RECT = 2;

    //  Type de dessin courant
    int CURRENT_DRAWING_TYPE = DRAWING_TYPE_SIMPLE;

    //  Couleur de dessin (valeur par défaut BLUE)
    int ALPHA = 255;
    int RED = 0;
    int GREEN = 0;
    int BLUE = 255;

    int touchX = -1;
    int touchY = -1;

    int beginX = -1;
    int endX = -1;
    int beginY = -1;
    int endY = -1;

    //  Liste des objets à dessiner
    ArrayList<ObjectToDraw> objectToDrawArrayList = new ArrayList<ObjectToDraw>();

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

        Paint p = new Paint();
        p.setARGB(ALPHA, RED, GREEN, BLUE);

        if (CURRENT_DRAWING_TYPE == DRAWING_TYPE_SIMPLE)
        {
            canvas.drawLine(beginX, beginY, endX, endY, p);
        }
        else if (CURRENT_DRAWING_TYPE == DRAWING_TYPE_RECT)
        {
            canvas.drawRect(beginX, beginY, endX, endY, p);
        }
        else if (CURRENT_DRAWING_TYPE == DRAWING_TYPE_ELLIPSE)
        {
            canvas.drawOval(beginX, beginY, endX, endY, p);
        }

        //  Itération sur les objets à déssiner
        for (int o=0; o< objectToDrawArrayList.size(); o++)
        {
            //  Prendre l'objet sur l'index
            ObjectToDraw object = objectToDrawArrayList.get(o);

            Paint paint = new Paint();

            if (object.drawingType == DRAWING_TYPE_SIMPLE)
            {
                paint.setARGB(ALPHA, object.red, object.green, object.blue);
                canvas.drawLine(object.beginX, object.beginY, object.endX, object.endY, paint);
            }
            else if (object.drawingType == DRAWING_TYPE_ELLIPSE)
            {
                paint.setARGB(ALPHA, object.red, object.green, object.blue);
                canvas.drawOval(object.beginX, object.beginY, object.endX, object.endY, paint);
            }
            else if (object.drawingType == DRAWING_TYPE_RECT)
            {
                paint.setARGB(ALPHA, object.red, object.green, object.blue);
                canvas.drawRect(object.beginX, object.beginY, object.endX, object.endY, paint);
            }
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

        if (event.getAction() == MotionEvent.ACTION_MOVE)
        {
            endX = (int)event.getX();
            endY = (int)event.getY();
        }

        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            ObjectToDraw obj = new ObjectToDraw();

            // Assigner les valeurs
            obj.beginX = this.beginX;
            obj.beginY = this.beginY;
            obj.endX = this.endX;
            obj.endY = this.endY;
            obj.drawingType = this.CURRENT_DRAWING_TYPE;
            obj.alpha = this.ALPHA;
            obj.red = this.RED;
            obj.green = this.GREEN;
            obj.blue = this.BLUE;

            objectToDrawArrayList.add(obj);
        }

        invalidate();

        return true;
    }

    public void setCurrentDrawingType(int currentDrawingType)
    {
        this.CURRENT_DRAWING_TYPE = currentDrawingType;
    }

    public void setColor(int a, int r, int g, int b)
    {
        this.ALPHA = a;
        this.RED = r;
        this.GREEN = g;
        this.BLUE = b;
    }

    public void clear()
    {
        if (objectToDrawArrayList.size() != 0)
        {
            objectToDrawArrayList.remove(
                    objectToDrawArrayList.size() - 1
            );
            invalidate();
        }
    }
}