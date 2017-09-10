package com.develop.zuzik.viewadapter;

import android.content.Context;
import android.view.View;

import com.develop.zuzik.viewadapter.recyclerviewadapter.ViewFactory;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */

public class QAViewFactory implements ViewFactory {

    @Override
    public View create(Context context) {
        return new QAView(context);
    }
}
