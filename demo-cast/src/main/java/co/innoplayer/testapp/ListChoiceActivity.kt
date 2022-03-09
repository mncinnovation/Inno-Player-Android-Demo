package co.innoplayer.testapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.innoplayer.testapp.databinding.ActivityListChoiceBinding
import co.innoplayer.license.LicenseUtil
import co.innoplayer.media.ads.AdBreak
import co.innoplayer.media.ads.AdSource
import co.innoplayer.media.ads.AdType
import co.innoplayer.media.ads.AdTypeSource
import co.innoplayer.media.captions.Caption
import co.innoplayer.media.captions.CaptionType
import co.innoplayer.media.captions.MimeTypeSubtitle
import co.innoplayer.media.drm.DrmLicense
import co.innoplayer.media.playlists.PlaylistItem
import java.io.Serializable

class ListChoiceActivity : AppCompatActivity() {
    private val URL_INNO = "https://manggung.innoplayer.co/"

    lateinit var listAdapter: ChoiceExpandableListAdapter
    private var listHeader = mutableListOf<String>()
    private var listChild: HashMap<String, List<List<PlaylistItem>>> = HashMap()

    private lateinit var binding: ActivityListChoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LicenseUtil().setLicenseKey(this, "4d685e4411536b836994ce17cc9d0ba02b7ae990ad48ec0ca43b468761ec3888")

        prepareListData()
        listAdapter = ChoiceExpandableListAdapter(this, listHeader, listChild)

