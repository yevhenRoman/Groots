package com.eugene.groots_test_task.survey.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eugene.groots_test_task.repository.ISurveyRepository
import com.eugene.groots_test_task.survey.viewmodels.SelectOneOptionSurveyViewModel

class SelectOneOptionSurveyViewModelFactory(private val repository: ISurveyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SelectOneOptionSurveyViewModel(repository) as T
    }
}