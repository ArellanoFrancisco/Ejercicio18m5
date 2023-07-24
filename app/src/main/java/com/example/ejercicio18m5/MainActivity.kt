package com.example.ejercicio18m5

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio18m5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var spManager: SPManager
    private lateinit var datosList: DatosList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        spManager = SPManager(this)
        datosList = spManager.getdataList()
        mostrarDatos()

        binding.button2.setOnClickListener {
            guardarDatos()
            mostrarDatos()
        }
        binding.button.setOnClickListener {
            eliminarDatos()
            mostrarDatos()
            binding.ETInt.text.clear()
            binding.ETText.text.clear()
            binding.ETDouble.text.clear()
        }
    }

    private fun guardarDatos() {
        val enteroText = binding.ETInt.text.toString()
        val texto = binding.ETText.text.toString()
        val decimalText = binding.ETDouble.text.toString()
        val selector = binding.switch1.isChecked

        if (enteroText.isNotEmpty() && decimalText.isNotEmpty()) {
            val entero = enteroText.toInt()
            val decimal = decimalText.toDouble()
            datosList.addDatos(entero, texto, selector, decimal)
            spManager.saveDataList(datosList)
        } else {
            Toast.makeText(this, "Debes ingresar los datos", Toast.LENGTH_SHORT).show()

        }
    }

    private fun mostrarDatos() {
        val dataList = datosList.obtenerDatosList()

        if (dataList.isNotEmpty()) {
            // Mostrar el valor entero almacenado en TextView
            val entero = dataList.last().entero
            binding.TVInt.text = getString(R.string.TvInt, entero)

            // Mostrar el texto almacenado en TextView
            val texto = dataList.last().texto
            binding.TVText.text = getString(R.string.Tvtext, texto)

            // Mostrar el valor decimal almacenado en TextView
            val decimal = dataList.last().decimal
            binding.TVDouble.text = getString(R.string.Tvdouble, decimal)

            // Mostrar el estado del Switch
            val selector = dataList.last().selector
            binding.switch1.isChecked = selector

            // Mostrar el texto asociado al estado del Switch
            val switchTextResId = if (selector) R.string.Switch1 else R.string.Switch2
            binding.switch1.text = getString(switchTextResId)
        } else {
            // Mostrar valores por defecto si no hay datos almacenados
            binding.TVInt.text = getString(R.string.TvInt, 0)
            binding.TVText.text = getString(R.string.Tvtext, "")
            binding.TVDouble.text = getString(R.string.Tvdouble, 0.0)
        }
    }


    private fun eliminarDatos() {
        datosList.obtenerDatosList().clear()
        spManager.saveDataList(datosList)
    }
}
