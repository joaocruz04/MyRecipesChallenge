package pt.joaocruz.myrecipeschallenge.dagger

import dagger.Component
import pt.joaocruz.myrecipeschallenge.recipes_screen.RecipesViewImpl
import pt.joaocruz.myrecipeschallenge.use_case.GetFeedUseCase
import javax.inject.Singleton

/**
 * Created by jcruz on 13.07.17.
 */


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(recipesViewImpl: RecipesViewImpl)
    fun inject(feedUseCase: GetFeedUseCase)
}