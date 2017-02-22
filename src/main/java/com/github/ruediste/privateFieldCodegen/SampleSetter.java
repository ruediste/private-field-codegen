package com.github.ruediste.privateFieldCodegen;

import java.util.function.BiConsumer;

public class SampleSetter implements BiConsumer<Sample, String> {

    public void accept(Sample sample, String message) {
        sample.message = message;

    }
}
