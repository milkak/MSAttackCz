package com.example.milos.msattackczm.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.example.milos.msattackczm.MainActivity;
import com.example.milos.msattackczm.R;
import com.example.milos.msattackczm.utils.MyAdapter;
import com.example.milos.msattackczm.utils.ThemeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Aidan Follestad (afollestad)
 */
public class SettingsActivity extends ThemableActivity
{
    public static Toolbar mToolbar;
    SharedPreferences  Sp;



    public static class SettingsFragment extends PreferenceFragment  implements  MyAdapter.ColorCallback {
        SharedPreferences prefs;
        MyAdapter ColorCallback;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preferences);

            findPreference("base_theme").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                //    ImageLoader.getInstance().clearMemoryCache();

                    final boolean dark = ThemeUtils.isDarkMode(getActivity());
                    prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    int preselect = 0;
                    if (prefs.getBoolean("true_black", false)) {
                        preselect = 2;
                    } else if (prefs.getBoolean("dark_mode", false)) {
                        preselect = 1;
                    }

                    new MaterialDialog.Builder(getActivity())
                            .title(R.string.base_theme)
                            .items(R.array.base_themes)
                            .theme(dark ? Theme.DARK : Theme.LIGHT)
                            .itemsCallbackSingleChoice(preselect, new MaterialDialog.ListCallback() {
                                @SuppressLint("CommitPrefEdits")
                                @Override
                                public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                                    if (getActivity() == null) return;
                                    SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                                    switch (i) {
                                        default:
                                            prefs.remove("dark_mode").remove("true_black");
                                            break;
                                        case 1:
                                            prefs.remove("true_black")
                                                    .putBoolean("dark_mode", true);
                                            break;
                                        case 2:
                                            prefs.putBoolean("dark_mode", true)
                                                    .putBoolean("true_black", true);
                                            break;
                                    }
                                    prefs.commit();
                                //    ImageLoader.getInstance().clearMemoryCache();
                                    getActivity().recreate();
                                }
                            }).show();
                    return false;
                }
            });



            findPreference("skin").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    final int current = Integer.parseInt(prefs.getString("skin", "" + 6));

                    //    List<String> stations = new ArrayList<String>();
                    List<String> stations = new ArrayList<String>();


                    CharSequence[] sa1 = getActivity().getResources().getTextArray(R.array.skin);

                    for (int i = 0; i < sa1.length; i++) //clean previous selected
                    {
                        stations.add(sa1[i].toString());

                    }

                    final String[] colors = new String[]{
                            "#F44336",
                            "#e91e63",
                            "#9c27b0",
                            "#673ab7",
                            "#3f51b5",
                            "#2196F3",
                            "#03A9F4",
                            "#00BCD4",
                            "#009688",
                            "#4CAF50",
                            "#8bc34a",
                            "#FFC107",
                            "#FF9800",
                            "#FF5722",
                            "#795548",
                            "#212121",
                            "#607d8b",
                            "#004d40"
                    };




                    new AlertDialog.Builder(getActivity())
                            .setTitle(R.string.skin)
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                        /*    .setPositiveButton(R.string.randomDialog, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    //sharedPref.edit().putString("skin", "" + i).commit();
                                    dialogInterface.cancel();

                                    // Random
                                    Random random = new Random();
                                    int pos = random.nextInt(colors.length - 1);
                                    prefs.edit().putString("skin_color", colors[pos]).commit();
                                }
                            })*/

                                    //.setAdapter()
                            .setSingleChoiceItems(R.array.skin, current, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    prefs.edit().putString("skin", "" + i).commit();
                                    dialogInterface.cancel();
                                    prefs.edit().putString("skin_color", colors[i]).apply();
                                    String skin = prefs.getString("skin_color", "#5677fc");
                                    mToolbar.setBackgroundColor(Color.parseColor(skin));

                                }
                            }).show();
//                    getActivity().recreate();
                    return false;
                }
            });

            // Authors
            Preference preference4 = (Preference) findPreference("authors");
            preference4.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {

                    MaterialDialog.Builder a = new MaterialDialog.Builder(getActivity());
                    prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String skin = prefs.getString("skin_color", "#5677fc");
                    final boolean dark = ThemeUtils.isDarkMode(getActivity());
                    if(dark)
                        a.theme(Theme.DARK);

                    a.positiveText(R.string.close);
                    a.positiveColor(Color.parseColor(skin));
                    LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view = layoutInflater.inflate(R.layout.authors, null);
                    a.customView(view);
                    a.title(R.string.authors);
                     /*a.callback(new MaterialDialog.Callback() {
                        @Override
                        public void onPositive(MaterialDialog materialDialog) {

                            materialDialog.cancel();
                        }

                        @Override
                        public void onNegative(MaterialDialog materialDialog) {

                        }
                    });*/
                /*a.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });*/
                    a.build().show();


                    return false;
                }
            });

                   /* MaterialDialog.Builder  dialog = new MaterialDialog.Builder(getActivity());
                    final boolean dark = ThemeUtils.isDarkMode(getActivity());
                    if (dark) dialog.theme(Theme.DARK);
                    dialog.title(R.string.skin);
                        dialog.adapter(new MyAdapter(getActivity(),current, 1,stations));


                    dialog.itemsCallbackSingleChoice(current, new MaterialDialog.ListCallback() {
                        @SuppressLint("CommitPrefEdits")
                        @Override
                        public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                            if (getActivity() == null) return;
                            SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                                    Toast.makeText(getActivity(), i, Toast.LENGTH_LONG).show();

                                   /* prefs.edit().putString("skin", "" + 5).commit();
                                    prefs.edit().putString("skin_color", color).apply();*/
                         /*   prefs.commit();
                            //    ImageLoader.getInstance().clearMemoryCache();
                            getActivity().recreate();
                        }
                    });
                    dialog.build().show();*/



         };


        public void onColorSelection(String color) {
            Toast.makeText(getActivity(), color, Toast.LENGTH_LONG).show();

            // getThemeUtils().thumbnailColor(color);
//        mCallback.ColorSelectionUpdate( color);
            //recreate();
        }



    };

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_activity_custom);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setPopupTheme(getThemeUtils().getPopupTheme());

            String skin = MainActivity.Sp.getString("skin_color", "#5677fc");

           mToolbar.setBackgroundColor(Color.parseColor(skin));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction().replace(R.id.settings_content, new SettingsFragment()).commit();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            String skin = MainActivity.Sp.getString("skin_color", "#5677fc");

            MainActivity.mToolbar.setBackgroundColor(Color.parseColor(skin));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}