package com.android.template.utils.calendar

import java.text.SimpleDateFormat
import java.util.*

class CalendarFactoryImpl : CalendarFactory {

    companion object {
        private const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"
        private const val DEFAULT_TIME_FORMAT = "HH:mm"
    }

    override fun dateStringToCalendar(dateString: String): Calendar {
        return dateStringToCalendar(dateString, DEFAULT_DATE_FORMAT)
    }

    override fun dateStringToCalendar(dateString: String, formatString: String): Calendar {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat(formatString, Locale.getDefault()).parse(dateString)
        resetHours(calendar)
        return calendar
    }

    private fun resetHours(calendarDate: Calendar) {
        calendarDate.set(Calendar.HOUR_OF_DAY, 0)
        calendarDate.set(Calendar.MINUTE, 0)
        calendarDate.set(Calendar.SECOND, 0)
        calendarDate.set(Calendar.MILLISECOND, 0)
    }

    override fun timeStringToCalendar(timeString: String): Calendar {
        return timeStringToCalendar(timeString, DEFAULT_TIME_FORMAT)
    }

    override fun timeStringToCalendar(timeString: String, formatString: String): Calendar {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat(formatString, Locale.getDefault()).parse(timeString)
        resetDays(calendar)
        return calendar
    }

    private fun resetDays(calendarHours: Calendar) {
        calendarHours.set(Calendar.YEAR, 0)
        calendarHours.set(Calendar.DAY_OF_YEAR, 0)
    }
}