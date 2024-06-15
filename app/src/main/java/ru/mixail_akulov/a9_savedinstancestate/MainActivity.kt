package ru.mixail_akulov.a9_savedinstancestate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mixail_akulov.a9_savedinstancestate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.activity1Button.setOnClickListener {
            startActivity(Intent(this, SimpleState1Activity::class.java))
        }
        binding.activity2Button.setOnClickListener {
            startActivity(Intent(this, SimpleState2Activity::class.java))
        }

        binding.activity3Button.setOnClickListener {
            startActivity(Intent(this, SimpleState3Activity::class.java))
        }

        binding.activity4Button.setOnClickListener {
            startActivity(Intent(this, SimpleState4Activity::class.java))
        }

        binding.activity5Button.setOnClickListener {
            startActivity(Intent(this, SimpleState5Activity::class.java))
        }
    }
}