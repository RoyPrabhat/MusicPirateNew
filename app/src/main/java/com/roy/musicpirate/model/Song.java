package com.roy.musicpirate.model;

/**
 * @author ROY
 * immutable class for storing information about a particular class
 */
public class Song {
    private long mId;
    private String mTitle;
    private String mArtist;
    private String mAlbum;
    private int mDuration;

    public Song(Builder builder) {
        mId = builder.mId;
        mTitle = builder.mTitle;
        mArtist = builder.mArtist;
        mAlbum = builder.mAlbum;
        mDuration = builder.mDuration;
    }


    public long getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public String getArtist() {
        return mArtist;
    }
    public String getAlbum() {
        return mAlbum;
    }
    public int getDuration() {
        return mDuration;
    }

    public static class Builder {
        private long mId;
        private String mTitle;
        private String mArtist;
        private String mAlbum;
        private int mDuration;

        public Builder(long id, String title, String artist, String album, int duration) {
            mId = id;
            mTitle = title;
            mArtist = artist;
            mAlbum = album;
            mDuration = duration;
        }

        public Builder id(long id) {
            this.mId = id;
            return this;
        }

        public Builder title(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder artist(String artist) {
            this.mArtist = artist;
            return this;
        }

        public Builder album(String album) {
            this.mAlbum = album;
            return this;
        }

        public Builder duration(int duration) {
            this.mDuration = duration;
            return this;
        }


        public Song build() {
            return new Song(this);
        }
    }

}
