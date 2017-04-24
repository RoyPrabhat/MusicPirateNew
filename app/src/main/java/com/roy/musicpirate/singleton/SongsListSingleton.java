package com.roy.musicpirate.singleton;


import com.roy.musicpirate.model.Song;

import java.util.ArrayList;

/**
 * @author ROY
 */
public class SongsListSingleton {

    private ArrayList<Song> mSongsList = new ArrayList<>();

    private static SongsListSingleton instance = null;

    public static synchronized SongsListSingleton getInstance() {
        if (instance == null) {
            instance = new SongsListSingleton();
        }

        return instance;
    }

    public void clear() {
        mSongsList = new ArrayList<>();
    }

    public ArrayList<Song> getSongList() {
        return mSongsList;
    }

    public void setSongsList(ArrayList<Song> songsList) {
        mSongsList = songsList;
    }
}
