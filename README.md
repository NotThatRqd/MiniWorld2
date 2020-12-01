# MiniWorld

MiniWorld is a Minecraft (Spigot) utility plugin for easily creating temporary instances of Minecraft worlds!
The intended use case for MiniWorld is for instanced dungeons, or other applications where a small temporary world is ideal.

## Supported Versions:
- Spigot 1.16.4

MiniWorld may function with additional versions of Minecraft, however this is untested and not guaranteed.
Support for pre-1.16 is not likely.

## MiniWorld Commands:

Command | Arguments | Description
------- | --------- | -----------
/mw | None | Provides information about MiniWorld and command usage
/mw list | None | Lists all worlds currently loaded into the server
/mw create | (world_name) | Creates a new MiniWorld. Note: MiniWorld names are prefixed by `mw/`
/mw clone | (world_name) | Creates a temporary clone of the targeted MiniWorld.
/mw delete | (world_name) | Deletes the targeted MiniWorld.
/mw goto | (world_name) | Teleports you to the desired world.

Access to all commands is currently provided by the permission `miniworld.op`.

## To-Do:

- [ ] Edit the behavior of 'StopEntityMovement' to not affect entities in clones of MiniWorlds.
- [ ] Come up with more things to add to the plugin. 
