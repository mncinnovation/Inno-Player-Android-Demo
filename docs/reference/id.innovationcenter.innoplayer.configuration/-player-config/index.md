[reference](../../index.md) / [id.innovationcenter.innoplayer.configuration](../index.md) / [PlayerConfig](./index.md)

# PlayerConfig

`class PlayerConfig : `[`Serializable`](https://developer.android.com/reference/java/io/Serializable.html)

Player configuration used to set up the player, contains all basic configuration attributes from XML custom attrs or the builder, along with Playlist and Advertising settings

### Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | `class Builder`<br>Builder of Player Config |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PlayerConfig(builder: `[`Builder`](-builder/index.md)`)``PlayerConfig(playlist: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`PlaylistItem`](../../id.innovationcenter.innoplayer.media.playlists/-playlist-item/index.md)`>? = ArrayList(), stretching: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = STRETCHING_PILLARBOX)`<br>Player configuration used to set up the player, contains all basic configuration attributes from XML custom attrs or the builder, along with Playlist and Advertising settings |

### Properties

| Name | Summary |
|---|---|
| [playlist](playlist.md) | `var playlist: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`PlaylistItem`](../../id.innovationcenter.innoplayer.media.playlists/-playlist-item/index.md)`>?`<br>an list of PlaylistItem |
| [stretching](stretching.md) | `var stretching: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`?`<br>is an view mode aspect ratio size of player |

### Companion Object Properties

| Name | Summary |
|---|---|
| [STRETCHING_CUTTED](-s-t-r-e-t-c-h-i-n-g_-c-u-t-t-e-d.md) | `const val STRETCHING_CUTTED: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Either the width or height is increased to obtain the desired aspect ratio. |
| [STRETCHING_NONE](-s-t-r-e-t-c-h-i-n-g_-n-o-n-e.md) | `const val STRETCHING_NONE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Displays the actual size of the video file with black borders |
| [STRETCHING_PILLARBOX](-s-t-r-e-t-c-h-i-n-g_-p-i-l-l-a-r-b-o-x.md) | `const val STRETCHING_PILLARBOX: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Either the width or height is decreased to obtain the desired aspect ratio. |
| [STRETCHING_STRETCH](-s-t-r-e-t-c-h-i-n-g_-s-t-r-e-t-c-h.md) | `const val STRETCHING_STRETCH: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The height is fixed and the width is increased or decreased to obtain the desired aspect ratio. |
