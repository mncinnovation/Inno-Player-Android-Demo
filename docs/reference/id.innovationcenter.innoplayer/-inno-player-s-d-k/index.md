[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoPlayerSDK](./index.md)

# InnoPlayerSDK

`class InnoPlayerSDK`

Class for initializing Inno Player, in here Inno Player can initiate by checking license key and retrieves feature access

### Types

| Name | Summary |
|---|---|
| [KeyCheckInitialListener](-key-check-initial-listener/index.md) | `interface KeyCheckInitialListener`<br>Is an interface to listen progress of checking license key lido player and to listen function after checking is finished. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `InnoPlayerSDK()`<br>Class for initializing Inno Player, in here Inno Player can initiate by checking license key and retrieves feature access |

### Functions

| Name | Summary |
|---|---|
| [init](init.md) | `fun init(applicationContext: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, applicationName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, lidoPlayerKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, keyCheckInitialListener: `[`KeyCheckInitialListener`](-key-check-initial-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Function to initialize InnoPlayer You need to call init() function only one time and before InnoPlayer View called. Call in `splash screen`, or your `Main activity` for example. |
