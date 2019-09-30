package com.zpj.zdialog;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.labo.kaji.swipeawaydialog.SwipeAwayDialogFragment;
import com.zpj.zdialog.base.IDialog;
import com.zpj.zdialog.utils.ScreenUtil;

public class ZDialog extends SwipeAwayDialogFragment implements IDialog {

    FragmentManager fragmentManager;
    int layoutRes;
    int dialogWidth;
    int dialogHeight;
    float dimAmount = 0.2f;
    public int gravity = Gravity.CENTER;
    boolean isCancelableOutside = true;
    boolean cancelable = true;
    View contentView;
    FragmentActivity activity;
    //Dialog动画style
    int animRes;
    private OnViewCreateListener onViewCreateListener;
    private OnDismissListener onDismissListener;
    private OnDialogStartListener onDialogStartListener;
    private static final String FTag = "dialogTag";

    public static ZDialog with(Context context) {
        ZDialog dialog = new ZDialog();
        FragmentActivity activity;
        if (context instanceof FragmentActivity) {
            activity = (FragmentActivity) context;
        } else {
            activity = ((FragmentActivity) ((ContextWrapper) context).getBaseContext());
        }
        dialog.setFragmentActivity(activity);
        return dialog;
    }

    public ZDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if (getLayoutRes() > 0) {
            //调用方通过xml获取view
            view = inflater.inflate(getLayoutRes(), container, false);
            contentView = view;
        } else if (getContentView() != null) {
            //调用方直接传入view
            view = getContentView();
        } else {
            throw new RuntimeException("You must call the setContentView");
        }
        return view;
    }

    protected int getLayoutRes() {
        return layoutRes;
    }

    protected View getContentView() {
        return contentView;
    }

    protected int getDialogWidth() {
        return dialogWidth;
    }

    protected int getDialogHeight() {
        return dialogHeight;
    }

    public boolean isCanceledOnTouchOutside() {
        return isCancelableOutside;
    }

    @Override
    public boolean isCancelable() {
        return cancelable;
    }

    public float getDimAmount() {
        return dimAmount;
    }

    protected int getGravity() {
        return gravity;
    }

    protected int getAnimRes() {
        return animRes;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置默认子View布局
//        contentView = view;
        //回调给调用者，用来设置子View及点击事件等
        if (onViewCreateListener != null) {
            onViewCreateListener.onViewCreate(this, view);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window == null) {
            return;
        }
        //设置背景色透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置Dialog动画效果
        if (getAnimRes() > 0) {
            window.setWindowAnimations(getAnimRes());
        }
        WindowManager.LayoutParams params = window.getAttributes();
        //设置Dialog的Width
        if (getDialogWidth() > 0) {
            params.width = getDialogWidth();
        } else {
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        //设置Dialog的Height
        if (getDialogHeight() > 0) {
            params.height = getDialogHeight();
        } else {
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        //设置屏幕透明度 0.0f~1.0f(完全透明~完全不透明)
        params.dimAmount = getDimAmount();
        params.gravity = getGravity();
        window.setAttributes(params);
        if (onDialogStartListener != null) {
            onDialogStartListener.onStart();
        }
    }

    private void setFragmentActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    public ZDialog setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        return this;
    }

    /**
     * 设置DialogView
     *
     * @param layoutRes 布局文件
     * @return Builder
     */
    public ZDialog setContentView(@LayoutRes int layoutRes) {
        this.layoutRes = layoutRes;
        return this;
    }

    /**
     * 设置DialogView
     *
     * @param dialogView View
     * @return Builder
     */
    public ZDialog setContentView(View dialogView) {
        this.contentView = dialogView;
        return this;
    }


    public ZDialog setSwipeEnable(boolean swipeable) {
        setSwipeable(swipeable);
        return this;
    }

    /**
     * 设置屏幕宽度百分比
     *
     * @param percentage 0.0f~1.0f
     * @return Builder
     */
    public ZDialog setScreenWidthP(float percentage) {
        this.dialogWidth = (int) (ScreenUtil.getScreenWidth(activity) * percentage);
        return this;
    }

    /**
     * 设置屏幕高度百分比
     *
     * @param percentage 0.0f~1.0f
     * @return Builder
     */
    public ZDialog setScreenHeightP(float percentage) {
        this.dialogHeight = (int) (ScreenUtil.getScreenHeight(activity) * percentage);
        return this;
    }

    /**
     * 设置Dialog的宽度
     *
     * @param width 宽度
     * @return Builder
     */
    public ZDialog setWidth(int width) {
        this.dialogWidth = width;
        return this;
    }

    /**
     * 设置Dialog的高度
     *
     * @param height 高度
     * @return Builder
     */
    public ZDialog setHeight(int height) {
        this.dialogHeight = height;
        return this;
    }

    /**
     * 设置背景色色值
     *
     * @param percentage 0.0f~1.0f 1.0f为完全不透明
     * @return Builder
     */
    public ZDialog setWindowBackgroundP(float percentage) {
        this.dimAmount = percentage;
        return this;
    }

    /**
     * 设置Gravity
     *
     * @param gravity Gravity
     * @return Builder
     */
    public ZDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * 设置dialog外点击是否可以让dialog消失
     *
     * @param cancelableOutSide true 则在dialog屏幕外点击可以使dialog消失
     * @return Builder
     */
    public ZDialog setCancelableOutSide(boolean cancelableOutSide) {
        this.isCancelableOutside = cancelableOutSide;
        return this;
    }

    /**
     * 设置是否屏蔽物理返回键
     *
     * @param cancelable true 点击物理返回键可以让dialog消失；反之不消失
     * @return Builder
     */
    public ZDialog setDialogCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    /**
     * 构建子View的listener
     *
     * @param listener IDialog.OnViewCreateListener
     * @return Builder
     */
    public ZDialog setOnViewCreateListener(OnViewCreateListener listener) {
        this.onViewCreateListener = listener;
        return this;
    }

    public ZDialog setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public ZDialog setOnDialogStartListener(OnDialogStartListener onDialogStartListener) {
        this.onDialogStartListener = onDialogStartListener;
        return this;
    }

    /**
     * 设置dialog的动画效果
     *
     * @param animStyle 动画资源文件
     * @return Builder
     */
    public ZDialog setAnimStyle(int animStyle) {
        this.animRes = animStyle;
        return this;
    }


//    /**
//     * 设置默认右侧点击按钮
//     *
//     * @param onclickListener IDialog.OnClickListener
//     * @return Builder
//     */
//    public ZDialog setPositiveButton(OnClickListener onclickListener) {
//        return setPositiveButton("确定", onclickListener);
//    }

//    /**
//     * 设置默认右侧点击按钮及文字
//     *
//     * @param btnStr          右侧文字
//     * @param onclickListener IDialog.OnClickListener
//     * @return Builder
//     */
//    public ZDialog setPositiveButton(String btnStr, OnClickListener onclickListener) {
//        this.positiveBtnListener = onclickListener;
//        this.positiveStr = btnStr;
//        this.showBtnRight = true;
//        return this;
//    }

//    public ZDialog setPositiveButtonTextColor(int color) {
//        this.positiveStrColor = color;
//        return this;
//    }
//
//    /**
//     * 设置左侧按钮
//     *
//     * @param onclickListener IDialog.OnClickListener
//     * @return Builder
//     */
//    public ZDialog setNegativeButton(OnClickListener onclickListener) {
//        return setNegativeButton("取消", onclickListener);
//    }

//    /**
//     * 设置左侧文字及按钮
//     *
//     * @param btnStr          文字
//     * @param onclickListener IDialog.OnClickListener
//     * @return Builder
//     */
//    public ZDialog setNegativeButton(String btnStr, OnClickListener onclickListener) {
//        this.negativeBtnListener = onclickListener;
//        this.negativeStr = btnStr;
//        this.showBtnLeft = true;
//        return this;
//    }

//    public ZDialog setNegativeButtonTextColor(int color) {
//        this.negativeStrColor = color;
//        return this;
//    }

//    /**
//     * 设置默认dialog的title
//     *
//     * @param title 标题
//     * @return Builder
//     */
//    public ZDialog setTitle(String title) {
//        this.titleStr = title;
//        return this;
//    }
//
//    public ZDialog setTitleTextColor(int color) {
//        this.titleTextColor = color;
//        return this;
//    }

//    /**
//     * 设置默认dialog的内容
//     *
//     * @param content 内容
//     * @return Builder
//     */
//    public ZDialog setContent(CharSequence content) {
//        this.contentStr = content;
//        return this;
//    }

//    public ZDialog settextColor(int color) {
//        this.textColor = color;
//        return this;
//    }


    public ZDialog setAnimatorCreateListener(OnAnimatorCreateListener onAnimatorCreateListener) {
        setOnAnimatorCreateListener(onAnimatorCreateListener);
        return this;
    }

    /**
     * 展示Dialog
     *
     */
    public void show() {
        if (getDialog() != null) {
            getDialog().show();
            return;
        }
        if (layoutRes <= 0 && contentView == null) {
            //如果没有设置布局 提供默认设置
            setDefaultOption();
        }
        if (fragmentManager == null) {
            fragmentManager = activity.getSupportFragmentManager();
        }
        show(fragmentManager, FTag);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(this);
        }
    }

    /**
     * 设置默认Dialog的配置
     */
    private void setDefaultOption() {
        cancelable = false;
        isCancelableOutside = false;
        gravity = Gravity.CENTER;
        dimAmount = 0.5f;
        dialogWidth = (int) (ScreenUtil.getScreenWidth(activity) * 0.85f);
        dialogHeight = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 移除之前的dialog
     */
    private void removePreDialog() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag(FTag);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return contentView.findViewById(id);
    }
}
