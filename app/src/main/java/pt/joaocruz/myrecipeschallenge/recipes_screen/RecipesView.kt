package pt.joaocruz.myrecipeschallenge.recipes_screen

import pt.joaocruz.myrecipeschallenge.model.Recipe

/**
 * Created by jcruz on 13.07.17.
 */
interface RecipesView {

    fun updateWithRecipes(recipes: ArrayList<Recipe>)
}