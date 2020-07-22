package id.innovationcenter.lidoplayertest

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.innovationcenter.lidoplayer.repository.model.config.PlayerConfig
import id.innovationcenter.lidoplayer.repository.model.playlist.PlaylistItem
import id.innovationcenter.lidoplayer.LidoPlayerView
import id.innovationcenter.lidoplayer.events.ErrorEvent
import id.innovationcenter.lidoplayer.events.SeekEvent
import id.innovationcenter.lidoplayer.events.listeners.VideoPlayerEvents
import kotlinx.android.synthetic.main.activity_audio_player.*

class AudioPlayerActivity : AppCompatActivity() {
    val TAG = "CLIENTAPP"
    lateinit var playerConfig: PlayerConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_audio_player)

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

        initVideo()

        setIconController()
    }

    private fun setIconController() {
        lidoPlayerView.setPlayIcon(
            iconColor =
            Color.parseColor("#543782")
        )
    }

    private fun initVideo() {

        val playlists = mutableListOf<PlaylistItem>()

        if(intent.extras != null)
            playlists.addAll(
                intent.getSerializableExtra("playlistItems") as List<PlaylistItem>
            )

        playerConfig = PlayerConfig(playlists)

        val contentPendingIntent = PendingIntent.getActivity(
            this, 0, Intent(
                this,
                AudioPlayerActivity::class.java
            ), 0
        )


        lidoPlayerView.setup(playerConfig, this, contentPendingIntent)
        Log.d(TAG, "INIT Video Player")
    }

    override fun onResume() {
        super.onResume()
        lidoPlayerView.onResume()
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
}
