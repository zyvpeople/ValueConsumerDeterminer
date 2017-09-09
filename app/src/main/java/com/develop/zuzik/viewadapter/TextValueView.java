package com.develop.zuzik.viewadapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.develop.zuzik.viewadapter.recyclerviewadapter.ValueView;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class TextValueView extends LinearLayout implements ValueView<TextValue> {

    private final TextView text;

    public TextValueView(Context context) {
        super(context);
        inflate(context, R.layout.view_text_value, this);
        text = (TextView) findViewById(R.id.text);
    }

    @Override
    public void setValue(TextValue textValue) {
        text.setText(getClass().getSimpleName() + " : " + textValue.text);
    }
}
