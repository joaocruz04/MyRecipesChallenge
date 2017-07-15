package pt.joaocruz.myrecipeschallenge.recipe_screen

import pt.joaocruz.myrecipeschallenge.model.Recipe

/**
 * Created by joaocruz04 on 15/07/2017.
 */
interface RecipePageView {

    fun updateWithRecipe(recipe: Recipe)
    fun showError(message: String)
    fun updateFavorite(favorite: Boolean)

}