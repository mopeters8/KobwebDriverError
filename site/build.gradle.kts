import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

val ktor_version: String by project

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    // alias(libs.plugins.kobwebx.markdown)

    //Added
    id("nu.studer.jooq") version "9.0"
    alias(libs.plugins.kotlinx.serialization)
}

group = "mo.kobweb.drivererror"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
        }
    }
}

kotlin {
    configAsKobwebApplication("drivererror", includeServer = true)

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
        }

        jsMain.dependencies {
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
            // implementation(libs.kobwebx.markdown)
            
        }
        jvmMain.dependencies {
            implementation("io.ktor:ktor-client-core:$ktor_version")
            implementation("io.ktor:ktor-client-cio:$ktor_version")
            implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
            compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime

            //TODO figure out why this driver won't load.
            //Figured this driver is only being used on JVM side, so only needed her.
            implementation("org.jooq:jooq:3.19.2")
            implementation("org.postgresql:postgresql:42.1.4")
        }
    }
}
