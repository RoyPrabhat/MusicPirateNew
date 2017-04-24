package com.roy.musicpirate.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.roy.musicpirate.R;
import com.roy.musicpirate.fragment.AllSongsFragment;
import com.roy.musicpirate.fragment.MainFragment;
import com.roy.musicpirate.service.MusicPirateService;
import com.roy.musicpirate.util.AlertDialogueUtil;
import com.roy.musicpirate.util.SharedPreferenceManager;
import com.roy.musicpirate.util.ToolbarUtil;

/**
 * @author ROY
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static MusicPirateService MPService;
    private Intent playIntent;
    private boolean musicBound = false;
    private MediaPlayer mMediaPlayer;
    private Intent mPlayIntent;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        ToolbarUtil.buildToolbar(toolbar, getString(R.string.app_name), MainActivity.this);

        if (!SharedPreferenceManager.haveShownWelcomeMessage(getApplicationContext())) {
            AlertDialogueUtil.showWelcomeMessage(this);
            SharedPreferenceManager.saveWelcomeMessageShown(getApplicationContext());
        }

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_activity_option_container);
        if (fragment == null) {
            fragment = new MainFragment();
            fm.beginTransaction()
                    .add(R.id.main_activity_option_container, fragment)
                    .commit();
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(this, MusicPirateService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(AllSongsFragment.class.getName()) != null) {
            // I'm viewing all songsFragment
            getSupportFragmentManager().popBackStack(MainFragment.class.getName(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            super.onBackPressed();
        }
    }

    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicPirateService.MusicBinder binder = (MusicPirateService.MusicBinder)service;
            //get service
            MPService = binder.getService();
            mMediaPlayer = binder.getMediaPlayer();
            //pass list
         /*   MPService.setSongs(mSongList);
            MPService.setPlayPauseButton(mPlayPause);
            MPService.setTotalDurationView(mTotalDuration);
            MPService.setCurrentPositionView(mCurrentPosition);
            MPService.setSeekBar(mSeekbar);
            mAllSongsListAdapter.setMusicPlayerService(MPService);
        */    musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    public static MusicPirateService getMPService() {
        return MPService;
    }

    @Override
    public void onDestroy() {
        stopService(playIntent);
        MPService = null;
        super.onDestroy();
    }

}
