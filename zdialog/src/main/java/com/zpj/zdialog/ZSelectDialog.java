//package com.zpj.zdialog;
//
//import android.app.Activity;
//import android.support.annotation.LayoutRes;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//
//import com.zpj.dialoglib.R;
//
//import java.util.List;
//
///**
// * @author Z-P-J
// * @date 2019/6/1 16:25
// */
//public class ZSelectDialog<T> {
//
//    private Activity activity;
//
//    private final int layoutRes = R.layout.layout_dialog_list;
//
//    private ZDialog dialog;
//
//    private EasyRecyclerView<T> easyRecyclerView;
//
//    private List<T> list;
//
//    private EasyAdapter.Callback<T> callback;
//
//    private RecyclerView.LayoutManager layoutManager;
//
//    private int gravity = Gravity.CENTER;
//
//    @LayoutRes
//    private int itemRes;
//
//
//    public ZSelectDialog(Activity activity) {
//        this.activity = activity;
//        View view = LayoutInflater.from(this.activity).inflate(layoutRes, null, false);
//        RecyclerView recyclerView = view.getView(R.id.recycler_view);
//        easyRecyclerView = new EasyRecyclerView<>(recyclerView);
//        dialog = ZDialog.with(activity).setContentView(view);
//        dialog.setSwipeable(false);
//    }
//
//    public static ZSelectDialog with(Activity activity) {
//        return new ZSelectDialog(activity);
//    }
//
//
//
//    public ZSelectDialog<T> setItemRes(@LayoutRes int res) {
//        this.itemRes = res;
//        return this;
//    }
//
//    public ZSelectDialog<T> setItemList(List<T> list) {
//        this.list = list;
//        return this;
//    }
//
//    public ZSelectDialog<T> setAdapterCallback(EasyAdapter.Callback<T> callback) {
//        this.callback = callback;
//        return this;
//    }
//
//    public ZSelectDialog<T> setLayoutManager(RecyclerView.LayoutManager layoutManager) {
//        this.layoutManager = layoutManager;
//        return this;
//    }
//
//    public ZSelectDialog<T> setGravity(int gravity) {
//        this.gravity = gravity;
//        return this;
//    }
//
//    public void show() {
//        easyRecyclerView.setList(list)
//                .setItemRes(itemRes)
//                .setLayoutManager(layoutManager == null ? new LinearLayoutManager(activity) : layoutManager)
//                .setCallback(callback)
//                .build();
//        dialog.setGravity(gravity).show();
//    }
//
//    public void dismiss() {
//        dialog.dismiss();
//    }
//
//}
