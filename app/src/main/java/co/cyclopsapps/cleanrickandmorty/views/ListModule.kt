package co.cyclopsapps.cleanrickandmorty.views

import androidx.lifecycle.ViewModel
import co.cyclopsapps.cleanrickandmorty.utilities.ViewModelKey
import co.cyclopsapps.cleanrickandmorty.views.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Carlos Daniel Agudelo on 16/05/2021.
 */

@Module
abstract class ListModule {

    @ContributesAndroidInjector
    abstract fun bindMainFragment(): MainFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}