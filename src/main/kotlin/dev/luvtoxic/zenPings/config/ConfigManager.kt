package dev.luvtoxic.zenPings.config


import dev.luvtoxic.zenPings.ZenPings
import org.bukkit.Sound
import java.util.logging.Logger

class ConfigManager(private val plugin: ZenPings) {
    private val logger: Logger = plugin.logger
    private var pingConfig: PingConfig = PingConfig()

    fun loadConfigValues() {
        plugin.saveDefaultConfig()
        plugin.reloadConfig()
        val config = plugin.config
        pingConfig = PingConfig(
            replacementText = config.getString("replacement-text") ?: pingConfig.replacementText,
            sound = runCatching {
                Sound.valueOf(config.getString("sound") ?: "BLOCK_NOTE_BLOCK_PLING")
            }.getOrElse {
                logger.warning("Invalid sound in config, defaulting to BLOCK_NOTE_BLOCK_PLING")
                Sound.BLOCK_NOTE_BLOCK_PLING
            },
            volume = config.getDouble("volume", 1.0).toFloat(),
            pitch = config.getDouble("pitch", 0.8).toFloat(),
            prefix = config.getString("prefix") ?: pingConfig.prefix,
            cooldownSeconds = config.getLong("cooldown-seconds", 5L),
            actionBarMessage = config.getString("action-bar-message") ?: pingConfig.actionBarMessage
        )
    }

    fun getConfig(): PingConfig = pingConfig
}