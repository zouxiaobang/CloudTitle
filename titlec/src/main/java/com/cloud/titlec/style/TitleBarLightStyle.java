package com.cloud.titlec.style;

import com.cloud.titlec.R;
import com.cloud.titlec.base.ITitleBarStyle;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc 亮色标题样式
 **/
public class TitleBarLightStyle implements ITitleBarStyle {

    @Override
    public int getTitleBarHeight() {
        return 0;
    }

    @Override
    public int getBackgroundColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public int getBackIconResource() {
        return R.mipmap.bar_icon_back_black;
    }

    @Override
    public int getLeftTextColor() {
        return 0xFF666666;
    }

    @Override
    public int getTitleTextColor() {
        return 0xFF222222;
    }

    @Override
    public int getRightTextColor() {
        return 0xFFA4A4A4;
    }

    @Override
    public float getLeftTextSize() {
        return 14;
    }

    @Override
    public float getTitleTextSize() {
        return 16;
    }

    @Override
    public float getRightTextSize() {
        return 14;
    }

    @Override
    public boolean isLineVisible() {
        return true;
    }

    @Override
    public int getLineColor() {
        return 0xFFECECEC;
    }

    @Override
    public int getLineSize() {
        return 1;
    }

    @Override
    public int getLeftBackground() {
        return R.drawable.bar_selector_selectable_white;
    }

    @Override
    public int getRightBackground() {
        return R.drawable.bar_selector_selectable_white;
    }
}
