package com.masterdevskills.cha1.ext5;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoggerTest {

    @Test
    void checkIfAllMethodImplemented() {
        Method[] methods = Logger.class.getMethods();
        assertMethodExist(methods, "info");
        assertMethodExist(methods, "trace");
        assertMethodExist(methods, "debug");
        assertMethodExist(methods, "warn");
    }

    private void assertMethodExist(final Method[] methods, final String name) {
        var param = "java.util.function.Supplier";
        boolean anyMatch = Arrays.stream(methods)
                          .anyMatch(method -> method.getName().equals(name)
                                                      && method.getParameterTypes()[1].getName().equals(param));
        assertTrue(anyMatch);
    }
}