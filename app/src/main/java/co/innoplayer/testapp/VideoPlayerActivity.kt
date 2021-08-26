package co.innoplayer.testapp

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import co.innoplayer.analytics.AnalyticsListener
import co.innoplayer.configuration.PlayerConfig
import co.innoplayer.events.DisplayClickEvent
import co.innoplayer.events.ErrorEvent
import co.innoplayer.events.SeekEvent
import co.innoplayer.events.listeners.VideoPlayerEvents
import co.innoplayer.ima.utils.MediaSourceAdsUtils
import co.innoplayer.media.playlists.PlaylistItem
import co.innoplayer.testapp.databinding.ActivityVideoPlayerBinding
import co.innoplayer.testapp.databinding.CastContextErrorBinding
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.dynamite.DynamiteModule
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

class VideoPlayerActivity : AppCompatActivity() {
    val TAG = "CLIENTAPP"
    lateinit var playerConfig: PlayerConfig
    private var castContext: CastContext? = null
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    lateinit var binding: ActivityVideoPlayerBinding
    lateinit var bindingCastContextError: CastContextErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        bindingCastContextError = CastContextErrorBinding.inflate(layoutInflater)

        firebaseAnalytics = Firebase.analytics
        if (isGoogleApiAvailable(this)) {
            try {
                castContext = CastContext.getSharedInstance(this)
            } catch (e: RuntimeException) {
                var cause = e.cause
                while (cause != null) {
                    if (cause is DynamiteModule.LoadingException) {
                        setContentView(bindingCastContextError.root)
                        return
                    }
                    cause = cause.cause
                }
                e.printStackTrace()
                throw e
            }
        }
        setContentView(binding.root)

