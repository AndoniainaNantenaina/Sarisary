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
    public static int ALPHA = 255;
    public static int RED = 0;
    public static int GREEN = 0;
    public static int BLUE = 255;

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

        //  Itération sur les objets à déssiner
        for (int o=0; o< objectToDrawArrayList.size(); o++)
        {
            //  Prendre l'objet sur l'index
            ObjectToDraw object = objectToDrawArrayList.get(o);

            Paint p = new Paint();

            if (object.drawingType == DRAWING_TYPE_SIMPLE)
            {
                p.setARGB(ALPHA, object.red, object.green, object.blue);
                canvas.drawLine(object.beginX, object.beginY, object.endX, object.endY, p);
            }
            else if (object.drawingType == DRAWING_TYPE_ELLIPSE)
            {
                p.setARGB(ALPHA, object.red, object.green, object.blue);
                canvas.drawOval(object.beginX, object.beginY, object.endX, object.endY, p);
            }
            else if (object.drawingType == DRAWING_TYPE_RECT)
            {
                p.setARGB(ALPHA, object.red, object.green, object.blue);
                canvas.drawRect(object.beginX, object.beginY, object.endX, object.endY, p);
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

        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            endX = (int)event.getX();
            endY = (int)event.getY();

            ObjectToDraw obj = new ObjectToDraw();
            obj.drawingType = CURRENT_DRAWING_TYPE;
            obj.red = RED;
            obj.green = GREEN;
            obj.blue = BLUE;

            obj.beginX = beginX;
            obj.beginY = beginY;
            obj.endX = endX;
            obj.endY = endY;

            //  Ajouter dans la liste
            objectToDrawArrayList.add(obj);
            System.out.println(objectToDrawArrayList.size());
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

    public void clear()
    {
        objectToDrawArrayList.clear();
        invalidate();
    }
}