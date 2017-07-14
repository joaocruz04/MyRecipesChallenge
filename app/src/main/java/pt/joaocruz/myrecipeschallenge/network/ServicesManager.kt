package pt.joaocruz.myrecipeschallenge.network


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import io.reactivex.Observable
import pt.joaocruz.myrecipeschallenge.App
import pt.joaocruz.myrecipeschallenge.model.Recipe
import pt.joaocruz.myrecipeschallenge.model.User
import java.io.FileReader
import java.io.IOException


/**
 * Created by jcruz on 13.07.17.
 */
class ServicesManager {

    fun login(email: String, password: String): Observable<User> {
        return Observable.just(User())
    }

    fun getRecipes(): Observable<ArrayList<Recipe>> {
        val array: ArrayList<Recipe> = ArrayList()
        var gson: Gson = Gson()
        val turnsType = object : TypeToken<List<Recipe>>() {}.type
        val recipes = gson.fromJson<List<Recipe>>(loadFromFile(), turnsType)
        array.addAll(recipes)
        return Observable.just(array)
    }

    fun loadFromFile(): String {
        var json = ""
        try {
            val istream = App.getInstance().assets.open("recipes.json")
            val size = istream.available()
            val buffer = ByteArray(size)
            istream.read(buffer)
            istream.close()
            json = String(buffer)
        }
        catch (e: IOException) {}
        catch (e: Exception) {}

        return json
    }

}