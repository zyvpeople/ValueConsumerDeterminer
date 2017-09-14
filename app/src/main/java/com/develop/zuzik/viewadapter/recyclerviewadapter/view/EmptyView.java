package com.develop.zuzik.viewadapter.recyclerviewadapter.view;

import android.content.Context;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;

/**
 * Created by yaroslavzozulia on 9/14/17.
 */

public class EmptyView<Value> extends View implements ValueView<Value> {

    public EmptyView(Context context) {
        super(context);
    }

    @Override
    public void setValue(Value value) {
    }
}
