package com.develop.zuzik.viewadapter.valueview;

import android.content.Context;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;
import com.develop.zuzik.viewadapter.value.BooleanMutableValue;
import com.develop.zuzik.viewadapter.value.StringMutableValue;

/**
 * Created by yaroslavzozulia on 9/12/17.
 */

public class StringMutableValueViewFactory implements ValueViewFactory<StringMutableValue> {

    @Override
    public ValueView<StringMutableValue> create(Context context) {
        return new StringMutableValueView(context);
    }
}
