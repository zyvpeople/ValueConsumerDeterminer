package com.develop.zuzik.value_consumer_determiner.sample.valueview;

import android.content.Context;

import com.develop.zuzik.value_consumer_determiner.sample.value.BooleanMutableValue;
import com.develop.zuzik.valueconsumerdeterminer.recyclerviewadapter.ValueView;
import com.develop.zuzik.valueconsumerdeterminer.recyclerviewadapter.ValueViewFactory;

/**
 * Created by yaroslavzozulia on 9/12/17.
 */

public class BooleanMutableValueViewFactory implements ValueViewFactory<BooleanMutableValue> {

    @Override
    public ValueView<BooleanMutableValue> create(Context context) {
        return new BooleanMutableValueView(context);
    }
}
