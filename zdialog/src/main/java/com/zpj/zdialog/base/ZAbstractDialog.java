package com.zpj.zdialog.base;

import android.support.annotation.IdRes;
import android.view.View;

public abstract class ZAbstractDialog<T extends ZAbstractDialog<T>> extends DialogFragment {

    abstract T show();

    abstract <S extends View> S getView(@IdRes int id);

}
