package com.example.ulybkaradugiapp.ui

import androidx.lifecycle.*
import com.example.ulybkaradugiapp.data.GetDocumentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val repository: GetDocumentsRepository
) : ViewModel() {

    private val reload = MutableLiveData(false)

    val documents = reload.switchMap {
//        LiveData that has values collected from the origin Flow
//        coroutine inside
        repository.getDocuments().asLiveData()
    }

    fun reloadList() {
        reload.value = true
    }
}