package com.develop.zuzik.value_consumer_determiner.sample.valueview;

import android.content.Context;

import com.develop.zuzik.valueconsumerdeterminer.recyclerviewadapter.ValueView;
import com.develop.zuzik.valueconsumerdeterminer.recyclerviewadapter.ValueViewFactory;


/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class QAViewFactory<Value> implements ValueViewFactory<Value> {

    @Override
    public ValueView<Value> create(Context context) {
        return new QAView<>(context);
    }
}
