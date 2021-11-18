package com.example.ulybkaradugiapp.ui

import androidx.lifecycle.*
import com.example.ulybkaradugiapp.data.model.DocumentDetail
import com.example.ulybkaradugiapp.data.GetDocumentsRepository
import com.example.ulybkaradugiapp.other.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val repository: GetDocumentsRepository
) : ViewModel() {

    private val reload = MutableLiveData(false)

    val documents = reload.switchMap { shouldFetch ->
        repository.getDocuments(shouldFetch).asLiveData()
    }

    fun reloadList(shouldFetch: Boolean) {
        reload.value = shouldFetch
    }

    fun getDetails(idDocument: Int, shouldFetch: Boolean) =
        repository.getDetails(idDocument, shouldFetch).asLiveData()


    fun updateDetail(detail: DocumentDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDetail(detail)
        }
    }

    fun getDetailsToStore(idDocument: Int){
        viewModelScope.launch {
            repository.tryUpdateDetails(idDocument)
        }
    }

//    fun getAmountFromHeader(idDocument: Int, shouldFetch: Boolean) =
//        repository.getHeader(idDocument,shouldFetch).asLiveData()
}