package com.develop.zuzik.viewadapter;

import android.content.Context;

import com.develop.zuzik.viewadapter.recyclerviewadapter.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.ViewFactory;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class IntValueViewFactory implements ViewFactory<IntValue> {

    @Override
    public ValueView<IntValue> create(Context context) {
        return new IntValueView(context);
    }
}
