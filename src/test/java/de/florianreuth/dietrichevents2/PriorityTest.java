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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class PriorityTest {

    private static final List<Object> VALUES = new LinkedList<>();

    @Test
    void fire() {
        DietrichEvents2.global().subscribe(TestListener.TestEvent.ID, createListener("Test3"), Priorities.NORMAL);
        DietrichEvents2.global().subscribe(TestListener.TestEvent.ID, createListener("Test2"), Priorities.HIGH);
        DietrichEvents2.global().subscribe(TestListener.TestEvent.ID, createListener("Test1"), Priorities.LOWEST);
        DietrichEvents2.global().subscribe(TestListener.TestEvent.ID, createListener("Test5"), Priorities.HIGHEST);
        DietrichEvents2.global().subscribe(TestListener.TestEvent.ID, createListener("Test4"), Priorities.LOW);
        DietrichEvents2.global().subscribe(TestListener.TestEvent.ID, createListener("Test6"), Priorities.FALLBACK);
        DietrichEvents2.global().subscribe(TestListener.TestEvent.ID, createListener("Test7"), Priorities.MONITOR);
        DietrichEvents2.global().callUnsafe(TestListener.TestEvent.ID, new TestListener.TestEvent(null));

        Assertions.assertEquals(VALUES.get(0), "Test7");
        Assertions.assertEquals(VALUES.get(1), "Test5");
        Assertions.assertEquals(VALUES.get(2), "Test2");
        Assertions.assertEquals(VALUES.get(3), "Test3");
        Assertions.assertEquals(VALUES.get(4), "Test4");
        Assertions.assertEquals(VALUES.get(5), "Test1");
        Assertions.assertEquals(VALUES.get(6), "Test6");
    }

    private static TestListener createListener(final String name) {
        return event -> VALUES.add(name);
    }

}
