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

    //запускается, когда меняется значение reload - чтобы обновить список
    val documents = reload.switchMap {
        //конвертация flow в liveData
        //данные обозреваем (подписывваемся на изменения) во фрагменте
        repository.getDocuments().asLiveData()
    }

    fun reloadList() {
        reload.value = true
    }
}