package id.innovationcenter.lidoplayerandroidsdk

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import co.innoplayer.analytics.AnalyticsListener
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.dynamite.DynamiteModule
import co.innoplayer.configuration.PlayerConfig
import co.innoplayer.decoder.DecoderCounters
import co.innoplayer.events.ErrorEvent
import co.innoplayer.events.SeekEvent
import co.innoplayer.events.listeners.VideoPlayerEvents
import co.innoplayer.ima.utils.MediaSourceAdsUtils
import co.innoplayer.media.playlists.PlaylistItem
import kotlinx.android.synthetic.main.activity_video_player.lidoPlayerView

class VideoPlayerActivity : AppCompatActivity() {
    val TAG = "CLIENTAPP"
    lateinit var playerConfig: PlayerConfig
    private var handlers: Handler? = null
    private var castContext: CastContext? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isGoogleApiAvailable(this)) {
            try {
                castContext = CastContext.getSharedInstance(this)
            } catch (e: RuntimeException) {
                var cause = e.cause
                while (cause != null) {
                    if (cause is DynamiteModule.LoadingException) {
                        setContentView(R.layout.cast_context_error)
                        return
                    }
                    cause = cause.cause
                }
                e.printStackTrace()
                throw e
            }
        }
        setContentView(R.layout.activity_video_player)

        lidoPlayerView.addOnErrorListener(object : VideoPlayerEvents.OnErrorListener {
            override fun onError(error: ErrorEvent?) {
                Log.e(TAG, "isPlayerErrorMsg: ${error?.message}")
            }
        })

        lidoPlayerView.addOnBufferChangeListener(object : VideoPlayerEvents.OnBufferChangeListener {
            override fun onBufferChange(isLoading: Boolean) {
                Log.e(TAG, "onBuffer change: $isLoading")
            }

        })

        lidoPlayerView.addOnDisplayClickListener(object : VideoPlayerEvents.OnDisplayClickListener {
            override fun onDisplayClick() {
                Log.e(TAG, "isDisplayClicked")
            }
        })

        lidoPlayerView.addOnPlayerStateEndListener(object :
            VideoPlayerEvents.OnPlayerStateEndListener {
            override fun onPlayerStateEnd(playWhenReady: Boolean) {
                Log.e(TAG, "playerStateEnd: $playWhenReady")
            }
        })

        lidoPlayerView.addOnTracksChangeListener(object : VideoPlayerEvents.TracksChangeListener {
            override fun onTracksChange() {
                //add logic to do when tracks media has changed
                //example for check player has next media to play or not by calling playerHasNext() function
                Log.e(TAG, "trackHasChanged")
            }
        })

        lidoPlayerView.addOnSeekListener(object : VideoPlayerEvents.OnSeekListener {
            override fun onSeek(seekEvent: SeekEvent) {
                Log.e(TAG, "Scrub controller at ${seekEvent.position}")
            }
//            override fun onScrubMoveStart(position: Long) {
//                //when scrub progress of player start to move
//                Log.e(TAG, "Scrub default controller start at $position")
//            }
//
//            override fun onScrubMove(position: Long) {
//                //when scrub progress of player moving
//                Log.e(TAG, "Scrub default controller move at $position")
//            }
//
//            override fun onScrubMoveStop(position: Long) {
//                //when scrub progress of player has stop move
//                Log.e(TAG, "Scrub default controller stop move at $position")
//            }
        })


        initVideo()

        lidoPlayerView?.setAnalyticsListener(object : AnalyticsListener {
            override fun onAudioSessionId(
                eventTime: AnalyticsListener.EventTime,
                audioSessionId: Int
            ) {
                super.onAudioSessionId(eventTime, audioSessionId)
                Log.e(TAG,"currenttime: ${eventTime.currentPlaybackPositionMs}")
            }

            override fun onAudioUnderrun(
                eventTime: AnalyticsListener.EventTime,
                bufferSize: Int,
                bufferSizeMs: Long,
                elapsedSinceLastFeedMs: Long
            ) {
                super.onAudioUnderrun(eventTime, bufferSize, bufferSizeMs, elapsedSinceLastFeedMs)
                Log.e(TAG,"currenttime: ${eventTime.currentPlaybackPositionMs}")
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
                Log.e(TAG,"currenttime: ${eventTime.currentPlaybackPositionMs}")
            }

            override fun onDecoderDisabled(
                eventTime: AnalyticsListener.EventTime,
                trackType: Int,
                decoderCounters: DecoderCounters
            ) {
                super.onDecoderDisabled(eventTime, trackType, decoderCounters)
                Log.e(TAG,"currenttime: ${eventTime.currentPlaybackPositionMs}")
            }
            override fun onDecoderEnabled(
                eventTime: AnalyticsListener.EventTime,
                trackType: Int,
                decoderCounters: co.innoplayer.decoder.DecoderCounters
            ) {
                super.onDecoderEnabled(eventTime, trackType, decoderCounters)
                Log.e(TAG,"currenttime: ${eventTime.currentPlaybackPositionMs}")
            }

            override fun onSeekStarted(eventTime: AnalyticsListener.EventTime) {
                super.onSeekStarted(eventTime)
                Log.e(TAG,"currenttime: ${eventTime.currentPlaybackPositionMs}")
            }

            override fun onDecoderInitialized(
                eventTime: AnalyticsListener.EventTime,
                trackType: Int,
                decoderName: String,
                initializationDurationMs: Long
            ) {
                super.onDecoderInitialized(
                    eventTime,
                    trackType,
                    decoderName,
                    initializationDurationMs
                )
                Log.e(TAG,"currenttime: ${eventTime.currentPlaybackPositionMs}")
            }

            override fun onDecoderInputFormatChanged(
                eventTime: AnalyticsListener.EventTime,
                trackType: Int,
                format: co.innoplayer.Format
            ) {
                super.onDecoderInputFormatChanged(eventTime, trackType, format)
                Log.e(TAG,"currenttime: ${eventTime.currentPlaybackPositionMs}")
            }
            override fun onTracksChanged(
                eventTime: AnalyticsListener.EventTime,
                trackGroups: co.innoplayer.source.TrackGroupArray
            ) {
                super.onTracksChanged(eventTime, trackGroups)
                Log.e(TAG,"currenttime: ${eventTime.currentPlaybackPositionMs}")
            }
            override fun onShuffleModeChanged(
                eventTime: AnalyticsListener.EventTime,
                shuffleModeEnabled: Boolean
            ) {
                super.onShuffleModeChanged(eventTime, shuffleModeEnabled)
                Log.e(TAG,"currenttime: ${eventTime.currentPlaybackPositionMs}")
            }

            override fun onAudioAttributesChanged(
                eventTime: AnalyticsListener.EventTime,
                audioAttributes: co.innoplayer.audio.AudioAttributes
            ) {
                super.onAudioAttributesChanged(eventTime, audioAttributes)
                Log.e(TAG,"currenttime: ${eventTime.currentPlaybackPositionMs}")
            }
        })
