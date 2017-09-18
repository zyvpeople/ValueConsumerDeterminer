package com.develop.zuzik.viewadapter.example.decorator;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class MatchParentWidthRecyclerViewAdapterDecorator<VH extends RecyclerView.ViewHolder> extends RecyclerViewAdapterDecorator<VH> {

    public MatchParentWidthRecyclerViewAdapterDecorator(RecyclerView.Adapter<VH> adapter) {
        super(adapter);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH viewHolder = super.onCreateViewHolder(parent, viewType);
        viewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return viewHolder;
    }
}
