package com.saltwaterdeveloper.simplerosary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class PrayerAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<PrayerAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //Stores the button that changes to specific prayer
        val button: Button

        init {
            //Sets the button to the prayer button from the layout file
            button = view.findViewById(R.id.prayerButton)
        }
    }

    // Creates new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Creates a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        //Returns the new view holder
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        //Sets the text of the button to the correct prayer name
        viewHolder.button.text = dataSet[position]

        //Initializes the button's on click listener
        viewHolder.button.setOnClickListener {
            //Update which prayer is being said and the prayer text
            MainActivity.prayerTextIndex = position
            MainActivity.updatePrayerText()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}