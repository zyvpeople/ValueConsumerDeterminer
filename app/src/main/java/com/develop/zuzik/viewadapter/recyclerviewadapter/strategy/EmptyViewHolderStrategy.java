package com.develop.zuzik.viewadapter.recyclerviewadapter.strategy;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewHolderStrategy;
import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class EmptyViewHolderStrategy<Value> implements ViewHolderStrategy<Value> {

    private final ViewFactory factory;
    private final ViewHolderStrategy<Value> strategy;

    public EmptyViewHolderStrategy(ViewFactory factory, ViewHolderStrategy<Value> strategy) {
        this.factory = factory;
        this.strategy = strategy;
    }

    @Override
    public int getViewTypesCount() {
        return strategy.getViewTypesCount() + 1;
    }

    @Override
    public int findItemViewType(int position, List<Value> values) {
        int type = strategy.findItemViewType(position, values);
        if (type == -1) {
            logE(position, values);
        }
        return type;
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(Context context, int viewType, List<Value> values) {
        return viewType == -1
                ? new ViewHolder<>(factory.create(context))
                : strategy.onCreateViewHolder(context, viewType, values);
    }

    private void logE(int position, List<Value> values) {
        Log.e(
                getClass().getSimpleName(),
                String.format(
                        "%s for %s is not set",
                        ValueViewFactory.class.getSimpleName(),
                        values.get(position)));
    }
}