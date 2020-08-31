[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoPlayerView](index.md) / [pause](./pause.md)

# pause

`fun pause(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Pauses playback, changing the state of Inno Player from playing to paused. Calling pause() while media is already paused does nothing. Note: When using this method with a non-DVR live stream, the player will appear paused. After this, when play() is called, the stream restarts from the live edge

