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
 * An annotation to indicate that an interface uses the
 * <a href="https://www.graalvm.org/truffle/javadoc/org/graalvm/polyglot/Value.html#target-type-mapping-heading">polyglot type mapping</a>.
 *
 * <p>
 * <b>Example</b> with a python module hello.py
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
 *
 */

@Introduction
@Bean
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GraalPyModuleBean {
    /**
     * Python module to import.
     * @return python module
     */
    String value();
}
