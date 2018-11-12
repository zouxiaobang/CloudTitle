package com.cloud.titlec.base;

import android.view.View;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc
 **/
public interface OnTitleBarListener {
    /**
     * 左边View被点击
     */
    void onLeftClicked(View view);

    /**
     * 标题被点击
     */
    void onTitleClicked(View view);

    /**
     * 右边View被点击
     */
    void onRightClicked(View view);
}
