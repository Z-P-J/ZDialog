package com.zpj.zdialog;

import android.support.annotation.IdRes;
import android.view.View;

public interface IDialog {

    void dismiss();

    void hide();

    void show();

    void dismissWithoutAnim();

    <T extends View> T findViewById(@IdRes int id);

    interface OnViewCreateListener {
        void onViewCreate(IDialog dialog, View view);
    }

    interface OnClickListener {
        void onClick(IDialog dialog);
    }

    interface OnDismissListener {
        void onDismiss(IDialog dialog);
    }

    interface OnPositiveButtonClickListener {
        void onClick(IDialog dialog, String text);
    }
}
