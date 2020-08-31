[reference](../../index.md) / [id.innovationcenter.innoplayer.events](../index.md) / [AdEvent](./index.md)

# AdEvent

`open class AdEvent`

Parent class of AdEvent

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AdEvent(client: `[`AdSource`](../../id.innovationcenter.innoplayer.media.ads/-ad-source/index.md)`? = null, creativeType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null)`<br>Parent class of AdEvent |

### Properties

| Name | Summary |
|---|---|
| [client](client.md) | `var client: `[`AdSource`](../../id.innovationcenter.innoplayer.media.ads/-ad-source/index.md)`?`<br>The client that is currently being used. |
| [creativeType](creative-type.md) | `var creativeType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>The type of ad that was skipped. |
| [tag](tag.md) | `var tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>The URL of the ad tag that was skipped. |

### Inheritors

| Name | Summary |
|---|---|
| [AdClickEvent](../-ad-click-event/index.md) | `class AdClickEvent : `[`AdEvent`](./index.md)<br>Payload that accompanies the onAdClick() event. |
| [AdCompleteEvent](../-ad-complete-event/index.md) | `class AdCompleteEvent : `[`AdEvent`](./index.md)<br>Payload that accompanies the onAdComplete() event |
| [AdSkippedEvent](../-ad-skipped-event/index.md) | `class AdSkippedEvent : `[`AdEvent`](./index.md)<br>Payload that accompanies the onAdSkipped() event |
