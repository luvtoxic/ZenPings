package dev.luvtoxic.zenPings.handler

import org.bukkit.entity.Player
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class PingSettings {
    private val toggles: ConcurrentHashMap<UUID, Boolean> = ConcurrentHashMap()
    private val lastPingTimes: ConcurrentHashMap<UUID, Long> = ConcurrentHashMap()

    fun isPingEnabled(player: Player): Boolean = toggles.getOrDefault(player.uniqueId, true)

    fun togglePing(player: Player): Boolean {
        return toggles.compute(player.uniqueId) { _, current -> !(current ?: true) } ?: true
    }

    fun canPing(player: Player, cooldownSeconds: Long): Boolean {
        val now = System.currentTimeMillis()
        val lastPing = lastPingTimes.getOrDefault(player.uniqueId, 0L)
        return if (now - lastPing >= cooldownSeconds * 1000) {
            lastPingTimes[player.uniqueId] = now
            true
        } else {
            false
        }
    }
}