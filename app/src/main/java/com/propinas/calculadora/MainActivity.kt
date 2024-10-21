package com.propinas.calculadora

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.propinas.calculadora.databinding.ActivityMainBinding
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.calculateButton.setOnClickListener {
            val costString = binding.costOfService.text.toString()
            if (costString.isNotEmpty()) {
                val costInt = costString.toInt()
                val tipPercentage = when {
                    binding.optionTwentyPercent.isChecked -> 0.20
                    binding.optionEighteenPercent.isChecked -> 0.18
                    binding.optionFifteenPercent.isChecked -> 0.15
                    else -> 0.0
                }

                val totalValue = caclularTip(costInt, tipPercentage)

                if (binding.roundUpSwitch.isChecked) {
                    val roundedValue = round(totalValue)
                    binding.tipResult.text = roundedValue.toString()
                } else {
                    binding.tipResult.text = totalValue.toString()
                }
            } else {
                binding.tipResult.setText("Enter a valid cost")
            }

        }
    }

    private fun caclularTip(valor: Int, percentage: Double): Double {
//        var valueWithTip = 0.0
        var tip = 0.0

        tip = (valor * percentage)

//        valueWithTip = valor + tip

        return tip
    }
}