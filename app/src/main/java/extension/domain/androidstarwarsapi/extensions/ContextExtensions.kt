package extension.domain.androidstarwarsapi.extensions

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast


fun Context.toast(message: Any, length: Int = Toast.LENGTH_SHORT, centre: Boolean = true) {
    Toast.makeText(this, message.toString(), length).apply {
        if(centre){
            val v: TextView? = this.view?.findViewById(android.R.id.message)
            v?.gravity = Gravity.CENTER
        }
    }.show()
}
