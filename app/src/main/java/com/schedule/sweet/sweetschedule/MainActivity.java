package com.schedule.sweet.sweetschedule;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.schedule.sweet.sweetschedule.fragment.Contacts;
import com.schedule.sweet.sweetschedule.fragment.Other;
import com.schedule.sweet.sweetschedule.fragment.Schedule;
import com.schedule.sweet.sweetschedule.fragment.Schedule_Day;
import com.schedule.sweet.sweetschedule.fragment.TestFragment;
import com.ashokvarma.bottomnavigation.*;


public class MainActivity extends AppCompatActivity implements Schedule.OnFragmentInteractionListener,Contacts.OnFragmentInteractionListener,Other.OnFragmentInteractionListener,Schedule_Day.OnFragmentInteractionListener{

    private BottomNavigationBar mBottomNavigationBar;
    private Schedule schedule;
    private Contacts contacts;
    private Other other;
    private PopupWindow popup_schedule;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化3个fragment
        schedule = Schedule.newInstance("123", "456");
        contacts = Contacts.newInstance("123", "456");
        other = Other.newInstance("123", "456");
        //添加并设置默认的fragment
        getFragmentManager().beginTransaction().add(R.id.fl_container, schedule, "schedule").commitAllowingStateLoss();
        getFragmentManager().beginTransaction().add(R.id.fl_container, contacts, "contacts").hide(contacts).commitAllowingStateLoss();
        getFragmentManager().beginTransaction().add(R.id.fl_container, other, "other").hide(other).commitAllowingStateLoss();
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
                        getFragmentManager().beginTransaction()
                                .hide(contacts).hide(other).show(schedule)
                                .commitAllowingStateLoss();
                        break;
                    case 1 :
                        getFragmentManager().beginTransaction()
                                .hide(other).hide(schedule).show(contacts)
                                .commitAllowingStateLoss();
                        break;
                    case 2 :
                        getFragmentManager().beginTransaction()
                                .hide(contacts).hide(schedule).show(other)
                                .commitAllowingStateLoss();
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
                if (position == 0)
                {
                    //实例化popupWindow
                    popup_schedule = new PopupWindow(getLayoutInflater().inflate(R.layout.popup_schedule, null)
                            , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics())
                            , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 92, getResources().getDisplayMetrics()));
                    popup_schedule.setOutsideTouchable(true);
                    popup_schedule.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popup_schedule.setFocusable(true);
                    Log.d("pop", (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
                            , popup_schedule.getContentView().getMeasuredHeight(), getResources().getDisplayMetrics())+"");
                    Log.d("count", "onTabReselected: " + mBottomNavigationBar.getChildCount());
                    popup_schedule.showAsDropDown(mBottomNavigationBar,0
                            ,-(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, popup_schedule.getContentView().getMeasuredHeight(), getResources().getDisplayMetrics())
                                    -(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mBottomNavigationBar.getMeasuredHeight(), getResources().getDisplayMetrics()), Gravity.CENTER);
                }
            }
        });
        //TestFragment tf = new TestFragment();
        //getFragmentManager().beginTransaction().add(R.id.fl_container, tf).commitAllowingStateLoss();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
