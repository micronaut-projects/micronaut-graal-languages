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

import io.micronaut.core.annotation.Experimental;
import jakarta.inject.Singleton;
import org.graalvm.polyglot.Context;
import org.graalvm.python.embedding.utils.GraalPyResources;

@Experimental
@Singleton
final class DefaultGraalPyContextBuilderFactory implements GraalPyContextBuilderFactory {
    @Override
    public Context.Builder createBuilder() {
        return GraalPyResources.contextBuilder();
    }
}
