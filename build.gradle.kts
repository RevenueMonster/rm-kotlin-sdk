plugins {
    kotlin("multiplatform") version "1.7.20"
    kotlin("plugin.serialization") version "2.0.0"
    id("org.jetbrains.dokka") version "1.7.20"
    id("com.android.library")
    id("maven-publish")
    id("signing")
}

// apply(plugin = "maven-publish")
// apply(plugin = "com.android.library")
// apply(plugin = "org.jetbrains.dokka")

group = "io.revenuemonster.sdk"
// remove prefix v if the version included, eg `v1.0.0`
//version = "(System.getenv("RM_KOTLIN_SDK_VERSION") ?: "1.0.0").removePrefix("v")"
version = "2.2.0"

val artifact = "rm-kotlin-sdk"
val pkgUrl = "https://github.com/RevenueMonster/rm-kotlin-sdk"
val gitUrl = "github.com:RevenueMonster/rm-kotlin-sdk.git"
val ktorVersion = "2.1.2"

repositories {
    google()
    mavenCentral()
}

val dokkaOutputDir = "$buildDir/dokka"

tasks.dokkaHtml {
    outputDirectory.set(file(dokkaOutputDir))
}

val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
//    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    from(tasks.dokkaHtml)
}

// val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") {
//    delete(dokkaOutputDir)
// }

// val javadocJar = tasks.register<Jar>("javadocJar") {
//    dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
//    archiveClassifier.set("javadoc")
//    from(dokkaOutputDir)
// }

android {
    compileSdk = 32
    defaultConfig {
        minSdk = 22
        targetSdk = 32
        multiDexEnabled = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
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
        publishLibraryVariants("release")
    }
    // setup for JVM
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }

    sourceSets {
        // Dependencies for common
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("org.apache.commons:commons-collections4:4.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }
        val jvmTest by getting {}
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }
        val androidTest by getting {}

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
    repositories {
        maven {
            name = "Oss"
            setUrl {
                "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            }
            credentials {
                username = System.getenv("SONATYPE_USERNAME")
                password = System.getenv("SONATYPE_PASSWORD")
            }
        }
        maven {
            name = "Snapshot"
            setUrl { "https://s01.oss.sonatype.org/content/repositories/snapshots/" }
            credentials {
                username = System.getenv("SONATYPE_USERNAME")
                password = System.getenv("SONATYPE_PASSWORD")
            }
        }
    }

    publications {
        publications.configureEach {
            if (this is MavenPublication) {
                artifact(dokkaJar)
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
//        create<MavenPublication>("maven") {
//        withType<MavenPublication> {
//            groupId = "$group"
//            artifactId = artifact
//            version = version
//            artifact(dokkaJar)
//        }
    }
}

if (System.getenv("GPG_PRIVATE_KEY") != null && System.getenv("GPG_PRIVATE_PASSWORD") != null) {
    signing {
        useInMemoryPgpKeys(
            System.getenv("GPG_PRIVATE_KEY"),
            System.getenv("GPG_PRIVATE_PASSWORD")
        )
        sign(publishing.publications)
    }
}
