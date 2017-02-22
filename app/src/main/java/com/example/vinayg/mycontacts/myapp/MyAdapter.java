package com.example.vinayg.mycontacts.myapp;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vinayg.mycontacts.R;
import com.example.vinayg.mycontacts.helper.Contact;
import com.example.vinayg.mycontacts.helper.ItemTouchHelperAdapter;
import com.example.vinayg.mycontacts.helper.ItemTouchHelperViewHolder;

import java.util.List;

/**
 * Created by vinay.g.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private List<Contact> mContacts;
    ContactsDataSource mContactsDataSource;
    public MyAdapter(List<Contact> contacts) {
        mContacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView name = holder.mName;
        TextView phone = holder.mPhoneNumber;
        Contact data = mContacts.get(position);
        name.setText(data.getName());
        phone.setText(data.getPhnumber());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

    }

    @Override
    public void onItemDismiss(int position) {
        mContacts.remove(position);
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final TextView mName;
        public final TextView mPhoneNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.contactsname);
            mPhoneNumber = (TextView) itemView.findViewById(R.id.phonenumber);
        }

        @Override
        public void onItemSelected() {
            Log.d("selected","item");
            itemView.setBackgroundColor(Color.BLUE);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }


}
