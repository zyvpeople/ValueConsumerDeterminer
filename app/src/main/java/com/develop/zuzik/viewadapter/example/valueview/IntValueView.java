package com.develop.zuzik.viewadapter.example.valueview;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.develop.zuzik.viewadapter.R;
import com.develop.zuzik.viewadapter.example.value.IntValue;
import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.ValueView;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class IntValueView extends LinearLayout implements ValueView<IntValue> {

    private final TextView value;

    public IntValueView(Context context) {
        super(context);
        inflate(context, R.layout.view_int_value, this);
        value = (TextView) findViewById(R.id.value);
    }

    @Override
    public void setValue(IntValue intValue) {
        value.setText(getClass().getSimpleName() + " : " + intValue.value);
    }
}
