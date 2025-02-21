package ua.od.okkt.androidcourse

import android.app.Application
import org.koin.core.context.startKoin
import ua.od.okkt.androidcourse.di.appModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}