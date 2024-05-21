package org.sopt.app3.planfit.presentation

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import coil.ImageLoader
import coil.decode.ImageDecoderDecoder
import coil.load
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.core.ui.base.BaseActivity
import org.sopt.app3.planfit.databinding.ActivityStretchingMainBinding


class StretchingMainActivity : BaseActivity<ActivityStretchingMainBinding>({ inflater ->
    ActivityStretchingMainBinding.inflate(inflater)
}) {
    private var timer: CountDownTimer? = null
    private var isTimerRunning = false
    private val totalTime = 5 * 60 * 1000
    private var timeElapsed = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivExerciseStretching.setImageResource(R.drawable.gif_exc_cycle)
        setUpTimer()

        binding.btnProgressStop.setOnClickListener {
            toggleTimer()
        }
        binding.btnProgressNext.setOnClickListener {
            moveToExerciseMainActivity()
        }
    }

    private fun setUpTimer() {
        binding.pbProgressBar.max = totalTime
    }

    private fun moveToExerciseMainActivity() {
        val intent = Intent(
            this,
            org.sopt.app3.planfit.presentation.exercisemain.ExerciseMainActivity::class.java
        )
        startActivity(intent)
    }

    private fun startTimer() {
        val remainingTime = totalTime - timeElapsed
        timer = object : CountDownTimer(remainingTime, 1000) {
            override fun onTick(untilFinished: Long) {
                timeElapsed = totalTime - untilFinished
                val secondsElapsed = timeElapsed / 1000
                val minutes = secondsElapsed / 60
                val seconds = secondsElapsed % 60

                binding.tvTimeMinute.setText(String.format("%02d", minutes))
                binding.tvTimeSecond.setText(String.format("%02d", seconds))
                binding.pbProgressBar.progress = timeElapsed.toInt()
            }

            override fun onFinish() {
                binding.tvTimeMinute.setText("03")
                binding.tvTimeSecond.setText("00")
                binding.pbProgressBar.progress = totalTime
                timeElapsed = 0
            }
        }
        timer?.start()
        isTimerRunning = true
        loadGif(true)
    }

    private fun stopTimer() {
        timer?.cancel()
        isTimerRunning = false
        loadGif(false)
    }

    private fun toggleTimer() {
        if (isTimerRunning) {
            stopTimer()
        } else {
            startTimer()
        }
        updateButtonImage()
    }

    private fun loadGif(started: Boolean) {
        if (started) {
            val imageLoader = ImageLoader.Builder(this)
                .components {
                    add(ImageDecoderDecoder.Factory())
                }
                .build()
            binding.ivExerciseStretching.load(R.raw.gif_exc_cycle, imageLoader = imageLoader)
        } else {
            binding.ivExerciseStretching.setImageResource(R.drawable.gif_exc_cycle)
        }
    }

    private fun updateButtonImage() {
        val drawable = if (isTimerRunning) R.drawable.ic_pause__24 else R.drawable.ic_play__24
        binding.btnProgressStop.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, drawable)
    }
}
