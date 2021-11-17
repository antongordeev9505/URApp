package com.example.ulybkaradugiapp.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.ulybkaradugiapp.data.model.DocumentDetail
import com.example.ulybkaradugiapp.data.GetDocumentsRepository
import com.example.ulybkaradugiapp.other.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val repository: GetDocumentsRepository
) : ViewModel() {

    private val reload = MutableLiveData(false)

    val documents = reload.switchMap {
        repository.getDocuments().asLiveData()
    }

    fun reloadList() {
        reload.value = true
    }

    fun getDetails(idDocument: Int) =
        repository.getDetails(idDocument).asLiveData()
}