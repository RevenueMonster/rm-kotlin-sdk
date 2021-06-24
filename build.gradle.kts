plugins {
    kotlin("multiplatform") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.10"
    id("org.jetbrains.dokka") version "1.4.32"
    id("maven-publish")
    id("signing")
}

group = "io.revenuemonster"
version = "1.0.0-alpha.5"

val artifact = "rm-kotlin-sdk"
val url = "https://github.com/RevenueMonster/rm-kotlin-sdk"
val gitUrl = "github.com:RevenueMonster/rm-kotlin-sdk.git"
val ktorVersion = "1.6.0"
val serializationVersion = "1.2.1"

repositories {
    mavenCentral()
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
//            dependsOn(commonMain)
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
//        val nativeMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-ios:$ktorVersion")
//                implementation("io.ktor:ktor-client-curl:$ktorVersion")
//            }
//        }
//        val nativeTest by getting {
//        }
//        val iosMain by creating {
//            dependsOn(commonMain)
//        }
    }
}

val dokkaOutputDir = "$buildDir/dokka"

tasks.dokkaHtml {
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

publishing {
    repositories {
        maven {
            name = "SonatypeOSS"
            setUrl { "https://s01.oss.sonatype.org/content/groups/staging/" }
            credentials {
                username = System.getenv("SONATYPE_USERNAME")
                password = System.getenv("SONATYPE_PASSWORD")
            }
        }
//        maven {
//            name = "Snapshot"
//            setUrl { "https://s01.oss.sonatype.org/content/repositories/snapshots/" }
//            credentials {
//                username = System.getenv("SONATYPE_USERNAME")
//                password = System.getenv("SONATYPE_PASSWORD")
//            }
//        }
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/RevenueMonster/rm-kotlin-sdk")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        withType<MavenPublication> {
            artifact(javadocJar)
            version = "$version"
            groupId = "$group"
            artifactId = "$artifact"
            pom {
                name.set(artifact)
                description.set("Revenue Monster Kotlin Multiplatform SDK")
                url.set("$url")

                licenses {
                    license {
                        name.set("MIT license")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                issueManagement {
                    system.set("GitHub Issues")
                    url.set("$url/issues")
                }

                developers {
                    developer {
                        id.set("si3nloong")
                        name.set("Lee Sian Loong")
                        email.set("sianloong90@gmail.com")
                    }
                    developer {
                        id.set("SnorSnor9998")
                        name.set("SnorSnor")
                        email.set("snorsnor9998@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://$gitUrl")
                    developerConnection.set("scm:git:ssh://$gitUrl")
                    url.set("$url")
                }
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(
        System.getenv("GPG_PRIVATE_KEY"),
        System.getenv("GPG_PRIVATE_PASSWORD")
    )
    sign(publishing.publications)
}
