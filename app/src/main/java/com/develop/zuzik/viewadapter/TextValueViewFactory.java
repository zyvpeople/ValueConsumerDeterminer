package com.develop.zuzik.viewadapter;

import android.content.Context;

import com.develop.zuzik.viewadapter.recyclerviewadapter.ValueView;
import com.develop.zuzik.viewadapter.recyclerviewadapter.ValueViewFactory;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class TextValueViewFactory implements ValueViewFactory<TextValue> {

    @Override
    public ValueView<TextValue> create(Context context) {
        return new TextValueView(context);
    }
}
