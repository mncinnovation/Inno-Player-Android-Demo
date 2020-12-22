package id.innovationcenter.lidoplayertest

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class ClientApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        try {
            MultiDex.install(this)
        } catch (e : RuntimeException){
            e.printStackTrace()
            Log.e("ClientApp","printStacktrace: "+e.message)
        }
    }
    override fun onCreate() {
        super.onCreate()

    }
}