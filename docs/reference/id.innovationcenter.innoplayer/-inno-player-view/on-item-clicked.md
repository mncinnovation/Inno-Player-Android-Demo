[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoPlayerView](index.md) / [onItemClicked](./on-item-clicked.md)

# onItemClicked

`fun onItemClicked(trackFormat: `[`TrackFormat`](../../id.innovationcenter.innoplayer.core.repository.model.tracksetting/-track-format/index.md)`?, speed: `[`Speed`](../../id.innovationcenter.innoplayer.core.repository.model.tracksetting/-speed/index.md)`?, overrides: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<SelectionOverride?>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Fired when item list on panel setting is clicked

### Parameters

`TrackFormat` - : retrieves track format, may null if item selected is speed

`Speed` - : retrieves speed, may null if item selected is track format (video quality / subtitle)

`List` - &lt;DefaultTrackSelector.SelectionOverride?&gt;: retrieves List of SelectionOverride