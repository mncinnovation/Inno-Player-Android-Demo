[reference](../../index.md) / [id.innovationcenter.innoplayer.events](../index.md) / [ErrorEvent](./index.md)

# ErrorEvent

`class ErrorEvent : `[`Exception`](https://developer.android.com/reference/java/lang/Exception.html)

An error event

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ErrorEvent(message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, exception: `[`Exception`](https://developer.android.com/reference/java/lang/Exception.html)`?, outOfMemoryError: `[`OutOfMemoryError`](https://developer.android.com/reference/java/lang/OutOfMemoryError.html)`?)`<br>An error event |

### Properties

| Name | Summary |
|---|---|
| [exception](exception.md) | `var exception: `[`Exception`](https://developer.android.com/reference/java/lang/Exception.html)`?`<br>The exception thrown along with this error |
| [message](message.md) | `var message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Error message that has been detected |
| [outOfMemoryError](out-of-memory-error.md) | `var outOfMemoryError: `[`OutOfMemoryError`](https://developer.android.com/reference/java/lang/OutOfMemoryError.html)`?`<br>Out of memory exception thrown along with this error if any |
