package pt.joaocruz.myrecipeschallenge.recipes_screen

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener
import pt.joaocruz.myrecipeschallenge.R
import pt.joaocruz.myrecipeschallenge.model.Recipe



/**
 * Created by jcruz on 14.07.17.
 */
class RecipeViewHolder (view: View, gridAdapter: GridAdapter) : RecyclerView.ViewHolder(view) {

    val adapter = gridAdapter
    val image: ImageView = view.findViewById(R.id.thumbnail)
    val title: TextView = view.findViewById(R.id.title)
    val headline: TextView = view.findViewById(R.id.headline)
    val difficulty: TextView = view.findViewById(R.id.difficulty)
    var recipe: Recipe?=null

    init {
        view.setOnClickListener {
            if (recipe!=null)
                adapter.onItemClick(recipe!!)
        }
    }


    fun bindRecipe(recipe: Recipe, height: Int) {
        this.recipe = recipe
        title.text = recipe.name ?: ""
        headline.text = recipe.headline ?: ""
        headline.height = height
        difficulty.text = "${recipe.difficulty?:"-"}"

        ImageLoader.getInstance().loadImage(recipe.thumb, object: ImageLoadingListener{
            override fun onLoadingComplete(imageUri: String?, view: View?, loadedImage: Bitmap?) {
                image.setImageBitmap(loadedImage)
            }

            override fun onLoadingStarted(imageUri: String?, view: View?) {
                image.setImageBitmap(null)
            }

            override fun onLoadingCancelled(imageUri: String?, view: View?) {}
            override fun onLoadingFailed(imageUri: String?, view: View?, failReason: FailReason?) {}

        })
    }

}