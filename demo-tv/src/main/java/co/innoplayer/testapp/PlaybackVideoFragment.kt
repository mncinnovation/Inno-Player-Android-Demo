package co.innoplayer.testapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackTransportControlGlue
import co.innoplayer.InnoPlayerView
import co.innoplayer.configuration.PlayerConfig
import co.innoplayer.core.repository.local.TAG_INNOPLAYER
import co.innoplayer.core.utils.MediaSourceUtils
import co.innoplayer.events.ErrorEvent
import co.innoplayer.events.listeners.VideoPlayerEvents
import co.innoplayer.media.playlists.PlaylistItem
import co.innoplayer.testapp.MovieList.URL_INNO

/** Handles video playback with media controls. */
class PlaybackVideoFragment : Fragment() {

    private lateinit var mTransportControlGlue: PlaybackTransportControlGlue<MediaPlayerAdapter>
    private lateinit var innoPlayerView: InnoPlayerView
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val (_, title, description, _, _, videoUrl, _, mediaId, category, tracks, playlist, adSchedule, drmLicense) =
            activity?.intent?.getSerializableExtra(DetailsActivity.MOVIE) as Movie

        val mView = inflater.inflate(R.layout.fragment_playback, container, false)

        Log.e("AndroidTv", "videoUrl: $videoUrl")
        Log.e("AndroidTv", "title: $title")

        innoPlayerView = mView.findViewById(R.id.innoPlayerView)

        val playlists = mutableListOf<PlaylistItem>()
        playlists.add(
            PlaylistItem(
                mediaId = mediaId,
                category = category,
                title = title,
                file = videoUrl,
                tracks = tracks,
                adSchedule = adSchedule,
                drmLicenses = drmLicense
            )
        )

        playlist?.let {
            playlists.clear()
            playlists.addAll(it)
        }
//        playlists.add(PlaylistItem(videoUrl))


        innoPlayerView.setup(
            PlayerConfig(
                PlayerConfig.Builder().playlist(
                    playlists
                )
            ), activity, MediaSourceUtils(mContext), true
        )

        innoPlayerView.addOnErrorListener(object : VideoPlayerEvents.OnErrorListener {
            override fun onError(error: ErrorEvent?) {
                Log.e(TAG_INNOPLAYER, "${error?.message ?: "Something wrong"}")
            }
        })
        return mView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        val glueHost = VideoSupportFragmentGlueHost(this@PlaybackVideoFragment)
//        val playerAdapter = MediaPlayerAdapter(activity)
//        playerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE)
//
//        mTransportControlGlue = PlaybackTransportControlGlue(getActivity(), playerAdapter)
//        mTransportControlGlue.host = glueHost
//        mTransportControlGlue.title = title
//        mTransportControlGlue.subtitle = description
//        mTransportControlGlue.playWhenPrepared()
//
//        playerAdapter.setDataSource(Uri.parse(videoUrl))

    }

    override fun onStop() {
        super.onStop()
        innoPlayerView.stop()
    }

    override fun onPause() {
        super.onPause()
        innoPlayerView.onPause()
//        mTransportControlGlue.pause()
    }
}