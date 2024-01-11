plugins {
  id("com.android.application")
  /*id("org.jetbrains.kotlin.android")*/
  /*kotlin("android") version "1.9.10" //1.5.31*/
  id("androidx.navigation.safeargs") version "2.5.3" apply true
}

buildscript {
  repositories {
    google()
  }
  dependencies {
    val nav_version = "2.5.3"
    classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
  }
}

android {
  namespace = "com.dpaulos6.localelens"
  compileSdk = 34



  defaultConfig {
    applicationId = "com.dpaulos6.localelens"
    minSdk = 33
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  /*kotlinOptions {
    jvmTarget = "1.8"
  }*/

  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("com.github.bumptech.glide:glide:4.15.1")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.10.0")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
  implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
  implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
  implementation("com.google.firebase:firebase-auth:22.3.0")
  implementation("androidx.camera:camera-camera2:1.3.0")
  implementation("androidx.camera:camera-view:1.3.0")
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
  implementation("androidx.activity:activity-compose:1.8.1")
  implementation(platform("androidx.compose:compose-bom:2023.09.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3")
  implementation("androidx.compose.material:material-icons-extended:1.5.4")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")

  val cameraXVersion = "1.3.0"
  implementation("androidx.camera:camera-core:$cameraXVersion")
  implementation("androidx.camera:camera-camera2:$cameraXVersion")
  implementation("androidx.camera:camera-lifecycle:$cameraXVersion")
  implementation("androidx.camera:camera-video:$cameraXVersion")

  implementation("androidx.camera:camera-view:$cameraXVersion")
  implementation("androidx.camera:camera-extensions:$cameraXVersion")

  implementation("org.tensorflow:tensorflow-lite-task-vision-play-services:0.4.2")
  implementation("com.google.android.gms:play-services-tflite-gpu:16.2.0")

  testImplementation("junit:junit:4.13.2")
  testImplementation("androidx.test:rules:1.4.0")
  testImplementation("androidx.test:runner:1.4.0")
  testImplementation("androidx.test.espresso:espresso-core:3.4.0")
  testImplementation("org.robolectric:robolectric:4.4")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test:core:1.4.0")
  androidTestImplementation("androidx.test:rules:1.4.0")
  androidTestImplementation("androidx.test:runner:1.4.0")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

  implementation("org.tensorflow:tensorflow-lite-task-vision:0.4.0")
  // Import the GPU delegate plugin Library for GPU inference
  implementation("org.tensorflow:tensorflow-lite-gpu-delegate-plugin:0.4.0")
  implementation("org.tensorflow:tensorflow-lite-gpu:2.9.0")
}
