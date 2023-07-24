package com.example.ejercicio18m5
import android.content.Context
import android.content.SharedPreferences

class SPManager(context: Context) {
    companion object {
        private const val SHARED_PREFERENCES_NAME = "Ejercicio18M5"
        private const val Lista_Datos = "DatosList"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun getdataList(): DatosList {
        val serializedatosList = sharedPreferences.getString(Lista_Datos, null)
        return DatosList.deserialize(serializedatosList)
    }

    fun saveDataList(datosList: DatosList) {
        val editor = sharedPreferences.edit()
        editor.putString(Lista_Datos, datosList.serialize())
        editor.apply()
    }

    fun deleteDataList() {
        val editor = sharedPreferences.edit()
        editor.remove(Lista_Datos)
        editor.apply()
    }
}
