package co.innoplayer.testapp

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import co.innoplayer.InnoPlayer
import co.innoplayer.configuration.PlayerConfig
import co.innoplayer.core.utils.MediaSourceUtils
import co.innoplayer.events.*
import co.innoplayer.events.listeners.VideoPlayerEvents
import co.innoplayer.ima.utils.MediaSourceAdsUtils
import co.innoplayer.media.ads.AdBreak
import co.innoplayer.media.ads.ImaAdvertising
import co.innoplayer.media.playlists.PlaylistItem
import co.innoplayer.source.Allocator
import co.innoplayer.source.LoadControl
import co.innoplayer.testapp.databinding.ActivityAudioPlayerBinding
import co.innoplayer.testapp.databinding.CastContextErrorBinding
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.dynamite.DynamiteModule

class AudioPlayerActivity : AppCompatActivity() {
    val TAG = "CLIENTAPP"
    lateinit var playerConfig: PlayerConfig
    private var castContext: CastContext? = null
    lateinit var binding: ActivityAudioPlayerBinding
    lateinit var bindingCastContextError: CastContextErrorBinding
    private var innoPlayer: InnoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioPlayerBinding.inflate(layoutInflater)
        bindingCastContextError = CastContextErrorBinding.inflate(layoutInflater)

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


        binding.apply {
            innoPlayer = this.innoPlayerView.getPlayer()

            innoPlayer?.addListener(
                EventType.LEVELS_CHANGED,
                object : EventListener.VideoPlayerEvents.OnLevelsChangedListener {
                    override fun onLevelsChanged(levelsChangedEvent: LevelsChangedEvent) {
                        Log.e(TAG, "onLevelChanged: ${levelsChangedEvent.currentQuality}")
                    }
                })
            innoPlayer?.addListener(
                EventType.ERROR,
                object : EventListener.VideoPlayerEvents.OnErrorListener {
                    override fun onError(error: ErrorEvent?) {
                        Log.e(TAG, "isPlayerErrorMsg: ${error?.message}")
                    }
                })
            innoPlayer?.addListener(
                EventType.BUFFER,
                object : EventListener.VideoPlayerEvents.OnBufferChangeListener {
                    override fun onBufferChange(isLoading: Boolean) {
                        Log.e(TAG, "isPlayerLoading: $isLoading")
                    }
                })
            innoPlayer?.addListener(
                EventType.DISPLAY_CLICK,
                object : EventListener.VideoPlayerEvents.OnDisplayClickListener {
                    override fun onDisplayClick(event: DisplayClickEvent) {
                        Log.e(TAG, "isDisplayClicked")
                    }
                })

            innoPlayer?.addListener(
                EventType.WARNING,
                object : EventListener.VideoPlayerEvents.OnWarningListener {
                    override fun onWarning(warningEvent: WarningEvent) {
                        Log.e(TAG, "isPlayerWarningMsg: ${warningEvent.message}")
                    }
                })

            innoPlayer?.addListener(
                EventType.STATE_END,
                object : EventListener.VideoPlayerEvents.OnPlayerStateEndListener {
                    override fun onPlayerStateEnd(playWhenReady: Boolean) {
                        Log.e(TAG, "playerStateEnd: $playWhenReady")
                    }
                })

            innoPlayer?.addListener(
                EventType.TRACK_CHANGED,
                object : EventListener.VideoPlayerEvents.TracksInfoChangedListener {
                    override fun onTracksInfoChanged() {
                        Log.e(TAG, "trackHasChanged")
                    }
                })
            innoPlayer?.addListener(
                EventType.SEEK,
                object : EventListener.VideoPlayerEvents.OnSeekListener {
                    override fun onSeek(seekEvent: SeekEvent) {
                        Log.e(
                            TAG,
                            "Scrub default controller start at ${seekEvent.position} to ${seekEvent.offset}"
                        )
                    }
                })
        }

        initVideo()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu_cast, menu)
        menu?.let {
            CastButtonFactory.setUpMediaRouteButton(this, it, R.id.media_route_menu_item)
        }

        return true
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

    private fun setIconController() {
        binding.innoPlayerView.setPlayIcon(
            iconColor =
            Color.parseColor("#543782")
        )
    }

    private fun initVideo() {

        val playlists = mutableListOf<PlaylistItem>()
        val ads = mutableListOf<AdBreak>()

        if (intent.extras != null) {
            playlists.addAll(
                intent.getSerializableExtra("playlistItems") as List<PlaylistItem>
            )
            (intent.getSerializableExtra("ads") as? List<AdBreak>?)?.let {
                ads.addAll(
                    it
                )
            }

        }

        val loadControl: LoadControl = object : LoadControl {
            override fun onPrepared() {}
            override fun onStopped() {}
            override fun onReleased() {}
            override fun getAllocator(): Allocator? {
                return null
            }

            override fun getBackBufferDurationUs(): Long {
                return 0
            }


            override fun retainBackBufferFromKeyframe(): Boolean {
                return false
            }

            override fun shouldContinueLoading(
                playbackPositionUs: Long,
                bufferedDurationUs: Long,
                playbackSpeed: Float
            ): Boolean {
                return false
            }

            override fun shouldStartPlayback(
                bufferedDurationUs: Long,
                playbackSpeed: Float,
                rebuffering: Boolean
            ): Boolean {
                return false
            }
        }

        binding.innoPlayerView.getInnoPlayerSettings()?.setLoadControl(loadControl)

        val playerConfig = PlayerConfig.Builder()
            .setPlaylist(playlists)
            .setStretching(PlayerConfig.STRETCHING_STRETCH)

        if (ads.size > 0)
            playerConfig.setAdvertising(ImaAdvertising(ads, null))

//        val mediaSourceUtils = MediaSourceUtils(this)
        val mediaSourceUtils = MediaSourceAdsUtils(this)

        innoPlayer?.setActivityPlayer(this)
        innoPlayer?.setup(
            playerConfig.build(),
            this,
            mediaSourceUtils
        )
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
}
