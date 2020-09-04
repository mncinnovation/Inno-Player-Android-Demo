[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoDefaultTimeBar](index.md) / [setAdGroupTimesMs](./set-ad-group-times-ms.md)

# setAdGroupTimesMs

`fun setAdGroupTimesMs(adGroupTimesMs: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`>?, playedAdGroups: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>?, adGroupCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Sets the times of ad groups and whether each ad group has been played.

### Parameters

`adGroupTimesMs` - An array where the first `adGroupCount` elements are the times of
ad groups in milliseconds. May be `null` if there are no ad groups.

`playedAdGroups` - An array where the first `adGroupCount` elements indicate whether
the corresponding ad groups have been played. May be `null` if there are no ad
groups.

`adGroupCount` - The number of ad groups.