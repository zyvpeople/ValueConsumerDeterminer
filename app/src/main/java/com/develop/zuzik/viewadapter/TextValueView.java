package com.develop.zuzik.viewadapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.develop.zuzik.viewadapter.recyclerviewadapter.ValueView;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class TextValueView extends LinearLayout implements ValueView<TextValue> {

    private final TextView value;

    public TextValueView(Context context) {
        super(context);
        inflate(context, R.layout.view_text_value, this);
        value = (TextView) findViewById(R.id.value);
    }

    @Override
    public void setValue(TextValue textValue) {
        value.setText(getClass().getSimpleName() + " : " + textValue.value);
    }
}
