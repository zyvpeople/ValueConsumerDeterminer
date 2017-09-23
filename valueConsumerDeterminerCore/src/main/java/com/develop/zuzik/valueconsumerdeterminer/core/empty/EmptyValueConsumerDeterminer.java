package com.develop.zuzik.valueconsumerdeterminer.core.empty;

import android.util.Log;

import com.develop.zuzik.valueconsumerdeterminer.core.exception.GetValueConsumerException;
import com.develop.zuzik.valueconsumerdeterminer.core.exception.GetValueConsumerTypeException;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumer;
import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumerDeterminer;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class EmptyValueConsumerDeterminer<Value, Consumer extends ValueConsumer> implements ValueConsumerDeterminer<Value, Consumer> {

    private final ValueConsumerDeterminer<Value, Consumer> determiner;
    private final ValueConsumer<Value> emptyConsumer;

    public EmptyValueConsumerDeterminer(ValueConsumerDeterminer<Value, Consumer> determiner, ValueConsumer<Value> emptyConsumer) {
        this.determiner = determiner;
        this.emptyConsumer = emptyConsumer;
    }

    @Override
    public int getValueConsumerTypesCount() {
        return determiner.getValueConsumerTypesCount() + 1;
    }

    @Override
    public int getValueConsumerType(Value value) throws GetValueConsumerTypeException {
        try {
            return determiner.getValueConsumerType(value);
        } catch (GetValueConsumerTypeException e) {
            logFactoryIsNotSet(value);
            return determiner.getValueConsumerTypesCount();
        }
    }

    @Override
    public ValueConsumer<Value> getValueConsumer(int type) throws GetValueConsumerException {
        return type == determiner.getValueConsumerTypesCount()
                ? emptyConsumer
                : determiner.getValueConsumer(type);
    }

    private void logFactoryIsNotSet(Value value) {
        Log.w(
                getClass().getSimpleName(),
                String.format(
                        "%s for %s is not set",
                        ValueConsumer.class.getSimpleName(),
                        value));
    }
}