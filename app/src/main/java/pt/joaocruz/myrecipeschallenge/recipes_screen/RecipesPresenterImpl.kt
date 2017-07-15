package pt.joaocruz.myrecipeschallenge.recipes_screen

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pt.joaocruz.myrecipeschallenge.data.DataManager
import pt.joaocruz.myrecipeschallenge.model.Recipe
import pt.joaocruz.myrecipeschallenge.network.ServicesManager
import pt.joaocruz.myrecipeschallenge.use_case.GetFeedUseCase
import pt.joaocruz.myrecipeschallenge.use_case.LoginUseCase
import java.util.regex.Pattern

/**
 * Created by jcruz on 13.07.17.
 */
class RecipesPresenterImpl(servicesManager: ServicesManager, dataManager: DataManager, loginUseCase: LoginUseCase, ioScheduler: Scheduler, threadScheduler: Scheduler) : RecipesPresenter {

    var view : RecipesView?=null
    var servicesManager = servicesManager
    var dataManager = dataManager
    var loginUseCase = loginUseCase
    private val ioScheduler = ioScheduler
    private val threadScheduler = threadScheduler


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
            loginUseCase
                    .withEmailAndPassword(email, password)
                    .build()
                    .subscribeOn(threadScheduler)
                    .observeOn(ioScheduler)
                    .subscribe {
                        if (it.email == null) {
                            view?.showLoginErrorMessage("Invalid login")
                        }
                        else {
                            view?.loginSuccess(it)
                        }
                    }
        }
    }

    fun validEmail(email: String?): Boolean {
        if (email==null || email.isEmpty())
            return false
        else {
            val pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            val patternTool = Pattern.compile(pattern)
            return patternTool.matcher(email).matches()
        }
    }

    fun validPassword(password: String?): Boolean {
        return (password!=null && password.count()>4)
    }

    override fun recipeSelected(recipe: Recipe?) {
        if (recipe!=null) {
            dataManager.storeRecipe(recipe)
            view?.showRecipeDetailPage(recipe)
        }
    }
}