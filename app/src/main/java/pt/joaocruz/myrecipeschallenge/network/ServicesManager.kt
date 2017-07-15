package pt.joaocruz.myrecipeschallenge.network

import io.reactivex.Observable
import pt.joaocruz.myrecipeschallenge.model.Recipe
import pt.joaocruz.myrecipeschallenge.model.User

/**
 * Created by joaocruz04 on 15/07/2017.
 */
interface ServicesManager {

    fun login(email: String, password: String): Observable<User>
    fun getRecipes(): Observable<ArrayList<Recipe>>
    fun favorite(recipe: Recipe?, boolean: Boolean?): Observable<Boolean>
}