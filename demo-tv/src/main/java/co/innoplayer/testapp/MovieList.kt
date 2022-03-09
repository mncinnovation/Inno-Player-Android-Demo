package co.innoplayer.testapp

import co.innoplayer.media.ads.AdBreak
import co.innoplayer.media.ads.AdSource
import co.innoplayer.media.ads.AdType
import co.innoplayer.media.ads.AdTypeSource
import co.innoplayer.media.captions.Caption
import co.innoplayer.media.captions.CaptionType
import co.innoplayer.media.captions.MimeTypeSubtitle
import co.innoplayer.media.drm.DrmLicense
import co.innoplayer.media.playlists.PlaylistItem

object MovieList {
    val URL_INNO = "https://innoplayer.co/"

    val MOVIE_CATEGORY = arrayOf(
        "Basic Streaming",
        "Live Streaming",
        "Audio",
        "Playlist",
        "Advertising - Google IMA",
        "DRM",
        "Subtitles Embedded in Stream",
        "Subtitles Out of Stream"
    )

    val listBasicStream: List<Movie> by lazy {
        setupMoviesOfIndex()
    }
    val listLiveStream: List<Movie> by lazy {
        setupMoviesOfIndex(1)
    }
    val listAudio: List<Movie> by lazy {
        setupMoviesOfIndex(2)
    }
    val listPlaylist: List<Movie> by lazy {
        setupMoviesOfIndex(3)
    }
    val listAdSchedule: List<Movie> by lazy {
        setupMoviesOfIndex(4)
    }
    val listDRM: List<Movie> by lazy {
        setupMoviesOfIndex(5)
    }
    val listSubtitleInStream: List<Movie> by lazy {
        setupMoviesOfIndex(6)
    }
    val listSubtitleOutStream: List<Movie> by lazy {
        setupMoviesOfIndex(7)
    }

    private var count: Long = 0

