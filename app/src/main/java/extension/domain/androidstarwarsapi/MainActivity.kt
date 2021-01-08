package extension.domain.androidstarwarsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import extension.domain.androidstarwarsapi.data.models.PeopleModel
import extension.domain.androidstarwarsapi.data.people.PeopleViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ((PeopleModel?) -> Unit) {
    val viewModel by lazy {
        ViewModelProvider(this).get(PeopleViewModel::class.java)
    }

    var peoplePaginatedAdapter: PeoplePaginatedAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peoplePaginatedAdapter = PeoplePaginatedAdapter(this)

        peopleRecyclerView?.adapter = peoplePaginatedAdapter
        viewModel.getPeopleFromCloud()
        lifecycleScope.launch {
            viewModel.getPeopleFromLocalPaginated().distinctUntilChanged().collectLatest {
                peoplePaginatedAdapter?.submitData(it)
            }
        }

    }

    override fun invoke(p1: PeopleModel?) {

    }
}