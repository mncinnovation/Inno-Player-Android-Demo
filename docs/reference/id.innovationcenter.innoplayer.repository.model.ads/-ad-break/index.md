[reference](../../index.md) / [id.innovationcenter.innoplayer.core.repository.model.ads](../index.md) / [AdBreak](./index.md)

# AdBreak

`class AdBreak : `[`Serializable`](https://developer.android.com/reference/java/io/Serializable.html)

An ad break

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AdBreak(offset: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, source: `[`AdSource`](../-ad-source/index.md)`, tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, adType: `[`AdType`](../-ad-type/index.md)`)`<br>An ad break |

### Properties

| Name | Summary |
|---|---|
| [adType](ad-type.md) | `var adType: `[`AdType`](../-ad-type/index.md)<br>type of ad breaks |
| [offset](offset.md) | `var offset: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Is an position of ads, value of offset is PRE or POST or for midle ads with time in second 30 for 30 seconds after playing video for example |
| [source](source.md) | `var source: `[`AdSource`](../-ad-source/index.md)<br>Is an AdSource |
| [tag](tag.md) | `var tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Url tag of ad |
