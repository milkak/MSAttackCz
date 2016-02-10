package com.example.milos.msattackczm.ui;

/**
 * Created by milos on 7.1.2015.
 */
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Shape {

    private Paint mPaint;
    private Path mPath;

    public Shape() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
    }

    public void setCircle(float x, float y, float radius, Path.Direction dir) {
        mPath.reset();
        mPath.addCircle(x, y, radius, dir);
    }

    public void setPolygon(float x, float y, float radius, int numOfPt) {

        double section = 2.0 * Math.PI / numOfPt;

        mPath.reset();
        mPath.moveTo((float) (x + radius * Math.cos(0)), (float) (y + radius
                * Math.sin(0)));

        for (int i = 1; i < numOfPt; i++) {
            mPath.lineTo((float) (x + radius * Math.cos(section * i)),
                    (float) (y + radius * Math.sin(section * i)));
        }

        mPath.close();
    }

    public void setStar(float x, float y, float radius, float innerRadius,
                        int numOfPt) {

        double section = 2.0 * Math.PI / numOfPt;

        mPath.reset();
        mPath.moveTo((float) (x + radius * Math.cos(0)), (float) (y + radius
                * Math.sin(0)));
        mPath.lineTo((float) (x + innerRadius * Math.cos(0 + section / 2.0)),
                (float) (y + innerRadius * Math.sin(0 + section / 2.0)));

        for (int i = 1; i < numOfPt; i++) {
            mPath.lineTo((float) (x + radius * Math.cos(section * i)),
                    (float) (y + radius * Math.sin(section * i)));
            mPath.lineTo(
                    (float) (x + innerRadius
                            * Math.cos(section * i + section / 2.0)),
                    (float) (y + innerRadius
                            * Math.sin(section * i + section / 2.0)));
        }

        mPath.close();

    }

    public Path getPath() {
        return mPath;
    }

    public Paint getPaint() {
        return mPaint;
    }
}