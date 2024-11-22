-repackageclasses
-ignorewarnings
-dontnote
-dontwarn
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-dontoptimize
-dontpreverify

# Keep filenames and line numbers for stack traces
#-keepattributes SourceFile,LineNumberTable

-dontwarn com.google.ar.core.**

# Code obfuscation
-keepclassmembers class com.tekidoer.sockshttp** { <fields>; }

-keep class com.trilead.ssh2.** { *; }
-keep class com.tekidoer.sockshttp.preference.** { *; }
-keep class com.tekidoer.sockshttp.SocksHttpMainActivity.** { *; }
-keep class com.tekidoer.sockshttp.SocksHttpApp.** { *; }
-keep class net.i2p.crypto.** { *; }
-keep class org.conscrypt.** { *; }