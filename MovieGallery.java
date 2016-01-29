package com.example.xusoku.viewpagertest;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;

/**
 * Created by xusoku on 2016/1/27.
 */
public class MovieGallery extends Gallery
{
    private int mCenterX;
    private float mZoomRatio = 0.2F;
    public MovieGallery(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        setStaticTransformationsEnabled(true);
        setCallbackDuringFling(false);
    }

    private static int getViewCenterX(View paramView)
    {
        return paramView.getLeft() + paramView.getWidth() / 2;
    }

    private int getCenterOfCoverflow()
    {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    }

    public float getViewZoomRatio(View paramView)
    {
        float width = paramView.getWidth();
        int x = this.mCenterX - getViewCenterX(paramView);

        if (Math.abs(x) > width) {
            return 1.0F;
        }

        return Math.abs(1 + (width - Math.abs(x)) / width * mZoomRatio);
    }

    private void scaleParamView(View paramView, Transformation paramTransformation)
    {
        Matrix matrix = paramTransformation.getMatrix();
        float ratio = getViewZoomRatio(paramView);
        matrix.setScale(ratio, ratio);
        int height = paramView.getHeight();
        int width = paramView.getWidth();
        matrix.preTranslate(-(width / 2), -(height / 2));
        matrix.postTranslate(width / 2, height / 2);
        Log.e("aa", ratio + "");
    }

    @Override
    protected boolean getChildStaticTransformation(View paramView, Transformation paramTransformation)
    {
        scaleParamView(paramView, paramTransformation);
        if (paramView == getSelectedView()) {
            paramTransformation.setAlpha(1f);
        }
        else {
            paramTransformation.setAlpha(0.6f);
        }
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
        super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public void setZoomRatio(float paramFloat)
    {
        this.mZoomRatio = paramFloat;
    }
}
