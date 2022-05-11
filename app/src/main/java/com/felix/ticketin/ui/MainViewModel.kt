package com.felix.ticketin.ui

import androidx.lifecycle.*
import com.felix.ticketin.DataStoreManager
import kotlinx.coroutines.launch

class MainViewModel(private val pref: DataStoreManager): ViewModel() {
    val vText: MutableLiveData<String> = MutableLiveData("")

    fun saveDataStore(value: String) {
        viewModelScope.launch {
            pref.setPass(value)
        }
    }

    fun getDataStore(): LiveData<String> {
        return pref.getPass().asLiveData()
    }
}