package pt.joaocruz.myrecipeschallenge.use_case

import de.geschenkidee.geschenkidee.usecase.UseCase
import io.reactivex.Observable
import pt.joaocruz.myrecipeschallenge.App
import pt.joaocruz.myrecipeschallenge.model.Recipe
import pt.joaocruz.myrecipeschallenge.model.User
import pt.joaocruz.myrecipeschallenge.network.ServicesImpl
import pt.joaocruz.myrecipeschallenge.network.ServicesManager
import javax.inject.Inject

/**
 * Created by jcruz on 13.07.17.
 */
class FavoriteUseCase : UseCase {

    var sm: ServicesManager?=null
    var favorite: Boolean?=null
    var recipe: Recipe?=null


    constructor(servicesManager: ServicesManager?, recipe: Recipe, favorite: Boolean) {
        this.favorite = favorite
        this.recipe = recipe
        this.sm = servicesManager
    }


    override fun build(): Observable<Boolean> {
        App.getInstance().appComponent.inject(this)
        return sm?.favorite(recipe, favorite)?: Observable.just(false)
    }


}