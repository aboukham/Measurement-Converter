package com.measurment.convertor

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.measurment.convertor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MeasurementAdapter
    val context: Context = this
    val measurements = mutableListOf<Measurement>(
        Measurement("Weight", R.drawable.weight),
        Measurement("Distance", R.drawable.distance),
        Measurement("Liquid Capacity", R.drawable.liquid_capacity),
        Measurement("Temperature", R.drawable.temperature),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MeasurementAdapter(measurements)
        binding.rvConvertor.layoutManager = GridLayoutManager(this, 1)
        binding.rvConvertor.adapter = adapter

    }




}