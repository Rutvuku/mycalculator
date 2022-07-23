package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean=false
    var lastDot  : Boolean= false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        val input = findViewById<TextView>(R.id.tvInput)
        input.append((view as Button).text)
        lastNumeric=true

    }
    fun onClear(view: View){
        val input1 = findViewById<TextView>(R.id.tvInput)
        input1.text = ""
        lastNumeric=false
        lastDot=false

    }

    fun onDecimal(view: View){
        val input2 = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric && !lastDot){
            input2.append(".")
            lastNumeric=false
            lastDot=true
        }
    }

    fun onEqual(view:View){
        val input4= findViewById<TextView>(R.id.tvInput)
        if(lastNumeric) {
            var tvValue = input4.text.toString()
            var prefix =""

           

            try {

                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue =tvValue.substring(1)
                }

                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    input4.text = removezerodot((one.toDouble() - two.toDouble()).toString())
                }

                else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    input4.text = removezerodot((one.toDouble() + two.toDouble()).toString())
                }

                else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    input4.text = removezerodot((one.toDouble() * two.toDouble()).toString())
                }

                else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    input4.text = removezerodot((one.toDouble() / two.toDouble()).toString())
                }



            }catch (e:java.lang.ArithmeticException){
                e.printStackTrace()
            }

        }

    }

    private fun removezerodot(result:String):String{
        var remove= result
        if(result.contains(".0")){
            remove=result.substring(0,result.length-2)
        }
        return remove
    }

    fun onOperator(view:View){
        val input3 = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric && !isOperatorAdded(input3.text.toString())){
            input3.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String) : Boolean{
        return if(value.startsWith("-")){
            false

        }else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}