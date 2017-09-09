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
    private final List<Pair<Class<Value>, ViewFactory<Value>>> factories = new ArrayList<>();

    public <V extends Value> void addFactory(Class<V> valueClass, ViewFactory<V> factory) {
        factories.add(new Pair<>((Class<Value>) valueClass, (ViewFactory<Value>) factory));
    }

    public void setValues(List<Value> values) {
        this.values.clear();
        this.values.addAll(values);
    }

    @Override
    public int getItemViewType(int position) {
        Class<?> valueClass = values.get(position).getClass();
        for (int i = 0; i < factories.size(); i++) {
            if (factories.get(i).first.equals(valueClass)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder<>((View) factories.get(viewType).second.create(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((ValueView<Value>) holder.view).setValue(values.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
