# NaKeR's Bedrock Camera Lib is a library for Minecraft Java (Fabric), which replicates Minecraft Bedrock's Script API Camera APIs

## This mod is a *library*. It does not add/do anything on it's own.

This library adds similar camera APIs to the ones in bedrock!
Currently, supports `setCamera` and `fade`.

This library was written in Kotlin, so FLK is a dependency.
The library contains a bunch of easings for you to make transitions!

## Installation (for developers)
Add JitPack to your repositories:
```groovy
maven { url 'https://jitpack.io' }
```
And add Bedrock Camera Lib to your dependencies:
```groovy
modImplementation 'com.github.NaKeRNarolino:BECamera:<LIB_VERSION>-<MINECRAFT_VERSION>r'
```

Examples in both languages(Java and Kotlin).

Java
```java
CameraManager.INSTANCE.setCamera(
    new CameraData(
            new Vec3d(30, 30, 30), // Camera location
            new Vec3d(15, 15, 15), // The position the camera will be looking at
            new EaseOptions(
                    Easings.INSTANCE::easeInOutBack, // Easing function for the transition
                    2500 // duration in ms
            )
        )
); // Setting a custom camera
                
CameraManager.INSTANCE.fade(new CameraFadeOptions(
    Colors.WHITE, // Color (ARGB)
    1500, // Fade in time, ms
    2000, // Hold time, ms
    1500 // Fade out time, ms
)); // Fading the camera
```

Kotlin
```kotlin
CameraManager.setCamera(
	CameraData(
        location = Vec3d(30.0, 30.0, 30.0),
        targetLocation = Vec3d(15.0, 15.0, 15.0),
        easeOptions = EaseOptions(
            easing = Easings::easeInOutBack,
            duration = 2500
        )
    )
) // Setting a custom camera

CameraManager.fade(
    CameraFadeOptions(
        fadeColor = Colors.WHITE,
        fadeInTime = 1500,
        holdTime = 2000,
        fadeOutTime = 1500
    )
) // Fading the camera
```

> You can also use `CameraManager.clear()` or `CameraManager.INSTANCE.clear()` to bring back player's camera state to the default one. Note that this does not affect fades.