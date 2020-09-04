[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoPlayerView](index.md) / [setFullscreenExitIcon](./set-fullscreen-exit-icon.md)

# setFullscreenExitIcon

`fun setFullscreenExitIcon(icon: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`? = ContextCompat.getDrawable(context, R.drawable.button_fullscreen_exit), iconColor: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = Color.parseColor("#FFFFF"), showInController: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`? = true): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Function to custom exit fullscreen icon

### Parameters

`icon` - an exit fullscreen icon, with default from lido player R.drawable.button_fullscreen_exit

`iconColor` - color of exit fullscreen icon, please use Color.parseColor() for iconColor. Default is Color.parseColor("#FFFFFF")

`showInController` - Boolean value with true to show in controller or false to hide exit fullscreen icon