package com.example.fishgame

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    //Create variables for every element of the app
    private lateinit var objective1Fish1: TextView
    private lateinit var objective1Fish2: TextView
    private lateinit var objective1Fish3: TextView
    private lateinit var objective1Count1: TextView
    private lateinit var objective1Count2: TextView
    private lateinit var objective1Count3: TextView

    private lateinit var objective2Fish1: TextView
    private lateinit var objective2Fish2: TextView
    private lateinit var objective2Fish3: TextView
    private lateinit var objective2Count1: TextView
    private lateinit var objective2Count2: TextView
    private lateinit var objective2Count3: TextView

    private lateinit var objective3Fish1: TextView
    private lateinit var objective3Fish2: TextView
    private lateinit var objective3Fish3: TextView
    private lateinit var objective3Count1: TextView
    private lateinit var objective3Count2: TextView
    private lateinit var objective3Count3: TextView

    private lateinit var objective2Text: TextView
    private lateinit var objective3Text: TextView

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    private lateinit var difficultySeekBar: SeekBar

    private var allFish = arrayListOf(
        "Cod", "Salmon", "Tuna", "Shrimp", "Sturgeon", "Starfish",
        "Jellyfish", "Eel", "Sea urchin", "Turtle", "Moonfish",
        "Shark", "Anglerfish", "Giant squid", "Undead fish", "Magic fish")

    //Create arraylists with the fish from each tier
    private var tier1Fish = arrayListOf("☆Cod", "☆Salmon", "☆Tuna", "☆Shrimp", "☆Starfish", "☆Sturgeon")
    private var tier2Fish = arrayListOf("☆☆Jellyfish", "☆☆Eel", "☆☆Sea urchin", "☆☆Turtle", "☆☆Moonfish", "☆☆Sturgeon")
    private var tier3Fish = arrayListOf("☆☆☆Shark", "☆☆☆Anglerfish", "☆☆☆Giant squid", "☆☆☆Undead fish", "☆☆☆Magic fish", "☆☆☆Sturgeon")

    //Create arraylists with random counts for each fish
    private var tier1Count = List(20) { Random.nextInt(2, 4) }
    private var tier2Count = List(20) { Random.nextInt(2, 4) }
    private var tier3Count = List(20) { Random.nextInt(2, 3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize the first objective fish
        objective1Fish1 = findViewById(R.id.objective1Fish1)
        objective1Fish2 = findViewById(R.id.objective1Fish2)
        objective1Fish3 = findViewById(R.id.objective1Fish3)
        objective1Count1 = findViewById(R.id.objective1Count1)
        objective1Count2 = findViewById(R.id.objective1Count2)
        objective1Count3 = findViewById(R.id.objective1Count3)

        //Randomize the first objective fish
        tier1Fish.shuffle()
        tier2Fish.shuffle()
        tier3Fish.shuffle()
        objective1Fish1.text = tier1Fish[0]
        objective1Fish2.text = tier2Fish[1]
        objective1Fish3.text = tier3Fish[2]

        tier1Count = tier1Count.shuffled()
        tier2Count = tier2Count.shuffled()
        tier3Count = tier3Count.shuffled()
        objective1Count1.text = tier1Count[0].toString()
        objective1Count2.text = tier2Count[1].toString()
        objective1Count3.text = tier3Count[2].toString()

        //Initialize the second objective fish
        objective2Fish1 = findViewById(R.id.objective2Fish1)
        objective2Fish2 = findViewById(R.id.objective2Fish2)
        objective2Fish3 = findViewById(R.id.objective2Fish3)
        objective2Count1 = findViewById(R.id.objective2Count1)
        objective2Count2 = findViewById(R.id.objective2Count2)
        objective2Count3 = findViewById(R.id.objective2Count3)

        //Randomize the second objective fish
        tier1Fish.shuffle()
        tier2Fish.shuffle()
        tier3Fish.shuffle()
        objective2Fish1.text = tier1Fish[0]
        objective2Fish2.text = tier2Fish[1]
        objective2Fish3.text = tier3Fish[2]

        tier1Count = tier1Count.shuffled()
        tier2Count = tier2Count.shuffled()
        tier3Count = tier3Count.shuffled()
        objective2Count1.text = tier1Count[0].toString()
        objective2Count2.text = tier2Count[1].toString()
        objective2Count3.text = tier3Count[2].toString()

        //Initialize the third objective fish
        objective3Fish1 = findViewById(R.id.objective3Fish1)
        objective3Fish2 = findViewById(R.id.objective3Fish2)
        objective3Fish3 = findViewById(R.id.objective3Fish3)
        objective3Count1 = findViewById(R.id.objective3Count1)
        objective3Count2 = findViewById(R.id.objective3Count2)
        objective3Count3 = findViewById(R.id.objective3Count3)

        //Randomize the third objective fish
        tier1Fish.shuffle()
        tier2Fish.shuffle()
        tier3Fish.shuffle()
        objective2Fish1.text = tier1Fish[0]
        objective2Fish2.text = tier2Fish[1]
        objective2Fish3.text = tier3Fish[2]

        tier1Count = tier1Count.shuffled()
        tier2Count = tier2Count.shuffled()
        tier3Count = tier3Count.shuffled()
        objective2Count1.text = tier1Count[0].toString()
        objective2Count2.text = tier2Count[1].toString()
        objective2Count3.text = tier3Count[2].toString()

        //Initialize Objectives text
        objective2Text = findViewById(R.id.objective2Text)
        objective3Text = findViewById(R.id.objective3Text)

        //Initialize buttons
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        //Initialize the difficulty seek bar
        difficultySeekBar = findViewById(R.id.difficultySeekBar)

        //Change the fish count required when sliding the seek bar
        difficultySeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                objective1Count1.text = (tier1Count[0] * (p1 + 1)).toString()
                objective1Count2.text = (tier2Count[0] * (p1 + 1)).toString()
                objective1Count3.text = (tier3Count[0] * (p1 + 1)).toString()

                objective2Count1.text = (tier1Count[0] * (p1 + 1)).toString()
                objective2Count2.text = (tier2Count[0] * (p1 + 1)).toString()
                objective2Count3.text = (tier3Count[0] * (p1 + 1)).toString()

                objective3Count1.text = (tier1Count[0] * (p1 + 1)).toString()
                objective3Count2.text = (tier2Count[0] * (p1 + 1)).toString()
                objective3Count3.text = (tier3Count[0] * (p1 + 1)).toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        //When the buttons are pressed -> changes their color and makes the next objectives visible
        button1.setOnClickListener{
            //When the selected state changes ->
            //the colors of the button change from res/color/button_background_color.xml and res/color/button_text_color.xml
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

    //Changes the text on the button and makes the next objective visible
    public fun complete1(){
        objective2Text.visibility = View.VISIBLE
        objective2Fish1.visibility = View.VISIBLE
        objective2Fish2.visibility = View.VISIBLE
        objective2Fish3.visibility = View.VISIBLE
        objective2Count1.visibility = View.VISIBLE
        objective2Count2.visibility = View.VISIBLE
        objective2Count3.visibility = View.VISIBLE
        button2.visibility = View.VISIBLE
        button2.isClickable = true
        button1.text = "completed"
    }

    public fun complete2(){
        objective3Text.visibility = View.VISIBLE
        objective3Fish1.visibility = View.VISIBLE
        objective3Fish2.visibility = View.VISIBLE
        objective3Fish3.visibility = View.VISIBLE
        objective3Count1.visibility = View.VISIBLE
        objective3Count2.visibility = View.VISIBLE
        objective3Count3.visibility = View.VISIBLE
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