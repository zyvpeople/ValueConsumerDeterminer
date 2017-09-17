package com.develop.zuzik.viewadapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.ValueView;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class IntLeftView extends LinearLayout implements ValueView<Integer> {

    private final TextView value;

    public IntLeftView(Context context) {
        super(context);
        inflate(context, R.layout.view_int_left, this);
        value = (TextView) findViewById(R.id.value);
    }

    @Override
    public void setValue(Integer intValue) {
        value.setText(getClass().getSimpleName() + " : " + intValue);
    }
}
