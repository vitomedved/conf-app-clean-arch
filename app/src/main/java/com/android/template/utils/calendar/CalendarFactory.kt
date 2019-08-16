package com.android.template.utils.calendar

import java.util.*


interface CalendarFactory {
    fun dateStringToCalendar(dateString: String): Calendar

    fun dateStringToCalendar(dateString: String, formatString: String): Calendar

    fun timeStringToCalendar(timeString: String): Calendar

    fun timeStringToCalendar(timeString: String, formatString: String): Calendar
}