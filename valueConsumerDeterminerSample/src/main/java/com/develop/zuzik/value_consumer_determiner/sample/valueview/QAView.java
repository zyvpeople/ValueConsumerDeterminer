package com.develop.zuzik.value_consumer_determiner.sample.valueview;

import android.content.Context;
import android.widget.LinearLayout;

import com.develop.zuzik.value_consumer_determiner.R;
import com.develop.zuzik.valueconsumerdeterminer.recyclerview.ValueView;

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
