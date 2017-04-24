package com.roy.musicpirate.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * @author ROY
 */
public class PermissionUtil {

    public static boolean isReadExternalStoragePermissionPresent(Context context) {
        int permissionCheck = ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED)
            return true;
        else return false;
    }
}
