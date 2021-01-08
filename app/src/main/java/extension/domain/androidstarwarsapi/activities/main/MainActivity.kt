package extension.domain.androidstarwarsapi.activities.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import extension.domain.androidstarwarsapi.R
import extension.domain.androidstarwarsapi.activities.detail.DetailActivity
import extension.domain.androidstarwarsapi.data.models.PeopleModel
import extension.domain.androidstarwarsapi.data.people.PeopleViewModel
import extension.domain.androidstarwarsapi.extensions.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class MainActivity : AppCompatActivity(), ((PeopleModel?) -> Unit) {
    val viewModel by lazy {
        ViewModelProvider(this).get(PeopleViewModel::class.java)
    }

    var peoplePaginatedAdapter: PeoplePaginatedAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        peoplePaginatedAdapter = PeoplePaginatedAdapter(this)
        people_recycler_view?.apply {
            addItemDecoration(
                DividerItemDecorator(
                ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.divider
                )!!
            )
            )
            adapter = peoplePaginatedAdapter
        }


        fetch()
        try_again_fab?.setOnClickListener {
            fetch()
        }

    }


    override fun invoke(item: PeopleModel?) {
        item?.let {
            startActivity(Intent(this, DetailActivity::class.java).apply {
                putExtra("item", item)
            })
        }
    }

    @SuppressLint("CheckResult")
    private fun fetch() {
        showTapToRetry(false)
        viewModel.getPeopleFromCloudPaginated().subscribe {
            lifecycleScope.launch {
                peoplePaginatedAdapter?.submitData(it)
            }
        }
    }
    fun loadFromCache(){
        //would use something like this to cache the values for potential offline use
        viewModel.getPeopleFromCloud()
        lifecycleScope.launch {
            viewModel.getPeopleFromLocalPaginated().distinctUntilChanged().collectLatest {
                peoplePaginatedAdapter?.submitData(it)
            }
        }
    }

    val handler = Handler(Looper.getMainLooper())
    fun showTapToRetry(show:Boolean=true){
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            try_again_fab?.visibility = if (show) View.VISIBLE else View.GONE
            if(show){
                people_recycler_view?.visibility = if (peoplePaginatedAdapter?.itemCount==0) View.GONE else View.VISIBLE
                empty_screen_message?.visibility = if (peoplePaginatedAdapter?.itemCount==0) View.VISIBLE else View.GONE

                if (peoplePaginatedAdapter?.itemCount!=0){
                    toast(getString(R.string.empty_message))
                    //only toasting here as this would appear if there are some items in the list and then it fails
                }
            }
            else{
                people_recycler_view?.visibility = View.VISIBLE
                empty_screen_message?.visibility = View.GONE
            }
        }, 500)
        //added this hacky delay to stop FloatingActionButton from quickly hiding and showing, if it fails consecutively
    }
}