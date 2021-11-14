package com.example.testapp.util

import android.util.Log
import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory
/*
* Envia ping al DNS primario de Google.
* Si tiene éxito, eso significa que tenemos Internet.
*/
object DoesNetworkHaveInternet {
    private const val TAG = "AppDebug"
    fun execute(socketFactory: SocketFactory): Boolean {
        return try{
            Log.d(TAG, "PINGING google.")
            val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            Log.d(TAG, "PING success.")
            true
        }catch (e: IOException){
            Log.e(TAG, "No internet connection. $e")
            false
        }
    }
}
