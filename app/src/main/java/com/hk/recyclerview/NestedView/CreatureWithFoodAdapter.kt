package com.hk.recyclerview.NestedView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.hk.recyclerview.R
import com.hk.recyclerview.databinding.ListItemCreatureWithFoodBinding
import com.hk.recyclerview.model.Creature
import com.hk.recyclerview.model.CreatureStore

class CreatureWithFoodAdapter(private val creatures: List<Creature>): RecyclerView.Adapter<CreatureWithFoodAdapter.CreatureWithFoodViewHolder>() {


    // RecycledViewPool lets you share Views between multiple RecyclerViews.
    private val viewPool = RecyclerView.RecycledViewPool()

    inner class CreatureWithFoodViewHolder(val binding: ListItemCreatureWithFoodBinding):
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var creature: Creature
        private var foodAdapter = FoodAdapter(mutableListOf())
        fun bind(creature: Creature) {
            this.creature = creature
            val context = binding.root.context
            binding.creatureImage.setImageResource(
                    context.resources.getIdentifier(creature.uri, null, context.packageName))
            setFoods()
        }

        private fun setFoods() {
            val innerRecyclerViewLayoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.foodRecyclerView.layoutManager  = innerRecyclerViewLayoutManager
            innerRecyclerViewLayoutManager.scrollToPosition(creature.lastScrollPositionOfInnerRecyclerView)
            binding.foodRecyclerView.adapter = foodAdapter
            binding.foodRecyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val total: Int = innerRecyclerViewLayoutManager.getItemCount()
                    val firstVisibleItemCount: Int = innerRecyclerViewLayoutManager.findFirstVisibleItemPosition()
                    val lastVisibleItemCount: Int = innerRecyclerViewLayoutManager.findLastVisibleItemPosition()

                    println("total - $total")
                    println("firstVisibleItemCount - $firstVisibleItemCount")
                    println("lastVisibleItemCount - $lastVisibleItemCount")
                    creature.lastScrollPositionOfInnerRecyclerView = firstVisibleItemCount
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })

            val foods = CreatureStore.getCreatureFoods(creature)
            foodAdapter.updateFoods(foods)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CreatureWithFoodViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = DataBindingUtil.inflate<ListItemCreatureWithFoodBinding>(
                LayoutInflater.from(viewGroup.context),
            R.layout.list_item_creature_with_food,
                viewGroup,
                false
        )
        val holder = CreatureWithFoodViewHolder(binding)
        holder.binding.foodRecyclerView.setRecycledViewPool(viewPool)
        LinearSnapHelper().attachToRecyclerView(holder.binding.foodRecyclerView)
        return holder
    }

    override fun onBindViewHolder(holder: CreatureWithFoodViewHolder, position: Int) {
        holder.bind(creatures[position])
    }

    override fun getItemCount(): Int {
        return creatures.size
    }
}