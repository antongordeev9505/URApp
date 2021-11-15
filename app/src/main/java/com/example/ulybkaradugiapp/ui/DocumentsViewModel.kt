package com.example.ulybkaradugiapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ulybkaradugiapp.api.GetDocumentsApi
import com.example.ulybkaradugiapp.data.ApiDocument
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val api: GetDocumentsApi
) : ViewModel() {

    private val documentsLiveData = MutableLiveData<List<ApiDocument>>()
    val documents: LiveData<List<ApiDocument>> = documentsLiveData

    init {
        viewModelScope.launch {
            val documents = api.getListOfDocuments()
            documentsLiveData.value = documents.data
        }
    }
}