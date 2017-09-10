package com.develop.zuzik.viewadapter.recyclerviewadapter.decorator;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public abstract class RecyclerViewAdapterDecorator<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final RecyclerView.Adapter<VH> adapter;

    public RecyclerViewAdapterDecorator(RecyclerView.Adapter<VH> adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        return adapter.getItemViewType(position);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        adapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount();
    }
}
