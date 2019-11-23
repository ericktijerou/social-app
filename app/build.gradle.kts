import Dependencies.androidAppCompat
import Dependencies.androidConstraintLayout
import Dependencies.androidCore
import Dependencies.androidCoreTesting
import Dependencies.androidDataBindingCompiler
import Dependencies.androidLiveData
import Dependencies.androidTestEspressoCore
import Dependencies.androidTestExtJunit
import Dependencies.androidTestRules
import Dependencies.androidTestRunner
import Dependencies.androidViewModel
import Dependencies.junit
import Dependencies.koinAndroidScope
import Dependencies.koinAndroidViewModel
import Dependencies.koinTest
import Dependencies.kotlinCoroutinesAndroid
import Dependencies.kotlinCoroutinesCore
import Dependencies.kotlinCoroutinesTest
import Dependencies.kotlinSerializationRuntime
import Dependencies.kotlinStdLib
import Dependencies.leakCanary
import Dependencies.materialDesign
import Dependencies.mockk
import Dependencies.okHttp
import Dependencies.okHttpInterceptor
import Dependencies.okhttpMockWebServer
import Dependencies.okhttpTls
import Dependencies.retrofit
import Dependencies.retrofitGsonConverter
import Dependencies.retrofitKotlixConverter
import Dependencies.retrofitScalarsConverter
import Dependencies.timber

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(29)

    defaultConfig {
        applicationId = "io.github.wellingtoncosta.feed"
        minSdkVersion(16)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isTestCoverageEnabled = true

            buildConfigField(
                    "String",
                    "API_URL",
                    "\"https://randomuser.me/\""
            )
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            isZipAlignEnabled = true

            proguardFile(getDefaultProguardFile("proguard-android.txt"))
            proguardFile(file("proguard-rules.pro"))

            buildConfigField(
                    "String",
                    "API_URL",
                    "\"https://randomuser.me/\""
            )
        }
    }

    dataBinding {
        isEnabled = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        val commonTest = "src/commonTest/java"
        getByName("androidTest").java.srcDirs(commonTest)
        getByName("test").java.srcDirs(commonTest)
    }
}

kapt {
    useBuildCache = true
}

dependencies {
    //Android
    implementation(androidAppCompat)
    implementation(androidConstraintLayout)
    implementation(androidCore)
    kapt(androidDataBindingCompiler)
    implementation(androidLiveData)
    implementation(androidViewModel)
    implementation(androidTestEspressoCore)

    implementation(okHttp)
    implementation(okHttpInterceptor)

    // Retrofit
    implementation(retrofit)
    implementation(retrofitGsonConverter)
    implementation(retrofitScalarsConverter)
    implementation(retrofitKotlixConverter)

    // Google Material Design
    implementation(materialDesign)

    // Koin
    implementation(koinAndroidScope)
    implementation(koinAndroidViewModel)

    // Kotlin
    implementation(kotlinStdLib)
    implementation(kotlinCoroutinesAndroid)
    implementation(kotlinCoroutinesCore)
    implementation(kotlinSerializationRuntime)

    // Leak Canary
    debugImplementation(leakCanary)

    // Tests
    testImplementation(junit)
    testImplementation(mockk)
    testImplementation(okhttpTls)
    testImplementation(androidCoreTesting)
    testImplementation(okhttpMockWebServer)
    testImplementation(kotlinCoroutinesTest)

    androidTestImplementation(koinTest)
    androidTestImplementation(okhttpTls)
    androidTestImplementation(androidTestRules)
    androidTestImplementation(androidTestRunner)
    androidTestImplementation(androidTestExtJunit)
    androidTestImplementation(okhttpMockWebServer)

    // Timber
    implementation(timber)
}
