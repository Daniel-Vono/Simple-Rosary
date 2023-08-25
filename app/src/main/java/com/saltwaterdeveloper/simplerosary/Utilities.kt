package com.saltwaterdeveloper.simplerosary

import android.content.Context
import android.content.res.Configuration

//Pre: A nullable integer
//Post: A non-nullable integer
//Desc: Converts a nullable integer to a non-nullable integer
fun toNonNullableInt(start: Int?): Int {
    return start!!
}

//Pre: The current context
//Post: The screen width
//Desc: Gets the screen width from a context
fun getScreenWidth(context: Context): Int {
    return context.resources.displayMetrics.widthPixels
}

//Pre: The current context
//Post: The screen height
//Desc: Gets the screen height from a context
fun getScreenHeight(context: Context): Int {
    return context.resources.displayMetrics.heightPixels
}

//Pre: The current context
//Post: A boolean representing if dark mode is enabled
//Desc: Gets if the device is in dark mode or light mode
fun isDarkModeEnabled(context: Context): Boolean {
    val nightModeFlag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return nightModeFlag == Configuration.UI_MODE_NIGHT_YES
}
