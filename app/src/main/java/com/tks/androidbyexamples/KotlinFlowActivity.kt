package com.tks.androidbyexamples

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class KotlinFlowActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.m_looper_handler_activity)
    }

    override fun onResume() {
        super.onResume()
        //https://amitshekhar.me/blog/flow-api-in-kotlin
       // simpleFlowExample()
        //flowOfExample()
        //asFlowExample()
        //channelFlowExample()
        simulateDownloadFileUsingFlow()
        // this is staging branch
    }
}


/*example of various flow builders*/
private fun simpleFlowExample(){

    CoroutineScope(Dispatchers.Main).launch {
        flow {
            (0..10).forEach {
                emit(it)
                delay(1000)
            }
        }.map {
            it * it
        }.collect {
            Log.d("Takendra-flow", it.toString())
        }
    }

}
private fun flowOfExample() {

    CoroutineScope(Dispatchers.Main).launch {

        flowOf(4, 5, 6, 7, 89,).collect {

            Log.d("Takendra-flowOf",it.toString())
    }
}

}

private fun asFlowExample(){


    CoroutineScope(Dispatchers.Main).launch {


        (1..5).asFlow()
            .collect {
                Log.d("Takendra-asflow ", it.toString())
            }


    }
}

private fun channelFlowExample(){
    CoroutineScope(Dispatchers.Main).launch {

        channelFlow {
            (0..10).forEach {
                send(it)
                delay(2000)
            }
        }
            .collect {
                Log.d("Takendra-channel flow ", it.toString())
            }


    }
}

private fun simulateDownloadFileUsingFlow() {

    val dowanlodImageflow= flow{ emit(10)  // here flow act as a speaker

        emit(40)
        emit(60)
        emit(80)
        emit(100)
    }.flowOn(Dispatchers.IO)//.map { it*2 }  // here map  act as a translator


    CoroutineScope(Dispatchers.Main).launch {


        dowanlodImageflow.collect{// here collect act as a listener

            Log.d("Takendra->","File progress $it")
        }
    }

}