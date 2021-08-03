plugins {
    kotlin("multiplatform") version "1.5.21"
    kotlin("plugin.serialization") version "1.5.21"
    id("com.android.library")
    id("maven-publish")
    id("signing")
}

apply(plugin = "maven-publish")
apply(plugin = "com.android.library")

group = "io.revenuemonster.sdk"
version = "1.0.0-beta.1"

val artifact = "rm-kotlin-sdk"
val pkgUrl = "https://github.com/RevenueMonster/rm-kotlin-sdk"
val gitUrl = "github.com:RevenueMonster/rm-kotlin-sdk.git"
val ktorVersion = "1.6.2"
val serializationVersion = "1.2.2"

repositories {
    google()
    mavenCentral()
}


android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.3"
    defaultConfig{
        minSdkVersion(22)
        targetSdkVersion(30)
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}


kotlin {
    // setup for android
    android {
        publishLibraryVariants("debug")
    }
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

//    val hostOs = System.getProperty("os.name")
//    val isMingwX64 = hostOs.startsWith("Windows")
//    val nativeTarget = when {
//        hostOs == "Mac OS X" -> macosX64("native")
//        hostOs == "Linux" -> linuxX64("native")
//        isMingwX64 -> mingwX64("native")
//        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
//    }

    sourceSets {
        // Dependencies for common
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.1")
                implementation("org.apache.commons:commons-collections4:4.4")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-apache:$ktorVersion")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
//        val jsMain by getting {}
//        val jsTest by getting {
//            dependencies {
//                implementation(kotlin("test-js"))
//            }
//        }
        // Dependencies for iOS and desktop
//        val nativeMain by getting {
//            dependencies {
//            }
//        }
//        val nativeTest by getting {}
//        val iosMain by creating {
//            dependsOn(commonMain)
//        }
    }

    configure(listOf(targets["metadata"], android())) {
        mavenPublication {
            val targetPublication = this@mavenPublication
            tasks.withType<AbstractPublishToMaven>()
                .matching { it.publication == targetPublication }
        }
    }

}

publishing {
//    repositories {
//        maven {
//            name = "Oss"
//            setUrl {
//                val repositoryId =
//                    System.getenv("SONATYPE_REPOSITORY_ID") ?: error("Missing env variable: SONATYPE_REPOSITORY_ID")
//                "https://oss.sonatype.org/service/local/staging/deployByRepositoryId/${repositoryId}/"
//            }
//            credentials {
//                username = System.getenv("SONATYPE_USERNAME")
//                password = System.getenv("SONATYPE_PASSWORD")
//            }
//        }
//        maven {
//            name = "Snapshot"
//            setUrl { "https://oss.sonatype.org/content/repositories/snapshots/" }
//            credentials {
//                username = System.getenv("SONATYPE_USERNAME")
//                password = System.getenv("SONATYPE_PASSWORD")
//            }
//        }
//    }

    publications {
        create<MavenPublication>("maven") {
            groupId = "$group"
            artifactId = artifact
            version = version
            pom {
                name.set(artifact)
                description.set("Revenue Monster Kotlin Multiplatform SDK")
                url.set(pkgUrl)

                licenses {
                    license {
                        name.set("MIT license")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                issueManagement {
                    system.set("GitHub Issues")
                    url.set("$pkgUrl/issues")
                }

                developers {
                    developer {
                        id.set("si3nloong")
                        name.set("Lee Sian Loong")
                        email.set("sianloong90@gmail.com")
                    }
                    developer {
                        id.set("SnorSnor9998")
                        name.set("Snor")
                        email.set("snorsnor9998@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://$gitUrl")
                    developerConnection.set("scm:git:ssh://$gitUrl")
                    url.set(pkgUrl)
                }
            }
            //            from(components["java"])
        }
    }
}

// signing {
//    useInMemoryPgpKeys(
//        System.getenv("GPG_PRIVATE_KEY"),
//        System.getenv("GPG_PRIVATE_PASSWORD")
//    )
//    sign(publishing.publications)
// }
