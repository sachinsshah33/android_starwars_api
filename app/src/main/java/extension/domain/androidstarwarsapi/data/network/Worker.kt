package extension.domain.androidstarwarsapi.data.network

import androidx.paging.ExperimentalPagingApi
import extension.domain.androidstarwarsapi.App
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

@ExperimentalPagingApi
object Worker {
    private var mClient: OkHttpClient? = null
    val client: OkHttpClient
        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        get() {
            if (mClient == null) {
                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        if(App.isDeveloper){
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    })
                    .addInterceptor {
                        it.proceed(it.request().newBuilder().addHeader("Content-Type", "application/json").build())
                    }
                mClient = httpBuilder.build()
            }
            return mClient!!
        }
}