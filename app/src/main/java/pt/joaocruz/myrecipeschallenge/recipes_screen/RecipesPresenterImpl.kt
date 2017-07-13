package pt.joaocruz.myrecipeschallenge.recipes_screen

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pt.joaocruz.myrecipeschallenge.network.ServicesManager
import pt.joaocruz.myrecipeschallenge.use_case.GetFeedUseCase

/**
 * Created by jcruz on 13.07.17.
 */
class RecipesPresenterImpl(servicesManager: ServicesManager?) : RecipesPresenter {

    var sm: ServicesManager? = servicesManager
    var view : RecipesView?=null

    override fun registerView(view: RecipesView) {
        this.view = view
    }

    override fun getRecipes() {
        GetFeedUseCase(sm).build()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    println("Wee")
                }
    }

}