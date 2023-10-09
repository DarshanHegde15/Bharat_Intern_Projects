package com.example.easycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.easycalc.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    var lastNumeric=false
    var stateError=false
    var lastDot=false
    private lateinit var expression:Expression

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClear(view: View) {
        binding.type.text=""
        lastNumeric=false
    }

    fun onBackClick(view: View) {
        binding.type.text=binding.type.text.toString().dropLast(1)
        try{
            val lastChar=binding.type.text.toString().last()
            if(lastChar.isDigit()){
                onEquals()
            }
        }catch(ex:Exception){
            binding.result.text=""
            binding.result.visibility=View.GONE
            Log.e("Last char error",ex.toString())
        }
    }

    fun onOperatorClick(view: View) {
        if(!stateError && lastNumeric){
            binding.type.append((view as Button).text)
            lastDot=false
            lastNumeric=false
            onEquals()
        }
    }

    fun onDigitClick(view: View) {
        if(stateError){
            binding.type.text=(view as Button).text
            stateError=false
        }else{
            binding.type.append((view as Button).text)
        }
        lastNumeric=true
        onEquals()
    }

    fun onAllClearClick(view: View) {
        binding.type.text=""
        binding.result.text=""
        stateError=false
        lastNumeric=false
        lastDot=false
        binding.result.visibility=View.GONE
    }

    fun onEqualsClick(view:View){
        onEquals()
        binding.type.text=binding.result.text.toString().drop(1)
    }

    fun onEquals() {
        if (lastNumeric && !stateError) {
            val text = binding.type.text.toString()
            expression = ExpressionBuilder(text).build()
            try {
                val result=expression.evaluate()
                binding.result.visibility=View.VISIBLE
                binding.result.text="="+result.toString()
            } catch (ex:ArithmeticException) {
                Log.e("Evaluate Error!",ex.toString())
                binding.result.text="Error"
                stateError=true
                lastNumeric=false
            }
        }
    }

}