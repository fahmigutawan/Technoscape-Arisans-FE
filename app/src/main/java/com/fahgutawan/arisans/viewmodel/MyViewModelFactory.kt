package com.fahgutawan.arisans.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fahgutawan.arisans.repo.ArisansApiRepo

class MyViewModelFactory(val repo:ArisansApiRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(repo) as T
    }
}