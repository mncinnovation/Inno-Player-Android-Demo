package co.innoplayer.testapp

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.FragmentActivity
import co.innoplayer.InnoPlayerSDK
import co.innoplayer.core.repository.model.feature.Feature
import co.innoplayer.events.InnoError

/** Loads [PlaybackVideoFragment]. */
class PlaybackActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        applicationContext.let {
            InnoPlayerSDK().init(
                applicationContext = it,
                activity = this,
                keyCheckInitialListener = object : InnoPlayerSDK.KeyCheckInitialListener {
                    override fun onFinishInnoPlayerKeyCheck(
                        feature: Feature?,
                        innoError: InnoError?
                    ) {
                        Log.e("AndroidTV", "Finish: canplaycontent is : ${feature?.canPlayContent}")
                        innoError?.let { error ->
                            Log.e("AndroidTV","Error is : ${error.message}")
                        }

                    }

                    override fun onProgressInnoPlayerKeyCheck(isShowProgress: Boolean) {

                    }

                },
                applicationName = "ExoPlayerDemo",
                innoPlayerKey = "f230a20ffa2bc9d33f18fc0444ef95106f2f903bbfaf30a45b15c60619f8d70b"
            )

            InnoPlayerSDK().initMncAnalytics(
                it,
                "Fw2EgY5ZdYS9XrBkxEJMBXm3AcjB0Lq4gZuSmZUht94wXQlM",
                null,
                "InnoPlayerDemo"
            )
        }
        Handler().postDelayed({
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, PlaybackVideoFragment())
                    .commit()
            }
        }, 5000)
    }
}