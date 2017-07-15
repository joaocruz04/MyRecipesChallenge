package pt.joaocruz.myrecipeschallenge.dagger

import dagger.Module
import dagger.Provides
import pt.joaocruz.myrecipeschallenge.data.DataManager
import pt.joaocruz.myrecipeschallenge.data.DataManagerImpl
import pt.joaocruz.myrecipeschallenge.network.ServicesImpl
import pt.joaocruz.myrecipeschallenge.network.ServicesManager
import pt.joaocruz.myrecipeschallenge.recipe_screen.RecipePagePresenter
import pt.joaocruz.myrecipeschallenge.recipe_screen.RecipePagePresenterImpl
import pt.joaocruz.myrecipeschallenge.recipes_screen.RecipesPresenter
import pt.joaocruz.myrecipeschallenge.recipes_screen.RecipesPresenterImpl
import javax.inject.Singleton

/**
 * Created by jcruz on 13.07.17.
 */

@Module
class AppModule {

    @Provides
    internal fun provideRecipesPresenter(servicesManager: ServicesManager, dataManager: DataManager): RecipesPresenter {
        return RecipesPresenterImpl(servicesManager, dataManager)
    }

    @Provides
    internal fun provideRecipePagePresenter(servicesManager: ServicesManager, dataManager: DataManager): RecipePagePresenter {
        return RecipePagePresenterImpl(servicesManager, dataManager)
    }

    @Singleton
    @Provides
    internal fun provideServicesManager(): ServicesManager {
        return ServicesImpl()
    }

    @Singleton
    @Provides
    internal fun provideDataManager(): DataManager {
        return DataManagerImpl()
    }

}