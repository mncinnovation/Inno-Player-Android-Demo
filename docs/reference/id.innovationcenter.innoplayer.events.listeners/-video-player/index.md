[reference](../../index.md) / [id.innovationcenter.innoplayer.events.listeners](../index.md) / [VideoPlayer](./index.md)

# VideoPlayer

`interface VideoPlayer`

Interface definition for controlling video playback.

### Types

| Name | Summary |
|---|---|
| [PlayerCallback](-player-callback/index.md) | `interface PlayerCallback`<br>Interface for alerting caller of major video events. |

### Functions

| Name | Summary |
|---|---|
| [addPlayerCallback](add-player-callback.md) | `abstract fun addPlayerCallback(callback: `[`PlayerCallback`](-player-callback/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Provide the player with a callback for major video events (pause, complete, resume, etc). |
| [disablePlaybackControls](disable-playback-controls.md) | `abstract fun disablePlaybackControls(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Prevent the media controller (playback controls) from appearing. |
| [enablePlaybackControls](enable-playback-controls.md) | `abstract fun enablePlaybackControls(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Allow the media controller (playback controls) to appear when appropriate. |
| [getCurrentPosition](get-current-position.md) | `abstract fun getCurrentPosition(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the playback progress state (milliseconds) of the current video. |
| [getDuration](get-duration.md) | `abstract fun getDuration(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Get the total length of the currently loaded video in milliseconds. |
| [getVolume](get-volume.md) | `abstract fun getVolume(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Gets the current volume. Range is [0-100]. |
| [pause](pause.md) | `abstract fun pause(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Pause the currently loaded video. |
| [play](play.md) | `abstract fun play(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Play the currently loaded video from its current position. |
| [removePlayerCallback](remove-player-callback.md) | `abstract fun removePlayerCallback(callback: `[`PlayerCallback`](-player-callback/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Remove a player callback from getting notified on video events. |
| [resume](resume.md) | `abstract fun resume(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Resume the currently loaded video. |
| [seekTo](seek-to.md) | `abstract fun seekTo(videoPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Progress the currently loaded video to the given position (milliseconds). |
| [setVideoPath](set-video-path.md) | `abstract fun setVideoPath(videoUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set the URL or path of the video to play. |
| [stopPlayback](stop-playback.md) | `abstract fun stopPlayback(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Stop playing the currently loaded video. |
