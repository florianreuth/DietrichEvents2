/*
 * This file is part of DietrichEvents2 - https://github.com/florianreuth/DietrichEvents2
 * Copyright (C) 2023-2026 Florian Reuth <git@florianreuth.de>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.florianreuth.dietrichevents2;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 4, time = 5)
@Measurement(iterations = 4, time = 5)
public class BenchmarkCaller implements BenchmarkListener {

    private static final int ITERATIONS = 100_000;

    @Setup
    public void setup() {
        DietrichEvents2.global().subscribe(BenchmarkEvent.ID, this);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1, warmups = 1)
    public void callBenchmarkListener(Blackhole blackhole) {
        for (int i = 0; i < ITERATIONS; i++) {
            DietrichEvents2.global().call(BenchmarkEvent.ID, new BenchmarkListener.BenchmarkEvent(blackhole));
        }
    }

    @Override
    public void onBenchmark(Blackhole blackhole) {
        blackhole.consume(Integer.bitCount(Integer.parseInt("123")));
    }

}
