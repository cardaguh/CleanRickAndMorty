package co.cyclopsapps.cleanrickandmorty.views.di

import androidx.lifecycle.ViewModel
import co.cyclopsapps.cleanrickandmorty.utilities.ViewModelKey
import co.cyclopsapps.cleanrickandmorty.views.MainActivity
import co.cyclopsapps.cleanrickandmorty.views.MainFragment
import co.cyclopsapps.cleanrickandmorty.views.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindMainFragment(): MainFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}