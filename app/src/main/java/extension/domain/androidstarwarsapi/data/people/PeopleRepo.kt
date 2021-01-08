package extension.domain.androidstarwarsapi.data.people

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import extension.domain.androidstarwarsapi.App
import extension.domain.androidstarwarsapi.Constants
import extension.domain.androidstarwarsapi.data.local.AppDatabase
import extension.domain.androidstarwarsapi.data.models.PeopleModel
import extension.domain.androidstarwarsapi.data.network.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PeopleRepo() {
    private var dao: PeopleDAO? = null

    init {
        val myDatabase =
            AppDatabase.getInstance(App.AppContext)
        dao = myDatabase.peopleDAO()

    }


    fun insert(model: PeopleModel?=null, models: List<PeopleModel>?=null){
        ArrayList<PeopleModel>().apply {
            model?.let {
                add(it)
            }
            models?.let {
                addAll(it)
            }
            if(!isNullOrEmpty()){
                dao?.insert(this)
            }
        }
    }
    fun update(model: PeopleModel?=null, models: List<PeopleModel>?=null){
        ArrayList<PeopleModel>().apply {
            model?.let {
                add(it)
            }
            models?.let {
                addAll(it)
            }
            if(!isNullOrEmpty()){
                dao?.update(this)
            }
        }
    }
    fun delete(model: PeopleModel?=null, models: List<PeopleModel>?=null){
        ArrayList<PeopleModel>().apply {
            model?.let {
                add(it)
            }
            models?.let {
                addAll(it)
            }
            if(!isNullOrEmpty()){
                dao?.delete(this)
            }
        }
    }

    fun deleteAll(){
        dao?.deleteAll()
    }

    fun getPeopleFromLocalPaginatedFlow(): PagingSource<Int, PeopleModel> {
        return dao!!.getPeopleFromLocalPaginated()
    }
    fun getPeopleFromLocalPaginated(): Flow<PagingData<PeopleModel>> {
        return Pager(config = PagingConfig(pageSize = Constants.pagedSize, enablePlaceholders = false), pagingSourceFactory = { getPeopleFromLocalPaginatedFlow() }).flow
    }



    @SuppressLint("CheckResult")
    fun getPeopleFromCloud(page: Int = 1): LiveData<PeopleModel> {
        val response = MutableLiveData<PeopleModel>()
        APIService.invoke().people(page = page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({ result ->
                when {
                    result.body()?.results==null -> {
                        "error"
                    }
                    result.body()!!.results!!.isEmpty() -> {
                        "end"
                    }
                    else -> {
                        insert(models = result.body()!!.results!!)
                    }
                }
            }, { error ->
                "error"
                error.printStackTrace()
            })
        return response
    }
}