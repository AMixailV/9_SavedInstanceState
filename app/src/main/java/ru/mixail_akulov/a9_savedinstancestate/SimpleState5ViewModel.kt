package ru.mixail_akulov.a9_savedinstancestate

import android.graphics.Color
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random

class SimpleState5ViewModel: ViewModel() {

    private val stateLiveData = MutableLiveData<State>()
    val state: LiveData<State> get() = stateLiveData

    fun initState(state: State) {
        stateLiveData.value = state
    }

    fun increment() {
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            counterValue = oldState.counterValue + 1
        )
    }

    fun setRandomColor() {
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            counterTextColor = Color.rgb(
                Random.nextInt(256),
                Random.nextInt(256),
                Random.nextInt(256)
            )
        )
    }

    fun switchVisibility() {
        val oldState = stateLiveData.value
        stateLiveData.value = oldState?.copy(
            counterVisible = !oldState.counterVisible
        )
    }
}

@Parcelize
data class State(
    val counterValue: Int,
    val counterTextColor: Int,
    val counterVisible: Boolean
) : Parcelable