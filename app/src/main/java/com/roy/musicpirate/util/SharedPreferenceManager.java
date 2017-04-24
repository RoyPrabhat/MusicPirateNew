package com.roy.musicpirate.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author ROY
 */
public class SharedPreferenceManager {

    private static final String SHOW_WELCOME_MESSAGE = "SHOW_WELCOME_MESSAGE";
    private static final String MUSIC_PIRATE_PREFS = "MUSIC_PIRATE_PREFS";



    public static void saveWelcomeMessageShown(Context applicationContext) {
        SharedPreferences prefs = applicationContext.getSharedPreferences(MUSIC_PIRATE_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(SHOW_WELCOME_MESSAGE, true);
        editor.apply();
    }


    public static boolean haveShownWelcomeMessage(Context applicationContext) {
        SharedPreferences prefs = applicationContext.getSharedPreferences(MUSIC_PIRATE_PREFS,Context.MODE_PRIVATE);
        return prefs.getBoolean(SHOW_WELCOME_MESSAGE, false);
    }
}
