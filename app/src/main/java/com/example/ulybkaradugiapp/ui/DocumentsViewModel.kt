package com.example.ulybkaradugiapp.ui

import androidx.lifecycle.*
import com.example.ulybkaradugiapp.data.GetDocumentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val repository: GetDocumentsRepository
) : ViewModel() {

//    LiveData that has values collected from the origin Flow
//    coroutine inside
    val documents = repository.getDocuments().asLiveData()
}