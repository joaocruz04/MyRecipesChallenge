package pt.joaocruz.myrecipeschallenge.recipes_screen

/**
 * Created by jcruz on 13.07.17.
 */
interface RecipesPresenter {

    fun registerView(view: RecipesView)
    fun getRecipes()
    fun loginWithEmailAndPassword(email: String, password: String)
}