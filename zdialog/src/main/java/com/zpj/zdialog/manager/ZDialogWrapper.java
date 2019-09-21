package com.zpj.zdialog.manager;

import com.zpj.zdialog.ZDialog;

public class ZDialogWrapper {

    private ZDialog dialog;//统一管理dialog的弹出顺序

    public ZDialogWrapper(ZDialog dialog) {
        this.dialog = dialog;
    }

    public ZDialog getDialog() {
        return dialog;
    }

    public void setDialog(ZDialog dialog) {
        this.dialog = dialog;
    }

}
