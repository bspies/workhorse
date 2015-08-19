package org.workhorse.config;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import org.workhorse.activity.Task;
import org.workhorse.activity.impl.HumanTask;
import org.workhorse.exec.ctx.ActivityContext;
import org.workhorse.process.impl.StandardProcess;
import org.workhorse.process.impl.StandardSubProcess;
import org.workhorse.process.Process;
import org.workhorse.process.SubProcess;
import org.workhorse.exec.impl.StackExecution;
import org.workhorse.exec.ctx.Context;
import org.workhorse.exec.Execution;
import org.workhorse.factory.impl.DefaultActivityFactory;
import org.workhorse.factory.ActivityFactory;
import org.workhorse.var.DateType;
import org.workhorse.var.MutableVariable;
import org.workhorse.var.VariableFactory;
import org.workhorse.var.VariableKey;
import org.workhorse.var.impl.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */
public class CoreModule extends AbstractModule
{
    private static final TypeLiteral<Class<? extends MutableVariable<?>>> ANY_MUTABLE_TYPE =
            new TypeLiteral<Class<? extends MutableVariable<?>>>(){};
    
    /**
     * Configures the core dependencies with default bindings.
     */
    @Override protected void configure() {

        bind(Process.class).to(StandardProcess.class);
        bind(SubProcess.class).to(StandardSubProcess.class);
        bind(Task.class).to(HumanTask.class);
        bind(Execution.class).to(StackExecution.class);
        //bind(Context.class).to(ActivityContext.class); TODO qualify this
        bind(ActivityFactory.class).to(DefaultActivityFactory.class);
    }
}
