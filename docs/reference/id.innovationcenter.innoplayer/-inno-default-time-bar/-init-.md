[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoDefaultTimeBar](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`InnoDefaultTimeBar(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`? = null, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, timebarAttrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`? = attrs)`

A time bar that shows a current position, buffered position, duration and ad markers.

A InnoDefaultTimeBar can be customized by setting attributes, as outlined below.

Attributes

The following attributes can be set on a InnoDefaultTimeBar when used in a layout XML file:

* **`bar_height`** - Dimension for the height of the time bar.

* Default: [DEFAULT_BAR_HEIGHT_DP](-d-e-f-a-u-l-t_-b-a-r_-h-e-i-g-h-t_-d-p.md)

* **`touch_target_height`** - Dimension for the height of the area in which touch
interactions with the time bar are handled. If no height is specified, this also determines
the height of the view.

* Default: [DEFAULT_TOUCH_TARGET_HEIGHT_DP](-d-e-f-a-u-l-t_-t-o-u-c-h_-t-a-r-g-e-t_-h-e-i-g-h-t_-d-p.md)

* **`ad_marker_width`** - Dimension for the width of any ad markers shown on the
bar. Ad markers are superimposed on the time bar to show the times at which ads will play.

* Default: [DEFAULT_AD_MARKER_WIDTH_DP](-d-e-f-a-u-l-t_-a-d_-m-a-r-k-e-r_-w-i-d-t-h_-d-p.md)

* **`scrubber_enabled_size`** - Dimension for the diameter of the circular scrubber
handle when scrubbing is enabled but not in progress. Set to zero if no scrubber handle
should be shown.

* Default: [DEFAULT_SCRUBBER_ENABLED_SIZE_DP](-d-e-f-a-u-l-t_-s-c-r-u-b-b-e-r_-e-n-a-b-l-e-d_-s-i-z-e_-d-p.md)

* **`scrubber_disabled_size`** - Dimension for the diameter of the circular scrubber
handle when scrubbing isn't enabled. Set to zero if no scrubber handle should be shown.

* Default: [DEFAULT_SCRUBBER_DISABLED_SIZE_DP](-d-e-f-a-u-l-t_-s-c-r-u-b-b-e-r_-d-i-s-a-b-l-e-d_-s-i-z-e_-d-p.md)

* **`scrubber_dragged_size`** - Dimension for the diameter of the circular scrubber
handle when scrubbing is in progress. Set to zero if no scrubber handle should be shown.

* Default: [DEFAULT_SCRUBBER_DRAGGED_SIZE_DP](-d-e-f-a-u-l-t_-s-c-r-u-b-b-e-r_-d-r-a-g-g-e-d_-s-i-z-e_-d-p.md)

* **`scrubber_drawable`** - Optional reference to a drawable to draw for the
scrubber handle. If set, this overrides the default behavior, which is to draw a circle for
the scrubber handle.
* **`played_color`** - Color for the portion of the time bar representing media
before the current playback position.

* Corresponding method: [.setPlayedColor](set-played-color.md)
* Default: [DEFAULT_PLAYED_COLOR](-d-e-f-a-u-l-t_-p-l-a-y-e-d_-c-o-l-o-r.md)

* **`scrubber_color`** - Color for the scrubber handle.

* Corresponding method: [.setScrubberColor](set-scrubber-color.md)
* Default: [DEFAULT_SCRUBBER_COLOR](-d-e-f-a-u-l-t_-s-c-r-u-b-b-e-r_-c-o-l-o-r.md)

* **`buffered_color`** - Color for the portion of the time bar after the current
played position up to the current buffered position.

* Corresponding method: [.setBufferedColor](set-buffered-color.md)
* Default: [DEFAULT_BUFFERED_COLOR](-d-e-f-a-u-l-t_-b-u-f-f-e-r-e-d_-c-o-l-o-r.md)

* **`unplayed_color`** - Color for the portion of the time bar after the current
buffered position.

* Corresponding method: [.setUnplayedColor](set-unplayed-color.md)
* Default: [DEFAULT_UNPLAYED_COLOR](-d-e-f-a-u-l-t_-u-n-p-l-a-y-e-d_-c-o-l-o-r.md)

* **`ad_marker_color`** - Color for unplayed ad markers.

* Corresponding method: [.setAdMarkerColor](set-ad-marker-color.md)
* Default: [DEFAULT_AD_MARKER_COLOR](-d-e-f-a-u-l-t_-a-d_-m-a-r-k-e-r_-c-o-l-o-r.md)

* **`played_ad_marker_color`** - Color for played ad markers.

* Corresponding method: [.setPlayedAdMarkerColor](set-played-ad-marker-color.md)
* Default: [DEFAULT_PLAYED_AD_MARKER_COLOR](-d-e-f-a-u-l-t_-p-l-a-y-e-d_-a-d_-m-a-r-k-e-r_-c-o-l-o-r.md)
