package dev.luvtoxic.zenPings.handler

import dev.luvtoxic.zenPings.config.ConfigManager
import dev.luvtoxic.zenPings.config.PingConfig
import dev.luvtoxic.zenPings.service.NotificationService
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.entity.Player
import java.util.regex.Pattern

class PingHandler(
    private val settings: PingSettings,
    private val notificationService: NotificationService,
    private val configManager: ConfigManager
) {
    private val miniMessage = MiniMessage.miniMessage()
    private val plainSerializer = PlainTextComponentSerializer.plainText()

    fun processMessage(sender: Player, message: Component): Component? {
        val config = configManager.getConfig()
        val mentionedPlayers = mutableSetOf<Player>()

        if (!settings.canPing(sender, config.cooldownSeconds)) {
            notificationService.notifyCooldown(sender)
            return null // Cancel message if on cooldown
        }

        var newMessage = plainSerializer.serialize(message)

        for (target in sender.server.onlinePlayers) {
            val pattern = Pattern.compile("(?i)${Regex.escape(config.prefix)}${Regex.escape(target.name)}\\b")
            if (pattern.matcher(newMessage).find() && settings.isPingEnabled(target)) {
                mentionedPlayers.add(target)
                val replacement = miniMessage.serialize(getReplacementComponent(config, target, sender))
                newMessage = pattern.matcher(newMessage).replaceAll(replacement)
            }
        }

        mentionedPlayers.forEach { target ->
            notificationService.notifyPlayer(target, sender, config)
        }

        return try {
            miniMessage.deserialize(newMessage)
        } catch (e: Exception) {
            sender.server.logger.warning("Failed to deserialize message: $newMessage")
            message // Fallback to original message
        }
    }

    private fun getReplacementComponent(config: PingConfig, target: Player, sender: Player): Component {
        val color = when {
            sender.hasPermission("zenpings.color.vip") -> "<gold>"
            sender.hasPermission("zenpings.color.default") -> "<aqua>"
            else -> "<gray>"
        }

        val format = config.replacementText.replace("%player%", target.name)
        return miniMessage.deserialize("$color$format")
    }
}