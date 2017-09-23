package com.develop.zuzik.valueconsumerdeterminer.recyclerviewadapter;

import android.view.View;
import android.view.ViewGroup;

import com.develop.zuzik.valueconsumerdeterminer.core.exception.GetValueConsumerException;
import com.develop.zuzik.valueconsumerdeterminer.core.exception.GetValueConsumerTypeException;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumer;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumerDeterminer;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public final class RecyclerViewValueConsumerDeterminer<Value> {

    private final ValueConsumerDeterminer<Value, ValueViewFactory> determiner;

    public RecyclerViewValueConsumerDeterminer(ValueConsumerDeterminer<Value, ValueViewFactory> determiner) {
        this.determiner = determiner;
    }

    public int getItemViewType(int position,
                               List<Value> values) {
        try {
            return determiner.getValueConsumerType(values.get(position));
        } catch (GetValueConsumerTypeException e) {
            throw new RuntimeException(e);
        }
    }

    public ViewHolder<View> onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        try {
            ValueConsumer<Value> consumer = determiner.getValueConsumer(viewType);
            ValueViewFactory<Value> factory = (ValueViewFactory<Value>) consumer;
            ValueView<Value> valueView = factory.create(parent.getContext());
            View view = (View) valueView;
            return new ViewHolder<>(view);
        } catch (GetValueConsumerException e) {
            throw new RuntimeException(e);
        }
    }

    public void onBindViewHolder(ViewHolder<View> holder,
                                 int position,
                                 List<Value> values) {
        ((ValueView<Value>) holder.view).setValue(values.get(position));
    }
}
