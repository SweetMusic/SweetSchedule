package com.schedule.sweet.sweetschedule;

import android.app.Fragment;
import android.net.Uri;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.schedule.sweet.sweetschedule.fragment.Contacts;
import com.schedule.sweet.sweetschedule.fragment.Other;
import com.schedule.sweet.sweetschedule.fragment.Schedule;
import com.schedule.sweet.sweetschedule.fragment.TestFragment;
import com.ashokvarma.bottomnavigation.*;


public class MainActivity extends AppCompatActivity implements Schedule.OnFragmentInteractionListener{

    BottomNavigationBar mBottomNavigationBar;
    Schedule schedule;
    Contacts contacts;
    Other other;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化3个fragment
        schedule = Schedule.newInstance("123", "456");
        contacts = Contacts.newInstance("123", "456");
        other = Other.newInstance("123", "456");
        //设置默认的fragment
        getFragmentManager().beginTransaction().add(R.id.fl_container, schedule).commitAllowingStateLoss();

        //初始化并设置BottomNavigationBar
        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        mBottomNavigationBar.setActiveColor(R.color.colorBlue);
        //在BottomNavigationBar里添加子项
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.schedule, "Schedule"))
                .addItem(new BottomNavigationItem(R.drawable.contacts, "Contacts"))
                .addItem(new BottomNavigationItem(R.drawable.other, "Other"))
                .setFirstSelectedPosition(0)
                .initialise();
        //设置监听器
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position)
                {
                    case 0 :
                        if(index != position){
                            getFragmentManager().beginTransaction().replace(R.id.fl_container, schedule)
                                    .commitAllowingStateLoss();
                        }
                        break;
                    case 1 :
                        if(index != position){
                            getFragmentManager().beginTransaction().replace(R.id.fl_container, contacts)
                                    .commitAllowingStateLoss();
                        }
                        break;
                    case 2 :
                        if(index != position){
                            getFragmentManager().beginTransaction().replace(R.id.fl_container, other)
                                    .commitAllowingStateLoss();
                        }
                        break;
                }
                index = position;
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                //todo make a popupWindow
            }
        });
        //TestFragment tf = new TestFragment();
        //getFragmentManager().beginTransaction().add(R.id.fl_container, tf).commitAllowingStateLoss();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
