package com.fahgutawan.arisans.interfaces

import com.fahgutawan.arisans.model.HomeArisan

interface ArisanLoaderInterface {
    suspend fun onArisanListLoaded(list:List<HomeArisan>)
}