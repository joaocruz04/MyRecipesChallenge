package pt.joaocruz.myrecipeschallenge.recipe_screen

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pt.joaocruz.myrecipeschallenge.data.DataManager
import pt.joaocruz.myrecipeschallenge.model.Recipe
import pt.joaocruz.myrecipeschallenge.network.ServicesManager
import pt.joaocruz.myrecipeschallenge.use_case.FavoriteUseCase

/**
 * Created by joaocruz04 on 15/07/2017.
 */
class RecipePagePresenterImpl(servicesManager: ServicesManager, dataManager: DataManager) : RecipePagePresenter {

    val sm = servicesManager
    val dm = dataManager
    var view: RecipePageView?=null

    override fun registerView(view: RecipePageView) {
        this.view = view
    }

    override fun loadRecipe(id: String?) {
        if (id!=null) {
            val recipe = dm.loadRecipe(id)
            if (recipe != null)
                view?.updateWithRecipe(recipe)
        }
    }

    override fun favoritePressed(isFavorite: Boolean, recipe: Recipe) {
        FavoriteUseCase(sm, recipe, isFavorite).build()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it) {
                        view?.updateFavorite(isFavorite)
                    } else {
                        view?.showError("Service error")
                    }
                }
    }
}