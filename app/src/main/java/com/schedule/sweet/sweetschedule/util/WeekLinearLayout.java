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

    private int dayHeight, cvHeight, cvMarginStart, goI, cvMarginTop, cvWidth;

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
        Log.d("-----On-----", "onLayout: " + getChildCount() + changed);
        if(changed){
            for(int i = 0;i < getChildCount();i++)
            {
                dayHeight = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50
                        , getResources().getDisplayMetrics()) ;
                cvMarginStart = ((MarginLayoutParams) getChildAt(i).getLayoutParams()).getMarginStart();
                cvMarginTop = ((MarginLayoutParams) getChildAt(i).getLayoutParams()).topMargin;
                cvWidth = getChildAt(i).getMeasuredWidth();
                cvHeight = getChildAt(i).getMeasuredHeight();
                Log.d("onLayout", "onLayout: "+i);
                Log.d("msg", "cvMarginTop: "+ cvMarginTop + "   cvMarginStart"+ cvMarginStart);
                dayLayout(getChildAt(i),1);
            }
        }
    }

    private void dayLayout(View childView, int num){
        boolean go = false;
        if (getChildCount() != 0 && !getChildAt(0).equals(childView) && getChildCount() != 1)
        {
            for(int i = 0;i < getChildCount();i++)
            {
                Log.d("for", "-------"+num+" : "+i+"----------");
                if(getChildAt(i).equals(childView) && i != getChildCount()-1){
                    Log.d("equals", "eqqqqq");
                    continue;
                }
                //top check
                if(getChildAt(i).getTop() - cvMarginTop * num >= dayHeight * (num - 1) - 1
                        && getChildAt(i).getTop() - cvMarginTop * num <= dayHeight * (num - 1) + 1){
                    //width check
                    Log.d("height", "hehhhehe");
                    if((cvMarginStart + cvWidth <=
                            ((MarginLayoutParams)getChildAt(i).getLayoutParams()).getMarginStart()) ||
                            (cvMarginStart >=
                                    ((MarginLayoutParams)getChildAt(i).getLayoutParams()).getMarginStart() +
                                            getChildAt(i).getMeasuredWidth())){
                        Log.d("2", cvMarginStart+"");
//                        childView.layout(getChildAt(i).getMeasuredWidth() * (num-1) - (num-1) * ((MarginLayoutParams)getChildAt(i).getLayoutParams()).getMarginStart()
//                                ,cvMarginStart,getChildAt(i).getMeasuredWidth() * (num-1) - (num-1) * ((MarginLayoutParams)getChildAt(i).getLayoutParams()).getMarginStart() + cvWidth
//                                ,cvMarginStart + cvHeight);
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
                    childView.layout(cvMarginStart
                            ,dayHeight * (num-1) + cvMarginTop * num
                            ,cvWidth + cvMarginStart
                            ,cvMarginTop * num + dayHeight * (num-1) + cvHeight );
                    break;
                }
                if(i == getChildCount()-1 && go){
                    childView.layout(cvMarginStart
                            ,getChildAt(goI).getMeasuredHeight() * (num-1) + cvMarginTop * num
                            ,cvMarginStart + cvWidth
                            ,getChildAt(goI).getMeasuredHeight() * (num-1) + cvHeight + cvMarginTop * num);
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
