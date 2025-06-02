package com.example.secure_screen
import android.view.WindowManager
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

// MainActivity is the main entry point for the app, it extends FlutterActivity
class MainActivity : FlutterActivity(){
    // CHANNEL is the name of the channel to communicate with the Dart side
    private val CHANNEL = "screen_security"

    // configureFlutterEngine is a method that is called when the Flutter engine is created
    // it sets up the channel to communicate with the Dart side
    override  fun configureFlutterEngine(flutterEngine: FlutterEngine){
        // super.configureFlutterEngine is called to set up the Flutter engine
        super.configureFlutterEngine(flutterEngine)

        // MethodChannel is used to set up the channel to communicate with the Dart side
        // it takes the binary messenger and the name of the channel
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger,CHANNEL).setMethodCallHandler{
            // call is the method call from the Dart side
            // result is the result of the method call
            call, result ->
                when(call.method){
                    // if the method call is "enableSecureMode", it sets the FLAG_SECURE flag on the window
                    "enableSecureMode"->{
                        window.setFlags(
                            WindowManager.LayoutParams.FLAG_SECURE,
                            WindowManager.LayoutParams.FLAG_SECURE
                        )
                        // result.success is called to return a successful result to the Dart side
                        result.success(null)
                    }
                    // if the method call is "disableSecureMode", it clears the FLAG_SECURE flag on the window
                    "disableSecureMode" -> {
                        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
                        // result.success is called to return a successful result to the Dart side
                        result.success(null)
                    }
                    // if the method call is not recognized, result.notImplemented is called to return an error to the Dart side
                    else -> result.notImplemented()
                }
        }
    }
}

