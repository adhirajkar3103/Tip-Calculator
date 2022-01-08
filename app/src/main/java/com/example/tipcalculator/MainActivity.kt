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
    private lateinit var tipDescription : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputBaseAmount = findViewById(R.id.inputBaseAmount)
        inputTipPerc = findViewById(R.id.inputTipPerc)
        tipPerc = findViewById(R.id.tipPerc)
        inputTipAmount = findViewById(R.id.inputTipAmount)
        inputTotalAmount = findViewById(R.id.inputTotalAmount)
        tipDescription = findViewById(R.id.tipDescription)

        inputTipPerc.progress = INITIAL_TIP_AMOUNT
        tipPerc.text = "$INITIAL_TIP_AMOUNT %"
        updateTipDescription(INITIAL_TIP_AMOUNT)
        inputTipPerc.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                tipPerc.text = "$progress %"
                computeTipAndTotal()
                updateTipDescription(progress)
            }
            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}
        })

        inputBaseAmount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                computeTipAndTotal()
            }

        })
    }

    private fun updateTipDescription(progress : Int) {
        val tipDesc = when(progress){
            in 0..9 -> "Poor"
            in 10..14 -> "Acceptable"
            in 15..19 -> "Good"
            in 20..24 -> "Great"
            else -> "Amazing"
        }
        tipDescription.text = tipDesc
    }

    private fun computeTipAndTotal() {
        if(inputBaseAmount.text.isNullOrBlank()){
            inputTipAmount.text = ""
            inputTotalAmount.text = ""
            return
        }
        // get bill amount and seekbar percent
        val baseAmount = inputBaseAmount.text.toString().toDouble()
        val tipPerc = inputTipPerc.progress
        //convert
        val tipAmt = String.format("%.2f",  baseAmount*tipPerc/100).toDouble()
        val totalAmt =   String.format("%.2f", tipAmt + baseAmount).toDouble()
        //change tip and total textviews
        inputTipAmount.text="$tipAmt"
        inputTotalAmount.text = "$totalAmt"
    }
}