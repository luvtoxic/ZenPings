package dev.luvtoxic.zenPings.service

import dev.luvtoxic.zenPings.config.PingConfig
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player

class NotificationService {
    private val miniMessage = MiniMessage.miniMessage()

    fun notifyPlayer(player: Player, sender: Player, config: PingConfig) {
        player.playSound(player.location, config.sound, config.volume, config.pitch)
        val message = miniMessage.deserialize(
            config.actionBarMessage.replace("%player%", sender.name)
        )
        player.sendActionBar(message)
    }

    fun notifyCooldown(player: Player) {
        player.sendMessage(
            net.kyori.adventure.text.Component.text(
                "Please wait before pinging again!",
                net.kyori.adventure.text.format.NamedTextColor.RED
            )
        )
    }
}