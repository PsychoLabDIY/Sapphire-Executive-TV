# Implementation notes

## Baseline and evolution

Upstream: `Gathin23/cleanyourtv` at `fe6cdcefa817da9f6facd3a17a3c5a086124f258` (MIT). The physical TV uses a locally modified tree that also includes the earlier wallpaper-cache repair. This repository documents the actual customization rather than claiming the upstream commit alone produces the installed APK.

- V2 / V2.3: six custom banners, colored dynamic statistics, slim dock and same-certificate APK update.
- V3.1: six-app home filter, native app library, RAM bar, city weather, corrected single-row tile geometry; retains normal geometry for other layouts.
- V4: bounded dock width increase, independent weather label, native menu resources and library focus styling.

## Included implementation

`implementation/java/ExecutiveStats.java` and `ExecutiveWeather.java` are the actual foreground statistics and weather helpers. The `.java.txt` files are V4 method excerpts integrated into AppAdapter and MainActivity; they are not standalone classes. `implementation/skin/` contains the actual V4 drawable XML files. `implementation/tests/` contains the additional geometry tests, which depend on the corresponding locally patched AppAdapter.

The original launcher owns D-pad navigation, app catalogue, HDMI source selection and system integration. V4 changes their presentation without replacing these mechanisms. Weather uses fixed city-center coordinates (not GPS), HTTPS, bounded response size, timeouts and stale-cache handling. The app requires INTERNET for weather requests. Refer to Open-Meteo's attribution and usage terms before redistributing or operating a public build.

## Deployment and verification boundaries

The local Windows installer copied a matching previously installed source tree, patched anchored source locations, built offline and ran the upstream plus custom unit tests. It checked package ID, version code and original signing certificate, backed up the existing APK and used in-place installation. The signing key never needs to leave the owner's computer. This public snapshot omits the local installer, original APK, signing keys, private paths, network addresses, full artwork and individual brand assets. It cannot be built directly without the remaining local modifications.

## Evidence

Three original 1920 x 1080 native ADB PNGs from the V6.2 QA archive. See [QA findings](qa-v62.md). Older implementation excerpts describe the earlier V4 snapshot, not a full buildable V6.2 tree.
