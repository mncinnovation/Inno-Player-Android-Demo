package co.innoplayer.testapp

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import co.innoplayer.InnoPlayer
import co.innoplayer.analytics.AnalyticsListener
import co.innoplayer.configuration.PlayerConfig
import co.innoplayer.events.*
import co.innoplayer.ima.utils.MediaSourceAdsUtils
import co.innoplayer.media.ads.AdBreak
import co.innoplayer.media.ads.ImaAdvertising
import co.innoplayer.media.captions.Caption
import co.innoplayer.media.captions.CaptionType
import co.innoplayer.media.captions.MimeTypeSubtitle
import co.innoplayer.media.playlists.PlaylistItem
import co.innoplayer.player.PlaybackLocation
import co.innoplayer.source.Allocator
import co.innoplayer.source.LoadControl
import co.innoplayer.testapp.databinding.ActivityVideoPlayerBinding
import co.innoplayer.testapp.databinding.CastContextErrorBinding
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.dynamite.DynamiteModule


class VideoPlayerActivity : AppCompatActivity() {
    val TAG = "CLIENTAPP"

    private var castContext: CastContext? = null
    private lateinit var binding: ActivityVideoPlayerBinding
    private lateinit var bindingCastContextError: CastContextErrorBinding
    private var innoPlayer: InnoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        bindingCastContextError = CastContextErrorBinding.inflate(layoutInflater)

