package io.micronaut.graal.graalpy;

import io.micronaut.graal.graalpy.annotations.GraalPyModuleBean;

@GraalPyModuleBean("dealer")  // (1)
public interface DealerService {
    Object[] shuffle(Object[] cards); // (2)
}
