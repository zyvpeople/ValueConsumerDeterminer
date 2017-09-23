package com.develop.zuzik.valueconsumerdeterminer.pageradapter;

import android.support.v4.app.Fragment;

import com.develop.zuzik.valueconsumerdeterminer.core.exception.GetValueConsumerException;
import com.develop.zuzik.valueconsumerdeterminer.core.exception.GetValueConsumerTypeException;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumer;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumerDeterminer;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/23/17.
 */

public class PagerAdapterValueConsumerDeterminer<Value> {

    private final ValueConsumerDeterminer<Value, ValueFragmentFactory> determiner;

    public PagerAdapterValueConsumerDeterminer(ValueConsumerDeterminer<Value, ValueFragmentFactory> determiner) {
        this.determiner = determiner;
    }

    public Fragment getItem(int position,
                            List<Value> values) {
        try {
            Value value = values.get(position);
            int type = determiner.getValueConsumerType(value);
            ValueConsumer<Value> consumer = determiner.getValueConsumer(type);
            ValueFragmentFactory<Value> factory = (ValueFragmentFactory<Value>) consumer;
            return factory.create(value);
        } catch (GetValueConsumerTypeException | GetValueConsumerException e) {
            throw new RuntimeException(e);
        }
    }
}
