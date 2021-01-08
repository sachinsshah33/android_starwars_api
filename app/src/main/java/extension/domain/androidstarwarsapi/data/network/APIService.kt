package extension.domain.androidstarwarsapi.data.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import extension.domain.androidstarwarsapi.data.models.PeopleAPIModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*

interface APIService{
    @GET("api/{path}/")
    fun people(@Path("path") id: String = "people", @Query("page") page: Int = 1): io.reactivex.Observable<Response<PeopleAPIModel>>

    companion object{
        operator fun invoke() : APIService{
            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmmSSS'Z'")
                .registerTypeAdapter(Date::class.java, DateDeserializer())
                .excludeFieldsWithoutExposeAnnotation()
                .create()

            return Retrofit.Builder()
                .baseUrl("https://swapi.dev/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(Worker.client)
                .build()
                .create(APIService::class.java)
        }
    }
}