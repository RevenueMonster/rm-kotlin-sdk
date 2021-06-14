# RM KMP SDK

> Revenue Monster Kotlin Multiplatform SDK, support every possible platform, such as desktop, mobile and web

## 🔨 Installation

```kts
kotlin {
   sourceSets {
      commonMain {
         dependencies {
            implementation 'org.revenuemonster.sdk:RevenueMonsterSDK:1.0.0-alpha.0'
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
| Android | - | JVM | ❎ |
| iOS | x64 | Kotlin/Native | ❎ |
| iOS | (arm64) | Kotlin/Native | ❎ |
| macOS | - | Kotlin/Native | ❎ |
| linux | - | Kotlin/Native | ❎ |
| window | - | Kotlin/Native | ❎ |

## ⚠️ Disclaimer

> This package under heavily development, please don't try this yet!

## 📄 License

MIT
