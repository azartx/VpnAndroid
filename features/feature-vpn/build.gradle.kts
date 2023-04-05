import com.vpn.vpnandroid.build.Dependencies.AndroidX.appCompat
import com.vpn.vpnandroid.build.Dependencies.AndroidX.coreKtx
import com.vpn.vpnandroid.build.Dependencies.Coroutines.coroutinesAndroid
import com.vpn.vpnandroid.build.Dependencies.Di.koin
import com.vpn.vpnandroid.build.Dependencies.Di.koinAndroid
import com.vpn.vpnandroid.build.Dependencies.Lib.kotlinReflect
import com.vpn.vpnandroid.build.Dependencies.Parser.gson
import com.vpn.vpnandroid.build.Dependencies.Test.junit

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.vpn.features.vpn"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        all {
            isMinifyEnabled = false
            isShrinkResources = false
            ndk.abiFilters.addAll(listOf("x86", "x86_64", "armeabi-v7a", "arm64-v8a"))
        }
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("libs")
            java.srcDirs("src/main/kotlin")
        }
    }

    splits {
        abi {
            isEnable = true
            reset()
            include("x86", "x86_64", "armeabi-v7a", "arm64-v8a") //select ABIs to build APKs for
            isUniversalApk = true //generate an additional APK that contains all the ABIs
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar", "*.jar"))))

    implementation(coreKtx)
    implementation(appCompat)

    implementation(coroutinesAndroid)
    
    implementation(gson)

    implementation(koin)
    implementation(koinAndroid)

    implementation(kotlinReflect)

    testImplementation(junit)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}