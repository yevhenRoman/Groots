package com.eugene.groots_test_task.survey

import androidx.lifecycle.ViewModel

abstract class BaseSurveyViewModel : ViewModel() {
    // To give possibility end survey from every screen in future
    open fun submitSurvey() {
        // Can be empty for now
    }
}