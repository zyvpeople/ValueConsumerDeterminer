package com.develop.zuzik.value_consumer_determiner.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.develop.zuzik.valueconsumerdeterminer.recyclerview.RecyclerViewValueConsumerDeterminer;
import com.develop.zuzik.valueconsumerdeterminer.recyclerview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslavzozulia on 9/17/17.
 */

public class ExampleRecyclerViewAdapter<Value> extends RecyclerView.Adapter<ViewHolder<View>> {

    private final List<Value> values = new ArrayList<>();
    private final RecyclerViewValueConsumerDeterminer<Value> determinerAdapter;

    public ExampleRecyclerViewAdapter(RecyclerViewValueConsumerDeterminer<Value> determinerAdapter) {
        this.determinerAdapter = determinerAdapter;
    }

    public void setValues(List<Value> values) {
        this.values.clear();
        this.values.addAll(values);
    }

    @Override
    public int getItemViewType(int position) {
        return determinerAdapter.getItemViewType(position, values);
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(ViewGroup parent, int viewType) {
        return determinerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder<View> holder, int position) {
        determinerAdapter.onBindViewHolder(holder, position, values);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
