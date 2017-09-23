package com.develop.zuzik.valueconsumerdeterminer.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumerDeterminer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslavzozulia on 9/17/17.
 */

public class ValueRecyclerViewAdapter<Value> extends RecyclerView.Adapter<ViewHolder<View>> {

    private final List<Value> values = new ArrayList<>();
    private final RecyclerViewValueConsumerDeterminer<Value> determiner;

    public ValueRecyclerViewAdapter(ValueConsumerDeterminer<Value, ValueViewFactory> determiner) {
        this.determiner = new RecyclerViewValueConsumerDeterminer<>(determiner);
    }

    public void setValues(List<Value> values) {
        this.values.clear();
        this.values.addAll(values);
    }

    @Override
    public int getItemViewType(int position) {
        return determiner.getItemViewType(position, values);
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(ViewGroup parent, int viewType) {
        return determiner.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder<View> holder, int position) {
        determiner.onBindViewHolder(holder, position, values);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
