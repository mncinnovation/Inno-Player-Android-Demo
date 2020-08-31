[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoPlayerView](index.md) / [setSettingIcon](./set-setting-icon.md)

# setSettingIcon

`fun setSettingIcon(icon: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`? = ContextCompat.getDrawable(context, R.drawable.button_setting), iconColor: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = Color.parseColor("#FFFFF"), showInController: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`? = true): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Function to custom setting icon

### Parameters

`icon` - an setting icon, with default from lido player R.drawable.button_settings_white_24dp

`iconColor` - color of setting icon, please use Color.parseColor() for iconColor. Default is Color.parseColor("#FFFFFF")

`showInController` - Boolean value with true to show in controller or false to hide setting icon