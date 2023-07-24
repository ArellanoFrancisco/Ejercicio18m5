package com.example.ejercicio18m5

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class DatosList {
    private val datosList: MutableList<Datos> = mutableListOf()

    fun obtenerDatosList(): MutableList<Datos> {
        return datosList
    }

    fun addDatos(entero: Int, texto: String, selector: Boolean, decimal: Double) {
        datosList.add(Datos(entero, texto, selector, decimal))
    }


    // Serialización y deserialización para guardar y recuperar la lista de datos en formato JSON
    fun serialize(): String {
        val jsonArray = JSONArray()
        for (datos in datosList) {
            val jsonObject = JSONObject()
            jsonObject.put("entero", datos.entero)
            jsonObject.put("texto", datos.texto)
            jsonObject.put("selector", datos.selector)
            jsonObject.put("decimal", datos.decimal)
            jsonArray.put(jsonObject)
        }
        return jsonArray.toString()
    }

    companion object {
        fun deserialize(data: String?): DatosList {
            val datosList = DatosList()
            if (!data.isNullOrEmpty()) {
                try {
                    val jsonArray = JSONArray(data)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val entero = jsonObject.getInt("entero")
                        val texto = jsonObject.getString("texto")
                        val selector = jsonObject.getBoolean("selector")
                        val decimal = jsonObject.getDouble("decimal")
                        datosList.addDatos(entero, texto, selector, decimal)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            return datosList
        }
    }
}


