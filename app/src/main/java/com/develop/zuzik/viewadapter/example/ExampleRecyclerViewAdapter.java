package com.develop.zuzik.viewadapter.example;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.RecyclerViewValueConsumerDeterminerAdapter;
import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslavzozulia on 9/17/17.
 */

public class ExampleRecyclerViewAdapter<Value> extends RecyclerView.Adapter<ViewHolder<View>> {

    private final List<Value> values = new ArrayList<>();
    private final RecyclerViewValueConsumerDeterminerAdapter<Value> determinerAdapter;

    public ExampleRecyclerViewAdapter(RecyclerViewValueConsumerDeterminerAdapter<Value> determinerAdapter) {
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
        return determinerAdapter.onCreateViewHolder(parent, viewType, values);
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
