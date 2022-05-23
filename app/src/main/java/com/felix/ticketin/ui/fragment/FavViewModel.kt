package com.felix.ticketin.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.ticketin.data.Repository
import com.felix.ticketin.data.Resource
import com.felix.ticketin.data.room.FavEntity
import com.felix.ticketin.di.viewModelModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavViewModel(private val repository: Repository) : ViewModel() {
    private val _dataFavorite = MutableLiveData<List<FavEntity>>()
    val dataFavorite : LiveData<List<FavEntity>> get() = _dataFavorite

    fun getAllPosts(){
        viewModelScope.launch(Dispatchers.IO) {
            _dataFavorite.postValue(repository.getAllFav())
        }
    }
}