package pt.joaocruz.myrecipeschallenge.recipes_screen

import pt.joaocruz.myrecipeschallenge.model.Recipe

/**
 * Created by jcruz on 13.07.17.
 */
interface RecipesPresenter {

    fun registerView(view: RecipesView)
    fun getRecipes()
    fun loginWithEmailAndPassword(email: String, password: String)
    fun recipeSelected(recipe: Recipe?)
}