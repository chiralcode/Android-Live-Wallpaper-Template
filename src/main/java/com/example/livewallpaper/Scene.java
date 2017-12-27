package com.example.livewallpaper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Scene {

    private Paint backgroundPaint;
    private Paint outerCirclePaint;
    private Paint circlePaint;

    // animation specific variables
    private float outerCircleRadius;
    private float circleRadius;

    private int centerX;
    private int centerY;

    private int circleX;
    private int circleY;

    private float angle;

    public Scene() {

        backgroundPaint = new Paint();
        backgroundPaint.setColor(0xff8aa8a0);

        outerCirclePaint = new Paint();
        outerCirclePaint.setAntiAlias(true);
        outerCirclePaint.setColor(0xff5e736d);
        outerCirclePaint.setStyle(Style.STROKE);
        outerCirclePaint.setStrokeWidth(3.0f);

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(0xffa2bd3a);
        circlePaint.setStyle(Style.FILL);

    }

    public synchronized void updateSize(int width, int height) {

        centerX = width / 2;
        centerY = height / 2;

        int size = (width < height) ? width : height;

        outerCircleRadius = size / 3;
        circleRadius = outerCircleRadius * 0.2f;

        update();

    }

    public synchronized void update() {

        angle += 1.0f;
        if (angle > 360f) {
            angle -= 360f;
        }

        circleX = (int) (centerX - outerCircleRadius * Math.cos(Math.toRadians(angle)));
        circleY = (int) (centerY - outerCircleRadius * Math.sin(Math.toRadians(angle)));

    }

    public synchronized void draw(Canvas canvas) {

        // clear the background
        canvas.drawPaint(backgroundPaint);

        // draw objects
        canvas.drawCircle(centerX, centerY, outerCircleRadius, outerCirclePaint);
        canvas.drawCircle(centerX, centerY, 5f, circlePaint);
        canvas.drawCircle(circleX, circleY, circleRadius, circlePaint);

    }

}
