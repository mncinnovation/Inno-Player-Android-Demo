[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoDefaultTimeBar](./index.md)

# InnoDefaultTimeBar

`class InnoDefaultTimeBar : `[`View`](https://developer.android.com/reference/android/view/View.html)`, TimeBar`

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

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `InnoDefaultTimeBar(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`? = null, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, timebarAttrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`? = attrs)`<br>A time bar that shows a current position, buffered position, duration and ad markers. |

### Functions

| Name | Summary |
|---|---|
| [addListener](add-listener.md) | `fun addListener(listener: OnScrubListener): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds a listener for scrubbing events. |
| [drawableStateChanged](drawable-state-changed.md) | `fun drawableStateChanged(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getPreferredUpdateDelay](get-preferred-update-delay.md) | `fun getPreferredUpdateDelay(): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)<br>Returns the preferred delay in milliseconds of media time after which the time bar position should be updated. |
| [jumpDrawablesToCurrentState](jump-drawables-to-current-state.md) | `fun jumpDrawablesToCurrentState(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onDraw](on-draw.md) | `fun onDraw(canvas: `[`Canvas`](https://developer.android.com/reference/android/graphics/Canvas.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onFocusChanged](on-focus-changed.md) | `fun onFocusChanged(gainFocus: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, direction: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, previouslyFocusedRect: `[`Rect`](https://developer.android.com/reference/android/graphics/Rect.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onInitializeAccessibilityEvent](on-initialize-accessibility-event.md) | `fun onInitializeAccessibilityEvent(event: `[`AccessibilityEvent`](https://developer.android.com/reference/android/view/accessibility/AccessibilityEvent.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onInitializeAccessibilityNodeInfo](on-initialize-accessibility-node-info.md) | `fun onInitializeAccessibilityNodeInfo(info: `[`AccessibilityNodeInfo`](https://developer.android.com/reference/android/view/accessibility/AccessibilityNodeInfo.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onKeyDown](on-key-down.md) | `fun onKeyDown(keyCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, event: `[`KeyEvent`](https://developer.android.com/reference/android/view/KeyEvent.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onLayout](on-layout.md) | `fun onLayout(changed: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, left: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, top: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, right: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, bottom: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMeasure](on-measure.md) | `fun onMeasure(widthMeasureSpec: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, heightMeasureSpec: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onRtlPropertiesChanged](on-rtl-properties-changed.md) | `fun onRtlPropertiesChanged(layoutDirection: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onTouchEvent](on-touch-event.md) | `fun onTouchEvent(event: `[`MotionEvent`](https://developer.android.com/reference/android/view/MotionEvent.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [performAccessibilityAction](perform-accessibility-action.md) | `fun performAccessibilityAction(action: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, args: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [removeListener](remove-listener.md) | `fun removeListener(listener: OnScrubListener): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a listener for scrubbing events. |
| [setAdGroupTimesMs](set-ad-group-times-ms.md) | `fun setAdGroupTimesMs(adGroupTimesMs: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`>?, playedAdGroups: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>?, adGroupCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the times of ad groups and whether each ad group has been played. |
| [setAdMarkerColor](set-ad-marker-color.md) | `fun setAdMarkerColor(adMarkerColor: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the color for unplayed ad markers. |
| [setBufferedColor](set-buffered-color.md) | `fun setBufferedColor(bufferedColor: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the color for the portion of the time bar after the current played position up to the current buffered position. |
| [setBufferedPosition](set-buffered-position.md) | `fun setBufferedPosition(bufferedPosition: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the buffered position. |
| [setDuration](set-duration.md) | `fun setDuration(duration: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the duration. |
| [setEnabled](set-enabled.md) | `fun setEnabled(enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setKeyCountIncrement](set-key-count-increment.md) | `fun setKeyCountIncrement(count: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the position increment for key presses and accessibility actions, as a number of increments that divide the duration of the media. For example, passing 20 will cause key presses to increment/decrement the position by 1/20th of the duration (if known). |
| [setKeyTimeIncrement](set-key-time-increment.md) | `fun setKeyTimeIncrement(time: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the position increment for key presses and accessibility actions, in milliseconds. |
| [setPlayedAdMarkerColor](set-played-ad-marker-color.md) | `fun setPlayedAdMarkerColor(playedAdMarkerColor: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the color for played ad markers. |
| [setPlayedColor](set-played-color.md) | `fun setPlayedColor(playedColor: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the color for the portion of the time bar representing media before the playback position. |
| [setPosition](set-position.md) | `fun setPosition(position: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the current position. |
| [setScrubberColor](set-scrubber-color.md) | `fun setScrubberColor(scrubberColor: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the color for the scrubber handle. |
| [setUnplayedColor](set-unplayed-color.md) | `fun setUnplayedColor(unplayedColor: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the color for the portion of the time bar after the current played position. |

### Companion Object Properties

| Name | Summary |
|---|---|
| [DEFAULT_AD_MARKER_COLOR](-d-e-f-a-u-l-t_-a-d_-m-a-r-k-e-r_-c-o-l-o-r.md) | `const val DEFAULT_AD_MARKER_COLOR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default color for ad markers. |
| [DEFAULT_AD_MARKER_WIDTH_DP](-d-e-f-a-u-l-t_-a-d_-m-a-r-k-e-r_-w-i-d-t-h_-d-p.md) | `const val DEFAULT_AD_MARKER_WIDTH_DP: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default width for ad markers, in dp. |
| [DEFAULT_BAR_HEIGHT_DP](-d-e-f-a-u-l-t_-b-a-r_-h-e-i-g-h-t_-d-p.md) | `const val DEFAULT_BAR_HEIGHT_DP: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default height for the time bar, in dp. |
| [DEFAULT_BUFFERED_COLOR](-d-e-f-a-u-l-t_-b-u-f-f-e-r-e-d_-c-o-l-o-r.md) | `const val DEFAULT_BUFFERED_COLOR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default color for the buffered portion of the time bar. |
| [DEFAULT_PLAYED_AD_MARKER_COLOR](-d-e-f-a-u-l-t_-p-l-a-y-e-d_-a-d_-m-a-r-k-e-r_-c-o-l-o-r.md) | `const val DEFAULT_PLAYED_AD_MARKER_COLOR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default color for played ad markers. |
| [DEFAULT_PLAYED_COLOR](-d-e-f-a-u-l-t_-p-l-a-y-e-d_-c-o-l-o-r.md) | `const val DEFAULT_PLAYED_COLOR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default color for the played portion of the time bar. |
| [DEFAULT_SCRUBBER_COLOR](-d-e-f-a-u-l-t_-s-c-r-u-b-b-e-r_-c-o-l-o-r.md) | `const val DEFAULT_SCRUBBER_COLOR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default color for the scrubber handle. |
| [DEFAULT_SCRUBBER_DISABLED_SIZE_DP](-d-e-f-a-u-l-t_-s-c-r-u-b-b-e-r_-d-i-s-a-b-l-e-d_-s-i-z-e_-d-p.md) | `const val DEFAULT_SCRUBBER_DISABLED_SIZE_DP: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default diameter for the scrubber when disabled, in dp. |
| [DEFAULT_SCRUBBER_DRAGGED_SIZE_DP](-d-e-f-a-u-l-t_-s-c-r-u-b-b-e-r_-d-r-a-g-g-e-d_-s-i-z-e_-d-p.md) | `const val DEFAULT_SCRUBBER_DRAGGED_SIZE_DP: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default diameter for the scrubber when dragged, in dp. |
| [DEFAULT_SCRUBBER_ENABLED_SIZE_DP](-d-e-f-a-u-l-t_-s-c-r-u-b-b-e-r_-e-n-a-b-l-e-d_-s-i-z-e_-d-p.md) | `const val DEFAULT_SCRUBBER_ENABLED_SIZE_DP: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default diameter for the scrubber when enabled, in dp. |
| [DEFAULT_TOUCH_TARGET_HEIGHT_DP](-d-e-f-a-u-l-t_-t-o-u-c-h_-t-a-r-g-e-t_-h-e-i-g-h-t_-d-p.md) | `const val DEFAULT_TOUCH_TARGET_HEIGHT_DP: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default height for the touch target, in dp. |
| [DEFAULT_UNPLAYED_COLOR](-d-e-f-a-u-l-t_-u-n-p-l-a-y-e-d_-c-o-l-o-r.md) | `const val DEFAULT_UNPLAYED_COLOR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default color for the unplayed portion of the time bar. |
