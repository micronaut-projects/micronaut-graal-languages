package io.micronaut.graal.graalpy;

import io.micronaut.graal.graalpy.annotations.GraalPyModuleBean;

@GraalPyModuleBean("sys")
public interface SysModule{
    public boolean is_finalizing();
    public String[] path();
}
