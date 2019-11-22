buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Dependencies.androiGradlePlugin)
        classpath(Dependencies.kotlinGradlePlugin)
        classpath(Dependencies.kotlinSerializationGradlePugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}