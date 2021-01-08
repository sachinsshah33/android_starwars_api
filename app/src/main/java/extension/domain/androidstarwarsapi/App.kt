package extension.domain.androidstarwarsapi

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.paging.ExperimentalPagingApi
import extension.domain.androidstarwarsapi.activities.MainActivity
import java.lang.ref.WeakReference

@ExperimentalPagingApi
class App : Application() {

    companion object {
        var isDeveloper = true
        //TODO DISABLE FOR PRODUCTION

        private var mainActivity: WeakReference<MainActivity>? = null
        private var currentActivity: WeakReference<Activity>? = null

        lateinit var AppContext: Context

        val getCurrentActivity: Activity? get() = currentActivity?.get()
        val getMainActivity: Activity? get() = mainActivity?.get()

        //val getIMain: IMainFunctions? get() = driverMainActivity?.get()?.getIMain

        val isAppContextInitialized: Boolean get() = ::AppContext.isInitialized
    }

    override fun onCreate() {
        super.onCreate()
        AppContext = this

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (activity is MainActivity) {
                    mainActivity = WeakReference(activity)
                }
                currentActivity = WeakReference(activity)
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (activity is MainActivity) {
                    mainActivity?.clear()
                }
            }

            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {
                if (activity is MainActivity) {
                    mainActivity = WeakReference(activity)
                }
                currentActivity = WeakReference(activity)
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        })
    }
}
