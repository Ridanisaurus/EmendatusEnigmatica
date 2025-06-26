# Emendatus Enigmatica
#### MC 1.21 | Version 2.2.0

- Port to NeoForge 21.1.72 for Minecraft 1.21.1!
- Fixed EE Data Generation throwing exceptions, when other mods crashed earlier.
- Added Strata Limit per Material ([#205](https://github.com/Ridanisaurus/EmendatusEnigmatica/issues/205))
- Added JSON Validation System (Credit: Kanzaji | [#218](https://github.com/Ridanisaurus/EmendatusEnigmatica/pull/218))
- Fixed [#211](https://github.com/Ridanisaurus/EmendatusEnigmatica/issues/211)
- Fixed [#157](https://github.com/Ridanisaurus/EmendatusEnigmatica/issues/157)

Take a note that this is an alpha build of the port to 1.21.1. There is still a lot to be done, but the core functionality is there (Item Registration and Ore Generation). You can find the progress of the port, and planned features for 1.21.1 in [this project](https://github.com/users/Kanzaji/projects/3).

### Currently known bugs / Missing Features
- Sample System is currently disabled, requires full rework.
- Smithing recipe generation doesn't work.
- Trims don't render properly on armor.
- Some Fields, which get validated, have no use (Addon related stuff)
- JEI/EMI Support is not included in this version.
- Addon system is designated for a rework.
- Deprecation warnings are missing proper links to the wiki (Wiki is WIP)

Addons will be ported when the base mod is fully functional.
