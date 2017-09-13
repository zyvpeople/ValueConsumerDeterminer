package com.develop.zuzik.viewadapter.recyclerviewadapter.strategy;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewHolderStrategy;
import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class EmptyViewHolderStrategy<Value> implements ViewHolderStrategy<Value> {

    private static final int NOT_EXISTED_VIEW_TYPE = -1;

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
        if (type == NOT_EXISTED_VIEW_TYPE) {
            Log.e(
                    getClass().getSimpleName(),
                    String.format(
                            "%s for %s is not set",
                            ValueViewFactory.class.getSimpleName(),
                            values.get(position)));
        }
        return type;
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(Context context, int viewType, List<Value> values) {
        return viewType == NOT_EXISTED_VIEW_TYPE
                ? new ViewHolder<>(factory.create(context))
                : strategy.onCreateViewHolder(context, viewType, values);
    }

    @Override
    public void onBindViewHolder(ViewHolder<View> holder, int position, List<Value> values) {
        if (strategy.findItemViewType(position, values) != NOT_EXISTED_VIEW_TYPE) {
            ((ValueView<Value>) holder.view).setValue(values.get(position));
        }
    }
}