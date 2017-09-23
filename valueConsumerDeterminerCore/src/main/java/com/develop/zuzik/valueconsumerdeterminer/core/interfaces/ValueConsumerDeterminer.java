package com.develop.zuzik.valueconsumerdeterminer.core.interfaces;

import com.develop.zuzik.valueconsumerdeterminer.core.exception.GetValueConsumerException;
import com.develop.zuzik.valueconsumerdeterminer.core.exception.GetValueConsumerTypeException;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public interface ValueConsumerDeterminer<Value, Consumer extends ValueConsumer> {

    int getValueConsumerTypesCount();

    int getValueConsumerType(Value value) throws GetValueConsumerTypeException;

    ValueConsumer<Value> getValueConsumer(int type) throws GetValueConsumerException;
}
