package ru.clinri.trafficlighter

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import java.util.*

class MainActivity : Activity() {
    var imTrafLighter: ImageView? = null
    var imageArray: IntArray = intArrayOf(
        R.drawable.semafor_red,
        R.drawable.semafor_yellow,
        R.drawable.semafor_green
    )
    var counter: Int = 0
    var timer: Timer? = null
    var isRun: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imTrafLighter = findViewById(R.id.imTrafficLighter)

    }

    fun onClickStartStop(view: View) {
        view as ImageButton
        if (!isRun) {
            startStop()
            view.setImageResource(R.drawable.button_stop)
            isRun = true
        } else {
            imTrafLighter?.setImageResource(R.drawable.semafor_grey)
            view.setImageResource(R.drawable.button_start)
            timer?.cancel() //останавливаем таймер если он не равен null
            isRun = false
            counter = 0
        }
    }

    fun startStop() {
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    imTrafLighter?.setImageResource(imageArray[counter])
                    counter++
                    if (counter == 3) counter = 0
                }
            }
        }, 0, 1000)
    }
}