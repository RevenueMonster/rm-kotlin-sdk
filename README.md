# RM KMP SDK

<p>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/actions?query=workflow%3ATest"><img src="https://github.com/RevenueMonster/rm-kotlin-sdk/workflows/Test/badge.svg?branch=main" alt="test status" title="test status"/></a>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/actions?query=workflow%3APublish"><img src="https://github.com/RevenueMonster/rm-kotlin-sdk/workflows/Publish/badge.svg?branch=main" alt="publish status" title="publish status"/></a>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/releases"><img src="https://img.shields.io/github/v/tag/RevenueMonster/rm-kotlin-sdk" alt="semver tag" title="semver tag"/></a>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/blob/main/LICENSE"><img src="https://img.shields.io/github/license/RevenueMonster/rm-kotlin-sdk" alt="license" title="license"/></a>
</p>

![badge][badge-android]

[badge-android]: http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat

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
| Android | - | JVM | ✅ |
| iOS | x64 | Kotlin/Native | ❎ |
| iOS | (arm64) | Kotlin/Native | ❎ |
| macOS | - | Kotlin/Native | ❎ |
| linux | - | Kotlin/Native | ❎ |
| window | - | Kotlin/Native | ❎ |

## ⚠️ Disclaimer

> This package under heavily development, please don't try this in production!

## 📄 License

MIT
