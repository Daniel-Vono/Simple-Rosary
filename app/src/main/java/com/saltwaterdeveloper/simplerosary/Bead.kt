package com.saltwaterdeveloper.simplerosary

import android.widget.ImageButton

class Bead(val imgButton: ImageButton/*, val nextButtonIndex: Int*/, val id: Int) {

    companion object {
        //Represents the starting index of the rosary
        const val BEGINNING = -1

        //Represents the option to display no prayers in the prayer text
        const val EMPTY: Int = 5

        //Stores the text displayed before the start of a prayer
        val prayerTextStartBead = arrayOf(
            "Joyful Mysteries Selected",
            "Sorrowful Mysteries Selected",
            "Glorious Mysteries Selected",
            "Luminous Mysteries Selected",
            "Divine Mercy Selected",
            "No Prayer Selected"
        )
    }

    //Stores if the bead has already been prayed
    var isFinished = false

    //Stores all of the prayers associated with the bead
    var prayerText = arrayOf("Joy temp.", "Sorrow temp.", "Glory temp.", "Luminous temp.", "Mercy temp.", "Empty temp.")

    //Pre: None
    //Post: The ID of the new bead image
    //Desc: Toggles the finished state of the bead and returns the new bead image
    fun invertImageResource(): Int {
        when (id) {

            //Crucifix
            0 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.crucifixunfinished
                }
                isFinished = !isFinished
                return R.drawable.crucifixfinished
            }

            //Center Piece
            6 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.centerpieceunfinished
                }
                isFinished = !isFinished
                return R.drawable.centerpiecefinished
            }

            //Large Beads
            1 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.largebeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.largebeadfinished
            }

            5 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.largebeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.largebeadfinished
            }

            17 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.largebeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.largebeadfinished
            }

            28 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.largebeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.largebeadfinished
            }

            39 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.largebeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.largebeadfinished
            }

            50 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.largebeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.largebeadfinished
            }

            //Small Beads
            2 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            3 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            4 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            7 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            8 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            9 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            10 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            11 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            12 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            13 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            14 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            15 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            16 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            18 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            19 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            20 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            21 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            22 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            23 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            24 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            25 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            26 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            27 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            29 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            30 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            31 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            32 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            33 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            34 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            35 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            36 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            37 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            38 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            40 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            41 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            42 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            43 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            44 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            45 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            46 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            47 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            48 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            49 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            51 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            52 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            53 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            54 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            55 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            56 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            57 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            58 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            59 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            60 -> {
                if (isFinished) {
                    isFinished = !isFinished
                    return R.drawable.smallbeadunfinished
                }
                isFinished = !isFinished
                return R.drawable.smallbeadfinished
            }

            //Throws an error if no bead is found
            else -> throw error("Invalid Bead Index: Could not invert bead.")
        }
    }
}