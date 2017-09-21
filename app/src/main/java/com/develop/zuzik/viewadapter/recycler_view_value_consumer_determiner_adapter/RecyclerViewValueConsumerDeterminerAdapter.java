package com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter;

import android.view.View;
import android.view.ViewGroup;

import com.develop.zuzik.viewadapter.value_consumer_determiner.exception.GetValueConsumerException;
import com.develop.zuzik.viewadapter.value_consumer_determiner.exception.GetValueConsumerTypeException;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumerDeterminer;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

//TODO: create builder
//TODO: remove type casting
public final class RecyclerViewValueConsumerDeterminerAdapter<Value> {

    private final ValueConsumerDeterminer<Value, ValueViewFactory> determiner;

    public RecyclerViewValueConsumerDeterminerAdapter(ValueConsumerDeterminer<Value, ValueViewFactory> determiner) {
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
                                               int viewType,
                                               List<Value> values) {
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
        if (holder.view instanceof ValueView) {
            ((ValueView<Value>) holder.view).setValue(values.get(position));
        }
    }
}
