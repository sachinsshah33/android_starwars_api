package extension.domain.androidstarwarsapi

import extension.domain.androidstarwarsapi.extensions.getDateInReadableString
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

class ExampleUnitTest {
    @Test
    fun stringToReadableDate_isCorrect() {
        val dateString = "2014-12-10T16:20:44.310000Z"
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmmSSS'Z'", Locale.getDefault()).parse(dateString)
        val readableDateString = date.getDateInReadableString() + " @ " + date.getDateInReadableString()
        assertEquals("Wed 10th Dec 2014 @ Wed 10th Dec 2014", readableDateString)
    }
}