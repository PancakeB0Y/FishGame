package com.example.fishgame

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import kotlin.random.Random


class Events : Activity() {
    //Create a class for the events and add all of them into an arraylist
    class Event(var title: String, var text: String) {}

    //Create each event and add them to a list
    private var event1 = Event("Blessing of the Goldfish", "Every player gains 10 Gold")
    private var event2 = Event(
        "Market Crash",
        "Every player looses half their Gold (Rounded down to their disadvantage)"
    )
    private var event3 = Event("Whirlpool", "Each player is teleported to the tile they want")
    private var event4 =
        Event("Strong Winds", "Every player has +2 action points on their next turn")
    private var event5 = Event("Fish Rain", "Every player draws two cards from deck 1")
    private var event6 =
        Event("Deck Swapping", "All inventories are swapped clockwise (Equipment too)")
    private var event7 = Event(
        "Blessing of the Fish God",
        "Every player has the effect of the holy fish hook for their next turn"
    )
    private var event8 = Event("Shark Attack!!", "Everyone discards their highest star fish")
    private var event9 =
        Event("Kraken Attack!!", "Every player is placed on the tile Mouth of the beast")
    private var event10 = Event("Wrath of the Fish God", "You can't use your equipment next turn")

    private val allEvents = arrayListOf<Event>(
        event1, event2, event3, event4, event5, event6, event7, event8, event9, event10
    )

    //Create variables for every needed element of the app
    private lateinit var eventTitle1: TextView
    private lateinit var eventTitle2: TextView
    private lateinit var eventTitle3: TextView
    private lateinit var eventTitle4: TextView
    private lateinit var eventTitle5: TextView
    private lateinit var eventTitle6: TextView
    private lateinit var eventTitle7: TextView
    private lateinit var eventTitle8: TextView
    private lateinit var eventTitle9: TextView
    private lateinit var eventTitle10: TextView

    private lateinit var eventText1: TextView
    private lateinit var eventText2: TextView
    private lateinit var eventText3: TextView
    private lateinit var eventText4: TextView
    private lateinit var eventText5: TextView
    private lateinit var eventText6: TextView
    private lateinit var eventText7: TextView
    private lateinit var eventText8: TextView
    private lateinit var eventText9: TextView
    private lateinit var eventText10: TextView

    private lateinit var generateEventButton: Button

    private lateinit var skipButton1: Button
    private lateinit var skipButton2: Button
    private lateinit var skipButton3: Button
    private lateinit var skipButton4: Button
    private lateinit var skipButton5: Button

    //Create an arraylist for all event titles and for all event text
    private var allEventTitles = arrayListOf<TextView>()
    private var allEventText = arrayListOf<TextView>()

    //Initialize eventData.txt
    private val FILE_NAME = GlobalClass.FILE_NAME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        //Initialize every needed element of the app
        eventTitle1 = findViewById(R.id.eventTitle1)
        eventTitle2 = findViewById(R.id.eventTitle2)
        eventTitle3 = findViewById(R.id.eventTitle3)
        eventTitle4 = findViewById(R.id.eventTitle4)
        eventTitle5 = findViewById(R.id.eventTitle5)
        eventTitle6 = findViewById(R.id.eventTitle6)
        eventTitle7 = findViewById(R.id.eventTitle7)
        eventTitle8 = findViewById(R.id.eventTitle8)
        eventTitle9 = findViewById(R.id.eventTitle9)
        eventTitle10 = findViewById(R.id.eventTitle10)

        eventText1 = findViewById(R.id.eventText1)
        eventText2 = findViewById(R.id.eventText2)
        eventText3 = findViewById(R.id.eventText3)
        eventText4 = findViewById(R.id.eventText4)
        eventText5 = findViewById(R.id.eventText5)
        eventText6 = findViewById(R.id.eventText6)
        eventText7 = findViewById(R.id.eventText7)
        eventText8 = findViewById(R.id.eventText8)
        eventText9 = findViewById(R.id.eventText9)
        eventText10 = findViewById(R.id.eventText10)

        generateEventButton = findViewById(R.id.generateEventButton)

