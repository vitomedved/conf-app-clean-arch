package com.android.template.utils.calendar

import java.util.*

// TODO: maybe somehow merge with DateUtils from domain? Does that make any sense?
interface CalendarUtils {
    fun isCalendarDateEqualToStringDate(calendarDate: Calendar, stringDate: String): Boolean

    fun isCalendarDateEqualToStringDate(calendarDate: Calendar, stringDate: String, dateFormat: String): Boolean

    fun getShortMonthName(date: Calendar): String

    fun getShortWeekdayName(date: Calendar): String

    fun getDayOfMonthNumber(date: Calendar): Int

    fun getTimeDurationString(time: String, durationInMin: Int): String

    fun getTimeDurationString(time: String, timeFormat: String, durationInMin: Int): String

    fun subtractDayWithStringDateLimit(date: Calendar, limitDateString: String): Boolean

    fun subtractDayWithStringDateLimit(date: Calendar, limitDateString: String, stringFormat: String): Boolean

    // NOTE: This will return true only if date with added day will be lower than limit date
    fun addDayWithStringDateLimit(date: Calendar, limitDateString: String): Boolean

    // NOTE: This will return true only if date with subtracted day will be higher than limit date
    fun addDayWithStringDateLimit(date: Calendar, limitDateString: String, stringFormat: String): Boolean
}