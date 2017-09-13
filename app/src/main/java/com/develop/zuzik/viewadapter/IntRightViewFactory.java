package com.develop.zuzik.viewadapter;

import android.content.Context;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueViewFactory;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class IntRightViewFactory implements ValueViewFactory<Integer> {

    @Override
    public ValueView<Integer> create(Context context) {
        return new IntRightView(context);
    }
}
