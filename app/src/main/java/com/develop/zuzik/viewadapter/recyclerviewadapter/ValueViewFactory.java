package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.content.Context;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public interface ValueViewFactory<Value> {
    ValueView<Value> create(Context context);
}
