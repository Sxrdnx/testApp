package com.example.testapp.util

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast

fun EditText.validate( validation: (String)->Unit){
    this.addTextChangedListener(object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
            validation(p0.toString())
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    })
}

fun Activity.toast(message: CharSequence, duration:Int= Toast.LENGTH_SHORT)=Toast.makeText(this,message,duration).show()