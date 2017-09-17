package com.develop.zuzik.viewadapter;

import android.content.Context;

import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.ValueView;
import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.ValueViewFactory;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class IntValueViewFactory implements ValueViewFactory<IntValue> {

    @Override
    public ValueView<IntValue> create(Context context) {
        return new IntValueView(context);
    }
}
