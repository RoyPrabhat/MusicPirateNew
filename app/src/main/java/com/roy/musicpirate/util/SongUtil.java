package com.roy.musicpirate.util;

import android.database.Cursor;
import android.provider.MediaStore;


import com.roy.musicpirate.model.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author ROY
 */
public class SongUtil {

    public static final String SONG = " song";
    public static final String SONGS = " songs";
    public static final String MINIMUM_SONG_COUNT = "0 " + SONG;

    public static ArrayList<Song> sortSongsListAlphabetically(ArrayList<Song> songList) {

        Collections.sort(songList, new Comparator<Song>() {
            public int compare(Song a, Song b) {
                return a.getTitle().compareTo(b.getTitle());
            }
        });
        return songList;
    }

    public static ArrayList<Song> getAllSongsList(Cursor mMusicCursor) {
        ArrayList<Song> songList = new ArrayList<>();
        if (mMusicCursor != null && mMusicCursor.moveToFirst()) {
            //get columns
            int titleColumn = mMusicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = mMusicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = mMusicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int albumName = mMusicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ALBUM);

            int duration = mMusicCursor.getColumnIndex
                    (MediaStore.Audio.Media.DURATION);
            //add songs to list
            do {
                long thisId = mMusicCursor.getLong(idColumn);
                String thisTitle = mMusicCursor.getString(titleColumn);
                String thisArtist = mMusicCursor.getString(artistColumn);
                String thisAlbum = mMusicCursor.getString(albumName);
                int thisDuration = mMusicCursor.getInt(duration);
                songList.add(new Song.Builder(thisId, thisTitle, thisArtist, thisAlbum, thisDuration).build());
            }
            while (mMusicCursor.moveToNext());
        }

        return SongUtil.sortSongsListAlphabetically(songList);
    }

    public static String getDisplayTotalSongsString(int size) {
        String totalSongString;
        if(size>1)
            totalSongString = size + SONGS;
        else if ( size == 1 )totalSongString = size + SONG;
        else totalSongString = MINIMUM_SONG_COUNT;
        return totalSongString;
    }

}