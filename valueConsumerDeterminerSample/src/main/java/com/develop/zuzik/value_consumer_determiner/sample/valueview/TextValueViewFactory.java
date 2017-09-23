package com.develop.zuzik.value_consumer_determiner.sample.valueview;

import android.content.Context;

import com.develop.zuzik.value_consumer_determiner.sample.value.TextValue;
import com.develop.zuzik.valueconsumerdeterminer.recyclerview.ValueView;
import com.develop.zuzik.valueconsumerdeterminer.recyclerview.ValueViewFactory;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class TextValueViewFactory implements ValueViewFactory<TextValue> {

    @Override
    public ValueView<TextValue> create(Context context) {
        return new TextValueView(context);
    }
}
