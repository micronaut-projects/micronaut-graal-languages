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

import jakarta.annotation.PreDestroy;
import jakarta.inject.Singleton;
import org.graalvm.polyglot.Context;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Wrapper for a GraalPy context.
 */
@Singleton
@io.micronaut.context.annotation.Context
final class GraalPyContext {

    static final String PYTHON = "python";

    private Context context;

    public GraalPyContext(GraalPyContextBuilderFactory builder) {
        context = builder.createBuilder().build();
        context.initialize(PYTHON);
    }

    Context get() {
        return context;
    }

    @PreDestroy
    void close() throws IOException {
        try {
            context.interrupt(Duration.of(5,  ChronoUnit.SECONDS));
            context.close(true);
        } catch (Exception e) {
            // ignore
        } finally {
            context = null;
        }
    }
}
