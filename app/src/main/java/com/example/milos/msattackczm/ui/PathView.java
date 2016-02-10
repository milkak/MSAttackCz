package  com.example.milos.msattackczm.ui;

/**
 * Created by milos on 7.1.2015.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;

public class PathView extends View {

    public int pressobject = -1;
    private Shape mShape;
    private float mRatioRadius;
    private float mXvalue;
    private float mYvalue;
    private Paint paint = new Paint();
    private Paint myPaint;

    private float downx,upx ;
    private float downy,upy ;
    float width = (getWidth()/ 2.0f);
    Rect myCircle;
    TextView mTVCoordinates = null;
    float mX,mY;
    float x;
    public Matrix mMatrix,cMatrix,dMatrix,eMatrix;
    public float mAngle;
    private int stateToSave;



    private int mNumberOfPoint = 3; // default

    public PathView(Context context) {
        super(context);
        init(context);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PathView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mShape = new Shape();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1f);
        myCircle=new Rect((int) (width/ 2.0f+121),130,30,30);

        mMatrix = new Matrix();

        cMatrix = new Matrix();

        dMatrix = new Matrix();
        eMatrix = new Matrix();

        // X (width/ 2.0f+121) Y 128..190





    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

            width = canvas.getWidth();
        int height = canvas.getHeight();

        if ((width == 0) || (height == 0)) {
            return;
        }

        float w2 = getWidth() / 16f;
        float h2 = getHeight() / 16f;
        mMatrix.reset();
        mMatrix.postRotate(mAngle);
        mMatrix.postTranslate(w2, h2);

       cMatrix.reset();
        cMatrix.postRotate(mAngle);
        cMatrix.postTranslate(w2, h2);


        dMatrix.reset();
        dMatrix.postRotate(mAngle);
        dMatrix.postTranslate(w2, h2);

        eMatrix.reset();
        eMatrix.postRotate(mAngle);
        eMatrix.postTranslate(w2, h2);


        canvas.concat(mMatrix);
        
        x = (float) width / 2.0f;
        float y = (float) height / 2.0f;
       /*  float x = (float) width / 2.0f;
         float y = (float) 80;*/
        float radius;
        if (width > height) {
            radius = height * mRatioRadius;
        } else {
            radius = width * mRatioRadius;
        }

      //  mShape.setPolygon(x, y, radius, mNumberOfPoint);
       //canvas.drawPath(mShape.getPath(), mShape.getPaint());
        mShape.setPolygon(x, 70,(float) 69.39, 6);
      // Log.d( "Radius " + radius," "+x+" "+y+" "+width+" "+height);

        canvas.drawPath(mShape.getPath(), mShape.getPaint());


        float startX = 164;
        float startY = 60;
        float stopX = 97;
        float stopY = 147;



        canvas.drawLine(x-68, 65, x-110, 107, paint);


        canvas.drawLine(x+68, 67, x+110, 107, paint);


        paint.setFakeBoldText(true);
        paint.setTextSize(20);
        canvas.drawText("Je to ",x-30,59,paint);

      //  paint.setFakeBoldText(true);
        paint.setTextSize(20);
        canvas.drawText("attack ?",x-30,76,paint);

        // draw hexagon


        mShape.setPolygon(x-131, 170,(float) 69.39, 6);
        canvas.drawPath(mShape.getPath(), mShape.getPaint());
        canvas.drawText("Příznaky ",x-170,150,paint);
        canvas.drawText("konstantní ",x-170,175,paint);
        canvas.drawText("po dobu ",x-170,190,paint);
        canvas.drawText("24 hod",x-170,215,paint);


        // draw circle

        // smooths
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(4.5f);
        // opacity
        //p.setAlpha(0x80); //
        
        // circlebig1
        canvas.drawCircle(x+121, 170,(float) 55.39 , p);

        canvas.drawText("Příznaky ",x+85,160,paint);
        canvas.drawText("vypnutí a",x+85,185,paint);
        canvas.drawText("zapnutí",x+85,210,paint);

        canvas.drawLine(x+131,230, x+131, 250, paint);

//      příznaky vypnutí a zapnutí
       // mShape.setCircle(337,190,(float) 49.39,Path.Direction.CW);


        canvas.drawLine(x-131,230, x-176, 263, paint);
        canvas.drawLine(x-131, 230, x-86, 263, paint);

        canvas.drawPath(mShape.getPath(), mShape.getPaint());

        canvas.drawCircle(x-166, 301,(float) 40.39 , p);
        canvas.drawText("Ano",x-176,301,paint);
        canvas.drawPath(mShape.getPath(), mShape.getPaint());

        canvas.drawCircle(x-80, 301,(float) 40.39 , p);
        canvas.drawText("Ne",x-100,301,paint);


        
        canvas.drawCircle(x+130, 305,(float) 55.39 , p);
        canvas.drawText("Příznaky",x+90,295,paint);
        canvas.drawText("spojené",x+90,315,paint);
        canvas.drawText("s teplem",x+90,330,paint);


        canvas.drawPath(mShape.getPath(), mShape.getPaint());



        //invalidate();



    }

/*    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            // invalidate();
            return false;
        }


        return false;
    }*/



    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // put your logic here

        return super.dispatchTouchEvent(event);
    }


    private int getIndex(MotionEvent event) {
        int idx = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        return idx;
    }


    private Path createCirPath(float x, float y, int id) {
        Path p = new Path();

        p.addCircle(x, y, 50, Path.Direction.CW);
        return p;
    }


    public void setTextView(TextView tv){
        // Reference to TextView Object
        mTVCoordinates = tv;
    }


    public void setShapeRadiusRatio(float ratio) {
        mRatioRadius = ratio;
    }

    public void setNumberOfPoint(int pt) {
        mNumberOfPoint = pt;
    }


    public void setSeekBarXValue(float Xvalue) {
        mXvalue = Xvalue;
    }

    public void setSeekBarYValue(float Yvalue) {
        mYvalue = Yvalue;
    }

}