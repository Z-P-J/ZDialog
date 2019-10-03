package com.zpj.zdialog;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zpj.zdialog.base.IDialog;

/**
 * @author Z-P-J
 * @date 2019/5/15 23:10
 */
public class ZAlertDialog {

    private Activity activity;

    private final ZDialog dialog;

    private String title;

    private View contentView;

    private String negativBtnStr = "取消";

    private String positiveBtnStr = "确定";

    private boolean isCancelable = true;
    private boolean isCancelableOutside = true;

    private IDialog.OnClickListener positiveBtnListener;
    private IDialog.OnClickListener negativeBtnListener;
    private IDialog.OnDismissListener onDismissListener;

    private ZAlertDialog(Activity activity) {
        this.activity = activity;
        dialog = ZDialog.with(activity);
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

    public ZAlertDialog setContentView(View contentView) {
        this.contentView = contentView;
        return this;
    }

    public ZAlertDialog setContent(String content) {
        TextView textView = (TextView) LayoutInflater.from(activity).inflate(R.layout.content_text_view, null, false);
        textView.setText(content);
        return setContentView(textView);
    }

    public ZAlertDialog setContent(@StringRes int content) {
        return setContent(activity.getResources().getString(content));
    }

    public ZAlertDialog setCancelable(boolean cancelable) {
        isCancelable = cancelable;
        return this;
    }

    public ZAlertDialog setCancelableOutside(boolean cancelableOutside) {
        isCancelableOutside = cancelableOutside;
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

    public ZAlertDialog setOnDismissListener(IDialog.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public ZAlertDialog show() {
        dialog.setContentView(R.layout.layout_dialog_alert)
                .setWindowBackgroundP(0.2f)
                .setScreenWidthP(0.9f)
                .setDialogCancelable(isCancelable)
                .setCancelableOutSide(isCancelableOutside)
                .setOnViewCreateListener(new IDialog.OnViewCreateListener() {
                    @Override
                    public void onViewCreate(final IDialog dialog, View view) {

                        LinearLayout container = dialog.getView(R.id.layout_container);
                        container.addView(contentView);

                        Button cancelBtn = dialog.getView(R.id.btn_cancel);
                        Button okBtn = dialog.getView(R.id.btn_ok);
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

                        TextView titleText = dialog.getView(R.id.text_title);
                        titleText.setText(title);
                    }
                })
                .setOnDismissListener(onDismissListener)
                .show();
        return this;
    }

    public void dismiss() {
        dialog.dismiss();
    }

}
