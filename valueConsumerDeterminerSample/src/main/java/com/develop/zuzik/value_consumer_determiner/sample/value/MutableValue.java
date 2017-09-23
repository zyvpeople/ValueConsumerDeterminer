package com.develop.zuzik.value_consumer_determiner.sample.value;

/**
 * Created by yaroslavzozulia on 9/12/17.
 */

public class MutableValue<Value> {

    private Value value;
    private ValueChangedListener<Value> listener;

    public MutableValue(Value value) {
        this.value = value;
    }

    public final void setValue(Value value) {
        this.value = value;
        notifyListener();
    }

    public final void setValueChangedListener(ValueChangedListener<Value> listener) {
        this.listener = listener;
        notifyListener();
    }

    private void notifyListener() {
        if (listener != null) {
            listener.onChanged(value);
        }
    }
}