        with(binding) {
            innoPlayerView.addOnErrorListener(object : VideoPlayerEvents.OnErrorListener {
                override fun onError(error: ErrorEvent?) {
                    Log.e(TAG, "isPlayerErrorMsg: ${error?.message}")
                }
            })

            innoPlayerView.addOnBufferChangeListener(object :
                VideoPlayerEvents.OnBufferChangeListener {
                override fun onBufferChange(isLoading: Boolean) {
                    Log.e(TAG, "onBuffer change: $isLoading")
                }

            })

            innoPlayerView.addOnDisplayClickListener(object :
                VideoPlayerEvents.OnDisplayClickListener {
                override fun onDisplayClick(event: DisplayClickEvent) {
                    Log.e(TAG, "isDisplayClicked")
                }
            })

            innoPlayerView.addOnPlayerStateEndListener(object :
                VideoPlayerEvents.OnPlayerStateEndListener {
                override fun onPlayerStateEnd(playWhenReady: Boolean) {
                    Log.e(TAG, "playerStateEnd: $playWhenReady")
                }
            })

            innoPlayerView.addOnTracksChangeListener(object :
                VideoPlayerEvents.TracksChangeListener {
                override fun onTracksChange() {
                    //add logic to do when tracks media has changed
                    //example for check player has next media to play or not by calling playerHasNext() function
                    Log.e(TAG, "trackHasChanged")
                }
            })

            innoPlayerView.addOnSeekListener(object : VideoPlayerEvents.OnSeekListener {
                override fun onSeek(seekEvent: SeekEvent) {
                    Log.e(TAG, "Scrub controller at ${seekEvent.position}")
                }
            })

            innoPlayerView.setAnalyticsListener(object : AnalyticsListener {
                override fun onAudioUnderrun(
                    eventTime: AnalyticsListener.EventTime,
                    bufferSize: Int,
                    bufferSizeMs: Long,
                    elapsedSinceLastFeedMs: Long
                ) {
                    super.onAudioUnderrun(
                        eventTime,
                        bufferSize,
                        bufferSizeMs,
                        elapsedSinceLastFeedMs
                    )
                    Log.e(TAG, "currenttime: ${eventTime.currentPlaybackPositionMs}")
                }

                override fun onBandwidthEstimate(
                    eventTime: AnalyticsListener.EventTime,
                    totalLoadTimeMs: Int,
                    totalBytesLoaded: Long,
                    bitrateEstimate: Long
                ) {
                    super.onBandwidthEstimate(
                        eventTime,
                        totalLoadTimeMs,
                        totalBytesLoaded,
                        bitrateEstimate
                    )
                    Log.e(TAG, "currenttime: ${eventTime.currentPlaybackPositionMs}")
                }

                override fun onSeekStarted(eventTime: AnalyticsListener.EventTime) {
                    super.onSeekStarted(eventTime)
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                        param(FirebaseAnalytics.Param.ITEM_ID, eventTime.windowIndex.toLong())
                        param(FirebaseAnalytics.Param.ITEM_NAME, "SeekStarted")
                        param(FirebaseAnalytics.Param.CONTENT_TYPE, "PlayerControl")
                        param(FirebaseAnalytics.Param.VALUE, eventTime.currentPlaybackPositionMs)
                    }
                    Log.e(TAG, "currenttime: ${eventTime.currentPlaybackPositionMs}")
                }

                override fun onTracksChanged(
                    eventTime: AnalyticsListener.EventTime,
                    trackGroups: co.innoplayer.source.TrackGroupArray
                ) {
                    super.onTracksChanged(eventTime, trackGroups)
                    Log.e(TAG, "currenttime: ${eventTime.currentPlaybackPositionMs}")
                }

                override fun onShuffleModeChanged(
                    eventTime: AnalyticsListener.EventTime,
                    shuffleModeEnabled: Boolean
                ) {
                    super.onShuffleModeChanged(eventTime, shuffleModeEnabled)
                    Log.e(TAG, "currenttime: ${eventTime.currentPlaybackPositionMs}")
                }

                override fun onAudioAttributesChanged(
                    eventTime: AnalyticsListener.EventTime,
                    audioAttributes: co.innoplayer.audio.AudioAttributes
                ) {
                    super.onAudioAttributesChanged(eventTime, audioAttributes)
                    Log.e(TAG, "currenttime: ${eventTime.currentPlaybackPositionMs}")
                }
            })
        }


        initVideo()
    }

    private fun setIconController() {
        binding.innoPlayerView.setPlayIcon(
            iconColor =
            Color.parseColor("#543782")
        )
    }

    private fun initVideo() {

        val playlists = mutableListOf<PlaylistItem>()

        if (intent.extras != null)
            playlists.addAll(
                intent.getSerializableExtra("playlistItems") as List<PlaylistItem>
            )

        playerConfig = PlayerConfig(playlists)

//        val mediaSourceUtils = MediaSourceUtils(this)
        val mediaSourceUtils = MediaSourceAdsUtils(this)
        binding.innoPlayerView.setup(
            playerConfig,
            this,
            mediaSourceUtils,
            true,
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu_cast, menu)
        CastButtonFactory.setUpMediaRouteButton(this, menu, R.id.media_route_menu_item)

        return true
    }

    override fun onResume() {
        super.onResume()
        binding.innoPlayerView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.innoPlayerView.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        binding.innoPlayerView.onPause()
    }

    override fun onBackPressed() {
        if (!binding.innoPlayerView.onBackPressedIsExitFullscreen())
            super.onBackPressed()
    }

    // Without the Google API's Chromecast won't work
    private fun isGoogleApiAvailable(context: Context): Boolean {
        val isOldPlayStoreInstalled: Boolean =
            doesPackageExist("com.google.market")
        val isNewPlayStoreInstalled: Boolean =
            doesPackageExist("com.android.vending")
        val isPlaystoreInstalled =
            isNewPlayStoreInstalled || isOldPlayStoreInstalled
        val isGoogleApiAvailable = GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS
        return isPlaystoreInstalled && isGoogleApiAvailable
    }

    private fun doesPackageExist(targetPackage: String): Boolean {
        try {
            packageManager.getPackageInfo(targetPackage, PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
        return true
    }
}
