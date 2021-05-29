package com.hk.recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.hk.recyclerview.databinding.FragmentAdapterSelectionBinding


class AdapterSelectionFragment : Fragment() {

    private lateinit var dataBinding : FragmentAdapterSelectionBinding
    private lateinit var adapter : SelectionAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         dataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_adapter_selection, container, false)
        return dataBinding.root
    }

    companion object {
        fun newInstance() = AdapterSelectionFragment()
    }

    override fun onResume() {
        super.onResume()
        if(activity != null) {
            adapter = SelectionAdapter(inflateList(), activity as MainActivity)
            val layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            //using custom span size
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if((position+1)%3==0) {
                        2
                    } else {
                        1
                    }
                }

            }
            dataBinding.selectionRecyclerView.layoutManager = layoutManager
            dataBinding.selectionRecyclerView.adapter = adapter
        }
    }
    private fun  inflateList(): ArrayList<String> {
        val list = arrayListOf<String>()
        list.add("Linear RV with Multiple Favorites")
        list.add("Linear RV with Single Favorites")
        list.add("Nested RV")
        return list
    }
}