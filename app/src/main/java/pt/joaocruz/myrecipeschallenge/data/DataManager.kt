package pt.joaocruz.myrecipeschallenge.data

import pt.joaocruz.myrecipeschallenge.model.Recipe

/**
 * Created by joaocruz04 on 15/07/2017.
 */
interface DataManager {

    fun storeRecipe(recipe: Recipe)
    fun loadRecipe(id: String): Recipe?

}