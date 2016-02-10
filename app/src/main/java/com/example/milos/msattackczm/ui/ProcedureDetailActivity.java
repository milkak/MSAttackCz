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

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.milos.msattackczm.MainActivity;
import com.example.milos.msattackczm.R;

import java.util.ArrayList;
import java.util.List;


/**
 * An activity representing a single Procedure detail screen. This activity is
 * only used on handset devices. On tablet-size devices, item details are
 * presented side-by-side with a list of items in a
 * {@link ProcedureListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link ProcedureDetailFragment}.
 */
public class ProcedureDetailActivity extends ActionBarActivity  {


    PathView mPathView;
    public static  String TITLE = "";
    public static final String ITEM_ID = "item_id";


    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);





        TITLE = MainActivity.actionBarTitle;
        setContentView(R.layout.activity_procedure_detail);
        mPathView = (PathView) findViewById(R.id.pathView);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(TITLE) ;

        String skin = MainActivity.Sp.getString("skin_color", "#5677fc");

        toolbar.setBackgroundColor(Color.parseColor(skin));




		/*
		savedInstanceState is non-null when there is fragment state
		saved from previous configurations of this activity
		(e.g. when rotating the screen from portrait to landscape).
		In this case, the fragment will automatically be re-added
		to its container so we don't need to manually add it.
		For more information, see the Fragments API guide at:

		http://developer.android.com/guide/components/fragments.html

		*/
        if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(
					ProcedureDetailFragment.ARG_ITEM_ID,
					getIntent().getStringExtra(
							ProcedureDetailFragment.ARG_ITEM_ID));
			ProcedureDetailFragment fragment = new ProcedureDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.procedure_detail_container, fragment).commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		ProcedureDetailFragment fragment = (ProcedureDetailFragment) getSupportFragmentManager()
				.findFragmentById(R.id.procedure_detail_container);
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
            //NavUtils.navigateUpFromSameTask(this);
			return true;
		/*case R.id.settings:
			return true;*/

		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.shortmenu, menu);

		return super.onCreateOptionsMenu(menu);
	}




    
}
