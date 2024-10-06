package io.micronaut.graallanguages.core;

import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.graallanguages.ContextProvider;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import org.graalvm.polyglot.Value;

@Requires(property = "spec.name", value = "HomeControllerTest")
//tag::controller[]
@Controller
public class HomeController {
    private final ContextProvider contextProvider;

    HomeController(ContextProvider contextProvider) {
        this.contextProvider = contextProvider;
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    HttpResponse<String> index() {
        Value f = contextProvider.getContext().eval("js", "x => x + 1");
        if (f.canExecute()) {
            return HttpResponse.ok("" + f.execute(41).asInt());
        }
        return HttpResponse.serverError();
    }
}
//end::controller[]
