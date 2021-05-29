package com.hk.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hk.recyclerview.databinding.ItemSelectBinding

class SelectionAdapter(private var selections: ArrayList<String>, var activity: MainActivity) : RecyclerView.Adapter<SelectionAdapter.SelectionViewHolder>(){


    inner class SelectionViewHolder(val binding: ItemSelectBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var selectionString: String
        init {
            binding.cvSelection.setOnClickListener(this)
        }
        fun bind(selectionString: String) {
            this.selectionString = selectionString
            binding.txtSelection.text = selectionString
        }

        override fun onClick(v: View?) {

            activity.openFragment(selectionString)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SelectionViewHolder {
        val binding = DataBindingUtil.inflate<ItemSelectBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_select,
            viewGroup,
            false
        )
        return SelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectionViewHolder, position: Int) {
        holder.bind(selections[position])
    }

    override fun getItemCount(): Int {
        return selections.size
    }
}