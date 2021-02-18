package com.example.tipcalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }

    }
    private fun calculateTip(){
//        Checks the UI and calculates the Tip
//        Getting the text of the cost of service (chaining)
        val stringInputTextField = binding.costOfServiceEditText.text.toString()
//Converting text to a decimal number
        val cost = stringInputTextField.toDoubleOrNull()
        if (cost == null){
            binding.tipResult.text = ""
            return
        }
//        Getting the tip percentage
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
//        Calculate the tip and round it up
        var tip = tipPercentage * cost //used var instead of var because you may need to round up the value if the user selected that option ,so the value might change
//        Checking whether the roundup switch is checked
        if (binding.roundUpSwitch.isChecked){
            tip = ceil(tip)
        }
//    A number formatter to use to format numbers as currency
        NumberFormat.getCurrencyInstance()
        displayTip(tip)


    }
    private fun displayTip(tip: Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
//        display the Tip
        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
    }


    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}