package com.develop.zuzik.viewadapter;

import android.content.Context;
import android.widget.LinearLayout;

import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.ValueView;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class QAView<Value> extends LinearLayout implements ValueView<Value> {

    public QAView(Context context) {
        super(context);
        inflate(context, R.layout.view_qa, this);
    }

    @Override
    public void setValue(Value value) {
    }
}
