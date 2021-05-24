package com.hk.recyclerview

import android.app.Application
import com.hk.recyclerview.model.CreatureStore

class OurApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        //initialize Creature
        CreatureStore.loadCreatures(this)
        CreatureStore.loadFoods(this)
    }
}