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
