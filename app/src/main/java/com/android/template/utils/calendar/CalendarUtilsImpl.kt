package com.android.template.utils.calendar

import java.text.SimpleDateFormat
import java.util.*

class CalendarUtilsImpl(private val calendarFactory: CalendarFactory) : CalendarUtils {

    companion object {
        private const val SHORT_MONTH_NAME_FORMAT = "MMM"
        private const val SHORT_WEEKDAY_NAME_FORMAT = "EEE"
    }

    override fun isCalendarDateEqualToStringDate(calendarDate: Calendar, stringDate: String): Boolean {
        resetHours(calendarDate)
        return calendarDate.compareTo(calendarFactory.dateStringToCalendar(stringDate)) == 0
    }

    override fun isCalendarDateEqualToStringDate(calendarDate: Calendar, stringDate: String, dateFormat: String): Boolean {
        return calendarDate == calendarFactory.dateStringToCalendar(stringDate, dateFormat)
    }

    override fun getShortMonthName(date: Calendar): String {
        return SimpleDateFormat(SHORT_MONTH_NAME_FORMAT, Locale.getDefault()).format(date.time)
    }

    override fun getShortWeekdayName(date: Calendar): String {
        return SimpleDateFormat(SHORT_WEEKDAY_NAME_FORMAT, Locale.getDefault()).format(date.time)
    }

    override fun getDayOfMonthNumber(date: Calendar): Int {
        return date.get(Calendar.DAY_OF_MONTH)
    }

    private fun resetHours(calendarDate: Calendar) {
        calendarDate.set(Calendar.HOUR_OF_DAY, 0)
        calendarDate.set(Calendar.MINUTE, 0)
        calendarDate.set(Calendar.SECOND, 0)
        calendarDate.set(Calendar.MILLISECOND, 0)
    }

    override fun getTimeDurationString(time: String, durationInMin: Int): String {
        return getDurationStringInternal(calendarFactory.timeStringToCalendar(time), durationInMin)
    }

    override fun getTimeDurationString(time: String, timeFormat: String, durationInMin: Int): String {
        return getDurationStringInternal(calendarFactory.timeStringToCalendar(time, timeFormat), durationInMin)
    }

    private fun getDurationStringInternal(startTime: Calendar, durationInMin: Int): String {
        val endTime = Calendar.getInstance()
        endTime.time = startTime.time
        endTime.add(Calendar.MINUTE, durationInMin)

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(startTime.time) + " - " + dateFormat.format(endTime.time)
    }

    override fun subtractDayWithStringDateLimit(date: Calendar, limitDateString: String): Boolean {
        var ret = false

        val limitDate = calendarFactory.dateStringToCalendar(limitDateString)

        if(0 == limitDate.compareTo(date)) {
            return ret
        }

        val subtractedDate = Calendar.getInstance()
        subtractedDate.time = date.time
        subtractedDate.add(Calendar.DAY_OF_YEAR, -1)

        if (subtractedDate >= limitDate) {
            date.time = subtractedDate.time
            ret = true
        }

        return ret
    }

    override fun subtractDayWithStringDateLimit(date: Calendar, limitDateString: String, stringFormat: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addDayWithStringDateLimit(date: Calendar, limitDateString: String): Boolean {
        var ret = false

        val limitDate = calendarFactory.dateStringToCalendar(limitDateString)

        if(0 == limitDate.compareTo(date)) {
            return ret
        }

        val addedDate = Calendar.getInstance()
        addedDate.time = date.time
        addedDate.add(Calendar.DAY_OF_YEAR, 1)

        if (addedDate <= limitDate) {
            date.time = addedDate.time
            ret = true
        }

        return ret
    }

    override fun addDayWithStringDateLimit(date: Calendar, limitDateString: String, stringFormat: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}