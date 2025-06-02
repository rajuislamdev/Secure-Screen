import 'package:flutter/material.dart';
import 'package:secure_screen/controller/screen_security.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  bool isSecureModeEnabled = false;

  @override
  void dispose() {
    super.dispose();
    // disable secure mode
    ScreenSecurity.disableSecureMode();
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
              if (isSecureModeEnabled) {
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
