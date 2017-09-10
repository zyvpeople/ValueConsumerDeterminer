package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class RecyclerViewAdapter<Value> extends RecyclerView.Adapter<ViewHolder<View>> {

    private final RecyclerViewAdapterState<Value> state;
    private final ViewHolderStrategy<Value> strategy;

    RecyclerViewAdapter(List<Pair<Class<Value>, ValueViewFactory<Value>>> factories,
                        ViewHolderStrategy<Value> strategy) {
        state = new RecyclerViewAdapterState<>(factories);
        this.strategy = strategy;
    }

    public void setValues(List<Value> values) {
        state.setValues(values);
    }

    @Override
    public int getItemViewType(int position) {
        return strategy.getItemViewType(position, state);
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(ViewGroup parent, int viewType) {
        return strategy.onCreateViewHolder(parent.getContext(), viewType, state);
    }

    @Override
    public void onBindViewHolder(ViewHolder<View> holder, int position) {
        strategy.onBindViewHolder(holder, position, state);
    }

    @Override
    public int getItemCount() {
        return state.values.size();
    }
}
