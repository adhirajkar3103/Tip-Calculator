package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

private const val TAG = "MainActivity"
private const val INITIAL_TIP_AMOUNT = 15
class MainActivity : AppCompatActivity() {
    private lateinit var inputBaseAmount : EditText
    private lateinit var inputTipPerc : SeekBar
    private lateinit var tipPerc : TextView
    private lateinit var inputTipAmount : TextView
    private lateinit var inputTotalAmount : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputBaseAmount = findViewById(R.id.inputBaseAmount)
        inputTipPerc = findViewById(R.id.inputTipPerc)
        tipPerc = findViewById(R.id.tipPerc)
        inputTipAmount = findViewById(R.id.inputTipAmount)
        inputTotalAmount = findViewById(R.id.inputTotalAmount)

        inputTipPerc.progress = INITIAL_TIP_AMOUNT
        tipPerc.text = "$INITIAL_TIP_AMOUNT %"
        inputTipPerc.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                tipPerc.text = "$progress %"
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}

            override fun onStopTrackingTouch(seek: SeekBar) {}
        })
    }
}