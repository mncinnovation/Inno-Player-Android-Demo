[reference](../../../index.md) / [id.innovationcenter.innoplayer.events.listeners](../../index.md) / [VideoPlayer](../index.md) / [PlayerCallback](./index.md)

# PlayerCallback

`interface PlayerCallback`

Interface for alerting caller of major video events.

### Functions

| Name | Summary |
|---|---|
| [onCompleted](on-completed.md) | `abstract fun onCompleted(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when the current video has completed playback to the end of the video. |
| [onError](on-error.md) | `abstract fun onError(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when an error occurs during video playback. |
| [onPause](on-pause.md) | `abstract fun onPause(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when the current video pauses playback. |
| [onPlay](on-play.md) | `abstract fun onPlay(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when the current video starts playing from the beginning. |
| [onResume](on-resume.md) | `abstract fun onResume(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called when the current video resumes playing from a paused state. |
