package com.example.vinayg.mycontacts.myapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinayg.mycontacts.R;
import com.example.vinayg.mycontacts.helper.Contact;
import com.example.vinayg.mycontacts.helper.SimpleItemTouchHelperCallback;

import java.util.List;



/**
 * Created by vinay.g on 08-Feb-17.
 */

public class TabFragment1 extends Fragment {
    private static final String TAG = TabFragment1.class.getName();
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Contact> contacts;
    private String[] myDataset;
    private ContactsDataSource mContactsDataSource;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mContactsDataSource = new ContactsDataSource(getContext());
        mContactsDataSource.open();
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return view;
    }

    private void addcallbacks() {
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume()");
        contacts = mContactsDataSource.getAllContacts();
        mAdapter = new MyAdapter(contacts);
        mRecyclerView.setAdapter(mAdapter);
        addcallbacks();

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()");
    }

    public void setdata() {
        myDataset = new String[]{"Afghanistan;Kabul",
                "Armenia;Yerevan",
                "Azerbaijan;Baku",
                "Bahrain;Manama",
                "Bangladesh;Dhaka",
                "Bhutan;Thimphu",
                "Brunei;Bandar Seri Begawan",
                "Cambodia;Phnom Penh",
                "China;Beijing",
                "Cyprus;Nicosia",
                "Georgia;Tbilisi",
                "India;New Delhi",
                "Indonesia;Jakarta",
                "Iran;Tehran",
                "Iraq;Baghdad",
                "Israel;Jerusalem",
                "Japan;Tokyo",
                "Jordan;Amman",
                "Kazakhstan;Astana",
                "Kuwait;Kuwait City",
                "Kyrgyzstan;Bishkek",
                "Laos;Vientiane",
                "Lebanon;Beirut",
                "Malaysia;Kuala Lumpur",
                "Maldives;Male",
                "Mongolia;Ulaanbaatar",
                "Myanmar(Burma);Naypyidaw",
                "Nepal;Kathmandu",
                "North Korea;Pyongyang",
                "Oman;Muscat",
                "Pakistan;Islamabad",
                "Palestine;Ramallah",
                "Philippines;Manila",
                "Qatar;Doha",
                "Russia;Moscow",
                "Saudi Arabia;Riyadh",
                "Singapore;Singapore",
                "South Korea;Seoul",
                "Sri Lanka;Sri Jayawardenepura Kotte",
                "Syria;Damascus",
                "Taiwan;Taipei",
                "Tajikistan;Dushanbe",
                "Thailand;Bangkok",
                "Timor-Leste;Dili",
                "Turkey;Ankara",
                "Turkmenistan;Ashgabat",
                "United Arab Emirates;Abu Dhabi",
                "Uzbekistan;Tashkent",
                "Vietnam;Hanoi",
                "Yemen;Sana"};
    }
}
