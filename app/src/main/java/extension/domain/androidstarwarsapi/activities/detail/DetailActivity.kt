package extension.domain.androidstarwarsapi.activities.detail

import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.ExperimentalPagingApi
import extension.domain.androidstarwarsapi.R
import extension.domain.androidstarwarsapi.data.models.PeopleModel
import extension.domain.androidstarwarsapi.extensions.getDateInReadableString
import extension.domain.androidstarwarsapi.extensions.getTimeInReadibleString
import kotlinx.android.synthetic.main.activity_detail.*

@ExperimentalPagingApi
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val item = intent.getParcelableExtra<PeopleModel>("item")!!



        name_value?.apply {
            if(item.created!=null){
                text = item.name
                visibility = View.VISIBLE
            }
            else{
                visibility = View.VISIBLE
            }
        }
        //could make above label redundant and set name to the title of activity: title = item.name


        height_value?.apply {
            if(item.created!=null){
                text = item.height
                visibility = View.VISIBLE
            }
            else{
                visibility = View.VISIBLE
            }
        }


        mass_value?.apply {
            if(item.created!=null){
                text = item.mass
                visibility = View.VISIBLE
            }
            else{
                visibility = View.VISIBLE
            }
        }


        data_record_created_value?.apply {
            if(item.created!=null){
                text = String.format(
                    getString(
                        R.string.data_record_created_value
                    ), item.created?.getDateInReadableString(), item.created?.getTimeInReadibleString())
                visibility = View.VISIBLE
            }
            else{
                visibility = View.VISIBLE
            }
        }
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed()
            true
        } else super.onKeyDown(keyCode, event)
    }
    override fun onBackPressed() {
        finish()
    }
}