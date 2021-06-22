# RM KMP SDK

<p>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/actions?query=workflow%3ATest"><img src="https://github.com/RevenueMonster/rm-kotlin-sdk/workflows/Test/badge.svg?branch=main" alt="test status" title="test status"/></a>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/actions?query=workflow%3APublish"><img src="https://github.com/RevenueMonster/rm-kotlin-sdk/workflows/Publish/badge.svg?branch=main" alt="publish status" title="publish status"/></a>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/releases"><img src="https://img.shields.io/github/v/tag/RevenueMonster/rm-kotlin-sdk" alt="semver tag" title="semver tag"/></a>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/blob/main/LICENSE"><img src="https://img.shields.io/github/license/RevenueMonster/rm-kotlin-sdk" alt="license" title="license"/></a>
</p>

![badge][badge-android]

[badge-android]: http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat
[badge-ios]: http://img.shields.io/badge/platform-ios-CDCDCD.svg?style=flat
[badge-js]: http://img.shields.io/badge/platform-js-F8DB5D.svg?style=flat
[badge-jvm]: http://img.shields.io/badge/platform-jvm-DB413D.svg?style=flat
[badge-linux]: http://img.shields.io/badge/platform-linux-2D3F6C.svg?style=flat 
[badge-windows]: http://img.shields.io/badge/platform-windows-4D76CD.svg?style=flat
[badge-mac]: http://img.shields.io/badge/platform-macos-111111.svg?style=flat
[badge-watchos]: http://img.shields.io/badge/platform-watchos-C0C0C0.svg?style=flat
[badge-tvos]: http://img.shields.io/badge/platform-tvos-808080.svg?style=flat
[badge-wasm]: https://img.shields.io/badge/platform-wasm-624FE8.svg?style=flat
[badge-nodejs]: https://img.shields.io/badge/platform-nodejs-68a063.svg?style=flat

> Revenue Monster Kotlin Multiplatform SDK, support every possible platform, such as desktop, mobile and web


## 🔨 Installation

```kts
kotlin {
   sourceSets {
      val commonMain by getting {
         dependencies {
            implementation("io.revenuemonster:rm-kotlin-sdk:1.0.0-alpha.0")
         }
      }
   }
}
```

<p align="center"><b>build.gradle(.kts)</b></p>

## 🪣 Requirements

- [Kotlin](https://github.com/JetBrains/kotlin) compiler at least 1.5.10
- [JDK](https://www.oracle.com/java/technologies/javase-downloads.html) at least 1.8

## ✨ Features

- Minimal package dependency
- Support multiple platform

## 📦️ Dependencies

| Package name | Version |
|--------------|---------|
| [ktor](https://github.com/ktorio/ktor) | 1.6.0 |
| [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) | 1.2.1 |

## 🤖 Supported Platforms

> The idea is to support every possible platforms without pain

| Platform | Architecture | Technology |Support |
|---------------|---|-----|:-------:|
| ![badge][badge-android] | jvm | JVM | ✅ |
| ![badge][badge-ios] | iosx64 | Kotlin/Native | ❎ |
| ![badge][badge-ios] | iosarm64 | Kotlin/Native | ❎ |
| ![badge][badge-mac] | - | Kotlin/Native | ❎ |
| ![badge][badge-linux] | - | Kotlin/Native | ❎ |
| ![badge][badge-windows] | - | Kotlin/Native | ❎ |

## ⚠️ Disclaimer

> This package under heavily development, please don't try this in production!

## 📄 License

MIT
