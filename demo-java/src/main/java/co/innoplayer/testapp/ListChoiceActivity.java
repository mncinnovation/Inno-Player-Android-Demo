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
import testapp.BuildConfig;
import testapp.databinding.ActivityListChoiceBinding;


public class ListChoiceActivity extends AppCompatActivity implements InnoPlayerSDK.KeyCheckInitialListener {
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
                "Inno Player Demo", BuildConfig.KEY_INNOPLAYER,
                this
        );

        innoPlayerSDK.initMncAnalytics(
                this,
                BuildConfig.KEY_MNC_ANALYTICS,
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
        PlaylistItem playlistItemLive1 = new PlaylistItem();
        PlaylistItem playlistItemLive2 = new PlaylistItem();
        playlistItemLive1.setTitle("HLS");
        playlistItemLive1.setFile("https://tv.balkanweb.com/news24/livestream/playlist.m3u8");
        playlistItemLive1.setTitle("DASH");
        playlistItemLive1.setFile("https://livesim.dashif.org/livesim/ato_10/testpic_2s/Manifest.mpd");

        live1.add(playlistItemLive1);
        live2.add(playlistItemLive2);
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
        PlaylistItem playlistItemMp3 = new PlaylistItem();
        playlistItemMp3.setTitle("MP3");
        playlistItemMp3.setFile("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
        playlistItemMp3.setImage("https://upload.wikimedia.org/wikipedia/commons/5/5e/MP3_logo.png");
        playlistItemMp3.setDescription("SoundHelix Sound");
        audio1.add(playlistItemMp3);
        PlaylistItem playlistItemAac = new PlaylistItem();
        playlistItemAac.setTitle("AAC");
        playlistItemAac.setDescription("AAC Sample Sound");
        playlistItemAac.setFile(URL_INNO + "cdn/audios/ES_Cocoona.aac");
        audio2.add(playlistItemAac);
        PlaylistItem playlistItemOgg = new PlaylistItem();
        playlistItemOgg.setFile(URL_INNO + "cdn/audios/ES_Cocoona.ogg");
        playlistItemOgg.setTitle("OGG Vorbis");
        playlistItemOgg.setDescription("OGG Vorbis Sample Sound");
        audio3.add(playlistItemOgg);
        PlaylistItem playlistItemOpus = new PlaylistItem();
        playlistItemOpus.setDescription("Opus Sample Sound");
        playlistItemOpus.setTitle("Opus");
        playlistItemOpus.setFile(URL_INNO + "cdn/audios/ES_Cocoona.opus");
        audio4.add(playlistItemOpus);
        PlaylistItem playlistItemBbc = new PlaylistItem();
        playlistItemBbc.setTitle("Internet Radio\nBBC Media");
        playlistItemBbc.setDescription("Internet Radio");
        playlistItemBbc.setFile(URL_INNO + "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio1_mf_q");
        audio5.add(playlistItemBbc);

        audioSample.add(audio1);
        audioSample.add(audio2);
        audioSample.add(audio3);
        audioSample.add(audio4);
        audioSample.add(audio5);
        listChild.put(listHeader.get(2), audioSample);

        listHeader.add("Playlist");
        List<PlaylistItem> playlist1 = new ArrayList<>();
        List<List<PlaylistItem>> playlistSample = new ArrayList<>();
        PlaylistItem playlistItemPreview = new PlaylistItem();
        playlistItemPreview.setTitle("Preview Thumbnails");
        playlistItemPreview.setFile("https://bitdash-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd");
        playlistItemPreview.setTracks(thumbnailTrack);
        PlaylistItem playlistItemMpegDash = new PlaylistItem();
        playlistItemMpegDash.setFile("https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.mpd");
        playlistItemMpegDash.setTitle("MPEG-DASH");
        playlist1.add(playlistItemPreview);
        playlist1.add(playlistItemMpegDash);
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
        PlaylistItem playlistItemAds1 = new PlaylistItem();
        playlistItemAds1.setTitle("Linear Preload Ad Skipable");
        playlistItemAds1.setFile("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv");
        playlistItemAds1.setAdSchedule(listAds);
        playlistItemAds1.setCategory("ads");
        playlistItemAds1.setAdTypeSource(AdTypeSource.VAST);
        ima0.add(playlistItemAds1);

        List<AdBreak> listAds2 = new ArrayList<>();
        listAds2.add(new AdBreak(null, null, "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=", null));
        PlaylistItem playlistItemAds2 = new PlaylistItem();
        playlistItemAds2.setTitle("Linear Preload Ad");
        playlistItemAds2.setAdSchedule(listAds2);
        playlistItemAds2.setFile("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv");
        playlistItemAds2.setAdTypeSource(AdTypeSource.VAST);
        playlistItemAds2.setMediaId("ads-linearskipable");
        playlistItemAds2.setCategory("ads");

        ima1.add(playlistItemAds2);

        List<AdBreak> listAds3 = new ArrayList<>();
        listAds3.add(new AdBreak(null, null, "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator=", AdType.NONLINEAR));

        PlaylistItem playlistItemAds3 = new PlaylistItem();
        playlistItemAds3.setTitle("Non Linear Ad");
        playlistItemAds3.setAdSchedule(listAds3);
        playlistItemAds3.setFile("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv");
        playlistItemAds3.setAdTypeSource(AdTypeSource.VAST);
        playlistItemAds3.setMediaId("ads-nonlinear");
        playlistItemAds3.setCategory("ads");

        ima2.add(playlistItemAds3);

        List<AdBreak> listAds4 = new ArrayList<>();
        listAds4.add(new AdBreak("PRE", AdSource.IMA, "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=", AdType.LINEAR));
        List<String> adMid = new ArrayList<>();
        adMid.add("20");
        for (int i = 0; i < adMid.size(); i++) {
            listAds4.add(new AdBreak(adMid.get(i), AdSource.IMA, "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=", AdType.LINEAR));
        }
        listAds4.add(new AdBreak("POST", AdSource.IMA, "https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.co&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=", AdType.LINEAR));

        PlaylistItem playlistItemAds4 = new PlaylistItem();
        playlistItemAds4.setTitle("Scheduled linear preroll, non-linear midroll, linear postroll");
        playlistItemAds4.setAdSchedule(listAds4);
        playlistItemAds4.setFile("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv");
        playlistItemAds4.setAdTypeSource(AdTypeSource.VMAP);
        playlistItemAds4.setMediaId("ads-schedule");
        playlistItemAds4.setCategory("ads");

        ima3.add(playlistItemAds4);


        List<AdBreak> listAds5 = new ArrayList<>();
        listAds5.add(new AdBreak(null, null, "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/"
                + "ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp"
                + "&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite"
                + "%26sample_ar%3Dpremidpost&cmsid=496&vid=short_onecue&correlator=", AdType.NONLINEAR));
        PlaylistItem playlistItemAds5 = new PlaylistItem();
        playlistItemAds5.setTitle("VMAP linear preroll, non-linear midroll, linear postroll");
        playlistItemAds5.setAdSchedule(listAds5);
        playlistItemAds5.setFile("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv");
        playlistItemAds5.setAdTypeSource(AdTypeSource.VMAP);
        playlistItemAds5.setMediaId("ads-vmap");
        playlistItemAds5.setCategory("ads");

        ima4.add(playlistItemAds5);

        List<AdBreak> listAds6 = new ArrayList<>();
        listAds6.add(new AdBreak(null, null, "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinearvpaid2js&correlator=", AdType.NONLINEAR));

        PlaylistItem playlistItemAds6 = new PlaylistItem();
        playlistItemAds6.setTitle("VPAID");
        playlistItemAds6.setAdSchedule(listAds6);
        playlistItemAds6.setFile("https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv");
        playlistItemAds6.setAdTypeSource(AdTypeSource.VPAID);
        playlistItemAds6.setMediaId("ads-vpaid");
        playlistItemAds6.setCategory("ads");

        ima5.add(playlistItemAds6);

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

        PlaylistItem playlistItemHLSEmbedded = new PlaylistItem();
        playlistItemHLSEmbedded.setFile("https://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/bipbop_16x9_variant.m3u8");
        playlistItemHLSEmbedded.setTitle("CEA-608 - HLS (Embedded in stream)");
        subsEmbed1.add(playlistItemHLSEmbedded);

        PlaylistItem playlistItemMpegEmbedded = new PlaylistItem();
        playlistItemMpegEmbedded.setFile("https://vm2.dashif.org/dash/vod/testpic_2s/cea608.mpd");
        playlistItemMpegEmbedded.setTitle("CEA-608 - MPEG-DASH (Embedded in stream)");
        subsEmbed2.add(playlistItemMpegEmbedded);

        PlaylistItem playlistItemTTMLEmbedded = new PlaylistItem();
        playlistItemTTMLEmbedded.setFile("https://irtdashreference-i.akamaihd.net/dash/live/901161/bfs/manifestARD.mpd");
        playlistItemTTMLEmbedded.setTitle("TTML (Embedded in stream)");
        subsEmbed3.add(
                playlistItemTTMLEmbedded
        );

        PlaylistItem playlistItemWebVTTHLSEmbedded = new PlaylistItem();
        playlistItemWebVTTHLSEmbedded.setFile("https://bitmovin-a.akamaihd.net/content/sintel/hls/playlist.m3u8");
        playlistItemWebVTTHLSEmbedded.setTitle("WebVTT-HLS (Embedded in stream)");
        subsEmbed4.add(playlistItemWebVTTHLSEmbedded);

        PlaylistItem playlistItemWebVTTDashEmbedded = new PlaylistItem();
        playlistItemWebVTTDashEmbedded.setFile("https://bitmovin-a.akamaihd.net/content/sintel/sintel.mpd");
        playlistItemWebVTTDashEmbedded.setTitle("WebVTT-DASH (Embedded in stream)");
        subsEmbed5.add(playlistItemWebVTTDashEmbedded);

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
        captionTracks.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/ttml/TOS-en.ttml", CaptionType.CAPTIONS, "English", MimeTypeSubtitle.TTML.getValue(), "en", true));
        captionTracks.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/ttml/TOS-es.ttml", CaptionType.CAPTIONS, "Espanol", MimeTypeSubtitle.TTML.getValue(), "es", false));
        captionTracks.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/ttml/TOS-fr-Goofy.ttml", CaptionType.CAPTIONS, "France", MimeTypeSubtitle.TTML.getValue(), "fr", false));
        captionTracks.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/ttml/TOS-de.ttml", CaptionType.CAPTIONS, "Denmark", MimeTypeSubtitle.TTML.getValue(), "de", false));
        captionTracks.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/ttml/TOS-it.ttml", CaptionType.CAPTIONS, "Italy", MimeTypeSubtitle.TTML.getValue(), "it", false));
        captionTracks.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/ttml/TOS-ru.ttml", CaptionType.CAPTIONS, "Rusia", MimeTypeSubtitle.TTML.getValue(), "ru", false));
        captionTracks.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/ttml/TOS-no.ttml", CaptionType.CAPTIONS, "Norwegia", MimeTypeSubtitle.TTML.getValue(), "no", false));
        captionTracks.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/ttml/TOS-JP.ttml", CaptionType.CAPTIONS, "Japan", MimeTypeSubtitle.TTML.getValue(), "jp", false));
        captionTracks.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/ttml/TOS-Indonesian.ttml", CaptionType.CAPTIONS, "Indonesian", MimeTypeSubtitle.TTML.getValue(), "in", false));
        captionTracks.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/ttml/TOS-Persian.ttml", CaptionType.CAPTIONS, "Persian", MimeTypeSubtitle.TTML.getValue(), "pe", false));
        captionTracks.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/ttml/TOS-CH.ttml", CaptionType.CAPTIONS, "Chinese", MimeTypeSubtitle.TTML.getValue(), "ch", false));

        PlaylistItem playlistItemTTMLSub = new PlaylistItem();
        playlistItemTTMLSub.setTitle("TTML All Selection");
        playlistItemTTMLSub.setTracks(captionTracks);
        playlistItemTTMLSub.setFile(videoSourceSubtitle);

        subOutTTML.add(playlistItemTTMLSub);

        subList.add(subOutTTML);
        listChild.put(listHeader.get(7), subList);

        listHeader.add("Subtitles SRT Out of stream");
        List<PlaylistItem> subOutSRT = new ArrayList<>();
        List<List<PlaylistItem>> subListSrt = new ArrayList<>();

        List<Caption> captionTracksSrt = new ArrayList<>();

        captionTracksSrt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/srt/TOS-en.srt", CaptionType.CAPTIONS, "English", MimeTypeSubtitle.APPLICATION_SUBRIP.getValue(), "ch", true));
        captionTracksSrt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/srt/TOS-es.srt", CaptionType.CAPTIONS, "Espanol", MimeTypeSubtitle.APPLICATION_SUBRIP.getValue(), "es", false));
        captionTracksSrt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/srt/TOS-fr-Goofy.srt", CaptionType.CAPTIONS, "France", MimeTypeSubtitle.APPLICATION_SUBRIP.getValue(), "es", false));
        captionTracksSrt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/srt/TOS-de.srt", CaptionType.CAPTIONS, "Denmark", MimeTypeSubtitle.APPLICATION_SUBRIP.getValue(), "de", false));
        captionTracksSrt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/srt/TOS-it.srt", CaptionType.CAPTIONS, "Italy", MimeTypeSubtitle.APPLICATION_SUBRIP.getValue(), "it", false));
        captionTracksSrt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/srt/TOS-ru.srt", CaptionType.CAPTIONS, "Rusia", MimeTypeSubtitle.APPLICATION_SUBRIP.getValue(), "ru", false));
        captionTracksSrt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/srt/TOS-no.srt", CaptionType.CAPTIONS, "Norwegia", MimeTypeSubtitle.APPLICATION_SUBRIP.getValue(), "no", false));
        captionTracksSrt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/srt/TOS-JP.srt", CaptionType.CAPTIONS, "Japan", MimeTypeSubtitle.APPLICATION_SUBRIP.getValue(), "jp", false));
        captionTracksSrt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/srt/TOS-Indonesian.srt", CaptionType.CAPTIONS, "Indonesian", MimeTypeSubtitle.APPLICATION_SUBRIP.getValue(), "in", false));
        captionTracksSrt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/srt/TOS-Persian.srt", CaptionType.CAPTIONS, "Persian", MimeTypeSubtitle.APPLICATION_SUBRIP.getValue(), "pe", false));
        captionTracksSrt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/srt/TOS-CH.srt", CaptionType.CAPTIONS, "Chinese", MimeTypeSubtitle.APPLICATION_SUBRIP.getValue(), "ch", false));

        PlaylistItem playlistItemSrtSub = new PlaylistItem();
        playlistItemSrtSub.setTracks(captionTracksSrt);
        playlistItemSrtSub.setTitle("SRT All Selection");
        playlistItemSrtSub.setFile(videoSourceSubtitle);

        subOutSRT.add(playlistItemSrtSub);

        subListSrt.add(subOutSRT);
        listChild.put(listHeader.get(8), subListSrt);

        listHeader.add("Subtitles SRT Out of stream");
        List<PlaylistItem> subOutVTT = new ArrayList<>();
        List<List<PlaylistItem>> subListVtt = new ArrayList<>();

        List<Caption> captionTracksVtt = new ArrayList<>();
        captionTracksVtt.add(new Caption("https://tears-of-steel-subtitles.s3.amazonaws.com/tears-en.vtt", CaptionType.CAPTIONS, "English", MimeTypeSubtitle.TEXT_VTT.getValue(), "en", true));
        captionTracksVtt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/webvtt/TOS-es.vtt", CaptionType.CAPTIONS, "Espanol", MimeTypeSubtitle.TEXT_VTT.getValue(), "es", false));
        captionTracksVtt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/webvtt/TOS-fr-Goofy.vtt", CaptionType.CAPTIONS, "France", MimeTypeSubtitle.TEXT_VTT.getValue(), "fr", false));
        captionTracksVtt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/webvtt/TOS-de.vtt", CaptionType.CAPTIONS, "Denmark", MimeTypeSubtitle.TEXT_VTT.getValue(), "de", false));
        captionTracksVtt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/webvtt/TOS-it.vtt", CaptionType.CAPTIONS, "Italy", MimeTypeSubtitle.TEXT_VTT.getValue(), "it", false));
        captionTracksVtt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/webvtt/TOS-ru.vtt", CaptionType.CAPTIONS, "Rusia", MimeTypeSubtitle.TEXT_VTT.getValue(), "ru", false));
        captionTracksVtt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/webvtt/TOS-no.vtt", CaptionType.CAPTIONS, "Norwegia", MimeTypeSubtitle.TEXT_VTT.getValue(), "no", false));
        captionTracksVtt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/webvtt/TOS-JP.vtt", CaptionType.CAPTIONS, "Japan", MimeTypeSubtitle.TEXT_VTT.getValue(), "jp", false));
        captionTracksVtt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Indonesian.vtt", CaptionType.CAPTIONS, "Indonesian", MimeTypeSubtitle.TEXT_VTT.getValue(), "in", false));
        captionTracksVtt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Persian.vtt", CaptionType.CAPTIONS, "Persian", MimeTypeSubtitle.TEXT_VTT.getValue(), "pe", false));
        captionTracksVtt.add(new Caption(URL_INNO + "cdn/videos/tears_of_steel/subtitle/webvtt/TOS-CH.vtt", CaptionType.CAPTIONS, "Chinese", MimeTypeSubtitle.TEXT_VTT.getValue(), "ch", false));

        PlaylistItem playlistItemWebVttSub = new PlaylistItem();
        playlistItemWebVttSub.setFile(videoSourceSubtitle);
        playlistItemWebVttSub.setTitle("WebVTT All Selection");
        playlistItemWebVttSub.setTracks(captionTracksVtt);

        subOutVTT.add(playlistItemWebVttSub);

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