package pt.joaocruz.myrecipeschallenge.recipes_screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pt.joaocruz.myrecipeschallenge.R


class RecipesViewImpl : RecipesView, AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_view_impl)
    }
}
