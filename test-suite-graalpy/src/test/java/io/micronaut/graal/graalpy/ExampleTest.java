package io.micronaut.graal.graalpy;

import io.micronaut.context.ApplicationContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.beans.beancontext.BeanContext;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Tests snippets used in the documentation
@MicronautTest(startApplication = false, environments = ExampleTest.ENV)
public class ExampleTest {
    public static final String ENV = "graalpy.example.test";

    @Inject ApplicationContext beanContext;
    @Inject GraalPyContext pyContext;

    @Test
    void testDealerService() {
        // In order to avoid using resources in the tests, we create the Python module dynamically
        // and create the DealerService bean only after the module was created
        pyContext.get().eval("python",
            // language=python
            """
            # tag::python_module[]
            import random
            def shuffle(data):
                random.shuffle(data)
                return data
            # end::python_module[]

            import sys
            import types
            m = types.ModuleType('dealer')
            m.__dict__['shuffle'] = shuffle
            sys.modules['dealer'] = m
            """);
        DocExample service = beanContext.getBean(DocExample.class);
        Object[] cards = service.play();
        assertEquals(Set.of(cards), Set.of(1, 2, 3));
    }
}
