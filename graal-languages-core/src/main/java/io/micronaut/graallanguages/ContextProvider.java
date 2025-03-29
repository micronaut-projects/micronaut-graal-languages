/*
 * Copyright 2017-2024 original authors
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
package io.micronaut.graallanguages;

import io.micronaut.core.annotation.Experimental;
import org.graalvm.polyglot.Context;

/**
 * Wrapper around {@link Context} since it is a final class and cannot be proxied after its instantiation in a factory.
 * @author Sergio del Amo
 * @since 1.1.0
 */
@Experimental
public class ContextProvider {
    private Context context;

    /**
     * Empty Constructor.
     */
    public ContextProvider() {
    }

    /**
     *
     * @param context Graal Languages {@link Context}
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     *
     * @return Graal Languages {@link Context}
     */
    public Context getContext() {
        return context;
    }
}
