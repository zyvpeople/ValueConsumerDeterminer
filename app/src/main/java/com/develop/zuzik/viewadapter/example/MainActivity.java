package com.develop.zuzik.viewadapter.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.develop.zuzik.viewadapter.R;
import com.develop.zuzik.viewadapter.example.value.IntValue;
import com.develop.zuzik.viewadapter.example.value.TextValue;
import com.develop.zuzik.viewadapter.example.valueview.IntLeftViewFactory;
import com.develop.zuzik.viewadapter.example.valueview.IntRightViewFactory;
import com.develop.zuzik.viewadapter.example.valueview.IntValueViewFactory;
import com.develop.zuzik.viewadapter.example.valueview.QAViewFactory;
import com.develop.zuzik.viewadapter.example.valueview.TextValueViewFactory;
import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.RecyclerViewValueConsumerDeterminerAdapter;
import com.develop.zuzik.viewadapter.example.decorator.MatchParentWidthRecyclerViewAdapterDecorator;
import com.develop.zuzik.viewadapter.example.value.BooleanMutableValue;
import com.develop.zuzik.viewadapter.example.value.StringMutableValue;
import com.develop.zuzik.viewadapter.recycler_view_value_consumer_determiner_adapter.ValueViewFactory;
import com.develop.zuzik.viewadapter.value_consumer_determiner.builder.ValueConsumerDeterminerBuilder;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumerDeterminer;
import com.develop.zuzik.viewadapter.example.valueview.BooleanMutableValueViewFactory;
import com.develop.zuzik.viewadapter.example.valueview.StringMutableValueViewFactory;
import com.develop.zuzik.viewadapter.view_group_value_consumer_determiner_adapter.ViewGroupValueConsumerDeterminerAdapter;

import java.util.Arrays;
import java.util.List;

//TODO: list is changed
//TODO: animation
//TODO: not only list but grid etc
//TODO: item decoration
//TODO: create special view with interface and remove checking in onBindViewHolder
//TODO: add log if set already set factory
//TODO: focus is removed when scroll item out of screen
//TODO: fix warnings
//TODO: check if view is not ValueView

//TODO: add valueConsumer generic to builder and add protection
//TODO: add valueview generic to factory for protection
//TODO: forget about type casting
//TODO: write test
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

//        RecyclerViewAdapter<Object> adapter = RecyclerViewAdapterBuilder
//                .create()
//                .displayCustomViewIfValueCantBeDisplayed(new QAViewFactory())
////                .viewForClass(TextValue.class, new TextValueViewFactory())
////                .viewForClass(IntValue.class, new IntValueViewFactory())
////                .viewForClass(BooleanMutableValue.class, new BooleanMutableValueViewFactory())
////                .viewForClass(StringMutableValue.class, new StringMutableValueViewFactory())
//
//                .viewForClass(TextValue.class, new TextValueViewFactory())
//                .viewForEquality(2, new IntRightViewFactory())
//                .viewForClass(Integer.class, new IntLeftViewFactory())
//                .viewForReference(intValue2, new IntValueViewFactory())
////                .viewForClass(StringMutableValue.class, new StringMutableValueViewFactory())
//                .build();
//        adapter.setValues(values);
//        recyclerView.setAdapter(new MatchParentWidthRecyclerViewAdapterDecorator<>(adapter));


        ValueConsumerDeterminerBuilder<Object, ValueViewFactory> intValueBuilder = ValueConsumerDeterminerBuilder
                .create(ValueViewFactory.class)
                .consumerForEquality(2, new IntRightViewFactory())
                .consumerForClass(Integer.class, new IntLeftViewFactory())
                .consumerForReference(intValue2, new IntValueViewFactory())
                .consumerForClass(IntValue.class, new IncorrectValueConsumer<IntValue>());

        ValueConsumerDeterminer<Object, ValueViewFactory> determiner = ValueConsumerDeterminerBuilder
                .create(ValueViewFactory.class)
                .emptyConsumer(new QAViewFactory<>())
                .consumers(intValueBuilder)
                .consumerForClass(TextValue.class, new TextValueViewFactory())
                .consumerForClass(BooleanMutableValue.class, new BooleanMutableValueViewFactory())
                .consumerForClass(StringMutableValue.class, new StringMutableValueViewFactory())
                .build();

        ExampleRecyclerViewAdapter<Object> adapter = new ExampleRecyclerViewAdapter<>(new RecyclerViewValueConsumerDeterminerAdapter<>(determiner));
        adapter.setValues(values);
        recyclerView.setAdapter(new MatchParentWidthRecyclerViewAdapterDecorator<>(adapter));

        new ViewGroupValueConsumerDeterminerAdapter<>(determiner).setValues(values, linearLayout);

    }

    private static final class IncorrectValueConsumer<Value> implements ValueConsumer<Value> {
    }

}
