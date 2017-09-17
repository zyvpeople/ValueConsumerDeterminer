package com.develop.zuzik.viewadapter.value_consumer_determiner.builder;

import com.develop.zuzik.viewadapter.value_consumer_determiner.composite.CompositeValueConsumerDeterminer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.empty.EmptyValueConsumerDeterminer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.interfaces.ValueConsumerDeterminer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.predicate.ClassPredicate;
import com.develop.zuzik.viewadapter.value_consumer_determiner.predicate.EqualityPredicate;
import com.develop.zuzik.viewadapter.value_consumer_determiner.predicate.PredicateValueConsumerDeterminer;
import com.develop.zuzik.viewadapter.value_consumer_determiner.predicate.ReferencePredicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslavzozulia on 9/10/17.
 */
public class ValueConsumerDeterminerBuilder<Value> {

    public static <Value> ValueConsumerDeterminerBuilder<Value> create() {
        return new ValueConsumerDeterminerBuilder<>();
    }

    private final List<ValueConsumerDeterminer<Value>> determiners = new ArrayList<>();
    private ValueConsumer<Value> emptyConsumerOrNull = null;

    private ValueConsumerDeterminerBuilder() {
    }

    public <V extends Value> ValueConsumerDeterminerBuilder<Value> consumerForClass(Class<V> valueClass, ValueConsumer<V> consumer) {
        determiners.add(new PredicateValueConsumerDeterminer<Value>(new ClassPredicate<Value>((Class<Value>) valueClass), (ValueConsumer<Value>) consumer));
        return this;
    }

    public <V extends Value> ValueConsumerDeterminerBuilder<Value> consumerForEquality(V value, ValueConsumer<V> consumer) {
        determiners.add(new PredicateValueConsumerDeterminer<Value>(new EqualityPredicate<Value>(value), (ValueConsumer<Value>) consumer));
        return this;
    }

    public <V extends Value> ValueConsumerDeterminerBuilder<Value> consumerForReference(V reference, ValueConsumer<V> consumer) {
        determiners.add(new PredicateValueConsumerDeterminer<Value>(new ReferencePredicate<Value>(reference), (ValueConsumer<Value>) consumer));
        return this;
    }

    public ValueConsumerDeterminerBuilder<Value> emptyConsumer(ValueConsumer<Value> emptyConsumer) {
        emptyConsumerOrNull = emptyConsumer;
        return this;
    }

    public ValueConsumerDeterminerBuilder<Value> consumers(ValueConsumerDeterminerBuilder<Value> builder) {
        determiners.addAll(builder.determiners);
        return this;
    }

    public ValueConsumerDeterminer<Value> build() {
        CompositeValueConsumerDeterminer<Value> compositeDeterminer = new CompositeValueConsumerDeterminer<>(determiners);
        return emptyConsumerOrNull == null
                ? compositeDeterminer
                : new EmptyValueConsumerDeterminer<>(compositeDeterminer, emptyConsumerOrNull);
    }
}
