package com.measurment.convertor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.measurment.convertor.databinding.ActivitySecondBinding
import java.text.DecimalFormat

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        val measurement = intent.getStringExtra("measurement")
        val hint = intent.getStringExtra("hint")
        binding.etMeasurement.hint = hint
        binding.tvMeasurement.text = measurement
        val dcf = DecimalFormat("#.###")
        with(binding){
            btnConvert.setOnClickListener {
                    try {
                        val value = etMeasurement.text.toString().toDouble()
                        if (measurement.equals("weight", true)){
                            if (hint.equals("pounds", true)){

                                val kilograms = poundToKilogram(value)
                                tvResult.text = "${dcf.format(kilograms)} Kilograms"
                            }else {
                                val pounds = kilogramToPound(value)
                                tvResult.text = "${dcf.format(pounds)} Pounds"
                            }
                            btnRelationship.setOnClickListener {
                                tvRelationship.text = "1 kilogram = $POUNDS_IN_ONE_KILOGRAM pounds"
                            }

                        } else  if (measurement.equals("distance", true)){
                            if (hint.equals("kilometers", true)){
                                val miles = kilometerToMile(value)
                                tvResult.text = "${dcf.format(miles)} Miles"
                            }else {
                                val kilometers = mileToKilometer(value)
                                tvResult.text = "${dcf.format(kilometers)} Kilometers"
                            }
                            btnRelationship.setOnClickListener {
                                tvRelationship.text = "1 mile = $KILOMETERS_IN_ONE_MILE kilometers"
                            }
                        }else  if (measurement.equals("liquid capacity", true)){
                            if (hint.equals("liters", true)){
                                val gallons = litreToGallon(value)
                                tvResult.text = "${dcf.format(gallons)} Gallons"
                            }else {
                                val litres = gallonToLitre(value)
                                tvResult.text = "${dcf.format(litres)} Liters"
                            }
                            btnRelationship.setOnClickListener {
                                tvRelationship.text = "1 gallon = $LITRES_IN_ONE_GALLON liters"
                            }
                        }else  if (measurement.equals("temperature", true)){
                            if (hint.equals("celsius", true)){
                                val fahrenheit = celsiusToFahrenheit(value)
                                tvResult.text = "${dcf.format(fahrenheit)} Fahrenheit"
                            }else {
                                val celsius = fahrenheitToCelsius(value)
                                tvResult.text = "${dcf.format(celsius)} Celsius"
                            }
                            btnRelationship.setOnClickListener {
                                tvRelationship.text = "1 Fahrenheit = 9 * celsius / 5  + 32"
                            }
                        }
                    }catch (e : java.lang.NumberFormatException){
                        showDialog("You entered invalid input, please enter the numbers only")
                    }
                }
                btnBack.setOnClickListener {
                    finish()
                }
                btnReset.setOnClickListener {
                    etMeasurement.setText("")
                    tvResult.text = ""
                    tvRelationship.text = ""
                }
            }


        }


    fun poundToKilogram(pound: Double) = pound / POUNDS_IN_ONE_KILOGRAM
    fun kilogramToPound(kilogram: Double) = kilogram * POUNDS_IN_ONE_KILOGRAM
    fun kilometerToMile(kilometer: Double) = kilometer / KILOMETERS_IN_ONE_MILE
    fun mileToKilometer(mile : Double) = mile * KILOMETERS_IN_ONE_MILE
    fun litreToGallon(litre: Double) = litre / LITRES_IN_ONE_GALLON
    fun gallonToLitre(gallon: Double) = gallon * LITRES_IN_ONE_GALLON
    fun celsiusToFahrenheit(celsius: Double) = 32 + (9 * celsius) / 5
    fun fahrenheitToCelsius(fahrenheit: Double) = (fahrenheit - 32) * 5 / 9
    companion object{
        const val POUNDS_IN_ONE_KILOGRAM = 2.204
        const val KILOMETERS_IN_ONE_MILE = 1.609344
        const val LITRES_IN_ONE_GALLON = 3.785


    }

    fun showDialog( desc:String ) {
       val builder = AlertDialog.Builder(this)

        builder.setTitle("Invalid input")
        builder.setMessage(desc)
        builder.setPositiveButton("Try again") { dialogInterface, which ->
            which.toString()
            dialogInterface.dismiss()
        }
        val dialog = builder.create()

        dialog.show()
    }
}