# RM Kotlin Multiplatform SDK

<p>
   <a href="https://search.maven.org/search?q=g:io.revenuemonster.sdk"><img src="https://img.shields.io/maven-central/v/io.revenuemonster.sdk/rm-kotlin-sdk" alt="maven central" title="maven central"/></a>
   <a href="https://github.com/RevenueMonster/rm-kotlin-sdk/actions?query=workflow%3ATest"><img src="https://github.com/RevenueMonster/rm-kotlin-sdk/workflows/Test/badge.svg?branch=main" alt="test status" title="test status"/></a>
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

> Revenue Monster Kotlin Multiplatform SDK, support every possible platform, such as desktop and mobile.


## 🔨 Installation with Gradle

### Maven Central

```bash
repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("io.revenuemonster.sdk:rm-kotlin-sdk:2.2.0")
}
```
⚠ Don't forget to implement ⚠</br>
[Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) </br>
[Kotlinx Datetime](https://github.com/Kotlin/kotlinx-datetime) </br>

For more detail please visit our [wiki](https://github.com/RevenueMonster/rm-kotlin-sdk/wiki)

## 🪣 Requirements

- [Kotlin](https://github.com/JetBrains/kotlin) compiler at least 1.5.10
- [JDK](https://www.oracle.com/java/technologies/javase-downloads.html) at least 1.8

## ✨ Features

- Minimal package dependency
- Support multiple platform

## 📦️ Dependencies

| Package name                                                             | Version |
| ------------------------------------------------------------------------ |---------|
| [ktor](https://github.com/ktorio/ktor)                                   | 2.1.2   |
| [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) | 1.7.20  |
| [kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime)           | 0.4.0   |

## 🤖 Supported Platforms

> The idea is to support every possible platforms without pain

| Platform                | Architecture | Technology | Package name          | Support |
| ----------------------- | ------------ | --------- | --------------------- | :-----: |
| ![badge][badge-mac]     | macOS        | JVM       | rm-kotlin-sdk-jvm     |   ✅    |
| ![badge][badge-linux]   | linux        | JVM       | rm-kotlin-sdk-jvm     |   ✅    |
| ![badge][badge-windows] | window       | JVM       | rm-kotlin-sdk-jvm     |   ✅    |
| ![badge][badge-android] | android      | Android   | rm-kotlin-sdk-android |   ✅    |


## 🙈 Example

[How to get your credential](https://github.com/RevenueMonster/rm-kotlin-sdk/wiki/How-to-get-your-credential)

```kotlin
import io.revenuemonster.sdk.RevenueMonsterAuth
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.auth.Config
import io.revenuemonster.sdk.util.RMException

val config = Config(
    clientID = "<<<Client ID>>>",
    clientSecret = "<<<Client Secret>>>",
    privateKey = "<<<YOUR PRIVATE KEY>>>",
    sandbox = true
)

GlobalScope.launch {
    try {
        val auth = RevenueMonsterAuth(config).getAccessToken()
        val sdk = RevenueMonsterSDK(auth)

        val result = sdk.payment.getTransactions()
        println(result)
    }catch (e: RMException){
        println(e.message)
        println(e.errorCode)
        println(e.errorMessage)
    }catch (e: Throwable){
        e.printStackTrace()
    }
}
```

## Custom Request (Sample)

If there's some new Feature / Open API, is not updated only we suggest to use this method, we handled most of the data processing & security check part. What you need to do only pass the request parameters and url.

### Get
```kotlin
val sdk = RevenueMonsterSDK(auth)
val response = sdk.custom(url = "/v3/store/1692604052727021311", HttpMethod.Get)
val json = Json {
   ignoreUnknownKeys = true
}
val store = json.decodeFromString<Item<Store>>(response.bodyAsText())

```

### Post

```kotlin
@Serializable
data class LoyaltyPointRequest(
    val point: Int,
    val type: String,
    val countryCode: String,
    val phoneNumber: String
)

@Serializable
data class RMResponse(
    val code: String
)
```


```kotlin

val data = LoyaltyPointRequest(
   point = 100,
   type = "PHONENUMBER",
   countryCode = "60",
   phoneNumber = "123456789"
)

try{

   val jsonElement = Json.encodeToJsonElement(data)
   val sdk = RevenueMonsterSDK(auth)
   val response = sdk.custom("/v3/loyalty/reward", HttpMethod.Post, jsonElement)
   val result = Json.decodeFromString<RMResponse>(response.bodyAsText())

}catch (e: RMException){
   println(e.message)
   println(e.errorCode)
   println(e.errorMessage)
}catch (e: Throwable){
   e.printStackTrace()
}



```

## Version 2.1.1 onward

Starting from version 2.1.1 onward, users will have the ability to manually set the timeout or socket timeout. </br>
By default, the request timeout will be set to 95 seconds (95000L), and the socket timeout will be set to 60 seconds (60000L).

```kotlin
sdk.setTimeout(
   timeout = 120000L,
   socketTimeout = 120000L
)
```

## 📄 License

[MIT](https://github.com/RevenueMonster/rm-kotlin-sdk/blob/main/LICENSE)
