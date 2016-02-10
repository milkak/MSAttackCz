package com.example.milos.msattackczm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.example.milos.msattackczm.ui.ProcedureDetailActivity;
import com.example.milos.msattackczm.ui.ProcedureDetailFragment;
import com.example.milos.msattackczm.ui.ProcedureListFragment;
import com.example.milos.msattackczm.ui.RSCenterActivity;
import com.example.milos.msattackczm.ui.SettingsActivity;
import com.example.milos.msattackczm.ui.ThemableActivity;
import com.example.milos.msattackczm.utils.ThemeUtils;

import java.util.ArrayList;


public class MainActivity extends ThemableActivity implements
        ProcedureListFragment.Callbacks  {

/**
 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
 * device.
 */
private boolean mTwoPane;



private DrawerLayout mDrawerLayout;
    private LinearLayout mLiearLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    private TextView mText1;


    public static SharedPreferences Sp;
    String skin;
    public static Toolbar mToolbar;
   public static String actionBarTitle="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Sp = PreferenceManager.getDefaultSharedPreferences(this);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setPopupTheme(getThemeUtils().getPopupTheme());
        setSupportActionBar(mToolbar);


        String skin = Sp.getString("skin_color", "#5677fc");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final boolean dark = ThemeUtils.isDarkMode(this);


        mText1 = (TextView) findViewById(R.id.text1);


        mToolbar.setNavigationIcon(R.drawable.ic_intro_menu);
        mToolbar.setBackgroundColor(Color.parseColor(skin));



        if (dark)  mText1.setTextColor(getThemeUtils().primaryColor());
        else
         mText1.setTextColor(getThemeUtils().primaryColorDark());

        mTitle = mDrawerTitle = getTitle();
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLiearLayout = (LinearLayout) findViewById(R.id.drawer_view);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);


        navDrawerItems = new ArrayList<NavDrawerItem>();

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));

        navMenuIcons.recycle();


        try {
            mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
            adapter = new NavDrawerListAdapter(getApplicationContext(),
                    navDrawerItems);
            mDrawerList.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
/*        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);*/

        // 1

        try {

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar,
                R.string.app_name,
                R.string.app_name
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();

            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);

                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        } catch (Exception e) {
            e.printStackTrace();
        }





        if (savedInstanceState == null) {

            displayView(-1);
        }
    }


    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position,
                                long id) {
            mDrawerLayout.closeDrawer(mLiearLayout);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    displayView(position);
                }
            }, 300);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_rscenter:
                Intent intent = new Intent(getApplicationContext(), RSCenterActivity.class);
                startActivity(intent);
                return true;




            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {



        return super.onPrepareOptionsMenu(menu);
    }


    /**
     * Callback method from {@link ProcedureListFragment.Callbacks} indicating
     * that the item with the given ID was selected.
     */
   /* @Override
    public void onItemSelected(String id,String Title) {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.

            actionBarTitle = Title;
            Intent detailIntent = new Intent(this,
                    ProcedureDetailActivity.class);
            detailIntent.putExtra(ProcedureDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
    }*/



    /**
     * Callback method from {@link ProcedureListFragment.Callbacks} indicating
     * that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id,String Title) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            actionBarTitle = Title;
            Bundle arguments = new Bundle();
            arguments.putString(ProcedureDetailFragment.ARG_ITEM_ID, id);
            ProcedureDetailFragment fragment = new ProcedureDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.procedure_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            actionBarTitle = Title;
            Intent detailIntent = new Intent(this,
                    ProcedureDetailActivity.class);
            detailIntent.putExtra(ProcedureDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }




    private void displayView(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                //fragment = new SettingsActivity.SettingsFragment();
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);

                break;
           case 1:
               final boolean dark = ThemeUtils.isDarkMode(this);

               new MaterialDialog.Builder(this)
                       .title(R.string.app_about)
                       .content(R.string.app_about_popis)
                       .positiveText("Zavřít")
                       .theme(dark ? Theme.DARK : Theme.LIGHT)
                       .show();

                break;
           /* case 2:
                fragment = new ColorPickerFragment();
                break;
                */


            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();


            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
        } else {

            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);
    }






}
