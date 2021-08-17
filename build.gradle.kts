plugins {
    kotlin("multiplatform") version "1.5.21"
    kotlin("plugin.serialization") version "1.5.21"
    id("org.jetbrains.dokka") version "1.5.0"
    id("com.android.library")
    id("maven-publish")
    id("signing")
}

apply(plugin = "maven-publish")
apply(plugin = "com.android.library")
apply(plugin = "org.jetbrains.dokka")

group = "io.revenuemonster.sdk"
version = System.getenv("RM_KOTLIN_SDK_VERSION") ?: "1.0.0-beta.6"

val artifact = "rm-kotlin-sdk"
val pkgUrl = "https://github.com/RevenueMonster/rm-kotlin-sdk"
val gitUrl = "github.com:RevenueMonster/rm-kotlin-sdk.git"
val ktorVersion = "1.6.2"

repositories {
    google()
    mavenCentral()
}

val dokkaOutputDir = "$buildDir/dokka"

tasks.getByName<org.jetbrains.dokka.gradle.DokkaTask>("dokkaHtml") {
    outputDirectory.set(file(dokkaOutputDir))
}

val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") {
    delete(dokkaOutputDir)
}

val javadocJar = tasks.register<Jar>("javadocJar") {
    dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaOutputDir)
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.3"
    defaultConfig {
        minSdkVersion(22)
        targetSdkVersion(30)
        multiDexEnabled = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile("src\\androidMain\\AndroidManifest.xml")
            java.srcDirs("src\\androidMain\\kotlin")
            res.srcDirs("src\\androidMain\\res")
        }
    }
}

kotlin {
    // setup for android
    android {
        publishAllLibraryVariants()
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
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
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
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
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
//         Dependencies for iOS and desktop
//        val nativeMain by getting {
//            dependencies {
//            }
//        }
//        val nativeTest by getting {}
//        val iosMain by creating {
//            dependsOn(commonMain)
//        }

        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.Experimental")
            }
        }

        targets.all {
            compilations.all {
                kotlinOptions {
                    freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
                }
            }
        }
    }
}

publishing {
//    repositories {
//        maven {
//            name = "Oss"
//            setUrl {
//                "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
//            }
//            credentials {
//                username = System.getenv("SONATYPE_USERNAME")
//                password = System.getenv("SONATYPE_PASSWORD")
//            }
//        }
//        maven {
//            name = "Snapshot"
//            setUrl { "https://s01.oss.sonatype.org/content/repositories/snapshots/" }
//            credentials {
//                username = System.getenv("SONATYPE_USERNAME")
//                password = System.getenv("SONATYPE_PASSWORD")
//            }
//        }
//    }

    publications {
//        create<MavenPublication>("maven") {
        withType<MavenPublication> {
            groupId = "$group"
            artifactId = artifact
            version = version
            artifact(javadocJar)
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
