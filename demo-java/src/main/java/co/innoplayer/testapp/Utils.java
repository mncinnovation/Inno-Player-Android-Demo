package co.innoplayer.testapp;

import android.content.Context;
import android.content.pm.PackageManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class Utils {
     static boolean isGoogleApiAvailable(Context context) {
         boolean isOldPlayStoreInstalled = doesPackageExist(context, "com.google.market");
         boolean isNewPlayStoreInstalled = doesPackageExist(context, "com.google.vending");
         boolean isPlayStoreInstalled = isOldPlayStoreInstalled || isNewPlayStoreInstalled;
         boolean isGoogleApiAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS;

        return isPlayStoreInstalled && isGoogleApiAvailable;
    }

    static boolean doesPackageExist(Context context, String targetPackage) {
        try {
           context.getPackageManager().getPackageInfo(targetPackage, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }
}
