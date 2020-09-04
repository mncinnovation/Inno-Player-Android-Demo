[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoPlayerView](index.md) / [setPlayIcon](./set-play-icon.md)

# setPlayIcon

`fun setPlayIcon(icon: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`? = ContextCompat.getDrawable(context, R.drawable.button_play), iconColor: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = Color.parseColor("#FFFFFF"), showInController: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`? = true): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Function to custom play icon

### Parameters

`icon` - an play icon, with default from lido player R.drawable.button_play

`iconColor` - color of play icon, please use Color.parseColor() for iconColor. Default is Color.parseColor("#FFFFFF")

`showInController` - Boolean value with true to show in controller or false to hide play icon