package pt.joaocruz.myrecipeschallenge.recipes_screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_recipes_view_impl.*
import org.jetbrains.anko.dip
import pt.joaocruz.myrecipeschallenge.App
import pt.joaocruz.myrecipeschallenge.R
import pt.joaocruz.myrecipeschallenge.model.Recipe
import javax.inject.Inject


class RecipesViewImpl : RecipesView, AppCompatActivity() {

    @Inject
    lateinit var presenter: RecipesPresenter
    private var adapter: GridAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_view_impl)
        App.getInstance().appComponent.inject(this)
        setupRecyclerView()
        presenter.registerView(this)
        presenter.getRecipes()
    }

    override fun updateWithRecipes(recipes: ArrayList<Recipe>) {
        adapter?.addItems(recipes)
    }

    fun setupRecyclerView() {
        adapter = GridAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.addItemDecoration(GridAdapter.SpacesItemDecoration(dip(2.5F)))
        recyclerView.adapter = adapter
    }
}
