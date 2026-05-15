package io.micronaut.graal.graalpy;

import io.micronaut.context.ApplicationContext;
import io.micronaut.json.JsonMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.beans.beancontext.BeanContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
    @Inject JsonMapper jsonMapper;

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

    @Test
    void testDeserializeNestedSerdeableRecord() throws IOException {
        byte[] json = """
            {
              "fileName": "review.txt",
              "sentiment": {
                "positive": 0.75,
                "neutral": 0.2,
                "negative": 0.05,
                "compound": 0.91,
                "label": "positive"
              }
            }
            """.getBytes(StandardCharsets.UTF_8);

        SentimentAnalysis analysis = jsonMapper.readValue(json, SentimentAnalysis.class);

        assertEquals("review.txt", analysis.fileName());
        assertEquals("positive", analysis.sentiment().label());
        assertEquals(0.91, analysis.sentiment().compound());
    }
}
