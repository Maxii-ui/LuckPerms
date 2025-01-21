/*
 * This file is part of LuckPerms, licensed under the MIT License.
 *
 *  Copyright (c) lucko (Luck) <luck@lucko.me>
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package net.luckperms.api.event.sync;

import net.luckperms.api.event.LuckPermsEvent;
import net.luckperms.api.event.type.Cancellable;
import net.luckperms.api.event.util.Param;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.UUID;

/**
 * Called after a request for synchronisation has been received via the messaging service,
 * but before it has actually been completed.
 *
 * <p>Note: the generic {@link PreSyncEvent} will also be called for {@link SyncType#FULL full syncs}.</p>
 */
public interface PreNetworkSyncEvent extends LuckPermsEvent, Cancellable {

    /**
     * Gets the ID of the sync request
     *
     * @return the id of the sync request
     */
    @Param(0)
    @NonNull UUID getSyncId();

    /**
     * Gets the sync type.
     *
     * @return the sync type
     * @since 5.5
     */
    @Param(1)
    @NonNull SyncType getType();

    /**
     * Gets the unique id of the specific user that will be synced, if applicable.
     *
     * @return the unique id of the specific user
     * @since 5.5
     */
    @Param(2)
    @Nullable UUID getSpecificUserUniqueId();

}
