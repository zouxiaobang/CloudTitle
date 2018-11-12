package com.cloud.titlec.base;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc 标题样式接口
 **/
public interface ITitleBarStyle {
    /**
     * 标题栏高度 -- 默认：ActionBar高度
     */
    int getTitleBarHeight();

    /**
     * 背景颜色
     */
    int getBackgroundColor();

    /**
     * 返回按钮图标
     */
    int getBackIconResource();

    /**
     * 左边文本颜色
     */
    int getLeftTextColor();

    /**
     * 标题文本颜色
     */
    int getTitleTextColor();

    /**
     * 右边文本颜色
     */
    int getRightTextColor();

    /**
     * 左边文本大小
     */
    float getLeftTextSize();

    /**
     * 标题文本大小
     */
    float getTitleTextSize();

    /**
     * 右边文本大小
     */
    float getRightTextSize();

    /**
     * 分割线是否可见
     */
    boolean isLineVisible();

    /**
     * 分割线颜色
     */
    int getLineColor();

    /**
     * 分割线宽度
     */
    int getLineSize();

    /**
     * 左边背景资源
     */
    int getLeftBackground();

    /**
     * 右边背景资源
     */
    int getRightBackground();
}
