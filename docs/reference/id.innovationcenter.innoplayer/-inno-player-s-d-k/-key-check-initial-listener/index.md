[reference](../../../index.md) / [id.innovationcenter.innoplayer](../../index.md) / [InnoPlayerSDK](../index.md) / [KeyCheckInitialListener](./index.md)

# KeyCheckInitialListener

`interface KeyCheckInitialListener`

Is an interface to listen progress of checking license key lido player and to listen function after checking is finished.

### Functions

| Name | Summary |
|---|---|
| [onFinishLidoPlayerKeyCheck](on-finish-lido-player-key-check.md) | `abstract fun onFinishLidoPlayerKeyCheck(feature: Feature?, errorMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>function that have value of feature that can inform you, feature that can supported by InnoPlayer based on your key. |
| [onProgressLidoPlayerKeyCheck](on-progress-lido-player-key-check.md) | `abstract fun onProgressLidoPlayerKeyCheck(isShowProgress: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Function to check progress of checking key |
