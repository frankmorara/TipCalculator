package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }
    fun calculateTip(){
//        Checks the UI and calculates the Tip
//        Getting the text of the cost of service (chaining)
        val stringInputTextField = binding.costOfService.text.toString()
//Converting text to a decimal number
        val cost = stringInputTextField.toDoubleOrNull()
        if (cost == null){
            binding.tipResult.text = ""
            return
        }
//        Getting the tip percentage
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when(selectedId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
//        Calculate the tip and round it up
        var tip = tipPercentage * cost //used var instead of var because you may need to round up the value if the user selected that option ,so the value might change
//        Checking whether the roundup switch is checked
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp){
            tip = ceil(tip)
        }
//    A number formatter to use to format numbers as currency
        NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
//        display the Tip
        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)

    }
}