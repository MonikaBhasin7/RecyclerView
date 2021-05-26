package com.hk.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hk.recyclerview.databinding.ListItemFoodBinding
import com.hk.recyclerview.model.Food

class FoodAdapter(private val foods: MutableList<Food>): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {


    class FoodViewHolder(private val binding: ListItemFoodBinding): RecyclerView.ViewHolder(binding.root) {

        private lateinit var food: Food
        fun bind(food: Food) {
            this.food = food
            val context = itemView.context
            binding.foodImage.setImageResource(
                context.resources.getIdentifier(food.thumbnail, null, context.packageName)
            )
        }
    }

    fun updateFoods(foods: List<Food>) {
        this.foods.clear()
        this.foods.addAll(foods)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = DataBindingUtil.inflate<ListItemFoodBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.list_item_food,
            viewGroup,
            false
        )
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foods[position])
    }

    override fun getItemCount(): Int {
        return foods.size
    }
}