package pt.joaocruz.myrecipeschallenge.recipes_screen

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import org.jetbrains.anko.dip
import pt.joaocruz.myrecipeschallenge.App
import pt.joaocruz.myrecipeschallenge.R
import pt.joaocruz.myrecipeschallenge.inflate
import pt.joaocruz.myrecipeschallenge.model.Recipe

/**
 * Created by jcruz on 14.07.17.
 */
class GridAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val recipes: ArrayList<Recipe> = ArrayList()
    private var recyclerView: RecyclerView?=null
    private val heights: ArrayList<Int> = ArrayList()
    private var clickListener: OnItemClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView = parent.inflate(R.layout.recipe_list_item)
        val holder = RecipeViewHolder(inflatedView, this)
        return holder
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is RecipeViewHolder) {
            if (heights.size < position + 1)
                computeHeights(position, holder)
            holder.bindRecipe(recipes[position], heights[position])
        }
    }

    fun onItemClick(recipe: Recipe) {
        clickListener?.onItemClicked(recipe)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.clickListener = listener
    }

    fun getItemAt(position: Int): Recipe {
        return recipes[position]
    }

    // Computes the heights of a complete row of items, checking which is the highest
    fun computeHeights(position: Int, holder: RecipeViewHolder) {
        val m1 = measureTextViewsHeightAt(position, holder)
        if (position+1 < recipes.size) {
            val m2 = measureTextViewsHeightAt(position+1, holder)
            if (m2.third>m1.third) {
                heights.add(m2.third - m1.first)
                heights.add(m2.second)
            } else {
                heights.add(m1.second)
                heights.add(m1.third - m2.first)
            }
        } else
            heights.add(m1.second)
    }

    // Returns a Triple with respectively:
    // - Title TextView height
    // - Headline TextView height
    // - Sum of both heights
    fun measureTextViewsHeightAt(position: Int, holder: RecipeViewHolder): Triple<Int, Int, Int> {
        holder.title.text = recipes[position].name
        holder.headline.text = recipes[position].headline
        val titleH = getTextViewHeight(holder.title)
        val headlineH = getTextViewHeight(holder.headline)
        val item1H = titleH + headlineH
        return Triple(titleH, headlineH, item1H);
    }

    fun addItems(recipes: ArrayList<Recipe>) {
        this.recipes.addAll(recipes)
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }


    fun getTextViewHeight(textView: TextView): Int {
        var w = (deviceWidth()-App.getInstance().dip(2.5F))/2 - App.getInstance().dip(20F)
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.AT_MOST)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        textView.measure(widthMeasureSpec, heightMeasureSpec)
        return textView.measuredHeight
    }

    fun deviceWidth(): Int {
        val point = Point()
        var wManager = App.getInstance().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wManager.defaultDisplay.getSize(point)
        return point.x
    }


    class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.left = space
            outRect.right = space
            outRect.bottom = space
            outRect.top = space
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(recipe: Recipe)
    }
}

