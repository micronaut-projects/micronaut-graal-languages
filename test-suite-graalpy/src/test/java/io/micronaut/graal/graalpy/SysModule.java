package io.micronaut.graal.graalpy;

import io.micronaut.graal.graalpy.annotations.GraalPyModule;

@GraalPyModule("sys")
public interface SysModule{
    public boolean is_finalizing();
    public String[] path();
}
