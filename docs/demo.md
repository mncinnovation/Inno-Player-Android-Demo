# Demo test file

1. Basic Streaming
   - HLS
     ```https://lidoplayer.innovationcenter.id/cdn/videos/la_chute_d_une_plume/index.m3u8```
   - MPEG-DASH
     ```"https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.mpd"```
   - HLS (CMAF)
     ```https://lidoplayer.innovationcenter.id/cdn/videos/cosmos-laundromat/cosmos_laundromat_h264_master.m3u8```
   - MPEG-DASH (CMAF)
     ```https://lidoplayer.innovationcenter.id/cdn/videos/cosmos-laundromat/cosmos_laundromat_h264.mpd```
2. Live Streaming
   - HLS
     ```https://akamai-axtest.akamaized.net/routes/lapd-v1-acceptance/www_c4/Manifest.m3u8```
   - MPEG-DASH
     ```https://akamai-axtest.akamaized.net/routes/lapd-v1-acceptance/www_c4/Manifest.mpd```
3. Audio
   - MP3
     ```https://lidoplayer.innovationcenter.id/cdn/audios/ES_Cocoona.mp3```
   - AAC
     ```https://lidoplayer.innovationcenter.id/cdn/audios/ES_Cocoona.aac```
   - OGG Vorbis
     ```https://lidoplayer.innovationcenter.id/cdn/audios/ES_Cocoona.ogg```
   - Opus
     ```https://lidoplayer.innovationcenter.id/cdn/audios/ES_Cocoona.opus```
   - Internet Radio
     
4. Playlist
5. Advertising
   - Google IMA SDK
     - Linear preroll ad
       ```https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.id&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=```
     - Non-linear ad
       ```https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator=```
     - Scheduled linear preroll, non-linear midroll, linear postroll
       - preroll -> ```https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=```
       - midroll -> ```https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinear&correlator=```
       - postroll -> ```https://pubads.g.doubleclick.net/gampad/ads?iu=/21705426382/1.0&description_url=http%3A%2F%2Finnovationcenter.id&tfcd=0&npa=0&sz=400x300%7C640x480&gdfp_req=1&output=vast&unviewed_position_start=1&env=vp&impl=s&correlator=```
     - VMAP linear preroll, non-linear midroll, linear postroll
       ```https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpostpodbumper&cmsid=496&vid=short_onecue&correlator=```
     - VPAID
       ```https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinearvpaid2js&correlator=```
6. DRM
   - Widevine
     - video -> ```https://storage.googleapis.com/shaka-demo-assets/sintel-widevine/dash.mpd```
     - license server -> ```https://cwip-shaka-proxy.appspot.com/no_auth```
   - Playready (for Android TV)
     - video -> ```"https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel-dash-playready.ism/.mpd"```
     - license server -> ```https://test.playready.microsoft.com/service/rightsmanager.asmx?PlayRight=1&UseSimpleNonPersistentLicense=1```
   - Multi DRM
     - video -> ```https://amssamples.streaming.mediaservices.windows.net/622b189f-ec39-43f2-93a2-201ac4e31ce1/BigBuckBunny.ism/manifest(format=mpd-time-csf)```
     - widevine license server -> ```https://amssamples.keydelivery.mediaservices.windows.net/Widevine/?KID=1ab45440-532c-4399-94dc-5c5ad9584bac```
     - playready license server -> ```https://amssamples.keydelivery.mediaservices.windows.net/PlayReady/```
7. Subtitle
   - Embedded in stream
     - CEA-608
       - HLS
         ```https://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/bipbop_16x9_variant.m3u8```
       - MPEG-DASH
         ```https://vm2.dashif.org/dash/vod/testpic_2s/cea608.mpd```
     - CEA-708
     - TTML
       ```https://irtdashreference-i.akamaihd.net/dash/live/901161/bfs/manifestARD.mpd```
     - WebVTT
       - HLS
         ```https://bitmovin-a.akamaihd.net/content/sintel/hls/playlist.m3u8```
       - MPEG-DASH
         ```https://bitmovin-a.akamaihd.net/content/sintel/sintel.mpd```
   - Out of stream
     - TTML
       - video -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/tears_of_steel.mpd```
       - subtitle English -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/ttml/TOS-en.ttml```
       - subtitle Spanish -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/ttml/TOS-es.ttml```
       - subtitle French -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/ttml/TOS-fr-Goofy.ttml```
       - subtitle Dutch -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/ttml/TOS-de.ttml```
       - subtitle Italian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/ttml/TOS-it.ttml```
       - subtitle Russian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/ttml/TOS-ru.ttml```
       - subtitle Norwegian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/ttml/TOS-no.ttml```
       - subtitle Japanese -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/ttml/TOS-JP.ttml```
       - subtitle Indonesian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/ttml/TOS-Indonesian.ttml```
       - subtitle Persian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/ttml/TOS-Persian.ttml```
       - subtitle Chinese -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/ttml/TOS-CH.ttml```
     - SRT
       - video -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/tears_of_steel.mpd```
       - subtitle English -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/srt/TOS-en.srt```
       - subtitle Spanish -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/srt/TOS-es.srt```
       - subtitle French -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/srt/TOS-fr-Goofy.srt```
       - subtitle Dutch -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/srt/TOS-de.srt```
       - subtitle Italian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/srt/TOS-it.srt```
       - subtitle Russian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/srt/TOS-ru.srt```
       - subtitle Norwegian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/srt/TOS-no.srt```
       - subtitle Japanese -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/srt/TOS-JP.srt```
       - subtitle Indonesian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/srt/TOS-Indonesian.srt```
       - subtitle Persian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/srt/TOS-Persian.srt```
     - WebVTT
       - video -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/tears_of_steel.mpd```
       - subtitle English -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/webvtt/TOS-en.vtt```
       - subtitle Spanish -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/webvtt/TOS-es.vtt```
       - subtitle French -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/webvtt/TOS-fr-Goofy.vtt```
       - subtitle Dutch -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/webvtt/TOS-de.vtt```
       - subtitle Italian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/webvtt/TOS-it.vtt```
       - subtitle Russian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/webvtt/TOS-ru.vtt```
       - subtitle Norwegian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/webvtt/TOS-no.vtt```
       - subtitle Japanese -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/webvtt/TOS-JP.vtt```
       - subtitle Indonesian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Indonesian.vtt```
       - subtitle Persian -> ```https://lidoplayer.innovationcenter.id/cdn/videos/tears_of_steel/subtitle/webvtt/TOS-Persian.vtt```
