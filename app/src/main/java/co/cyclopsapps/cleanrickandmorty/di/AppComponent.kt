package co.cyclopsapps.cleanrickandmorty.di

import android.app.Application
import co.cyclopsapps.cleanrickandmorty.base.di.App
import co.cyclopsapps.cleanrickandmorty.base.di.ViewModelProviderModule
import co.cyclopsapps.cleanrickandmorty.utilities.di.UtilsModule
import co.cyclopsapps.cleanrickandmorty.views.ListModule
import co.cyclopsapps.cleanrickandmorty.views.di.MainModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class, // De cajon copy paste
        UtilsModule::class,
        MainModule::class,
        ViewModelProviderModule::class,
        ListModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App) // lo unico que cambia es este por
                       // tu nombre de class que extiende de Application
}