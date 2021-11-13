package com.example.ulybkaradugiapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.ulybkaradugiapp.data.GetDocumentsRepository

class DocumentsViewModel @ViewModelInject constructor(
    private val repository: GetDocumentsRepository
) : ViewModel() {
}