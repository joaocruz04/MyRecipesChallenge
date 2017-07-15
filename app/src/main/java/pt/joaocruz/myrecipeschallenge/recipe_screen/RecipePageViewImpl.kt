package pt.joaocruz.myrecipeschallenge.recipe_screen

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.activity_recipe_page.*
import org.jetbrains.anko.toast
import pt.joaocruz.myrecipeschallenge.App
import pt.joaocruz.myrecipeschallenge.R
import pt.joaocruz.myrecipeschallenge.model.Recipe
import javax.inject.Inject

/**
 * Created by joaocruz04 on 15/07/2017.
 */
class RecipePageViewImpl: RecipePageView, AppCompatActivity() {

    @Inject
    lateinit var presenter: RecipePagePresenter

    var recipe: Recipe?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_page)
        App.getInstance().appComponent.inject(this)
        presenter.registerView(this)
        val id = intent?.getStringExtra("id")
        presenter.loadRecipe(id)
        setTitle("Recipe detail")
    }

    // Interface

    override fun updateWithRecipe(recipe: Recipe) {
        this.recipe = recipe
        ImageLoader.getInstance().displayImage(recipe.image, image)
        titleLbl.text = recipe.name?:"-"
        headlineLbl.text = recipe.headline?:"-"
        carbos.text = recipe.carbos?:"?"
        proteins.text = recipe.proteins?:"?"
        fats.text = recipe.fats?:"?"
        calories.text = recipe.calories?:"?"
        fibers.text = recipe.fibers?:"?"
        description.text = recipe.description
        difficultyLbl.text = if (recipe.difficulty!=null) "${recipe.difficulty}" else "?"
        ratingBar.visibility = if (recipe.rating!=null) View.VISIBLE else View.GONE
        ratingBar.rating = recipe.rating?:0F


        var delIngredientsStr = ""
        var undelIngredientsStr = ""

        recipe.deliverable_ingredients?.forEach {
            delIngredientsStr += "- $it\n"
        }

        recipe.undeliverable_ingredients?.forEach {
            undelIngredientsStr += "- $it\n"
        }

        deliverableIngredients.text = if (delIngredientsStr.isEmpty()) "-" else delIngredientsStr
        undeliverableIngredients.text = if (undelIngredientsStr.isEmpty()) "-" else undelIngredientsStr
    }

    override fun showError(message: String) {
        toast(message)
    }

    override fun updateFavorite(favorite: Boolean) {
        // Change button drawable..
    }

    // Menu

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.recipe_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                if (recipe!=null)
                    presenter.favoritePressed(true, recipe!!)
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }


}