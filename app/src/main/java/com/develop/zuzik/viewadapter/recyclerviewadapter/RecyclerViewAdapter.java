package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class RecyclerViewAdapter<Value> extends RecyclerView.Adapter<ViewHolder<View>> {

    private final List<Value> values = new ArrayList<>();
    private final List<Pair<Class<Value>, ValueViewFactory<Value>>> factories = new ArrayList<>();
    private final ViewHolderStrategy<Value> strategy = new EmptyViewHolderStrategy<>(new EmptyViewFactory());

    public <V extends Value> void addFactory(Class<V> valueClass, ValueViewFactory<V> factory) {
        factories.add(new Pair<>((Class<Value>) valueClass, (ValueViewFactory<Value>) factory));
    }

    public void setValues(List<Value> values) {
        this.values.clear();
        this.values.addAll(values);
    }

    @Override
    public int getItemViewType(int position) {
        return strategy.getItemViewType(position, values, factories);
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(ViewGroup parent, int viewType) {
        return strategy.onCreateViewHolder(parent.getContext(), viewType, factories);
    }

    @Override
    public void onBindViewHolder(ViewHolder<View> holder, int position) {
        strategy.onBindViewHolder(holder, position, values, factories);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
