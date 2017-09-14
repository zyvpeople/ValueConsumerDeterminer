package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewHolderStrategy;
import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class RecyclerViewAdapter<Value> extends RecyclerView.Adapter<ViewHolder<View>> {

    private final List<Value> values = new ArrayList<>();
    private final ViewHolderStrategy<Value> strategy;

    RecyclerViewAdapter(ViewHolderStrategy<Value> strategy) {
        this.strategy = strategy;
    }

    public void setValues(List<Value> values) {
        this.values.clear();
        this.values.addAll(values);
    }

    @Override
    public int getItemViewType(int position) {
        return strategy.findItemViewType(position, values);
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(ViewGroup parent, int viewType) {
        return strategy.onCreateViewHolder(parent.getContext(), viewType, values);
    }

    @Override
    public void onBindViewHolder(ViewHolder<View> holder, int position) {
        if (holder.view instanceof ValueView) {
            ((ValueView<Value>) holder.view).setValue(values.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
