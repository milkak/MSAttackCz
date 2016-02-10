/*  
Copyright (C) 2013, 2014 EP Studios, Inc.
www.epstudiossoftware.com

This file is part of EP Coding.

    EP Coding is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    EP Coding is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with EP Coding.  If not, see <http://www.gnu.org/licenses/>.

    Note also:

    CPT copyright 2012 American Medical Association. All rights
    reserved. CPT is a registered trademark of the American Medical
    Association.

    A limited number of CPT codes are used in this program under the Fair Use
    doctrine of US Copyright Law.  See README.md for more information.
 */

package com.example.milos.msattackczm.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.milos.msattackczm.R;

import java.util.Set;
import java.util.TreeSet;

/**
 * A fragment representing a single Procedure detail screen. This fragment is
 * either contained in a {@link ProcedureListActivity} in two-pane mode (on
 * tablets) or a {@link ProcedureDetailActivity} on handsets.
 */
@SuppressWarnings("FieldCanBeLocal")
public class ProcedureDetailFragment extends Fragment implements
		OnClickListener {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";
    private Callbacks mCallbacks;



    /**
	 * The content this fragment is presenting.
	 */
	private int mItem = 0;

	// get context from owning Activity
	private Context context;

    PathView mPathView;



    /**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ProcedureDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		if (getArguments().containsKey(ARG_ITEM_ID)) {
			String itemID = getArguments().getString(ARG_ITEM_ID);
			try {
				mItem = Integer.parseInt(itemID);
			} catch (NumberFormatException e) {
				mItem = 0; // AFB ablation will be shown
			}
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

	@Override
	public void onResume() {
		super.onResume();
		loadSettings();
	}

    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String title);
    }


    @Override
	public void onClick(View v) {
		/*switch (v.getId()) {
		case R.id.summary_button:
			summarizeCoding();
			break;

		case R.id.clear_button:
			clearEntries();
			break;
		}*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.procedure_codes, container,
				false);
		context = getActivity();
        mPathView = (PathView) rootView.findViewById(R.id.pathView);
        mPathView.setOnTouchListener( MyOnTouchListener);

        TextView primaryCodeTextView = (TextView) rootView
                .findViewById(R.id.primary_code_textView);
        TextView secondaryCodeTextView = (TextView) rootView
                .findViewById(R.id.secondary_code_textView);
        TextView CodeTextView = (TextView) rootView
                .findViewById(R.id.code_textView);



		loadSettings();

		switch (mItem) {
            case 0:
                mPathView.setVisibility(View.INVISIBLE);
                secondaryCodeTextView.setVisibility(View.INVISIBLE);
                CodeTextView.setVisibility(View.INVISIBLE);
                primaryCodeTextView.setText(R.string.code_vision_loss);
                break;
            case 1:
                mPathView.setVisibility(View.INVISIBLE);
                secondaryCodeTextView.setVisibility(View.INVISIBLE);
                CodeTextView.setVisibility(View.INVISIBLE);
                primaryCodeTextView.setText(R.string.code_double_vision);
                break;
            case 2:
                mPathView.setVisibility(View.INVISIBLE);
                secondaryCodeTextView.setVisibility(View.INVISIBLE);
                CodeTextView.setVisibility(View.INVISIBLE);
                primaryCodeTextView.setText(R.string.code_weakness);
                break;
            case 3:
                mPathView.setVisibility(View.INVISIBLE);
                secondaryCodeTextView.setVisibility(View.INVISIBLE);
                CodeTextView.setVisibility(View.INVISIBLE);
                primaryCodeTextView.setText(R.string.code_walking);
                break;
            case 4:
                mPathView.setVisibility(View.INVISIBLE);
                secondaryCodeTextView.setVisibility(View.INVISIBLE);
                CodeTextView.setVisibility(View.INVISIBLE);
                primaryCodeTextView.setText(R.string.code_vertigo);
                break;
            case 5:
                primaryCodeTextView.setTextAppearance(getActivity(), android.R.style.TextAppearance_DeviceDefault_Medium);
                //primaryCodeTextView.textAppearance
                secondaryCodeTextView.setTextAppearance(getActivity(), android.R.style.TextAppearance_DeviceDefault);
                CodeTextView.setTextAppearance(getActivity(), android.R.style.TextAppearance_DeviceDefault);
                primaryCodeTextView.setText(R.string.code_attack1);
                secondaryCodeTextView.setText(R.string.code_attack2);
                CodeTextView.setText(R.string.code_attack3);

                primaryCodeTextView.setTextColor(Color.MAGENTA);


                break;
		default:
			break;
		}


		// set up buttons
		return rootView;
	}


	private void clearEntries() {

	}

	private void summarizeCoding() {
	}

	public void saveCoding() {
	}

	public void loadCoding() {
		load(context);
	}

	private void load(Context context) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		String mItemString = String.valueOf(mItem);
		Set<String> defaultStringSet = new TreeSet<String>();
		Set<String> codeNumbersChecked = prefs.getStringSet(mItemString,
				defaultStringSet);

	}

	void loadSettings() {
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
          float w2 = mPathView.getWidth() / 16f;
          float h2 = mPathView.getHeight() / 16f;
          float r = 0;
          float rc = 0;
          float rd = 0;
          float re = 0;

          if (action == MotionEvent.ACTION_DOWN) {
              float[] pts = {
                      mPathView.x+121, 170
              };
              float[] ptsc = {
                      mPathView.x-161, 301
              };
              float[] ptsd = {
                      mPathView.x-80, 301
              };
              float[] ptse = {
                      mPathView.x+130, 305
              };

              mPathView.mMatrix.mapPoints(pts);
              r = (float) Math.hypot(event.getX() - pts[0], event.getY() - pts[1]);

              mPathView.cMatrix.mapPoints(ptsc);
              rc = (float) Math.hypot(event.getX() - ptsc[0], event.getY() - ptsc[1]);

              mPathView.dMatrix.mapPoints(ptsd);
              rd = (float) Math.hypot(event.getX() - ptsd[0], event.getY() - ptsd[1]);

              mPathView.eMatrix.mapPoints(ptse);
              re = (float) Math.hypot(event.getX() - ptse[0], event.getY() - ptse[1]);
          }
          if (r < (float) 55.39) {
              mPathView.mAngle = (float) (180 * Math.atan2(event.getY() - h2, event.getX() - w2) / Math.PI);
              Log.d("Radius ", " Stlacení kruhu 1");
              mPathView.pressobject = 1;
             // this is only for one Activity
              if (r!=0.0) {
                  Intent intent = new Intent(context, PseudoActivity.class);
                  startActivity(intent);
                  getActivity().recreate();
              }

              return true;
          }
          else if (rc < (float) 40.39) {
              mPathView.mAngle = (float) (180 * Math.atan2(event.getY() - h2, event.getX() - w2) / Math.PI);
              Log.d("Radius ", " Stlacení kruhu 2");
              mPathView.pressobject = 2;
              r=1000;
              // this is only for one Activity
              if (rc!=0.0) {
                  Intent intent = new Intent(context, AnoActivity.class);
                  startActivity(intent);
                  getActivity().recreate();
              }

              return true;
          }

          else if (rd < (float) 40.39) {
              mPathView.mAngle = (float) (180 * Math.atan2(event.getY() - h2, event.getX() - w2) / Math.PI);
              Log.d("Radius ", " Stlacení kruhu 3");
              mPathView.pressobject = 3;
              r=1000;
              // this is only for one Activity
              if (rc!=0.0) {
                  Intent intent = new Intent(context, NeActivity.class);
                  startActivity(intent);
                  getActivity().recreate();
              }
              return true;
          }

          else if (re < (float) 55.39) {
              mPathView.mAngle = (float) (180 * Math.atan2(event.getY() - h2, event.getX() - w2) / Math.PI);
              Log.d("Radius ", " Stlacení kruhu 4");
              mPathView.pressobject = 4;
              Log.d("Radius ", " Stlacení kruhu 4"+mPathView.pressobject);
              r=1000;
              // this is only for one Activity
              if (re!=0.0) {
                  Intent intent = new Intent(context, PseudoActivity.class);
                  startActivity(intent);
                  getActivity().recreate();
              }

              return true;
          }



          return false;
      }



  };

}
