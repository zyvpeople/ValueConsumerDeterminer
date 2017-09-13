package com.develop.zuzik.viewadapter.valueview;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.develop.zuzik.viewadapter.R;
import com.develop.zuzik.viewadapter.TextValue;
import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ValueView;
import com.develop.zuzik.viewadapter.value.BooleanMutableValue;
import com.develop.zuzik.viewadapter.value.ValueChangedListener;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class BooleanMutableValueView extends LinearLayout implements ValueView<BooleanMutableValue> {

    private final CompoundButton compoundButton;

    public BooleanMutableValueView(Context context) {
        super(context);
        inflate(context, R.layout.view_boolean_mutable_value, this);
        compoundButton = (CompoundButton) findViewById(R.id.value);
    }

    @Override
    public void setValue(final BooleanMutableValue value) {
        value.setValueChangedListener(new ValueChangedListener<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                compoundButton.setOnCheckedChangeListener(null);
                if (compoundButton.isChecked() != aBoolean) {
                    compoundButton.setChecked(aBoolean);
                }
                compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        value.setValue(b);
                    }
                });
            }
        });
    }
}
