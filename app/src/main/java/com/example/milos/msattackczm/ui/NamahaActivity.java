package com.example.milos.msattackczm.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.milos.msattackczm.MainActivity;
import com.example.milos.msattackczm.R;

/**
 * Created by milos on 22.3.2015.
 */
public class NamahaActivity  extends ThemableActivity {

    public static Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pseudo_attack);

        TextView CodeTextView = (TextView) findViewById(R.id.code_pseudoattack);
        CodeTextView.setText(R.string.namaha);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setPopupTheme(getThemeUtils().getPopupTheme());

        String skin = MainActivity.Sp.getString("skin_color", "#5677fc");


        mToolbar.setBackgroundColor(Color.parseColor(skin));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Neprodělal jsem žádnou námahu.");
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

}
