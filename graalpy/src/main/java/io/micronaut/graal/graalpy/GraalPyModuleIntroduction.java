/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.graal.graalpy;

import io.micronaut.aop.InterceptorBean;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.context.exceptions.ConfigurationException;
import io.micronaut.core.annotation.Experimental;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.type.Argument;
import io.micronaut.graal.graalpy.annotations.GraalPyModuleBean;
import io.micronaut.inject.ArgumentInjectionPoint;
import io.micronaut.inject.BeanDefinition;
import io.micronaut.inject.InjectionPoint;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.micronaut.graal.graalpy.GraalPyContext.PYTHON;

@Experimental
@InterceptorBean(GraalPyModuleBean.class)
@Prototype
class GraalPyModuleIntroduction implements MethodInterceptor<Object, Object> {

    private static final Map<Class<?>, Source> SOURCES = new ConcurrentHashMap<>();

    private final Value pythonModule;
    private volatile Object pythonModuleInterface;

    GraalPyModuleIntroduction(GraalPyContext graalPyContext,
                              InjectionPoint<?> injectionPoint,
                              ApplicationContext context) {
        if (injectionPoint instanceof ArgumentInjectionPoint<?, ?> argumentInjectionPoint) {
            Argument<?> argument = argumentInjectionPoint.asArgument();
            Class<?> beanType = argument.getType();
            BeanDefinition<?> beanDefinition = context.getBeanDefinition(beanType);
            String moduleName = beanDefinition.stringValue(GraalPyModuleBean.class)
                .orElseThrow(() -> new ConfigurationException(String.format("@%s annotation without name of the module", GraalPyModuleBean.class.getSimpleName())));
            Source source = SOURCES.computeIfAbsent(beanType, (key) -> Source.create(PYTHON, "import " + moduleName + "; " + moduleName));
            try {
                this.pythonModule = graalPyContext.get().eval(source);
            } catch (PolyglotException ex) {
                throw new ConfigurationException(String.format("Import of Python module '%s' failed.", moduleName), ex);
            }
        } else {
            throw new IllegalStateException("Unexpected InjectionPoint passed to MethodInterceptor.");
        }
    }

    @Nullable
    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {
        Class<?> type = context.getDeclaringType();
        if (pythonModuleInterface == null) {
            pythonModuleInterface = pythonModule.as(type);
        }
        return context.getExecutableMethod().invoke(
                pythonModuleInterface,
                context.getParameterValues()
        );
    }

}
