package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
                computeTipAndTotal()
            }
            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}
        })

        inputBaseAmount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG,"text changed to $s")
                computeTipAndTotal()
            }

        })
    }

    private fun computeTipAndTotal() {
        // get bill amount and seekbar percent
        val baseAmount = inputBaseAmount.text.toString().toDouble()
        val tipPerc = inputTipPerc.progress
        //convert
        val tipAmt = baseAmount*tipPerc/100
        val totalAmt = tipAmt + baseAmount
        //change tip and total textviews
        inputTipAmount.text="$tipAmt"
        inputTotalAmount.text = "$totalAmt"
    }
}