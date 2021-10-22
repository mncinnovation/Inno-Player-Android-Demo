package co.innoplayer.testapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;

public class ClientApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            MultiDex.install(this);
        } catch (RuntimeException e) {
            e.printStackTrace();
            Log.e("ClientApp", "printStacktrace: " + e.getMessage());
        }
    }
}
