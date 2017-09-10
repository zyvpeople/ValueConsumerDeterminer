package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.content.Context;
import android.util.Pair;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.exception.ValueViewFactoryIsNotSetException;
import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

class ErrorViewHolderStrategy<Value> implements ViewHolderStrategy<Value> {

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
        throw new ValueViewFactoryIsNotSetException(valueClass);
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(Context context,
                                               int viewType,
                                               List<Pair<Class<Value>, ValueViewFactory<Value>>> factories) {
        return new ViewHolder<>((View) factories.get(viewType).second.create(context));
    }

    @Override
    public void onBindViewHolder(ViewHolder<View> holder,
                                 int position,
                                 List<Value> values,
                                 List<Pair<Class<Value>, ValueViewFactory<Value>>> factories) {
        ((ValueView<Value>) holder.view).setValue(values.get(position));
    }
}
