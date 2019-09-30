package com.zpj.zdialog;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zpj.zdialog.base.IDialog;

/**
 * @author Z-P-J
 * @date 2019/5/15 23:10
 */
public class ZAlertDialog {

    private Activity activity;

    private String title;

    private String content;

//    private boolean showCheckedBox;
//
//    private boolean isChecked;

    private String negativBtnStr = "取消";

    private String positiveBtnStr = "确定";

    private IDialog.OnClickListener positiveBtnListener;
    private IDialog.OnClickListener negativeBtnListener;

//    private CheckLayout.OnCheckedChangeListener onCheckedChangeListener;

    private ZAlertDialog(Activity activity) {
        this.activity = activity;
    }

    public static ZAlertDialog with(Activity activity) {
        return new ZAlertDialog(activity);
    }

    public ZAlertDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public ZAlertDialog setTitle(@StringRes int title) {
        this.title = activity.getResources().getString(title);
        return this;
    }

    public ZAlertDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public ZAlertDialog setContent(@StringRes int content) {
        this.content = activity.getResources().getString(content);
        return this;
    }

//    public ZAlertDialog setShowCheckedBox(boolean showCheckedBox) {
//        this.showCheckedBox = showCheckedBox;
//        return this;
//    }

//    public ZAlertDialog setChecked(boolean checked) {
//        isChecked = checked;
//        return this;
//    }

//    public ZAlertDialog setOnCheckedChangeListener(CheckLayout.OnCheckedChangeListener onCheckedChangeListener) {
//        this.onCheckedChangeListener = onCheckedChangeListener;
//        return this;
//    }

    public ZAlertDialog setPositiveButton(IDialog.OnClickListener onclickListener) {
        return setPositiveButton("确定", onclickListener);
    }

    public ZAlertDialog setPositiveButton(String btnStr, IDialog.OnClickListener onclickListener) {
        this.positiveBtnStr = btnStr;
        this.positiveBtnListener = onclickListener;
        return this;
    }

    public ZAlertDialog setPositiveButton(@StringRes int strRes, IDialog.OnClickListener onclickListener) {
        return setPositiveButton(activity.getResources().getString(strRes), onclickListener);
    }

    public ZAlertDialog setNegativeButton(IDialog.OnClickListener onclickListener) {
        return setNegativeButton("取消", onclickListener);
    }

    public ZAlertDialog setNegativeButton(String btnStr, IDialog.OnClickListener onclickListener) {
        this.negativBtnStr = btnStr;
        this.negativeBtnListener = onclickListener;
        return this;
    }

    public ZAlertDialog setNegativeButton(@StringRes int strRes, IDialog.OnClickListener onclickListener) {
        return setNegativeButton(activity.getResources().getString(strRes), onclickListener);
    }

    public void show() {
        ZDialog.with(activity)
                .setContentView(R.layout.layout_dialog_alert)
                .setWindowBackgroundP(0.2f)
                .setScreenWidthP(0.9f)
                .setOnViewCreateListener(new IDialog.OnViewCreateListener() {
                    @Override
                    public void onViewCreate(final IDialog dialog, View view) {
                        Button cancelBtn = dialog.findViewById(R.id.btn_cancel);
                        Button okBtn = dialog.findViewById(R.id.btn_ok);
                        okBtn.setText(positiveBtnStr);
                        cancelBtn.setText(negativBtnStr);
                        okBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (positiveBtnListener != null) {
                                    positiveBtnListener.onClick(dialog);
                                } else {
                                    dialog.dismiss();
                                }
                            }
                        });
                        cancelBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (negativeBtnListener != null) {
                                    negativeBtnListener.onClick(dialog);
                                } else {
                                    dialog.dismiss();
                                }
                            }
                        });

                        TextView titleText = dialog.findViewById(R.id.text_title);
                        TextView contentText = dialog.findViewById(R.id.text_content);
                        titleText.setText(title);
                        contentText.setText(content);

//                        if (showCheckedBox) {
//                            CheckLayout checkLayout = dialog.findViewById(R.id.layout_check);
//                            checkLayout.setVisibility(View.VISIBLE);
//                            checkLayout.setChecked(isChecked);
//                            checkLayout.setOnCheckedChangeListener(onCheckedChangeListener);
//                        }
                    }
                })
                .show();
    }

}
