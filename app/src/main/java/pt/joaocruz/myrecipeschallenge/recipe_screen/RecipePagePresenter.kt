package pt.joaocruz.myrecipeschallenge.recipe_screen

import pt.joaocruz.myrecipeschallenge.model.Recipe


/**
 * Created by joaocruz04 on 15/07/2017.
 */
interface RecipePagePresenter {

    fun registerView(view: RecipePageView)
    fun loadRecipe(id: String?)
    fun favoritePressed(isFavorite: Boolean, recipe: Recipe)

}