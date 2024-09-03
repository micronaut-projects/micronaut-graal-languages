package example;

import io.micronaut.graal.graalpy.annotations.GraalPyModuleBean;

@GraalPyModuleBean("example")
public interface ExampleService {
    String foo();
}