        skipButton1 = findViewById(R.id.skipButton1)
        skipButton2 = findViewById(R.id.skipButton2)
        skipButton3 = findViewById(R.id.skipButton3)
        skipButton4 = findViewById(R.id.skipButton4)
        skipButton5 = findViewById(R.id.skipButton5)

        //Add all of the event titles to their respective list
        allEventTitles = arrayListOf<TextView>(
            eventTitle1,
            eventTitle2,
            eventTitle3,
            eventTitle4,
            eventTitle5,
            eventTitle6,
            eventTitle7,
            eventTitle8,
            eventTitle9,
            eventTitle10
        )

        //Add all of the event text to their respective list
        allEventText = arrayListOf<TextView>(
            eventText1,
            eventText2,
            eventText3,
            eventText4,
            eventText5,
            eventText6,
            eventText7,
            eventText8,
            eventText9,
            eventText10
        )

        //Check if the events that has been opened from eventsData.txt
        var openOnce = readFromFile()?.get(0).toBoolean()
        if (!openOnce) {
            //If it has not been opened -> randomize order of the events and add that order to eventsData.txt
            var randomEventOrderList = arrayListOf<Char>(
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
            )
            randomEventOrderList.shuffle()
            var randomEventOrderString =
                "" + randomEventOrderList[0] + randomEventOrderList[1] + randomEventOrderList[2] + randomEventOrderList[3] + randomEventOrderList[4] + randomEventOrderList[5] + randomEventOrderList[6] + randomEventOrderList[7] + randomEventOrderList[8] + randomEventOrderList[9]
            saveData(true, 0, randomEventOrderString)
        } else {
            //If it has been opened -> make all previously generated events visible
            var eventCounter = readFromFile()?.get(1)?.toInt()
            if (eventCounter != null) {
                var eventOrderStrings = getShuffledEventOrder()
                for (i in 0..eventCounter - 1) {
                    var curEvent = eventOrderStrings[i]
                    if (curEvent != "s") {
                        allEventTitles.get(i).text = allEvents[eventOrderStrings[i].toInt()].title
                        allEventText.get(i).text = allEvents[eventOrderStrings[i].toInt()].text
                        allEventTitles.get(i).visibility = View.VISIBLE
                        allEventText.get(i).visibility = View.VISIBLE
                        if (i == 0) {
                            skipButton1.isEnabled = false
                        } else if (i == 1) {
                            skipButton2.isEnabled = false
                        } else if (i == 2) {
                            skipButton3.isEnabled = false
                        } else if (i == 3) {
                            skipButton4.isEnabled = false
                        } else if (i == 4) {
                            skipButton5.isEnabled = false
                        }
                    } else {
                        if (i == 0) {
                            skipButton1.text = "SKIPPED"
                            skipButton1.isEnabled = false
                        } else if (i == 1) {
                            skipButton2.text = "SKIPPED"
                            skipButton2.isEnabled = false
                        } else if (i == 2) {
                            skipButton3.text = "SKIPPED"
                            skipButton3.isEnabled = false
                        } else if (i == 3) {
                            skipButton4.text = "SKIPPED"
                            skipButton4.isEnabled = false
                        } else if (i == 4) {
                            skipButton5.text = "SKIPPED"
                            skipButton5.isEnabled = false
                        }
                    }
                }
            }
        }

        //Add a listener for the each button
        generateEventButton.setOnClickListener {
            generateEvent()
        }

        skipButton1.setOnClickListener {
            skipEvent(0)
            skipButton1.text = "SKIPPED"
            skipButton1.isEnabled = false
        }

        skipButton2.setOnClickListener {
            skipEvent(1)
            skipButton2.text = "SKIPPED"
            skipButton2.isEnabled = false
        }

        skipButton3.setOnClickListener {
            skipEvent(2)
            skipButton3.text = "SKIPPED"
            skipButton3.isEnabled = false
        }

        skipButton4.setOnClickListener {
            skipEvent(3)
            skipButton4.text = "SKIPPED"
            skipButton4.isEnabled = false
        }

