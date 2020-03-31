import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinNativeCompile
import java.net.URI

plugins {
    kotlin("multiplatform") version "1.3.71"
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
    jcenter()
}

val hostOs = System.getProperty("os.name")
val isWindows = hostOs.startsWith("Windows")

val packageName = "kotlinx.interop.wasm.dom"
val jsinteropKlibFile = buildDir.resolve("klib").resolve("$packageName-jsinterop.klib")

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */

    wasm32 {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    js {
        browser {}
    }

    jvm ()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }

        val wasm32Main by getting {
            dependencies {
                implementation(files(jsinteropKlibFile))
            }
        }
        val wasm32Test by getting {
            dependencies {}
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}

val jsinterop by tasks.creating(Exec::class) {
    workingDir = projectDir

    val ext = if (isWindows) ".bat" else ""
    val distributionPath = project.properties["kotlin.native.home"] as String?

    if (distributionPath != null) {
        val jsinteropCommand = file(distributionPath).resolve("bin").resolve("jsinterop$ext")

        inputs.property("jsinteropCommand", jsinteropCommand)
        inputs.property("jsinteropPackageName", packageName)
        outputs.file(jsinteropKlibFile)

        commandLine(
            jsinteropCommand,
            "-pkg", packageName,
            "-o", jsinteropKlibFile,
            "-target", "wasm32"
        )
    } else {
        doFirst {
            // Abort build execution if the distribution path isn't specified.
            throw GradleException(
                """
                    |
                    |Kotlin/Native distribution path must be specified to build the JavaScript interop.
                    |Use 'kotlin.native.home' project property to specify it.
                """.trimMargin()
            )
        }
    }
}

tasks.withType(AbstractKotlinNativeCompile::class).all {
    dependsOn(jsinterop)
}

val assemble by tasks.getting