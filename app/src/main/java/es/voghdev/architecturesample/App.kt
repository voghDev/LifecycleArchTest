package es.voghdev.architecturesample

import android.app.Application
import es.voghdev.architecturesample.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(koinModules)
        }
    }
}
