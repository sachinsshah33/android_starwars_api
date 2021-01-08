package extension.domain.androidstarwarsapi.extensions

import com.ibm.icu.text.RuleBasedNumberFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


fun Number.toOrdinal() = RuleBasedNumberFormat(Locale(Locale.getDefault().language), RuleBasedNumberFormat.ORDINAL).format(this)?:this.toString()

fun Number.withCommas() = NumberFormat.getNumberInstance(Locale(Locale.getDefault().language)).format(this)?:this.toString()

fun Number.toMonth(short:Boolean=false): String{//MONTHS START FROM 0=January
    return SimpleDateFormat(if (short) "LLL" else "LLLL", Locale(Locale.getDefault().language)).format(Calendar.getInstance().apply { set(Calendar.MONTH, this@toMonth.toInt()) }.time)
}
fun Number.toDay(short:Boolean=false): String{//DAYS START FROM 1=Sunday
    return SimpleDateFormat(if (short) "EEE" else "EEEE", Locale(Locale.getDefault().language)).format(Calendar.getInstance().apply { set(Calendar.DAY_OF_WEEK, this@toDay.toInt()) }.time)
}

fun Number.toHours(isSeconds:Boolean=false) = (if (isSeconds) TimeUnit.SECONDS else TimeUnit.MILLISECONDS).toHours(this.toLong()).toInt()
fun Number.toMinutes(isSeconds:Boolean=false) = (if (isSeconds) TimeUnit.SECONDS else TimeUnit.MILLISECONDS).toMinutes(this.toLong()).toInt()