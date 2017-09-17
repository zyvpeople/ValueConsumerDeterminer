package com.develop.zuzik.viewadapter.view_group_value_consumer_determiner_adapter;

import android.view.View;
import android.view.ViewGroup;

import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.ValueView;
import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.ValueViewFactory;
import com.develop.zuzik.viewadapter.value_consumer_determiner.exception.GetValueConsumerException;
import com.develop.zuzik.viewadapter.value_consumer_determiner.exception.GetValueConsumerTypeException;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumerDeterminer;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/17/17.
 */

//TODO: create builder
//TODO: remove type casting
public class ViewGroupValueConsumerDeterminerAdapter<Value> {

    private final ValueConsumerDeterminer<Value> determiner;

    public ViewGroupValueConsumerDeterminerAdapter(ValueConsumerDeterminer<Value> determiner) {
        this.determiner = determiner;
    }

    public void setValues(List<Value> values, ViewGroup to) {
        to.removeAllViews();
        for (Value value : values) {
            try {
                int type = determiner.getValueConsumerType(value);
                ValueConsumer<Value> consumer = determiner.getValueConsumer(type);
                ValueViewFactory<Value> factory = (ValueViewFactory<Value>) consumer;
                ValueView<Value> valueView = factory.create(to.getContext());
                View view = (View) valueView;
                to.addView(view);
                valueView.setValue(value);
            } catch (GetValueConsumerTypeException | GetValueConsumerException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
