package com.greentea.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.greentea.barvolume.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //disesuaikan dengan jenis tag xmlnya
//    private lateinit var edt_length : EditText
//    private lateinit var edt_width : EditText
//    private lateinit var edt_height : EditText
//    private lateinit var btn_calculate : Button
//    private lateinit var tv_result : TextView

//    menyiapkan viewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //use the viewBinding
        binding.btnCalculate.setOnClickListener(this)

        //didalam onCreate(), kita akan menyamakan variable yang dibuat diatas dengan id pada xml
//        edt_length = findViewById(R.id.edt_length)
//        edt_width = findViewById(R.id.edt_width)
//        edt_height = findViewById(R.id.edt_height)
//        btn_calculate = findViewById(R.id.btn_calculate)
//        tv_result = findViewById(R.id.tv_result)

        //for button, we have to use setOnClickListener(), because we will click the button
//        btn_calculate.setOnClickListener(this)

        if(savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT)
            binding.tvResult.text = result
        }
    }

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    //to save ketika kita melakukan rotate phone
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, binding.tvResult.text.toString())
    }

    override fun onClick(p0: View?) {
        //this will count the volume, but we have to change it to string first so that it'll ignore space
        if(p0?.id == R.id.btn_calculate){
            val inputLength = binding.edtLength.text.toString().trim()
            val inputWidth = binding.edtWidth.text.toString().trim()
            val inputHeight = binding.edtHeight.text.toString().trim()

            //to make the input required to filled:
            var isEmptyField = false

            if(inputLength.isEmpty()){
                isEmptyField = true
                binding.edtLength.error = getString(R.string.fieldRequired)
            }

            if(inputWidth.isEmpty()){
                isEmptyField = true
                binding.edtWidth.error = getString(R.string.fieldRequired)
            }

            if(inputHeight.isEmpty()){
                isEmptyField = true
                binding.edtHeight.error = getString(R.string.fieldRequired)
            }

            if(!isEmptyField){
                //after that, we can turn it back to double
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()

                //to show it to user, change it again to string
                binding.tvResult.text = volume.toString()
            }
        }
    }
}