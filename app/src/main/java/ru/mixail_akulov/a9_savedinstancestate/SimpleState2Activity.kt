package ru.mixail_akulov.a9_savedinstancestate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import ru.mixail_akulov.a9_savedinstancestate.databinding.ActivitySimpleStateBinding
import kotlin.properties.Delegates.notNull
import kotlin.random.Random

class SimpleState2Activity : AppCompatActivity() {

    private var binding: ActivitySimpleStateBinding? = null
    private var counterValue by notNull<Int>()
    private var counterTextColor by notNull<Int>()
    private var counterVisible by notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySimpleStateBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.incrementButton?.setOnClickListener { increment() }
        binding?.randomColorButton?.setOnClickListener { setRandomColor() }
        binding?.switchVisibilityButton?.setOnClickListener { switchVisibility() }

        if (savedInstanceState == null) {
            counterValue = 0
            counterTextColor = ContextCompat.getColor(this, R.color.purple_500)
            counterVisible = true
        } else {
            counterValue = savedInstanceState.getInt(KEY_COUNTER)
            counterTextColor = savedInstanceState.getInt(KEY_COLOR)
            counterVisible = savedInstanceState.getBoolean(KEY_IS_VISIBLE)
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_COUNTER, counterValue)
        outState.putInt(KEY_COLOR, counterTextColor)
        outState.putBoolean(KEY_IS_VISIBLE, counterVisible)
    }

    private fun increment() {
        counterValue++
        renderState()
    }

    private fun setRandomColor() {
        counterTextColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun switchVisibility() {
        counterVisible = !counterVisible
        renderState()
    }

    private fun renderState() = with (binding) {
        this!!.counterTextView.text = counterValue.toString()
        counterTextView.setTextColor(counterTextColor)
        counterTextView.visibility = if (counterVisible) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        @JvmStatic private val KEY_COUNTER = "COUNTER"
        @JvmStatic private val KEY_COLOR = "COLOR"
        @JvmStatic private val KEY_IS_VISIBLE = "IS_VISIBLE"
    }
}