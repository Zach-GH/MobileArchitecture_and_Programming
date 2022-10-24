package com.example.meisner_band_app;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionsUtil {

    public static boolean hasPermissions(final Activity activity, final String permission,
                                         int rationaleMessageId, final int requestCode) {
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showPermissionRationaleDialog(activity, rationaleMessageId, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(activity,
                                new String[] { permission }, requestCode);
                    }
                });
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[] { permission }, requestCode);
            }
            return false;
        }
        return true;
    }

    private static void showPermissionRationaleDialog(Activity activity, int messageId, DialogInterface.OnClickListener onClickListener) {

        new AlertDialog.Builder(activity)
                .setTitle(R.string.permission_needed)
                .setMessage(messageId)
                .setPositiveButton(R.string.ok, onClickListener)
                .create()
                .show();
    }
}
