package com.fernando.aplicacionsettings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.fernando.aplicacionsettings.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

// "by" sirve para crear una sola instancia de esta "base de datos"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
private var firstTime: Boolean = true

class MainActivity : AppCompatActivity() {

    companion object {
        const val VOLUME_LVL = "volume_lvl"
        const val KEY_DARKMODE = "KEY_DARKMODE"
        const val KEY_VIBRATION = "KEY_VIBRATION"
        const val KEY_BLUETOOTH = "KEY_BLUETOOTH"
    }


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperamos los parametros actualizados y actualizamos la UI con "FLOW"
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect { settingsModel ->
                if (settingsModel != null) {
                    runOnUiThread {
                        binding.SwitchVibration.isChecked = settingsModel.vibration
                        binding.switchBluetooth.isChecked = settingsModel.bluetooth
                        binding.swichDarkMode.isChecked = settingsModel.darkmode
                        binding.rsVolume.setValues(settingsModel.volume.toFloat())
                        firstTime = !firstTime
                    }

                }
            }

        }
        initUI()
    }

    private fun initUI() {
        binding.rsVolume.addOnChangeListener { _, value, _ ->
//            Log.i("Fernando", "el valor es $value")
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }

        // La barra baja se pone cuando no te interesa usar una variable que te devuelve el listener
        binding.switchBluetooth.setOnCheckedChangeListener { _, value ->
            // Dentro de la corotine se usa el "IO" para temas de backend como bases de datos
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(Companion.KEY_BLUETOOTH, value)
            }
        }

        binding.SwitchVibration.setOnCheckedChangeListener { _, value ->
            // Dentro de la corotine se usa el "IO" para temas de backend como bases de datos
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(Companion.KEY_VIBRATION, value)
            }
        }

        binding.swichDarkMode.setOnCheckedChangeListener { _, value ->

            if (value){
                enableDarkMode()
            } else {
                disabledDarkMode()
            }


            // Dentro de la corotine se usa el "IO" para temas de backend como bases de datos
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(Companion.KEY_DARKMODE, value)
            }
        }


    }


    // Suspend es para las corrutinas
    private suspend fun saveVolume(value: Int) {

        dataStore.edit { preferences ->
            // Dependediendo del tipo de variable se pone una cosa u otra
            preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    // Vamos a crear una funcion para todos los switch y no replicar codigo guardando en la bd
    private suspend fun saveOptions(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    private fun getSettings(): Flow<SettingsData?> {
        return dataStore.data.map { preferences ->
            SettingsData(
                volume = preferences[intPreferencesKey(VOLUME_LVL)] ?: 50, // "?:
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: true,
                darkmode = preferences[booleanPreferencesKey(KEY_DARKMODE)] ?: false,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: true
            )

        }
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disabledDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

}