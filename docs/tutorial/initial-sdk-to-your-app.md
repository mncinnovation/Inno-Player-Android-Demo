# Initial InnoPlayer SDK on project

## Add InnoPlayer SDK Libraries

Add this in build.gradle (project).

```groovy
allprojects {
    repositories {
        maven {
            url 'https://nyoba.innoplayer.co/cdn/repo/android/'
        }
    }
}
```

And than open build.gradle (app). Add some innoplayer library based on your needed. 
To play basic functionality please add following library below.
```groovy
dependencies {
    implementation "co.innoplayer:innoplayer-core:0.3.0"
    implementation "co.innoplayer:innoplayer-common:0.3.0"
}
```

To make player support ads, after add basic functionality please add innoplayer-ima library.
```groovy
dependencies {
    implementation "co.innoplayer:innoplayer-core:0.3.0"
    implementation "co.innoplayer:innoplayer-common:0.3.0"
    implementation "co.innoplayer:innoplayer-ima:0.3.0"
}
```

After all of that, sync your project.

## Initial InnoPlayer SDK

In here, InnoPlayer can collect data of your app.
InnoPlayer need to know and check, your application name, your InnoPlayer license key, and others value at init parameter.
You need to call this function only one time and before LidoPlayerView called. Call in ``splash screen``, or your ``Main activity`` for example.

Add ``LidoPlayerSDK().init(aplicationContext, activity, applicationName, lidoPlayerKey, keyCheckInitialListener)`` to activity class like below.

```kotlin
class MainActivity : AppCompatActivity(), LidoPlayerSDK.KeyCheckInitialListener {
    val TAG = "CLIENTAPP"
    lateinit var playerConfig: PlayerConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ...
        LidoPlayerSDK().init(
            applicationContext, this,
            "LidoPlayerDemo", "f230a20ffa2bc9d33f18fc0444ef95106f2f903....",
            this
        )

        setContentView(R.layout.activity_main)
    }
    ...
}
```

After that you need to implement KeyCheckInitialListener, there is 2 function members in KeyCheckInitialListener.

* ``onProgressLidoPlayerKeyCheck(isShowProgress: Boolean)`` : function to check progress of key check
* ``onSuccessLidoPlayerKeyCheck(feature: Feature?)`` : function that have value of feature that can inform you, feature that can supported by InnoPlayer based on your key.

Full Example

```kotlin
class MainActivity : AppCompatActivity(), LidoPlayerSDK.KeyCheckInitialListener {
    val TAG = "CLIENTAPP"
    lateinit var playerConfig: PlayerConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ...
        LidoPlayerSDK().init(
            applicationContext, this,
            "LidoPlayerDemo", "f230a20ffa2bc9d33f18fc0444ef95106f2f903bbfaf30a45b15c60619f8d70b",
            this
        )

        setContentView(R.layout.activity_main)
    }
    ...

    override fun onProgressLidoPlayerKeyCheck(isShowProgress: Boolean) {
        Log.d(TAG, "progress checking key : $isShowProgress")
    }

    override fun onSuccessLidoPlayerKeyCheck(feature: Feature?) {
        Log.d(TAG, "feature this key : $feature")
        val showAds = feature?.canShowAds ?: false
        val playDrmContent = feature?.canPlayDRMContent ?: false
        Log.d(TAG, "canShowAds : $showAds")
        Log.d(TAG, "canPlayDRMContent : $playDrmContent")

    }
}
```
