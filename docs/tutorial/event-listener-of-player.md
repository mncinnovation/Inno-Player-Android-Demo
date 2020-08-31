# Event Listener of InnoPlayer

InnoPlayer View has command and expenditure operations that can be customized by the developer.

Event Listener of InnoPlayer are as follows:
* ``addOnErrorListener(ErrorEventListener)`` : event listener that will return an error when InnoPlayer View failed to process media source and has error

Example
```kotlin
        innoPlayerView.addOnErrorListener(object : InnoPlayerView.ErrorEventListener {
            override fun onErrorListener(error: LidoPlaybackException?) {
                Log.d(TAG, "isPlayerErrorMsg: ${error?.message}")
            }
        })
```
See more detail about addOnErrorListener at [addOnErrorListener reference](../reference/id.innovationcenter.innoplayer/-inno-player-view/add-on-error-listener.md).

* ``addOnFullscreenListener(OnFullscreenListener)`` : event listener that will have function to return change of fullscreen player state.

Example
```kotlin
        innoPlayerView.addOnFullscreenListener(object : VideoPlayerEvents.OnFullscreenListener {
            override fun onFullscreen(isFullscreen: Boolean) {
                Log.e(TAG, "isFullscreenMode: $isFullscreen")
            }
        })
```
See more detail about addOnFullscreenListener at [addOnFullscreenListener reference](../reference/id.innovationcenter.innoplayer/-inno-player-view/add-on-fullscreen-listener.md).

* ``addOnBufferChangeListener(VideoPlayerEvents.OnErrorListener)`` : event listener that will have function to return change of the fullscreen the player state.

Example
```kotlin
        innoPlayerView.addOnBufferChangeListener(object : InnoPlayerView.OnBufferChangeListener {
            override fun onBufferChange(isLoading: Boolean) {
                Log.d(TAG, "isPlayerLoading: $isLoading")
            }
        })
```
See more detail about addOnFullscreenListener at [addOnBufferChangeListener reference](../reference/id.innovationcenter.innoplayer/-inno-player-view/add-on-buffer-change-listener.md).


* ``addOnDisplayClickListener(DisplayClickListener)`` : event listener that will have function to call when diplay of InnoPlayer View clicked by user.

Example
```kotlin
        innoPlayerView.addOnDisplayClickListener(object : InnoPlayerView.DisplayClickListener {
            override fun onDisplayClick() {
                Log.d(TAG, "isDisplayClicked")
            }
        })
```
See more detail about addOnDisplayClickListener at [addOnDisplayClickListener reference](../reference/id.innovationcenter.innoplayer/-inno-player-view/add-on-display-click-listener.md).

* ``addOnPlayerStateEndListener(PlayerStateEndListener)`` : event listener that will have function to return play when ready of player when player state changed to ended.

Example
```kotlin
        innoPlayerView.addOnPlayerStateEndListener(object : InnoPlayerView.PlayerStateEndListener {
            override fun onPlayerStateEnd(playWhenReady: Boolean) {
                Log.d(TAG, "playerStateEnd: $playWhenReady")
            }
        })
```
See more detail about addOnPlayerStateEndListener at [addOnPlayerStateEndListener reference](../reference/id.innovationcenter.innoplayer/-inno-player-view/add-on-player-state-end-listener.md).

* ``addOnTracksChangeListener`(TracksChangeListener)`` : event listener that will have function to call when track of list has changed.

In here you able to check player has next media to play or not by calling playerHasNext() function.
Example
```kotlin
        innoPlayerView.addOnTracksChangeListener(object: InnoPlayerView.TracksChangeListener{
            override fun onTracksChange() {
                //add logic to do when tracks media has changed
                //example for enable or disable button next in your custom ui controller, need to check player has next media to play or not by calling playerHasNext() function
                btnplayer_next_center?.isEnabled = playerHasNext()
                Log.d(TAG, "trackHasChanged")
            }
        })
```
See more detail about addOnTracksChangeListener at [addOnTracksChangeListener reference](../reference/id.innovationcenter.innoplayer/-inno-player-view/add-on-tracks-change-listener.md).

* ``addOnSeekListener(OnSeekListener)`` : event listener that will have function to detect seek progress of player in default ui controllor move.

Example
```kotlin
       innoPlayerView.addOnSeekListener(object : InnoPlayerView.TimebarScrubListener{
            override fun onSeek(seekEvent: SeekEvent) {
                Log.d(TAG, "Seek controller start at ")
            }
        })
```
See more detail about addOnSeekListener at [addOnSeekListener reference](../reference/id.innovationcenter.innoplayer/-inno-player-view/add-on-seek-listener.md).

For more detail about event listener, please open [listener reference](../reference/id.innovationcenter.innoplayer.events.listeners/index.md)