plugins {
    id("io.micronaut.build.internal.graal-languages-module")
}
dependencies {
    api(libs.managed.graal.polyglot)
    implementation(mn.micronaut.context)
    testRuntimeOnly(libs.managed.graal.js)
    testImplementation(mn.micronaut.http.server.netty)
    testImplementation(mn.micronaut.jackson.databind)
    testImplementation(mn.micronaut.http.client)
    testAnnotationProcessor(mn.micronaut.inject.java)
    testImplementation(mnTest.micronaut.test.junit5)
    testRuntimeOnly(libs.junit.jupiter.engine)
}
tasks.withType<Test> {
    useJUnitPlatform()
}
micronautBuild {
    binaryCompatibility {
        enabled.set(false)
    }
}
