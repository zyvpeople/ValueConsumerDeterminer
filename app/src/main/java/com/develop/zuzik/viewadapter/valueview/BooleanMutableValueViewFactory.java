package com.develop.zuzik.viewadapter.valueview;

import android.content.Context;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;
import com.develop.zuzik.viewadapter.value.BooleanMutableValue;

/**
 * Created by yaroslavzozulia on 9/12/17.
 */

public class BooleanMutableValueViewFactory implements ValueViewFactory<BooleanMutableValue> {

    @Override
    public ValueView<BooleanMutableValue> create(Context context) {
        return new BooleanMutableValueView(context);
    }
}
