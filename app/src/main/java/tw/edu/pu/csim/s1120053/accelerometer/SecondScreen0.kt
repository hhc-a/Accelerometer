package tw.edu.pu.csim.s1120053.accelerometer

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen0() {
    var msg by remember { mutableStateOf("") }
    var msg2 by remember { mutableStateOf("") }
    var showFirst by remember { mutableStateOf(false) }
    var xTilt by remember { mutableStateOf(0f) }
    var yTilt by remember { mutableStateOf(0f) }
    val context = LocalContext.current
    val sensorManager = context.getSystemService(SENSOR_SERVICE) as SensorManager
    val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event != null){
                xTilt = event.values[0]
                yTilt = event.values[1]
                msg = "加速感應器實例\n" + xTilt.toString()+"\n"+yTilt.toString()
                if (Math.abs(xTilt)<1 && Math.abs(yTilt)<1) {
                    msg2 = "朝下平放"
                }
                else if (Math.abs(xTilt) + Math.abs(yTilt)> 32) {
                    msg2 = "手機搖晃"
                }
            }
        }
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        }
    }
    Column(modifier = Modifier.fillMaxSize().background(Color.Yellow),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(msg)
        Text(msg2)
        Button(onClick = { showFirst = true })
        {

            Text(text = "返回畫面1")
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize() // Box填滿整個螢幕
    ) {
        Image(
            painter = painterResource(id = R.drawable.penguin),
            contentDescription = "penguin",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopEnd)
//                .offset(penguinOffset.x.dp,penguinOffset.y.dp)
        )
    }
    if (showFirst){
        FirstScreen()
    }
}