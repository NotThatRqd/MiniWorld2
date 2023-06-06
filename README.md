# ⚠️ Notice

MiniWorld2 still needs polish. Currently, all the messages are just short unformatted responses[^1]. MiniWorld2
also has not been tested that much. If you find a bug or mistake, please open an issue pointing it out! :)
I'll be honest, I don't really expect anyone to use this, but I will still be using this myself for personal
projects.

[^1]: See issue #1

## MiniWorld2

MiniWorld2 is a [Minecraft Paper](https://papermc.io/) utility/API plugin for easily creating temporary instances of Minecraft worlds!
The intended use case for MiniWorld2 is for mini-games, instanced dungeons, or other applications where
a small temporary world is ideal. At this point there is no online Maven repository, you just have to
download, build, and add it to your own local Maven repository if you want to use it.

The original MiniWorld plugin was created by [TheKDub](https://github.com/thekdub). At first, I was going to simply
update the old code, but at this point it seems it would be easier to simply re-write it as a whole, as the old code
was very messy and to be honest I *hate* migrating legacy code.

## Commands

All the commands of MiniWorld2 are simply user-friendly ways to access the api (located in `MiniWorld2Manager.java`).
The functionality of each and every command can be accessed directly through java if you want to use MiniWorld2 with
your plugin :)

| Command/Usage                           | Description                                   | Permission                 |
|-----------------------------------------|-----------------------------------------------|----------------------------|
| `/CreateVoidWorld world-name`           | A helper command to create void worlds        | MiniWorld2.CreateVoidWorld |
| `/CreateMiniature parent-world`         | Creates a miniature[^2] of the provided world | MiniWorld2.CreateMiniature |
| `/RemoveMiniature miniature-world-name` | Deletes a miniature[^2] world                 | MiniWorld2.RemoveMiniature |

[^2]: A "miniature" world is a temporary clone of a world. This world will automatically be deleted when the server stops.

## Dependencies 🧰
- [Multiverse-Core](https://www.spigotmc.org/resources/multiverse-core.390/) is **required** for MiniWorld2
- [WorldGuard](https://dev.bukkit.org/projects/worldguard) is optional and integration can be disabled in the config.yml

## Supported Versions:
- Paper 1.19.4

MiniWorld2 may function with additional versions of Minecraft, however this is untested and not guaranteed.

## Todo:
- [x] Add createEmptyWorld
- [x] Add createCloneOf
- [x] Add deleteWorld
- [x] Add deleteCloneWorld
- [x] Add WorldGuard integration (one possible issue: #5)
- [ ] Rename MiniWorld2 --> MiniWorlds2
- [ ] Add polish -- make commands have nice formatted responses
