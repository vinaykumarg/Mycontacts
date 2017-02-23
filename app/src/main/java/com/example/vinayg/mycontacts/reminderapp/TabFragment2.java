package com.example.vinayg.mycontacts.reminderapp;

/**
 * Created by vinay.g on 08-Feb-17.
 */

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinayg.mycontacts.R;

public class TabFragment2 extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final String TAG = TabFragment2.class.getName();
    private RecyclerView mRecyclerView;
    private ReminderAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CursorLoader cursorLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview1);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        getActivity().getSupportLoaderManager().initLoader(1, null, this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        cursorLoader= new CursorLoader(getContext(), Uri.parse("content://androidcontentproviderdemo.androidcontentprovider.reminders"), null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter = new ReminderAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
