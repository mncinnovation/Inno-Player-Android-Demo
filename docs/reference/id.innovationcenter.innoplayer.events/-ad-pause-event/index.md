[reference](../../index.md) / [id.innovationcenter.innoplayer.events](../index.md) / [AdPauseEvent](./index.md)

# AdPauseEvent

`class AdPauseEvent`

Payload that accompanies the onAdPause() event.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AdPauseEvent(creativeType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, newState: PlayerState? = null, oldState: PlayerState? = null)`<br>Payload that accompanies the onAdPause() event. |

### Properties

| Name | Summary |
|---|---|
| [creativeType](creative-type.md) | `var creativeType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>VAST-only The MIME type of the current media file specified in the VAST XML |
| [newState](new-state.md) | `var newState: PlayerState?`<br>The new state of the player. This should be "paused". |
| [oldState](old-state.md) | `var oldState: PlayerState?`<br>The state of the player prior to ad pause |
| [tag](tag.md) | `var tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>The URL of the ad tag that is currently playing. |
