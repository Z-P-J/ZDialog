package com.zpj.zdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zpj.zdialog.base.IDialog;
import com.zpj.zdialog.utils.ScreenUtil;
import com.zpj.zdialog.utils.ViewUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZMenuDialog {

    private static final String TAG = "ZMenuDialog";

    public interface OnItemClickListener {
        void onItemClicked(String title, int position);
    }

    private List<String> titleList = new ArrayList<>();
    private final ZDialog dialog;
    private final Context context;
    private int locationX = 0;
    private int locationY = 0;
//    private int paddingStart;
//    private int paddingTop;
//    private int paddingEnd;
//    private int paddingBottom;
    private OnItemClickListener mOnItemClickListener;

    private int childHeight;

    private ZMenuDialog(Context context) {
        this.context = context;
        dialog = ZDialog.with(context)
                .setSwipeEnable(false)
                .setContentView(R.layout.dialog_list)
                .setWindowBackgroundP(0f);
    }

    public static ZMenuDialog with(Context context) {
        ZMenuDialog dialog = new ZMenuDialog(context);

        return dialog;
    }

    public ZMenuDialog addItems(String... itemTitles) {
        titleList.addAll(Arrays.asList(itemTitles));
        return this;
    }

    public ZMenuDialog addItems(List<String> itemTitles) {
        titleList.addAll(itemTitles);
        return this;
    }

    public ZMenuDialog addItem(String itemTitle) {
        titleList.add(itemTitle);
        return this;
    }

    public ZMenuDialog setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        return this;
    }

    public void show(int x, int y) {
        locationX = x;
        locationY = y;
        dialog.setOnDialogStartListener(new IDialog.OnDialogStartListener() {
            @Override
            public void onStart() {
                Dialog d = dialog.getDialog();
                if (d != null) {
                    Window window = d.getWindow();
                    if (window != null) {
                        initWindow(window);
                    }
                }
            }
        }).setOnViewCreateListener(new IDialog.OnViewCreateListener() {
            @Override
            public void onViewCreate(final IDialog dialog, View view) {
                LinearLayout containerLayout = view.findViewById(R.id.container);
                int i = 0;
                for (String title : titleList) {
                    final TextView textView = (TextView) LayoutInflater.from(view.getContext())
                            .inflate(R.layout.item_popup_list, null, false);
                    textView.setTag(i);
//                    final ViewTreeObserver observer = textView.getViewTreeObserver();
//                    observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                        @Override
//                        public boolean onPreDraw() {
//                            if (observer.isAlive()) {
//                                observer.removeOnPreDrawListener(this);
//                            }
//                            childHeight = textView.getMeasuredHeight();
//                            Log.d(TAG, "position=" + textView.getTag() + "  height=" + childHeight);
//                            ZDialog zDialog = ZMenuDialog.this.dialog;
//                            Window window = zDialog.getDialog().getWindow();
//                            WindowManager.LayoutParams lp = window.getAttributes();
//
//                            if (locationX > ScreenUtil.getScreenWidth(zDialog.getActivity()) / 2) {
//                                window.setGravity(Gravity.END | Gravity.TOP);
//                                lp.x = ScreenUtil.getScreenWidth(zDialog.getActivity()) - locationX;
//                            } else {
//                                window.setGravity(Gravity.START | Gravity.TOP);
//                                lp.x = locationX;
//                            }
//
//                            int windowHeight = childHeight * titleList.size();
//                            if (locationY + windowHeight > ScreenUtil.getScreenHeight(zDialog.getActivity())) {
//                                locationY -= windowHeight;
//                            }
//                            lp.y = locationY;
//                            lp.dimAmount = 0.0f;
//                            lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
//                            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//                            window.setAttributes(lp);
//                            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                            return true;
//                        }
//                    });
                    Log.d(TAG, "  getMeasuredHeight=" + textView.getMeasuredHeight());
                    textView.setText(title);
                    containerLayout.addView(textView);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                int position = (int) textView.getTag();
                                mOnItemClickListener.onItemClicked(textView.getText().toString(), position);
                            }
                            dialog.dismiss();
                        }
                    });
                    i++;
                }
            }
        }).show();
    }


    private void initWindow(Window window) {
        WindowManager.LayoutParams lp = window.getAttributes();

        if (locationX > ScreenUtil.getScreenWidth(dialog.getActivity()) / 2) {
            window.setGravity(Gravity.END | Gravity.TOP);
            lp.x = ScreenUtil.getScreenWidth(dialog.getActivity()) - locationX;
        } else {
            window.setGravity(Gravity.START | Gravity.TOP);
            lp.x = locationX;
        }

//        int windowHeight = (int) ViewUtil.dp2px(dialog.getContext(), 50 * (titleList.size() + 1));
//        if (locationY + windowHeight > ScreenUtil.getScreenHeight(dialog.getActivity())) {
//            locationY -= windowHeight;
//        }
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
             lp.y = locationY - resources.getDimensionPixelSize(resourceId);
        } else {
            lp.y = locationY;
        }
        lp.dimAmount = 0.0f;
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Log.d(TAG, "getContentView().getMeasuredHeight()=" + dialog.getContentView().getMeasuredHeight());
    }

}
