package com.example.paint1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        ImageButton btnDrawLine = (ImageButton) findViewById(R.id.btnDrawLine);
        ImageButton btnDrawEllipse = (ImageButton) findViewById(R.id.btnDrawEllipse);

        btnDrawLine.setOnClickListener(v -> drawingView.setCurrentDrawingType(
                DrawingView.DRAWING_TYPE_RECT));
        btnDrawEllipse.setOnClickListener(v -> drawingView.setCurrentDrawingType(
                DrawingView.DRAWING_TYPE_ELLIPSE));
    }
}