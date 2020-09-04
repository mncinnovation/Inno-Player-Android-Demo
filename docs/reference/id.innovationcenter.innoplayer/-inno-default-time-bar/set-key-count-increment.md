[reference](../../index.md) / [id.innovationcenter.innoplayer](../index.md) / [InnoDefaultTimeBar](index.md) / [setKeyCountIncrement](./set-key-count-increment.md)

# setKeyCountIncrement

`fun setKeyCountIncrement(count: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Sets the position increment for key presses and accessibility actions, as a number of
increments that divide the duration of the media. For example, passing 20 will cause key
presses to increment/decrement the position by 1/20th of the duration (if known).

Clears any increment specified in a preceding call to [.setKeyTimeIncrement](#).

### Parameters

`count` - The number of increments that divide the duration of the media.