package co.innoplayer.testapp

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import co.innoplayer.configuration.PlayerConfig
import co.innoplayer.core.utils.MediaSourceUtils
import co.innoplayer.events.ErrorEvent
import co.innoplayer.events.SeekEvent
import co.innoplayer.events.listeners.VideoPlayerEvents
import co.innoplayer.media.playlists.PlaylistItem
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
                override fun onDisplayClick() {
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
        }

        initVideo()

        setIconController()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu_cast, menu)
        CastButtonFactory.setUpMediaRouteButton(this, menu, R.id.media_route_menu_item)

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

        if (intent.extras != null)
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

        val mediaSourceUtils = MediaSourceUtils(this)
        binding.innoPlayerView.setup(playerConfig, this, mediaSourceUtils, contentPendingIntent)

        Log.d(TAG, "INIT Video Player")
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
