package ua.od.okkt.androidcourse

import android.app.Application
import ua.od.okkt.androidcourse.di.AppModule
import ua.od.okkt.androidcourse.di.DaggerAppComponent

class App : Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }
}