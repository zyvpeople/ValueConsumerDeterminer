package com.develop.zuzik.viewadapter.recyclerviewadapter.strategy;

import android.content.Context;
import android.util.Pair;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.Predicate;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewHolderStrategy;
import com.develop.zuzik.viewadapter.recyclerviewadapter.viewholder.ViewHolder;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class PredicateViewHolderStrategy<Value> implements ViewHolderStrategy<Value> {

    private final List<Pair<Predicate<Value>, ValueViewFactory<Value>>> factories;

    public PredicateViewHolderStrategy(List<Pair<Predicate<Value>, ValueViewFactory<Value>>> factories) {
        this.factories = factories;
    }

    @Override
    public int getViewTypesCount() {
        return factories.size();
    }

    @Override
    public int findItemViewType(int position, List<Value> values) {
        Value value = values.get(position);
        for (int i = 0; i < factories.size(); i++) {
            Predicate<Value> predicate = factories.get(i).first;
            if (predicate.isSatisfied(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ViewHolder<View> onCreateViewHolder(Context context, int viewType, List<Value> values) {
        return new ViewHolder<>((View) factories.get(viewType).second.create(context));
    }

    @Override
    public void onBindViewHolder(ViewHolder<View> holder, int position, List<Value> values) {
        ((ValueView<Value>) holder.view).setValue(values.get(position));
    }
}
