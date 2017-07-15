package pt.joaocruz.myrecipeschallenge.recipes_screen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_recipes_view_impl.*
import org.jetbrains.anko.dip
import pt.joaocruz.myrecipeschallenge.App
import pt.joaocruz.myrecipeschallenge.R
import pt.joaocruz.myrecipeschallenge.model.Recipe
import javax.inject.Inject
import android.view.MenuInflater
import android.view.MotionEvent
import android.widget.Toast
import org.jetbrains.anko.toast
import pt.joaocruz.myrecipeschallenge.model.User
import pt.joaocruz.myrecipeschallenge.recipe_screen.RecipePageViewImpl
import pt.joaocruz.myrecipeschallenge.ui.LoginDialog


class RecipesViewImpl : RecipesView, AppCompatActivity(), LoginDialog.LoginDialogListener, GridAdapter.OnItemClickListener {


    @Inject
    lateinit var presenter: RecipesPresenter
    private var adapter: GridAdapter?=null
    private var loginDialog: LoginDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_view_impl)
        setTitle("Recipes List")
        App.getInstance().appComponent.inject(this)
        setupRecyclerView()
        presenter.registerView(this)
        presenter.getRecipes()
    }

    fun setupRecyclerView() {
        adapter = GridAdapter()
        adapter?.setOnItemClickListener(this)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.addItemDecoration(GridAdapter.SpacesItemDecoration(dip(2.5F)))
        recyclerView.adapter = adapter
    }

    override fun onItemClicked(recipe: Recipe) {
        presenter.recipeSelected(recipe)
    }

    // Interface
    override fun updateWithRecipes(recipes: ArrayList<Recipe>) {
        adapter?.addItems(recipes)
    }

    override fun showRecipeDetailPage(recipe: Recipe) {
        val intent = Intent(this, RecipePageViewImpl::class.java)
        intent.putExtra("id", recipe.id)
        startActivity(intent)
    }

    // Login

    fun loginButtonPressed() {
        loginDialog = LoginDialog().listener(this)
        loginDialog?.show(supportFragmentManager, "logindialog")
    }

    override fun onLoginSubmit(email: String, password: String) {
        presenter.loginWithEmailAndPassword(email, password)
    }

    override fun showLoginErrorMessage(message: String) {
        toast(message)
    }

    override fun loginSuccess(user: User) {
        hideLoginDialogIfShown()
    }

    override fun showLoginParametersErrorMessage(message: String) {
        toast(message)
    }

    fun hideLoginDialogIfShown() {
        loginDialog?.dismiss()
    }

    // Processing dialog

    override fun showProcessingDialog() {
        // Show the processing popup spinner
    }

    override fun hideProcessingDialog() {
        // Hide the processing popup spinner
    }


    // Menu

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_login -> {
                loginButtonPressed()
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }



}
