[reference](../../index.md) / [id.innovationcenter.innoplayer.events](../index.md) / [SeekEvent](./index.md)

# SeekEvent

`class SeekEvent`

Payload that accompanies the onSeek() event

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SeekEvent(position: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, offset: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`)`<br>Payload that accompanies the onSeek() event |

### Properties

| Name | Summary |
|---|---|
| [offset](offset.md) | `var offset: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>The position that has been requested to seek to (in miliseconds). |
| [position](position.md) | `var position: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>The position of the player before the player seeks (in ,miliseconds). |
