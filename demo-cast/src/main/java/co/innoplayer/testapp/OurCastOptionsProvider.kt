package co.innoplayer.testapp

import android.content.Context
import co.innoplayer.chromecast.CastOptionsProvider
import com.google.android.gms.cast.framework.CastOptions
import com.google.android.gms.cast.framework.media.CastMediaOptions
import com.google.android.gms.cast.framework.media.NotificationOptions

class OurCastOptionsProvider : CastOptionsProvider() {
    override fun getCastOptions(context: Context): CastOptions {
        val castOptions = super.getCastOptions(context)
        val newCastOptions = CastOptions.Builder()
        newCastOptions.setReceiverApplicationId(castOptions.receiverApplicationId)

        val notificationOptions = NotificationOptions.Builder()
            .setTargetActivityClassName(ExpandedCastControlerActivity::class.java.name)
            .build()

        val mediaOptions = CastMediaOptions.Builder()
            .setNotificationOptions(notificationOptions)
            .setExpandedControllerActivityClassName(ExpandedCastControlerActivity::class.java.name)
            .build()
        newCastOptions.setCastMediaOptions(mediaOptions)

        return newCastOptions.build()
    }
}