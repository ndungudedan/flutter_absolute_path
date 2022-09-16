package com.kasem.flutter_absolute_path

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import android.util.Log
import android.content.pm.ProviderInfo
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import java.security.Provider


class FlutterAbsolutePathPlugin(): MethodCallHandler, FlutterPlugin {
    private lateinit var applicationContext: Context

    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        this.applicationContext=binding.applicationContext
        val channel = MethodChannel(binding.binaryMessenger, "flutter_absolute_path")
        channel.setMethodCallHandler(FlutterAbsolutePathPlugin())
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        TODO("Not yet implemented")
    }

/*companion    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "flutter_absolute_path")
            channel.setMethodCallHandler(FlutterAbsolutePathPlugin())
        }
    }*/

    override fun onMethodCall(call: MethodCall, result: Result) {
        when {
            call.method == "getAbsolutePath" -> {
                val uriString = call.argument<Any>("uri") as String
                val uri = Uri.parse(uriString)

//                val provider = applicationProviders?.firstOrNull { uri.authority == it.authority }
//                if (provider != null) {
//                    val folderPath = Environment.getExternalStorageDirectory().path + "/Pictures"
//                    result.success("$folderPath/${uri.lastPathSegment}")
//                    return
//                }

                result.success(FileDirectory.getAbsolutePath(this.applicationContext, uri))
            }
            else -> result.notImplemented()
        }
    }

//    val applicationProviders: List<ProviderInfo>? by lazy {
//        val applicationId = context.packageName
//        context.packageManager
//                .getInstalledPackages(PackageManager.GET_PROVIDERS)
//                .firstOrNull { it.packageName == applicationId }
//                ?.providers?.toList()
//    }
}
