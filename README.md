# ⚠️ Notice

MiniWorld2 **does not** have all features implemented and is not ready for use!! Sorry, I'm still working on it!

## MiniWorld2

MiniWorld2 is a [Minecraft Paper](https://papermc.io/) utility/API plugin for easily creating temporary instances of Minecraft worlds!
The intended use case for MiniWorld2 is for mini-games, instanced dungeons, or other applications where
a small temporary world is ideal. At this point there is no online Maven repository, you just have to
download, build, and add it to your own local Maven repository if you want to use it.

The original MiniWorld plugin was created by [TheKDub](https://github.com/thekdub). At first, I was going to simply
update the old code, but at this point it seems it would be easier to simply re-write it as a whole, as the old code
was very messy and to be honest I *hate* migrating legacy code.

## Dependencies 🧰
- [Multiverse-Core](https://www.spigotmc.org/resources/multiverse-core.390/) is **required** for MiniWorld2
- [WorldGuard](https://dev.bukkit.org/projects/worldguard) support is planned but is not yet done

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
