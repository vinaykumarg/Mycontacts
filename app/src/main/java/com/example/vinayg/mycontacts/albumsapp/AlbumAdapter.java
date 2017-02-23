package com.example.vinayg.mycontacts.albumsapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vinayg.mycontacts.R;

import java.util.List;


/**
 * Created by vinay.g.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    List<Albums> mAlbums;
    public AlbumAdapter(List<Albums> albums){
        this.mAlbums = albums;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_row, parent, false);
        AlbumAdapter.ViewHolder myViewHolder = new AlbumAdapter.ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView album = holder.mAlbumName;
        TextView track = holder.mTrackName;
        Albums albums = mAlbums.get(position);
        album.setText(albums.getAlbumName());
        track.setText(albums.getTrackName());

    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mAlbumName;
        public final TextView mTrackName;

        public ViewHolder(View itemView) {
            super(itemView);
            mAlbumName = (TextView) itemView.findViewById(R.id.albumname);
            mTrackName = (TextView) itemView.findViewById(R.id.track);
        }
    }
}
