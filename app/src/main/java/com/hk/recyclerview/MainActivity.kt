package com.hk.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.hk.recyclerview.MultipleFavorites.MultipleFavoriteScreen
import com.hk.recyclerview.NestedView.CreatureWithFoodAdapter
import com.hk.recyclerview.NestedView.NestedViewScreen
import com.hk.recyclerview.SingleFavorites.SingleFavoriteScreen
import com.hk.recyclerview.databinding.ActivityCreatureBinding
import com.hk.recyclerview.model.CreatureStore

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityCreatureBinding
    private val creatureAdapter = CreatureWithFoodAdapter(CreatureStore.getCreatures())
    private lateinit var fragmentTransaction: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentTransaction = supportFragmentManager.beginTransaction()
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_creature)
    }


    override fun onResume() {
        super.onResume()
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, AdapterSelectionFragment.newInstance())
        fragmentTransaction.commit()
    }

    fun openFragment(fragmentType: String) {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        if(fragmentType.equals(getString(R.string.multiple_favorites), true)) {
            fragmentTransaction.replace(R.id.fragment_container, MultipleFavoriteScreen.newInstance())
        } else if(fragmentType.equals(getString(R.string.single_favorites), true)) {
            fragmentTransaction.replace(R.id.fragment_container, SingleFavoriteScreen.newInstance())
        } else if(fragmentType.equals(getString(R.string.nested_rv), true)) {
            fragmentTransaction.replace(R.id.fragment_container, NestedViewScreen.newInstance())
        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}