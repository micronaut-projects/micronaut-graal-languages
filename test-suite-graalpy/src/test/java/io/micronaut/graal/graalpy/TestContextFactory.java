package io.micronaut.graal.graalpy;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import org.graalvm.polyglot.Context;
import org.graalvm.python.embedding.utils.GraalPyResources;

@Bean
@Singleton
@Replaces(GraalPyContextBuilderFactory.class)
@Requires(env = ContextBuilderFactoryTest.ENV)
public class TestContextFactory implements GraalPyContextBuilderFactory {

    static final String PYTHON_PATH = "/micronaut/test";

    @Override
    public Context.Builder createBuilder() {
        Context.Builder builder = GraalPyResources.contextBuilder();
        builder.option("python.PythonPath", PYTHON_PATH);
        return builder;
    }
}
