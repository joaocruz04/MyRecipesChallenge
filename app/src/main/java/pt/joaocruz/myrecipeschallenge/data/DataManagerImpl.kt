package pt.joaocruz.myrecipeschallenge.data

import pt.joaocruz.myrecipeschallenge.model.Recipe

/**
 * Created by joaocruz04 on 15/07/2017.
 */
open class DataManagerImpl: DataManager {

    var recipe: Recipe?=null

    override fun loadRecipe(id: String): Recipe? {
        if (recipe!=null && recipe?.id==id)
            return recipe
        else
            return null
    }

    override fun storeRecipe(recipe: Recipe) {
        this.recipe = recipe
    }
}