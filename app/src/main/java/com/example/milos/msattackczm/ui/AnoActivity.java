package com.example.milos.msattackczm.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.milos.msattackczm.MainActivity;
import com.example.milos.msattackczm.R;

/**
 * Created by milos on 21.3.2015.
 */
public class AnoActivity extends ThemableActivity {

    public static Toolbar mToolbar;
    AnoView mAnoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ano_view);

        mAnoView = (AnoView) findViewById(R.id.anoView);
        mAnoView.setOnTouchListener( MyOnTouchListener);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setPopupTheme(getThemeUtils().getPopupTheme());

        String skin = MainActivity.Sp.getString("skin_color", "#5677fc");


        mToolbar.setBackgroundColor(Color.parseColor(skin));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Příznaky konstantní pod dobu 24 hod.");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {


            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    View.OnTouchListener MyOnTouchListener
            = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            if (action == MotionEvent.ACTION_UP) {
                // invalidate();
                return false;
            }
            float w2 = mAnoView.getWidth() / 16f;
            float h2 = mAnoView.getHeight() / 16f;
            float r = 0;
            float rc = 0;
            float rd = 0;
            float re = 0;

            if (action == MotionEvent.ACTION_DOWN) {
                float[] pts = {
                        mAnoView.x-190, 305
                };
                float[] ptsc = {
                        mAnoView.x-65, 305
                };
                float[] ptsd = {
                        mAnoView.x+130, 305
                };

                mAnoView.mMatrix.mapPoints(pts);
                r = (float) Math.hypot(event.getX() - pts[0], event.getY() - pts[1]);

                mAnoView.cMatrix.mapPoints(ptsc);
                rc = (float) Math.hypot(event.getX() - ptsc[0], event.getY() - ptsc[1]);

                mAnoView.dMatrix.mapPoints(ptsd);
                rd = (float) Math.hypot(event.getX() - ptsd[0], event.getY() - ptsd[1]);

            }
            if (r < (float) 55.39) {
                mAnoView.mAngle = (float) (180 * Math.atan2(event.getY() - h2, event.getX() - w2) / Math.PI);
                Log.d("Radius ", " Stlacení kruhu 1");
                mAnoView.pressobject = 1;
                // this is only for one Activity
                if (r!=0.0) {
                    Intent intent = new Intent(getApplicationContext(), InfekceActivity.class);
                    startActivity(intent);
                    AnoActivity.this.recreate();
                }

                return true;
            }
            else if (rc < (float) 55.39) {
                mAnoView.mAngle = (float) (180 * Math.atan2(event.getY() - h2, event.getX() - w2) / Math.PI);
                Log.d("Radius ", " Stlacení kruhu 2");
                mAnoView.pressobject = 2;
                r=1000;
                // this is only for one Activity
                if (rc!=0.0) {
                    Intent intent = new Intent(getApplicationContext(), InfekceNeActivity.class);
                    startActivity(intent);
                    AnoActivity.this.recreate();
                }

                return true;
            }

            else if (rd < (float) 55.39) {
                mAnoView.mAngle = (float) (180 * Math.atan2(event.getY() - h2, event.getX() - w2) / Math.PI);
                Log.d("Radius ", " Stlacení kruhu 3");
                mAnoView.pressobject = 3;
                r=1000;
                // this is only for one Activity
                if (rd!=0.0) {
                    Intent intent = new Intent(getApplicationContext(), NamahaActivity.class);
                    startActivity(intent);
                    AnoActivity.this.recreate();
                }

                return true;
            }




            return false;
        }



    };

}
