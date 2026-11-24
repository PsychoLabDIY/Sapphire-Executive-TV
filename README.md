# Sapphire Executive TV

**A custom Android TV home screen for Xiaomi TV A Pro 55 (2026).**  
CleanYourTV-based interface · graphite and champagne accents · remote-first navigation

![Sapphire Executive V6.2 - native Home screen](docs/images/home.png)

*Direct 1920 x 1080 Android screen capture from the installed V6.2 build, 24 November 2026. No camera, crop or compositing.*

[Polski opis](docs/README.pl.md) · [Implementation notes](docs/implementation.md) · [MIT license](LICENSE)

## What it does

| Feature | Implementation |
| --- | --- |
| Six favorite apps | HBO Max, Netflix, Prime Video, YouTube, Google Play, Projectivy |
| App library | Native CleanYourTV catalogue; 17 apps visible in the V6.2 native capture |
| System information | Live RAM usage, storage availability and a native RAM bar |
| Clock and date | Dynamic Polish header and champagne accent |
| Weather | Fixed-city Zamość forecast via Open-Meteo; foreground-triggered requests, 30-minute refresh after success |
| Remote navigation | Existing D-pad, focus handling, and native input controls |
| Inputs | Existing HDMI, AV and television source picker |
| Wallpaper | Existing personal night-time Zamość artwork retained |
| Update safety | Local backup, package/version/certificate checks, offline Gradle build, unit tests, in-place APK update |

## On-device gallery

| App library | HDMI menu |
| --- | --- |
| ![Native app library](docs/images/app-library.png) | ![Native HDMI picker](docs/images/hdmi.png) |

All three PNGs are byte-for-byte original captures from the same on-device ADB QA report. [QA findings and limitations](docs/qa-v62.md).
## Test device

| Component | Configuration |
| --- | --- |
| TV | Xiaomi TV A Pro 55 (2026), MiTV-MZTU0 / river |
| OS | Android TV 14 |
| Memory | 2 GB class; 1784 MB reported by the launcher |
| Panel | 4K |
| Base | [CleanYourTV](https://github.com/Gathin23/cleanyourtv), local version 3.6 |
| Development | Windows, JDK 17, Android SDK 34, Gradle 8.9, ADB |

## Project status

V6.2 is running on the test TV. Six native dock cards have 25% transparent backgrounds, separate native rims and equal spacing. Home, clock, weather, library and HDMI were observed. The idle screen was not reached within the QA timeout; remote input also interrupted the automated focus sequence. These captures do not establish panel quality, long-run performance or reboot persistence. The source snapshot below remains partial.
## Source and availability

The `implementation/` directory contains actual Java components, V4 resource files, helper excerpts and geometry tests used in the local project. It is **not a standalone Android build**; the full locally patched upstream tree and signing material are not distributed. See the implementation notes for the boundaries of this snapshot. Do not uninstall a working launcher to resolve a signature mismatch.

## Credits

Built on [Gathin's CleanYourTV](https://github.com/Gathin23/cleanyourtv) ([base commit](https://github.com/Gathin23/cleanyourtv/tree/fe6cdcefa817da9f6facd3a17a3c5a086124f258), MIT). Weather data: [Open-Meteo](https://open-meteo.com/). App names and logos belong to their respective owners. Not affiliated with Xiaomi, streaming providers or the upstream maintainer.