        skipButton5.setOnClickListener {
            skipEvent(4)
            skipButton5.text = "SKIPPED"
            skipButton5.isEnabled = false
        }
    }

    //On button press -> make the next event from the randomized order visible
    private fun generateEvent() {
        var eventCount = readFromFile()?.get(1)?.toInt()
        if (eventCount != null) {
            if (eventCount < 10) {
                var eventOrderStrings = getShuffledEventOrder()
                var eventOrder = readFromFile()?.get(2)
                allEventTitles[eventCount].text =
                    allEvents[eventOrderStrings[eventCount].toInt()].title
                allEventText[eventCount].text =
                    allEvents[eventOrderStrings[eventCount].toInt()].text
                allEventTitles[eventCount].visibility = View.VISIBLE
                allEventText[eventCount].visibility = View.VISIBLE
                if (eventCount == 0) {
                    skipButton1.isEnabled = false
                } else if (eventCount == 1) {
                    skipButton2.isEnabled = false
                } else if (eventCount == 2) {
                    skipButton3.isEnabled = false
                } else if (eventCount == 3) {
                    skipButton4.isEnabled = false
                } else if (eventCount == 4) {
                    skipButton5.isEnabled = false
                }
                if (eventOrder != null) {
                    saveData(true, eventCount + 1, eventOrder)
                }
            }
        }
    }

    //Skip given event in the event order
    private fun skipEvent(eventNumber: Int) {
        var eventOrderChars = readFromFile()?.get(2)?.toCharArray()
        eventOrderChars?.set(eventNumber, 's')
        var eventOrder = eventOrderChars?.let { String(it) }

        var eventCount = readFromFile()?.get(1)?.toInt()

        if (eventOrder != null) {
            if (eventCount != null) {
                saveData(true, eventCount + 1, eventOrder)
            }
        }

    }

    //Exit the events tab
    fun closeEvents(view: View) {
        finish()
    }

    //Save the given data to eventsData.txt
    //openOnce = true when the events tab is opened for the first time
    //eventCount -> the number of generated events
    //eventOrder -> the random order in which the events are generated
    private fun saveData(openOnce: Boolean, eventCount: Int, eventOrder: String) {
        val openOnce = openOnce
        val eventCount = eventCount.toString()
        val eventOrder = eventOrder

        try {
            var outputStreamWriter =
                OutputStreamWriter(openFileOutput(FILE_NAME, MODE_PRIVATE))
            outputStreamWriter.write(openOnce.toString())
            outputStreamWriter.write("\n")
            outputStreamWriter.write(eventCount)
            outputStreamWriter.write("\n")
            outputStreamWriter.write(eventOrder)
            outputStreamWriter.close()
            //Log.i("text", "opened once: " + openOnce)
            Log.i("text", "event count: " + eventCount)
            Log.i("text", "event order: " + eventOrder)
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    //Return the data from eventsData.txt
    private fun readFromFile(): Array<String>? {
        var text = arrayOf<String>()
        try {
            val inputStream: InputStream? = openFileInput(FILE_NAME)
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String? = ""
                val stringBuilder = StringBuilder()
                while (bufferedReader.readLine().also { receiveString = it } != null) {
                    stringBuilder.append("\n").append(receiveString)
                }
                inputStream.close()
                text = stringBuilder.toString().split("\r?\n|\r".toRegex()).toTypedArray()
                text[0] = text[1]
                text[1] = text[2]
                text[2] = text[3]
                text[3] = null.toString()
            }
        } catch (e: FileNotFoundException) {
            Log.e("login activity", "File not found: $e")
        } catch (e: IOException) {
            Log.e("login activity", "Can not read file: $e")
        }

        return text
    }

    //Return the order of events in integers
    private fun getShuffledEventOrder(): ArrayList<String> {
        var eventOrderChars = readFromFile()?.get(2)?.toCharArray()
        var eventOrderString = arrayListOf<String>()

        if (eventOrderChars != null) {
            for (i in eventOrderChars) {
                eventOrderString.add(i.toString())

            }
        }

        return eventOrderString
    }

}