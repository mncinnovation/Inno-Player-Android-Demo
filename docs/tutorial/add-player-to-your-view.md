# Add Player and Custom View & Controller InnoPlayer

## Add Player to Your View (fragment or activity)

Add ``id.innovationcenter.lidoplayer.LidoPlayerView`` to parent or root of view like below

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    ...

    <id.innovationcenter.innoplayer.InnoPlayerView
        android:id="@+id/innoPlayerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />

    ...

</RelativeLayout>
```

## Custom View & Controller InnoPlayer

Here is attr that you can use in LidoPlayerView:

* ``inno_stretching``: To control mode view of video player with option value is pillarbox (default), stretch, cutted
* ``fastBackwardIncrement``: To custom backward increment value in milisecond, default is 10000
* ``fastForwardIncrement``: To custom forward increment value in milisecond, default is 10000
* ``controller_layout_id``: To full custom ui controller, if you want to use your own ui controller you can use this attr and add your custom controller layout in here. If not added to LidoPlayerView, LidoPlayerView will use default ui controller.
* ``audio_player_only``: To set player is for playing audio only or not


Below is example of additional attr that you can use:
```xml
    <id.innovationcenter.innoplayer.InnoPlayerView
        android:id="@+id/innoPlayerView"
        android:layout_width="match_parent"
        android:layout_height="@dimen/_200sdp"
        app:resizeMode="pillarbox"
        app:fastBackwardIncrement="20000"
        app:fastForwardIncrement="20000"
        app:controller_layout_id="@layout/clientlidoplayercustom_control"
    />
```

You can also customize InnoPlayer default ui controller.

You can change icon, color of icon, and change visibility in default ui controller.
All of parameter have default value. You can set only on parameter you want to change.

* Play Icon, call ``setPlayIcon`` from lidoPlayerView object.
```kotlin
  setPlayIcon(
    icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.button_play),
    iconColor: Int? = Color.parseColor("#FFFFFF"),
    /*please use Color.parseColor() for iconColor*/
    showInController: Boolean? = true
  )
```
Example
```kotlin
    lidoPlayerView.setPlayIcon(iconColor = Color.parseColor("#543782"))
```

* Pause Icon, call ``setPauseIcon`` from lidoPlayerView object.
```kotlin
  setPauseIcon(
    icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.button_pause),
    iconColor: Int? = Color.parseColor("#FFFFFF"),
    /*please use Color.parseColor() for iconColor*/
    showInController: Boolean? = true
  )
```
Example
```kotlin
    lidoPlayerView.setPauseIcon(icon = ContextCompat.getDrawable(context, R.drawable.ic_pause))
```

* Previous Icon, call ``setPreviousIcon`` from lidoPlayerView object.
```kotlin
  setPreviousIcon(
    icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.button_prev),
    iconColor: Int? = Color.parseColor("#FFFFFF"),
    /*please use Color.parseColor() for iconColor*/
    showInController: Boolean? = true
  )
```
Example
```kotlin
    lidoPlayerView.setPreviousIcon(
        icon = ContextCompat.getDrawable(context, R.drawable.ic_prev),
        iconColor: Int? = Color.parseColor("#B8C6CE")
    )
```

* Next Icon, call ``setNextIcon`` from lidoPlayerView object.
```kotlin
  setNextIcon(
    icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.button_next),
    iconColor: Int? = Color.parseColor("#FFFFFF"),
    /*please use Color.parseColor() for iconColor*/
    showInController: Boolean? = true
  )
```
Example
```kotlin
    lidoPlayerView.setNextIcon(
        icon = ContextCompat.getDrawable(context, R.drawable.ic_next),
        iconColor: Int? = Color.parseColor("#B8C6CE")
    )
```

* Backward Icon, call ``setBackwardIcon`` from lidoPlayerView object.
```kotlin
  setBackwardIcon(
    icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.button_ccw),
    iconColor: Int? = Color.parseColor("#FFFFFF"),
    /*please use Color.parseColor() for iconColor*/
    showInController: Boolean? = true
  )
```
Example
```kotlin
    lidoPlayerView.setBackwardIcon(showInController = false)
```

* Forward Icon, call ``setForwardIcon`` from lidoPlayerView object.
```kotlin
  setForwardIcon(
    icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.button_cw),
    iconColor: Int? = Color.parseColor("#FFFFFF"),
    /*please use Color.parseColor() for iconColor*/
    showInController: Boolean? = true
  )
```
Example
```kotlin
    lidoPlayerView.setForwardIcon(showInController = false)
```

* Setting Icon, call ``setSettingIcon`` from lidoPlayerView object.
```kotlin
  setSettingIcon(
    icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_settings_white_24dp),
    iconColor: Int? = Color.parseColor("#FFFFFF"),
    /*please use Color.parseColor() for iconColor*/
    showInController: Boolean? = true
  )
```
Example
```kotlin
    lidoPlayerView.setForwardIcon(
        icon = ContextCompat.getDrawable(context, R.drawable.ic_action_settings)
    )
```

* Fullscreen Enter Icon, call ``setFullscreenEnterIcon`` from lidoPlayerView object.
```kotlin
  setFullscreenEnterIcon(
    icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.button_fullscreen),
    iconColor: Int? = Color.parseColor("#FFFFFF"),
    /*please use Color.parseColor() for iconColor*/
    showInController: Boolean? = true
  )
```
Example
```kotlin
    lidoPlayerView.setFullscreenEnterIcon(
        icon = ContextCompat.getDrawable(context, R.drawable.ic_fullscreen)
    )
```

* Fullscreen Exit Icon, call ``setFullscreenExitIcon`` from lidoPlayerView object.
```kotlin
  setFullscreenExitIcon(
    icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.button_fullscreen_exit),
    iconColor: Int? = Color.parseColor("#FFFFFF"),
    /*please use Color.parseColor() for iconColor*/
    showInController: Boolean? = true
  )
```
Example
```kotlin
    lidoPlayerView.setFullscreenExitIcon(
        icon = ContextCompat.getDrawable(context, R.drawable.ic_fullscreen_exit)
    )
```