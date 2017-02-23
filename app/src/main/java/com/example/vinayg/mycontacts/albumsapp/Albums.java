package com.example.vinayg.mycontacts.albumsapp;

/**
 * Created by vinay.g.
 */

public class Albums {
    private String mAlbumName,mTrackName;
    private int mId;
    public Albums(){

    }
    public Albums(String albumName, String trackName, int id) {
        mAlbumName = albumName;
        mTrackName = trackName;
        mId = id;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public void setAlbumName(String albumName) {
        mAlbumName = albumName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTrackName() {
        return mTrackName;
    }

    public void setTrackName(String trackName) {
        mTrackName = trackName;
    }
}
