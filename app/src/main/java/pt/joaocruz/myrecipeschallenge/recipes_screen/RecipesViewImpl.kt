package pt.joaocruz.myrecipeschallenge.recipes_screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pt.joaocruz.myrecipeschallenge.App
import pt.joaocruz.myrecipeschallenge.R
import javax.inject.Inject


class RecipesViewImpl : RecipesView, AppCompatActivity() {

    @Inject
    lateinit var presenter: RecipesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_view_impl)
        App.getInstance().appComponent.inject(this)
        presenter.registerView(this)
        presenter.getRecipes()
    }
}
