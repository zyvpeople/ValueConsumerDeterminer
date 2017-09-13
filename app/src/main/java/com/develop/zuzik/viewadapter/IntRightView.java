package com.develop.zuzik.viewadapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class IntRightView extends LinearLayout implements ValueView<Integer> {

    private final TextView value;

    public IntRightView(Context context) {
        super(context);
        inflate(context, R.layout.view_int_right, this);
        value = (TextView) findViewById(R.id.value);
    }

    @Override
    public void setValue(Integer intValue) {
        value.setText(getClass().getSimpleName() + " : " + intValue);
    }
}