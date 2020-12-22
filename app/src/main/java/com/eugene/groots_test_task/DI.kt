package com.eugene.groots_test_task

import android.content.Context
import com.eugene.groots_test_task.repository.ISurveyRepository
import com.eugene.groots_test_task.repository.PreferenceManager
import com.eugene.groots_test_task.repository.SurveyRepository

/**
 * Imagine that it's our DI framework
 */
object DI {
    val preferenceManager: PreferenceManager by lazy { PreferenceManager(context!!) }
    val surveyRepository: ISurveyRepository by lazy { SurveyRepository(preferenceManager) }
    // Require to initialize
    var context: Context? = null
}