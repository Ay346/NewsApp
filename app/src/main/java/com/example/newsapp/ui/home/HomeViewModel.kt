package com.example.newsapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.newsapp.news.domain.DefaultNewsRepository
import com.example.newsapp.data.NewsArticle
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.newsapp.ui.Result
import timber.log.Timber

@HiltViewModel
class HomeViewModel @Inject constructor(val repo: DefaultNewsRepository) : ViewModel() {

       val uiStateweather = MutableLiveData<Result<List<NewsArticle>>>()
//    fun getNews() {
//        viewModelScope.launch(Dispatchers.IO) {
//            uiStateweather.postValue(UiState.Loading)
//            val response = repo.getNewsFromWebservice()
//            uiStateweather.postValue(response)
//        }
//    }
    init {
        loadData()
    }

    fun loadData() {

        viewModelScope.launch {
         var response = repo.getNewsFromWebservice("eg")
            uiStateweather.value=response
//            Timber.i("$data")
//            if (data is Result.Success) {
//                Timber.i(data.data[1].title)
//            }
        }
    }
}