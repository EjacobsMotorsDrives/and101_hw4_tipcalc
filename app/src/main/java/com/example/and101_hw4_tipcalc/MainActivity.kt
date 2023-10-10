package com.example.and101_hw4_tipcalc

import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.and101_hw4_tipcalc.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }



    }
    private fun calculateTip()
    {



        val listPrice = binding.costOfService.text.toString()
        val listP = listPrice.toDoubleOrNull()

        val qtyR = binding.unitsSold.text.toString()
        val qty = qtyR.toDoubleOrNull()



        if (listP == null)
        {
            binding.grossResult.text = ""
            return
        }

        if (qty == null)
        {
            binding.netresult.text = ""
            return
        }


        val commissionPercent = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        val taxRate = when (binding.stateOptions.checkedRadioButtonId) {
            R.id.option_NY -> 0.04875
            R.id.option_NJ -> 0.0625
            else -> 0.0635
        }

        val partnerType = when (binding.salesOptions.checkedRadioButtonId) {
            R.id.sales_0 -> 0.20
            R.id.sales_10 -> 0.40
            else -> 0.50
        }

        var cost = listP * partnerType * qty
        var total = listP * qty
        var tax = total * taxRate

        var gross = total + tax


        var netsale = total -tax - cost
        var commission = netsale * commissionPercent



        if (binding.roundUpSwitch.isChecked)
        {
            gross = kotlin.math.ceil(gross)
            tax = kotlin.math.ceil(tax)
            commission = kotlin.math.ceil(commission)
            netsale = kotlin.math.ceil(netsale)
        }


        val formattedGross = NumberFormat.getCurrencyInstance().format(gross)
        binding.grossResult.text = getString(R.string.gross_amount, formattedGross)

        val formattedtax = NumberFormat.getCurrencyInstance().format(tax)
        binding.taxresult.text = getString(R.string.tax_amount, formattedtax)

        val formattedcommission = NumberFormat.getCurrencyInstance().format(commission)
        binding.comresult.text = getString(R.string.Commission_amount, formattedcommission)

        val formattednet = NumberFormat.getCurrencyInstance().format(netsale)
        binding.netresult.text = getString(R.string.net_amount, formattednet)

    }
}

