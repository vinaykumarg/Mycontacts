package com.example.vinayg.mycontacts.albumsapp;

/**
 * Created by vinay.g on 08-Feb-17.
 */

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinayg.mycontacts.R;

import java.util.ArrayList;
import java.util.List;


public class TabFragment3 extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = TabFragment3.class.getName();
    private RecyclerView mRecyclerView;
    private CursorLoader cursorLoader;
    private String[] strings;
    private List<Albums> mAlbums;
    private AlbumAdapter mAlbumAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView()");
        View view = inflater.inflate(R.layout.tab_fragment_3, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        getActivity().getSupportLoaderManager().initLoader(2,null,this);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        cursorLoader= new CursorLoader(getContext(), Uri.parse("content://com.example.sailik.contentprovidertask_20_feb"), null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        strings = data.getColumnNames();
        mAlbums = new ArrayList<Albums>();
        Log.d("data",strings[0]+" "+strings[1]+" "+strings[2]);
        if (data.moveToFirst()){
            do{
                Albums albums = new Albums();
                albums.setId(Integer.parseInt(data.getString(data.getColumnIndex(strings[0]))));
                albums.setAlbumName(data.getString(data.getColumnIndex(strings[1])));
                albums.setTrackName(data.getString(data.getColumnIndex(strings[2])));
                mAlbums.add(albums);
            }while(data.moveToNext());
        }

        mAlbumAdapter = new AlbumAdapter(mAlbums);
        mRecyclerView.setAdapter(mAlbumAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


}
