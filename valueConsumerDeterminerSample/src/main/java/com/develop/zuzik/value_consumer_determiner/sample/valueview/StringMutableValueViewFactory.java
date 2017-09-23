package com.develop.zuzik.value_consumer_determiner.sample.valueview;

import android.content.Context;

import com.develop.zuzik.value_consumer_determiner.recycler_view_value_consumer_determiner_adapter.ValueView;
import com.develop.zuzik.value_consumer_determiner.recycler_view_value_consumer_determiner_adapter.ValueViewFactory;
import com.develop.zuzik.value_consumer_determiner.sample.value.StringMutableValue;

/**
 * Created by yaroslavzozulia on 9/12/17.
 */

public class StringMutableValueViewFactory implements ValueViewFactory<StringMutableValue> {

    @Override
    public ValueView<StringMutableValue> create(Context context) {
        return new StringMutableValueView(context);
    }
}
