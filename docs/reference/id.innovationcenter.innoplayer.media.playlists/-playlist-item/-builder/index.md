[reference](../../../index.md) / [id.innovationcenter.innoplayer.media.playlists](../../index.md) / [PlaylistItem](../index.md) / [Builder](./index.md)

# Builder

`class Builder`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Builder()` |

### Properties

| Name | Summary |
|---|---|
| [adTag](ad-tag.md) | `var adTag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [description](description.md) | `var description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [drmLicenseUrl](drm-license-url.md) | `var drmLicenseUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [drmScheme](drm-scheme.md) | `var drmScheme: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [file](file.md) | `var file: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [httpHeaders](http-headers.md) | `var httpHeaders: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>?` |
| [image](image.md) | `var image: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [mediaId](media-id.md) | `var mediaId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [recommendations](recommendations.md) | `var recommendations: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [scheduledAdsTag](scheduled-ads-tag.md) | `var scheduledAdsTag: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AdBreak`](../../../id.innovationcenter.innoplayer.media.ads/-ad-break/index.md)`>?` |
| [subtitleLanguage](subtitle-language.md) | `var subtitleLanguage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [subtitleList](subtitle-list.md) | `var subtitleList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`SubtitleInfo`](../../../id.innovationcenter.innoplayer.media.captions/-subtitle-info/index.md)`>?` |
| [subtitleMimeType](subtitle-mime-type.md) | `var subtitleMimeType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [subtitleUri](subtitle-uri.md) | `var subtitleUri: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [title](title.md) | `var title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |

### Functions

| Name | Summary |
|---|---|
| [adTagUri](ad-tag-uri.md) | `fun adTagUri(adTag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Builder`](./index.md) |
| [build](build.md) | `fun build(): `[`PlaylistItem`](../index.md) |
| [description](description.md) | `fun description(description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Builder`](./index.md) |
| [drmLicenseUrl](drm-license-url.md) | `fun drmLicenseUrl(drmLicenseUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Builder`](./index.md) |
| [drmScheme](drm-scheme.md) | `fun drmScheme(drmScheme: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Builder`](./index.md) |
| [file](file.md) | `fun file(file: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Builder`](./index.md) |
| [httpHeaders](http-headers.md) | `fun httpHeaders(httpHeaders: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Builder`](./index.md) |
| [image](image.md) | `fun image(image: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Builder`](./index.md) |
| [mediaId](media-id.md) | `fun mediaId(mediaId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Builder`](./index.md) |
| [recommendations](recommendations.md) | `fun recommendations(recommendations: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Builder`](./index.md) |
| [setScheduledAdsTag](set-scheduled-ads-tag.md) | `fun setScheduledAdsTag(scheduleAdsTag: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AdBreak`](../../../id.innovationcenter.innoplayer.media.ads/-ad-break/index.md)`>?): `[`Builder`](./index.md) |
| [subtitleLanguage](subtitle-language.md) | `fun subtitleLanguage(subtitleLanguage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Builder`](./index.md) |
| [subtitleList](subtitle-list.md) | `fun subtitleList(subtitleList: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`SubtitleInfo`](../../../id.innovationcenter.innoplayer.media.captions/-subtitle-info/index.md)`>?): `[`Builder`](./index.md) |
| [subtitleMimeType](subtitle-mime-type.md) | `fun subtitleMimeType(subtitleMimeType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Builder`](./index.md) |
| [subtitleUri](subtitle-uri.md) | `fun subtitleUri(subtitleUri: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Builder`](./index.md) |
| [title](title.md) | `fun title(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Builder`](./index.md) |
