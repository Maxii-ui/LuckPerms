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

package me.lucko.luckperms.nukkit.listeners;

import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.server.RemoteServerCommandEvent;
import cn.nukkit.event.server.ServerCommandEvent;
import me.lucko.luckperms.common.config.ConfigKeys;
import me.lucko.luckperms.common.locale.Message;
import me.lucko.luckperms.nukkit.LPNukkitPlugin;

import java.util.Locale;
import java.util.regex.Pattern;

public class NukkitPlatformListener implements Listener {
    private static final Pattern OP_COMMAND_PATTERN = Pattern.compile("^/?(\\w+:)?(deop|op)( .*)?$");

    private final LPNukkitPlugin plugin;

    public NukkitPlatformListener(LPNukkitPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        handleCommand(e.getPlayer(), e.getMessage().toLowerCase(Locale.ROOT), e);
    }

    @EventHandler(ignoreCancelled = true)
    public void onServerCommand(ServerCommandEvent e) {
        handleCommand(e.getSender(), e.getCommand().toLowerCase(Locale.ROOT), e);
    }

    @EventHandler(ignoreCancelled = true)
    public void onRemoteServerCommand(RemoteServerCommandEvent e) {
        handleCommand(e.getSender(), e.getCommand().toLowerCase(Locale.ROOT), e);
    }

    private void handleCommand(CommandSender sender, String cmdLine, Cancellable event) {
        if (cmdLine.isEmpty()) {
            return;
        }

        if (this.plugin.getConfiguration().get(ConfigKeys.OPS_ENABLED)) {
            return;
        }

        if (OP_COMMAND_PATTERN.matcher(cmdLine).matches()) {
            event.setCancelled(true);
            Message.OP_DISABLED.send(this.plugin.getSenderFactory().wrap(sender));
        }
    }

}
