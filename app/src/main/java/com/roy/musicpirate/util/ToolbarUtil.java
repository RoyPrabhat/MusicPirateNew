package com.roy.musicpirate.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.roy.musicpirate.R;

/**
 * @author ROY
 */
public class ToolbarUtil {

    public static void buildToolbar(Toolbar toolbar, String title, AppCompatActivity activity) {

        if (toolbar != null) {
            activity.setSupportActionBar(toolbar);

            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle(title);
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                toolbar.setElevation(activity.getResources().getDimension(R.dimen.toolbar_elevation));
            }
        }
    }
}