        binding.expandableListView.setAdapter(listAdapter)
        binding.expandableListView.setOnChildClickListener { _, _, p2, p3, _ ->
            val playlistItem = listChild[listHeader[p2]]?.get(p3) as List<PlaylistItem>

            var intent = Intent(this, VideoPlayerActivity::class.java)
            if (listHeader[p2].contains("Audio"))
                intent = Intent(this, AudioPlayerActivity::class.java)
            intent.putExtra("playlistItems", playlistItem as Serializable)

            startActivity(intent)

            var itemChild = ""
            for ((i, item) in playlistItem.withIndex()) {
                itemChild += item.title
                if (i != playlistItem.size - 1) {
                    itemChild += ", "
                }
            }
            Toast.makeText(
                applicationContext,
                listHeader[p2]
                        + " : "
                        + itemChild, Toast.LENGTH_SHORT
            ).show()

            true
        }

    }

    private fun prepareListData() {
        listHeader.add("Basic Streaming")

        val hls5: MutableList<PlaylistItem> = ArrayList()
        val hls1: MutableList<PlaylistItem> = ArrayList()
        val hls2: MutableList<PlaylistItem> = ArrayList()
        val hls3: MutableList<PlaylistItem> = ArrayList()
        val hls4: MutableList<PlaylistItem> = ArrayList()
        val hls6: MutableList<PlaylistItem> = ArrayList()

        val hlsPlaylistSample = mutableListOf<List<PlaylistItem>>()

        hls1.add(
            PlaylistItem(
                mediaId = "BasicHLSVideoDemo",
                category = "Basic",
                title = "HLS",
                file = "https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8"
//                file = "${URL_INNO}cdn/videos/la_chute_d_une_plume/index.m3u8"
            )
        )
        hls2.add(
            PlaylistItem(
                mediaId = "BasicMPEG-DASHVideoDemo",
                category = "Basic",
                title = "MPEG-DASH",
                file = "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.mpd"
            )
        )
        hls3.add(
            PlaylistItem(
                mediaId = "BasicHLSCMAFVideoDemo",
                category = "Basic",
                title = "HLS (CMAF)",
                file = "${URL_INNO}cdn/videos/cosmos-laundromat/cosmos_laundromat_h264_master.m3u8"
            )
        )
        hls4.add(
            PlaylistItem(
                mediaId = "BasicMPEG-DASHCMAFVideoDemo",
                category = "Basic",
                title = "MPEG-DASH (CMAF)",
                file = "${URL_INNO}cdn/videos/cosmos-laundromat/cosmos_laundromat_h264.mpd"
            )
        )
        val thumbnailTrack = mutableListOf<Caption>()
        thumbnailTrack.clear()
        thumbnailTrack.add(
            Caption(
                "https://bitdash-a.akamaihd.net/content/MI201109210084_1/thumbnails/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.jpg",
                CaptionType.THUMBNAILS
            )
        )
        hls5.add(
            PlaylistItem(
                mediaId = "BasicPreviewThumbnailsVideoDemo",
                title = "Preview Thumbnails",
                category = "Basic",
                startTime = 5,
                file = "https://bitdash-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd",
                tracks = thumbnailTrack
            )
        )

        hls6.add(
            PlaylistItem(
                mediaId = "BasicHLSAES128",
                title = "HLS AES-128",
                category = "Basic",
                file = "https://www.radiantmediaplayer.com/media/rmp-segment/bbb-abr-aes/playlist.m3u8",
            )
        )

        hlsPlaylistSample.addAll(listOf(hls1, hls2, hls3, hls4, hls5, hls6))
        listChild[listHeader[0]] = hlsPlaylistSample

        listHeader.add("Live Streaming")
        val liveStream1: MutableList<PlaylistItem> = ArrayList()
        val liveStream2: MutableList<PlaylistItem> = ArrayList()
        val liveStreamList = mutableListOf<List<PlaylistItem>>()
        liveStream1.add(
            PlaylistItem(
                title = "HLS",
                file = "https://tv.balkanweb.com/news24/livestream/playlist.m3u8"
            )
        )
        liveStream2.add(
            PlaylistItem(
                title = "DASH",
                file = "https://livesim.dashif.org/livesim/ato_10/testpic_2s/Manifest.mpd"
//                        file = "https://akamai-axtest.akamaized.net/routes/lapd-v1-acceptance/www_c4/Manifest.mpd"
            )
        )
        liveStreamList.addAll(listOf(liveStream1, liveStream2))
        listChild[listHeader[1]] = liveStreamList

        listHeader.add("Audio")
        val audio1: MutableList<PlaylistItem> = ArrayList()
        val audio2: MutableList<PlaylistItem> = ArrayList()
        val audio3: MutableList<PlaylistItem> = ArrayList()
        val audio4: MutableList<PlaylistItem> = ArrayList()
        val audio5: MutableList<PlaylistItem> = ArrayList()
        val audioList = mutableListOf<List<PlaylistItem>>()
        audio1.add(
            PlaylistItem(
                title = "MP3",
//                file = "${URL_INNO}cdn/audios/ES_Cocoona.mp3",
                file = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
                category = "audio",
                mediaId = "mp3-1",
                image = "https://upload.wikimedia.org/wikipedia/commons/5/5e/MP3_logo.png",
                description = "SoundHelix Sound"
            )
        )

        audio2.add(
            PlaylistItem(
                title = "AAC",
                file = "${URL_INNO}cdn/audios/ES_Cocoona.aac",
//                file = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
                description = "AAC Sample Sound"
            )
        )
        audio3.add(
            PlaylistItem(
                title = "OGG Vorbis",
                file = "${URL_INNO}cdn/audios/ES_Cocoona.ogg",
                description = "OGG Vorbis Sample Sound"
            )
        )
        audio4.add(
            PlaylistItem(
                title = "Opus",
                file = "${URL_INNO}cdn/audios/ES_Cocoona.opus",
                description = "Opus Sample Sound"
            )
        )
        audio5.add(
            PlaylistItem(
                title = "Internet Radio\nBBC Media",
                file = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1_mf_q",
                description = "Internet Radio"
            )
        )
        audioList.addAll(listOf(audio1, audio2, audio3, audio4, audio5))
        listChild[listHeader[2]] = audioList


        listHeader.add("Playlist")
        val playlist1: MutableList<PlaylistItem> = ArrayList()
        val playlist2: MutableList<PlaylistItem> = ArrayList()
        val playlistList = mutableListOf<List<PlaylistItem>>()
        playlist1.add(
            PlaylistItem(
                title = "Berita 1",
                file = "https://cdn-stream.metube.co/videos/2020/01/28/2wdDOoaddALCkjbbD231/2wdDOoaddALCkjbbD231.m3u8"
            )
        )
        playlist1.add(
            PlaylistItem(
                title = "Berita 2",
                file = "https://cdn-stream.metube.co/videom3u8/h8RfOmhxTyaGnZ5JZxKj/index.m3u8"
            )
        )

        val listAds1 = mutableListOf<AdBreak>()
        val adBreak = AdBreak.Builder()
            .setTag("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=")
            .setOffset("PRE")
            .setSource(AdSource.IMA)
            .setAdType(AdType.LINEAR)
            .build()
        listAds1.add(adBreak)
        val adMid1 = arrayOf(
            "20"
        )

        for (i in adMid1.indices) {
            listAds1.add(
                AdBreak(
                    adMid1[i],
                    AdSource.IMA,
                    "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=",
//                    "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator=",
                    AdType.LINEAR
                )
            )
        }
        listAds1.add(
            AdBreak(
                "POST",
                AdSource.IMA,
                "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=",
                AdType.LINEAR
            )
        )

        thumbnailTrack.clear()
        thumbnailTrack.add(
            Caption(
                "https://bitdash-a.akamaihd.net/content/MI201109210084_1/thumbnails/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.jpg",
                CaptionType.THUMBNAILS
            )
        )
        playlist2.add(
            PlaylistItem(
                title = "Preview Thumbnails",
                file = "https://bitdash-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd",
                tracks = thumbnailTrack
            )
        )
        playlist2.add(
            PlaylistItem(
                title = "MPEG-DASH",
                file = "https://media.xiph.org/tearsofsteel/tears_of_steel_1080p.webm"
//                file = "${URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd"
            )
        )
        playlistList.addAll(listOf(playlist2))
        listChild[listHeader[3]] = playlistList

        listHeader.add("Advertising - Google IMA SDK")
        val ima0: MutableList<PlaylistItem> = ArrayList()
        val ima1: MutableList<PlaylistItem> = ArrayList()
        val ima2: MutableList<PlaylistItem> = ArrayList()
        val ima3: MutableList<PlaylistItem> = ArrayList()
        val ima4: MutableList<PlaylistItem> = ArrayList()
        val ima5: MutableList<PlaylistItem> = ArrayList()
        val imaPlaylist = mutableListOf<List<PlaylistItem>>()
        val listAds = mutableListOf<AdBreak>()
        listAds.add(
            AdBreak(
                tag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator="
            )
        )
        ima0.add(
            PlaylistItem(
                mediaId = "ads-linearskipable",
                title = "Linear Preload Ad Skipable",
                category = "ads",
//                file = "${URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd",
                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                adSchedule = listAds,
                adTypeSource = AdTypeSource.VAST
            )
        )

        val listAds2 = mutableListOf<AdBreak>()
        listAds2.add(
            AdBreak(
                tag = "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator="
            )
        )
        ima1.add(
            PlaylistItem(
                mediaId = "ads-linear",
                title = "Linear Preload Ad",
                category = "ads",
//                file = "${URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd",
                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                adSchedule = listAds2,
                adTypeSource = AdTypeSource.VAST
            )
        )

        val listAds3 = mutableListOf<AdBreak>()
        listAds3.add(
            AdBreak(
                tag = "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator=",
                adType = AdType.NONLINEAR
            )
        )
        ima2.add(
            PlaylistItem(
                mediaId = "ads-nonlinear",
                title = "Non Linear Ad",
                category = "ads",
//                file = "${URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd",
                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                adSchedule = listAds3
            )
        )
        val listAds4 = mutableListOf<AdBreak>()
        listAds4.add(
            AdBreak(
                "PRE",
                AdSource.IMA,
//                "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator=",
                "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=",
                AdType.LINEAR
            )
        )
        //Mid roll ads
        listAds4.add(
            AdBreak(
                "10",
                AdSource.IMA,
                "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=",
                AdType.LINEAR
            )
        )
        listAds4.add(
            AdBreak(
                "20",
                AdSource.IMA,
                "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinear&correlator=",
                AdType.LINEAR
            )
        )
        listAds4.add(
            AdBreak(
                "30",
                AdSource.IMA,
                "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=",
                AdType.LINEAR
            )
        )
        //==end of mid roll ads
        listAds4.add(
            AdBreak(
                "POST",
                AdSource.IMA,
                "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=",
                AdType.LINEAR
            )
        )

        ima3.add(
            PlaylistItem(
                mediaId = "ads-schedule",
                title = "Scheduled linear preroll, non-linear midroll, linear postroll",
//                file = "${URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd",
                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                category = "ads",
                adSchedule = listAds4,
                adTypeSource = AdTypeSource.VMAP
            )
        )
        val listAds5 = mutableListOf<AdBreak>()

        listAds5.add(
            AdBreak(
                tag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/"
                        + "ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp"
                        + "&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite"
                        + "%26sample_ar%3Dpremidpost&cmsid=496&vid=short_onecue&correlator="
            )
        )
        ima4.add(
            PlaylistItem(
                mediaId = "ads-vmap",
                title = "VMAP linear preroll, non-linear midroll, linear postroll",
                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
//                file = "${URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd",
//                adTag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpodbumper&cmsid=496&vid=short_onecue&correlator="
                category = "ads",
                adSchedule = listAds5,
                adTypeSource = AdTypeSource.VMAP
            )
        )
        val listAds6 = mutableListOf<AdBreak>()

        listAds6.add(AdBreak(tag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinearvpaid2js&correlator="))
        ima5.add(
            PlaylistItem(
                mediaId = "ads-vpaid",
                title = "VPAID",
//                file = "${URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd",
                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                category = "ads",
                adSchedule = listAds6,
                adTypeSource = AdTypeSource.VPAID
//                adTag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinearvpaid2js&correlator="
            )
        )

        imaPlaylist.addAll(listOf(ima0, ima1, ima2, ima3, ima4, ima5))
        listChild[listHeader[4]] = imaPlaylist

        listHeader.add("DRM")
        val gts1: MutableList<PlaylistItem> = ArrayList()
        val gtsPlaylist = mutableListOf<List<PlaylistItem>>()
        val drmLicenseList = mutableListOf<DrmLicense>()//
        drmLicenseList.add(
            DrmLicense(
                "widevine",
                "https://cwip-shaka-proxy.appspot.com/no_auth"
            )
        )
        gts1.add(
            PlaylistItem(
                title = "Widevine",//https://storage.googleapis.com/wvmedia/cbc1/h264/tears/tears_aes_cbc1.mpd
//                file = "https://storage.googleapis.com/wvmedia/cbc1/h264/tears/tears_aes_cbc1.mpd",
                file = "https://storage.googleapis.com/shaka-demo-assets/sintel-widevine/dash.mpd",
                drmLicenses = drmLicenseList
            )
        )

        gtsPlaylist.addAll(listOf(gts1))
        listChild[listHeader[5]] = gtsPlaylist

        listHeader.add("Subtitles Embedded in stream")
        val subtitle1: MutableList<PlaylistItem> = ArrayList()
        val subtitle2: MutableList<PlaylistItem> = ArrayList()
        val subtitle3: MutableList<PlaylistItem> = ArrayList()
        val subtitle4: MutableList<PlaylistItem> = ArrayList()
        val subtitle5: MutableList<PlaylistItem> = ArrayList()
        val subtitleList = mutableListOf<List<PlaylistItem>>()

        subtitle1.add(
            PlaylistItem(
                title = "CEA-608 - HLS (Embedded in stream)",
                file = "https://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/bipbop_16x9_variant.m3u8"
            )
        )
        subtitle2.add(
            PlaylistItem(
                title = "CEA-608 - MPEG-DASH (Embedded in stream)",
                file = "https://vm2.dashif.org/dash/vod/testpic_2s/cea608.mpd"
            )
        )
        subtitle3.add(
            PlaylistItem(
                title = "TTML (Embedded in stream)",
                file = "https://irtdashreference-i.akamaihd.net/dash/live/901161/bfs/manifestARD.mpd"
            )
        )
        subtitle4.add(
            PlaylistItem(
                title = "WebVTT-HLS (Embedded in stream)",
                file = "https://bitmovin-a.akamaihd.net/content/sintel/hls/playlist.m3u8"
            )
        )
        subtitle5.add(
            PlaylistItem(
                title = "WebVTT-DASH (Embedded in stream)",
                file = "https://bitmovin-a.akamaihd.net/content/sintel/sintel.mpd"
            )
        )

        subtitleList.addAll(listOf(subtitle1, subtitle2, subtitle3, subtitle4, subtitle5))
        listChild[listHeader[6]] = subtitleList


        listHeader.add("Subtitles TTML Out of stream")
        val sub0: MutableList<PlaylistItem> = ArrayList()
        val subList = mutableListOf<List<PlaylistItem>>()

        val captionTracks = mutableListOf<Caption>()
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
//        val videoSourceSubtitle = "${URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd"
//        val videoSourceSubtitle =
//            "https://demo.unified-streaming.com/video/tears-of-steel/tears-of-steel.ism/.mpd"
        val videoSourceSubtitle = "https://media.xiph.org/tearsofsteel/tears_of_steel_1080p.webm"

        sub0.add(
            PlaylistItem(
                title = "TTML All Selection",
                file = videoSourceSubtitle,
                tracks = captionTracks
            )
        )

        subList.addAll(
            listOf(
                sub0
            )
        )
        listChild[listHeader[7]] = subList

        listHeader.add("Subtitles SRT Out of stream")
        val srt0: MutableList<PlaylistItem> = ArrayList()
        val srtList = mutableListOf<List<PlaylistItem>>()

        val listSubtSRT = mutableListOf<Caption>()
        listSubtSRT.add(
            Caption(
//                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-en.srt",
                file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-en.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "en",
                defaultTrack = true
            )
        )
        listSubtSRT.add(
            Caption(
//                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-es.srt",
                file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-es.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "es"
            )
        )
        listSubtSRT.add(
            Caption(
//                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-fr-Goofy.srt",
                file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-fr-Goofy.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "fr"
            )
        )
        listSubtSRT.add(
            Caption(
//                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-de.srt",
                file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-de.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "de"
            )
        )
        listSubtSRT.add(
            Caption(
//                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-it.srt",
                file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-it.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "it"
            )
        )
        listSubtSRT.add(
            Caption(
//                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-ru.srt",
                file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-ru.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "ru"
            )
        )
        listSubtSRT.add(
            Caption(
//                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-no.srt",
                file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-no.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "no"
            )
        )
        listSubtSRT.add(
            Caption(
//                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-JP.srt",
                file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-JP.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "jp"
            )
        )
        listSubtSRT.add(
            Caption(
//                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-Indonesian.srt",
                file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-Indonesian.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "in"
            )
        )
        listSubtSRT.add(
            Caption(
//                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-Persian.srt",
                file = "https://media.xiph.org/tearsofsteel/subtitles/TOS-Persian.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "pe"
            )
        )

        srt0.add(
            PlaylistItem(
                title = "SRT All Selection",
                file = videoSourceSubtitle,
                tracks = listSubtSRT
            )
        )


        srtList.addAll(
            listOf(
                srt0
            )
        )
        listChild[listHeader[8]] = srtList


        listHeader.add("Subtitles WebVTT Out of stream")

        val vtt11: MutableList<PlaylistItem> = ArrayList()
        val vttList = mutableListOf<List<PlaylistItem>>()

        val listSubt = mutableListOf<Caption>()

        listSubt.add(
            Caption(
//                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-en.vtt",
                file = "https://tears-of-steel-subtitles.s3.amazonaws.com/tears-en.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "en"
            )
        )
        listSubt.add(
            Caption(
                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-es.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "es"
            )
        )
        listSubt.add(
            Caption(
//                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-fr-Goofy.vtt",
                file = "https://tears-of-steel-subtitles.s3.amazonaws.com/tears-fr.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "fr"
            )
        )
        listSubt.add(
            Caption(
                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-de.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "de"
            )
        )
        listSubt.add(
            Caption(
                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-it.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "it"
            )
        )
        listSubt.add(
            Caption(
                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-ru.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "ru"
            )
        )
        listSubt.add(
            Caption(
                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-no.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "no"
            )
        )
        listSubt.add(
            Caption(
                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-JP.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "jp"
            )
        )
        listSubt.add(
            Caption(
                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Indonesian.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "in"
            )
        )
        listSubt.add(
            Caption(
                file = "${URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Persian.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "pe"
            )
        )
        vtt11.add(
            PlaylistItem(
                title = "WebVTT All Selection",
                file = videoSourceSubtitle,
                tracks = listSubt
            )
        )
        vttList.addAll(
            listOf(vtt11)
        )
        listChild[listHeader[9]] = vttList
    }

}