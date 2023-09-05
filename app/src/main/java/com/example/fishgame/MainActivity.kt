package com.example.fishgame

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.ComponentActivity

import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private lateinit var goal1Count: TextView
    private lateinit var goal2Count: TextView
    private lateinit var goal3Count: TextView

    private lateinit var goal1Creature: TextView
    private lateinit var goal2Creature: TextView
    private lateinit var goal3Creature: TextView

    private lateinit var difficultySeekBar: SeekBar

    private var fishKind = arrayListOf("Tuna", "Salmon", "Shark", "Crab", "Octopus", "Jellyfish")
    private var fishCount = List(20) { Random.nextInt(2, 7) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goal1Count = findViewById(R.id.goal1Count)
        goal2Count = findViewById(R.id.goal2Count)
        goal3Count = findViewById(R.id.goal3Count)

        goal1Creature = findViewById(R.id.goal1Creature)
        goal2Creature = findViewById(R.id.goal2Creature)
        goal3Creature = findViewById(R.id.goal3Creature)

        difficultySeekBar = findViewById(R.id.difficultySeekBar)

        fishKind.shuffle()
        fishCount = fishCount.shuffled()

        goal1Count.text = fishCount[0].toString()
        goal2Count.text = fishCount[1].toString()
        goal3Count.text = fishCount[2].toString()

        goal1Creature.text = fishKind[0]
        goal2Creature.text = fishKind[1]
        goal3Creature.text = fishKind[2]

        difficultySeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                goal1Count.text = (fishCount[0] * (p1 + 1)).toString()
                goal2Count.text = (fishCount[1] * (p1 + 1)).toString()
                goal3Count.text = (fishCount[2] * (p1 + 1) ).toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }
}