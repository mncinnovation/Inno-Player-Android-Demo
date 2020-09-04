[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoPlayerView](index.md) / [onItemPanelSettingClicked](./on-item-panel-setting-clicked.md)

# onItemPanelSettingClicked

`fun onItemPanelSettingClicked(trackFormat: TrackFormat?, speed: Speed?, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?, overrides: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<SelectionOverride?>?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Fired when item list on panel setting is clicked

### Parameters

`trackFormat` - retrieves track format, may null if item selected is speed

`speed` - retrieves speed, may null if item selected is track format (video quality / captions)

`overrides` - &lt;DefaultTrackSelector.SelectionOverride?&gt;: retrieves List of SelectionOverride