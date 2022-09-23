package com.example.canvastest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    DrawView drawView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawView = new DrawView(this);
        drawView.setOnTouchListener(this);
        setContentView(drawView);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        drawView.drawLine((float)(displayMetrics.widthPixels / 2), 0,
                (float)(displayMetrics.widthPixels / 2), displayMetrics.heightPixels - 100);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: drawView.drawCircle(motionEvent.getX(), motionEvent.getY(), 30); break;
            case MotionEvent.ACTION_MOVE:
                drawView.translateCircle(motionEvent.getX(), motionEvent.getY());
                break;
        }
        return true;
    }


}