    private fun setupMoviesOfIndex(index: Int = 0): List<Movie> {
        val studio = arrayOf(
            "Studio Zero",
            "Studio One",
            "Studio Two",
            "Studio Three",
            "Studio Four",
            "Studio Five"
        )
        val bgImageUrl = arrayOf(
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/bg.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/bg.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/bg.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/bg.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/bg.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/bg.jpg"
        )
        val cardImageUrl = arrayOf(
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/card.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/card.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/card.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/card.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/card.jpg"
        )
        val category = "Basic"

        val mediaIds = arrayOf(
            "BasicHLSVideoDemo",
            "BasicMPEG-DASHVideoDemo",
            "BasicHLSCMAFVideoDemo",
            "BasicMPEG-DASHCMAFVideoDemo",
            "BasicPreviewThumbnailsVideoDemo"
        )
        val description = "Fusce id nisi turpis. Praesent viverra bibendum semper. " +
                "Donec tristique, orci sed semper lacinia, quam erat rhoncus massa, non congue tellus est " +
                "quis tellus. Sed mollis orci venenatis quam scelerisque accumsan. Curabitur a massa sit " +
                "amet mi accumsan mollis sed et magna. Vivamus sed aliquam risus. Nulla eget dolor in elit " +
                "facilisis mattis. Ut aliquet luctus lacus. Phasellus nec commodo erat. Praesent tempus id " +
                "lectus ac scelerisque. Maecenas pretium cursus lectus id volutpat."

        var listTracks: List<Caption>? = null
        val thumbnailTrack = mutableListOf<Caption>()
        thumbnailTrack.clear()
        thumbnailTrack.add(
            Caption(
                "https://bitdash-a.akamaihd.net/content/MI201109210084_1/thumbnails/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.jpg",
                CaptionType.THUMBNAILS
            )
        )

        if (index == 0 || index == 3) {
            listTracks = thumbnailTrack
        }

        val title = when (index) {
            1 -> arrayOf(
                "HLS",
                "DASH"
            )
            2 -> arrayOf(
                "MP3",
                "AAC",
                "OGG Vorbis",
                "Opus",
                "Internet Radio\nBBC Media"
            )
            3 -> arrayOf(
                "MPEG-DASH, Preview Thumbnails"
            )
            4 -> arrayOf(
                "Linear Preload Ad Skipable",
                "Linear Preload Ad",
                "Non Linear Ad",
                "Scheduled linear preroll, non-linear midroll, linear postroll",
                "VMAP linear preroll, non-linear midroll, linear postroll",
                "VPAID"
            )
            5 -> arrayOf(
                "Playready"
            )
            6 -> arrayOf(
                "CEA-608 - HLS",
                "CEA-608 - MPEG-DASH",
                "TTML",
                "WebVTT-HLS",
                "WebVTT-DASH"
            )
            7 -> arrayOf(
                "TTML",
                "SRT",
                "WebVTT"
            )
            else -> arrayOf(
                "HLS",
                "MPEG-DASH",
                "HLS (CMAF)",
                "MPEG-DASH (CMAF)",
                "Preview Thumbnails"
            )
        }

        val videoUrl = when (index) {
            1 -> arrayOf(
                "https://tv.balkanweb.com/news24/livestream/playlist.m3u8",
                "https://livesim.dashif.org/livesim/ato_10/testpic_2s/Manifest.mpd"
            )
            2 -> arrayOf(
                "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
                "${URL_INNO}cdn/audios/ES_Cocoona.aac",
                "${URL_INNO}cdn/audios/ES_Cocoona.ogg",
                "${URL_INNO}cdn/audios/ES_Cocoona.opus",
                "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1_mf_q"
            )
            3 -> arrayOf(
//                "${URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd",
                "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.mpd",
                "https://bitdash-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd"
            )
            4 -> arrayOf(
                "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv"
            )
            5 -> arrayOf(
//                "https://storage.googleapis.com/shaka-demo-assets/sintel-widevine/dash.mpd"
                "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel-dash-playready.ism/.mpd"
//                "https://delta19tatasky.akamaized.net/out/vod/v1/PKTS2020032310085103_smil/i/25.mpd"
            )
            6 -> arrayOf(
                "https://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/bipbop_16x9_variant.m3u8",
                "https://vm2.dashif.org/dash/vod/testpic_2s/cea608.mpd",
                "https://irtdashreference-i.akamaihd.net/dash/live/901161/bfs/manifestARD.mpd",
                "https://bitmovin-a.akamaihd.net/content/sintel/hls/playlist.m3u8",
                "https://bitmovin-a.akamaihd.net/content/sintel/sintel.mpd"
            )
            7 -> arrayOf(
//                "https://demo.unified-streaming.com/video/tears-of-steel/tears-of-steel.ism/.mpd",
//                "https://demo.unified-streaming.com/video/tears-of-steel/tears-of-steel.ism/.mpd",
//                "https://demo.unified-streaming.com/video/tears-of-steel/tears-of-steel.ism/.mpd"
                "https://media.xiph.org/tearsofsteel/tears_of_steel_1080p.webm",
                "https://media.xiph.org/tearsofsteel/tears_of_steel_1080p.webm",
                "https://media.xiph.org/tearsofsteel/tears_of_steel_1080p.webm"
//                "${URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd",
//                "${URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd",
//                "${URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd"
            )
            else -> arrayOf(
//                "${URL_INNO}cdn/videos/la_chute_d_une_plume/index.m3u8",
//                "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8",
                "https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8",
                "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.mpd",
                "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8",
                "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8",
                "https://bitdash-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd"
            )
        }
        val drmLicenseList = mutableListOf<DrmLicense>()//
        drmLicenseList.add(
            DrmLicense(
                "playready",
//                "https://cwip-shaka-proxy.appspot.com/no_auth"
                "https://test.playready.microsoft.com/service/rightsmanager.asmx?PlayRight=1&UseSimpleNonPersistentLicense=1"
//                "https://test.playready.microsoft.com/directtaps/svc/pr30/rightsmanager.asmx?UseSimpleNonPersistentLicense=1"
//                "https://tatasky.stage.ott.irdeto.com/playready/rightsmanager.asmx?CrmId=tatasky&AccountId=tatasky&ContentId=19379_19694&SessionId=13DCDE445493A6C2&Ticket=DB63236AA01F1F78"
            )
        )

        return title.indices.map {
            buildMovieInfo(
                title[it],
                description,
                studio[it],
                videoUrl[it],
                cardImageUrl[it],
                bgImageUrl[it],
                if (index == 0) mediaIds[it] else null,
                if (index == 0) category else null,
                if (index == 7) getCaptionTracks(it) else if (it == title.size - 1) listTracks else null,
                buildPlaylistMovie(
                    index,
                    title[it],
                    if (index == 3)//playlist
                        videoUrl
                    else
                        arrayOf(videoUrl[it]),
                    if (index == 7) getCaptionTracks(it) else listTracks,
                    adBreaks = if (index == 4) getListAds(it) else null,
                    if (index == 5) drmLicenseList else null
                ),
                if (index == 4) getListAds(it) else null,
                if (index == 5) drmLicenseList else null
            )
        }
    }

