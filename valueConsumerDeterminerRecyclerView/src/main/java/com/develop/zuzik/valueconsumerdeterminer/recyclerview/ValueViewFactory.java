package com.develop.zuzik.valueconsumerdeterminer.recyclerview;

import android.content.Context;

import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumer;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public interface ValueViewFactory<Value> extends ValueConsumer<Value> {
    ValueView<Value> create(Context context);
}
