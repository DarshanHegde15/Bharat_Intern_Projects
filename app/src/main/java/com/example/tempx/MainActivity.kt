package com.example.tempx

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtView=getTxtView(this,R.layout.drawable_list,R.id.txtView)
        val editText:TextInputEditText=findViewById(R.id.textEditText)
        var et: TextView=findViewById<TextView>(R.id.result)
        val btn:Button=findViewById(R.id.button)

        val item= listOf("°C","°F","K")
        val convertTo:AutoCompleteTextView=findViewById(R.id.autoCompleteTextView2)

        val convertToAdapter=ArrayAdapter(this,R.layout.drawable_list,item)
        convertTo.setAdapter(convertToAdapter)
        convertTo.onItemClickListener= AdapterView.OnItemClickListener{
                adapterView, view, i, l ->  val itemSelected=adapterView.getItemAtPosition(i)
            txtView.setText(itemSelected.toString())
            btn.setOnClickListener{
                if(txtView.text.toString()=="°C") {
                    et.setText(editText.text.toString() + "°C")
                    et.visibility= View.VISIBLE
                }
                else if(txtView.text.toString()=="°F"){
                    val str=editText.text.toString()
                    val temp=str.toDouble()
                    val result=(temp*9/5)+32
                    et.setText(result.toString()+"°F")
                }
                else{
                    val str=editText.text.toString()
                    val temp=str.toDouble()
                    val result=temp+273.15
                    et.setText(result.toString()+" K")
                }
            }

        }

    }

    fun getTxtView(context: Context, xmlFileResId: Int, textViewId: Int):TextView{
        val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view=inflater.inflate(xmlFileResId,null)
        val textView=view.findViewById<TextView>(textViewId)
        return textView
    }

}

