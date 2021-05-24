package com.hk.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hk.recyclerview.model.Creature

class CreatureAdapter(private val creatures: List<Creature>): RecyclerView.Adapter<CreatureAdapter.CreatureViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class CreatureViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private lateinit var creature: Creature

        fun bind(creature: Creature) {
            this.creature = creature
            val context = itemView.context
//            itemView.creatureImage.setImageResource(
//                context.resources.getIdentifier(creature.uri, null, context.packageName))
//            itemView.fullName.text = creature.fullName
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): CreatureViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_creature, viewGroup, false)

        return CreatureViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: CreatureViewHolder, position: Int) {
        holder.bind(creatures[position])
    }

    override fun getItemCount(): Int {
       return creatures.size
    }
}