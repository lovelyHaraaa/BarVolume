package com.greentea.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //disesuaikan dengan jenis tag xmlnya
    private lateinit var edt_length : EditText
    private lateinit var edt_width : EditText
    private lateinit var edt_height : EditText
    private lateinit var btn_calculate : Button
    private lateinit var tv_result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //didalam onCreate(), kita akan menyamakan variable yang dibuat diatas dengan id pada xml
        edt_length = findViewById(R.id.edt_length)
        edt_width = findViewById(R.id.edt_width)
        edt_height = findViewById(R.id.edt_height)
        btn_calculate = findViewById(R.id.btn_calculate)
        tv_result = findViewById(R.id.tv_result)

        //for button, we have to use setOnClickListener(), because we will click the button
        btn_calculate.setOnClickListener(this)

        if(savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT)
            tv_result.text = result
        }
    }

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    //to save ketika kita melakukan rotate phone
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tv_result.text.toString())
    }

    override fun onClick(p0: View?) {
        //this will count the volume, but we have to change it to string first so that it'll ignore space
        if(p0?.id == R.id.btn_calculate){
            val inputLength = edt_length.text.toString().trim()
            val inputWidth = edt_width.text.toString().trim()
            val inputHeight = edt_height.text.toString().trim()

            //to make the input required to filled:
            var isEmptyField = false

            if(inputLength.isEmpty()){
                isEmptyField = true
                edt_length.error = getString(R.string.fieldRequired)
            }

            if(inputWidth.isEmpty()){
                isEmptyField = true
                edt_width.error = getString(R.string.fieldRequired)
            }

            if(inputHeight.isEmpty()){
                isEmptyField = true
                edt_height.error = getString(R.string.fieldRequired)
            }

            if(!isEmptyField){
                //after that, we can turn it back to double
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()

                //to show it to user, change it again to string
                tv_result.text = volume.toString()
            }
        }
    }
}