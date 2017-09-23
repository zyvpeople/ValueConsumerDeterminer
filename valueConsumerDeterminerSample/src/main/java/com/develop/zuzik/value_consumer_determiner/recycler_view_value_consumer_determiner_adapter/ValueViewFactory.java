package com.develop.zuzik.value_consumer_determiner.recycler_view_value_consumer_determiner_adapter;

import android.content.Context;

import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumer;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public interface ValueViewFactory<Value> extends ValueConsumer<Value> {
    ValueView<Value> create(Context context);
}
