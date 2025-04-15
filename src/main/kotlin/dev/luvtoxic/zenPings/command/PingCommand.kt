package dev.luvtoxic.zenPings.command

import dev.luvtoxic.zenPings.ZenPings
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PingCommand(private val plugin: ZenPings) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(Component.text("Only players can use this command!", NamedTextColor.RED))
            return true
        }

        if (!sender.hasPermission("zenpings.toggle")) {
            sender.sendMessage(Component.text("No permission!", NamedTextColor.RED))
            return true
        }

        val settings = plugin.getPingSettings()

        if (args.isEmpty()) {
            val state = settings.togglePing(sender)
            sender.sendMessage(
                Component.text(
                    "Pings ${if (state) "enabled" else "disabled"}!",
                    if (state) NamedTextColor.GREEN else NamedTextColor.RED
                )
            )
            return true
        }

        when (args[0].lowercase()) {
            "on" -> {
                if (settings.isPingEnabled(sender)) {
                    sender.sendMessage(Component.text("Pings already enabled!", NamedTextColor.YELLOW))
                } else {
                    settings.togglePing(sender)
                    sender.sendMessage(Component.text("Pings enabled!", NamedTextColor.GREEN))
                }
            }
            "off" -> {
                if (!settings.isPingEnabled(sender)) {
                    sender.sendMessage(Component.text("Pings already disabled!", NamedTextColor.YELLOW))
                } else {
                    settings.togglePing(sender)
                    sender.sendMessage(Component.text("Pings disabled!", NamedTextColor.RED))
                }
            }
            "reload" -> {
                if (sender.hasPermission("zenpings.reload")) {
                    plugin.reloadConfig()
                    plugin.configManager.loadConfigValues()
                    sender.sendMessage(Component.text("Config reloaded!", NamedTextColor.GREEN))
                } else {
                    sender.sendMessage(Component.text("No permission!", NamedTextColor.RED))
                }
            }
            else -> sender.sendMessage(Component.text("Usage: /ping [on|off|reload]", NamedTextColor.RED))
        }

        return true
    }
}
