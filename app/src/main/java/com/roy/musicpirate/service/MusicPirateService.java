package com.roy.musicpirate.service;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.roy.musicpirate.R;
import com.roy.musicpirate.model.Song;

import java.util.ArrayList;

/**
 * @author ROY
 */
public class MusicPirateService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener {

    //media player
    private static MediaPlayer mPlayer;
    //song list
    private ArrayList<Song> mSongs;
    //current position

    private int mSongPosition;
    private static boolean isPlaying = false;
    private static ImageButton mPlayPauseButton;
    private TextView mTotalDurationView;
    private TextView mCurrentPositionView;
    private String mTotalDuration;
    private SeekBar mSeekBar;

    private final IBinder mMusicBind = new MusicBinder();


    public class MusicBinder extends Binder {
        public MusicPirateService getService() {
            return MusicPirateService.this;
        }

        public MediaPlayer getMediaPlayer() {
            return mPlayer;
        }
    }
    private Handler myHandler = new Handler();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMusicBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
        this.stopSelf();
        return false;
    }


    public void onCreate() {
        //create the service
        super.onCreate();
        //initialize position
        mSongPosition = 0;
        //create player
        mPlayer = new MediaPlayer();
        initMusicPlayer();
    }

    public void initMusicPlayer() {
        //set player properties
        mPlayer.setWakeMode(getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnErrorListener(this);
    }

    public void setSongs(ArrayList<Song> songs) {
        this.mSongs = songs;
    }

    public void setSong(int songIndex) {
        mSongPosition = songIndex;
    }

/*    public void playNext() {
        mSongPosition++;
        if (mSongPosition >= mSongs.size()) mSongPosition = 0;
        playSong();
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }



    public void playPrevious() {
        mSongPosition--;
        if (mSongPosition < 0) mSongPosition = mSongs.size() - 1;
        playSong();
    }

    public void playPause(ImageButton mPlayPause) {
        if (isPlaying) {
            mPlayer.pause();
            isPlaying = false;
            if (!mPlayer.isPlaying())
                setPlayPauseResourceId(mPlayPause);
        } else {
            mPlayer.start();
            isPlaying = true;
            setPlayPauseResourceId(mPlayPause);
        }
    }

    public void setPlayPauseResourceId(ImageButton mPlayPause) {
        if (isPlaying)
            mPlayPause.setImageResource(R.drawable.pause_circle_outline);
        else mPlayPause.setImageResource(R.drawable.play_circle_outline);

    }

    public void setPlayPauseButton(ImageButton playPauseButton) {
        this.mPlayPauseButton = playPauseButton;
    }

    public MediaPlayer getMediaPlayer() {
        return mPlayer;
    }

    public String getDuration() {

        String duration = new String();

        if (mPlayer != null) {
            int seconds = (mPlayer.getDuration() / 1000) % 60;
            int minutes = ((mPlayer.getDuration() / (1000 * 60)) % 60);
            int hours = ((mPlayer.getDuration() / (1000 * 60 * 60)) % 24);

            if (hours > 0 && hours < 10)
                duration = duration + "0" + hours + ":";
            else if (hours > 10)
                duration = hours + ":";

            if (minutes > 0 && minutes < 10)
                duration = duration + "0" + minutes + ":";
            else if (minutes > 10)
                duration = duration + minutes + ":";

            if (seconds > 0 && seconds < 10)
                duration = duration + "0" + seconds + ":";
            else if (seconds > 10)
                duration = duration + seconds;
        }
        return duration;
    }

    public String getCurrentPosition() {

        String duration = new String();
        if (mPlayer != null) {
            int seconds = (mPlayer.getCurrentPosition() / 1000) % 60;
            int minutes = ((mPlayer.getCurrentPosition() / (1000 * 60)) % 60);
            int hours = ((mPlayer.getCurrentPosition() / (1000 * 60 * 60)) % 24);

            if (hours > 0 && hours <10)
                duration = String.valueOf(hours) + ":";
            else if(hours >= 10)
                duration = duration + minutes + ":";

            if (minutes > 0 && minutes < 10)
                duration = duration + "0" + minutes + ":";
            else if (minutes >= 10)
                duration = duration + minutes + ":";
            else duration = duration + "00:";

            if (seconds > 0 && seconds < 10)
                duration = duration + "0" + seconds;
            else if (seconds >= 10)
                duration = duration + String.valueOf(seconds);
            else duration = duration + "00";
        }
        return duration;
    }

    public void setTotalDurationView(TextView totalDurationView) {
        this.mTotalDurationView = totalDurationView;
    }

    public void setCurrentPositionView(TextView currentPositionView) {
        this.mCurrentPositionView = currentPositionView;
    }

    public void setSeekBar(SeekBar seekBar) {
        this.mSeekBar = seekBar;
    }
*/

    @Override
    public void onCompletion(MediaPlayer mp) {
  //      this.playNext();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
    /*    mTotalDuration = this.getDuration();
        mSeekBar.setMax(mPlayer.getDuration()/1000);
        mp.start();
        mTotalDurationView.setText(mTotalDuration);
 */   }

  /*  public void playSong() {
        //play a song
        isPlaying = true;
        this.mPlayPauseButton.setImageResource(R.drawable.pause_circle_outline);
        mPlayer.reset();

        Song playSong = mSongs.get(mSongPosition);
        long currSong = playSong.getId();
        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                currSong);
        try {
            mPlayer.setDataSource(getApplicationContext(), trackUri);
        } catch (Exception e) {
            Log.e("MUSIC SERVICE", "Error setting data source", e);
        }
        mPlayer.prepareAsync();
        myHandler.postDelayed(UpdateSongTime, 100);
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            mCurrentPositionView.setText(getCurrentPosition());
            if(mPlayer != null)
            myHandler.postDelayed(this, 100);
            if(mPlayer != null)
            mSeekBar.setProgress(mPlayer.getCurrentPosition()/1000);
        }
    };
*/
}