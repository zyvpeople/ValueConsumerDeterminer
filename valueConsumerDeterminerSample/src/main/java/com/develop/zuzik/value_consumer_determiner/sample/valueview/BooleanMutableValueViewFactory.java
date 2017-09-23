package com.develop.zuzik.value_consumer_determiner.sample.valueview;

import android.content.Context;

import com.develop.zuzik.value_consumer_determiner.recycler_view_value_consumer_determiner_adapter.ValueView;
import com.develop.zuzik.value_consumer_determiner.recycler_view_value_consumer_determiner_adapter.ValueViewFactory;
import com.develop.zuzik.value_consumer_determiner.sample.value.BooleanMutableValue;

/**
 * Created by yaroslavzozulia on 9/12/17.
 */

public class BooleanMutableValueViewFactory implements ValueViewFactory<BooleanMutableValue> {

    @Override
    public ValueView<BooleanMutableValue> create(Context context) {
        return new BooleanMutableValueView(context);
    }
}
