package com.example.vinayg.mycontacts.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.vinayg.mycontacts.R;

public class MainActivity extends AppCompatActivity {


    private FloatingActionButton fabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Vinay"));
        tabLayout.addTab(tabLayout.newTab().setText("Manasa"));
        tabLayout.addTab(tabLayout.newTab().setText("Saili"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        fabBtn = (FloatingActionButton) findViewById(R.id.fab);
        fabBtn.show();
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DialogActivity.class);
                startActivity(intent);
            }
        });
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final pagerAdapter adapter = new pagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition()>0){
                    fabBtn.hide();
//                    Toast.makeText(getApplication(),tab.getPosition()+"",Toast.LENGTH_SHORT).show();
                }
                else {
                    fabBtn.show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