    private fun getCaptionTracks(index: Int): List<Caption> {
        val captionTracks = mutableListOf<Caption>()
        captionTracks.clear()
        when (index) {
            1 -> {
                captionTracks.add(
                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-en.srt",
                        file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-en.srt",
                        mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                        language = "en"
                    )
                )
                captionTracks.add(
                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-es.srt",
                        file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-es.srt",
                        mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                        language = "es"
                    )
                )
                captionTracks.add(
                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-fr-Goofy.srt",
                        file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-fr-Goofy.srt",
                        mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                        language = "fr"
                    )
                )
                captionTracks.add(
                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-de.srt",
                        file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-de.srt",
                        mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                        language = "de"
                    )
                )
                captionTracks.add(
                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-it.srt",
                        file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-it.srt",
                        mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                        language = "it"
                    )
                )
                captionTracks.add(
                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-ru.srt",
                        file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-ru.srt",
                        mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                        language = "ru"
                    )
                )
                captionTracks.add(
                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-no.srt",
                        file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-no.srt",
                        mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                        language = "no"
                    )
                )
                captionTracks.add(
                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-JP.srt",
                        file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-JP.srt",
                        mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                        language = "jp"
                    )
                )
                captionTracks.add(
                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-Indonesian.srt",
                        file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-Indonesian.srt",
                        mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                        language = "in"
                    )
                )
                captionTracks.add(
                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-Persian.srt",
                        file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-Persian.srt",
                        mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                        language = "pe"
                    )
                )
            }
            2 -> {
                captionTracks.add(
                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-fr-Goofy.vtt",
                        file = "https://tears-of-steel-subtitles.s3.amazonaws.com/tears-fr.vtt",
                        mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                        language = "fr"
                    )
                )
                captionTracks.add(
                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-en.vtt",
                        file = "https://tears-of-steel-subtitles.s3.amazonaws.com/tears-en.vtt",
                        mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                        language = "en"
                    )
                )
//                captionTracks.add(
//                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-es.vtt",
//                        mimeType = MimeTypeSubtitle.TEXT_VTT.value,
//                        language = "es"
//                    )
//                )

