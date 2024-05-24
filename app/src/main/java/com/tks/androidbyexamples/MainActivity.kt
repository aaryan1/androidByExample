package com.tks.androidbyexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.*
import android.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var intent: Intent
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val button:Button=findViewById(R.id.btn_looper)
//
//        button.setOnClickListener(this)
//    }

    //just  for interview sake code is being written in in on resume function
    override fun onResume() {
        super.onResume()
        setContentView(R.layout.activity_main)
        val button:Button=findViewById(R.id.btn_looper)
        val button_flow:Button=findViewById(R.id.btn_flow)
        button.setOnClickListener(this)
        button_flow.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_looper-> callLooperActivity()
            R.id.btn_flow->callFlowActivity()
            else -> noOperationDefined()

        }
    }

    private fun callFlowActivity() {
        intent=Intent(applicationContext,KotlinFlowActivity::class.java)
        startActivity(intent)
    }

    private  fun callLooperActivity(){
         intent=Intent(applicationContext,Mlooperhandler::class.java)
        startActivity(intent)
    }

    private fun noOperationDefined()=Toast.makeText(this,"No operation defined",Toast.LENGTH_LONG).show()
}