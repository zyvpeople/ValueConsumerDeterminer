package com.develop.zuzik.viewadapter.recyclerviewadapter;

import android.content.Context;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.interfaces.ViewFactory;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

class EmptyViewFactory implements ViewFactory {
    @Override
    public View create(Context context) {
        return new View(context);
    }
}
