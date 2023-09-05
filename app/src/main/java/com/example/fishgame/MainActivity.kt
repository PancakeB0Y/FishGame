package com.example.fishgame

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    private lateinit var objective2Text: TextView
    private lateinit var objective3Text: TextView

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    private lateinit var objective1Count: TextView
    private lateinit var objective2Count: TextView
    private lateinit var objective3Count: TextView

    private lateinit var objective1Fish: TextView
    private lateinit var objective2Fish: TextView
    private lateinit var objective3Fish: TextView

    private lateinit var difficultySeekBar: SeekBar

    private var allFish = arrayListOf(
        "Cod", "Salmon", "Tuna", "Shrimp", "Sturgeon", "Starfish",
        "Jellyfish", "Eel", "Sea urchin", "Turtle", "Moonfish",
        "Shark", "Anglerfish", "Giant squid", "Undead fish", "Magic fish")

    private var tier1Fish = arrayListOf("Cod", "Salmon", "Tuna", "Shrimp", "Sturgeon", "Starfish")
    private var tier2Fish = arrayListOf("Jellyfish", "Eel", "Sea urchin", "Turtle", "Moonfish")
    private var tier3Fish = arrayListOf("Shark", "Anglerfish", "Giant squid", "Undead fish", "Magic fish")

    private var tier1Count = List<Int>(20) { Random.nextInt(2, 4) }
    private var tier2Count = List(20) { Random.nextInt(2, 4) }
    private var tier3Count = List(20) { Random.nextInt(2, 3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        objective2Text = findViewById(R.id.objective2Text)
        objective3Text = findViewById(R.id.objective3Text)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        objective1Count = findViewById(R.id.objective1Count)
        objective2Count = findViewById(R.id.objective2Count)
        objective3Count = findViewById(R.id.objective3Count)

        objective1Fish = findViewById(R.id.objective1Fish)
        objective2Fish = findViewById(R.id.objective2Fish)
        objective3Fish = findViewById(R.id.objective3Fish)

        difficultySeekBar = findViewById(R.id.difficultySeekBar)

        tier1Count = tier1Count.shuffled()
        tier2Count = tier2Count.shuffled()
        tier3Count = tier3Count.shuffled()
        objective1Count.text = tier1Count[0].toString()
        objective2Count.text = tier2Count[1].toString()
        objective3Count.text = tier3Count[2].toString()

        tier1Fish.shuffle()
        tier2Fish.shuffle()
        tier3Fish.shuffle()
        objective1Fish.text = tier1Fish[0]
        objective2Fish.text = tier2Fish[1]
        objective3Fish.text = tier3Fish[2]

        difficultySeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                objective1Count.text = (tier1Count[0] * (p1 + 1)).toString()
                objective2Count.text = (tier2Count[0] * (p1 + 1)).toString()
                objective3Count.text = (tier3Count[0] * (p1 + 1)).toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })


        button1.setOnClickListener{
            button1.isSelected = true
            complete1()
        }

        button2.setOnClickListener{
            button2.isSelected = true
            complete2()
        }

        button3.setOnClickListener{
            button3.isSelected = true
            complete3()
        }
    }

    public fun complete1(){
        objective2Text.visibility = View.VISIBLE
        objective2Count.visibility = View.VISIBLE
        objective2Fish.visibility = View.VISIBLE
        button2.visibility = View.VISIBLE
        button2.isClickable = true
        button1.text = "completed"
    }

    public fun complete2(){
        objective3Text.visibility = View.VISIBLE
        objective3Count.visibility = View.VISIBLE
        objective3Fish.visibility = View.VISIBLE
        button3.visibility = View.VISIBLE
        button3.isClickable = true
        button2.text = "completed"
    }

    public fun complete3(){
        button3.text = "completed"
    }

    public fun reset(View: View){
        recreate()
    }
}