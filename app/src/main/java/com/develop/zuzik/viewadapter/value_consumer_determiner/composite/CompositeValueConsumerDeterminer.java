package com.develop.zuzik.viewadapter.value_consumer_determiner.composite;

import com.develop.zuzik.viewadapter.value_consumer_determiner.exception.GetValueConsumerException;
import com.develop.zuzik.viewadapter.value_consumer_determiner.exception.GetValueConsumerTypeException;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumerDeterminer;

import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */
public class CompositeValueConsumerDeterminer<Value> implements ValueConsumerDeterminer<Value> {

    private final List<ValueConsumerDeterminer<Value>> determiners;

    public CompositeValueConsumerDeterminer(List<ValueConsumerDeterminer<Value>> determiners) {
        this.determiners = determiners;
    }

    @Override
    public int getValueConsumerTypesCount() {
        int count = 0;
        for (ValueConsumerDeterminer<Value> determiner : determiners) {
            count += determiner.getValueConsumerTypesCount();
        }
        return count;
    }

    @Override
    public int getValueConsumerType(Value value) throws GetValueConsumerTypeException {
        int count = 0;
        for (ValueConsumerDeterminer<Value> determiner : determiners) {
            try {
                return determiner.getValueConsumerType(value) + count;
            } catch (GetValueConsumerTypeException e) {
                //
            }
            count += determiner.getValueConsumerTypesCount();
        }
        throw new GetValueConsumerTypeException();
    }

    @Override
    public ValueConsumer<Value> getValueConsumer(int type) throws GetValueConsumerException {
        int count = 0;
        for (ValueConsumerDeterminer<Value> determiner : determiners) {
            count += determiner.getValueConsumerTypesCount();
            if (type < count) {
                int typeForDeterminer = count - type;
                return determiner.getValueConsumer(typeForDeterminer);
            }
        }
        throw new GetValueConsumerException();
    }
}
