package id.innovationcenter.lidoplayertest

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.innovationcenter.lidoplayer.repository.model.LidoPlaybackException
import id.innovationcenter.lidoplayer.repository.model.config.PlayerConfig
import id.innovationcenter.lidoplayer.repository.model.playlist.PlaylistItem
import id.innovationcenter.lidoplayer.ui.LidoPlayerView
import kotlinx.android.synthetic.main.activity_audio_player.*

class AudioPlayerActivity : AppCompatActivity() {
    val TAG = "CLIENTAPP"
    lateinit var playerConfig: PlayerConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_audio_player)

        lidoPlayerView.addOnErrorListener(object : LidoPlayerView.ErrorEventListener {
            override fun onErrorListener(error: LidoPlaybackException?) {
                Log.e(TAG, "isPlayerErrorMsg: ${error?.message}")
            }
        })

        lidoPlayerView.addOnLoadingChangeListener(object : LidoPlayerView.LoadingEventListener {
            override fun onLoadingChange(isLoading: Boolean) {
                Log.e(TAG, "isPlayerLoading: $isLoading")
            }
        })

        lidoPlayerView.addOnDisplayClickListener(object : LidoPlayerView.DisplayClickListener {
            override fun onDisplayClick() {
                Log.e(TAG, "isDisplayClicked")
            }
        })

        lidoPlayerView.addOnPlayerStateEndListener(object : LidoPlayerView.PlayerStateEndListener {
            override fun onPlayerStateEnd(playWhenReady: Boolean) {
                Log.e(TAG, "playerStateEnd: $playWhenReady")
            }
        })

        lidoPlayerView.addOnTracksChangeListener(object : LidoPlayerView.TracksChangeListener {
            override fun onTracksChange() {
                //add logic to do when tracks media has changed
                //example for check player has next media to play or not by calling playerHasNext() function
                Log.e(TAG, "trackHasChanged")
            }
        })

        lidoPlayerView.addTimebarScrubListener(object : LidoPlayerView.TimebarScrubListener {
            override fun onScrubMoveStart(position: Long) {
                //when scrub progress of player start to move
                Log.e(TAG, "Scrub default controller start at $position")
            }

            override fun onScrubMove(position: Long) {
                //when scrub progress of player moving
                Log.e(TAG, "Scrub default controller move at $position")
            }

            override fun onScrubMoveStop(position: Long) {
                //when scrub progress of player has stop move
                Log.e(TAG, "Scrub default controller stop move at $position")
            }
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


        lidoPlayerView.config(playerConfig, this, contentPendingIntent)
        Log.d(TAG, "INIT Video Player")
    }

    override fun onResume() {
        super.onResume()
        lidoPlayerView.onResumePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        //tidak perlu ditambah jika audio player only dan ingin diputar background service
        if (!lidoPlayerView.isAudioPlayerOnly())
            lidoPlayerView.destroyPlayer()
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
