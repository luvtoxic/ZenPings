# ZenPings
**ZenPings** is a lightweight, no-nonsense Minecraft plugin for [Paper](https://papermc.io/) servers that brings clean, customizable ping notifications to your server chat.

Mention someone with `@Username`, and they’ll get a sound alert, styled name highlight, and a configurable action bar message. Perfect for staying noticed in busy chats without the clutter.

## Features 
- **Ping Alerts** - Mention a player with `@Username` to highlight their name and play a sound.


- **Toggle Pings** - Players can enable/disable pings with `/ping` or `/ping on/off`.


- **Cooldowns** - Prevents sping spam with a configureable delay.


- **Optimized** - Built in Kotlin with performance in mind.  


- **Paper-Only** - Designed specifically for modern Paper servers for best performance.


## Commands

| Command              | Description                                 |
|----------------------|---------------------------------------------|
| `/ping`              | Toggle ping alerts on/off                   |
| `/ping on` / `off`   | Explicitly enable or disable pings          |
| `/ping reload`       | Reloads the plugin config (admin only)      |


## Permissions
| Permission            | Description                                     |
|------------------------|-------------------------------------------------|
| `zenpings.toggle`      | Allows use of the `/ping` command               |
| `zenpings.reload`      | Allows `/ping reload`                           |
| `zenpings.color.vip`   | Grants gold-colored pings                       |
| `zenpings.color.default` | Grants standard ping color (aqua/gray/Custom)   |


## Configuration
```yaml
replacement-text: "<yellow>@<aqua>%player%</aqua>"
sound: BLOCK_NOTE_BLOCK_PLING
volume: 1.0
pitch: 0.8
prefix: "@"
cooldown-seconds: 5
action-bar-message: "<yellow>%player% mentioned you!</yellow>"
```


## License
This project is licensed for **non-commercial use only**.  
You may modify and share it freely, but reselling or including it in paid products is **not allowed**.  
See [LICENSE](./LICENSE) for full terms.


## Building from Source
### 1. Clone the Repository
```bash
git clone https://github.com/luvtoxic/ZenPings.git
cd ZenPings
```

### 2. Compile and Package
```bash
mvn clean package
```
The compiled plugin JAR will be located at:
```
target/ZenPings-x.x.x.jar
```

### 3. Ready for use!
Drop the `ZenPings.jar` in the plugins folder & its ready!


# Support
Join my [Discord](https://discord.gg/HFP5v3rZAA) for any issues / suggestions.


> Built with ☕ and ❤️ by [luvtoxic](https://github.com/luvtoxic)





