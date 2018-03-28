package com.schedule.sweet.sweetschedule;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.schedule.sweet.sweetschedule.fragment.Schedule;
import com.schedule.sweet.sweetschedule.fragment.TestFragment;

public class MainActivity extends AppCompatActivity implements Schedule.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Schedule schedule = Schedule.newInstance("123", "456");
        getFragmentManager().beginTransaction().add(R.id.fl_container, schedule).commitAllowingStateLoss();
        //TestFragment tf = new TestFragment();
        //getFragmentManager().beginTransaction().add(R.id.fl_container, tf).commitAllowingStateLoss();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
