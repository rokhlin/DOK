# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#
#-ignorewarnings
#
## Uncomment this to preserve the line number information for
## debugging stack traces.
##-keepattributes SourceFile,LineNumberTable
#
## If you keep the line number information, uncomment this to
## hide the original source file name.
##-renamesourcefileattribute SourceFile
#
## Retrofit
#-keep class com.google.gson.** { *; }
#-keep class com.google.inject.** { *; }
#-keep class org.apache.http.** { *; }
#-keep class org.apache.james.mime4j.** { *; }
#-keep class javax.inject.** { *; }
#-keep class javax.xml.stream.** { *; }
#-keep class retrofit.** { *; }
#-keep class com.google.appengine.** { *; }
#-keepattributes *Annotation*
#-keepattributes Signature
#-dontwarn com.squareup.okhttp.*
#-dontwarn rx.**
#-dontwarn javax.xml.stream.**
#-dontwarn com.google.appengine.**
#-dontwarn java.nio.file.**
#-dontwarn org.codehaus.**
#
#-dontwarn retrofit2.**
#-dontwarn org.codehaus.mojo.**
#-keep class retrofit2.** { *; }
#-keepattributes Exceptions
#-keepattributes RuntimeVisibleAnnotations
#-keepattributes RuntimeInvisibleAnnotations
#-keepattributes RuntimeVisibleParameterAnnotations
#-keepattributes RuntimeInvisibleParameterAnnotations
#
#-keepattributes EnclosingMethod
#-keepclasseswithmembers class * {
#    @retrofit2.http.* <methods>;
#}
#-keepclasseswithmembers interface * {
#    @retrofit2.* <methods>;
#}
## Platform calls Class.forName on types which do not exist on Android to determine platform.
#-dontnote retrofit2.Platform
## Platform used when running on RoboVM on iOS. Will not be used at runtime.
#-dontnote retrofit2.Platform$IOS$MainThreadExecutor
## Platform used when running on Java 8 VMs. Will not be used at runtime.
#-dontwarn retrofit2.Platform$Java8
## Retain declared checked exceptions for use by a Proxy instance.
#-keepattributes Exceptions
#
## Add any classes the interact with gson
## the following line is for illustration purposes
#-keep class com.example.asheq.zanis_postmans.ListAddressesActivity
#-keep class com.example.asheq.zanis_postmans.ListOrderActivity
#-keep class com.example.asheq.zanis_postmans.LoginActivity
#-keep class com.example.asheq.zanis_postmans.SendReportsActivity
#-keep class com.example.asheq.track.TrackLocationService
#-keep class com.example.asheq.track.TrackLocationApplication
#
## Hide warnings about references to newer platforms in the library
#-dontwarn android.support.v7.**
## don't process support library
#-keep class android.support.v7.** { *; }
#-keep interface android.support.v7.** { *; }
#
#-dontwarn rx.**
#
#-dontwarn okio.**
#
#-dontwarn com.squareup.okhttp.*
#
#-dontwarn retrofit.appengine.UrlFetchClient
#
#
#-keep class retrofit.** { *; }
#
#-keepclasseswithmembers class * {
#
#@retrofit.http.* <methods>; }
#
#
###---------------Begin: proguard configuration for Gson  ----------
## Gson uses generic type information stored in a class file when working with fields. Proguard
## removes such information by default, so configure it to keep all of it.
#-keepattributes Signature
#
## For using GSON @Expose annotation
#-keepattributes *Annotation*
#
## Gson specific classes
#-dontwarn sun.misc.**
##-keep class com.google.gson.stream.** { *; }
#
## Application classes that will be serialized/deserialized over Gson
#-keep class com.google.gson.examples.android.model.** { *; }
#
## Prevent proguard from stripping interface information from TypeAdapterFactory,
## JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
#-keep class * implements com.google.gson.TypeAdapterFactory
#-keep class * implements com.google.gson.JsonSerializer
#-keep class * implements com.google.gson.JsonDeserializer
#
###---------------End: proguard configuration for Gson  ----------