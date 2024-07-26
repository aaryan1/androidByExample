package com.tks.androidbyexamples

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class LooperHandlerActivity:AppCompatActivity() {

    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m_looper_handler_activity)


        // Step 1: Create and start the HandlerThread
        handlerThread = HandlerThread("BackgroundThread")
        handlerThread.start()



        // Step 2: Initialize the handler with the Looper of the HandlerThread
        handler = object : Handler(handlerThread.looper) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    1 -> Log.d("HandlerThread", "Message received: ${msg.obj}")
                }
            }
        }


        // Step 3: Post messages or runnables to the Handler
        handler.post {
            Log.d("HandlerThread", "Runnable executed")
        }

        // Step 4: Send a message to the handler
        val message = handler.obtainMessage(1, "Hello, Handler!")
        handler.sendMessage(message)




    }

    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quitSafely()
        //quites the looper
    }
}