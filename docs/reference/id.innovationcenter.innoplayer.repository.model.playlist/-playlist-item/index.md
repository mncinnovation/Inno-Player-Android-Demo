[reference](../../index.md) / [id.innovationcenter.innoplayer.core.repository.model.playlist](../index.md) / [PlaylistItem](./index.md)

# PlaylistItem

`class PlaylistItem : `[`Serializable`](https://developer.android.com/reference/java/io/Serializable.html)

An item in a Playlist

### Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | `class Builder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PlaylistItem(file: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>`PlaylistItem(var2: `[`PlaylistItem`](./index.md)`)``PlaylistItem(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, file: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, subtitleUri: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, subtitleMimeType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, subtitleLanguage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, subtitleList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`SubtitleInfo`](../../id.innovationcenter.innoplayer.core.repository.model.subtitle/-subtitle-info/index.md)`>? = null, image: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, adTag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, scheduledAdsTag: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AdBreak`](../../id.innovationcenter.innoplayer.core.repository.model.ads/-ad-break/index.md)`>? = null, mediaId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, drmLicenses: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`DrmLicense`](../../id.innovationcenter.innoplayer.core.repository.model.drm/-drm-license/index.md)`>? = null, recommendations: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, urlThumbnails: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null)`<br>An item in a Playlist |

### Properties

| Name | Summary |
|---|---|
| [adTag](ad-tag.md) | `var adTag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Single ad tag of item |
| [description](description.md) | `var description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Short description of the item |
| [drmLicenses](drm-licenses.md) | `var drmLicenses: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`DrmLicense`](../../id.innovationcenter.innoplayer.core.repository.model.drm/-drm-license/index.md)`>?`<br>An list of DrmLicense |
| [file](file.md) | `var file: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>URL to a single video file, audio file, or live stream to play |
| [image](image.md) | `var image: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Image of item |
| [mediaId](media-id.md) | `var mediaId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>An id of media |
| [recommendations](recommendations.md) | `var recommendations: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>an recomendations description |
| [scheduledAdsTag](scheduled-ads-tag.md) | `var scheduledAdsTag: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AdBreak`](../../id.innovationcenter.innoplayer.core.repository.model.ads/-ad-break/index.md)`>?`<br>List of ad break |
| [subtitleLanguage](subtitle-language.md) | `var subtitleLanguage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Language of subtitle |
| [subtitleList](subtitle-list.md) | `var subtitleList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`SubtitleInfo`](../../id.innovationcenter.innoplayer.core.repository.model.subtitle/-subtitle-info/index.md)`>?`<br>List of subtitles |
| [subtitleMimeType](subtitle-mime-type.md) | `var subtitleMimeType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Mimetype of subtitle |
| [subtitleUri](subtitle-uri.md) | `var subtitleUri: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>URL of subtitle |
| [title](title.md) | `var title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Title of the item. This is displayed inside of the player prior to playback, as well as in the visual playlist. |
| [urlThumbnails](url-thumbnails.md) | `var urlThumbnails: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>an url thumbnails for seekbar preview |

### Functions

| Name | Summary |
|---|---|
| [cloneList](clone-list.md) | `fun cloneList(src: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`PlaylistItem`](./index.md)`>?): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`PlaylistItem`](./index.md)`>?` |
| [toJson](to-json.md) | `fun toJson(): `[`JSONObject`](https://developer.android.com/reference/org/json/JSONObject.html) |
