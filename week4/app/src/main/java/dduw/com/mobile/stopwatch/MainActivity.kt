package dduw.com.mobile.stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import dduw.com.mobile.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isRunning = false
    private var time = 0
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start/Pause 버튼 클릭 리스너
        binding.startPauseButton.setOnClickListener {
            if (!isRunning) {
                startTimer()
                isRunning = true
                binding.startPauseButton.text = "Pause"
            } else {
                pauseTimer()
                isRunning = false
                binding.startPauseButton.text = "Start"
            }
        }

        // Clear 버튼 클릭 리스너
        binding.clearButton.setOnClickListener {
            if (isRunning) {
                clearTimerWithoutPause()
            } else {
                clearTimerWithPause()
            }
        }
    }

    private fun startTimer() {
        val startTime = System.currentTimeMillis()
        runnable = object : Runnable {
            override fun run() {
                val currentTime = System.currentTimeMillis()
                time = (currentTime - startTime).toInt()

                val minutes = (time / 60000) % 60
                val seconds = (time / 1000) % 60
                val milliseconds = (time % 1000) / 10
                binding.timeDisplay.text = String.format("%02d:%02d,%02d", minutes, seconds, milliseconds)

                handler.postDelayed(this, 10)
            }
        }
        handler.post(runnable)
    }


    private fun pauseTimer() {
        handler.removeCallbacks(runnable)
    }

    private fun clearTimerWithPause() {
        pauseTimer()
        time = 0
        binding.timeDisplay.text = "00:00,00"
    }

    private fun clearTimerWithoutPause() {
        time = 0
        binding.timeDisplay.text = "00:00,00"
        // 타이머가 계속 진행될 수 있도록 Runnable을 다시 시작합니다.
        handler.removeCallbacks(runnable)
        startTimer()
    }

}