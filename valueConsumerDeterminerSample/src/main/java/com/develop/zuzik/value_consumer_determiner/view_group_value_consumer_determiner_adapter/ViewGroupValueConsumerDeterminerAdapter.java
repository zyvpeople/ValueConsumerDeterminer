package com.develop.zuzik.value_consumer_determiner.view_group_value_consumer_determiner_adapter;

import android.view.View;
import android.view.ViewGroup;

import com.develop.zuzik.valueconsumerdeterminer.core.exception.GetValueConsumerException;
import com.develop.zuzik.valueconsumerdeterminer.core.exception.GetValueConsumerTypeException;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumer;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumerDeterminer;
import com.develop.zuzik.valueconsumerdeterminer.recyclerview.ValueView;
import com.develop.zuzik.valueconsumerdeterminer.recyclerview.ValueViewFactory;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/17/17.
 */

//TODO: create builder
//TODO: remove type casting
public class ViewGroupValueConsumerDeterminerAdapter<Value> {

    private final ValueConsumerDeterminer<Value, ValueViewFactory> determiner;

    public ViewGroupValueConsumerDeterminerAdapter(ValueConsumerDeterminer<Value, ValueViewFactory> determiner) {
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
