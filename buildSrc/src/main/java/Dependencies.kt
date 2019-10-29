import sun.misc.Version

object Versions {
    val compileSdkVersion = 29
    val buildToolsVersion = "29.0.2"
    val minSdkVersion = 19
    val targetSdkVersion = 29
    val versionCode = 1
    val versionName = "1.0"
    val junit = "4.12"
    val ext_junit = "1.1.1"

    val appcompat = "1.0.0"
    val kotlin_version = "1.3.50"
    val core_ktx = "1.1.0"
    val constraintlayout = "1.1.3"
    val espresso_core = "3.2.0"
    val dagger = "2.23.2"
    val lifecycle = "2.1.0"
    val navigation = "2.1.0"
    val retrofit = "2.6.2"
    val legacy_support_v4 = "1.0.0"
}


object Dependencies {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val dagger_android_processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    val ext_junit = "androidx.test.ext:junit:${Versions.ext_junit}"
    val junit = "junit:junit:${Versions.junit}"
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
    val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val legacy_support_v4 = "androidx.legacy:legacy-support-v4:${Versions.legacy_support_v4}"

}
