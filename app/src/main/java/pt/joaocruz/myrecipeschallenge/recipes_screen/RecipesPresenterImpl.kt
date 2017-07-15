package pt.joaocruz.myrecipeschallenge.recipes_screen

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pt.joaocruz.myrecipeschallenge.data.DataManager
import pt.joaocruz.myrecipeschallenge.model.Recipe
import pt.joaocruz.myrecipeschallenge.network.ServicesManager
import pt.joaocruz.myrecipeschallenge.use_case.GetFeedUseCase
import pt.joaocruz.myrecipeschallenge.use_case.LoginUseCase

/**
 * Created by jcruz on 13.07.17.
 */
class RecipesPresenterImpl(servicesManager: ServicesManager, dataManager: DataManager) : RecipesPresenter {

    var view : RecipesView?=null
    var servicesManager = servicesManager
    var dataManager = dataManager

    override fun registerView(view: RecipesView) {
        this.view = view
    }

    override fun getRecipes() {
        GetFeedUseCase(servicesManager).build()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view?.updateWithRecipes(it)
                }
    }

    override fun loginWithEmailAndPassword(email: String, password: String) {
        val emailError = "Please insert valid email"
        val passwordError = "Password must be at least 5 character long"
        val bothError = "Please insert valid email and password"

        val validEmail = validEmail(email)
        val validPassword = validPassword(password)

        if (!validEmail && !validPassword)
            view?.showLoginParametersErrorMessage(bothError)
        else if (!validEmail)
            view?.showLoginParametersErrorMessage(emailError)
        else if (!validPassword)
            view?.showLoginParametersErrorMessage(passwordError)
        else {
            view?.showProcessingDialog()
            LoginUseCase(servicesManager, email, password).build()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (it.email == null)
                            view?.showLoginErrorMessage("Invalid login")
                        else
                            view?.loginSuccess(it)
                    }
        }
    }

    private fun validEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validPassword(password: String?): Boolean {
        return (password!=null && password.count()>4)
    }

    override fun recipeSelected(recipe: Recipe?) {
        if (recipe!=null) {
            dataManager.storeRecipe(recipe)
            view?.showRecipeDetailPage(recipe)
        }
    }
}