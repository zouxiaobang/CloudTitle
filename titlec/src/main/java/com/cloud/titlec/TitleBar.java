package com.cloud.titlec;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloud.titlec.base.ITitleBarStyle;
import com.cloud.titlec.base.OnTitleBarListener;
import com.cloud.titlec.style.TitleBarLightStyle;
import com.cloud.titlec.style.TitleBarNightStyle;
import com.cloud.titlec.style.TitleBarTransparentStyle;

/**
 * @author xb.zou
 * @date 2018/11/12
 * @desc 标题Widget
 **/
public class TitleBar extends FrameLayout implements View.OnClickListener, Runnable {
    /**
     * 标题的样式 -- 默认亮色样式
     */
    private static ITitleBarStyle sTitleBarStyle = new TitleBarLightStyle();
    /**
     * 标题的点击事件
     */
    private OnTitleBarListener mListener;
    private LinearLayout mMainLayout;
    private TextView mLeftView;
    private TextView mTitleView;
    private TextView mRightView;
    private View mLineView;


    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
        initStyle(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        //设置标题的默认宽度
        if (widthMode == MeasureSpec.UNSPECIFIED
                || widthMode == MeasureSpec.AT_MOST) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY);
        }

        //设置标题的默认高度
        if (heightMode == MeasureSpec.UNSPECIFIED
                || heightMode == MeasureSpec.AT_MOST) {
            int titleHeight = sTitleBarStyle.getTitleBarHeight();
            if (titleHeight <= 0) {
                titleHeight = ViewBuilder.getActionBarHeight(getContext());
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(titleHeight, MeasureSpec.EXACTLY);
            }

            int heightSize = MeasureSpec.getSize(heightMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            final Drawable background = getBackground();
            //判断背景是否是一张图片
            if (background instanceof BitmapDrawable) {
                mMainLayout.getLayoutParams().height = heightSize;
                //计算标题栏宽度和图片宽度的比例
                final double ratio = (double) widthSize / (double) background.getIntrinsicWidth();
                heightMeasureSpec = MeasureSpec.makeMeasureSpec((int)
                        (ratio * background.getIntrinsicHeight()), MeasureSpec.EXACTLY);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        if (getOnTitleBarListener() == null) {
            return;
        }

        final int id = v.getId();
        if (id == R.id.bar_id_left_view) {
            getOnTitleBarListener().onLeftClicked(v);
        }else if (id == R.id.bar_id_title_view) {
            getOnTitleBarListener().onTitleClicked(v);
        }else if (id == R.id.bar_id_right_view) {
            getOnTitleBarListener().onRightClicked(v);
        }
    }

    @Override
    public void run() {
        // 更新中间标题的内边距，避免向左或者向右偏移
        final int leftSize = mLeftView.getWidth();
        final int rightSize = mRightView.getWidth();
        if (leftSize != rightSize) {
            if (leftSize > rightSize) {
                mTitleView.setPadding(0, 0, leftSize - rightSize, 0);
            } else {
                mTitleView.setPadding(rightSize - leftSize, 0, 0, 0);
            }
        }

        // 更新 View 状态
        if (!"".equals(mLeftView.getText().toString()) || ViewBuilder.hasCompoundDrawables(mLeftView)) {
            mLeftView.setEnabled(true);
        }
        if (!"".equals(mTitleView.getText().toString()) || ViewBuilder.hasCompoundDrawables(mTitleView)) {
            mTitleView.setEnabled(true);
        }
        if (!"".equals(mRightView.getText().toString()) || ViewBuilder.hasCompoundDrawables(mRightView)) {
            mRightView.setEnabled(true);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 设置监听
        mTitleView.setOnClickListener(this);
        mLeftView.setOnClickListener(this);
        mRightView.setOnClickListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        // 移除监听
        mTitleView.setOnClickListener(null);
        mLeftView.setOnClickListener(null);
        mRightView.setOnClickListener(null);
        super.onDetachedFromWindow();
    }

    public OnTitleBarListener getOnTitleBarListener(){
        return mListener;
    }

    /**
     * 标题的点击事件监听器
     */
    public void setOnTitleBarListener(OnTitleBarListener listener){
        mListener = listener;
    }

    /**
     * 获取标题内容
     */
    public CharSequence getTitle(){
        return mTitleView.getText();
    }

    /**
     * 设置标题内容
     */
    public void setTitle(int stringId){
        setTitle(getResources().getString(stringId));
    }

    /**
     * 设置标题内容
     */
    public void setTitle(CharSequence text){
        mTitleView.setText(text);
        post(this);
    }

    /**
     * 设置左边文本
     */
    public void setLeftText(int stringId){
        setLeftText(getResources().getString(stringId));
    }

    /**
     * 设置左边文本
     */
    public void setLeftText(CharSequence text){
        mLeftView.setText(text);
        post(this);
    }

    /**
     * 获取左边文本
     */
    public CharSequence getLeftText(){
        return mLeftView.getText();
    }

    /**
     * 设置右边文本
     */
    public void setRightText(int stringId){
        setRightText(getResources().getString(stringId));
    }

    /**
     * 设置右边文本
     */
    public void setRightText(CharSequence text){
        mRightView.setText(text);
        post(this);
    }

    /**
     * 获取右边文本
     */
    public CharSequence getRightText(){
        return mRightView.getText();
    }

    /**
     * 设置左边文本颜色
     */
    public void setLeftTextColor(int color){
        mLeftView.setTextColor(color);
    }

    /**
     * 设置右边文本颜色
     */
    public void setRightTextColor(int color){
        mRightView.setTextColor(color);
    }

    /**
     * 设置标题文本颜色
     */
    public void setTitleTextColor(int color){
        mTitleView.setTextColor(color);
    }


    /**
     * 设置左边图标
     */
    public void setLeftIcon(int iconId){
        if (iconId > 0){
            setLeftIcon(getContext().getResources().getDrawable(iconId));
        }
    }

    /**
     * 设置左边图标
     */
    public void setLeftIcon(Drawable drawable){
        mLeftView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        post(this);
    }

    /**
     * 设置右边图标
     */
    public void setRightIcon(int iconId){
        if (iconId > 0){
            setRightIcon(getContext().getResources().getDrawable(iconId));
        }
    }

    /**
     * 设置右边图标
     */
    public void setRightIcon(Drawable drawable){
        mRightView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        post(this);
    }

    /**
     * 设置左边文本的点击背景
     */
    public void setLeftBackground(int bgId){
        if (bgId > 0){
            mLeftView.setBackgroundResource(bgId);
        }
    }

    /**
     * 设置左边文本的点击背景
     */
    public void setLeftBackground(Drawable drawable){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mLeftView.setBackground(drawable);
        }else {
            mLeftView.setBackgroundDrawable(drawable);
        }
    }
    /**
     * 设置右边文本的点击背景
     */
    public void setRightBackground(int bgId){
        if (bgId > 0){
            mRightView.setBackgroundResource(bgId);
        }
    }

    /**
     * 设置右边文本的点击背景
     */
    public void setRightBackground(Drawable drawable){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mRightView.setBackground(drawable);
        }else {
            mRightView.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 设置左边文本大小
     */
    public void setLeftTextSize(int unit, int size){
        mLeftView.setTextSize(unit, size);
    }

    /**
     * 设置右边文本大小
     */
    public void setRightTextSize(int unit, int size){
        mRightView.setTextSize(unit, size);
    }

    /**
     * 设置标题文本大小
     */
    public void setTitleTextSize(int unit, int size){
        mTitleView.setTextSize(unit, size);
    }

    /**
     * 设置分割线是否可见
     */
    public void setLineVisible(boolean visible){
        mLineView.setVisibility(visible? VISIBLE: GONE);
    }

    /**
     * 设置分割线颜色
     */
    public void setLineColor(int color){
        mLineView.setBackgroundColor(color);
    }

    /**
     * 设置分割线大小
     */
    public void setLineSize(int size){
        ViewGroup.LayoutParams layoutParams = mLineView.getLayoutParams();
        layoutParams.height = size;
        mLineView.setLayoutParams(layoutParams);
    }

    /**
     * 获取主要的布局对象
     */
    public LinearLayout getMainLayout() {
        return mMainLayout;
    }

    /**
     * 获取左标题View对象
     */
    public TextView getLeftView() {
        return mLeftView;
    }

    /**
     * 获取标题View对象
     */
    public TextView getTitleView() {
        return mTitleView;
    }

    /**
     * 获取右标题View对象
     */
    public TextView getRightView() {
        return mRightView;
    }

    /**
     * 获取分割线View对象
     */
    public View getLineView() {
        return mLineView;
    }

    /**
     * 统一样式
     * 建议在Application中设置
     */
    public static void initStyle(ITitleBarStyle style) {
        TitleBar.sTitleBarStyle = style;
    }



    private void initView(Context context) {
        ViewBuilder builder = new ViewBuilder(context);
        mMainLayout = builder.getMainLayout();
        mLineView = builder.getLineView();
        mTitleView = builder.getTitleView();
        mLeftView = builder.getLeftView();
        mRightView = builder.getRightView();

        mLeftView.setEnabled(false);
        mTitleView.setEnabled(false);
        mRightView.setEnabled(false);

        mMainLayout.addView(mLeftView);
        mMainLayout.addView(mTitleView);
        mMainLayout.addView(mRightView);

        addView(mMainLayout, 0);
        addView(mLineView, 1);
    }

    private void initStyle(AttributeSet attrs) {
        final TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);

        final ITitleBarStyle style;
        // 样式设置
        switch (ta.getInt(R.styleable.TitleBar_bar_style, 0)) {
            case 0x10:
                style = new TitleBarLightStyle();
                break;
            case 0x20:
                style = new TitleBarNightStyle();
                break;
            case 0x30:
                style = new TitleBarTransparentStyle();
                break;
            default:
                style = TitleBar.sTitleBarStyle;
                break;
        }

        // 标题设置
        if (ta.hasValue(R.styleable.TitleBar_title_left)) {
            setLeftText(ta.getString(R.styleable.TitleBar_title_left));
        }

        if (ta.hasValue(R.styleable.TitleBar_title)) {
            setTitle(ta.getString(R.styleable.TitleBar_title));
        } else {
            // 如果当前上下文对象是Activity，就获取Activity的标题
            if (getContext() instanceof Activity) {
                CharSequence label = ViewBuilder.getActivityLabel((Activity) getContext());
                if (label != null && !"".equals(label.toString())) {
                    setTitle(label);
                }
            }
        }

        // 图标设置
        if (ta.hasValue(R.styleable.TitleBar_title_right)) {
            setRightText(ta.getString(R.styleable.TitleBar_title_right));
        }

        if (ta.hasValue(R.styleable.TitleBar_icon_left)) {
            setLeftIcon(getContext().getResources().getDrawable(ta.getResourceId(R.styleable.TitleBar_icon_left, 0)));
        }else {
            if (ta.getBoolean(R.styleable.TitleBar_icon_back, style.getBackIconResource() > 0)) {
                // 显示返回图标
                setLeftIcon(getContext().getResources().getDrawable(style.getBackIconResource()));
            }
        }

        if (ta.hasValue(R.styleable.TitleBar_icon_right)) {
            setRightIcon(getContext().getResources().getDrawable(ta.getResourceId(R.styleable.TitleBar_icon_right, 0)));
        }

        // 文字颜色设置
        setLeftTextColor(ta.getColor(R.styleable.TitleBar_color_left, style.getLeftTextColor()));
        setTitleTextColor(ta.getColor(R.styleable.TitleBar_color_title, style.getTitleTextColor()));
        setRightTextColor(ta.getColor(R.styleable.TitleBar_color_right, style.getRightTextColor()));

        // 文字大小设置
        setLeftTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(R.styleable.TitleBar_size_left, ViewBuilder.sp2px(getContext(), style.getLeftTextSize())));
        setTitleTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(R.styleable.TitleBar_size_title, ViewBuilder.sp2px(getContext(), style.getTitleTextSize())));
        setRightTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(R.styleable.TitleBar_size_right, ViewBuilder.sp2px(getContext(), style.getRightTextSize())));

        // 背景设置
        setLeftBackground(ta.getResourceId(R.styleable.TitleBar_background_left, style.getLeftBackground()));
        setRightBackground(ta.getResourceId(R.styleable.TitleBar_background_right, style.getRightBackground()));

        // 分割线设置
        setLineVisible(ta.getBoolean(R.styleable.TitleBar_line_visible, style.isLineVisible()));
        setLineColor(ta.getColor(R.styleable.TitleBar_line_color, style.getLineColor()));
        setLineSize(ta.getDimensionPixelSize(R.styleable.TitleBar_line_size, style.getLineSize()));

        // 回收TypedArray
        ta.recycle();

        // 设置默认背景
        if (getBackground() == null) {
            setBackgroundColor(style.getBackgroundColor());
        }
    }
}
