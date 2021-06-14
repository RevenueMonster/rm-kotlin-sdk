plugins {
    kotlin("multiplatform") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.10"
    id("maven-publish")
}

group = "org.rm.sdk"
version = "1.0.0-alpha.0"

repositories {
    mavenCentral()
    mavenLocal()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.rm.sdk"
            artifactId = "library"
            version = "$version"
            pom {
                name.set("rm-kotlin-sdk")
                description.set("Revenue Monster Kotlin Multiplatform SDK")
                url.set("https://github.com/RevenueMonster/rm-kotlin-sdk")
                developers {
                    developer {
                        id.set("si3nloong")
                        name.set("Lee Sian Loong")
                        email.set("sianloong90@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com:RevenueMonster/rm-kotlin-sdk.git")
                    developerConnection.set("scm:git:ssh://github.com:RevenueMonster/rm-kotlin-sdk.git")
                    url.set("https://github.com/RevenueMonster/rm-kotlin-sdk")
                }
            }
//            from(components["java"])
        }
    }
}

kotlin {
    // setup for JVM
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
//    js(LEGACY) {
//        browser {
//            commonWebpackConfig {
//                cssSupport.enabled = true
//            }
//        }
//    }
//    ios()

    val hostOs = System.getProperty("os.name")
//    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
//        hostOs == "Linux" -> linuxX64("native")
//        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    val ktorVersion = "1.6.0"
    val serializationVersion = "1.2.1"

    sourceSets {
        // Dependencies for common
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        // Dependencies for JVM, android mobile phone and android based application (such as tablet)
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("io.ktor:ktor-client-apache:$ktorVersion")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
//        val jsMain by getting {
//        }
//        val jsTest by getting {
//            dependencies {
//                implementation(kotlin("test-js"))
//            }
//        }
        // Dependencies for iOS and desktop
        val nativeMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
                implementation("io.ktor:ktor-client-curl:$ktorVersion")
            }
        }
        val nativeTest by getting {
        }
//        val iosMain by creating {
//            dependsOn(commonMain)
//        }
    }
}
