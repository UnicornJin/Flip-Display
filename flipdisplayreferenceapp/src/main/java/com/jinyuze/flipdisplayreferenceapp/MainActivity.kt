package com.jinyuze.flipdisplayreferenceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinyuze.flipdisplay.DisplayContent
import com.jinyuze.flipdisplay.FlipCharView
import com.jinyuze.flipdisplayreferenceapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewGroup: List<FlipCharView>
    private lateinit var displayGroup: MutableList<DisplayContent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewGroup = listOf(
            binding.flipCharView11, binding.flipCharView12, binding.flipCharView13, binding.flipCharView14, binding.flipCharView15, binding.flipCharView16, binding.flipCharView17, binding.flipCharView18, binding.flipCharView19, binding.flipCharView110, binding.flipCharView111, binding.flipCharView112, binding.flipCharView113, binding.flipCharView114, binding.flipCharView115,
            binding.flipCharView21, binding.flipCharView22, binding.flipCharView23, binding.flipCharView24, binding.flipCharView25, binding.flipCharView26, binding.flipCharView27, binding.flipCharView28, binding.flipCharView29, binding.flipCharView210, binding.flipCharView211, binding.flipCharView212, binding.flipCharView213, binding.flipCharView214, binding.flipCharView215,
            binding.flipCharView31, binding.flipCharView32, binding.flipCharView33, binding.flipCharView34, binding.flipCharView35, binding.flipCharView36, binding.flipCharView37, binding.flipCharView38, binding.flipCharView39, binding.flipCharView310, binding.flipCharView311, binding.flipCharView312, binding.flipCharView313, binding.flipCharView314, binding.flipCharView315,
            binding.flipCharView41, binding.flipCharView42, binding.flipCharView43, binding.flipCharView44, binding.flipCharView45, binding.flipCharView46, binding.flipCharView47, binding.flipCharView48, binding.flipCharView49, binding.flipCharView410, binding.flipCharView411, binding.flipCharView412, binding.flipCharView413, binding.flipCharView414, binding.flipCharView415,
            binding.flipCharView51, binding.flipCharView52, binding.flipCharView53, binding.flipCharView54, binding.flipCharView55, binding.flipCharView56, binding.flipCharView57, binding.flipCharView58, binding.flipCharView59, binding.flipCharView510, binding.flipCharView511, binding.flipCharView512, binding.flipCharView513, binding.flipCharView514, binding.flipCharView515,
            binding.flipCharView61, binding.flipCharView62, binding.flipCharView63, binding.flipCharView64, binding.flipCharView65, binding.flipCharView66, binding.flipCharView67, binding.flipCharView68, binding.flipCharView69, binding.flipCharView610, binding.flipCharView611, binding.flipCharView612, binding.flipCharView613, binding.flipCharView614, binding.flipCharView615,
            binding.flipCharView71, binding.flipCharView72, binding.flipCharView73, binding.flipCharView74, binding.flipCharView75, binding.flipCharView76, binding.flipCharView77, binding.flipCharView78, binding.flipCharView79, binding.flipCharView710, binding.flipCharView711, binding.flipCharView712, binding.flipCharView713, binding.flipCharView714, binding.flipCharView715,
        )

        displayGroup = mutableListOf<DisplayContent>()

        val welcomeMessage = DisplayContent(15, 7)
        welcomeMessage.setRow(3, "  Flip Display ")

        val name = DisplayContent(15, 7)
        name.setRow(2, " Author:")
        name.setRow(3, "   UnicornJin")

        val date = DisplayContent(15, 7)
        date.setRow(2, " Date:")
        date.setRow(3, "   2022/12/25  ")

        val christmas = DisplayContent(15, 7)
        christmas.setRow(2, "     Merry     ")
        christmas.setRow(3, "   Christmas   ")

        displayGroup.add(welcomeMessage)
        displayGroup.add(name)
        displayGroup.add(date)
        displayGroup.add(christmas)

        schedule()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun schedule() {
        GlobalScope.launch(context = Dispatchers.IO) {
            delay(1000)

            for (item in displayGroup) {
                for (i in viewGroup.indices) {
                    val row = i / 15
                    val column = i % 15
                    viewGroup[i].updateContent(item.getCharAt(row, column))
                }
                delay(9500)
            }
        }
    }
}