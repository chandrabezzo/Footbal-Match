package com.bezzo.core.util

import java.text.*
import java.util.*

/**
 * Created by bezzo on 05/10/17.
 */

object DateTimeUtils {

    // END EPOCH CONVERTER

    // DATE COMPONENT

    val currentDate: String
        get() = currentDay.toString() + "/" + currentMonth + "/" + currentYear

    val currentTime: String
        get() = currentHour.toString() + ":" + currentMinute + ":" + currentSecond

    val currentDateTime: String
        get() = "$currentDate $currentTime"

    val currentDay: Int
        get() {
            val calendar = Calendar.getInstance()
            return calendar.get(Calendar.DAY_OF_MONTH)
        }

    // January = 0
    val currentMonth: Int
        get() {
            val calendar = Calendar.getInstance()
            return calendar.get(Calendar.MONTH)
        }

    val currentYear: Int
        get() {
            val calendar = Calendar.getInstance()
            return calendar.get(Calendar.YEAR)
        }

    val currentHour: Int
        get() {
            val calendar = Calendar.getInstance()
            return calendar.get(Calendar.HOUR_OF_DAY)
        }

    val currentMinute: Int
        get() {
            val calendar = Calendar.getInstance()
            return calendar.get(Calendar.MINUTE)
        }

    val currentSecond: Int
        get() {
            val calendar = Calendar.getInstance()
            return calendar.get(Calendar.SECOND)
        }

    // END DATE COMPONENT

    // Calendar CONVERTER

    val todayInCalendar: Calendar
        get() = Calendar.getInstance()

    val lastDateOfMonth: Int
        get() {
            val calendar = Calendar.getInstance()
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        }

    // EPOCH CONVERTER

    fun epochToDate(epoch: Long): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .format(Date(epoch * 1000))
    }

    fun epochToTime(epoch: Long): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault())
                .format(Date(epoch * 1000))
    }

    fun epochToDateTime(epoch: Long): String {
        return SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                .format(Date(epoch * 1000))
    }

    fun epochToHumanDate(epoch: Long): String {
        return SimpleDateFormat("EEEE, dd MMMM yyyy", CommonUtils.localeID)
                .format(Date(epoch * 1000))
    }

    fun dateToEpoch(date: String): Long {

        var epoch: Long = 0

        try {
            epoch = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    .parse(date).time / 1000
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return epoch
    }

    fun dateTimeToEpoch(dateTime: String): Long {

        var epoch: Long = 0

        try {
            epoch = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                    .parse(dateTime).time / 1000
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return epoch
    }

    fun getDateTimeInCalendar(day: Int?, month: Int?, year: Int?, hour: Int?, minute: Int?): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, day!!)
        calendar.set(Calendar.MONTH, month!! - 1)
        calendar.set(Calendar.YEAR, year!!)
        calendar.set(Calendar.HOUR_OF_DAY, hour!!)
        calendar.set(Calendar.MINUTE, minute!!)

        return calendar
    }

    fun getDateInCalendar(day: Int?, month: Int?, year: Int?): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, day!!)
        calendar.set(Calendar.MONTH, month!! - 1)
        calendar.set(Calendar.YEAR, year!!)

        return calendar
    }

    //format date 28/11/1995
    fun dateToCalendar(date: String): Calendar {
        val splitDate = CommonUtils.getSplittedString(date, "/")
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[0]))
        calendar.set(Calendar.MONTH, Integer.parseInt(splitDate[1]) - 1)
        calendar.set(Calendar.YEAR, Integer.parseInt(splitDate[2]))

        return calendar
    }

    //format datetime 28/11/1995 19:00
    fun getDateTimeInCalendar(dateTime: String): Calendar {
        val datetimeSplit = CommonUtils.getSplittedString(dateTime, " ")
        val dateSplit = CommonUtils.getSplittedString(datetimeSplit[0], "/")
        val timeSplit = CommonUtils.getSplittedString(datetimeSplit[1], ":")

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, Integer.parseInt(dateSplit[0]))
        calendar.set(Calendar.MONTH, Integer.parseInt(dateSplit[1]) - 1)
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateSplit[2]))
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeSplit[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeSplit[1]))

        return calendar
    }

    fun getAddedDayCalendar(date: String, addDay: Int): Calendar {
        val calendar = dateToCalendar(date)
        calendar.add(Calendar.DAY_OF_MONTH, addDay)

        return calendar
    }

    fun getAddedMonthCalendar(date: String, addMonth: Int): Calendar {
        val calendar = dateToCalendar(date)
        calendar.add(Calendar.MONTH, addMonth)

        return calendar
    }

    fun getAddedYearCalendar(date: String, addYear: Int): Calendar {
        val calendar = dateToCalendar(date)
        calendar.add(Calendar.YEAR, addYear)

        return calendar
    }

    fun countDate(tglAwal: Long?, tglAkhir: Long?): Long? {
        val tglMulai = epochToDate(tglAwal!!)
        val tglSelesai = epochToDate(tglAkhir!!)

        val startCalendar = dateToCalendar(tglMulai)
        val endCalendar = dateToCalendar(tglSelesai)

        val dateInMilis = endCalendar.timeInMillis - startCalendar.timeInMillis

        return dateInMilis / (24 * 60 * 60 * 1000)
    }

    // END Calendar CONVERTER

    fun getMonth(position: Int): String {
        val months = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "Oktober", "November", "December")

        return months[position]
    }

    fun getDayOfWeek(position: Int): String {
        val dayOfWeek = arrayOf("Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu")

        return dayOfWeek[position]
    }

    fun getHour(hour: Int): String {
        var textHour = ""

        textHour = if (hour < 10) {
            "0$hour"
        } else {
            hour.toString()
        }

        return textHour
    }

    fun getMinute(minute: Int): String {
        var textMinute = ""

        if (minute < 10) {
            textMinute = "0$minute"
        } else {
            textMinute = minute.toString()
        }

        return textMinute
    }
}
