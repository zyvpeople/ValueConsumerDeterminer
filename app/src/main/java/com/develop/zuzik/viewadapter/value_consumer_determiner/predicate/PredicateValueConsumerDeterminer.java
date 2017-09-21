package com.develop.zuzik.viewadapter.value_consumer_determiner.predicate;

import com.develop.zuzik.viewadapter.value_consumer_determiner.exception.GetValueConsumerException;
import com.develop.zuzik.viewadapter.value_consumer_determiner.exception.GetValueConsumerTypeException;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.Predicate;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumerDeterminer;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class PredicateValueConsumerDeterminer<Value, Consumer extends ValueConsumer> implements ValueConsumerDeterminer<Value, Consumer> {

    private final Predicate<Value> predicate;
    private final ValueConsumer<Value> consumer;

    public PredicateValueConsumerDeterminer(Predicate<Value> predicate, ValueConsumer<Value> consumer) {
        this.predicate = predicate;
        this.consumer = consumer;
    }

    @Override
    public int getValueConsumerTypesCount() {
        return 1;
    }

    @Override
    public int getValueConsumerType(Value value) throws GetValueConsumerTypeException {
        if (predicate.isSatisfied(value)) {
            return 0;
        } else {
            throw new GetValueConsumerTypeException();
        }
    }

    @Override
    public ValueConsumer<Value> getValueConsumer(int type) throws GetValueConsumerException {
        return consumer;
    }
}
