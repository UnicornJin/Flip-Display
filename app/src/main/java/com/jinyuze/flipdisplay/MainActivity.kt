package com.jinyuze.flipdisplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinyuze.flipdisplay.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.thread

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val flipCharView = binding.flipCharView11
//
//        flipCharView.updateContent((Calendar.getInstance().get(Calendar.SECOND) % 10).digitToChar())
        schedule()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun schedule() {
        GlobalScope.launch(context = Dispatchers.IO) {
//            var count = 0
//            while (true) {
//                Log.d("Main", "updated: $count")
//                binding.flipCharView11.updateContent(count)//(count).digitToChar())
//                count += 1
//                delay(1000)
//            }
//              binding.flipCharView11.updateContent((5).digitToChar())
            delay(3000)
            binding.flipCharView11.updateContent('B')
            delay(10000)
            binding.flipCharView11.updateContent('c')

        }
    }
}