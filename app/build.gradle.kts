import java.util.Properties
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}


val secretPropertiesFile: File = rootProject.file("secrets.properties")
val sitProps = Properties()
secretPropertiesFile.inputStream().use { input ->
    sitProps.load(input)
}

android {
    namespace = "com.clean.mvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.clean.mvvm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        testInstrumentationRunner = "androidx.compose.ui.test.junit4.AndroidComposeTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "prefPassword", sitProps.getProperty("prefPassword"))
        buildConfigField("String", "prefName", sitProps.getProperty("prefName"))
        buildConfigField("String", "API_KEY", sitProps.getProperty("API_KEY"))
        buildConfigField("String", "baseUrl", sitProps.getProperty("baseUrl"))
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"

    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.android)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.google.android.material)
    implementation (libs.androidx.compose.material)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.room.ktx)
    //test
    androidTestImplementation(libs.androidx.ui.test.junit4)
// Needed for createComposeRule, but not createAndroidComposeRule:
    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.ui.test.junit4.android)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    //For runBlockingTest, CoroutineDispatcher etc.
    testImplementation(libs.kotlinx.coroutines.test)
    //For InstantTaskExecutorRule
    testImplementation(libs.androidx.core.testing)
    // Core library
    androidTestImplementation (libs.androidx.core)
    implementation(libs.lottie.compose)
    implementation(libs.shimmer.compose)
// AndroidJUnitRunner and JUnit Rules
    androidTestImplementation (libs.androidx.test.runner)
    androidTestImplementation (libs.androidx.test.rules)
    androidTestUtil (libs.androidx.test.orchestrator)

// Assertions
    androidTestImplementation (libs.androidx.test.ext.junit)
    androidTestImplementation (libs.androidx.test.truth)
    androidTestImplementation (libs.google.test.truth)

// Espresso dependencies
    androidTestImplementation (libs.test.espresso.core)
    androidTestImplementation (libs.test.espresso.contrib)
    androidTestImplementation (libs.test.espresso.intents)
    androidTestImplementation (libs.test.espresso.accessibility)
    androidTestImplementation (libs.test.idling.concurrent)

    implementation(libs.mockito.kotlin)
    implementation (libs.androidx.ui)
    implementation (libs.androidx.ui.test)
    //Koin
    implementation(libs.koin.android)
    implementation(libs.koin.core)
    testImplementation(libs.koin.test)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compat)
    testImplementation(libs.koin.test.junit4)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.converter.scalars)
    implementation(libs.encrypted.preferences)
    //Glide
    implementation(libs.landscapist.glide)

    implementation(libs.androidx.navigation.compose)
    //room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)
    ksp(libs.androidx.room.compiler)
}