package ua.od.okkt.androidcourse.di

// AppComponent.kt
import dagger.Component
import ua.od.okkt.androidcourse.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    // Тут ми вказуємо, куди Dagger може «вколювати» залежності
    fun inject(mainActivity: MainActivity)
}
