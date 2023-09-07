package com.example.fishgame

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class Events : Activity() {
    private lateinit var generateEventButton: Button
    private lateinit var closeEventsButton: Button

    //Create a class for the events and add all of them into an arraylist
    class event(var title: String, var text: String) {}

    var event1 = event("Blessing of the Goldfish", "Every player gains 10 Gold")
    var event2 = event(
        "Market Crash",
        "Every player looses half their Gold (Rounded down to their disadvantage)"
    )
    var event3 = event("Whirlpool", "Each player is teleported to the tile they want")
    var event4 = event("Strong Winds", "Every player has +2 action points on their next turn")
    var event5 = event("Fish Rain", "Every player draws two cards from deck 1")
    var event6 = event("Deck Swapping", "All inventories are swapped clockwise (Equipment too)")
    var event7 = event(
        "Blessing of the Fish God",
        "Every player has the effect of the holy fish hook for their next turn"
    )
    var event8 = event("Shark Attack!!", "Everyone discards their highest star fish")
    var event9 = event("Kraken Attack!!", "Every player is placed on the tile Mouth of the beast")
    var event10 = event("Wrath of the Fish God", "You can't use your equipment next turn")

    private var allEvents = arrayListOf<event>(
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
    private var eventCounter = 0;
    private var eventsShuffled = false

    private lateinit var sharedPrefs: SharedPreferences

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

        sharedPrefs = getSharedPreferences("EventsPrefs", Context.MODE_PRIVATE)

        if(!eventsShuffled){
            allEvents.shuffle()
            eventsShuffled = true
        }

        //Initialise the generate events button
        generateEventButton = findViewById(R.id.generateEventButton)

        generateEventButton.setOnClickListener {
            generateEvent()
        }

    }

    //When the button is pressed randomly choose an event and make it visible
    fun generateEvent() {
        if (eventCounter < 7) {
            noEventsText.visibility = View.GONE
            allEventTitles.get(eventCounter).text = allEvents[eventCounter].title
            allEventText.get(eventCounter).text = allEvents[eventCounter].text
            allEventTitles.get(eventCounter).visibility = View.VISIBLE
            allEventText.get(eventCounter).visibility = View.VISIBLE
            eventCounter++
        }
    }

    fun closeEvents(view: View) {
        finish()
    }
}