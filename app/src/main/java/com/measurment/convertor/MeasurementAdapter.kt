package com.measurment.convertor

import android.content.Context
import android.content.Intent
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.measurment.convertor.databinding.ItemDesignBinding

class MeasurementAdapter(val measurements : List<Measurement> ) : Adapter<MeasurementViewHolder>() {
    lateinit var binding: ItemDesignBinding
    var view : View? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasurementViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemDesignBinding.inflate(layoutInflater)
        return MeasurementViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MeasurementViewHolder, position: Int) {
        holder.bind(binding, measurements[position])
        view = holder.itemView
        holder.itemView.setOnClickListener{
            //Toast.makeText(holder.itemView.context, "clicked", Toast.LENGTH_LONG).show()
              showMenu(holder.itemView, measurements[position])
        }


    }

    fun showMenu(view: View, measurement: Measurement){
        val wrapper : Context = ContextThemeWrapper(view.context, R.style.PopupMenuStyle)
        val menu = PopupMenu(wrapper, view)
        val intent = Intent(view.context, SecondActivity::class.java)
        if (measurement.name.equals("weight", true)){
            menu.menuInflater.inflate(R.menu.weight_menu, menu.menu)
            menu.setOnMenuItemClickListener {
                    menuItem ->
                intent.putExtra("measurement", "Weight")
                when(menuItem.itemId){
                    R.id.wt_pdToKg -> {
                        intent.putExtra("hint", "Pounds")
                        view.context.startActivity(intent)
                        true
                    }
                    R.id.wt_kgToPd -> {
                        intent.putExtra("hint", "kilograms")
                        view.context.startActivity(intent)
                        true
                    }else ->
                    false
                }

            }

        }else if (measurement.name.equals("distance", true)){
            menu.menuInflater.inflate(R.menu.distance_menu, menu.menu)
            menu.setOnMenuItemClickListener {
                    menuItem ->
                intent.putExtra("measurement", "Distance")
                when(menuItem.itemId){
                    R.id.dt_mToKm -> {
                        intent.putExtra("hint", "Miles")
                        view.context.startActivity(intent)
                        true
                    }
                    R.id.dt_kmToM -> {
                        intent.putExtra("hint", "kilometers")
                        view.context.startActivity(intent)
                        true
                    }else ->
                    false
                }

            }
        }else if (measurement.name.equals("liquid capacity", true)){
            menu.menuInflater.inflate(R.menu.liquid_menu, menu.menu)
            menu.setOnMenuItemClickListener {
                    menuItem ->
                intent.putExtra("measurement", "Liquid Capacity")
                when(menuItem.itemId){
                    R.id.lc_glToLt -> {
                        intent.putExtra("hint", "gallons")
                        view.context.startActivity(intent)
                        true
                    }
                    R.id.lc_ltToGl -> {
                        intent.putExtra("hint", "liters")
                        view.context.startActivity(intent)
                        true
                    }else ->
                    false
                }

            }
        }else if (measurement.name.equals("temperature", true)){
            menu.menuInflater.inflate(R.menu.temperature_menu, menu.menu)
            menu.setOnMenuItemClickListener {
                    menuItem ->
                intent.putExtra("measurement", "Temperature")
                when(menuItem.itemId){
                    R.id.tm_cToF -> {
                        intent.putExtra("hint", "celsius")
                        view.context.startActivity(intent)
                        true
                    }
                    R.id.tm_fToC -> {
                        intent.putExtra("hint", "fahrenheit")
                        view.context.startActivity(intent)
                        true
                    }else ->
                    false
                }

            }
        }
        menu.show()
    }



    override fun getItemCount() = measurements.size

    lateinit var onSelectedListener : (Measurement, Int) -> Unit
    //lateinit var menu : PopupMenu
    fun setOnMeasurementSelectedListener(listener: (Measurement, Int) -> Unit){
        onSelectedListener = listener
    }


}

class MeasurementViewHolder(view: View) : ViewHolder(view){
    fun bind(binding: ItemDesignBinding, measurement: Measurement ){
        binding.tvName.text = measurement.name
        binding.ivImage.setImageResource(measurement.image)
    }
}