        try {
            castContext = CastContext.getSharedInstance(this)

        } catch (exc: IllegalStateException) {
            exc.printStackTrace()
            throw exc
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

        setContentView(binding.root)

        if (CastContext.getSharedInstance()?.sessionManager?.currentCastSession != null &&
            CastContext.getSharedInstance()?.sessionManager?.currentCastSession?.isConnected != false
        ) {
            binding.innoPlayerView.updatePlaybackLocation(PlaybackLocation.REMOTE)
        } else {
            binding.innoPlayerView.updatePlaybackLocation(PlaybackLocation.LOCAL)
        }

        binding.apply {
            innoPlayer = this.innoPlayerView.getPlayer()

            innoPlayer?.addListener(
                EventType.LEVELS_CHANGED,
                object : EventListener.VideoPlayerEvents.OnLevelsChangedListener {
                    override fun onLevelsChanged(levelsChangedEvent: LevelsChangedEvent) {
                        Log.e(TAG, "onLevelsChanged: ${levelsChangedEvent.currentQuality}")
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
                EventType.ERROR,
                object : EventListener.VideoPlayerEvents.OnWarningListener {
                    override fun onWarning(warningEvent: WarningEvent) {
                        Log.e(TAG, "isPlayerWarningMsg: ${warningEvent.message}")
                    }
                })

            innoPlayer?.addListener(
                EventType.SETUP,
                object : EventListener.VideoPlayerEvents.OnSetupListener {
                    override fun onSetup() {
                    }
                })

            innoPlayer?.addListener(
                EventType.FULLSCREEN,
                object : EventListener.VideoPlayerEvents.OnFullscreenListener {
                    override fun onFullscreen(isFullscreen: Boolean) {
                        Log.e(TAG, "isFullscreenMode: $isFullscreen")
                        if (isFullscreen) {
                            supportActionBar?.hide()
                        } else {
                            supportActionBar?.show()
                        }
                    }
                })

            innoPlayer?.addListener(
                EventType.ERROR,
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
                EventType.STATE_END,
                object : EventListener.VideoPlayerEvents.OnPlayerStateEndListener {
                    override fun onPlayerStateEnd(playWhenReady: Boolean) {
                        Log.e(TAG, "playerStateEnd: $playWhenReady")
                        innoPlayer?.pause()
                    }
                })

            innoPlayer?.addListener(
                EventType.COMPLETE,
                object : EventListener.VideoPlayerEvents.OnCompleteListener {
                    override fun onComplete(completeEvent: CompleteEvent) {
                        Log.e(TAG, "onComplete: $completeEvent")
                    }
                })

            innoPlayer?.addListener(
                EventType.TRACK_CHANGED,
                object : EventListener.VideoPlayerEvents.TracksInfoChangedListener {
                    override fun onTracksInfoChanged() {
                        //add logic to do when tracks media has changed
                        //example for check player has next media to play or not by calling playerHasNext() function
                        Log.e(TAG, "trackHasChanged")
                        Log.e(TAG, "hasPrev: " + innoPlayerView.playerHasPrevious())
                        Log.e(TAG, "hasNext: " + innoPlayerView.isPlayerHasNext())


                        textViewTitle.text = innoPlayer?.getPlaylistItem()?.title
                        val ads = mutableListOf<String>()
                        ads.add("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=")
                        innoPlayer?.playAd(ads)
                    }
                })

            innoPlayer?.addListener(
                EventType.SEEKED,
                object : EventListener.VideoPlayerEvents.OnSeekedListener {
                    override fun onSeeked(seekedEvent: SeekedEvent) {
                        Log.e(TAG, "onSeeked at ${seekedEvent.position}")
                    }
                })

            innoPlayer?.addListener(
                EventType.PLAY,
                object : EventListener.VideoPlayerEvents.OnPlayListener {
                    override fun onPlay(playEvent: PlayEvent) {
                        Log.e("VideoPlayer", "state: " + playEvent.oldState.state.toString())

                    }
                })

            innoPlayer?.addListener(
                EventType.PAUSE,
                object : EventListener.VideoPlayerEvents.OnPauseListener {
                    override fun onPause(pauseEvent: PauseEvent) {
                        Log.e("VideoPlayer", "state: " + pauseEvent.oldState.state.toString())

                    }
                })

            innoPlayer?.addListener(
                EventType.SEEK,
                object : EventListener.VideoPlayerEvents.OnSeekListener {
                    override fun onSeek(seekEvent: SeekEvent) {
                        Log.e(TAG, "onSeek at ${seekEvent.position} to ${seekEvent.offset}")
                    }
                })

            innoPlayer?.setAnalyticsListener(object : AnalyticsListener {


                override fun onPlayerError(
                    eventTime: AnalyticsListener.EventTime,
                    error: InnoPlaybackException
                ) {
                    Log.e(TAG, "eventTime: ${eventTime.currentPlaybackPositionMs}")
                    Log.e(TAG, "playerError: " + error.message)
                }

                override fun onVideoSizeChanged(
                    eventTime: AnalyticsListener.EventTime,
                    width: Int,
                    height: Int,
                    unappliedRotationDegrees: Int,
                    pixelWidthHeightRatio: Float
                ) {
                    Log.e(TAG, "eventTime: ${eventTime.currentPlaybackPositionMs}")
                    Log.e(TAG, "playerchangeSize: $width")
                    Log.e(TAG, "playerchangeSize: $height")
                }

                override fun onDrmKeysLoaded(eventTime: AnalyticsListener.EventTime) {
                    Log.e(TAG, "eventTime: ${eventTime.currentPlaybackPositionMs}")
                }
            })
        }

        initVideo()
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


    private fun initVideo() {
        val captionTracks = mutableListOf<Caption>()
        val captionEn = Caption.Builder()
            .setFile("https://innoplayer.co/cdn/videos/tears_of_steel/subtitle/ttml/TOS-en.ttml")
            .setLanguage("en")
            .setLabel("English")
            .setKind(CaptionType.CAPTIONS)
            .setMimeType(MimeTypeSubtitle.TTML.value)
            .setDefault(true)
            .build()
        captionTracks.add(captionEn)

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

        Log.d(TAG, "INIT Video Player")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu_cast, menu)
        menu?.let {
            CastButtonFactory.setUpMediaRouteButton(this, it, R.id.media_route_menu_item)
        }

        return true
    }

    override fun onResume() {
        super.onResume()
        binding.innoPlayerView.onResume()
    }

    override fun onDestroy() {
        binding.innoPlayerView.onDestroy()
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        binding.innoPlayerView.onPause()
    }

    override fun onStart() {
        super.onStart()
        binding.innoPlayerView.onStart()
    }


    override fun onBackPressed() {
        if (!binding.innoPlayerView.onBackPressedIsExitFullscreen()) {
            super.onBackPressed()
        }
    }
}
