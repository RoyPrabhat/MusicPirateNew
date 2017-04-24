package com.roy.musicpirate.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.roy.musicpirate.R;
import com.roy.musicpirate.activity.MainActivity;
import com.roy.musicpirate.adapter.AllSongsListAdapter;
import com.roy.musicpirate.model.Song;
import com.roy.musicpirate.musicplayercontrols.MusicController;
import com.roy.musicpirate.service.MusicPirateService;
import com.roy.musicpirate.singleton.SongsListSingleton;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author ROY
 */
public class AllSongsFragment extends Fragment {


    private ListView mAllSongsView;
    // private LinearLayoutManager mLayoutManager;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100;


    private AllSongsListAdapter mAllSongsListAdapter;
    private static MusicPirateService MPService;
    private Intent playIntent;
    private boolean musicBound = false;
    private ImageView iv;
    private MediaPlayer mMediaPlayer;
    private MusicController mMusicController;
    private ImageButton mPreviousSong, mPlayPause, mNextSong;
    private SeekBar mSeekbar;
    private TextView mTotalDuration, mCurrentPosition;

    private double startTime = 0;
    private Handler myHandler = new Handler();
    ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_all_songs, container, false);
        setHasOptionsMenu(true);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        mTotalDuration = (TextView) getActivity().findViewById(R.id.total_duration);
        mCurrentPosition = (TextView) getActivity().findViewById(R.id.current_position);
        mAllSongsView = (ListView) rootView.findViewById(R.id.all_songs_list);

        ArrayList<Song> songsList = SongsListSingleton.getInstance().getSongList();

        mAllSongsListAdapter = new AllSongsListAdapter(getContext(), songsList, mTotalDuration);
        mAllSongsView.setAdapter(mAllSongsListAdapter);
      //  mPlayPause = (ImageButton) getActivity().findViewById(R.id.play_pause);
      //  mPreviousSong = (ImageButton) getActivity().findViewById(R.id.previous_song);
      //  mNextSong = (ImageButton) getActivity().findViewById(R.id.next_song);
    //    mSeekbar = (SeekBar) getActivity().findViewById(R.id.seekBar);
      //  setUpNextSongButton();
        //setUpPreviousSongButton();
        //setUpPlayPause();
    }

  /*  private void setUpPlayPause() {
        mPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MPService.playPause(mPlayPause);
            }
        });
    }

    private void setUpPreviousSongButton() {
        mPreviousSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MPService.playPrevious();
    }
        });
    }

    private void setUpNextSongButton() {

        mNextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MPService.playNext();
            }
        });

    }

        //connect to the service

    */

    @Override
    public void onStart() {
        super.onStart();
        MPService = ((MainActivity)getActivity()).getMPService();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_shuffle:
                //shuffle
                break;
            case R.id.action_end:
                getActivity().stopService(playIntent);
                MPService = null;
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
