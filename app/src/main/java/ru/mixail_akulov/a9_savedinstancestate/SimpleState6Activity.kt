package ru.mixail_akulov.a9_savedinstancestate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import ru.mixail_akulov.a9_savedinstancestate.databinding.ActivitySimpleStateBinding

class SimpleState6Activity : AppCompatActivity() {
    private var binding: ActivitySimpleStateBinding? = null
    private val viewModel by viewModels<SimpleState5ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleStateBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.incrementButton?.setOnClickListener { viewModel.increment() }
        binding?.randomColorButton?.setOnClickListener { viewModel.setRandomColor() }
        binding?.switchVisibilityButton?.setOnClickListener { viewModel.switchVisibility() }

        viewModel.initState(savedInstanceState?.getParcelable(KEY_STATE) ?: State(
            counterValue = 0,
            counterTextColor = ContextCompat.getColor(this, R.color.purple_500),
            counterVisible = true
        ))

        viewModel.state.observe(this, Observer {
            renderState(it)
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, viewModel.state.value)
    }

    private fun renderState(state: State) = with (binding) {
        this!!.counterTextView.text = state.counterValue.toString()
        counterTextView.setTextColor(state.counterTextColor)
        counterTextView.visibility = if (state.counterVisible) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        @JvmStatic private val KEY_STATE = "STATE"
    }
}