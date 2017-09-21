package com.develop.zuzik.viewadapter.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.develop.zuzik.viewadapter.R;
import com.develop.zuzik.viewadapter.example.decorator.MatchParentWidthRecyclerViewAdapterDecorator;
import com.develop.zuzik.viewadapter.example.value.BooleanMutableValue;
import com.develop.zuzik.viewadapter.example.value.IntValue;
import com.develop.zuzik.viewadapter.example.value.StringMutableValue;
import com.develop.zuzik.viewadapter.example.value.TextValue;
import com.develop.zuzik.viewadapter.example.valueview.BooleanMutableValueViewFactory;
import com.develop.zuzik.viewadapter.example.valueview.IntLeftViewFactory;
import com.develop.zuzik.viewadapter.example.valueview.IntRightViewFactory;
import com.develop.zuzik.viewadapter.example.valueview.IntValueViewFactory;
import com.develop.zuzik.viewadapter.example.valueview.QAViewFactory;
import com.develop.zuzik.viewadapter.example.valueview.StringMutableValueViewFactory;
import com.develop.zuzik.viewadapter.example.valueview.TextValueViewFactory;
import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.RecyclerViewValueConsumerDeterminerAdapter;
import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.ValueViewFactory;
import com.develop.zuzik.viewadapter.value_consumer_determiner.builder.ValueConsumerDeterminerBuilder;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumerDeterminer;
import com.develop.zuzik.viewadapter.view_group_value_consumer_determiner_adapter.ViewGroupValueConsumerDeterminerAdapter;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean useRecycler = false;
        ViewGroup recyclerViewContainer = (ViewGroup) findViewById(R.id.recyclerViewContainer);
        ViewGroup linearLayoutContainer = (ViewGroup) findViewById(R.id.linearLayoutContainer);
        ViewGroup linearLayout = (ViewGroup) findViewById(R.id.linearLayout);

        recyclerViewContainer.setVisibility(useRecycler ? View.VISIBLE : View.GONE);
        linearLayoutContainer.setVisibility(!useRecycler ? View.VISIBLE : View.GONE);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        IntValue intValue2 = new IntValue(2);
        List<Object> values = Arrays.asList(
                new TextValue("Alpha"),
                new TextValue("Beta"),
                new TextValue("Gamma"),
                new IntValue(1),
                intValue2,
                new IntValue(3),
                new StringMutableValue(""),
                new StringMutableValue("1"),
                new StringMutableValue("2"),
                new StringMutableValue("3"),
                new StringMutableValue("4"),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                new BooleanMutableValue(true),
                new BooleanMutableValue(false),
                2,
                3);

        ValueConsumerDeterminerBuilder<Object, ValueViewFactory> intValueBuilder = ValueConsumerDeterminerBuilder
                .create(ValueViewFactory.class)
                .withEquality(2, new IntRightViewFactory())
                .withClass(Integer.class, new IntLeftViewFactory())
                .withReference(intValue2, new IntValueViewFactory())
                .withClass(IntValue.class, new IncorrectValueConsumer<IntValue>());

        ValueConsumerDeterminer<Object, ValueViewFactory> determiner = ValueConsumerDeterminerBuilder
                .create(ValueViewFactory.class)
                .withDefault(new QAViewFactory<>())
                .withConsumers(intValueBuilder)
                .withClass(TextValue.class, new TextValueViewFactory())
                .withClass(BooleanMutableValue.class, new BooleanMutableValueViewFactory())
                .withClass(StringMutableValue.class, new StringMutableValueViewFactory())
                .build();

        ExampleRecyclerViewAdapter<Object> adapter = new ExampleRecyclerViewAdapter<>(new RecyclerViewValueConsumerDeterminerAdapter<>(determiner));
        adapter.setValues(values);
        recyclerView.setAdapter(new MatchParentWidthRecyclerViewAdapterDecorator<>(adapter));

        new ViewGroupValueConsumerDeterminerAdapter<>(determiner).setValues(values, linearLayout);

    }

    private static final class IncorrectValueConsumer<Value> implements ValueConsumer<Value> {
    }

}
