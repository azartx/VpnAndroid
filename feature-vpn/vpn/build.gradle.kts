import com.vpn.vpnandroid.build.Dependencies

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


    implementation(project(":base"))
    implementation(project(":core:coreui"))
    implementation(project(":core:di-android"))
    implementation(project(":design"))
    implementation(project(":navigation"))
    implementation(project(":util"))


    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(Dependencies.AndroidX.navigation)
    implementation(Dependencies.AndroidX.navigationUi)
    implementation(Dependencies.Di.koin)
    implementation(Dependencies.Di.koinAndroid)
    implementation(Dependencies.Lib.glide)
    implementation(Dependencies.Lib.ktStdLib)
    implementation(Dependencies.Lib.timber)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitMoshi)
    implementation(Dependencies.View.constraintLayout)

}
