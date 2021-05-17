package co.cyclopsapps.cleanrickandmorty.views.di

import co.cyclopsapps.cleanrickandmorty.views.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}