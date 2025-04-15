package dev.luvtoxic.zenPings.config

import org.bukkit.Sound

data class PingConfig(
    var replacementText: String = "<gray>@<aqua>%player%</aqua>",
    var sound: Sound = Sound.BLOCK_NOTE_BLOCK_PLING,
    var volume: Float = 1.0f,
    var pitch: Float = 0.8f,
    var prefix: String = "@",
    var cooldownSeconds: Long = 5L,
    var actionBarMessage: String = "<yellow>%player% mentioned you!</yellow>"
)