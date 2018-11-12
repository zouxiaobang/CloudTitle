package com.cloud.titlec.style;

import com.cloud.titlec.R;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc 晚间标题样式
 **/
public class TitleBarNightStyle extends TitleBarLightStyle {
    @Override
    public int getBackgroundColor() {
        return 0xFF000000;
    }

    @Override
    public int getBackIconResource() {
        return R.mipmap.bar_icon_back_white;
    }

    @Override
    public int getLeftTextColor() {
        return 0xCCFFFFFF;
    }

    @Override
    public int getTitleTextColor() {
        return 0xEEFFFFFF;
    }

    @Override
    public int getRightTextColor() {
        return 0xCCFFFFFF;
    }

    @Override
    public int getLineColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public int getLeftBackground() {
        return R.drawable.bar_selector_selectable_black;
    }

    @Override
    public int getRightBackground() {
        return R.drawable.bar_selector_selectable_black;
    }
}
