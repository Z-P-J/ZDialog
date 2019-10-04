package com.zpj.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class EasyAdapter<T> extends RecyclerView.Adapter<EasyViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;


    private final List<T> list;

    private int itemRes;

    private View headerView;
    private View footerView;

    private IEasy.OnBindViewHolderCallback<T> callback;

    public EasyAdapter(List<T> list, int itemRes, IEasy.OnBindViewHolderCallback<T> callback) {
        this.list = list;
        this.itemRes = itemRes;
        this.callback = callback;
    }

    @NonNull
    @Override
    public EasyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new EasyViewHolder(headerView);
        } else if (viewType == TYPE_FOOTER) {
            return new EasyViewHolder(footerView);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemRes, viewGroup, false);
//            if (callback != null) {
//                callback.onCreateViewHolder(list, view, viewType);
//            }
            return new EasyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull EasyViewHolder holder, int position) {
        if (isHeaderPosition(position) || isFooterPosition(position)) return;
        if (callback != null) {
            callback.onBindViewHolder(holder, list, getRealPosition(holder));
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return isHeaderPosition(position) || isFooterPosition(position)
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull EasyViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderPosition(position)) {
            return TYPE_HEADER;
        } else if (isFooterPosition(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        int count = list == null ? 0 : list.size();
        if (headerView != null) {
            count++;
        }
        if (footerView != null) {
            count++;
        }
        return count;
    }

    private boolean isHeaderPosition(int position) {
        return headerView != null && position == 0;
    }

    private boolean isFooterPosition(int position) {
        return footerView != null && position == getItemCount() - 1;
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }

    public void setHeaderView(@NonNull View headerView) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headerView.setLayoutParams(params);
        this.headerView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return headerView;
    }

    public void setFooterView(@NonNull View footerView) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        footerView.setLayoutParams(params);
        this.footerView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    public View getFooterView() {
        return footerView;
    }

}
