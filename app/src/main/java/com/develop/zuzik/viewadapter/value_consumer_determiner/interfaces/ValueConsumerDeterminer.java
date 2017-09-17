package com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces;

import com.develop.zuzik.viewadapter.value_consumer_determiner.exception.GetValueConsumerException;
import com.develop.zuzik.viewadapter.value_consumer_determiner.exception.GetValueConsumerTypeException;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public interface ValueConsumerDeterminer<Value> {

    int getValueConsumerTypesCount();

    int getValueConsumerType(Value value) throws GetValueConsumerTypeException;

    ValueConsumer<Value> getValueConsumer(int type) throws GetValueConsumerException;
}
