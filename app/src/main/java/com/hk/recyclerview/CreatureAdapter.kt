package com.hk.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hk.recyclerview.databinding.ListItemCreatureBinding
import com.hk.recyclerview.model.Creature

class CreatureAdapter(private val creatures: List<Creature>): RecyclerView.Adapter<CreatureAdapter.CreatureViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class CreatureViewHolder(val binding: ListItemCreatureBinding): RecyclerView.ViewHolder(binding.root) {
        private lateinit var creature: Creature

        //to make the code clean, we have made a bind method in ViewHolder which is called from the onBindViewHolder() method.
        fun bind(creature: Creature) {
            this.creature = creature
            val context = binding.root.context
            binding.creatureImage.setImageResource(
                context.resources.getIdentifier(creature.uri, null, context.packageName))
            binding.fullName.text = creature.fullName
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): CreatureViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = DataBindingUtil.inflate<ListItemCreatureBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.list_item_creature,
            viewGroup,
            false
        )
        return CreatureViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: CreatureViewHolder, position: Int) {
        holder.bind(creatures[position])
    }

    override fun getItemCount(): Int {
       return creatures.size
    }
}