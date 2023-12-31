package com.example.fishgame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Collections
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    //Create variables for every needed element of the app
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
    private lateinit var rulesButton: Button

    private lateinit var difficultySeekBar: SeekBar

    //Create a list with every fish
    private var allFish = arrayListOf(
        "☆Cod",
        "☆Salmon",
        "☆Tuna",
        "☆Shrimp",
        "☆Starfish",
        "☆Sturgeon",
        "☆☆Jellyfish",
        "☆☆Eel",
        "☆☆Sea urchin",
        "☆☆Turtle",
        "☆☆Moonfish",
        "☆☆Sturgeon",
        "☆☆☆Shark",
        "☆☆☆Anglerfish",
        "☆☆☆Giant squid",
        "☆☆☆Undead fish",
        "☆☆☆Magic fish",
        "☆☆☆Sturgeon"
    )

    //Create arraylists with the fish from each tier
    private var tier1Fish =
        arrayListOf("☆Cod", "☆Salmon", "☆Tuna", "☆Shrimp", "☆Starfish", "☆Sturgeon")
    private var tier2Fish =
        arrayListOf("☆☆Jellyfish", "☆☆Eel", "☆☆Sea urchin", "☆☆Turtle", "☆☆Moonfish", "☆☆Sturgeon")
    private var tier3Fish = arrayListOf(
        "☆☆☆Shark",
        "☆☆☆Anglerfish",
        "☆☆☆Giant squid",
        "☆☆☆Undead fish",
        "☆☆☆Magic fish",
        "☆☆☆Sturgeon"
    )

    //Initialize eventData.txt
    private val FILE_NAME = GlobalClass.FILE_NAME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize every needed element of the app
        objective1Fish1 = findViewById(R.id.objective1Fish1)
        objective1Fish2 = findViewById(R.id.objective1Fish2)
        objective1Fish3 = findViewById(R.id.objective1Fish3)
        objective1Count1 = findViewById(R.id.objective1Count1)
        objective1Count2 = findViewById(R.id.objective1Count2)
        objective1Count3 = findViewById(R.id.objective1Count3)

        objective2Fish1 = findViewById(R.id.objective2Fish1)
        objective2Fish2 = findViewById(R.id.objective2Fish2)
        objective2Fish3 = findViewById(R.id.objective2Fish3)
        objective2Count1 = findViewById(R.id.objective2Count1)
        objective2Count2 = findViewById(R.id.objective2Count2)
        objective2Count3 = findViewById(R.id.objective2Count3)

        objective3Fish1 = findViewById(R.id.objective3Fish1)
        objective3Fish2 = findViewById(R.id.objective3Fish2)
        objective3Fish3 = findViewById(R.id.objective3Fish3)
        objective3Count1 = findViewById(R.id.objective3Count1)
        objective3Count2 = findViewById(R.id.objective3Count2)
        objective3Count3 = findViewById(R.id.objective3Count3)

        objective2Text = findViewById(R.id.objective2Text)
        objective3Text = findViewById(R.id.objective3Text)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        rulesButton = findViewById(R.id.rulesButton)

        difficultySeekBar = findViewById(R.id.difficultySeekBar)

        var tier3Count = 0;
        tier3Fish.shuffle()

        //Randomize the first objective fish
        //Objective 1 -  (1☆ and 2☆☆) or (1☆, 1☆☆, 1☆☆☆)
        tier1Fish.shuffle()
        objective1Fish1.text = tier1Fish[0]
        objective1Count1.text = "2"

        tier2Fish.shuffle()
        objective1Fish2.text = tier2Fish[1]
        objective1Count2.text = "1"
        if (Math.random() > 0.5) {
            objective1Fish3.text = tier2Fish[0]
            objective1Count3.text = "1"
        } else {
            objective1Fish3.text = tier3Fish[tier3Count]
            objective1Count3.text = "1"
            tier3Count++
        }

        //Randomize the second objective fish
        //Objective 2 - (1☆, 1☆☆ and 1☆☆☆), (2☆ and 1☆☆☆)
        tier1Fish.shuffle()
        objective2Fish1.text = tier1Fish[0]
        objective2Count1.text = "2"

        objective2Fish3.text = tier3Fish[tier3Count]
        objective2Count3.text = "1"
        tier3Count++
        if (Math.random() > 0.5) {
            objective2Fish2.text = tier1Fish[1]
            objective2Count2.text = "2"
        } else {
            tier2Fish.shuffle()
            objective2Fish2.text = tier2Fish[0]
            objective2Count2.text = "2"
        }

        //Randomize the third objective fish
        //Objective 3 - (1☆, 1☆☆ and 1☆☆☆) or (1☆☆ and 2☆☆☆)
        objective3Fish3.text = tier3Fish[tier3Count]
        objective3Count3.text = "1"
        tier3Count++
        val randNumber = Random.nextInt(0, 3)
        if (randNumber == 0) {
            tier1Fish.shuffle()
            objective3Fish1.text = tier1Fish[0]
            objective3Count1.text = "2"
            tier2Fish.shuffle()
            objective3Fish2.text = tier2Fish[0]
            objective3Count2.text = "2"
        } else if (randNumber == 1) {
            tier1Fish.shuffle()
            objective3Fish1.text = tier1Fish[0]
            objective3Count1.text = "3"
            tier2Fish.shuffle()
            objective3Fish2.text = tier2Fish[0]
            objective3Count2.text = "2"
        } else {
            tier2Fish.shuffle()
            objective3Fish1.text = tier2Fish[0]
            objective3Count1.text = "1"

            objective3Fish2.text = tier3Fish[tier3Count]
            objective3Count2.text = "1"
            tier3Count++
        }

        //Change the fish count required when sliding the seek bar
        difficultySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            //Save all objective counts at the easiest difficulty in a list
            private val initialCounts =
                arrayListOf(
                    objective1Count1.text.toString().toInt(),
                    objective1Count2.text.toString().toInt(),
                    objective1Count3.text.toString().toInt(),
                    objective2Count1.text.toString().toInt(),
                    objective2Count2.text.toString().toInt(),
                    objective2Count3.text.toString().toInt(),
                    objective3Count1.text.toString().toInt(),
                    objective3Count2.text.toString().toInt(),
                    objective3Count3.text.toString().toInt()
                )

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                objective1Count1.text =
                    (initialCounts[0] * (p1 + 1)).toString()
                objective1Count2.text =
                    (initialCounts[1] * (p1 + 1)).toString()
                objective1Count3.text =
                    (initialCounts[2] * (p1 + 1)).toString()

                objective2Count1.text =
                    (initialCounts[3] * (p1 + 1)).toString()
                objective2Count2.text =
                    (initialCounts[4] * (p1 + 1)).toString()
                objective2Count3.text =
                    (initialCounts[5] * (p1 + 1)).toString()

                objective3Count1.text =
                    (initialCounts[6] * (p1 + 1)).toString()
                objective3Count2.text =
                    (initialCounts[7] * (p1 + 1)).toString()
                objective3Count3.text =
                    (initialCounts[8] * (p1 + 1)).toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        //When the buttons are pressed -> change their color and make the next objectives visible
        button1.setOnClickListener {
            //When the selected state changes ->
            //the colors of the button change from res/color/button_background_color.xml and res/color/button_text_color.xml
            button1.isSelected = true
            complete1()
        }

        button2.setOnClickListener {
            button2.isSelected = true
            complete2()
        }

        button3.setOnClickListener {
            button3.isSelected = true
            complete3()
        }

        saveData()
    }

    //Change the text on the button and make the next objective visible
    private fun complete1() {
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

    private fun complete2() {
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

    private fun complete3() {
        button3.text = "completed"
    }

    //Reset the app
    fun reset(view: View) {
        recreate()
    }

    //Open the rules tab
    fun openRules(view: View) {
        val intent = Intent(this@MainActivity, Rules::class.java)
        startActivity(intent)
    }

    //Open the events tab
    fun openEvents(view: View) {
        val intent = Intent(this@MainActivity, Events::class.java)
        startActivity(intent)
    }

    //Save the initial data needed for the events in eventsData.txt
    private fun saveData() {
        var openOnce = false
        var eventCount = "0"
        var eventOrder = "0123456789"

        try {
            var outputStreamWriter =
                OutputStreamWriter(openFileOutput(FILE_NAME, MODE_PRIVATE))
            outputStreamWriter.write(openOnce.toString())
            outputStreamWriter.write("\n")
            outputStreamWriter.write(eventCount)
            outputStreamWriter.write("\n")
            outputStreamWriter.write(eventOrder)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}