//                captionTracks.add(
//                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-de.vtt",
//                        mimeType = MimeTypeSubtitle.TEXT_VTT.value,
//                        language = "de"
//                    )
//                )
//                captionTracks.add(
//                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-it.vtt",
//                        mimeType = MimeTypeSubtitle.TEXT_VTT.value,
//                        language = "it"
//                    )
//                )
//                captionTracks.add(
//                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-ru.vtt",
//                        mimeType = MimeTypeSubtitle.TEXT_VTT.value,
//                        language = "ru"
//                    )
//                )
//                captionTracks.add(
//                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-no.vtt",
//                        mimeType = MimeTypeSubtitle.TEXT_VTT.value,
//                        language = "no"
//                    )
//                )
//                captionTracks.add(
//                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-JP.vtt",
//                        mimeType = MimeTypeSubtitle.TEXT_VTT.value,
//                        language = "jp"
//                    )
//                )
//                captionTracks.add(
//                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Indonesian.vtt",
//                        mimeType = MimeTypeSubtitle.TEXT_VTT.value,
//                        language = "in"
//                    )
//                )
//                captionTracks.add(
//                    Caption(
//                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Persian.vtt",
//                        mimeType = MimeTypeSubtitle.TEXT_VTT.value,
//                        language = "pe"
//                    )
//                )
            }
            else -> {
                val captionEn = Caption.Builder()
                    .setFile("${URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-en.ttml")
                    .setLanguage("en")
                    .setLabel("English")
                    .setKind(CaptionType.CAPTIONS)
                    .setMimeType(MimeTypeSubtitle.TTML.value)
                    .setDefault(true)
                    .build()
                captionTracks.add(captionEn)
                captionTracks.add(
                    Caption(
                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-es.ttml",
                        mimeType = MimeTypeSubtitle.TTML.value,
                        language = "es"
                    )
                )
                captionTracks.add(
                    Caption(
                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-fr-Goofy.ttml",
                        mimeType = MimeTypeSubtitle.TTML.value,
                        language = "fr"
                    )
                )
                captionTracks.add(
                    Caption(
                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-de.ttml",
                        mimeType = MimeTypeSubtitle.TTML.value,
                        language = "de"
                    )
                )
                captionTracks.add(
                    Caption(
                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-it.ttml",
                        mimeType = MimeTypeSubtitle.TTML.value,
                        language = "it"
                    )
                )
                captionTracks.add(
                    Caption(
                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-ru.ttml",
                        mimeType = MimeTypeSubtitle.TTML.value,
                        language = "ru"
                    )
                )
                captionTracks.add(
                    Caption(
                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-no.ttml",
                        mimeType = MimeTypeSubtitle.TTML.value,
                        language = "no"
                    )
                )
                captionTracks.add(
                    Caption(
                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-JP.ttml",
                        mimeType = MimeTypeSubtitle.TTML.value,
                        language = "jp"
                    )
                )
                captionTracks.add(
                    Caption(
                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-Indonesian.ttml",
                        mimeType = MimeTypeSubtitle.TTML.value,
                        language = "in"
                    )
                )
                captionTracks.add(
                    Caption(
                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-Persian.ttml",
                        mimeType = MimeTypeSubtitle.TTML.value,
                        language = "pe"
                    )
                )
                captionTracks.add(
                    Caption(
                        file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-CH.ttml",
                        mimeType = MimeTypeSubtitle.TTML.value,
                        language = "ch"
                    )
                )
            }
        }
        return captionTracks
    }

    private fun getListAds(index: Int): List<AdBreak> {
        val listAdSchedule = mutableListOf<AdBreak>()

        listAdSchedule.clear()
        when (index) {
            1 -> {
                listAdSchedule.add(AdBreak(tag = "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator="))
            }
            2 -> listAdSchedule.add(
                AdBreak(
                    tag = "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator=",
                    adType = AdType.NONLINEAR
                )
            )
            3 -> {
                listAdSchedule.add(
                    AdBreak(
                        "PRE",
                        AdSource.IMA,
//                "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator=",
                        "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=",
                        AdType.LINEAR
                    )
                )
                val adMid = arrayOf(
                    "20"
                )

                for (i in adMid.indices) {
                    listAdSchedule.add(
                        AdBreak(
                            adMid[i],
                            AdSource.IMA,
                            "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=",
//                    "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator=",
                            AdType.LINEAR
                        )
                    )
                }
                listAdSchedule.add(
                    AdBreak(
                        "POST",
                        AdSource.IMA,
                        "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=",
                        AdType.LINEAR
                    )
                )
            }
            4 -> listAdSchedule.add(
                AdBreak(
                    tag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/"
                            + "ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp"
                            + "&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite"
                            + "%26sample_ar%3Dpremidpost&cmsid=496&vid=short_onecue&correlator="
                )
            )
            5 -> listAdSchedule.add(AdBreak(tag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinearvpaid2js&correlator="))
            else -> {
                listAdSchedule.add(AdBreak(tag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator="))
            }
        }

        return listAdSchedule
    }

    private fun buildPlaylistMovie(
        index: Int,
        title: String,
        videoUrls: Array<String>,
        tracks: List<Caption>?,
        adBreaks: List<AdBreak>? = null,
        drmLicense: List<DrmLicense>? = null
    ): List<PlaylistItem> {
        val mutableList = mutableListOf<PlaylistItem>()

        for (i in videoUrls.indices) {
            mutableList.add(
                PlaylistItem(
                    title = title,
                    file = videoUrls[i],
                    tracks = if (index == 7 || (index == 0 && i == videoUrls.size - 1)) tracks else null,
                    adSchedule = adBreaks,
                    drmLicenses = drmLicense
                )
            )
        }
        return mutableList
    }

    private fun buildMovieInfo(
        title: String,
        description: String,
        studio: String,
        videoUrl: String,
        cardImageUrl: String,
        backgroundImageUrl: String,
        mediaId: String?,
        category: String?,
        tracks: List<Caption>? = null,
        playlist: List<PlaylistItem>? = null,
        adSchedule: List<AdBreak>? = null,
        drmLicense: List<DrmLicense>? = null
    ): Movie {
        val movie = Movie()
        movie.id = count++
        movie.title = title
        movie.description = description
        movie.studio = studio
        movie.cardImageUrl = cardImageUrl
        movie.backgroundImageUrl = backgroundImageUrl
        movie.videoUrl = videoUrl
        movie.mediaId = mediaId
        movie.category = category
        movie.tracks = tracks
        movie.playlist = playlist
        movie.adSchedule = adSchedule
        movie.drmLicense = drmLicense
        return movie
    }
}