package co.cyclopsapps.cleanrickandmorty.base.di

import androidx.lifecycle.ViewModelProvider
import co.cyclopsapps.cleanrickandmorty.utilities.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelProviderModule {
    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}