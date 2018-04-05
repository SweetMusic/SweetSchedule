package com.schedule.sweet.sweetschedule.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schedule.sweet.sweetschedule.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Schedule.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Schedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Schedule extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Schedule_Day schedule_day;
    private Schedule_Week schedule_week;

    private OnFragmentInteractionListener mListener;

    public Schedule() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Schedule.
     */
    // TODO: Rename and change types and number of parameters
    public static Schedule newInstance(String param1, String param2) {
        Schedule fragment = new Schedule();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //实例化子fragment
        schedule_day = Schedule_Day.newInstance("123", "456");
        schedule_week = Schedule_Week.newInstance("123", "456");
        //在Schedule中添加子fragment
        getFragmentManager().beginTransaction().add(R.id.fl_container_schedule, schedule_day, "schedule_Day")
                .add(R.id.fl_container_schedule, schedule_week, "schedule_Week")
                .hide(schedule_week).commitAllowingStateLoss();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn1 = view.findViewById(R.id.btn_1);
        btn2 = view.findViewById(R.id.btn_2);
        btn3 = view.findViewById(R.id.btn_3);
        btn4 = view.findViewById(R.id.btn_4);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Schedule_Day m = (Schedule_Day) getActivity().getFragmentManager().findFragmentByTag("schedule_Day");
//                TextView tv;
//                tv = new TextView(getActivity());
//                tv.setText(R.string.app_name);
//                tv.setBackgroundResource(R.drawable.textview_border);
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
//                lp.setMargins(0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()),0,0);
//                lp.setMarginStart((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics()));
//                tv.setLayoutParams(lp);
//                tv.setWidth(100);
//                tv.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
//                tv.setHeight(200);
//                tv.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()));
//                m.addView(tv);
//                Log.d("click", "---- click ----");
//            }
//        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Schedule_Week m = (Schedule_Week) getActivity().getFragmentManager().findFragmentByTag("schedule_Week");
                TextView tv;
                tv = new TextView(getActivity());
                tv.setText(R.string.app_name);
                tv.setBackgroundResource(R.drawable.textview_border);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
                lp.setMargins(0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics()),0,0);
                lp.setMarginStart((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics()));
                tv.setLayoutParams(lp);
                tv.setWidth(100);
                tv.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
                tv.setHeight(50);
                tv.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()));
                m.addView(tv);
                Log.d("click", "---- click ----");
            }
        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Schedule_Day m = (Schedule_Day) getActivity().getFragmentManager().findFragmentByTag("schedule_Day");
//                TextView tv;
//                tv = new TextView(getActivity());
//                tv.setText(R.string.app_name);
//                tv.setBackgroundResource(R.drawable.textview_border);
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
//                lp.setMargins(0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 650, getResources().getDisplayMetrics()),0,0);
//                lp.setMarginStart((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics()));
//                tv.setLayoutParams(lp);
//                tv.setWidth(100);
//                tv.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
//                tv.setHeight(200);
//                tv.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()));
//                m.addView(tv);
//                Log.d("click", "---- click2 ----");
//            }
//        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Schedule_Week m = (Schedule_Week) getActivity().getFragmentManager().findFragmentByTag("schedule_Week");
                TextView tv;
                tv = new TextView(getActivity());
                tv.setText(R.string.app_name);
                tv.setBackgroundResource(R.drawable.textview_border);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
                lp.setMargins(0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics()),0,0);
                lp.setMarginStart((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics()));
                tv.setLayoutParams(lp);
                tv.setWidth(100);
                tv.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
                tv.setHeight(200);
                tv.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()));
                m.addView(tv);
                Log.d("click", "---- click2 ----");
            }
        });
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Schedule_Day m = (Schedule_Day) getActivity().getFragmentManager().findFragmentByTag("schedule_Day");
//                TextView tv;
//                tv = new TextView(getActivity());
//                tv.setText(R.string.app_name);
//                tv.setBackgroundResource(R.drawable.textview_border);
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
//                lp.setMargins(0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, getResources().getDisplayMetrics()),0,0);
//                lp.setMarginStart((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics()));
//                tv.setLayoutParams(lp);
//                tv.setWidth(100);
//                tv.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
//                tv.setHeight(200);
//                tv.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()));
//                m.addView(tv);
//                Log.d("click", "---- click3 ----");
//            }
//        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Schedule_Week m = (Schedule_Week) getActivity().getFragmentManager().findFragmentByTag("schedule_Week");
                TextView tv;
                tv = new TextView(getActivity());
                tv.setText(R.string.app_name);
                tv.setBackgroundResource(R.drawable.textview_border);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
                lp.setMargins(0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics()),0,0);
                lp.setMarginStart((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, getResources().getDisplayMetrics()));
                tv.setLayoutParams(lp);
                tv.setWidth(100);
                tv.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
                tv.setHeight(200);
                tv.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()));
                m.addView(tv);
                Log.d("click", "---- click3 ----");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Schedule_Day m = (Schedule_Day) getActivity().getFragmentManager().findFragmentByTag("schedule_Day");
                TextView tv;
                tv = new TextView(getActivity());
                tv.setText(R.string.app_name);
                tv.setBackgroundResource(R.drawable.textview_border);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
                lp.setMargins(0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 460, getResources().getDisplayMetrics()),0,0);
                lp.setMarginStart((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics()));
                tv.setLayoutParams(lp);
                tv.setWidth(100);
                tv.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
                tv.setHeight(200);
                tv.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()));
                m.addView(tv);
                Log.d("click", "---- click4 ----");
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
