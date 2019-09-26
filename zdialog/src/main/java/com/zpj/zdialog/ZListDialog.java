package com.zpj.zdialog;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * @author Z-P-J
 * @date 2019/6/1 16:25
 */
public class ZListDialog<T> {

    private Activity activity;

    private final int layoutRes = R.layout.layout_dialog_list;

    private ZDialog dialog;

    private EasyRecyclerView<T> easyRecyclerView;

    private List<T> list;

    private EasyAdapter.Callback<T> callback;

    private RecyclerView.LayoutManager layoutManager;

    private int gravity = Gravity.CENTER;

    @LayoutRes
    private int itemRes;
//    private View itemView;

    private TextView titleView;


    public ZListDialog(Activity activity) {
        this.activity = activity;
        View view = LayoutInflater.from(this.activity).inflate(layoutRes, null, false);
        titleView = view.findViewById(R.id.title_view);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        easyRecyclerView = new EasyRecyclerView<>(recyclerView);
        dialog = ZDialog.with(activity).setContentView(view);
        dialog.setSwipeable(false);
    }

    public static ZListDialog with(Activity activity) {
        return new ZListDialog(activity);
    }



    public ZListDialog<T> setItemRes(@LayoutRes int res) {
        this.itemRes = res;
        return this;
    }

//    public ZListDialog<T> setItemView(View view) {
//        this.itemView = view;
//        return this;
//    }

    public ZListDialog<T> setItemList(List<T> list) {
        this.list = list;
        return this;
    }

    public ZListDialog<T> setAdapterCallback(EasyAdapter.Callback<T> callback) {
        this.callback = callback;
        return this;
    }

    public ZListDialog<T> setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    public ZListDialog<T> setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public ZListDialog<T> setTitle(String title) {
        titleView.setText(title);
        return this;
    }

    public ZListDialog<T> setTitleTextColor(int textColor) {
        titleView.setTextColor(textColor);
        return this;
    }

    public ZListDialog<T> setTitleTextSize(float textSize) {
        titleView.setTextSize(textSize);
        return this;
    }

    public void show() {
        easyRecyclerView.setList(list)
                .setItemRes(itemRes)
                .setLayoutManager(layoutManager == null ? new LinearLayoutManager(activity) : layoutManager)
                .setCallback(callback)
                .build();
        dialog.setGravity(gravity).show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

}
