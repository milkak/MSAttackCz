package com.example.milos.msattackczm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milos.msattackczm.R;
import com.example.milos.msattackczm.model.RSCentra;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milos on 23.11.2014.
 */
public class RSCentraAdapter extends BaseAdapter  {
    /********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/

        /*********** Declare Used Variables *********/
        private Activity activity;
        private ArrayList data;
        private static LayoutInflater inflater=null;
        public Resources res;
        RSCentra tempValues=null;
        int i=0;

        /*************  CustomAdapter Constructor *****************/
        public RSCentraAdapter(Activity a, ArrayList d,Resources resLocal) {

            /********** Take passed values **********/
            activity = a;
            data=d;
            res = resLocal;

            /***********  Layout inflator to call external xml layout () ***********/
            inflater = ( LayoutInflater )activity.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        /******** What is the size of Passed Arraylist Size ************/
        public int getCount() {

            if(data.size()<=0)
                return 1;
            return data.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        /********* Create a holder Class to contain inflated xml file elements *********/
        public static class ViewHolder{

            public TextView nazev;
            public TextView adresa;
            public TextView url;
            public TextView email;

        }

        /****** Depends upon data size called for each row , Create each ListView row *****/
        public View getView(int position, View convertView, ViewGroup parent) {

            View vi = convertView;
            ViewHolder holder;

            if(convertView==null){

                /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
                vi = inflater.inflate(R.layout.rscenter_item, null);

                /****** View Holder Object to contain tabitem.xml file elements ******/

                holder = new ViewHolder();
                holder.nazev = (TextView) vi.findViewById(R.id.nazev);
                holder.adresa=(TextView)vi.findViewById(R.id.adresa);
                holder.url = (TextView) vi.findViewById(R.id.url);
               // holder.email=(TextView)vi.findViewById(R.id.email);

                /************  Set holder with LayoutInflater ************/
                vi.setTag( holder );
            }
            else
                holder=(ViewHolder)vi.getTag();

            if(data.size()<=0)
            {
                holder.nazev.setText("No Data");

            }
            else
            {
                /***** Get each Model object from Arraylist ********/
                tempValues=null;
                tempValues = (RSCentra) data.get(position);

                /************  Set Model values in Holder elements ***********/



                holder.nazev.setText( "" +tempValues.getNazev());
                holder.adresa.setText( "Adresa: "+tempValues.getAdresa() );
                holder.url.setText(tempValues.getUrl());
               // holder.email.setText(tempValues.getEmail());


            }
            return vi;
        }

    }

