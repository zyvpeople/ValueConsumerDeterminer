package com.develop.zuzik.viewadapter;

import android.content.Context;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class IntValueViewFactory implements ValueViewFactory<IntValue> {

    @Override
    public ValueView<IntValue> create(Context context) {
        return new IntValueView(context);
    }
}
