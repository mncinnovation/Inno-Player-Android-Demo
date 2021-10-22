package co.innoplayer.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.dynamite.DynamiteModule;

import java.util.ArrayList;
import java.util.List;

import co.innoplayer.configuration.PlayerConfig;
import co.innoplayer.core.utils.MediaSourceBuilder;
import co.innoplayer.ima.utils.MediaSourceAdsUtils;
import co.innoplayer.media.playlists.PlaylistItem;
import testapp.R;
import testapp.databinding.ActivityAudioPlayerBinding;
import testapp.databinding.CastContextErrorBinding;

public class AudioPlayerActivity extends AppCompatActivity {

    ActivityAudioPlayerBinding binding;
    CastContextErrorBinding castContextErrorBinding;
    CastContext castContext;
    PlayerConfig playerConfig;
    static String TAG = "CLIENTAPP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAudioPlayerBinding.inflate(getLayoutInflater());
        castContextErrorBinding = CastContextErrorBinding.inflate(getLayoutInflater());
        if (Utils.isGoogleApiAvailable(this)) {
            try {
                castContext = CastContext.getSharedInstance(this);
            } catch (RuntimeException e) {
                Throwable cause = e.getCause();
                while (cause != null) {
                    if (cause instanceof DynamiteModule.LoadingException) {
                        setContentView(binding.getRoot());
                        return;
                    }
                    cause = cause.getCause();
                }
                e.printStackTrace();
                throw e;
            }
        }
        setContentView(binding.getRoot());

        binding.innoPlayerView.addOnErrorListener(errorEvent -> {
            if (errorEvent != null) {
                Log.e(TAG, "isPlayerErrorMsg: " + errorEvent.getMessage());
            }
        });

        binding.innoPlayerView.addOnSeekListener(seekEvent -> Log.e(TAG, "Scrub controller at " + seekEvent.getPosition()));

        initVideo();
    }

    private void initVideo() {
        List<PlaylistItem> playlists = new ArrayList<>();
        if (getIntent().getExtras() != null) {
            playlists.addAll(
                    (List<PlaylistItem>) getIntent().getSerializableExtra("playlistItems")
            );
        }
        playerConfig = new PlayerConfig.Builder().playlist(playlists).build();
        MediaSourceBuilder mediaSourceUtils = new MediaSourceAdsUtils(AudioPlayerActivity.this);
        binding.innoPlayerView.setup(
                playerConfig,
                this,
                mediaSourceUtils,
                true
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_cast, menu);
        CastButtonFactory.setUpMediaRouteButton(this, menu, R.id.media_route_menu_item);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.innoPlayerView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.innoPlayerView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.innoPlayerView.onPause();
    }

    @Override
    public void onBackPressed() {
        if (!binding.innoPlayerView.onBackPressedIsExitFullscreen()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.innoPlayerView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        binding.innoPlayerView.onStop();
    }

}