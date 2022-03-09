package co.innoplayer.testapp

//import com.google.android.gms.cast.framework.CastButtonFactory
//import com.google.android.gms.cast.framework.CastContext
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import co.innoplayer.InnoPlayer
import co.innoplayer.configuration.PlayerConfig
import co.innoplayer.core.utils.MediaSourceUtils
import co.innoplayer.events.*
import co.innoplayer.media.playlists.PlaylistItem
import co.innoplayer.testapp.databinding.ActivityAudioPlayerBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

class AudioPlayerActivity : AppCompatActivity() {
    val TAG = "CLIENTAPP"
    private lateinit var playerConfig: PlayerConfig

    lateinit var binding: ActivityAudioPlayerBinding
    private var innoPlayer: InnoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioPlayerBinding.inflate(layoutInflater)

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

        val playlists = mutableListOf<PlaylistItem>()

        if (intent.extras != null)
            playlists.addAll(
                intent.getSerializableExtra("playlistItems") as List<PlaylistItem>
            )

        playerConfig = PlayerConfig(playlists)


        val mediaSourceUtils = MediaSourceUtils(this)
        innoPlayer?.setActivityPlayer(this)
        innoPlayer?.setup(
            playerConfig,
            this,
            mediaSourceUtils
        )
        Log.d(TAG, "INIT Video Player")
    }

    override fun onStart() {
        super.onStart()
        binding.innoPlayerView.onStart()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        binding.innoPlayerView.onNewIntent()
        setIntent(intent)
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
