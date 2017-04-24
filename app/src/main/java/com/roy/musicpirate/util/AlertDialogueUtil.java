package com.roy.musicpirate.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.roy.musicpirate.R;

/**
 * @author ROY
 */
public class AlertDialogueUtil {
    public static void showWelcomeMessage(Context context){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.AppTheme_AlertDialog);
        alertDialogBuilder.setMessage(context.getString(R.string.welcome_message));
        alertDialogBuilder.setTitle(context.getString(R.string.welcome));
        alertDialogBuilder.setPositiveButton(R.string.alert_dialogue_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        alertDialogBuilder.show();
    }
}
