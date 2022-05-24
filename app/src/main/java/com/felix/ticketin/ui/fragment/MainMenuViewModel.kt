package com.felix.ticketin.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.ticketin.data.Repository
import com.felix.ticketin.data.Resource
import com.felix.ticketin.model.comingsoon.GetAllComingSoonResponse
import com.felix.ticketin.model.playingnow.GetAllPlayingNowIResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class MainMenuViewModel(private val repository: Repository) : ViewModel() {
    var email: MutableLiveData<String> = MutableLiveData("Hello, There")

    private val _playingNow = MutableLiveData<Resource<GetAllPlayingNowIResponse>>()
    private val _comingSoon = MutableLiveData<Resource<GetAllComingSoonResponse>>()
    val playingNow: LiveData<Resource<GetAllPlayingNowIResponse>> get() = _playingNow
    val comingSoon: LiveData<Resource<GetAllComingSoonResponse>> get() = _comingSoon

    fun uncensored(){
        email.value  =  "Have a\nNice Day :D"
        }

    fun getAllPlayingNow(){
        viewModelScope.launch {
            _playingNow.postValue(Resource.loading())
            try {
                _playingNow.postValue(Resource.success(repository.getAllPlayingNow()))
            }catch (exception : Exception){
                _playingNow.postValue(Resource.error(exception.message?: "Error Occurred"))
            }
        }
    }

    fun getAllComingSoon(){
        viewModelScope.launch {
            _comingSoon.postValue(Resource.loading())
            try {
                _comingSoon.postValue(Resource.success(repository.getAllComingSoon()))
            }catch (exception : Exception){
                _comingSoon.postValue(Resource.error(exception.message?: "Error Occurred"))
            }
        }
    }
    }

