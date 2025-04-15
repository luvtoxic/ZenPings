package dev.luvtoxic.zenPings.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class PingTabCompleter : TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): List<String> {
        if (args.size == 1) {
            val options = mutableListOf<String>()
            if (sender.hasPermission("zenpings.toggle")) {
                options.addAll(listOf("on", "off"))
            }
            if (sender.hasPermission("zenpings.reload")) {
                options.add("reload")
            }
            return options
        }
        return emptyList()
    }
}