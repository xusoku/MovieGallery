package com.example.xusoku.util;

/**
 * Author: xoracle ( Liang Ke Jin )
 * Date: 2015/11/16
 */

import android.content.Context;

/**
 * 通用的管理器，管理一些咋项
 * 传递数据
 */
public class CommonManager
{
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}