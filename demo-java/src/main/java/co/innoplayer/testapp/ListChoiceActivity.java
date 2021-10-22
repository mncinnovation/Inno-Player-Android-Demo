package co.innoplayer.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.innoplayer.InnoPlayerSDK;
import co.innoplayer.core.repository.model.feature.Feature;
import co.innoplayer.events.InnoError;
import co.innoplayer.media.ads.AdBreak;
import co.innoplayer.media.ads.AdSource;
import co.innoplayer.media.ads.AdType;
import co.innoplayer.media.ads.AdTypeSource;
import co.innoplayer.media.captions.Caption;
import co.innoplayer.media.captions.CaptionType;
import co.innoplayer.media.captions.MimeTypeSubtitle;
import co.innoplayer.media.drm.DrmLicense;
import co.innoplayer.media.playlists.PlaylistItem;
import testapp.databinding.ActivityListChoiceBinding;


public class ListChoiceActivity extends AppCompatActivity implements InnoPlayerSDK.KeyCheckInitialListener {
    String key = "42ef1e0bf5d4641449ebc9a00993c8ed5bcecf588e7c64fb2322f91b95859a7d";
    final String TAG = "CLIENTAPP";
    ChoiceExpandableListAdapter listAdapter;
    List<String> listHeader = new ArrayList<>();
    HashMap<String, List<List<PlaylistItem>>> listChild = new HashMap<>();

    ActivityListChoiceBinding binding;
    final static String URL_INNO = "https://innoplayer.co/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListChoiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String packageName = getApplicationContext().getPackageName();
        Log.e(TAG, "PACKAGENAME IS : " + packageName);

        InnoPlayerSDK innoPlayerSDK = new InnoPlayerSDK();
        innoPlayerSDK.init(
                this, this,
                "Inno Player Demo", key,
                this
        );

        innoPlayerSDK.initMncAnalytics(
                this,
                "Fw2EgY5ZdYS9XrBkxEJMBXm3AcjB0Lq4gZuSmZUht94wXQlM",
                null,
                "InnoPlayerDemo"
        );

        prepareListData();

        listAdapter = new ChoiceExpandableListAdapter(this, listHeader, listChild);
        binding.expandableListView.setAdapter(listAdapter);
        binding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (listChild.get(listHeader.get(i)) == null) {
                    return false;
                }
                List<PlaylistItem> playlistItems = listChild.get(listHeader.get(i)).get(i1);

