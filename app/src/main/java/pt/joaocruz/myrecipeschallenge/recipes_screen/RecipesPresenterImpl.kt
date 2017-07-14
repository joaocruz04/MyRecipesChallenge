package pt.joaocruz.myrecipeschallenge.recipes_screen

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pt.joaocruz.myrecipeschallenge.use_case.GetFeedUseCase

/**
 * Created by jcruz on 13.07.17.
 */
class RecipesPresenterImpl : RecipesPresenter {

    var view : RecipesView?=null

    override fun registerView(view: RecipesView) {
        this.view = view
    }

    override fun getRecipes() {
        GetFeedUseCase().build()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view?.updateWithRecipes(it)
                }
    }

}