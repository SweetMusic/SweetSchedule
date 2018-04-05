package com.schedule.sweet.sweetschedule;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.schedule.sweet.sweetschedule.fragment.Contacts;
import com.schedule.sweet.sweetschedule.fragment.Other;
import com.schedule.sweet.sweetschedule.fragment.Schedule;
import com.schedule.sweet.sweetschedule.fragment.Schedule_Day;
import com.schedule.sweet.sweetschedule.fragment.Schedule_Week;
import com.schedule.sweet.sweetschedule.fragment.TestFragment;
import com.ashokvarma.bottomnavigation.*;


public class MainActivity extends AppCompatActivity implements Schedule.OnFragmentInteractionListener,Contacts.OnFragmentInteractionListener,Other.OnFragmentInteractionListener,Schedule_Day.OnFragmentInteractionListener,Schedule_Week.OnFragmentInteractionListener{

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
                    View popupView = getLayoutInflater().inflate(R.layout.popup_schedule, null);
                    popup_schedule = new PopupWindow(popupView
                            , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 96, getResources().getDisplayMetrics())
                            , ViewGroup.LayoutParams.WRAP_CONTENT);
                    popup_schedule.setOutsideTouchable(true);
                    //设置背景，不然点击取消无效
                    popup_schedule.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popup_schedule.setFocusable(true);
                    popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                    Log.d("pop", mBottomNavigationBar.getMeasuredHeight()+"");
                    //获取mBottomNavigationBar在屏幕的高度，点在左下角
                    int[] i = new int[2];
                    mBottomNavigationBar.getLocationOnScreen(i);
                    //获取屏幕高度
                    DisplayMetrics dm = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(dm);
                    int screenHeight = dm.heightPixels;
                    Log.d("i", "i[0]: "+i[0] + "    i[1]:"+i[1]+"        h:"+screenHeight);
                    //设置popupW的位置，注意这里用的是bottom
                    popup_schedule.showAtLocation(mBottomNavigationBar,Gravity.BOTTOM
                            ,-(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics())
                            ,screenHeight-i[1]+mBottomNavigationBar.getMeasuredHeight());
//                    popup_schedule.showAsDropDown(mBottomNavigationBar
//                            ,(i[0]+mBottomNavigationBar.getMeasuredWidth()/2)-popupView.getMeasuredWidth()/2
//                            ,i[1]-popupView.getMeasuredHeight()-100
//                            ,Gravity.NO_GRAVITY);
                    TextView tv1 = popupView.findViewById(R.id.popup_tv1);
                    TextView tv2 = popupView.findViewById(R.id.popup_tv2);
                    TextView tv3 = popupView.findViewById(R.id.popup_tv3);

                    tv1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getFragmentManager().findFragmentByTag("schedule").getFragmentManager().beginTransaction().show(getFragmentManager().findFragmentByTag("schedule_Day"))
                                    .hide(getFragmentManager().findFragmentByTag("schedule_Week")).commitAllowingStateLoss();
                            popup_schedule.dismiss();
                        }
                    });

                    tv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getFragmentManager().findFragmentByTag("schedule").getFragmentManager().beginTransaction().hide(getFragmentManager().findFragmentByTag("schedule_Day"))
                                    .show(getFragmentManager().findFragmentByTag("schedule_Week")).commitAllowingStateLoss();
                            popup_schedule.dismiss();
                        }
                    });
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
