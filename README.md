# 📵 Flutter Secure Screen

Prevent screenshots and screen recordings on your Flutter app (Android only) using native Android `FLAG_SECURE` with a simple Flutter integration.

## 🚀 Features

- 📱 Toggle secure screen mode at runtime
- 🛡️ Prevent screenshots and screen recordings
- ✅ Easy integration using `MethodChannel`

---

## 📂 Project Structure

lib/
├── controller/
│ └── screen_security.dart # Dart bridge for secure screen control
└── home_screen.dart # Demo usage with a toggle switch

android/
└── app/
└── src/
└── main/
└── kotlin/
└── com/example/secure_screen/
└── MainActivity.kt # Native Android implementation


---

## 🛠️ How to Use

### 1. Update `MainActivity.kt`

```kotlin
package com.example.secure_screen

import android.view.WindowManager
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    private val CHANNEL = "screen_security"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            call, result ->
            when (call.method) {
                "enableSecureMode" -> {
                    window.setFlags(
                        WindowManager.LayoutParams.FLAG_SECURE,
                        WindowManager.LayoutParams.FLAG_SECURE
                    )
                    result.success(null)
                }
                "disableSecureMode" -> {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
                    result.success(null)
                }
                else -> result.notImplemented()
            }
        }
    }
}

```

### 2. Create Dart Bridge

```
// lib/controller/screen_security.dart

import 'package:flutter/services.dart';

class ScreenSecurity {
  static const _channel = MethodChannel('screen_security');

  static Future<void> enableSecureMode() async {
    await _channel.invokeMethod('enableSecureMode');
  }

  static Future<void> disableSecureMode() async {
    await _channel.invokeMethod('disableSecureMode');
  }
} 
```
### 3. Use It in Your UI

``` 
// lib/home_screen.dart

import 'package:flutter/material.dart';
import 'controller/screen_security.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  bool isSecureModeEnabled = false;

  @override
  void dispose() {
    ScreenSecurity.disableSecureMode(); // Clean up
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Home Screen')),
      body: Center(
        child: SwitchListTile(
          value: isSecureModeEnabled,
          onChanged: (v) {
            setState(() {
              isSecureModeEnabled = v;
              if (v) {
                ScreenSecurity.enableSecureMode();
              } else {
                ScreenSecurity.disableSecureMode();
              }
            });
          },
          title: Text('Secure Mode'),
        ),
      ),
    );
  }
}

```
📱 Platform Support
✅ Android

🚫 iOS: Not supported (iOS does not allow programmatic control of screenshot behavior like Android's FLAG_SECURE)

✅ Best Practices
Use secure mode for sensitive content like PDFs, videos, chats, etc.

Always clean up (disableSecureMode) when the screen is disposed or not in use.

🙌 Credits
Built with ❤️ using Flutter and Android native integration.
