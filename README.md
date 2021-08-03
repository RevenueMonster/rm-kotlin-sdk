# RM Kotlin Multiplatform SDK

<p>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/actions?query=workflow%3ATest"><img src="https://github.com/RevenueMonster/rm-kotlin-sdk/workflows/Test/badge.svg?branch=main" alt="test status" title="test status"/></a>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/releases"><img src="https://img.shields.io/github/v/tag/RevenueMonster/rm-kotlin-sdk" alt="semver tag" title="semver tag"/></a>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/blob/main/LICENSE"><img src="https://img.shields.io/github/license/RevenueMonster/rm-kotlin-sdk" alt="license" title="license"/></a>
</p>

![badge][badge-android]
![badge][badge-mac]
![badge][badge-linux]
![badge][badge-windows]

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

### Maven Central


### Gradle

```bash
dependencies {
    implementation("io.revenuemonster:rm-kotlin-sdk:1.0.0-beta.1")
}
```

### Android

For Android, RM-Kotlin-SDK work on Android 5.1 (API 22) or greater.\
If your target Android devices running **lower** than Android 8 (API 26)⚠\
Please config your `build.gradle` as shown below

```bash
android {
  defaultConfig {
      multiDexEnabled = true
  }

  compileOptions {
      coreLibraryDesugaringEnabled = true
    
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
  }

  dependencies {
    implementation 'io.revenuemonster.sdk:rm-kotlin-sdk:1.0.0-beta.1'
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
  }
  
}
```

And make sure your 
[Android Gradle plugin](https://developer.android.com/studio/releases/gradle-plugin#updating-plugin)
set to 4.0.0 or higher (recommended version 4.2.0).
```bash
buildscript {
    repositories {
        google()
        ...
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.0")
    }
}
```


#### Multiplatform

If you're using `multiplatform`, modify your `build.gradle.kts` file as follow

```bash
plugins {
   kotlin("multiplatform") version "1.5.10"
}

repositories {
   ...
   mavenCentral()
}

kotlin {
   sourceSets {
      val commonMain by getting {
         dependencies {
            implementation("io.revenuemonster:rm-kotlin-sdk:1.0.0-beta.1")
         }
      }
   }
}
```




## 🪣 Requirements

- [Kotlin](https://github.com/JetBrains/kotlin) compiler at least 1.5.10
- [JDK](https://www.oracle.com/java/technologies/javase-downloads.html) at least 1.8

## ✨ Features

- Minimal package dependency
- Support multiple platform

## 📦️ Dependencies

| Package name                                                             | Version |
| ------------------------------------------------------------------------ | ------- |
| [ktor](https://github.com/ktorio/ktor)                                   | 1.6.2   |
| [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) | 1.2.2   |
| [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime)           | 0.2.1   |

## 🤖 Supported Platforms

> The idea is to support every possible platforms without pain

| Platform                | Architecture | Technology    | Support |
| ----------------------- | ------------ | ------------- | :-----: |
| ![badge][badge-mac]     | macOS        | JVM           |   ✅    |
| ![badge][badge-linux]   | linux        | JVM           |   ✅    |
| ![badge][badge-windows] | window       | JVM           |   ✅    |
| ![badge][badge-android] | android      | JVM           |   ✅    |
| ![badge][badge-ios]     | iosx64       | Kotlin/Native |   ❌    |
| ![badge][badge-ios]     | iosarm64     | Kotlin/Native |   ❌    |

## 🙈 Example

```kotlin
import io.revenuemonster.sdk.model.Error
import io.revenuemonster.sdk.model.request.QuickPayOrder
import io.revenuemonster.sdk.model.request.QuickPayRequest
import io.revenuemonster.sdk.Config
import io.revenuemonster.sdk.RevenueMonsterSDK
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


val config = Config(
    clientID = "<<<Client ID>>>",
    clientSecret = "<<<Client Secret>>>",
    privateKey = "<<<YOUR PRIVATE KEY>>>",
    publicKey = "<<<YOUR RSA PUBLIC KEY>>>",
    sandbox = true
)


// sdk function is coroutine support, you need to launch using Coroutine package
GlobalScope.launch {
   try {

       val sdk = RevenueMonsterSDK(config)
       val data = QuickPayRequest(
           authCode = "134850717797247290",
           order = Order(
               id = "${Random((1..371289).random()).nextInt()}",
               title = "TEST",
               detail = "JUST A TEST",
               additionalData = "NONE",
               amount = 123,
               currencyType = "MYR"),
           ipAddress = "1.1.1.1",
           terminalId = "1623500916731469951",
           storeId = "1623743430847879711",
       )

       // create quick pay order
       val result = sdk.Payment.quickPay(data)
       println(result)

   } catch (e: Error) {
       println(e.printStackTrace())
   } catch (e: Throwable) {
       println(e.printStackTrace())
   }
}
```

## ⚠️ Disclaimer

> This package is under heavily development, please don't try this in production!

## 📄 License

[MIT](https://github.com/RevenueMonster/rm-kotlin-sdk/blob/main/LICENSE)