                Intent intent = new Intent(ListChoiceActivity.this, VideoPlayerActivity.class);
                if (listHeader.get(i).contains("Audio")) {
                    intent = new Intent(ListChoiceActivity.this, VideoPlayerActivity.class);
                }
                intent.putExtra("playlistItems", (Serializable) playlistItems);
                startActivity(intent);
                StringBuilder itemChild = new StringBuilder();
                for (int j = 0; j < playlistItems.size(); j++) {
                    itemChild.append(playlistItems.get(j).getTitle());
                    if (j != playlistItems.size() - 1) {
                        itemChild.append(", ");
                    }
                }
                Toast.makeText(ListChoiceActivity.this, listHeader.get(i) + " : " + itemChild.toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void prepareListData() {
        listHeader.add("Basic Streaming");
        List<PlaylistItem> basic1 = new ArrayList<>();
        List<PlaylistItem> basic2 = new ArrayList<>();
        List<PlaylistItem> basic3 = new ArrayList<>();
        List<PlaylistItem> basic4 = new ArrayList<>();
        List<PlaylistItem> basic5 = new ArrayList<>();
        List<PlaylistItem> basic6 = new ArrayList<>();
        List<List<PlaylistItem>> hlsPlaylistSample = new ArrayList<>();
        PlaylistItem playlistItemBasic1 = new PlaylistItem();
        playlistItemBasic1.setTitle("HLS");
        playlistItemBasic1.setMediaId("BasicHLSVideoDemo");
        playlistItemBasic1.setFile(URL_INNO + "cdn/videos/la_chute_d_une_plume/index.m3u8");
        playlistItemBasic1.setCategory("Basic");
        basic1.add(playlistItemBasic1);

        PlaylistItem playlistItemBasic2 = new PlaylistItem();
        playlistItemBasic2.setTitle("MPEG-DASH");
        playlistItemBasic2.setMediaId("BasicMPEG-DASHVideoDemo");
        playlistItemBasic2.setFile("https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.mpd");
        playlistItemBasic2.setCategory("Basic");
        basic2.add(playlistItemBasic2);

        PlaylistItem playlistItemBasic3 = new PlaylistItem();
        playlistItemBasic3.setTitle("HLS (CMAF)");
        playlistItemBasic3.setMediaId("BasicHLSCMAFVideoDemo");
        playlistItemBasic3.setFile(URL_INNO + "cdn/videos/cosmos-laundromat/cosmos_laundromat_h264_master.m3u8");
        playlistItemBasic3.setCategory("Basic");
        basic3.add(playlistItemBasic3);

        PlaylistItem playlistItemBasic4 = new PlaylistItem();
        playlistItemBasic4.setTitle("MPEG-DASH (CMAF)");
        playlistItemBasic4.setMediaId("BasicMPEG-DASHCMAFVideoDemo");
        playlistItemBasic4.setFile(URL_INNO + "cdn/videos/cosmos-laundromat/cosmos_laundromat_h264.mpd");
        playlistItemBasic4.setCategory("Basic");
        basic4.add(playlistItemBasic4);

        PlaylistItem playlistItemBasic5 = new PlaylistItem();
        playlistItemBasic5.setTitle("MPEG-DASH (CMAF)");
        playlistItemBasic5.setMediaId("BasicMPEG-DASHCMAFVideoDemo");
        playlistItemBasic5.setFile(URL_INNO + "cdn/videos/cosmos-laundromat/cosmos_laundromat_h264.mpd");
        playlistItemBasic5.setCategory("Basic");
        basic5.add(playlistItemBasic5);

        List<Caption> thumbnailTrack = new ArrayList<>();
        Caption caption = new Caption("https://bitdash-a.akamaihd.net/content/MI201109210084_1/thumbnails/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.jpg", CaptionType.THUMBNAILS, null, null, null, null);
        thumbnailTrack.add(caption);

        PlaylistItem playlistItemBasic6 = new PlaylistItem();
        playlistItemBasic6.setTitle("Preview Thumbnails");
        playlistItemBasic6.setMediaId("BasicPreviewThumbnailsVideoDemo");
        playlistItemBasic6.setFile("https://bitdash-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd");
        playlistItemBasic6.setCategory("Basic");
        basic6.add(playlistItemBasic6);

        hlsPlaylistSample.add(basic1);
        hlsPlaylistSample.add(basic2);
        hlsPlaylistSample.add(basic3);
        hlsPlaylistSample.add(basic4);
        hlsPlaylistSample.add(basic5);
        hlsPlaylistSample.add(basic6);

        listChild.put(listHeader.get(0), hlsPlaylistSample);

        listHeader.add("Live Streaming");
        List<PlaylistItem> live1 = new ArrayList<>();
        List<PlaylistItem> live2 = new ArrayList<>();
        List<List<PlaylistItem>> liveStreamSample = new ArrayList<>();
        live1.add(new PlaylistItem.Builder().title("HLS").file("https://tv.balkanweb.com/news24/livestream/playlist.m3u8").build());
        live2.add(new PlaylistItem.Builder().title("DASH").file("https://livesim.dashif.org/livesim/ato_10/testpic_2s/Manifest.mpd").build());
        liveStreamSample.add(live1);
        liveStreamSample.add(live2);
        listChild.put(listHeader.get(1), liveStreamSample);


        listHeader.add("Audio");
        List<PlaylistItem> audio1 = new ArrayList<>();
        List<PlaylistItem> audio2 = new ArrayList<>();
        List<PlaylistItem> audio3 = new ArrayList<>();
        List<PlaylistItem> audio4 = new ArrayList<>();
        List<PlaylistItem> audio5 = new ArrayList<>();
        List<List<PlaylistItem>> audioSample = new ArrayList<>();
        audio1.add(new PlaylistItem.Builder()
                .title("MP3")
                .file("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
                .image("https://upload.wikimedia.org/wikipedia/commons/5/5e/MP3_logo.png")
                .description("SoundHelix Sound").build());
        audio2.add(new PlaylistItem.Builder()
                .title("AAC")
                .file(URL_INNO + "cdn/audios/ES_Cocoona.aac")
                .description("AAC Sample Sound").build());
        audio3.add(new PlaylistItem.Builder()
                .title("OGG Vorbis")
                .file(URL_INNO + "cdn/audios/ES_Cocoona.ogg")
                .description("OGG Vorbis Sample Sound").build());
        audio4.add(new PlaylistItem.Builder()
                .title("Opus")
                .file(URL_INNO + "cdn/audios/ES_Cocoona.opus")
                .description("Opus Sample Sound").build());
        audio5.add(new PlaylistItem.Builder()
                .title("Internet Radio\nBBC Media")
                .file(URL_INNO + "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1_mf_q")
                .description("Internet Radio").build());

        audioSample.add(audio1);
        audioSample.add(audio2);
        audioSample.add(audio3);
        audioSample.add(audio4);
        audioSample.add(audio5);
        listChild.put(listHeader.get(2), audioSample);

        listHeader.add("Playlist");
        List<PlaylistItem> playlist1 = new ArrayList<>();
        List<List<PlaylistItem>> playlistSample = new ArrayList<>();
        playlist1.add(new PlaylistItem.Builder()
                .title("Preview Thumbnails")
                .file("https://bitdash-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd")
                .tracks(thumbnailTrack)
                .build()
        );
        playlist1.add(new PlaylistItem.Builder()
                .title("MPEG-DASH")
                .file("https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.mpd")
                .build()
        );
        playlistSample.add(playlist1);
        listChild.put(listHeader.get(3), playlistSample);

        listHeader.add("Advertising - Google IMA SDK");
        List<PlaylistItem> ima0 = new ArrayList<>();
        List<PlaylistItem> ima1 = new ArrayList<>();
        List<PlaylistItem> ima2 = new ArrayList<>();
        List<PlaylistItem> ima3 = new ArrayList<>();
        List<PlaylistItem> ima4 = new ArrayList<>();
        List<PlaylistItem> ima5 = new ArrayList<>();
        List<List<PlaylistItem>> imaSample = new ArrayList<>();

        List<AdBreak> listAds = new ArrayList<>();
        listAds.add(new AdBreak(null, null, "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=", null));
        ima0.add(new PlaylistItem.Builder().title("Linear Preload Ad Skipable")
                .mediaId("ads-linearskipable")
                .category("ads")
                .adSchedule(listAds)
                .file("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv")
                .adTypeSource(AdTypeSource.VAST)
                .build());

        List<AdBreak> listAds2 = new ArrayList<>();
        listAds2.add(new AdBreak(null, null, "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=", null));
        ima1.add(new PlaylistItem.Builder().title("Linear Preload Ad")
                .mediaId("ads-linearskipable")
                .category("ads")
                .adSchedule(listAds2)
                .file("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv")
                .adTypeSource(AdTypeSource.VAST)
                .build());

        List<AdBreak> listAds3 = new ArrayList<>();
        listAds3.add(new AdBreak(null, null, "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator=", AdType.NONLINEAR));
        ima2.add(new PlaylistItem.Builder().title("Non Linear Ad")
                .mediaId("ads-nonlinear")
                .category("ads")
                .adSchedule(listAds3)
                .file("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv")
                .build());

        List<AdBreak> listAds4 = new ArrayList<>();
        listAds4.add(new AdBreak("PRE", AdSource.IMA, "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=", AdType.LINEAR));
        List<String> adMid = new ArrayList<>();
        adMid.add("20");
        for (int i = 0; i < adMid.size(); i++) {
            listAds4.add(new AdBreak(adMid.get(i), AdSource.IMA, "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=", AdType.LINEAR));
        }
        listAds4.add(new AdBreak("POST", AdSource.IMA, "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=", AdType.LINEAR));

        ima3.add(new PlaylistItem.Builder().title("Scheduled linear preroll, non-linear midroll, linear postroll")
                .mediaId("ads-schedule")
                .category("ads")
                .adSchedule(listAds4)
                .adTypeSource(AdTypeSource.VMAP)
                .file("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv")
                .build());


        List<AdBreak> listAds5 = new ArrayList<>();
        listAds5.add(new AdBreak(null, null, "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/"
                + "ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp"
                + "&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite"
                + "%26sample_ar%3Dpremidpost&cmsid=496&vid=short_onecue&correlator=", AdType.NONLINEAR));
        ima4.add(new PlaylistItem.Builder().title("VMAP linear preroll, non-linear midroll, linear postroll")
                .mediaId("ads-vmap")
                .category("ads")
                .adTypeSource(AdTypeSource.VMAP)
                .adSchedule(listAds5)
                .file("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv")
                .build());

        List<AdBreak> listAds6 = new ArrayList<>();
        listAds6.add(new AdBreak(null, null, "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinearvpaid2js&correlator=", AdType.NONLINEAR));
        ima5.add(new PlaylistItem.Builder().title("VPAID")
                .mediaId("ads-vpaid")
                .category("ads")
                .adTypeSource(AdTypeSource.VPAID)
                .adSchedule(listAds6)
                .file("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv")
                .build());

        imaSample.add(ima0);
        imaSample.add(ima1);
        imaSample.add(ima2);
        imaSample.add(ima3);
        imaSample.add(ima4);
        imaSample.add(ima5);
        listChild.put(listHeader.get(4), imaSample);

        listHeader.add("DRM");
        List<PlaylistItem> drm1 = new ArrayList<>();
        List<List<PlaylistItem>> drmSample = new ArrayList<>();
        List<DrmLicense> drmLicenseList = new ArrayList<>();
        drmLicenseList.add(new DrmLicense("widevine", "https://cwip-shaka-proxy.appspot.com/no_auth"));
        PlaylistItem playlis = new PlaylistItem();
        playlis.setTitle("Widevine");
        playlis.setFile("https://storage.googleapis.com/shaka-demo-assets/sintel-widevine/dash.mpd");
        playlis.setDrmLicenses(drmLicenseList);
        drm1.add(playlis);

        drmSample.add(drm1);
        listChild.put(listHeader.get(5), drmSample);

        listHeader.add("Subtitles Embedded in stream");
        List<PlaylistItem> subsEmbed1 = new ArrayList<>();
        List<PlaylistItem> subsEmbed2 = new ArrayList<>();
        List<PlaylistItem> subsEmbed3 = new ArrayList<>();
        List<PlaylistItem> subsEmbed4 = new ArrayList<>();
        List<PlaylistItem> subsEmbed5 = new ArrayList<>();
        List<List<PlaylistItem>> subEmbedSample = new ArrayList<>();

        subsEmbed1.add(
                new PlaylistItem.Builder()
                        .title("CEA-608 - HLS (Embedded in stream)")
                        .file("https://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/bipbop_16x9_variant.m3u8")
                        .build()
        );
        subsEmbed2.add(
                new PlaylistItem.Builder()
                        .title("CEA-608 - MPEG-DASH (Embedded in stream)")
                        .file("https://vm2.dashif.org/dash/vod/testpic_2s/cea608.mpd")
                        .build()
        );
        subsEmbed3.add(
                new PlaylistItem.Builder()
                        .title("TTML (Embedded in stream)")
                        .file("https://irtdashreference-i.akamaihd.net/dash/live/901161/bfs/manifestARD.mpd")
                        .build()
        );
        subsEmbed4.add(
                new PlaylistItem.Builder()
                        .title("WebVTT-HLS (Embedded in stream)")
                        .file("https://bitmovin-a.akamaihd.net/content/sintel/hls/playlist.m3u8")
                        .build()
        );
        subsEmbed5.add(
                new PlaylistItem.Builder()
                        .title("WebVTT-DASH (Embedded in stream)")
                        .file("https://bitmovin-a.akamaihd.net/content/sintel/sintel.mpd")
                        .build()
        );

        subEmbedSample.add(subsEmbed1);
        subEmbedSample.add(subsEmbed2);
        subEmbedSample.add(subsEmbed3);
        subEmbedSample.add(subsEmbed4);
        subEmbedSample.add(subsEmbed5);
        listChild.put(listHeader.get(6), subEmbedSample);

        String videoSourceSubtitle =
                "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.mpd";

        listHeader.add("Subtitles TTML Out of stream");
        List<PlaylistItem> subOutTTML = new ArrayList<>();
        List<List<PlaylistItem>> subList = new ArrayList<>();

        List<Caption> captionTracks = new ArrayList<>();
        Caption captionEn = new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/ttml/TOS-en.ttml")
                .language("en")
                .label("English")
                .kind(CaptionType.CAPTIONS)
                .mimeType(MimeTypeSubtitle.TTML.getValue())
                .isdefault(true)
                .build();
        captionTracks.add(captionEn);
        captionTracks.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/ttml/TOS-es.ttml")
                .language("es")
                .mimeType(MimeTypeSubtitle.TTML.getValue())
                .build());
        captionTracks.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/ttml/TOS-fr-Goofy.ttml")
                .language("fr")
                .mimeType(MimeTypeSubtitle.TTML.getValue())
                .build());
        captionTracks.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/ttml/TOS-de.ttml")
                .language("de")
                .mimeType(MimeTypeSubtitle.TTML.getValue())
                .build());
        captionTracks.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/ttml/TOS-it.ttml")
                .language("it")
                .mimeType(MimeTypeSubtitle.TTML.getValue())
                .build());
        captionTracks.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/ttml/TOS-ru.ttml")
                .language("ru")
                .mimeType(MimeTypeSubtitle.TTML.getValue())
                .build());
        captionTracks.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/ttml/TOS-no.ttml")
                .language("no")
                .mimeType(MimeTypeSubtitle.APPLICATION_SUBRIP.getValue())
                .build());
        captionTracks.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/ttml/TOS-JP.ttml")
                .language("jp")
                .mimeType(MimeTypeSubtitle.TTML.getValue())
                .build());
        captionTracks.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/ttml/TOS-Indonesian.ttml")
                .language("in")
                .mimeType(MimeTypeSubtitle.TTML.getValue())
                .build());
        captionTracks.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/ttml/TOS-Persian.ttml")
                .language("pe")
                .mimeType(MimeTypeSubtitle.TTML.getValue())
                .build());
        captionTracks.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/ttml/TOS-CH.ttml")
                .language("ch")
                .mimeType(MimeTypeSubtitle.TTML.getValue())
                .build());
        subOutTTML.add(new PlaylistItem.Builder()
                .title("TTML All Selection")
                .tracks(captionTracks)
                .file(videoSourceSubtitle)
                .build());

        subList.add(subOutTTML);
        listChild.put(listHeader.get(7), subList);

        listHeader.add("Subtitles SRT Out of stream");
        List<PlaylistItem> subOutSRT = new ArrayList<>();
        List<List<PlaylistItem>> subListSrt = new ArrayList<>();

        List<Caption> captionTracksSrt = new ArrayList<>();

        captionTracksSrt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/srt/TOS-en.srt")
                .language("en")
                .label("English")
                .kind(CaptionType.CAPTIONS)
                .mimeType(MimeTypeSubtitle.APPLICATION_SUBRIP.getValue())
                .isdefault(true)
                .build());
        captionTracksSrt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/srt/TOS-es.srt")
                .language("es")
                .mimeType(MimeTypeSubtitle.APPLICATION_SUBRIP.getValue())
                .build());
        captionTracksSrt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/srt/TOS-fr-Goofy.srt")
                .language("fr")
                .mimeType(MimeTypeSubtitle.APPLICATION_SUBRIP.getValue())
                .build());
        captionTracksSrt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/srt/TOS-de.srt")
                .language("de")
                .mimeType(MimeTypeSubtitle.APPLICATION_SUBRIP.getValue())
                .build());
        captionTracksSrt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/srt/TOS-it.srt")
                .language("it")
                .mimeType(MimeTypeSubtitle.APPLICATION_SUBRIP.getValue())
                .build());
        captionTracksSrt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/srt/TOS-ru.srt")
                .language("ru")
                .mimeType(MimeTypeSubtitle.APPLICATION_SUBRIP.getValue())
                .build());
        captionTracksSrt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/srt/TOS-no.srt")
                .language("no")
                .mimeType(MimeTypeSubtitle.APPLICATION_SUBRIP.getValue())
                .build());
        captionTracksSrt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/srt/TOS-JP.srt")
                .language("jp")
                .mimeType(MimeTypeSubtitle.APPLICATION_SUBRIP.getValue())
                .build());
        captionTracksSrt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/srt/TOS-Indonesian.srt")
                .language("in")
                .mimeType(MimeTypeSubtitle.APPLICATION_SUBRIP.getValue())
                .build());
        captionTracksSrt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/srt/TOS-Persian.srt")
                .language("pe")
                .mimeType(MimeTypeSubtitle.APPLICATION_SUBRIP.getValue())
                .build());
        captionTracksSrt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/srt/TOS-CH.srt")
                .language("ch")
                .mimeType(MimeTypeSubtitle.APPLICATION_SUBRIP.getValue())
                .build());
        subOutSRT.add(new PlaylistItem.Builder()
                .title("SRT All Selection")
                .tracks(captionTracksSrt)
                .file(videoSourceSubtitle)
                .build());

        subListSrt.add(subOutSRT);
        listChild.put(listHeader.get(8), subListSrt);

        listHeader.add("Subtitles SRT Out of stream");
        List<PlaylistItem> subOutVTT = new ArrayList<>();
        List<List<PlaylistItem>> subListVtt = new ArrayList<>();

        List<Caption> captionTracksVtt = new ArrayList<>();

        captionTracksVtt.add(new Caption.Builder()
                .file("https://tears-of-steel-subtitles.s3.amazonaws.com/tears-en.vtt")
                .language("en")
                .label("English")
                .kind(CaptionType.CAPTIONS)
                .mimeType(MimeTypeSubtitle.TEXT_VTT.getValue())
                .isdefault(true)
                .build());
        captionTracksVtt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/webvtt/TOS-es.vtt")
                .language("es")
                .mimeType(MimeTypeSubtitle.TEXT_VTT.getValue())
                .build());
        captionTracksVtt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/webvtt/TOS-fr-Goofy.vtt")
                .language("fr")
                .mimeType(MimeTypeSubtitle.TEXT_VTT.getValue())
                .build());
        captionTracksVtt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/webvtt/TOS-de.vtt")
                .language("de")
                .mimeType(MimeTypeSubtitle.TEXT_VTT.getValue())
                .build());
        captionTracksVtt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/webvtt/TOS-it.vtt")
                .language("it")
                .mimeType(MimeTypeSubtitle.TEXT_VTT.getValue())
                .build());
        captionTracksVtt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/webvtt/TOS-ru.vtt")
                .language("ru")
                .mimeType(MimeTypeSubtitle.TEXT_VTT.getValue())
                .build());
        captionTracksVtt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/webvtt/TOS-no.vtt")
                .language("no")
                .mimeType(MimeTypeSubtitle.TEXT_VTT.getValue())
                .build());
        captionTracksVtt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/webvtt/TOS-JP.vtt")
                .language("jp")
                .mimeType(MimeTypeSubtitle.TEXT_VTT.getValue())
                .build());
        captionTracksVtt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Indonesian.vtt")
                .language("in")
                .mimeType(MimeTypeSubtitle.TEXT_VTT.getValue())
                .build());
        captionTracksVtt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Persian.vtt")
                .language("pe")
                .mimeType(MimeTypeSubtitle.TEXT_VTT.getValue())
                .build());
        captionTracksVtt.add(new Caption.Builder()
                .file(URL_INNO+"cdn/videos/tears_of_steel/subtitle/webvtt/TOS-CH.vtt")
                .language("ch")
                .mimeType(MimeTypeSubtitle.TEXT_VTT.getValue())
                .build());
        subOutVTT.add(new PlaylistItem.Builder()
                .title("WebVTT All Selection")
                .tracks(captionTracksVtt)
                .file(videoSourceSubtitle)
                .build());

        subListVtt.add(subOutVTT);
        listChild.put(listHeader.get(8), subListVtt);

    }

    @Override
    public void onProgressInnoPlayerKeyCheck(boolean b) {
        Log.d(TAG, "progress checking key : " + b);

    }

    @Override
    public void onFinishInnoPlayerKeyCheck(Feature feature, InnoError innoError) {
        Log.d(TAG, "feature is null : " + (feature == null));

        if (feature != null) {
            boolean canPlayContent = feature.getCanPlayContent();
            boolean showAds = feature.getCanShowAds();
            boolean playDrmContent = feature.getCanPlayDRMContent();
            Log.e("ClientApp", "canPlayContent: " + canPlayContent);

        }

    }
}