package com.eugene.groots_test_task.survey.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eugene.groots_test_task.common.Event
import com.eugene.groots_test_task.model.PriceKey
import com.eugene.groots_test_task.repository.ISurveyRepository
import com.eugene.groots_test_task.survey.BaseSurveyViewModel
import com.eugene.groots_test_task.survey.validator.PriceValidator

class PriceQuestionSurveyViewModel(
    private val priceValidator: PriceValidator,
    private val repository: ISurveyRepository
    ) : BaseSurveyViewModel() {
    // Init by default
    private val validatedInput = MutableLiveData("0.00")
    private val action = MutableLiveData<Event<Action>>()

    init {
        val preSelectedPrice = repository.getPriceFor(PriceKey.PRICE_FOR_ELECTRICITY_LAST_WEEK)
        preSelectedPrice?.let { validatedInput.value = preSelectedPrice }
    }

    fun getAction(): LiveData<Event<Action>> = action
    fun getValidatedInput(): LiveData<String> = validatedInput
    fun getCurrency(): LiveData<String> = MutableLiveData(priceValidator.currencyLabel)

    init {
        validatedInput.value = validatedInput.value?.let { priceValidator.validate(it) }
    }

    fun inputChanged(input: String) {
        if (input == validatedInput.value) {
            return
        }

        validatedInput.value = priceValidator.validate(input)
    }

    override fun submitSurvey() {
        super.submitSurvey()
        validatedInput.value?.let {
            repository.setPriceFor(PriceKey.PRICE_FOR_ELECTRICITY_LAST_WEEK,
                it
            )
        }
        action.value = Event(Action.FinishSurvey)
    }

    sealed class Action {
        object FinishSurvey: Action()
    }
}