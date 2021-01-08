package extension.domain.androidstarwarsapi.extensions

import org.apache.commons.lang3.time.DateUtils
import java.text.SimpleDateFormat
import java.util.*


fun Date.toCalander(): Calendar {
    val date = this
    return Calendar.getInstance().apply { time = date }
}

fun Date.getMonthAsString(makeShort: Boolean = false): String {
    return (this.getMonthAsInt()-1).toMonth(makeShort)
}
fun Date.getMonthAsInt(): Int {
    return this.toCalander().get(Calendar.MONTH)+1
}
fun Date.getDayOfMonthAsString(): String {
    return this.toCalander().get(Calendar.DAY_OF_MONTH).toOrdinal()
}

fun Date.getYearAsString(makeShort: Boolean? = false): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val year = calendar.get(Calendar.YEAR).toString()

    if(makeShort!=null && makeShort){
        return year.substring(year.length-2, 2)
    }
    return year
}

fun Date.getDayOfWeekAsString(makeShort: Boolean = false): String {
    return this.toCalander().get(Calendar.DAY_OF_WEEK).toDay(makeShort)
}

fun Date.getAmOrPmAsString(): String {
    return SimpleDateFormat("a", Locale(Locale.getDefault().language)).format(this)
}

fun Date.getHourAsString(is12Hour: Boolean? = false): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    if(is12Hour!=null && is12Hour){
        return calendar.get(Calendar.HOUR).toString()
    }
    return String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY))
}

fun Date.getMinuteAsString(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return String.format("%02d", calendar.get(Calendar.MINUTE))
}
fun Date.getDateInReadableString(): String {
    return this.getDayOfWeekAsString(true)+" "+this.getDayOfMonthAsString()+" "+this.getMonthAsString(true)+" "+this.getYearAsString()
}
fun Date.getTimeInReadibleString(): String {
    return this.getHourAsString()+":"+this.getMinuteAsString()+this.getAmOrPmAsString()
}