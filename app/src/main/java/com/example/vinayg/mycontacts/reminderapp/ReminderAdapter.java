package com.example.vinayg.mycontacts.reminderapp;

/**
 * Created by vinay.g.
 */

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vinayg.mycontacts.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinay.g.
 */
public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder>{
    Cursor mCursor;
    private String[] strings;
    List<Reminder> Reminders;

    public ReminderAdapter(Cursor cursor) {
        mCursor = cursor;
    }

    @Override
    public ReminderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_row, parent, false);
        ReminderAdapter.ViewHolder myViewHolder = new ReminderAdapter.ViewHolder(view);
        Reminders = new ArrayList<Reminder>();
        updatelist();
        return myViewHolder;
    }

    private void updatelist() {
        strings = mCursor.getColumnNames();

        if (mCursor.moveToFirst()){
            do{
                Reminder data = new Reminder();
                data.setId(Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(strings[0]))));
                data.setNameTask(mCursor.getString(mCursor.getColumnIndex(strings[1])));
                data.setTimeTask(mCursor.getString(mCursor.getColumnIndex(strings[2])));
                Reminders.add(data);
            }while(mCursor.moveToNext());
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView title = holder.mTitle;
        TextView time = holder.mTime;
        Reminder data = Reminders.get(position);
        title.setText(data.getNameTask());
        time.setText(data.getTimeTask());
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTitle;
        public final TextView mTime;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title_text_reminder);
            mTime = (TextView) itemView.findViewById(R.id.time_text_reminder);
        }

    }


}

