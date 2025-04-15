package dev.luvtoxic.zenPings

import dev.luvtoxic.zenPings.command.PingCommand
import dev.luvtoxic.zenPings.command.PingTabCompleter
import dev.luvtoxic.zenPings.config.ConfigManager
import dev.luvtoxic.zenPings.handler.PingHandler
import dev.luvtoxic.zenPings.handler.PingSettings
import dev.luvtoxic.zenPings.listener.ChatListener
import dev.luvtoxic.zenPings.service.NotificationService
import org.bukkit.plugin.java.JavaPlugin

class ZenPings : JavaPlugin() {
    lateinit var configManager: ConfigManager
        private set
    private lateinit var pingSettings: PingSettings
    private lateinit var notificationService: NotificationService
    private lateinit var pingHandler: PingHandler

    override fun onEnable() {
        saveDefaultConfig()
        configManager = ConfigManager(this)
        configManager.loadConfigValues()
        pingSettings = PingSettings()
        notificationService = NotificationService()
        pingHandler = PingHandler(pingSettings, notificationService, configManager)

        server.pluginManager.registerEvents(ChatListener(pingHandler), this)
        getCommand("ping")?.apply {
            setExecutor(PingCommand(this@ZenPings))
            setTabCompleter(PingTabCompleter())
        }

        logger.info("ZenPings enabled successfully!")
    }

    override fun onDisable() {
        logger.info("ZenPings disabled.")
    }

    fun getPingSettings(): PingSettings = pingSettings
}
