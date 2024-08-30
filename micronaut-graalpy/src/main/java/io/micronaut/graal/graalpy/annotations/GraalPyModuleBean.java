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
package io.micronaut.graal.graalpy.annotations;

import io.micronaut.aop.Introduction;
import io.micronaut.context.annotation.Bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for Micronaut Introduction Advice that implements this interface using
 * the <a href="https://www.graalvm.org/python">GraalPy</a> Python implementation and the
 * <a href="https://www.graalvm.org/truffle/javadoc/org/graalvm/polyglot/Value.html#target-type-mapping-heading">polyglot type mapping</a>.
 * <p>
 * <b>Global state</b>: there is a single shared <a href="https://www.graalvm.org/truffle/javadoc/org/graalvm/polyglot/Context">polyglot Context</a>
 * that the advice uses to import the Python modules
 * annotated with {@link GraalPyModuleBean} and execute their Python code. If multiple {@link GraalPyModuleBean}
 * annotations map to the same Python module, they will all delegate to the same underlying Python
 * module object.
 * <p>
 * <b>Python configuration</b>: the default Python context configuration can be altered by
 * implementing {@link io.micronaut.graal.graalpy.GraalPyContextBuilderFactory}.
 * <p>
 * <b>Threading</b>: the Python modules exposed to Java using this annotation can be accessed from
 * multiple threads in parallel. On the Python side different Java threads will appear as
 * different <a href="https://docs.python.org/3/library/threading.html#module-threading">Python level threads</a>.
 * <p>
 * <b>Scalability</b>: for compatibility reasons, the Python engine internally serializes multithreaded execution
 * of Python code using the <a href="https://docs.python.org/3/glossary.html#term-global-interpreter-lock">global
 * interpreter lock</a> and therefore there is only one thread executing Python code at a time. (Note: individual
 * threads take turns after fixed time quantum in a round-robin fashion).
 * <p>
 * If better utilization of multicore hardware is required, one can use multiple polyglot contexts.
 * Each context represents an isolated self-contained Python engine and has its own "global"
 * interpreter lock. The caveats are:
 * <ul>
 *     <li>Native Python extensions, such as NumPy, are currently not supported in multi-context mode.</li>
 *     <li>Python polyglot contexts are not cheap to create and should be pooled.</li>
 *     <li>There are caveats when sharing Python objects between contexts. It is recommended
 *     to avoid this if possible. More <a href="https://www.graalvm.org/truffle/javadoc/org/graalvm/polyglot/Context.Builder.html#allowValueSharing(boolean)">details</a>.</li>
 * </ul>
 * <p>
 * Currently, this Introduction Advice does not support multi-context execution, but users can
 * implement it by hand using the Context APIs directly. We are collecting feedback and requirements
 * to provide such features in the next versions.
 * <p>
 * <b>Deployment</b>: for the ease of installation and deployment of 3rd party Python packages
 * as well as custom Python scripts or packages, it is recommended to used the GraalPy Maven
 * plugin. The polyglot context is automatically configured to include the resources configured in
 * the plugin.
 * <p>
 * <b>Example</b>: python module hello.py
 * <pre>
 * def hello(txt):
 *     return "hello " + txt
 * </pre>
 *
 * <pre>
 * &#064;GraalPyModuleBinding("hello")
 * public interface Hello {
 *     String hello(String txt);
 * }
 * </pre>
 *
 * <pre>
 * &#064;Inject
 * public void inject(Hello hello) {
 *     hello.hello("python");
 * }
 * </pre>
 */

@Introduction
@Bean
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GraalPyModuleBean {
    /**
     * Name of the Python module to import. This may be a relative or absolute import.
     * In general, a valid value is anything that can follow after the {@code import} keyword
     * in Python.
     *
     * @return name of Python module
     */
    String value();
}
