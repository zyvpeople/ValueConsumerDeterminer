package com.develop.zuzik.valueconsumerdeterminer.viewgroup;

import android.view.View;
import android.view.ViewGroup;

import com.develop.zuzik.valueconsumerdeterminer.core.exception.GetValueConsumerException;
import com.develop.zuzik.valueconsumerdeterminer.core.exception.GetValueConsumerTypeException;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumer;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumerDeterminer;
import com.develop.zuzik.valueconsumerdeterminer.recyclerviewadapter.ValueView;
import com.develop.zuzik.valueconsumerdeterminer.recyclerviewadapter.ValueViewFactory;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/17/17.
 */

public class ViewGroupValueConsumerDeterminer<Value> {

    private final ValueConsumerDeterminer<Value, ValueViewFactory> determiner;

    public ViewGroupValueConsumerDeterminer(ValueConsumerDeterminer<Value, ValueViewFactory> determiner) {
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
