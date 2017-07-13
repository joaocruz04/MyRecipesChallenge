package pt.joaocruz.myrecipeschallenge.dagger

import dagger.Module
import dagger.Provides
import pt.joaocruz.myrecipeschallenge.network.ServicesManager
import pt.joaocruz.myrecipeschallenge.recipes_screen.RecipesPresenter
import pt.joaocruz.myrecipeschallenge.recipes_screen.RecipesPresenterImpl
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by jcruz on 13.07.17.
 */

@Module
class AppModule {

    @Provides
    internal fun provideRecipesPresenter(servicesManager: ServicesManager): RecipesPresenter {
        return RecipesPresenterImpl(servicesManager)
    }

    @Singleton
    @Provides
    internal fun provideServicesManager(): ServicesManager {
        return ServicesManager()
    }

}