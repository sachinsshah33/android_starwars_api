package extension.domain.androidstarwarsapi.data.models

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import extension.domain.androidstarwarsapi.App
import extension.domain.androidstarwarsapi.Constants
import extension.domain.androidstarwarsapi.activities.main.MainActivity
import extension.domain.androidstarwarsapi.data.network.APIService
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class PeoplePagingSource : PagingSource<Int, PeopleModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PeopleModel> {
        val page = params.key ?: Constants.DEFAULT_PAGE_INDEX
        return try {
            val response = APIService.invoke().peoplePaginated(page = page)
            LoadResult.Page(
                response.results, prevKey = if (page == Constants.DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.next==null) null else page + 1
            )
        } catch (exception: IOException) {
            (App.getCurrentActivity as? MainActivity)?.showTapToRetry(true)
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            (App.getCurrentActivity as? MainActivity)?.showTapToRetry(true)
            return LoadResult.Error(exception)
        }
    }
}