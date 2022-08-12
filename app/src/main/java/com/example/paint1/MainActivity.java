package com.example.paint1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.paint1.view.DrawingView;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Prendre le classe DrawingView
        drawingView = (DrawingView) findViewById(R.id.drawingView);
        setUpToolbarEventListener();
    }

    private void setUpToolbarEventListener()
    {
        //  Bouton de type de dessin
        ImageButton btnDrawLine = (ImageButton) findViewById(R.id.btnDrawRect);
        ImageButton btnDrawEllipse = (ImageButton) findViewById(R.id.btnDrawEllipse);
        ImageButton btnDrawSimple = (ImageButton) findViewById(R.id.btnDrawLine);

        btnDrawSimple.setOnClickListener(v -> drawingView.setCurrentDrawingType(
                DrawingView.DRAWING_TYPE_SIMPLE));
        btnDrawLine.setOnClickListener(v -> drawingView.setCurrentDrawingType(
                DrawingView.DRAWING_TYPE_RECT));
        btnDrawEllipse.setOnClickListener(v -> drawingView.setCurrentDrawingType(
                DrawingView.DRAWING_TYPE_ELLIPSE));

        Button redColorBtn = (Button) findViewById(R.id.redColorBtn);
        Button greenColorBtn = (Button) findViewById(R.id.greenColorBtn);
        Button blueColorBtn = (Button) findViewById(R.id.blueColorBtn);

        redColorBtn.setOnClickListener(
                v -> drawingView.setColor(255, 255, 0, 0)
        );
        greenColorBtn.setOnClickListener(
                v-> drawingView.setColor(255, 0, 255, 0)
        );
        blueColorBtn.setOnClickListener(
                v -> drawingView.setColor(255, 0, 0, 255)
        );

        ImageButton btnClear = (ImageButton) findViewById(R.id.btnClearDraw);
        btnClear.setOnClickListener(v -> drawingView.clear());
    }
}