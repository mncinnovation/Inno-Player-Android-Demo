package co.innoplayer.testapp

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import co.innoplayer.InnoPlayer
import co.innoplayer.analytics.AnalyticsListener
import co.innoplayer.configuration.PlayerConfig
import co.innoplayer.core.component.TimeBar
import co.innoplayer.core.utils.TimeUtils
import co.innoplayer.events.*
import co.innoplayer.ima.utils.MediaSourceAdsUtils
import co.innoplayer.media.ads.AdBreak
import co.innoplayer.media.ads.ImaAdvertising
import co.innoplayer.media.captions.Caption
import co.innoplayer.media.captions.CaptionType
import co.innoplayer.media.captions.MimeTypeSubtitle
import co.innoplayer.media.playlists.PlaylistItem
import co.innoplayer.source.Allocator
import co.innoplayer.source.LoadControl
import co.innoplayer.testapp.databinding.ActivityVideoPlayerBinding
import co.innoplayer.testapp.databinding.ClientinnoplayercustomControlBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability


class VideoPlayerActivity : AppCompatActivity() {
    val TAG = "CLIENTAPP"
    private var handlers: Handler? = null

    private lateinit var binding: ActivityVideoPlayerBinding
    lateinit var bindingCustomController: ClientinnoplayercustomControlBinding
    private var innoPlayer: InnoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        bindingCustomController = ClientinnoplayercustomControlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.innoPlayerView.setClientController(bindingCustomController.root)

        initPlayer()

        initVideo()


    }

    private fun initPlayer() {
        binding.apply {
            innoPlayer = this.innoPlayerView.getPlayer()
        }
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

    private fun setProgress() {
        handlers = Handler(Looper.getMainLooper())
        //Make sure you update Seekbar on UI thread
        handlers?.post(object : Runnable {
            override fun run() {
                bindingCustomController.apply {
                    innoPlayerProgress.setPosition((innoPlayer?.getPosition() ?: 0.0).toLong())
                    innoPlayerProgress.setBufferedPosition(
                        innoPlayer?.getCurrentBufferedPosition() ?: 0L
                    )
                    innoPlayerProgress.setDuration((innoPlayer?.getDuration() ?: 0.0).toLong())

                    tvPlayerCurrenttime.text =
                        TimeUtils.stringForTime((innoPlayer?.getPosition() ?: 0.0).toInt())
                    tvPlayerEndtime.text =
                        TimeUtils.stringForTime((innoPlayer?.getDuration() ?: 0.0).toInt())

                }
                handlers?.postDelayed(this, 1000)
            }
        })
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

        initViewController()
        setProgress()

        handleListener()

        Log.d(TAG, "INIT Video Player")
    }

    private fun handleListener() {
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
//                    innoPlayer?.setFullscreen(state = true, allowRotation = true)
                    Log.e(TAG, "onSetup")

                }
            })

        innoPlayer?.addListener(
            EventType.FULLSCREEN,
            object : EventListener.VideoPlayerEvents.OnFullscreenListener {
                override fun onFullscreen(isFullscreen: Boolean) {
                    Log.e(TAG, "isFullscreenMode: $isFullscreen")
                    if (isFullscreen) {
                        bindingCustomController.fullScreenEnterExit.setImageDrawable(
                            ContextCompat.getDrawable(
                                this@VideoPlayerActivity,
                                R.drawable.button_fullscreen_exit
                            )
                        )
                        supportActionBar?.hide()
                    } else {
                        bindingCustomController.fullScreenEnterExit.setImageDrawable(
                            ContextCompat.getDrawable(
                                this@VideoPlayerActivity,
                                R.drawable.button_fullscreen
                            )
                        )
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
                    bindingCustomController.apply {
                        exoPauseCenterAction.visibility = View.GONE
                        exoPlayCenteraction.visibility = View.VISIBLE
                    }
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
                    Log.e(TAG, "hasPrev: " + binding.innoPlayerView.playerHasPrevious())
                    Log.e(TAG, "hasNext: " + binding.innoPlayerView.isPlayerHasNext())
                    bindingCustomController.apply {
                        innoNextPlayer.visibility = if (binding.innoPlayerView.isPlayerHasNext()) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }

                        innoPrevPlayer.visibility =
                            if (binding.innoPlayerView.playerHasPrevious()) {
                                View.VISIBLE
                            } else {
                                View.GONE
                            }
                    }

                    binding.textViewTitle.text = innoPlayer?.getPlaylistItem()?.title
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
                    bindingCustomController.apply {
                        exoPauseCenterAction.visibility = View.VISIBLE
                        exoPlayCenteraction.visibility = View.GONE
                    }
                }
            })

        innoPlayer?.addListener(
            EventType.PAUSE,
            object : EventListener.VideoPlayerEvents.OnPauseListener {
                override fun onPause(pauseEvent: PauseEvent) {
                    Log.e("VideoPlayer", "state: " + pauseEvent.oldState.state.toString())
                    bindingCustomController.exoPauseCenterAction.visibility = View.GONE
                    bindingCustomController.exoPlayCenteraction.visibility = View.VISIBLE
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

    private fun initViewController() {
        with(bindingCustomController) {
            imgSettings.setOnClickListener {
                innoPlayer?.setShowPanelSetting(true)
            }

            fullScreenEnterExit.setOnClickListener {
                val isFullscreen = innoPlayer?.getFullscreen() ?: false
                innoPlayer?.setFullscreen(!isFullscreen, true)
            }
            exoPauseCenterAction.setOnClickListener {
                innoPlayer?.pause()
            }
            exoPlayCenteraction.setOnClickListener {
                innoPlayer?.play()
            }

            imgBwds.setOnClickListener {
                binding.innoPlayerView.backwardSeek()
            }

            imgfwd.setOnClickListener {
                binding.innoPlayerView.forwardSeek()
            }

            innoPlayerProgress.addListener(object : TimeBar.OnScrubListener {
                override fun onScrubStart(timeBar: TimeBar?, position: Long) {
                    innoPlayer?.pause()
                }

                override fun onScrubMove(timeBar: TimeBar?, position: Long) {

                }

                override fun onScrubStop(timeBar: TimeBar?, position: Long, canceled: Boolean) {
                    innoPlayer?.seek(position.toDouble())
                    innoPlayer?.play()
                }
            })
        }
//        binding.innoPlayerView.setShowControllerIcons(false)
//        binding.innoPlayerView.setShowControllerSeekbar(false)

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
