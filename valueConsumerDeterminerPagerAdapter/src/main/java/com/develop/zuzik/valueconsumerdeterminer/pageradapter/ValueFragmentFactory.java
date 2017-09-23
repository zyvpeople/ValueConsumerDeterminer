package com.develop.zuzik.valueconsumerdeterminer.pageradapter;

import android.support.v4.app.Fragment;

import com.develop.zuzik.valueconsumerdeterminer.core.interfaces.ValueConsumer;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public interface ValueFragmentFactory<Value> extends ValueConsumer<Value> {
    Fragment create(Value value);
}
