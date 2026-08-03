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

/**
 * This class is optional and does not have to be used. It shows how priorities can be used. Priorities are sorted
 * in ascending order, so the highest priority is the lowest number.
 */
public class Priorities {

    public static final int FALLBACK = Integer.MAX_VALUE;
    public static final int LOWEST = 2;
    public static final int LOW = 1;
    public static final int NORMAL = 0; // Default priority
    public static final int HIGH = -1;
    public static final int HIGHEST = -2;
    public static final int MONITOR = Integer.MIN_VALUE;

}
