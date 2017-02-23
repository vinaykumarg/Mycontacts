package com.example.vinayg.mycontacts.myapp;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinayg.mycontacts.R;
import com.example.vinayg.mycontacts.helper.Contact;
import com.example.vinayg.mycontacts.helper.ItemTouchHelperAdapter;
import com.example.vinayg.mycontacts.helper.ItemTouchHelperViewHolder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by vinay.g.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private List<Contact> mContacts;
    ContactsDataSource mDataSource;
    Context mContext;
    private int mPosition;
    public MyAdapter(ContactsDataSource contactsDataSource, Context context) {
        mDataSource = contactsDataSource;
        mContacts = mDataSource.getAllContacts();
        mContext = context;
    }

    public void setContacts(List<Contact> contacts) {
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
        ImageView imageView = holder.mImageView;
        imageView.setImageBitmap(retrieveContactPhoto(mContext,data.getPhnumber()));
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
        mDataSource.deleteContact(mContacts.get(position));
        mContacts.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mContacts.size());


    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final TextView mName;
        public final TextView mPhoneNumber;
        public final ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.contactsname);
            mPhoneNumber = (TextView) itemView.findViewById(R.id.phonenumber);
            mImageView = (ImageView) itemView.findViewById(R.id.list_image);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.BLUE);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
    public Bitmap retrieveContactPhoto(Context context, String number) {
        ContentResolver contentResolver = context.getContentResolver();
        String contactId = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID};

        Cursor cursor =
                contentResolver.query(
                        uri,
                        projection,
                        null,
                        null,
                        null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
            }
            cursor.close();
        }

        Bitmap photo = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.default_image);
        if (contactId!=null) {
            try {
                InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                        ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(contactId)));

                if (inputStream != null) {
                    photo = BitmapFactory.decodeStream(inputStream);
                }

                if (inputStream != null) {
                    inputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return photo;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);

    }
}
