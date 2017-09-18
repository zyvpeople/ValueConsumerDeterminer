package com.develop.zuzik.viewadapter.example.valueview;

import android.content.Context;

import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.ValueView;
import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.ValueViewFactory;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class IntRightViewFactory implements ValueViewFactory<Integer> {

    @Override
    public ValueView<Integer> create(Context context) {
        return new IntRightView(context);
    }
}
