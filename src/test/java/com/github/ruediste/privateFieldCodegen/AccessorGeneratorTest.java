package com.github.ruediste.privateFieldCodegen;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AccessorGeneratorTest {

    @Test
    public void test() {
        Sample sample = new Sample();
        assertEquals(null, sample.getMessage());
        AccessorGenerator.generate().setMessage(sample, "Hello World");
        assertEquals("Hello World", sample.getMessage());
    }
}
