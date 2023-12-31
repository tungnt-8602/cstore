plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.cstore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cstore"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug{}
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.github.smarteist:autoimageslider:1.4.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
//    implementation ("com.github.parse-community.Parse-SDK-Android:parse:1.18.5")
//    implementation ("org.slf4j:slf4j-nop:2.0.7")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    // Utility
    implementation("com.google.code.gson:gson:2.10.1")

    //Room
    implementation("androidx.room:room-runtime:2.6.0")
    annotationProcessor("androidx.room:room-compiler:2.6.0")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

}