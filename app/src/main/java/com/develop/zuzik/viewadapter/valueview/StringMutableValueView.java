package com.develop.zuzik.viewadapter.valueview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.develop.zuzik.viewadapter.R;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.value.BooleanMutableValue;
import com.develop.zuzik.viewadapter.value.StringMutableValue;
import com.develop.zuzik.viewadapter.value.ValueChangedListener;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class StringMutableValueView extends LinearLayout implements ValueView<StringMutableValue> {

    private final EditText editText;
    private StringMutableValue value;

    public StringMutableValueView(Context context) {
        super(context);
        inflate(context, R.layout.view_string_mutable_value, this);
        editText = (EditText) findViewById(R.id.value);
    }

    @Override
    public void setValue(final StringMutableValue value) {
        this.value = value;
        value.setValueChangedListener(new ValueChangedListener<String>() {
            @Override
            public void onChanged(String s) {
                editText.removeTextChangedListener(watcher);
                if (!editText.getText().toString().equals(s)) {
                    editText.setText(s);
                }
                editText.addTextChangedListener(watcher);
            }
        });
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            value.setValue(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
