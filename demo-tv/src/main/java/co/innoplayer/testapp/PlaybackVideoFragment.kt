package co.innoplayer.testapp

import android.content.Context
import android.os.Bundle
import co.innoplayer.configuration.PlayerConfig
import co.innoplayer.ima.utils.MediaSourceAdsUtils
import co.innoplayer.leanback.InnoVideoSupportFragment
import co.innoplayer.media.playlists.PlaylistItem
import co.innoplayer.testapp.DetailsActivity.Companion.MOVIE

/** Handles video playback with media controls. */
class PlaybackVideoFragment : InnoVideoSupportFragment() {

    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val (_, title, description, _, _, videoUrl, _, mediaId, category, tracks, playlist, adSchedule, drmLicense) =
            activity?.intent?.getSerializableExtra(MOVIE) as Movie
        val isAudio = activity?.intent?.getBooleanExtra("isAudio", false) ?: false


        val playlists = mutableListOf<PlaylistItem>()
        playlists.add(
            PlaylistItem(
                mediaId = mediaId,
                category = category,
                title = title,
                description = description,
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

        setup(
            PlayerConfig(
                PlayerConfig.Builder().setPlaylist(
                    playlists
                ).setAudioPlayerOnly(isAudio)
            ), activity, MediaSourceAdsUtils(requireContext()), true
        )

    }

}