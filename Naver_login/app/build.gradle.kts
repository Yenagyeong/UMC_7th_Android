plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "dduw.com.mobile.naver_login"
    compileSdk = 35 // 최신 Compile SDK 버전

    defaultConfig {
        applicationId = "dduw.com.mobile.naver_login"
        minSdk = 24
        targetSdk = 34 // 최신 Target SDK 버전
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    viewBinding {
        enable = true
    }

    dataBinding {
        enable = true
    }
}

dependencies {
    // Core dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Naver login
    implementation("com.navercorp.nid:oauth-jdk8:5.3.0") // 최신 JDK 8 지원

    // Kotlin and Coroutines
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10") // 최신 Kotlin 버전
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") // 최신 Coroutines 버전

    // AppCompat and Support libraries
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.legacy:legacy-support-core-utils:1.0.0")
    implementation("androidx.browser:browser:1.5.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0") // 최신 ConstraintLayout
    implementation("androidx.security:security-crypto:1.1.0-alpha03")

    // Networking (Retrofit and OkHttp)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0") // 최신 Moshi 버전
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0") // 최신 OkHttp

    // Animation
    implementation("com.airbnb.android:lottie:6.0.0") // 최신 Lottie
}
