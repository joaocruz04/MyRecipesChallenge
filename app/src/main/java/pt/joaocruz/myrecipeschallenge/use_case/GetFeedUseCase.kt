package pt.joaocruz.myrecipeschallenge.use_case

import de.geschenkidee.geschenkidee.usecase.UseCase
import io.reactivex.Observable
import pt.joaocruz.myrecipeschallenge.App
import pt.joaocruz.myrecipeschallenge.model.Recipe
import pt.joaocruz.myrecipeschallenge.network.ServicesManager
import javax.inject.Inject

/**
 * Created by jcruz on 13.07.17.
 */
class GetFeedUseCase : UseCase {

    @Inject
    lateinit var sm : ServicesManager

    override fun build(): Observable<ArrayList<Recipe>> {
        App.getInstance().appComponent.inject(this)
        return sm.getRecipes()
    }


}