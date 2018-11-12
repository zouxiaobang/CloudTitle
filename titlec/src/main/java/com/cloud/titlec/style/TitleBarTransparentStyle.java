package com.cloud.titlec.style;

import com.cloud.titlec.R;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc 透明标题样式
 **/
public class TitleBarTransparentStyle extends TitleBarNightStyle {
    @Override
    public int getBackgroundColor() {
        return 0x00000000;
    }

    @Override
    public int getLeftTextColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public int getTitleTextColor() {
        return 0x55000000;
    }

    @Override
    public int getRightTextColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public boolean isLineVisible() {
        return false;
    }

    @Override
    public int getLineColor() {
        return 0x00000000;
    }

    @Override
    public int getLeftBackground() {
        return R.drawable.bar_selector_selectable_transparent;
    }

    @Override
    public int getRightBackground() {
        return R.drawable.bar_selector_selectable_transparent;
    }
}
