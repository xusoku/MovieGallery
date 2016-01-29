package com.example.xusoku.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;

import com.example.xusoku.util.CommonManager;
import com.example.xusoku.viewpagertest.R;

/**
 * Created by xusoku on 2016/1/27.
 */
public class MovieGallery1 extends Gallery
{

    private static int mFirstChildWidth;
    private static int mFirstChildPadLeftWidth;
    private int mCenterX;
    private Context context;
    private int c;
    private int mPaddingLeft;
    private boolean isFirstSelect;
    private int offsetX;

    public MovieGallery1(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        this.context = paramContext;
        setStaticTransformationsEnabled(true);
        TypedArray array = paramContext.obtainStyledAttributes(paramAttributeSet, new int[] { getPaddingLeft() });
        this.mPaddingLeft = array.getDimensionPixelSize(0, 0);
        array.recycle();
    }

    private void scaleParamView(View paramView, Transformation paramTransformation)
    {
        paramTransformation.clear();
        paramTransformation.setTransformationType(2);
        Matrix matrix = paramTransformation.getMatrix();
        float f1 = getViewZoomRatio(paramView);
        paramView.getHeight();
        int i = paramView.getWidth();
        matrix.setScale(f1, f1);
        matrix.preTranslate(-(i / 2), -(i / 2));
        matrix.postTranslate(i / 2, i / 2);
        matrix.postTranslate(getOffsetX(), 0.0F);

    }

    private static int getViewCenterX(View paramView)
    {
        return paramView.getLeft() + paramView.getWidth() / 2;
    }

    private int getCenterOfCoverflow()
    {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    }

    private float getOffsetX()
    {
        if (this.isFirstSelect)
        {
            mFirstChildWidth = getChildAt(0).getWidth();
            mFirstChildPadLeftWidth = getChildAt(0).getPaddingLeft();
            this.isFirstSelect = false;
        }
//        this.offsetX = (mFirstChildWidth / 2 + mFirstChildPadLeftWidth + this.mPaddingLeft - this.c / 2 + CommonManager.dip2px(context,150 *2f));
        float aa=context.getResources().getDimension(R.dimen.movie_gallery_img_w);
        this.offsetX = (mFirstChildWidth / 2 + mFirstChildPadLeftWidth + this.mPaddingLeft - this.c / 2 + (int)(aa));
        return this.offsetX;
    }

    public float getViewZoomRatio(View paramView)
    {
        float width = paramView.getWidth();
        int x = this.mCenterX - getViewCenterX(paramView);
        if (Math.abs(x) > width) {
            return 1.0F;
        }
        return Math.abs(1 + (width - Math.abs(x)) / width *  0.21f);
    }

    protected boolean getChildStaticTransformation(View paramView, Transformation paramTransformation)
    {
        scaleParamView(paramView, paramTransformation);
        paramView.invalidate();
        return true;
    }

    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
        super.onFling(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2);
        return false;
    }

    protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        this.mCenterX = getCenterOfCoverflow();
        if (!this.isFirstSelect)
        {
            this.c = (paramInt1 * 2);
            getLayoutParams().width = this.c;
            this.isFirstSelect = true;
        }
        super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
        paramMotionEvent.offsetLocation(-this.offsetX, 0.0F);
        return super.onTouchEvent(paramMotionEvent);
    }
}