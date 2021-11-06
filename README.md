[![Build & Release](https://github.com/Ridanisaurus/EmendatusEnigmatica/actions/workflows/build-release.yml/badge.svg?branch=1.16-Current)](https://github.com/Ridanisaurus/EmendatusEnigmatica/actions/workflows/build-release.yml)

![EE Logo](https://i.imgur.com/pcX09Dd.png)

This mod is created to help unifying some items, blocks, and ores that are common among most mods. This mod provide little to no functionality as a stand-alone mod. It does however offer a wide range of options for Modpack authors to utilize these blocks, and items as a method of unification.

All blocks, and items have been properly Forge tagged, and have all the basic recipes. This mod also includes a world gen of ores that match the strata provided by other mods such as Create and Quark.

# License #
Emendatus Enigmatica is licensed under the MIT license. You may use it in modpacks, reviews, or any other form as long as you abide by the terms of this license.

# Integration #
The wonderful folks at AVSP have created KubeJS Scripts to help integrating Emendatus Enimgatica with your modpack.
[![AVSP EE Scripts](https://i.imgur.com/CquGD8Q.png)](https://www.curseforge.com/minecraft/customization/avsps-easy-emendatus-enigmatica-scripts)

# Maven #
Emendatus Enigmatica is available via [Nanite Maven](https://maven.nanite.dev/releases/com/ridanisaurus) for development purposes.

Update your `build.gradle` file to include the following:

```groovy
repositories {
    maven {
        url "https://maven.nanite.dev"
    }
}

dependencies {
    runtimeOnly fg.deobf("com.ridanisaurus:emendatusenigmatica:${ee_version}+mc${mc_version}")
}
```

Add the following to your `gradle.properties` file:

**NOTE:** Please refer to the maven page for the latest `ee_version`
```properties
ee_version=1.2.13-build.3
mc_version=1.16.5
```

# Showcase #
Here is a sample set of the items and blocks added by Emendatus Enigmatica
![Items&Blocks](https://i.imgur.com/miKIWM1.png)

And a small sample of an Ore/Stone combination that can be found in the world.
![Ores](https://i.imgur.com/aDXCUCN.png)