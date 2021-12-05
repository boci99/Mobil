package com.example.beadando

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {

    var first = true
    var real_first = 0.0
    lateinit var stepsValue: TextView
    //var running = false
    //var sensorManager: SensorManager? = null
    lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepsValue = findViewById(R.id.stepsValue)
    }

    override fun onResume() {
        super.onResume()
        //running = true
        Toast.makeText(this, "elindult", Toast.LENGTH_SHORT).show()
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        //var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)

        if (stepsSensor == null) {
            Toast.makeText(this, "No Step Counter Sensor !", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager?.registerListener(this, stepsSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }


    override fun onPause() {
        super.onPause()
        //running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        //if (running) {
            if(first){
                first = false
                real_first = event.values[0].toDouble()
            }
            stepsValue.setText("" + (event.values[0]-real_first))
        //}
    }
}