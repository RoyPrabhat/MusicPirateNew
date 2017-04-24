package com.roy.musicpirate.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.roy.musicpirate.R;
import com.roy.musicpirate.activity.MainActivity;
import com.roy.musicpirate.model.Song;
import com.roy.musicpirate.service.MusicPirateService;
import com.roy.musicpirate.singleton.SongsListSingleton;
import com.roy.musicpirate.util.PermissionUtil;
import com.roy.musicpirate.util.SongUtil;

import java.util.ArrayList;

/**
 * @author ROY
 */
public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();
    private LinearLayout mAllSongsLayout;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100;
    private ArrayList<Song> mSongList;
    private SongsListSingleton mSongsListSingleton;
    private static Cursor mMusicCursor;
    private ContentResolver mMusicResolver;
    private Uri mMusicUri;
    private TextView mSongsCount;
    private MusicPirateService MPService;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(rootView);
        setListeners();
        return rootView;
    }


    private void initViews(View rootView) {
        mAllSongsLayout = (LinearLayout) rootView.findViewById(R.id.all_songs);
        mSongsCount = (TextView) rootView.findViewById(R.id.songs_count);
    }

    private void setListeners() {
        setAllSongsClickListeners();
    }

    private void setAllSongsClickListeners() {
        mAllSongsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllSongsFragment allSongsFragment = new AllSongsFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_activity_option_container, allSongsFragment, allSongsFragment.getClass().getName());
                transaction.addToBackStack(MainFragment.class.getName());
                transaction.commit();
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRequiredPermissions();
    }

    private void setupRequiredPermissions() {

        boolean isPermissionPresent = PermissionUtil.isReadExternalStoragePermissionPresent(getContext());

        if (isPermissionPresent) {
            setUpSongsList();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {

                //TODO describe why the permission is required
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setUpSongsList();
                } else {
                    //TODO gracefully handle permission being denied by the user
                    // instead of null show a dialogue on click of it
                    mAllSongsLayout.setOnClickListener(null);
                }
                return;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MPService = ((MainActivity)getActivity()).getMPService();
    }

    private void setUpSongsList() {
        mSongList = new ArrayList<>();

        mMusicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        mMusicResolver = getActivity().getContentResolver();
        mMusicCursor = mMusicResolver.query(mMusicUri, null, null, null, null);

        mSongList = SongUtil.getAllSongsList(mMusicCursor);
        mSongsListSingleton.getInstance().setSongsList(mSongList);

        mSongsCount.setText(SongUtil.getDisplayTotalSongsString(mSongList.size()));
    }

}