package com.example.newsapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.newsapp.news.domain.DefaultNewsRepository
import com.example.newsapp.data.NewsArticle
import com.example.newsapp.data.model.Source
import com.example.newsapp.ui.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DashboardViewModel@Inject constructor(val repo: DefaultNewsRepository) : ViewModel() {
    val uiStateweather = MutableLiveData<Result<List<Source>>>()
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
    init {

        viewModelScope.launch {
            var response = repo.getSource()
            uiStateweather.value=response

        }

    }

}