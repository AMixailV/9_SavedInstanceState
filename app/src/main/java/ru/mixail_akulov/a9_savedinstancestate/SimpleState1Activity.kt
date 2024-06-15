package ru.mixail_akulov.a9_savedinstancestate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import ru.mixail_akulov.a9_savedinstancestate.databinding.ActivitySimpleStateBinding
import kotlin.random.Random

class SimpleState1Activity : AppCompatActivity() {

    private var binding: ActivitySimpleStateBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySimpleStateBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.incrementButton?.setOnClickListener { increment() }
        binding?.randomColorButton?.setOnClickListener { setRandomColor() }
        binding?.switchVisibilityButton?.setOnClickListener { switchVisibility() }
    }

    private fun increment() {
        var counter = binding?.counterTextView?.text.toString().toInt()
        counter++
        binding?.counterTextView?.text = counter.toString()
    }

    private fun setRandomColor() {
        val randomColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        binding?.counterTextView?.setTextColor(randomColor)
    }

    private fun switchVisibility() = with(binding?.counterTextView) {
        this?.visibility = if (this?.visibility == VISIBLE)
            INVISIBLE
        else
            VISIBLE
    }
}