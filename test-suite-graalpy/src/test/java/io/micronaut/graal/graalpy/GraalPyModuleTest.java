package io.micronaut.graal.graalpy;

import io.micronaut.context.BeanContext;
import jakarta.inject.Inject;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
public class GraalPyModuleTest {

    @Inject
    SysModule sysModule;

    @Inject
    BeanContext beanContext;

    @Test
    void testGraalPyModule() {
        assertTrue(beanContext.containsBean(SysModule.class));

        assertFalse(sysModule.is_finalizing());
        assertTrue(Arrays.asList(sysModule.path()).contains("/graalpy_vfs/src"));
    }

}
