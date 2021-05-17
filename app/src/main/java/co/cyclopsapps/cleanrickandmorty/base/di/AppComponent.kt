package co.cyclopsapps.cleanrickandmorty.base.di

import android.app.Application
import co.cyclopsapps.cleanrickandmorty.base.App
import co.cyclopsapps.cleanrickandmorty.utilities.di.UtilsModule
import co.cyclopsapps.cleanrickandmorty.character.di.CharacterModule
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
        CharacterModule::class,
        ViewModelProviderModule::class// De cajon copy paste
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