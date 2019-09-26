package com.zpj.zdialog;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.suke.widget.SwitchButton;
import com.zpj.zdialog.R;

/**
 * @author Z-P-J
 * @date 2019/5/15 23:10
 */
public class ZCheckDialog {

    public interface OnClickListener {
        void onClick(IDialog dialog, boolean isChecked);
    }

    private Activity activity;

    private String title;

    private String content;

    private boolean isChecked;

    private String negativBtnStr = "取消";

    private String positiveBtnStr = "确定";

    private OnClickListener positiveBtnListener;
    private OnClickListener negativeBtnListener;

    private ZCheckDialog(Activity activity) {
        this.activity = activity;
    }

    public static ZCheckDialog with(Activity activity) {
        return new ZCheckDialog(activity);
    }

    public ZCheckDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public ZCheckDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public ZCheckDialog setChecked(boolean checked) {
        isChecked = checked;
        return this;
    }

    public ZCheckDialog setPositiveButton(OnClickListener onclickListener) {
        return setPositiveButton("确定", onclickListener);
    }

    public ZCheckDialog setPositiveButton(String btnStr, OnClickListener onclickListener) {
        this.positiveBtnStr = btnStr;
        this.positiveBtnListener = onclickListener;
        return this;
    }

    public ZCheckDialog setNegativeButton(OnClickListener onclickListener) {
        return setNegativeButton("取消", onclickListener);
    }

    public ZCheckDialog setNegativeButton(String btnStr, OnClickListener onclickListener) {
        this.negativBtnStr = btnStr;
        this.negativeBtnListener = onclickListener;
        return this;
    }

    public void show() {
        ZDialog.with(activity)
                .setContentView(R.layout.layout_dialog_check)
                .setWindowBackgroundP(0.4f)
                .setScreenWidthP(0.9f)
                .setOnViewCreateListener(new IDialog.OnViewCreateListener() {
                    @Override
                    public void onViewCreate(final IDialog dialog, View view) {

                        final SwitchButton switchButton = dialog.findViewById(R.id.btn_switch);
                        switchButton.setChecked(isChecked);

                        Button cancelBtn = dialog.findViewById(R.id.btn_cancel);
                        Button okBtn = dialog.findViewById(R.id.btn_ok);
                        okBtn.setText(positiveBtnStr);
                        cancelBtn.setText(negativBtnStr);
                        okBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (positiveBtnListener != null) {
                                    positiveBtnListener.onClick(dialog, switchButton.isChecked());
                                } else {
                                    dialog.dismiss();
                                }
                            }
                        });
                        cancelBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (negativeBtnListener != null) {
                                    negativeBtnListener.onClick(dialog, switchButton.isChecked());
                                } else {
                                    dialog.dismiss();
                                }
                            }
                        });

                        TextView titleText = dialog.findViewById(R.id.text_title);
                        TextView contentText = dialog.findViewById(R.id.text_content);
                        titleText.setText(title);
                        contentText.setText(content);
                    }
                })
                .show();
    }

}
