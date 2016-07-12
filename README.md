#Discount Ascii Warehouse

This is a sample Android client to Discount Ascii Warehouse service(http://74.50.59.155:5000).

The app is written in Kotlin using MVVM pattern with DataBinding library from Google.

In the app you can find solutions how to properly configure and use Dagger2 for dependency injection. You can check how to add per Activity scope.

Additionally you can learn how to use dagger2 components in unit testing. (In this app the example includes integration test but the way of usage is the same).

The app also contains NdJsonConverterFactory. You can check how to use it with Retrofit2 and handle NDJSON responses and how to send NDJSON requests.

Continuing about REST queries, in the app you can learn how to add requests based cache to OkHttp3.
You can also learn how to add custom bindings using Google's Databinding framework and how to implement MVVM pattern using this library.

There is also a sample of gridded RecyclerView with item offset decoration and code which explains how to add pagination.

The last but not least is the SearchView in toolbar containing custom suggestions without ContentProvider and voice input mode.

And obviously all that things are fully written in Kotlin!

#tl;dr

The app contains material design components and main android libraries:
- Dagger2 - per Activity scope and dagger2 in test project
- Retrofit2 - NdJsonConverterFactory to handle NDJSON requests and responses
- OkHttp3 - requests based cache
- Google's Databinding - sample of usage with ViewModels and how to add custom bindings
- RxKotlin - just reactive programming in Kotlin
- RecyclerView - grid with item offset decoration and pagination
- SearchView - in toolbar with custom suggestions without ContentProvider and voice input mode

#Getting started

You need:
- Android Studio (I'm using version 2.1.2)
- Android SDK (Android SDK Build-tools 24, SDK Platform 24 and support libraries)
- JDK 1.8
- Kotlin plugin for Android Studio
