package com.example.ulybkaradugiapp.ui

import androidx.lifecycle.*
import com.example.ulybkaradugiapp.data.model.DocumentDetail
import com.example.ulybkaradugiapp.data.GetDocumentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    fun update(detail: DocumentDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(detail)
        }
    }
//    init {
//        viewModelScope.launch {
//            repository.getDetails(115725295)
//        }
//    }
}