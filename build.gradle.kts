// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version "7.3.1" apply false
    id ("com.android.library") version "7.3.1" apply false
    id ("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("com.google.dagger.hilt.android") version "2.48" apply false

}
buildscript{
    dependencies {
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.3")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}
