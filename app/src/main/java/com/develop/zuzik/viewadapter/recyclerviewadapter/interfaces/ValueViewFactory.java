package com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces;

import android.content.Context;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public interface ValueViewFactory<Value> {
    ValueView<Value> create(Context context);
}
