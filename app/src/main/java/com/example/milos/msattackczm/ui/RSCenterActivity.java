package com.example.milos.msattackczm.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milos.msattackczm.MainActivity;
import com.example.milos.msattackczm.R;
import com.example.milos.msattackczm.model.RSCentra;
import com.example.milos.msattackczm.utils.RSCentraAdapter;
import com.example.milos.msattackczm.utils.XMLParserRScentra;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milos on 23.3.2015.
 */
public class RSCenterActivity extends ThemableActivity{

    public static Toolbar mToolbar;
    private ListView mlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rscenter);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setPopupTheme(getThemeUtils().getPopupTheme());

        String skin = MainActivity.Sp.getString("skin_color", "#5677fc");


        mToolbar.setBackgroundColor(Color.parseColor(skin));


        // get RS center from xml file
        List<RSCentra> arrayOfLines = new ArrayList<RSCentra>();
        try {
            XMLParserRScentra parser = new XMLParserRScentra();
            arrayOfLines = parser.parse( getBaseContext().getAssets().open("RSCentra.xml"));

        } catch (IOException e) {
            e.printStackTrace();
        }




// Create the adapter to convert the array to views
        RSCentraAdapter adapter = new RSCentraAdapter(this,(ArrayList) arrayOfLines,null);
// Attach the adapter to a ListView
        mlist = (ListView) findViewById(R.id.listRScenter);
        mlist.setAdapter(adapter);

        mlist.setOnItemClickListener(new SlideMenuClickListener());



        // Item Click Listener for the listview
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                // Getting the Container Layout of the ListView
                RelativeLayout linearLayoutParent = (RelativeLayout) container;


                // Getting the Url TextView
                TextView tvUrl = (TextView) linearLayoutParent.getChildAt(2);

//                Toast.makeText(getBaseContext(), tvCountry.getText().toString(), Toast.LENGTH_SHORT).show();

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tvUrl.getText().toString()));
                startActivity(browserIntent);


            }
        };

        // Setting the item click listener for the listview
        mlist.setOnItemClickListener(itemClickListener);





        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Seznam RS center");
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

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position,
                                long id) {
            final Intent intent = new Intent(Intent.ACTION_VIEW);
           // String item  =  parent.getSelectedView().findViewById((int)id).toString();

            RelativeLayout mView = ((RelativeLayout) view);
            TextView sText = ((TextView) view.findViewById(R.id.url));

            Log.e("RSCenter", " "+parent.getItemAtPosition(position).toString()+" "+sText) ;

                   // intent.setData(Uri.parse(parent.getItemAtPosition(position).toString()));
                  //  startActivity(intent);


        }
    }



}
