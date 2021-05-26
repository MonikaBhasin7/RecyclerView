package com.hk.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hk.recyclerview.databinding.ActivityCreatureBinding
import com.hk.recyclerview.model.CreatureStore

class CreatureActivity : AppCompatActivity() {


    private lateinit var dataBinding : ActivityCreatureBinding
    private val creatureAdapter = CreatureWithFoodAdapter(CreatureStore.getCreatures())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_creature)
    }


    override fun onResume() {
        super.onResume()

        dataBinding.creatureRecyclerView.layoutManager = LinearLayoutManager(this)
        dataBinding.creatureRecyclerView.adapter = creatureAdapter
    }
}