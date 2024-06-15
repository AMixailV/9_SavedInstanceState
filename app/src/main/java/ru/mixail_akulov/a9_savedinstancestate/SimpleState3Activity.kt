package ru.mixail_akulov.a9_savedinstancestate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import ru.mixail_akulov.a9_savedinstancestate.databinding.ActivitySimpleStateBinding
import java.io.Serializable
import kotlin.random.Random

class SimpleState3Activity : AppCompatActivity() {

    private var binding: ActivitySimpleStateBinding? = null
    lateinit var state: State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySimpleStateBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.incrementButton?.setOnClickListener { increment() }
        binding?.randomColorButton?.setOnClickListener { setRandomColor() }
        binding?.switchVisibilityButton?.setOnClickListener { switchVisibility() }

        state = if (savedInstanceState == null) {
            State(
            counterValue = 0,
            counterTextColor = ContextCompat.getColor(this, R.color.purple_500),
            counterVisible = true
            )
        } else {
            savedInstanceState.getSerializable(KEY_STATE) as State
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(KEY_STATE, state)
    }

    private fun increment() {
        state.counterValue++
        renderState()
    }

    private fun setRandomColor() {
        state.counterTextColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun switchVisibility() {
        state.counterVisible = !state.counterVisible
        renderState()
    }

    private fun renderState() = with (binding) {
        this!!.counterTextView.text = state.counterValue.toString()
        counterTextView.setTextColor(state.counterTextColor)
        counterTextView.visibility = if (state.counterVisible) View.VISIBLE else View.INVISIBLE
    }

    class State(
        var counterValue: Int,
        var counterTextColor: Int,
        var counterVisible: Boolean
    ) : Serializable

    companion object {
        @JvmStatic private val KEY_STATE = "STATE"
    }
}