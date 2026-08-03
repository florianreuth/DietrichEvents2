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
 * This class represents an event that can be aborted. Event types that extend this class have to be static in order to
 * work. Listeners can call {@link #stopHandling()} to abort the event.
 *
 * @param <T> The type of the listener.
 */
public abstract class BreakableEvent<T> implements AbstractEvent<T> {

    /**
     * Whether the event is cancelled.
     */
    private boolean abort;

    @Override
    public final void call(T listener) {
        if (!isAbort()) {
            call0(listener);
        }
    }

    /**
     * Calls the listener.
     *
     * @param listener The listener to call.
     */
    public abstract void call0(final T listener);

    /**
     * Cancels the event.
     */
    public void stopHandling() {
        setAbort(true);
    }

    public boolean isAbort() {
        return abort;
    }

    public void setAbort(boolean abort) {
        this.abort = abort;
    }

}
