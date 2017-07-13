package pt.joaocruz.myrecipeschallenge.use_case

import de.geschenkidee.geschenkidee.usecase.UseCase
import io.reactivex.Observable
import pt.joaocruz.myrecipeschallenge.model.Recipe
import pt.joaocruz.myrecipeschallenge.network.ServicesManager

/**
 * Created by jcruz on 13.07.17.
 */
class GetFeedUseCase(servicesManager: ServicesManager?) : UseCase {

    val sm = servicesManager

    override fun build(): Observable<ArrayList<Recipe>> {
        return sm?.getRecipes()?: Observable.just(ArrayList())
    }


}