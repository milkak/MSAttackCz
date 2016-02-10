package com.example.milos.msattackczm      ;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milos.msattackczm.ui.SettingsActivity;
import com.example.milos.msattackczm.utils.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class CountrycodeActivity extends ListActivity {

    public static String RESULT_CONTRYCODE = "countrycode";
    public String[] countrynames, countrycodes;
    private TypedArray imgs;
    private String barva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  populateCountryList();
       /* ArrayAdapter<Country> adapter = new CountryListArrayAdapter(this, countryList);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country c = countryList.get(position);
                Intent returnIntent = new Intent();
                returnIntent.putExtra(RESULT_CONTRYCODE, c.getCode());
                setResult(RESULT_OK, returnIntent);
                imgs.recycle(); //recycle images
                finish();
            }
        });*/

        List<String> stations = new ArrayList<String>();


        CharSequence[] sa1 =this.getResources().getTextArray(R.array.skin);

        for (int i = 0; i < sa1.length; i++) //clean previous selected
        {
            stations.add(sa1[i].toString());

        }

        ArrayAdapter<String> adapter = new MyAdapter(this,1,1, stations,null);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 barva =  view.toString();
                Toast.makeText(getApplicationContext(), " "+barva, Toast.LENGTH_SHORT).show();

                finish();
            }
        });


    }


    public void onColorSelection(String color) {
        Toast.makeText(this, "You selected countrycode: " , Toast.LENGTH_LONG).show();
      SettingsActivity.mToolbar.setBackgroundColor(Color.parseColor(color));
        finish();
        // getThemeUtils().thumbnailColor(color);
//        mCallback.ColorSelectionUpdate( color);
        //recreate();
    }








}