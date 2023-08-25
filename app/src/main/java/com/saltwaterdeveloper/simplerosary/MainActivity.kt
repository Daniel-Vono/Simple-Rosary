package com.saltwaterdeveloper.simplerosary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    companion object {
        //The number of total beads
        const val TOTAL_BEADS = 61

        //Represents the starting prayer direction
        const val STARTING_DIRECTION: Boolean = true

        //Stores the number of times the user has to press the wrong button to be notified
        const val NUM_TRIES_BEFORE_ALERT_START: Int = 5

        //Stores the indices of the last bead
        val LAST_BEAD = arrayOf(60, 7)

        //Stores an array of beads and the current bead index
        val beads = Array<Bead?>(TOTAL_BEADS) { null }
        var currentBead = Bead.BEGINNING

        //Stores the direction of prayer
        var isPrayingCounterClockwise: Boolean = STARTING_DIRECTION

        //Stores the prayer text view and the index used to choose what prayer is shown
        var prayerTextView: WeakReference<TextView>? = null
        var prayerTextIndex: Int = Bead.EMPTY

        //Represents if the user is viewing the license
        var isViewingLicense: Boolean = false

        //Store the number of attempts before the user is notified about which bead to press
        var numTriesBeforeAlert = NUM_TRIES_BEFORE_ALERT_START

        //Stores the scroll view reference
        var scroll: WeakReference<ScrollView>? = null

        //Stores the button the toggles showing the license
        var licenseButton: WeakReference<Button>? = null


        //Pre: None
        //Post: None
        //Desc: Gets and sets the correct prayer text to display and
        fun updatePrayerText() {

            //Set the scroll to the top
            scroll?.get()?.scrollTo(0, 0)

            //Handles if the user was previously viewing the license
            if (isViewingLicense) {

                //Sets the text to View License
                licenseButton?.get()?.setText(R.string.ViewLicense)

                //The user is no longer viewing the license
                isViewingLicense = false
            }

            //Decides what the new prayer text should be
            if (currentBead == 7 && isPrayingCounterClockwise) {
                prayerTextView?.get()?.text = beads[LAST_BEAD[isPrayingCounterClockwise.toInt()]]!!.prayerText[prayerTextIndex]
            }
            else if (currentBead == Bead.BEGINNING) {
                prayerTextView?.get()?.text = Bead.prayerTextStartBead[prayerTextIndex]
            }
            else {
                prayerTextView?.get()?.text = beads[currentBead]!!.prayerText[prayerTextIndex]
            }
        }

        //Pre: None
        //Post: A numeric representation of a boolean
        //Desc: Allows for booleans to be converted into integers
        fun Boolean.toInt() = if (this) 1 else 0
    }

    //Maps the indices of the beads to image button IDs
    private val indexToButtonId = HashMap<Int, Int>()

    //Stores the button labels for the prayer buttons
    private val prayerTypeLabels = arrayOf(
        "Joyful Mysteries",
        "Sorrowful Mysteries",
        "Glorious Mysteries",
        "Luminous Mysteries",
        "Divine Mercy",
        "Empty"
    )

    //Stores the mysteries that need to be swapped
    private var secondMystery = arrayOf("")
    private var fifthMystery = arrayOf("")
    private var thirdMystery = arrayOf("")
    private var fourthMystery = arrayOf("")

    //Stores a standard decade and endings of decades and the rosary
    private var standardDecade = arrayOf("")
    private var decadeEnding = arrayOf("")
    private var rosaryEnding = arrayOf("")

    //Stores the second and fifth mystery bead text in an array
    private var realSecondFifthMystery = arrayOf(arrayOf(""))

    //Stores the third and fourth mystery bead text in an array
    private var realThirdFourthMystery = arrayOf(arrayOf(""))

    //Stores the decade and rosary end text in an array
    private var realDecadeEnding = arrayOf(arrayOf(""))
    private var realRosaryEnding = arrayOf(arrayOf(""))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializes the mysteries, decade and ending text
        initializeMysteries()

        //Stores a reference to the prayer text view
        prayerTextView = WeakReference(findViewById(R.id.prayerTextView))

        //Stores a reference to scroll view and resizes it based on the screen size
        scroll = WeakReference(findViewById(R.id.scrollId))
        scroll?.get()!!.layoutParams = generateNewLayout(scroll?.get()!!.layoutParams, 4f, 2.45f)

        //Initializes the index to button ID hash map
        initalizeIndexButtonIdHashMap()

        //Stores a reference to the recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.prayerRecyclerView)

        //Initializes the layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PrayerAdapter(prayerTypeLabels)
        recyclerView.adapter = adapter
        recyclerView.layoutParams = generateNewLayout(recyclerView.layoutParams, 1.41f, 4.25f)//1.37

        //Sets the background colour of the recycler view based on if the device is in light or dark mode
        if (isDarkModeEnabled(this)) recyclerView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.rvBgDarkMode
            )
        )

        //Gets a reference to the reset button and sets its on click listener
        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener {
            //Resets the rosary
            resetRosary()
        }

        //Gets a reference to the direction button and sets its on click listener
        val directionButton = findViewById<Button>(R.id.directionButton)
        directionButton.setOnClickListener {

            //Changes the prayer direction and then resets the rosary
            isPrayingCounterClockwise = !isPrayingCounterClockwise
            resetRosary()

            //Makes and displays a toast to the user to tell them the new praying direction
            Toast.makeText(
                this,
                "Now Praying " + directionButton.text.drop(5),
                Toast.LENGTH_SHORT
            ).show()


            //Updates the text of the direction button
            if (isPrayingCounterClockwise) directionButton.text = getString(R.string.Counterclockwise)
            else directionButton.text = getString(R.string.Clockwise)

            //Changes the prayer text for the 2nd, 3rd, 4th and 5th mystery beads
            beads[17]?.prayerText = realSecondFifthMystery[isPrayingCounterClockwise.toInt()]
            beads[28]?.prayerText = realThirdFourthMystery[isPrayingCounterClockwise.toInt()]
            beads[39]?.prayerText = realThirdFourthMystery[(!isPrayingCounterClockwise).toInt()]
            beads[50]?.prayerText = realSecondFifthMystery[(!isPrayingCounterClockwise).toInt()]

            //Changes the beginning and ending prayer text of all the decades
            beads[7]?.prayerText = realRosaryEnding[isPrayingCounterClockwise.toInt()]
            beads[16]?.prayerText = realDecadeEnding[(!isPrayingCounterClockwise).toInt()]
            beads[18]?.prayerText = realDecadeEnding[isPrayingCounterClockwise.toInt()]
            beads[27]?.prayerText = realDecadeEnding[(!isPrayingCounterClockwise).toInt()]
            beads[29]?.prayerText = realDecadeEnding[isPrayingCounterClockwise.toInt()]
            beads[38]?.prayerText = realDecadeEnding[(!isPrayingCounterClockwise).toInt()]
            beads[40]?.prayerText = realDecadeEnding[isPrayingCounterClockwise.toInt()]
            beads[49]?.prayerText = realDecadeEnding[(!isPrayingCounterClockwise).toInt()]
            beads[51]?.prayerText = realDecadeEnding[isPrayingCounterClockwise.toInt()]
            beads[60]?.prayerText = realRosaryEnding[(!isPrayingCounterClockwise).toInt()]

            //Updates the prayer text
            updatePrayerText()

        }

        //Gets a reference to the license button and sets its on click listener
        licenseButton = WeakReference(findViewById(R.id.licenseButton))
        licenseButton?.get()!!.setOnClickListener {

            //Displays the license text if the user is not already viewing it, otherwise it updates the prayer text
            if (!isViewingLicense) {

                //Set the scroll to the top
                scroll?.get()?.scrollTo(0, 0)

                //Sets the prayer text to be the license
                prayerTextView?.get()?.setText(R.string.license)

                //Change the license button text
                licenseButton?.get()?.setText(R.string.RemoveLicense)

                //The user is now viewing the license
                isViewingLicense = true
            }
            else {
                updatePrayerText()
            }
        }

        //Initializes all beads
        for (i in beads.indices) {

            //Stores the image button corresponding to the current index and sets it's on click listener
            val imgButton = findViewById<ImageButton>(toNonNullableInt(indexToButtonId[i]))
            imgButton.setOnClickListener {

                //Exits the click event if the last bead is currently selected
                if (currentBead == LAST_BEAD[isPrayingCounterClockwise.toInt()]) return@setOnClickListener

                //Exits if this bead ID is not the next bead
                if (beads[i]!!.id != getNextBead()) {

                    //Decrement the number of tries before an alert
                    numTriesBeforeAlert--

                    //Display a toast if no more tries remain
                    if (numTriesBeforeAlert == 0) {

                        //Makes and displays a toast to the user to tell them what is the next bead
                        Toast.makeText(
                            this,
                            "Wrong Bead: Next is #" + (getNextBead() + 1).toString() + ", you clicked #" + (i + 1).toString(),
                            Toast.LENGTH_SHORT
                        ).show()

                        //Resets the number of tries before the next alert
                        numTriesBeforeAlert = NUM_TRIES_BEFORE_ALERT_START
                    }

                    //return the click listener
                    return@setOnClickListener
                }

                //Updates the bead image
                beads[i]!!.imgButton.setImageResource(beads[i]!!.invertImageResource())

                //Moves to the next bead
                currentBead = getNextBead()

                //Updates the prayer text
                updatePrayerText()

                //Resets the number of tries before the next alert
                numTriesBeforeAlert = NUM_TRIES_BEFORE_ALERT_START
            }

            //Save the new bead in the beads array
            beads[i] = Bead(imgButton, i)
        }

        //Initialize and update the prayer text
        initializePrayerText()
        updatePrayerText()
    }

    //Pre: None
    //Post: None
    //Desc: Initializes the mysteries, decade and ending text
    private fun initializeMysteries(){

        //Initializes the prayer text for the 2nd, 3rd, 4th and 5th mysteries
        secondMystery = arrayOf(
            "2nd Joyful Mystery: The Visitation.\n\n" + getString(R.string.OurFather),
            "2nd Sorrowful Mystery: The scourging at the pillar.\n\n" + getString(R.string.OurFather),
            "The 2nd Glorious Mystery: The ascension.\n\n" + getString(R.string.OurFather),
            "2nd Mystery of Light: The wedding feast of Cana.\n\n" + getString(R.string.OurFather),
            getString(R.string.EternalFather),
            ""
        )
        fifthMystery = arrayOf(
            "5th Joyful Mystery: The finding of Jesus in the temple.\n\n" + getString(R.string.OurFather),
            "5th Sorrowful Mystery: The crucifixion.\n\n" + getString(R.string.OurFather),
            "The 5th Glorious Mystery: The crowning of Our Lady Queen of Heaven.\n\n" + getString(R.string.OurFather),
            "5th Mystery of Light: The institution of the Eucharist.\n\n" + getString(R.string.OurFather),
            getString(R.string.EternalFather),
            ""
        )
        thirdMystery = arrayOf(
            "3rd Joyful Mystery: The Birth of Our Lord.\n\n" + getString(R.string.OurFather),
            "3rd Sorrowful Mystery: The crowning with thorns.\n\n" + getString(R.string.OurFather),
            "The 3rd Glorious Mystery: The descent of the Holy Spirit.\n\n" + getString(R.string.OurFather),
            "3rd Mystery of Light: The proclamation of the kingdom of God.\n\n" + getString(R.string.OurFather),
            getString(R.string.EternalFather),
            ""
        )
        fourthMystery = arrayOf(
            "4th Joyful Mystery: The Presentation in the Temple.\n\n" + getString(R.string.OurFather),
            "4th Sorrowful Mystery: The carrying of the cross.\n\n" + getString(R.string.OurFather),
            "The 4th Glorious Mystery: The Assumption.\n\n" + getString(R.string.OurFather),
            "4th Mystery of Light: The Transfiguration.\n\n" + getString(R.string.OurFather),
            getString(R.string.EternalFather),
            ""
        )

        //Stores the beginning and ending prayer text of all the decades
        standardDecade = arrayOf(
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            getString(R.string.SorrowfulPassion),
            ""
        )
        decadeEnding = arrayOf(
            getString(R.string.HailMary) + "\n\n" + getString(R.string.GloryBe) + "\n\n" + getString(
                R.string.OMyJesus
            ),
            getString(R.string.HailMary) + "\n\n" + getString(R.string.GloryBe) + "\n\n" + getString(
                R.string.OMyJesus
            ),
            getString(R.string.HailMary) + "\n\n" + getString(R.string.GloryBe) + "\n\n" + getString(
                R.string.OMyJesus
            ),
            getString(R.string.HailMary) + "\n\n" + getString(R.string.GloryBe) + "\n\n" + getString(
                R.string.OMyJesus
            ),
            getString(R.string.SorrowfulPassion),
            ""
        )
        rosaryEnding = arrayOf(
            getString(R.string.HailMary) + "\n\n" + getString(R.string.GloryBe) + "\n\n" + getString(
                R.string.OMyJesus
            ) + "\n\n" + getString(R.string.HailHolyQueen) + "\n\n" + getString(R.string.LetUsPray) + "\n\n" + getString(
                R.string.RosaryConclusion
            ) + "\n\n" + getString(R.string.SignOfCross),
            getString(R.string.HailMary) + "\n\n" + getString(R.string.GloryBe) + "\n\n" + getString(
                R.string.OMyJesus
            ) + "\n\n" + getString(R.string.HailHolyQueen) + "\n\n" + getString(R.string.LetUsPray) + "\n\n" + getString(
                R.string.RosaryConclusion
            ) + "\n\n" + getString(R.string.SignOfCross),
            getString(R.string.HailMary) + "\n\n" + getString(R.string.GloryBe) + "\n\n" + getString(
                R.string.OMyJesus
            ) + "\n\n" + getString(R.string.HailHolyQueen) + "\n\n" + getString(R.string.LetUsPray) + "\n\n" + getString(
                R.string.RosaryConclusion
            ) + "\n\n" + getString(R.string.SignOfCross),
            getString(R.string.HailMary) + "\n\n" + getString(R.string.GloryBe) + "\n\n" + getString(
                R.string.OMyJesus
            ) + "\n\n" + getString(R.string.HailHolyQueen) + "\n\n" + getString(R.string.LetUsPray) + "\n\n" + getString(
                R.string.RosaryConclusion
            ) + "\n\n" + getString(R.string.SignOfCross),
            getString(R.string.SorrowfulPassion) + "\n\n" + getString(R.string.HolyGodX3) + "\n\n" + getString(
                R.string.LetUsPray
            ) + "\n\n" + getString(R.string.EternalGod) + "\n\n" + getString(R.string.SignOfCross),
            ""
        )

        //Initializes the second and fifth mystery bead text in an array
        realSecondFifthMystery = arrayOf(secondMystery, fifthMystery)

        //Initializes the third and fourth mystery bead text in an array
        realThirdFourthMystery = arrayOf(thirdMystery, fourthMystery)

        //Initializes the decade and rosary end text in an array
        realDecadeEnding = arrayOf(standardDecade, decadeEnding)
        realRosaryEnding = arrayOf(standardDecade, rosaryEnding)
    }

    //Pre: None
    //Post: None
    //Desc: Initializes the index to button ID hash map
    private fun initalizeIndexButtonIdHashMap(){

        //Introduction
        indexToButtonId[0] = R.id.button0
        indexToButtonId[1] = R.id.button1
        indexToButtonId[2] = R.id.button2
        indexToButtonId[3] = R.id.button3
        indexToButtonId[4] = R.id.button4
        indexToButtonId[5] = R.id.button5

        //First Decade
        indexToButtonId[6] = R.id.button6
        indexToButtonId[7] = R.id.button7
        indexToButtonId[8] = R.id.button8
        indexToButtonId[9] = R.id.button9
        indexToButtonId[10] = R.id.button10
        indexToButtonId[11] = R.id.button11
        indexToButtonId[12] = R.id.button12
        indexToButtonId[13] = R.id.button13
        indexToButtonId[14] = R.id.button14
        indexToButtonId[15] = R.id.button15
        indexToButtonId[16] = R.id.button16
        indexToButtonId[17] = R.id.button17

        //Second Decade
        indexToButtonId[18] = R.id.button18
        indexToButtonId[19] = R.id.button19
        indexToButtonId[20] = R.id.button20
        indexToButtonId[21] = R.id.button21
        indexToButtonId[22] = R.id.button22
        indexToButtonId[23] = R.id.button23
        indexToButtonId[24] = R.id.button24
        indexToButtonId[25] = R.id.button25
        indexToButtonId[26] = R.id.button26
        indexToButtonId[27] = R.id.button27

        //Third Decade
        indexToButtonId[28] = R.id.button28
        indexToButtonId[29] = R.id.button29
        indexToButtonId[30] = R.id.button30
        indexToButtonId[31] = R.id.button31
        indexToButtonId[32] = R.id.button32
        indexToButtonId[33] = R.id.button33
        indexToButtonId[34] = R.id.button34
        indexToButtonId[35] = R.id.button35
        indexToButtonId[36] = R.id.button36
        indexToButtonId[37] = R.id.button37
        indexToButtonId[38] = R.id.button38

        //Fourth Decade
        indexToButtonId[39] = R.id.button39
        indexToButtonId[40] = R.id.button40
        indexToButtonId[41] = R.id.button41
        indexToButtonId[42] = R.id.button42
        indexToButtonId[43] = R.id.button43
        indexToButtonId[44] = R.id.button44
        indexToButtonId[45] = R.id.button45
        indexToButtonId[46] = R.id.button46
        indexToButtonId[47] = R.id.button47
        indexToButtonId[48] = R.id.button48
        indexToButtonId[49] = R.id.button49

        //Fifth Decade
        indexToButtonId[50] = R.id.button50
        indexToButtonId[51] = R.id.button51
        indexToButtonId[52] = R.id.button52
        indexToButtonId[53] = R.id.button53
        indexToButtonId[54] = R.id.button54
        indexToButtonId[55] = R.id.button55
        indexToButtonId[56] = R.id.button56
        indexToButtonId[57] = R.id.button57
        indexToButtonId[58] = R.id.button58
        indexToButtonId[59] = R.id.button59
        indexToButtonId[60] = R.id.button60
    }

    //Pre: None
    //Post: The next bead in the prayer
    //Desc: Calculates and returns the next bead in the prayer
    private fun getNextBead(): Int {

        //Returns the next bead based on the direction and current bead
        when{
            !isPrayingCounterClockwise -> return currentBead + 1

            currentBead < 6 -> return currentBead + 1
            currentBead == 6 -> return TOTAL_BEADS - 1

            else -> return currentBead - 1
        }
    }

    private fun resetRosary() {

        //Resets each bead in the beads array
        for (i in beads.indices) {

            //If the bead is currently ready skip the bead
            if (!beads[i]!!.isFinished) continue

            //Gets the image button of the bead and inverts the image resource and the finished state
            val imgButton = findViewById<ImageButton>(toNonNullableInt(indexToButtonId[i]))
            imgButton.setImageResource(beads[i]!!.invertImageResource())
        }

        //Returns to the beginning of the rosary
        currentBead = Bead.BEGINNING

        //Updates the prayer text
        updatePrayerText()
    }

    //Pre: Initial layout parameters, width divisor to be subtracted and height divisor
    //Post: New layout parameters
    //Desc: Generates new layout parameters for a view
    private fun generateNewLayout(initialParams: ViewGroup.LayoutParams, widthSubDivisor: Float, heightDivisor: Float): ViewGroup.LayoutParams {

        //Creates new width and height
        val newWidth = getScreenWidth(this) - (getScreenWidth(this) / widthSubDivisor).toInt()
        val newHeight = (getScreenHeight(this) / heightDivisor).toInt()

        //Sets new width and height
        initialParams.width = newWidth
        initialParams.height = newHeight

        //Returns Parameters
        return initialParams
    }

    //Pre: None
    //Post: None
    //Desc: Initializes the prayer text for all of th beads in the beads array
    private fun initializePrayerText() {

        //Introduction
        beads[0]?.prayerText = arrayOf(
            getString(R.string.SignOfCross) + "\n\n" + getString(R.string.ApostlesCreed),
            getString(R.string.SignOfCross) + "\n\n" + getString(R.string.ApostlesCreed),
            getString(R.string.SignOfCross) + "\n\n" + getString(R.string.ApostlesCreed),
            getString(R.string.SignOfCross) + "\n\n" + getString(R.string.ApostlesCreed),
            getString(R.string.SignOfCross),
            ""
        )
        beads[1]?.prayerText = arrayOf(
            getString(R.string.OurFather),
            getString(R.string.OurFather),
            getString(R.string.OurFather),
            getString(R.string.OurFather),
            getString(R.string.MercyIntroduction),
            ""
        )
        beads[2]?.prayerText = arrayOf(
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            getString(R.string.OurFather),
            ""
        )
        beads[3]?.prayerText = arrayOf(
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            ""
        )
        beads[4]?.prayerText = arrayOf(
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            getString(R.string.HailMary),
            getString(R.string.ApostlesCreed),
            ""
        )

        //1st Decade
        beads[5]?.prayerText = arrayOf(
            getString(R.string.GloryBe),
            getString(R.string.GloryBe),
            getString(R.string.GloryBe),
            getString(R.string.GloryBe),
            getString(R.string.EternalFather),
            ""
        )
        beads[6]?.prayerText = arrayOf(
            "1st Joyful Mystery: The Annunciation.\n\n" + getString(R.string.OurFather),
            "1st Sorrowful Mystery: The agony in the Garden.\n\n" + getString(R.string.OurFather),
            "The 1st Glorious Mystery: The resurrection.\n\n" + getString(R.string.OurFather),
            "1st Mystery of Light: The Baptism in the Jordan.\n\n" + getString(R.string.OurFather),
            "(Skip This Bead)",
            ""
        )
        beads[7]?.prayerText = realRosaryEnding[STARTING_DIRECTION.toInt()]
        beads[8]?.prayerText = standardDecade
        beads[9]?.prayerText = standardDecade
        beads[10]?.prayerText = standardDecade
        beads[11]?.prayerText = standardDecade
        beads[12]?.prayerText = standardDecade
        beads[13]?.prayerText = standardDecade
        beads[14]?.prayerText = standardDecade
        beads[15]?.prayerText = standardDecade
        beads[16]?.prayerText = realDecadeEnding[(!STARTING_DIRECTION).toInt()]

        //2nd Decade
        beads[17]?.prayerText = realSecondFifthMystery[STARTING_DIRECTION.toInt()]
        beads[18]?.prayerText = realDecadeEnding[STARTING_DIRECTION.toInt()]
        beads[19]?.prayerText = standardDecade
        beads[20]?.prayerText = standardDecade
        beads[21]?.prayerText = standardDecade
        beads[22]?.prayerText = standardDecade
        beads[23]?.prayerText = standardDecade
        beads[24]?.prayerText = standardDecade
        beads[25]?.prayerText = standardDecade
        beads[26]?.prayerText = standardDecade
        beads[27]?.prayerText = realDecadeEnding[(!STARTING_DIRECTION).toInt()]

        //3rd Decade
        beads[28]?.prayerText = realThirdFourthMystery[STARTING_DIRECTION.toInt()]
        beads[29]?.prayerText = realDecadeEnding[STARTING_DIRECTION.toInt()]
        beads[30]?.prayerText = standardDecade
        beads[31]?.prayerText = standardDecade
        beads[32]?.prayerText = standardDecade
        beads[33]?.prayerText = standardDecade
        beads[34]?.prayerText = standardDecade
        beads[35]?.prayerText = standardDecade
        beads[36]?.prayerText = standardDecade
        beads[37]?.prayerText = standardDecade
        beads[38]?.prayerText = realDecadeEnding[(!STARTING_DIRECTION).toInt()]

        //4th Decade
        beads[39]?.prayerText = realThirdFourthMystery[(!STARTING_DIRECTION).toInt()]
        beads[40]?.prayerText = realDecadeEnding[STARTING_DIRECTION.toInt()]
        beads[41]?.prayerText = standardDecade
        beads[42]?.prayerText = standardDecade
        beads[43]?.prayerText = standardDecade
        beads[44]?.prayerText = standardDecade
        beads[45]?.prayerText = standardDecade
        beads[46]?.prayerText = standardDecade
        beads[47]?.prayerText = standardDecade
        beads[48]?.prayerText = standardDecade
        beads[49]?.prayerText = realDecadeEnding[(!STARTING_DIRECTION).toInt()]

        //5th Decade
        beads[50]?.prayerText = realSecondFifthMystery[(!STARTING_DIRECTION).toInt()]
        beads[51]?.prayerText = realDecadeEnding[STARTING_DIRECTION.toInt()]
        beads[52]?.prayerText = standardDecade
        beads[53]?.prayerText = standardDecade
        beads[54]?.prayerText = standardDecade
        beads[55]?.prayerText = standardDecade
        beads[56]?.prayerText = standardDecade
        beads[57]?.prayerText = standardDecade
        beads[58]?.prayerText = standardDecade
        beads[59]?.prayerText = standardDecade
        beads[60]?.prayerText = realRosaryEnding[(!STARTING_DIRECTION).toInt()]
    }
}