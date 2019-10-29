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

    /*
    implementation 'androidx.appcompat:appcompat:1.1.0'
implementation 'androidx.core:core-ktx:1.1.0'
    * */
}


object Dependencies {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
    val junit = "junit:junit:${Versions.junit}"
    val ext_junit = "androidx.test.ext:junit:${Versions.ext_junit}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
}
