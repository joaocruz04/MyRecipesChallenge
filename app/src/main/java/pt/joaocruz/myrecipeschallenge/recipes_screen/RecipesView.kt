package pt.joaocruz.myrecipeschallenge.recipes_screen

import pt.joaocruz.myrecipeschallenge.model.Recipe
import pt.joaocruz.myrecipeschallenge.model.User

/**
 * Created by jcruz on 13.07.17.
 */
interface RecipesView {

    fun updateWithRecipes(recipes: ArrayList<Recipe>)
    fun showLoginErrorMessage(message: String)
    fun showProcessingDialog()
    fun hideProcessingDialog()
    fun loginSuccess(user: User)
    fun showLoginParametersErrorMessage(message: String)
    fun showRecipeDetailPage(recipe: Recipe)
}