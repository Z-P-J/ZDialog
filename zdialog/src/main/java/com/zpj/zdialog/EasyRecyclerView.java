package com.zpj.zdialog;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class EasyRecyclerView<T> {

    private final RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;

    private EasyAdapter<T> easyAdapter;

    private List<T> list;

    private int itemRes;

    private EasyAdapter.Callback<T> callback;

    public EasyRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public EasyRecyclerView<T> setItemRes(int res) {
        this.itemRes = res;
        return this;
    }

    public EasyRecyclerView<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public EasyRecyclerView<T> setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    public EasyRecyclerView<T> setCallback(EasyAdapter.Callback<T> callback) {
        this.callback = callback;
        return this;
    }

    public void build() {
        easyAdapter = new EasyAdapter<T>(list, itemRes, callback);
        recyclerView.setLayoutManager(layoutManager != null ? layoutManager : new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(easyAdapter);
    }

    public void notifyDataSetChanged() {
        easyAdapter.notifyDataSetChanged();
    }

}
