package com.eugene.groots_test_task.survey.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eugene.groots_test_task.DI
import com.eugene.groots_test_task.repository.ISurveyRepository
import com.eugene.groots_test_task.survey.validator.GetPriceValidatorFactoryMethod
import com.eugene.groots_test_task.survey.viewmodels.PriceQuestionSurveyViewModel
import java.util.Locale

class PriceQuestionSurveyViewModelFactory(
    private val locale: Locale,
    private val repository: ISurveyRepository
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PriceQuestionSurveyViewModel(
            GetPriceValidatorFactoryMethod.getPriceValidator(locale),
            repository
        ) as T
    }
}