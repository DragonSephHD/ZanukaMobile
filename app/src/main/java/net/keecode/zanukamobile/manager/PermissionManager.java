package net.keecode.zanukamobile.manager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 *
 * Created by mkteu on 09.06.2016.
 */
public abstract class PermissionManager {

    private PermissionManager(){}

    private static final String[] requiredPermissions = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS
    };

    public static final int REQUESTCODE = 3493;


    public static void requestPermissions(Activity activity){
        if (areAllPermissionsGranted(activity))
            return;

        ActivityCompat.requestPermissions(activity,
                requiredPermissions,
                REQUESTCODE);
    }

    public static boolean areAllPermissionsGranted(Activity activity){
        boolean permissionsGranted = true;
        for(String string : requiredPermissions){
            if(!checkForPermission(string, activity))
                permissionsGranted = false;
        }
        return permissionsGranted;
    }

    public static boolean checkForPermission(String permission, Activity activity){
        if(activity == null)
            return false;
        int permissionState = ActivityCompat.checkSelfPermission(activity, permission);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }


}
