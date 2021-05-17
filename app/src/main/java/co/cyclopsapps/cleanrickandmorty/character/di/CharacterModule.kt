package co.cyclopsapps.cleanrickandmorty.character.di

import co.cyclopsapps.cleanrickandmorty.character.detail.DetailFragment
import co.cyclopsapps.cleanrickandmorty.character.list.ui.CategoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Carlos Daniel Agudelo on 16/05/2021.
 */

@Module
abstract class CharacterModule {

    @ContributesAndroidInjector
    abstract fun bindDetailFragment(): DetailFragment

    @ContributesAndroidInjector
    abstract fun bindCategoryFragment(): CategoryFragment

}