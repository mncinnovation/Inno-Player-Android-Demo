package id.innovationcenter.lidoplayertest

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.innovationcenter.lidoplayer.events.ErrorEvent
import id.innovationcenter.lidoplayer.events.SeekEvent
import id.innovationcenter.lidoplayer.events.listeners.VideoPlayerEvents
import id.innovationcenter.lidoplayer.repository.model.config.PlayerConfig
import id.innovationcenter.lidoplayer.repository.model.playlist.PlaylistItem
import kotlinx.android.synthetic.main.activity_video_player.lidoPlayerView

class VideoPlayerActivity : AppCompatActivity() {
    val TAG = "CLIENTAPP"
    lateinit var playerConfig: PlayerConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_video_player)

        lidoPlayerView.addOnErrorListener(object : VideoPlayerEvents.OnErrorListener {
            override fun onErrorListener(error: ErrorEvent?) {
                Log.e(TAG, "isPlayerErrorMsg: ${error?.message}")
            }
        })

        lidoPlayerView.addOnBufferChangeListener(object : VideoPlayerEvents.OnBufferChangeListener {
            override fun onBufferChange(isLoading: Boolean) {
                Log.e(TAG,"onBuffer change: $isLoading")
            }

        })

        lidoPlayerView.addOnDisplayClickListener(object : VideoPlayerEvents.OnDisplayClickListener {
            override fun onDisplayClick() {
                Log.e(TAG, "isDisplayClicked")
            }
        })

        lidoPlayerView.addOnPlayerStateEndListener(object : VideoPlayerEvents.OnPlayerStateEndListener {
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


//        btnOpenPlayer?.setOnClickListener {
//            val intent = Intent(this, LidoPlayerActivity::class.java)
//            intent.putExtra("fileStr", STREAM_URL1)
//            intent.putExtra("config", playerConfig)
//            startActivity(intent)
//        }
        initVideo()

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

        if(intent.extras != null)
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


        lidoPlayerView.setup(playerConfig, this, contentPendingIntent)
        Log.d(TAG, "INIT Video Player")
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
