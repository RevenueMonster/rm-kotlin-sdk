# RM Kotlin Multiplatform SDK

<p>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/actions?query=workflow%3ATest"><img src="https://github.com/RevenueMonster/rm-kotlin-sdk/workflows/Test/badge.svg?branch=main" alt="test status" title="test status"/></a>
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

### Multiplatform

If you're using `multiplatform`, modify your `build.gradle.kts` file as follow

```bash
plugins {
   kotlin("multiplatform") version "1.5.10"
}

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

### JVM

If you're using `jvm`, modify your `build.gradle.kts` file as follow

```bash
plugins {
   kotlin("jvm") version "1.5.10"
}

kotlin {
   sourceSets {
      val commonMain by getting {
         dependencies {
            implementation("io.revenuemonster:rm-kotlin-sdk-jvm:1.0.0-alpha.0")
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

## 🙈 Example

```kotlin
import io.revenuemonster.sdk.model.request.QuickPayOrder
import io.revenuemonster.sdk.model.request.QuickPayRequest
import kotlin.random.Random
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

...

val sdk = RevenueMonsterSDK(
   clientID = "1623743073701188526",
   clientSecret = "TZqprtCpGAhagCyDTFiqigAfIFjPOKHY",
   // pkcs8 private key, https://decoder.link/rsa_converter
   privateKey = "-----BEGIN PRIVATE KEY-----\n" +
         "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPdfakYApwp0kz\n" +
         "uTTb1EzvmM39HWnlwfrKkmpt+alYkV3h9rT5HKoRHCRewAzRFXYm5vV1srHrXBfc\n" +
         "bFJeZnd+Os6Bppe8iRQK/QPQVoNwAAJn40ipKcHPrcXZBb2fXaaM69Ff5+b4CSDt\n" +
         "DOhdiHKhgq7eLqHI7UXWddObpl05c2vY9j/bzpIiVAacgU6gNmVLv2ijaOGKX4lF\n" +
         "CovLFYRz3a47CBdpsqNo8y/tTwK5j9aTfPhon804Ye+ki5A3xV/0YJFk7R5b/7rv\n" +
         "BXBi73CR4dMdOMlJMZiP/5sjKtg79XPIbzv7fu2Uborqfil4iekK5CMYwMKpiZwI\n" +
         "YLRLcdgNAgMBAAECggEARkzFH0mc9BVVzs3cNDk1tleH4ZgvU5KaBfh3xQqJzMf3\n" +
         "XIqbplNAl3/V5sRvw2mj625ULGfzveT0IGTIfE/0EFxa47eyNYZq1vOjkc1DPXgT\n" +
         "tbrv2U2IFvANusqQYG7w9a99e2TVjwhSJTelCmiZ28GBVYk+aIq1JWG531VH66WC\n" +
         "Fwg27FE/YmY21URLZBeoTc6Hiw0EhItImJVBAJIlIqyuLaGC5zeg0WzT2d0hD6E8\n" +
         "JjAg4MuuJt/DyJWftZFnDEkoc1ZGZFdUl4AulPlvIo3jko+N/XRU8yncETWoe/Rf\n" +
         "S9BvwyReBgfzI1bpfmsH0krzgmK4oE4A3qGVAFt34QKBgQDT7Vc59G/Iii3uT2zT\n" +
         "f0UozaQ4QUEPYrj8+Uv8777z2Ek4uUhUernmo39JiwcsUqWcUqrVDvM5Z7T2FiGq\n" +
         "azWoYp15A/W1erhMYzLWHnfjNjLMSxvOvUM1rayCdovT6Gf+hXWgdbsaZgnI6/zq\n" +
         "TQ9DkdJ9EXC1PXfG5AqhqDRwqQKBgQCtS5X74SrM+hypt/mOdMTI7Z5pHm83Pjwp\n" +
         "RtvvjwTss8Q3cHxPw/pW34GFUbdfjxy0NBy/2jtODaxS65yJjZQvIE/pSb131E/V\n" +
         "i54ByD3GYqJwWZPNosxmssr93bgKmljorWcW5gSbVraArrwyPNgwNCqXrW0HOGDd\n" +
         "/ji5p2e2xQKBgQCPDxhitu7+oMiApn0ACFnXQXopv2L7z9iwbFkvVWfBk69Z6zk4\n" +
         "TeBQIO475u3gram97NAFIyJtDeTKegYf1F9UW72Rg2rKJx1h81TN1aN4bY28+ra6\n" +
         "yGgmRCa/quaZFbmdxtmwh8jV+SuBxkvknkA/jKBabPt4XRYN3MKQfctogQKBgQCc\n" +
         "0lkBHmvoILLNl5J7qwnltrry77q/aE9zHw+rA2b2eVuou9+TI4upEAwaxK9IB693\n" +
         "F+2oZGnkoZT/WP5IsFmYKAqI19Vg2j4Axp8Ph9D3dY0AVN/faGLG0SfHgtyoo7Kv\n" +
         "pTQ0UaV7mYYAf6AJamxNevfMWQHgVdG7MvLMd0Tw7QKBgHuHWOZqU9lj2FtOc9GL\n" +
         "VdIvNkXGkl/X5p8PU9IiKqf/eTYBxaHj+hvAR6BdOGxF3b5+58cGQGNJFZARscYY\n" +
         "iePqjUF49nH0qp7Oj0EuTbPV1kF120IKTgiMKkK6Y3FbafKcsz5IUgRD/N5ctG9X\n" +
         "PQS9CE72OPXdNRogeolCIfdx\n" +
         "-----END PRIVATE KEY-----\n",
   publicKey = "-----BEGIN RSA PUBLIC KEY-----\n" +
         "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5drTGuzwolTC37/yNBKZ\n" +
         "fszzXWrGBNHtGYQg6f6xkOgx7a6Ke12mbORfIVDnigtbQCXeRfMh5mpiH1C0rZxo\n" +
         "iV0FMjlTXV2r4YUle/qgWZbonjsNbNMVRrVzwtHuVdBu7z6Bhk9K4CaNxhjeGpCZ\n" +
         "C9nEPeQ23IzeQRNuZLZ5/VdRECd6H9pgojP6CqI+RkGo0Wat4ifqQTJ59HAjlZFo\n" +
         "CuBCXUwU3ppJAwrDVhzMnH5aaEznROnhLl+eOsNNdtV7KEU7OuuCE1fOoYTfa0ko\n" +
         "2q3RnJo3ZIQO0RHTOF3L/WfZHesgeXFrtIRW9mPsFKJ2xHrcOiXl6F9QoNNdnf9k\n" +
         "TwIDAQAB\n" +
         "-----END RSA PUBLIC KEY-----\n",
)

// sdk function is coroutine support, you need to launch using Coroutine package
GlobalScope.launch {
   try {
      val credential = sdk.getAccessToken()
      println(credential)

      // quick pay test
      val order = QuickPayOrder(
         id = "${Random.nextInt(10946114768247530.toInt(), 90946114768247530.toInt())}",
         title = "SNOR TEST",
         detail = "JUST A TEST",
         additionalData = "NONE",
         amount = 85700,
         currencyType = "MYR"
      )
      val qp = QuickPayRequest(
         authCode = "134850717797247290",
         order = order,
         ipAddress = "1.1.1.1",
         terminalId = "1623500916731469951",
         storeId = "1623743430847879711",
      )

      // find your desire function in `sdk`
      val result = sdk.payment.quickPay(qp)
      println(result)
   } catch(e: Throwable) {
      println(e)
   }
}
```

## ⚠️ Disclaimer

> This package is under heavily development, please don't try this in production!

## 📄 License

MIT
