package com.hk.recyclerview.SingleFavorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hk.recyclerview.R
import com.hk.recyclerview.databinding.ListItemCreatureBinding
import com.hk.recyclerview.model.Creature

class SingleFavoriteAdapter(private val creatures: List<Creature>) : RecyclerView.Adapter<SingleFavoriteAdapter.SingleHearSelectionViewHolder>() {
    var singleSelectedPosition = -1
    var favCreature: Creature? = null

    inner class SingleHearSelectionViewHolder(private val binding: ListItemCreatureBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var creature: Creature
        init {
            binding.ivHeart.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            if(adapterPosition == singleSelectedPosition ) {
                singleSelectedPosition = -1
                binding.ivHeart.setImageResource(R.drawable.ic_heart)
                creature.isFavorite = false
                favCreature?.isFavorite = false
                favCreature = null
            } else {
                singleSelectedPosition = adapterPosition
                binding.ivHeart.setImageResource(R.drawable.ic_heart_fill)
                creature.isFavorite = true
                if(favCreature != null) {
                    favCreature!!.isFavorite = false
                }
                favCreature = creature
                notifyDataSetChanged()
            }
        }

        fun bind(creature: Creature) {
            this.creature = creature
            val context = binding.root.context
            binding.creatureImage.setImageResource(
                context.resources.getIdentifier(creature.uri, null, context.packageName))
            binding.fullName.text = creature.fullName

            if(creature.isFavorite) binding.ivHeart.setImageResource(R.drawable.ic_heart_fill)
            else binding.ivHeart.setImageResource(R.drawable.ic_heart)
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): SingleHearSelectionViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = DataBindingUtil.inflate<ListItemCreatureBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.list_item_creature,
            viewGroup,
            false
        )
        return SingleHearSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SingleHearSelectionViewHolder, position: Int) {
        holder.bind(creatures[position])
    }

    override fun getItemCount(): Int {
        return creatures.size
    }
}