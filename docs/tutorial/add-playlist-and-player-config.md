# Add Playlist and Configuration LidoPlayerView

InnoPlayer already have class of ``PlaylistItem`` and ``PlayerConfig``.

PlaylistItem is class that have model data of media source information.
And PlayerConfig is an class that collect list of playlist item, and configure it.

PlaylistItem has attributes:

* ``file`` : Is media source url in string format, required to play media source.
* ``image`` : Image of media source, that can used to initial load of video.
* ``title``: Title of media source.
* ``description`` : Description of media source.
* ``subtitleUri`` : An source of subtitle of video that what to play with.
* ``subtitleMimeType`` : Is mime type of subtitle.
* ``subtitleLanguage`` : Is language of subtitle.

See more attributes PlaylistItem at [reference PlaylistItem InnoPlayer](../reference/id.innovationcenter.lidoplayer.repository.model.playlist/-playlist-item/index.md).

Create object of class PlayerConfig with list of playlist items as parameter.
And than call ``innoPlayerView.config(playerConfig, mediaSourceUtils, activity)``

Below is complete example how to config the player:

```kotlin
    lateinit var playerConfig: PlayerConfig
    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         LidoPlayerSDK().init(
           applicationContext, this,
           "ExoPlayerDemo", KEY_LIDO_PLAYER,
           this
         )

         setContentView(R.layout.activity_main)
         ...
         val itemPlaylist = PlaylistItem(
            file = "https://html5demos.com/assets/dizzy.mp4",
            image = "https://suaranasional.com/wp-content/uploads/2018/07/0381123f143a69be9bda08702ff858f72017257697.jpg",
            subtitleUri = "https://storage.googleapis.com/exoplayer-test-media-1/ttml/netflix_ttml_sample.xml",
            subtitleMimeType = "application/ttml+xml",
            subtitleLanguage = "en"
         )
         val itemPlaylist2 = PlaylistItem(file = https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_16x9/bipbop_16x9_variant.m3u8)

         val playlists = mutableListOf<PlaylistItem>()
         playlists.add(itemPlaylist)
         playlists.add(itemPlaylist2)

         playerConfig = PlayerConfig(playlists)
         
         val mediaSourceUtils = MediaSourceUtils(this)
         
         innoPlayerView.config(playerConfig, mediaSourceUtils, this)
         ...
    }
```
