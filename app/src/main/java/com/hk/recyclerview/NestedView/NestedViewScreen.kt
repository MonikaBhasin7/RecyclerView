package com.hk.recyclerview.NestedView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hk.recyclerview.R
import com.hk.recyclerview.databinding.FragmentSingleFavoriteScreenBinding
import com.hk.recyclerview.model.CreatureStore


class NestedViewScreen : Fragment() {

    private lateinit var dataBinding : FragmentSingleFavoriteScreenBinding
    private var adapter = CreatureWithFoodAdapter(CreatureStore.getCreatures())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_single_favorite_screen, container, false)
        return dataBinding.root
    }

    override fun onResume() {
        super.onResume()

        dataBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        dataBinding.recyclerView.adapter = adapter
    }
    companion object {
       fun newInstance() = NestedViewScreen()
    }
}