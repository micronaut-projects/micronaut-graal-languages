package io.micronaut.graal.graalpy;

import io.micronaut.context.BeanContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static io.micronaut.graal.graalpy.TestContextFactory.PYTHON_PATH;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = ContextBuilderFactoryTest.ENV)
public class ContextBuilderFactoryTest {

    public static final String ENV = "graalpy.context.builder.factory.test";

    @Inject
    SysModule sysModule;

    @Inject
    BeanContext beanContext;

    @Test
    void testContextBuilderFactory() {
        assertTrue(beanContext.containsBean(SysModule.class));

        assertFalse(sysModule.is_finalizing());
        assertFalse(Arrays.asList(sysModule.path()).contains("/graalpy_vfs/src"));
        assertTrue(Arrays.asList(sysModule.path()).contains(PYTHON_PATH));
    }

}
