[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoPlayerSDK](index.md) / [init](./init.md)

# init

`fun init(applicationContext: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, applicationName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, lidoPlayerKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, keyCheckInitialListener: `[`KeyCheckInitialListener`](-key-check-initial-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Function to initialize InnoPlayer
You need to call init() function only one time and before InnoPlayer View called.
Call in `splash screen`, or your `Main activity` for example.

**Return**
unit

