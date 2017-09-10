package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewFactory;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

class EmptyViewHolderStrategy<Value> implements ViewHolderStrategy<Value> {

    private static final int NOT_EXISTED_VIEW_TYPE = -1;
    private final ViewFactory factory;

    public EmptyViewHolderStrategy(ViewFactory factory) {
        this.factory = factory;
    }

    @Override
    public int getItemViewType(int position,
                               List<Value> values,
                               List<Pair<Class<Value>, ValueViewFactory<Value>>> factories) {
        Class<?> valueClass = values.get(position).getClass();
        for (int i = 0; i < factories.size(); i++) {
            if (factories.get(i).first.equals(valueClass)) {
                return i;
            }
        }
        Log.e(
                getClass().getSimpleName(),
                String.format(
                        "%s for %s is not set",
                        ValueViewFactory.class.getSimpleName(),
                        valueClass.getSimpleName()));
        return NOT_EXISTED_VIEW_TYPE;
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(Context context,
                                               int viewType,
                                               List<Pair<Class<Value>, ValueViewFactory<Value>>> factories) {
        return new ViewHolder<>(viewType == -1
                ? factory.create(context)
                : (View) factories.get(viewType).second.create(context));
    }

    @Override
    public void onBindViewHolder(ViewHolder<View> holder,
                                 int position,
                                 List<Value> values,
                                 List<Pair<Class<Value>, ValueViewFactory<Value>>> factories) {
        if (getItemViewType(position, values, factories) != NOT_EXISTED_VIEW_TYPE) {
            ((ValueView<Value>) holder.view).setValue(values.get(position));
        }
    }
}