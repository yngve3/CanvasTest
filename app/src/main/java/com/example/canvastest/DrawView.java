package com.example.canvastest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import com.example.canvastest.Shapes.Circle;
import com.example.canvastest.Shapes.Line;

public class DrawView extends View {

    private Paint paint;
    private Path path;

    private Line line;
    private Circle circle;

    private boolean toClear = false;

    public DrawView(Context context) {
        super(context);
        paint = new Paint();
        path = new Path();

        circle = new Circle();
        line = new Line();

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);
    }

    public void clear() {
        toClear = true;
        invalidate();
    }

    public void drawCircle(float cx, float cy, float radius) {
        circle.cx = cx;
        circle.cy = cy;
        circle.radius = radius;

        invalidate();
    }

    public void translateCircle(float dx, float dy) {
        circle.cx = dx;
        circle.cy = dy;

        invalidate();
    }

    public void drawLine(float startX, float startY, float stopX, float stopY) {
        line.startX = startX;
        line.startY = startY;
        line.stopX = stopX;
        line.stopY = stopY;

        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(line.startX, line.startY, line.stopX, line.stopY, paint);
        canvas.drawCircle(circle.cx, circle.cy, circle.radius, paint);

        path.reset();
        path.moveTo(line.startX, line.startY);
        path.quadTo(circle.cx, circle.cy, line.stopX, line.stopY);
        canvas.drawPath(path, paint);

        if (toClear) {
            canvas.drawColor(Color.TRANSPARENT);
            toClear = false;
        }
    }
}
