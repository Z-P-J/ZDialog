package com.zpj.zdialog;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zpj.dialoglib.R;

/**
 * @author Z-P-J
 * @date 2019/5/15 23:10
 */
public class ZAlertDialog {

    private Activity activity;

    private String title;

    private String content;

    private String negativBtnStr;

    private String positiveBtnStr;

    IDialog.OnClickListener positiveBtnListener;
    IDialog.OnClickListener negativeBtnListener;

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

    public ZAlertDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public ZAlertDialog setPositiveButton(IDialog.OnClickListener onclickListener) {
        return setPositiveButton("确定", onclickListener);
    }

    public ZAlertDialog setPositiveButton(String btnStr, IDialog.OnClickListener onclickListener) {
        this.positiveBtnStr = btnStr;
        this.positiveBtnListener = onclickListener;
        return this;
    }

    public ZAlertDialog setNegativeButton(IDialog.OnClickListener onclickListener) {
        return setNegativeButton("取消", onclickListener);
    }

    public ZAlertDialog setNegativeButton(String btnStr, IDialog.OnClickListener onclickListener) {
        this.negativBtnStr = btnStr;
        this.negativeBtnListener = onclickListener;
        return this;
    }

    public void show() {
        ZDialog.with(activity)
                .setContentView(R.layout.layout_dialog_alert)
                .setWindowBackgroundP(0.2f)
                .setScreenWidthP(0.9f)
                .setOnViewCreateListener(new IDialog.OnViewCreateListener() {
                    @Override
                    public void onViewCreate(final IDialog dialog, View view) {
                        Button cancelBtn = view.findViewById(R.id.btn_cancel);
                        Button okBtn = view.findViewById(R.id.btn_ok);
                        okBtn.setText(positiveBtnStr);
                        cancelBtn.setText(negativBtnStr);
                        okBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (positiveBtnListener != null) {
                                    positiveBtnListener.onClick(dialog);
                                }
                            }
                        });
                        cancelBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (negativeBtnListener != null) {
                                    negativeBtnListener.onClick(dialog);
                                }
                            }
                        });

                        TextView titleText = view.findViewById(R.id.text_title);
                        TextView contentText = view.findViewById(R.id.text_content);
                        titleText.setText(title);
                        contentText.setText(content);
                    }
                })
                .show();
    }

}