//        setIconController()
    }

    private fun setIconController() {
        lidoPlayerView.setPlayIcon(
            iconColor =
            Color.parseColor("#543782")
        )
    }

    private fun initVideo() {
        val itemPlaylist = PlaylistItem(
            file = VIDEO_SUBTITLE_URI1,
            image = IMG1,
            subtitleUri = SUBTITLE1_uri,
            subtitleMimeType = SUBTITLE1_MIMETYPE,
            subtitleLanguage = SUBTITLE_LANG
        )
        val itemPlaylist2 = PlaylistItem(
            file = STREAM_URL3, image = null,
            adTag = ADS_SINGLE_SKIPABLE_LINEAR
        )
        val itemPlaylistAudio = PlaylistItem(
//            file = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-8.mp3",
            file = AUDIO_URL_TEST3,
            image = IMG_NOAH,
            title = "Separuh AKu",
            description = "Noah band from Bandung"
        )
        val itemPlaylist3 = PlaylistItem(file = STREAM_URL1, image = null)

        val playlists = mutableListOf<PlaylistItem>()
//        playlists.add(itemPlaylistAudio)
//        playlists.add(itemPlaylist)
//        playlists.add(itemPlaylist3)
//        playlists.add(itemPlaylist2)

        if (intent.extras != null)
            playlists.addAll(
                intent.getSerializableExtra("playlistItems") as List<PlaylistItem>
            )

        playerConfig = PlayerConfig(playlists)

        val contentPendingIntent = PendingIntent.getActivity(
            this, 0, Intent(
                this,
                VideoPlayerActivity::class.java
            ), 0
        )

        val mediaSourceUtils = MediaSourceAdsUtils(this)
        lidoPlayerView.setup(
            playerConfig,
            this,
            mediaSourceUtils,
            contentPendingIntent,
            true,
            castContext
        )
        Log.d(TAG, "INIT Video Player")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu_cast, menu)
        CastButtonFactory.setUpMediaRouteButton(this, menu, R.id.media_route_menu_item)

        return true
    }

    override fun onResume() {
        super.onResume()
//        lidoPlayerView.onResumePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        //tidak perlu ditambah jika audio player only dan ingin diputar background service
        if (!lidoPlayerView.isAudioPlayerOnly())
            lidoPlayerView.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        //tidak perlu ditambah jika audio player only dan ingin diputar background service
        if (!lidoPlayerView.isAudioPlayerOnly())
            lidoPlayerView.onPause()
    }

    override fun onBackPressed() {
        if (!lidoPlayerView.onBackPressedIsExitFullscreen())
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

    companion object {
        const val KEY_LIDO_PLAYER =
            "f230a20ffa2bc9d33f18fc0444ef95106f2f903bbfaf30a45b15c60619f8d70b"

        const val STREAM_URL1 =
            "https://cdn-stream.metube.id/videos/2020/01/28/2wdDOoaddALCkjbbD231/2wdDOoaddALCkjbbD231.m3u8"
        const val fromSampleHLS =
            "http://playertest.longtailvideo.com/adaptive/bbbfull/bbbfull.m3u8"
        const val STREAM_URL2 =
            "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_16x9/bipbop_16x9_variant.m3u8"
        const val STREAM_URL3 =
            "https://cdn-stream.metube.id/videom3u8/h8RfOmhxTyaGnZ5JZxKj/index.m3u8"
        const val TYPE_OTHER_MP4 = "http://html5videoformatconverter.com/data/images/happyfit2.mp4"
        const val TYPE_OTHER_MP42 = "https://html5demos.com/assets/dizzy.mp4"

        const val IMG1 =
            "https://suaranasional.com/wp-content/uploads/2018/07/0381123f143a69be9bda08702ff858f72017257697.jpg"
        const val IMG_NOAH = "https://img.inews.id/media/822/files/inews_new/2019/08/09/noah5.jpg"

        const val RCTI = "https://cdn-livetv1.metube.id/hls/rcti.m3u8"
        const val MNCSPORTS = "https://cdn-livetv1.metube.id/hls/mncsport.m3u8"
        const val MNCTV = "https://cdn-livetv5.metube.id/hls/mnctv.m3u8"
        const val INEWSTV = "https://cdn-livetv5.metube.id/hls/inewstv.m3u8"
        const val GLOBALTV = "https://cdn-livetv1.metube.id/hls/globaltv.m3u8"

        const val STREM_URL_LIVE2 =
            "http://cspan1-lh.akamaihd.net/i/cspan1_1@304727/index_1000_av-p.m3u8"
        const val YT_DASH_H264 =
            "https://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0"
        const val DASH_MP4_H265 =
            "https://storage.googleapis.com/wvmedia/clear/hevc/tears/tears.mpd"

        //sample for support subtitle
        const val VIDEO_SUBTITLE_URI1 = "https://html5demos.com/assets/dizzy.mp4"
        const val SUBTITLE1_uri =
            "https://storage.googleapis.com/exoplayer-test-media-1/ttml/netflix_ttml_sample.xml"
        const val SUBTITLE1_MIMETYPE = "application/ttml+xml"
        const val SUBTITLE_LANG = "en"

        const val ADS_SINGLE_INLINE_LINEAR =
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinear&correlator="
        const val ADS_SINGLE_SKIPABLE_LINEAR =
            "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator="
        const val AUDIO_URL_TEST =
            "https://github.com/nzkozar/ExoplayerExample/blob/master/sample.m4a?raw=true"

        const val AUDIO_URL_TEST3 = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
    }
}
