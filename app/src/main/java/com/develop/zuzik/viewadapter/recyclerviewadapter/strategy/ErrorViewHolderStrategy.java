package com.develop.zuzik.viewadapter.recyclerviewadapter.strategy;

import android.content.Context;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.exception.ValueViewFactoryIsNotSetException;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewHolderStrategy;
import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */
public class ErrorViewHolderStrategy<Value> implements ViewHolderStrategy<Value> {

    private final ViewHolderStrategy<Value> strategy;

    public ErrorViewHolderStrategy(ViewHolderStrategy<Value> strategy) {
        this.strategy = strategy;
    }

    @Override
    public int getViewTypesCount() {
        return strategy.getViewTypesCount();
    }

    @Override
    public int findItemViewType(int position, List<Value> values) {
        int type = strategy.findItemViewType(position, values);
        if (type != -1) {
            return type;
        } else {
            throw new ValueViewFactoryIsNotSetException(values.get(position));
        }
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(Context context, int viewType, List<Value> values) {
        return strategy.onCreateViewHolder(context, viewType, values);
    }

    @Override
    public void onBindViewHolder(ViewHolder<View> holder, int position, List<Value> values) {
        strategy.onBindViewHolder(holder, position, values);
    }
}
