package id.innovationcenter.lidoplayerandroidsdk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import co.innoplayer.InnoPlayerSDK
import co.innoplayer.media.ads.AdBreak
import co.innoplayer.media.ads.AdSource
import co.innoplayer.media.ads.AdType
import co.innoplayer.media.drm.DrmLicense
import co.innoplayer.core.repository.model.feature.Feature
import co.innoplayer.media.captions.MimeTypeSubtitle
import co.innoplayer.media.captions.SubtitleInfo
import co.innoplayer.media.playlists.PlaylistItem
import kotlinx.android.synthetic.main.activity_list_choice.*
import java.io.Serializable

class ListChoiceActivity : AppCompatActivity(), InnoPlayerSDK.KeyCheckInitialListener {
    val TAG = "CLIENTAPP"
    val PROD_URL_INNO = "https://innoplayer.co/"
    val DEV_URL_INNO = "https://nyoba.innoplayer.co/"
    lateinit var listAdapter: ChoiceExpandableListAdapter
    private var listHeader = mutableListOf<String>()
    private var listChild: HashMap<String, List<List<PlaylistItem>>> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_choice)

        InnoPlayerSDK().init(
            this, this,
            "ExoPlayerDemo", "f230a20ffa2bc9d33f18fc0444ef95106f2f903bbfaf30a45b15c60619f8d70b",
            this
        )

        InnoPlayerSDK().initMncAnalytics(
            this,
            "Fw2EgY5ZdYS9XrBkxEJMBXm3AcjB0Lq4gZuSmZUht94wXQlM",
            null,
            "ExoPlayerDemo"
        )

        prepareListData()
        listAdapter = ChoiceExpandableListAdapter(this, listHeader, listChild)

        expandableListView.setAdapter(listAdapter)
        expandableListView.setOnChildClickListener { _, _, p2, p3, _ ->
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
        val hlsPlaylistSample = mutableListOf<List<PlaylistItem>>()

        hls1.add(
            PlaylistItem(
                videoID = "BasicHLSVideoDemo",
                category = "Basic",
                title = "HLS",
                file = "${DEV_URL_INNO}cdn/videos/la_chute_d_une_plume/index.m3u8"
            )
        )
        hls2.add(
            PlaylistItem(
                videoID = "BasicMPEG-DASHVideoDemo",
                category = "Basic",
                title = "MPEG-DASH",
                file = "https://demo.unified-streaming.com/video/tears-of-steel/tears-of-steel.ism/.mpd"
            )
        )
        hls3.add(
            PlaylistItem(
                videoID = "BasicHLSCMAFVideoDemo",
                category = "Basic",
                title = "HLS (CMAF)",
                file = "${DEV_URL_INNO}cdn/videos/cosmos-laundromat/cosmos_laundromat_h264_master.m3u8"
            )
        )
        hls4.add(
            PlaylistItem(
                videoID = "BasicMPEG-DASHCMAFVideoDemo",
                category = "Basic",
                title = "MPEG-DASH (CMAF)",
                file = "${DEV_URL_INNO}cdn/videos/cosmos-laundromat/cosmos_laundromat_h264.mpd"
            )
        )
        hls5.add(
            PlaylistItem(
                videoID = "BasicPreviewThumbnailsVideoDemo",
                title = "Preview Thumbnails",
                category = "Basic",
                file = "https://bitdash-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd",
                urlThumbnails = "https://bitdash-a.akamaihd.net/content/MI201109210084_1/thumbnails/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.jpg"
            )
        )
        hlsPlaylistSample.addAll(listOf(hls1, hls2, hls3, hls4, hls5))
        listChild[listHeader[0]] = hlsPlaylistSample

        listHeader.add("Live Streaming")
        val liveStream1: MutableList<PlaylistItem> = ArrayList()
        val liveStream2: MutableList<PlaylistItem> = ArrayList()
        val liveStreamList = mutableListOf<List<PlaylistItem>>()
        liveStream1.add(
            PlaylistItem(
                title = "HLS",
//                file = "https://cdn-livetv5.metube.id/hls/mnctv.m3u8"
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
                file = "${DEV_URL_INNO}cdn/audios/ES_Cocoona.mp3",
                image = "https://upload.wikimedia.org/wikipedia/commons/5/5e/MP3_logo.png",
                description = "SoundHelix Sound"
            )
        )

        audio2.add(
            PlaylistItem(
                title = "AAC",
                file = "${DEV_URL_INNO}cdn/audios/ES_Cocoona.aac",
                description = "AAC Sample Sound"
            )
        )
        audio3.add(
            PlaylistItem(
                title = "OGG Vorbis",
                file = "${DEV_URL_INNO}cdn/audios/ES_Cocoona.ogg",
                description = "OGG Vorbis Sample Sound"
            )
        )
        audio4.add(
            PlaylistItem(
                title = "Opus",
                file = "${DEV_URL_INNO}cdn/audios/ES_Cocoona.opus",
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
//        playlist2.add(
//            PlaylistItem(
//                title = "Subtitle1 TTML",
//                file = "https://html5demos.com/assets/dizzy.mp4",
//                subtitleUri = "https://storage.googleapis.com/exoplayer-test-media-1/ttml/netflix_ttml_sample.xml",
//                subtitleMimeType = "application/ttml+xml",
//                subtitleLanguage = "en"
//            )
//        )
//        playlist2.add(
//            PlaylistItem(
//                title = "Subtitle2 SSA/ASS position & alignment",
//                file = "https://storage.googleapis.com/exoplayer-test-media-1/gen-3/screens/dash-vod-single-segment/video-avc-baseline-480.mp4",
//                subtitleUri = "https://storage.googleapis.com/exoplayer-test-media-1/ssa/test-subs-position.ass",
//                subtitleMimeType = "text/x-ssa",
//                subtitleLanguage = "en"
//            )
//        )

        val listAds1 = mutableListOf<AdBreak>()
        listAds1.add(
            AdBreak(
                "PRE",
                AdSource.IMA,
//                "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator=",
                "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=",
                AdType.LINEAR
            )
        )
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

//        playlist2.add(
//            PlaylistItem(
//                title = "Scheduled linear preroll, non-linear midroll, linear postroll",
//                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
//                scheduledAdsTag = listAds1
//            )
//        )
//        playlist2.add(
//            PlaylistItem(
//                title = "VMAP linear preroll, non-linear midroll, linear postroll",
//                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
//                adTag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpodbumper&cmsid=496&vid=short_onecue&correlator="
//            )
//        )

        playlist2.add(
            PlaylistItem(
                title = "Preview Thumbnails",
                file = "https://bitdash-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd",
                urlThumbnails = "https://bitdash-a.akamaihd.net/content/MI201109210084_1/thumbnails/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.jpg"
            )
        )
        playlist2.add(
            PlaylistItem(
                title = "MPEG-DASH",
                file = "${DEV_URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd"
            )
        )
        playlistList.addAll(listOf(playlist2))
        listChild[listHeader[3]] = playlistList

        listHeader.add("Advertising - Google IMA SDK")
        val ima1: MutableList<PlaylistItem> = ArrayList()
        val ima2: MutableList<PlaylistItem> = ArrayList()
        val ima3: MutableList<PlaylistItem> = ArrayList()
        val ima4: MutableList<PlaylistItem> = ArrayList()
        val ima5: MutableList<PlaylistItem> = ArrayList()
        val ima6: MutableList<PlaylistItem> = ArrayList()
        val imaPlaylist = mutableListOf<List<PlaylistItem>>()
        ima1.add(
            PlaylistItem(
                title = "Linear Preload Ad",
                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                adTag = "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator="
            )
        )
        ima6.add(
            PlaylistItem(
                title = "Linear Preload Ad Skipable",
                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                adTag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator="
            )
        )
        ima2.add(
            PlaylistItem(
                title = "Non Linear Ad",
                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                adTag = "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator="
            )
        )
        val listAds = mutableListOf<AdBreak>()
        listAds.add(
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
            listAds.add(
                AdBreak(
                    adMid[i],
                    AdSource.IMA,
                    "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=",
//                    "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator=",
                    AdType.LINEAR
                )
            )
        }
        listAds.add(
            AdBreak(
                "POST",
                AdSource.IMA,
                "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=",
                AdType.LINEAR
            )
        )

        ima3.add(
            PlaylistItem(
                title = "Scheduled linear preroll, non-linear midroll, linear postroll",
                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                scheduledAdsTag = listAds
            )
        )
        ima4.add(
            PlaylistItem(
                title = "VMAP linear preroll, non-linear midroll, linear postroll",
                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
//                adTag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpodbumper&cmsid=496&vid=short_onecue&correlator="
                adTag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/"
                        + "ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp"
                        + "&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite"
                        + "%26sample_ar%3Dpremidpost&cmsid=496&vid=short_onecue&correlator="
            )
        )
        ima5.add(
            PlaylistItem(
                title = "VPAID",
                file = "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv",
                adTag = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinearvpaid2js&correlator="
            )
        )

        imaPlaylist.addAll(listOf(ima1, ima2, ima3, ima4, ima5, ima6))
        listChild[listHeader[4]] = imaPlaylist

        listHeader.add("DRM")
        val gts1: MutableList<PlaylistItem> = ArrayList()
        val gts2: MutableList<PlaylistItem> = ArrayList()
        val gts3: MutableList<PlaylistItem> = ArrayList()
        val gts4: MutableList<PlaylistItem> = ArrayList()
        val gts5: MutableList<PlaylistItem> = ArrayList()
        val gts6: MutableList<PlaylistItem> = ArrayList()
        val gtsPlaylist = mutableListOf<List<PlaylistItem>>()
        val drmLicenseList = mutableListOf<DrmLicense>()//
//        drmLicenseList.add(DrmLicense("widevine", "https://proxy.uat.widevine.com/proxy?provider=widevine_test"))
        drmLicenseList.add(DrmLicense("widevine", "https://cwip-shaka-proxy.appspot.com/no_auth"))
        gts1.add(
            PlaylistItem(
                title = "Widevine",//https://storage.googleapis.com/wvmedia/cbc1/h264/tears/tears_aes_cbc1.mpd
//                file = "https://storage.googleapis.com/wvmedia/cbc1/h264/tears/tears_aes_cbc1.mpd",
                file = "https://storage.googleapis.com/shaka-demo-assets/sintel-widevine/dash.mpd",
                drmLicenses = drmLicenseList
            )
        )
//        val drmLicenseList4 = mutableListOf<DrmLicense>()
//        drmLicenseList4.add(
//            DrmLicense(
//                "widevine",
//                "https://amssamples.keydelivery.mediaservices.windows.net/Widevine/?KID=1ab45440-532c-4399-94dc-5c5ad9584bac"
//            )
//        )
//        drmLicenseList4.add(
//            DrmLicense(
//                "playready",
//                "https://amssamples.keydelivery.mediaservices.windows.net/PlayReady/"
//            )
//        )
//        gts4.add(
//            PlaylistItem(
//                title = "Multi DRM",
//                file = "https://amssamples.streaming.mediaservices.windows.net/622b189f-ec39-43f2-93a2-201ac4e31ce1/BigBuckBunny.ism/manifest(format=mpd-time-csf)",
//                drmLicenses = drmLicenseList4
//            )
//        )
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
        val sub1: MutableList<PlaylistItem> = ArrayList()
        val sub2: MutableList<PlaylistItem> = ArrayList()
        val sub3: MutableList<PlaylistItem> = ArrayList()
        val sub4: MutableList<PlaylistItem> = ArrayList()
        val sub5: MutableList<PlaylistItem> = ArrayList()
        val sub6: MutableList<PlaylistItem> = ArrayList()
        val sub7: MutableList<PlaylistItem> = ArrayList()
        val sub8: MutableList<PlaylistItem> = ArrayList()
        val sub9: MutableList<PlaylistItem> = ArrayList()
        val sub10: MutableList<PlaylistItem> = ArrayList()
        val sub11: MutableList<PlaylistItem> = ArrayList()
        val subList = mutableListOf<List<PlaylistItem>>()

        val listSubtTTML = mutableListOf<SubtitleInfo>()
        listSubtTTML.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-en.ttml",
                mimeType = MimeTypeSubtitle.TTML.value,
                language = "en"
            )
        )
        listSubtTTML.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-es.ttml",
                mimeType = MimeTypeSubtitle.TTML.value,
                language = "es"
            )
        )
        listSubtTTML.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-fr-Goofy.ttml",
                mimeType = MimeTypeSubtitle.TTML.value,
                language = "fr"
            )
        )
        listSubtTTML.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-de.ttml",
                mimeType = MimeTypeSubtitle.TTML.value,
                language = "de"
            )
        )
        listSubtTTML.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-it.ttml",
                mimeType = MimeTypeSubtitle.TTML.value,
                language = "it"
            )
        )
        listSubtTTML.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-ru.ttml",
                mimeType = MimeTypeSubtitle.TTML.value,
                language = "ru"
            )
        )
        listSubtTTML.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-no.ttml",
                mimeType = MimeTypeSubtitle.TTML.value,
                language = "no"
            )
        )
        listSubtTTML.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-JP.ttml",
                mimeType = MimeTypeSubtitle.TTML.value,
                language = "jp"
            )
        )
        listSubtTTML.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-Indonesian.ttml",
                mimeType = MimeTypeSubtitle.TTML.value,
                language = "in"
            )
        )
        listSubtTTML.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-Persian.ttml",
                mimeType = MimeTypeSubtitle.TTML.value,
                language = "pe"
            )
        )
        listSubtTTML.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-CH.ttml",
                mimeType = MimeTypeSubtitle.TTML.value,
                language = "ch"
            )
        )
        val videoSourceSubtitle =
            "${DEV_URL_INNO}cdn/videos/tears_of_steel/tears_of_steel.mpd"
        sub0.add(
            PlaylistItem(
                title = "TTML All Selection",
                file = videoSourceSubtitle,
                subtitleList = listSubtTTML
            )
        )
        sub1.add(
            PlaylistItem(
                title = "TTML English",
                file = videoSourceSubtitle,
                subtitleLanguage = "en",
                subtitleMimeType = MimeTypeSubtitle.TTML.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-en.ttml"
            )
        )
        sub2.add(
            PlaylistItem(
                title = "TTML Spanish",
                file = videoSourceSubtitle,
                subtitleLanguage = "es",
                subtitleMimeType = MimeTypeSubtitle.TTML.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-es.ttml"
            )
        )
        sub3.add(
            PlaylistItem(
                title = "TTML French",
                file = videoSourceSubtitle,
                subtitleLanguage = "fr",
                subtitleMimeType = MimeTypeSubtitle.TTML.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-fr-Goofy.ttml"
            )
        )
        sub4.add(
            PlaylistItem(
                title = "TTML Dutch",
                file = videoSourceSubtitle,
                subtitleLanguage = "de",
                subtitleMimeType = MimeTypeSubtitle.TTML.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-de.ttml"
            )
        )
        sub5.add(
            PlaylistItem(
                title = "TTML Italian",
                file = videoSourceSubtitle,
                subtitleLanguage = "it",
                subtitleMimeType = MimeTypeSubtitle.TTML.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-it.ttml"
            )
        )
        sub6.add(
            PlaylistItem(
                title = "TTML Russian",
                file = videoSourceSubtitle,
                subtitleLanguage = "ru",
                subtitleMimeType = MimeTypeSubtitle.TTML.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-ru.ttml"
            )
        )
        sub7.add(
            PlaylistItem(
                title = "TTML Norwegian",
                file = videoSourceSubtitle,
                subtitleLanguage = "no",
                subtitleMimeType = MimeTypeSubtitle.TTML.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-no.ttml"
            )
        )
        sub8.add(
            PlaylistItem(
                title = "TTML Japanese",
                file = videoSourceSubtitle,
                subtitleLanguage = "jp",
                subtitleMimeType = MimeTypeSubtitle.TTML.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-JP.ttml"
            )
        )
        sub9.add(
            PlaylistItem(
                title = "TTML Indonesian",
                file = videoSourceSubtitle,
                subtitleLanguage = "in",
                subtitleMimeType = MimeTypeSubtitle.TTML.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-Indonesian.ttml"
            )
        )
        sub10.add(
            PlaylistItem(
                title = "TTML Persian",
                file = videoSourceSubtitle,
                subtitleLanguage = "pe",
                subtitleMimeType = MimeTypeSubtitle.TTML.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-Persian.ttml"
            )
        )
        sub11.add(
            PlaylistItem(
                title = "TTML Chinese",
                file = videoSourceSubtitle,
                subtitleLanguage = "ch",
                subtitleMimeType = MimeTypeSubtitle.TTML.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/ttml/TOS-CH.ttml"
            )
        )
        subList.addAll(
            listOf(
//                sub1,
//                sub2,
//                sub3,
//                sub4,
//                sub5,
//                sub6,
//                sub7,
//                sub8,
//                sub9,
//                sub10,
//                sub11,
                sub0
            )
        )
        listChild[listHeader[7]] = subList

        listHeader.add("Subtitles SRT Out of stream")
        val srt0: MutableList<PlaylistItem> = ArrayList()
        val srt1: MutableList<PlaylistItem> = ArrayList()
        val srt2: MutableList<PlaylistItem> = ArrayList()
        val srt3: MutableList<PlaylistItem> = ArrayList()
        val srt4: MutableList<PlaylistItem> = ArrayList()
        val srt5: MutableList<PlaylistItem> = ArrayList()
        val srt6: MutableList<PlaylistItem> = ArrayList()
        val srt7: MutableList<PlaylistItem> = ArrayList()
        val srt8: MutableList<PlaylistItem> = ArrayList()
        val srt9: MutableList<PlaylistItem> = ArrayList()
        val srt10: MutableList<PlaylistItem> = ArrayList()
        val srtList = mutableListOf<List<PlaylistItem>>()

        val listSubtSRT = mutableListOf<SubtitleInfo>()
        listSubtSRT.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-en.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "en"
            )
        )
        listSubtSRT.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-es.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "es"
            )
        )
        listSubtSRT.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-fr-Goofy.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "fr"
            )
        )
        listSubtSRT.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-de.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "de"
            )
        )
        listSubtSRT.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-it.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "it"
            )
        )
        listSubtSRT.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-ru.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "ru"
            )
        )
        listSubtSRT.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-no.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "no"
            )
        )
        listSubtSRT.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-JP.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "jp"
            )
        )
        listSubtSRT.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-Indonesian.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "in"
            )
        )
        listSubtSRT.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-Persian.srt",
                mimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                language = "pe"
            )
        )

        srt0.add(
            PlaylistItem(
                title = "SRT All Selection",
                file = videoSourceSubtitle,
                subtitleList = listSubtSRT
            )
        )
        srt1.add(
            PlaylistItem(
                title = "SRT English",
                file = videoSourceSubtitle,
                subtitleLanguage = "en",
                subtitleMimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-en.srt"
            )
        )
        srt2.add(
            PlaylistItem(
                title = "SRT Spanish",
                file = videoSourceSubtitle,
                subtitleLanguage = "es",
                subtitleMimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-es.srt"
            )
        )
        srt3.add(
            PlaylistItem(
                title = "SRT French",
                file = videoSourceSubtitle,
                subtitleLanguage = "fr",
                subtitleMimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-fr-Goofy.srt"
            )
        )
        srt4.add(
            PlaylistItem(
                title = "SRT Dutch",
                file = videoSourceSubtitle,
                subtitleLanguage = "de",
                subtitleMimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-de.srt"
            )
        )
        srt5.add(
            PlaylistItem(
                title = "SRT Italian",
                file = videoSourceSubtitle,
                subtitleLanguage = "it",
                subtitleMimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-it.srt"
            )
        )
        srt6.add(
            PlaylistItem(
                title = "SRT Russian",
                file = videoSourceSubtitle,
                subtitleLanguage = "ru",
                subtitleMimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-ru.srt"
            )
        )
        srt7.add(
            PlaylistItem(
                title = "SRT Norwegian",
                file = videoSourceSubtitle,
                subtitleLanguage = "no",
                subtitleMimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-no.srt"
            )
        )

        srt8.add(
            PlaylistItem(
                title = "SRT Japanese",
                file = videoSourceSubtitle,
                subtitleLanguage = "jp",
                subtitleMimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-JP.srt"
            )
        )
        srt9.add(
            PlaylistItem(
                title = "SRT Indonesian",
                file = videoSourceSubtitle,
                subtitleLanguage = "in",
                subtitleMimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-Indonesian.srt"
            )
        )
        srt10.add(
            PlaylistItem(
                title = "SRT Persian",
                file = videoSourceSubtitle,
                subtitleLanguage = "pe",
                subtitleMimeType = MimeTypeSubtitle.APPLICATION_SUBRIP.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/srt/TOS-Persian.srt"
            )
        )

        srtList.addAll(
            listOf(
//            srt1,
//                srt2
//                , srt3, srt4, srt5, srt6, srt7, srt8, srt9, srt10,
                srt0
            )
        )
        listChild[listHeader[8]] = srtList


        listHeader.add("Subtitles WebVTT Out of stream")
        val vtt1: MutableList<PlaylistItem> = ArrayList()
        val vtt2: MutableList<PlaylistItem> = ArrayList()
        val vtt3: MutableList<PlaylistItem> = ArrayList()
        val vtt4: MutableList<PlaylistItem> = ArrayList()
        val vtt5: MutableList<PlaylistItem> = ArrayList()
        val vtt6: MutableList<PlaylistItem> = ArrayList()
        val vtt7: MutableList<PlaylistItem> = ArrayList()
        val vtt8: MutableList<PlaylistItem> = ArrayList()
        val vtt9: MutableList<PlaylistItem> = ArrayList()
        val vtt10: MutableList<PlaylistItem> = ArrayList()
        val vtt11: MutableList<PlaylistItem> = ArrayList()
        val vttList = mutableListOf<List<PlaylistItem>>()

        vtt1.add(
            PlaylistItem(
                title = "English",
                file = videoSourceSubtitle,
                subtitleLanguage = "en",
                subtitleMimeType = MimeTypeSubtitle.TEXT_VTT.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-en.vtt"
            )
        )
        vtt2.add(
            PlaylistItem(
                title = "Spanish",
                file = videoSourceSubtitle,
                subtitleLanguage = "es",
                subtitleMimeType = MimeTypeSubtitle.TEXT_VTT.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-es.vtt"
            )
        )
        vtt3.add(
            PlaylistItem(
                title = "French",
                file = videoSourceSubtitle,
                subtitleLanguage = "fr",
                subtitleMimeType = MimeTypeSubtitle.TEXT_VTT.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-fr-Goofy.vtt"
            )
        )
        vtt4.add(
            PlaylistItem(
                title = "Dutch",
                file = videoSourceSubtitle,
                subtitleLanguage = "de",
                subtitleMimeType = MimeTypeSubtitle.TEXT_VTT.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-de.vtt"
            )
        )
        vtt5.add(
            PlaylistItem(
                title = "Italian",
                file = videoSourceSubtitle,
                subtitleLanguage = "it",
                subtitleMimeType = MimeTypeSubtitle.TEXT_VTT.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-it.vtt"
            )
        )
        vtt6.add(
            PlaylistItem(
                title = "Russian",
                file = videoSourceSubtitle,
                subtitleLanguage = "ru",
                subtitleMimeType = MimeTypeSubtitle.TEXT_VTT.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-ru.vtt"
            )
        )
        vtt7.add(
            PlaylistItem(
                title = "Norwegian",
                file = videoSourceSubtitle,
                subtitleLanguage = "no",
                subtitleMimeType = MimeTypeSubtitle.TEXT_VTT.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-no.vtt"
            )
        )

        vtt8.add(
            PlaylistItem(
                title = "Japanese",
                file = videoSourceSubtitle,
                subtitleLanguage = "jp",
                subtitleMimeType = MimeTypeSubtitle.TEXT_VTT.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-JP.vtt"
            )
        )
        vtt9.add(
            PlaylistItem(
                title = "Indonesian",
                file = videoSourceSubtitle,
                subtitleLanguage = "in",
                subtitleMimeType = MimeTypeSubtitle.TEXT_VTT.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Indonesian.vtt"
            )
        )
        vtt10.add(
            PlaylistItem(
                title = "Persian",
                file = videoSourceSubtitle,
                subtitleLanguage = "pe",
                subtitleMimeType = MimeTypeSubtitle.TEXT_VTT.value,
                subtitleUri = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Persian.vtt"
            )
        )

        val listSubt = mutableListOf<SubtitleInfo>()

        listSubt.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-en.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "en"
            )
        )
        listSubt.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-es.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "es"
            )
        )
        listSubt.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-fr-Goofy.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "fr"
            )
        )
        listSubt.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-de.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "de"
            )
        )
        listSubt.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-it.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "it"
            )
        )
        listSubt.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-ru.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "ru"
            )
        )
        listSubt.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-no.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "no"
            )
        )
        listSubt.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-JP.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "jp"
            )
        )
        listSubt.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Indonesian.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "in"
            )
        )
        listSubt.add(
            SubtitleInfo(
                subtitle = "${DEV_URL_INNO}cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Persian.vtt",
                mimeType = MimeTypeSubtitle.TEXT_VTT.value,
                language = "pe"
            )
        )
        vtt11.add(
            PlaylistItem(
                title = "WebVTT All Selection",
                file = videoSourceSubtitle,
                subtitleList = listSubt
            )
        )
        vttList.addAll(
            listOf(
//                vtt1, vtt2, vtt3, vtt4, vtt5, vtt6,
//                vtt7, vtt8, vtt9, vtt10,
                vtt11
            )
        )
        listChild[listHeader[9]] = vttList
    }

    override fun onFinishLidoPlayerKeyCheck(feature: Feature?, errorMessage: String?) {
        Log.d(TAG, "Error Message : $errorMessage")
        Log.d(TAG, "feature this key : $feature")
        val canPlayContent = feature?.canPlayContent ?: false
        val showAds = feature?.canShowAds ?: false
        val playDrmContent = feature?.canPlayDRMContent ?: false
        Log.d(TAG, "canPlayContent : $canPlayContent")
        Log.d(TAG, "canShowAds : $showAds")
        Log.d(TAG, "canPlayDRMContent : $playDrmContent")
    }

    override fun onProgressLidoPlayerKeyCheck(isShowProgress: Boolean) {
        Log.d(TAG, "progress checking key : $isShowProgress")
    }
}
