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
    private lateinit var generateEventButton: Button

    //Create a class for the events and add all of them into an arraylist
    class Event(var title: String, var text: String) {}

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

    private lateinit var eventText1: TextView
    private lateinit var eventText2: TextView
    private lateinit var eventText3: TextView
    private lateinit var eventText4: TextView
    private lateinit var eventText5: TextView
    private lateinit var eventText6: TextView
    private lateinit var eventText7: TextView

    //Create and arraylist for all the event titles and for all of the event text
    private var allEventTitles = arrayListOf<TextView>()
    private var allEventText = arrayListOf<TextView>()

    private lateinit var noEventsText: TextView

    private val FILE_NAME = GlobalClass.FILE_NAME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        //Initialize the titles of every event
        eventTitle1 = findViewById(R.id.eventTitle1)
        eventTitle2 = findViewById(R.id.eventTitle2)
        eventTitle3 = findViewById(R.id.eventTitle3)
        eventTitle4 = findViewById(R.id.eventTitle4)
        eventTitle5 = findViewById(R.id.eventTitle5)
        eventTitle6 = findViewById(R.id.eventTitle6)
        eventTitle7 = findViewById(R.id.eventTitle7)

        //Add all of the event titles to their respective list
        allEventTitles = arrayListOf<TextView>(
            eventTitle1,
            eventTitle2,
            eventTitle3,
            eventTitle4,
            eventTitle5,
            eventTitle6,
            eventTitle7
        )

        //Initialize the text of every event
        eventText1 = findViewById(R.id.eventText1)
        eventText2 = findViewById(R.id.eventText2)
        eventText3 = findViewById(R.id.eventText3)
        eventText4 = findViewById(R.id.eventText4)
        eventText5 = findViewById(R.id.eventText5)
        eventText6 = findViewById(R.id.eventText6)
        eventText7 = findViewById(R.id.eventText7)

        //Add all of the event text to their respective list
        allEventText = arrayListOf<TextView>(
            eventText1,
            eventText2,
            eventText3,
            eventText4,
            eventText5,
            eventText6,
            eventText7
        )

        noEventsText = findViewById(R.id.noEventsText)

        var openOnce = readFromFile()?.get(0).toBoolean()
        if (!openOnce) {
            var randomEventOrderList = arrayListOf<Char>(
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
            )
            randomEventOrderList.shuffle()
            var randomEventOrderString =
                "" + randomEventOrderList[0] + randomEventOrderList[1] + randomEventOrderList[2] + randomEventOrderList[3] + randomEventOrderList[4] + randomEventOrderList[5] + randomEventOrderList[6] + randomEventOrderList[7] + randomEventOrderList[8] + randomEventOrderList[9]
            saveData(true, 0, randomEventOrderString)
        } else {
            var eventCounter = readFromFile()?.get(1)?.toInt()
            if (eventCounter != null) {
                var eventOrderInts = getShuffledEventOrder()
                if(eventCounter < 6) {
                    for (i in 0..eventCounter - 1) {
                        if (eventCounter < 7) {
                            noEventsText.visibility = View.GONE
                            allEventTitles.get(i).text = allEvents[eventOrderInts[i]].title
                            allEventText.get(i).text = allEvents[eventOrderInts[i]].text
                            allEventTitles.get(i).visibility = View.VISIBLE
                            allEventText.get(i).visibility = View.VISIBLE
                        }
                    }
                }else{
                    for (i in 0..eventCounter) {
                        if (eventCounter < 7) {
                            noEventsText.visibility = View.GONE
                            allEventTitles.get(i).text = allEvents[eventOrderInts[i]].title
                            allEventText.get(i).text = allEvents[eventOrderInts[i]].text
                            allEventTitles.get(i).visibility = View.VISIBLE
                            allEventText.get(i).visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        //Initialise the generate events button
        generateEventButton = findViewById(R.id.generateEventButton)

        generateEventButton.setOnClickListener {
            generateEvent()
        }
    }

    //When the button is pressed randomly choose an event and make it visible
    private fun generateEvent() {
        var eventCounter = readFromFile()?.get(1)?.toInt()
        if (eventCounter != null) {
            if (eventCounter < 7) {
                var eventOrderInts = getShuffledEventOrder()

                noEventsText.visibility = View.GONE
                allEventTitles[eventCounter].text = allEvents[eventOrderInts[eventCounter]].title
                allEventText[eventCounter].text = allEvents[eventOrderInts[eventCounter]].text
                allEventTitles[eventCounter].visibility = View.VISIBLE
                allEventText[eventCounter].visibility = View.VISIBLE
                if (eventCounter != 6) {
                    var eventCount = readFromFile()?.get(2)
                    if (eventCount != null) {
                        saveData(true, eventCounter + 1, eventCount)
                    }
                }
            }
        }
    }

    fun closeEvents(view: View) {
        finish()
    }

    private fun saveData(isOpen: Boolean, eventCount: Int, eventOrder: String) {
        var openOnce = isOpen
        var eventCountData = eventCount.toString()
        var eventOrder = eventOrder

        try {
            var outputStreamWriter =
                OutputStreamWriter(openFileOutput(FILE_NAME, MODE_PRIVATE))
            outputStreamWriter.write(openOnce.toString())
            outputStreamWriter.write("\n")
            outputStreamWriter.write(eventCountData)
            outputStreamWriter.write("\n")
            outputStreamWriter.write(eventOrder)
            outputStreamWriter.close()
            Log.i("text", "openOnce: " + openOnce)
            Log.i("text", "eventCounter: " + eventCountData)
            Log.i("text", "eventOrder: " + eventOrder)
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

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

    private fun getShuffledEventOrder(): ArrayList<Int> {
        var eventOrderChars = readFromFile()?.get(2)?.toCharArray()
        var eventOrderInts = arrayListOf<Int>()
        if (eventOrderChars != null) {
            for (i in eventOrderChars) {
                eventOrderInts.add(i.code - 48)
            }
        }
        return eventOrderInts
    }

}