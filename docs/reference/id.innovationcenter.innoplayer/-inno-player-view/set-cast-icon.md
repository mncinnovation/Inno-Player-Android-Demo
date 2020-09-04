[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoPlayerView](index.md) / [setCastIcon](./set-cast-icon.md)

# setCastIcon

`fun setCastIcon(icon: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`? = ContextCompat.getDrawable(context, R.drawable.button_setting), iconColor: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = Color.parseColor("#FFFFF"), showInController: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`? = true): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Function to custom cast icon

### Parameters

`icon` - an setting icon, with default from lido player R.drawable.ic_cast

`iconColor` - color of cast icon, please use Color.parseColor() for iconColor. Default is Color.parseColor("#FFFFFF")

`showInController` - Boolean value with true to show in controller or false to hide cast icon, default true