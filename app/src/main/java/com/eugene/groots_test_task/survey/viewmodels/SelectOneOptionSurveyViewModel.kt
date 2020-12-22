package com.eugene.groots_test_task.survey.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eugene.groots_test_task.common.Event
import com.eugene.groots_test_task.repository.ISurveyRepository

class SelectOneOptionSurveyViewModel(private val repository: ISurveyRepository) : ViewModel() {
    private val actions = MutableLiveData<Event<Action>>()
    private var selectedOption: Int? = repository.getElectricityHoursAnswer()

    val options = repository.getElectricityHours()

    fun getAction(): LiveData<Event<Action>> = actions

    fun onOptionSelected(id: Int) {
        repository.setElectricityHoursAnswer(id)
        selectedOption = id
        actions.value = Event(Action.NavigateToPriceQuestionSurvey)
    }

    fun getSelectedOption() = options.firstOrNull { it.id == selectedOption }

    sealed class Action {
        object NavigateToPriceQuestionSurvey : Action()
    }
}