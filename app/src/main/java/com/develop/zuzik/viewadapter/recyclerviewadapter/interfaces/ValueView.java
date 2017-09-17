package com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces;

import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumer;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public interface ValueView<Value> extends ValueConsumer<Value> {
    void setValue(Value value);
}
