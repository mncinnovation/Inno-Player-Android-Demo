# Life Cycle of InnoPlayer

InnoPlayer View need to following android activity life cycle to make sure the player work well.
Users from this class must forward several important lifecycle methods. It's important that you forward the following methods:

* ``onResume()`` : function to resume the player when app is resume the activity/ fragment. Please add this function to onResume function on activity/ fragment where the player is implemented.
* ``onDestroy()`` : function to destroy or make the player clear. Please call this function onDestroy on activity or fragment.
* ``onPause()`` : function to pause the player when lifecycle of activity/ fragment pause. Please call this function at onPause() of your activity/ fragment.
* ``onBackPressedIsExitFullscreen`` : function to check fullscreen before onBackPressed action release. With this function player will exit fullscreen if player in fullscreen mode an then back is pressed.

If your player support fullscreen feature, and want to make InnoPlayer exit fullscreen when the user of your app press back, please to call ``onBackPressedIsExitFullscreen`` to check the player is already fullscreen or not.

If the return value is true, InnoPlayer View will exit the fullscreen and you  don't need to call the back command (``super.onBackPressed()``).

Example
```kotlin

    override fun onStart() {
        super.onStart()
        lidoPlayerView.onStart()
    }    

    override fun onResume() {
        super.onResume()
        lidoPlayerView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        lidoPlayerView.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        lidoPlayerView.onPause()
    }

    override fun onBackPressed() {
        if (!lidoPlayerView.onBackPressedIsExitFullscreen())
            super.onBackPressed()
    }

```