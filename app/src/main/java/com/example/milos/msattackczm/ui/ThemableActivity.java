package com.example.milos.msattackczm.ui;

import android.app.ActivityManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.example.milos.msattackczm.R;
import com.example.milos.msattackczm.utils.ThemeSingleton;
import com.example.milos.msattackczm.utils.ThemeUtils;

/**
 * @author Aidan Follestad (afollestad)
 */
public abstract class ThemableActivity extends AppCompatActivity {

    private ThemeUtils mThemeUtils;

    protected boolean hasNavDrawer() {
        return false;
    }

    public ThemeUtils getThemeUtils() {
        return mThemeUtils;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mThemeUtils = new ThemeUtils(this);
        setTheme(mThemeUtils.getCurrent(hasNavDrawer()));
        super.onCreate(savedInstanceState);

        final int accent = mThemeUtils.accentColor();
        ThemeSingleton.get().positiveColor = accent;
        ThemeSingleton.get().neutralColor = accent;
        ThemeSingleton.get().negativeColor = accent;
        ThemeSingleton.get().darkTheme = ThemeUtils.isDarkMode(this);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Sets color of entry in the system recents page
            ActivityManager.TaskDescription td = new ActivityManager.TaskDescription(
                    getString(R.string.app_name),
                    BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher),
                    mThemeUtils.primaryColor());
            setTaskDescription(td);
        }*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final int dark = getThemeUtils().primaryColorDark();
            if (hasNavDrawer()) {
                getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
            } else {
                getWindow().setStatusBarColor(dark);
            }
            if (getThemeUtils().isColoredNavBar())
                getWindow().setNavigationBarColor(dark);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mThemeUtils.isChanged(true)) {
            setTheme(mThemeUtils.getCurrent(hasNavDrawer()));
            recreate();
        }
    }
}
