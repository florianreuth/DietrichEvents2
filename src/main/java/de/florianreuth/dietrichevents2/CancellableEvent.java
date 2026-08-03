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
 * This class represents an event that can be cancelled.
 *
 * @param <T> The type of the listener.
 */
public abstract class CancellableEvent<T> implements AbstractEvent<T> {

    /**
     * Whether the event is cancelled.
     */
    private boolean cancelled;

    /**
     * Cancels the event.
     */
    public void cancel() {
        setCancelled(true);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
