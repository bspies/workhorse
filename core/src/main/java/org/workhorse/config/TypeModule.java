package org.workhorse.config;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import org.workhorse.var.DateType;
import org.workhorse.var.MutableVariable;
import org.workhorse.var.VariableKey;
import org.workhorse.var.impl.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Maps native types to variable types in WorkHorse.
 *
 * @author Brennan Spies
 */
public class TypeModule extends AbstractModule {

    private static final TypeLiteral<Class<? extends MutableVariable<?>>> ANY_MUTABLE_TYPE =
            new TypeLiteral<Class<? extends MutableVariable<?>>>(){};

    private MapBinder<VariableKey,Class<? extends MutableVariable<?>>> variableBinder;


    @Override protected void configure() {
        variableBinder = MapBinder.newMapBinder(binder(), VariableKey.TYPE_LITERAL, ANY_MUTABLE_TYPE);
        bindType(String.class, TextVariable.class);
        bindType(byte[].class, BinaryVariable.class);
        bindType(Short.class, ShortVariable.class);
        bindType(Integer.class, IntegerVariable.class);
        bindType(Long.class, LongVariable.class);
        bindType(BigDecimal.class, DecimalVariable.class);
        bindType(Date.class, TimestampVariable.class);
        bindType(Date.class, DateType.DATETIME, DateTimeVariable.class);
        bindType(Date.class, DateType.DATE, DateVariable.class);
        bindType(Date.class, DateType.TIME, TimeVariable.class);
    }

    private void bindType(Class<?> nativeType, Class<? extends MutableVariable<?>> variableType) {
        variableBinder.addBinding(VariableKey.get(nativeType)).toInstance(variableType);
    }

    private void bindType(Class<?> nativeType, Object qualifier, Class<? extends MutableVariable<?>> variableType) {
        variableBinder.addBinding(VariableKey.get(nativeType, qualifier))
                .toInstance(variableType);
    }
}
