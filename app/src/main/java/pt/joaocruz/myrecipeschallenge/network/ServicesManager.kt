package pt.joaocruz.myrecipeschallenge.network

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import io.reactivex.Observable
import pt.joaocruz.myrecipeschallenge.App
import pt.joaocruz.myrecipeschallenge.model.Recipe
import java.io.IOException

/**
 * Created by jcruz on 13.07.17.
 */
class ServicesManager {

    fun getRecipes(): Observable<ArrayList<Recipe>> {
        val array: ArrayList<Recipe> = ArrayList()
        /*
        val parser = Parser()
        var temparray: JsonArray<JsonObject>?=null
        try {
            var istream = App.getInstance().assets.open("recipes.json")
            temparray = parser.parse(istream) as JsonArray<JsonObject>?
            istream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        temparray?.forEach {
            array.add(i)
        }
        */
        return Observable.just(array)
    }

}