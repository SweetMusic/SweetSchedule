package com.schedule.sweet.sweetschedule.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.schedule.sweet.sweetschedule.R;

/**
 * Created by sweet on 2018/4/1.
 *
 */

public class DayLinearLayout extends LinearLayout {

    private int dayWidth;
    private int goI;

    public DayLinearLayout(Context context) {
        super(context);
    }

    public DayLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DayLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DayLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            for(int i = 0;i < getChildCount();i++)
            {
                Log.d("onLayout", "onLayout: "+i);
                dayLayout(getChildAt(i),1);
            }
        }
    }

    private void dayLayout(View childView, int num){
        boolean go = false;
        dayWidth = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100
                , getResources().getDisplayMetrics()) ;
        if (getChildCount() != 0 && !getChildAt(0).equals(childView))
        {
            for(int i = 0;i < getChildCount();i++)
            {
                Log.d("for", "-------"+num+" : "+i+"----------");
                if(getChildAt(i).equals(childView) && i != getChildCount()-1){
                    Log.d("equals", "eqqqqq");
                    continue;
                }
                Log.d("left", getChildAt(i).getLeft()+getChildAt(i).getMeasuredWidth()+"");
                Log.d("width if", getChildAt(i).getLeft() - ((MarginLayoutParams) childView.getLayoutParams()).getMarginStart() * num + ":" + dayWidth * (num - 1));
                //width check
                if(getChildAt(i).getLeft() - ((MarginLayoutParams) childView.getLayoutParams()).getMarginStart() * num == dayWidth * (num - 1)){
                    Log.d("cv.top", ((MarginLayoutParams) childView.getLayoutParams()).topMargin+"");
                    Log.d("cv.height", childView.getMeasuredHeight()+"");
                    Log.d("others.top",  ((MarginLayoutParams)getChildAt(i).getLayoutParams()).topMargin+"");
                    Log.d("others.height",  getChildAt(i).getMeasuredHeight()+"");
                    //top check
                    if((((MarginLayoutParams) childView.getLayoutParams()).topMargin + childView.getMeasuredHeight() <=
                            ((MarginLayoutParams)getChildAt(i).getLayoutParams()).topMargin) ||
                            (((MarginLayoutParams) childView.getLayoutParams()).topMargin >=
                                    ((MarginLayoutParams)getChildAt(i).getLayoutParams()).topMargin +
                                            getChildAt(i).getMeasuredHeight())){
                        Log.d("2", ((MarginLayoutParams) childView.getLayoutParams()).topMargin+"");
//                        childView.layout(getChildAt(i).getMeasuredWidth() * (num-1) - (num-1) * ((MarginLayoutParams)getChildAt(i).getLayoutParams()).getMarginStart()
//                                ,((MarginLayoutParams) childView.getLayoutParams()).topMargin,getChildAt(i).getMeasuredWidth() * (num-1) - (num-1) * ((MarginLayoutParams)getChildAt(i).getLayoutParams()).getMarginStart() + childView.getMeasuredWidth()
//                                ,((MarginLayoutParams) childView.getLayoutParams()).topMargin + childView.getMeasuredHeight());
                        go = true;
                        goI = i;
                        continue;
                    }
                    else if(i != getChildCount()-1 && !go){
                        Log.d("re", "reerer");
                        dayLayout(childView, ++num);
                        break;
                    }
                    if(go){
                        dayLayout(childView, ++num);
                        break;
                    }
                }
                if(i == getChildCount()-1 && !go)
                {
                    Log.d("3", "789");
                    childView.layout(dayWidth * (num-1) + ((MarginLayoutParams) childView.getLayoutParams()).getMarginStart() * num
                            ,((MarginLayoutParams) childView.getLayoutParams()).topMargin
                            ,childView.getMeasuredWidth() + dayWidth * (num-1) +((MarginLayoutParams) childView.getLayoutParams()).getMarginStart() * num
                            ,((MarginLayoutParams) childView.getLayoutParams()).topMargin + childView.getMeasuredHeight());
                    break;
                }
                if(i == getChildCount()-1 && go){
                    childView.layout(getChildAt(goI).getMeasuredWidth() * (num-1) + ((MarginLayoutParams) childView.getLayoutParams()).getMarginStart() * num
                            ,((MarginLayoutParams) childView.getLayoutParams()).topMargin
                            ,getChildAt(goI).getMeasuredWidth() * (num-1) + childView.getMeasuredWidth() + ((MarginLayoutParams) childView.getLayoutParams()).getMarginStart() * num
                            ,((MarginLayoutParams) childView.getLayoutParams()).topMargin + childView.getMeasuredHeight());
                    break;
                }
            }

        }
        else{
            Log.d("else","121321312312312");
            childView.layout(((MarginLayoutParams) childView.getLayoutParams()).getMarginStart()
                    ,((MarginLayoutParams) childView.getLayoutParams()).topMargin
                    ,childView.getMeasuredWidth() + ((MarginLayoutParams) childView.getLayoutParams()).getMarginStart()
                    ,((MarginLayoutParams) childView.getLayoutParams()).topMargin + childView.getMeasuredHeight());
        }

    }

}
