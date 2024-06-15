package ru.mixail_akulov.a9_savedinstancestate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.parcel.Parcelize
import ru.mixail_akulov.a9_savedinstancestate.databinding.ActivitySimpleStateBinding
import kotlin.random.Random

class SimpleState4Activity : AppCompatActivity() {

    private var binding: ActivitySimpleStateBinding? = null
    lateinit var state: State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySimpleStateBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.incrementButton?.setOnClickListener { increment() }
        binding?.randomColorButton?.setOnClickListener { setRandomColor() }
        binding?.switchVisibilityButton?.setOnClickListener { switchVisibility() }

        state = savedInstanceState?.getParcelable(KEY_STATE) ?: State(
            counterValue = 0,
            counterTextColor = ContextCompat.getColor(this, R.color.purple_500),
            counterVisible = true
        )
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(KEY_STATE, state)
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

    @Parcelize
    class State(
        var counterValue: Int,
        var counterTextColor: Int,
        var counterVisible: Boolean
    ) : Parcelable

//    class State(
//        var counterValue: Int,
//        var counterTextColor: Int,
//        var counterVisible: Boolean
//    ) : Parcelable {
//        constructor(parcel: Parcel) : this(
//            parcel.readInt(),
//            parcel.readInt(),
//            parcel.readByte() != 0.toByte()) {
//        }
//
//        override fun writeToParcel(parcel: Parcel, flags: Int) {
//            parcel.writeInt(counterValue)
//            parcel.writeInt(counterTextColor)
//            parcel.writeByte(if (counterVisible) 1 else 0)
//        }
//
//        override fun describeContents(): Int {
//            return 0
//        }
//
//        companion object CREATOR : Parcelable.Creator<State> {
//            override fun createFromParcel(parcel: Parcel): State {
//                return State(parcel)
//            }
//
//            override fun newArray(size: Int): Array<State?> {
//                return arrayOfNulls(size)
//            }
//        }
//    }

    companion object {
        @JvmStatic private val KEY_STATE = "STATE"
    }
}