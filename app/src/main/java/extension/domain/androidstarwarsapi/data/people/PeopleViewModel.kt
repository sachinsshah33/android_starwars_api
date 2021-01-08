package extension.domain.androidstarwarsapi.data.people

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import extension.domain.androidstarwarsapi.data.models.PeopleModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged


class PeopleViewModel(application: Application) : AndroidViewModel(application) {
    var repo: PeopleRepo? = null
    init {
        repo = PeopleRepo()
    }

    fun getPeopleFromLocalPaginated(): Flow<PagingData<PeopleModel>> {
        return repo!!.getPeopleFromLocalPaginated().cachedIn(viewModelScope).distinctUntilChanged()
    }

    fun getPeopleFromCloud(page: Int = 1): LiveData<PeopleModel>{
        return repo!!.getPeopleFromCloud()
    }

}