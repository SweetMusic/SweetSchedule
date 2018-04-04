package com.schedule.sweet.sweetschedule.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by sweet on 2018/4/1.
 *
 */

public class WeekLinearLayout extends LinearLayout {

    private int dayWidth, cvMarginStart, goI, cvMarginTop, cvHeight, cvWidth;

    public WeekLinearLayout(Context context) {
        super(context);
    }

    public WeekLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeekLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WeekLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("-----On-----", "onLayout: " + getChildCount());
        if(changed){
            for(int i = 0;i < getChildCount();i++)
            {
                cvMarginStart = ((MarginLayoutParams) getChildAt(0).getLayoutParams()).getMarginStart();
                cvMarginTop = ((MarginLayoutParams) getChildAt(i).getLayoutParams()).topMargin;
                cvWidth = getChildAt(i).getMeasuredWidth();
                cvHeight = getChildAt(i).getMeasuredHeight();
                Log.d("onLayout", "onLayout: "+i);
                dayLayout(getChildAt(i),1);
            }
        }
    }

    private void dayLayout(View childView, int num){
        boolean go = false;
        dayWidth = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100
                , getResources().getDisplayMetrics()) ;
        if (getChildCount() != 0 && !getChildAt(0).equals(childView) && getChildCount() != 1)
        {
            for(int i = 0;i < getChildCount();i++)
            {
                Log.d("for", "-------"+num+" : "+i+"----------");
                if(getChildAt(i).equals(childView) && i != getChildCount()-1){
                    Log.d("equals", "eqqqqq");
                    continue;
                }
                Log.d("marginStart", cvMarginStart+"");
                Log.d("left", getChildAt(i).getLeft()+getChildAt(i).getMeasuredWidth()+"");
                Log.d("left2", (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100
                        , getResources().getDisplayMetrics()) +"");
                Log.d("width if", getChildAt(i).getLeft() - cvMarginStart * num + ":" + dayWidth * (num - 1));
                //width check
                if(getChildAt(i).getLeft() - cvMarginStart * num >= dayWidth * (num - 1) - 1
                        && getChildAt(i).getLeft() - cvMarginStart * num <= dayWidth * (num - 1) + 1){
                    Log.d("cv.top", cvMarginTop+"");
                    Log.d("cv.height", cvHeight+"");
                    Log.d("others.top",  ((MarginLayoutParams)getChildAt(i).getLayoutParams()).topMargin+"");
                    Log.d("others.height",  getChildAt(i).getMeasuredHeight()+"");
                    //top check
                    if((cvMarginTop + cvHeight <=
                            ((MarginLayoutParams)getChildAt(i).getLayoutParams()).topMargin) ||
                            (cvMarginTop >=
                                    ((MarginLayoutParams)getChildAt(i).getLayoutParams()).topMargin +
                                            getChildAt(i).getMeasuredHeight())){
                        Log.d("2", cvMarginTop+"");
//                        childView.layout(getChildAt(i).getMeasuredWidth() * (num-1) - (num-1) * ((MarginLayoutParams)getChildAt(i).getLayoutParams()).getMarginStart()
//                                ,cvMarginTop,getChildAt(i).getMeasuredWidth() * (num-1) - (num-1) * ((MarginLayoutParams)getChildAt(i).getLayoutParams()).getMarginStart() + cvWidth
//                                ,cvMarginTop + cvHeight);
                        go = true;
                        goI = i;
                        continue;
                    }
                    else if(i != getChildCount()-1 && !go){
                        Log.d("re", "reerer");
                        dayLayout(childView, ++num);
                        break;
                    }
                    if(i != getChildCount()-1 && go){
                        dayLayout(childView, ++num);
                        break;
                    }
                }
                if(i == getChildCount()-1 && !go)
                {
                    Log.d("3", "789");
                    childView.layout(dayWidth * (num-1) + cvMarginStart * num
                            ,cvMarginTop
                            ,cvWidth + dayWidth * (num-1) +cvMarginStart * num
                            ,cvMarginTop + cvHeight);
                    break;
                }
                if(i == getChildCount()-1 && go){
                    childView.layout(getChildAt(goI).getMeasuredWidth() * (num-1) + cvMarginStart * num
                            ,cvMarginTop
                            ,getChildAt(goI).getMeasuredWidth() * (num-1) + cvWidth + cvMarginStart * num
                            ,cvMarginTop + cvHeight);
                    break;
                }
            }

        }
        else{
            Log.d("else","121321312312312");
            childView.layout(cvMarginStart
                    ,cvMarginTop
                    ,cvWidth + cvMarginStart
                    ,cvMarginTop + cvHeight);
        }

    }

}
