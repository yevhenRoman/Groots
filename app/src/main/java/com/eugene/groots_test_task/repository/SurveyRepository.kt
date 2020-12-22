package com.eugene.groots_test_task.repository

import com.eugene.groots_test_task.model.Option
import com.eugene.groots_test_task.model.PriceKey

class SurveyRepository(private val preferenceManager: PreferenceManager) : ISurveyRepository {

    // Imagine this values from backend :)
    override fun getElectricityHours(): List<Option> = arrayListOf(
        Option("0 - 5 hours", 1),
        Option("6 - 12 hours", 2),
        Option("13 - 18 hours", 3),
        Option("19 - 24 hours", 4),
    )

    override fun setElectricityHoursAnswer(id: Int) {
        preferenceManager.putInt(ELECTRICITY_ANSWER_KEY, id)
    }

    override fun getElectricityHoursAnswer(): Int? {
        // Careful, using default value here -> Int.MIN_VALUE
        val saved =  preferenceManager.getInt(ELECTRICITY_ANSWER_KEY, Int.MIN_VALUE)
        return if (saved == Int.MIN_VALUE) {
            null
        } else {
            saved
        }
    }

    override fun getPriceFor(key: PriceKey): String? {
        return preferenceManager.getString(key.key, null)
    }

    override fun setPriceFor(key: PriceKey, price: String) {
        preferenceManager.putString(key.key, price)
    }

    companion object {
        private const val ELECTRICITY_ANSWER_KEY = "ELECTRICITY_ANSWER_KEY"
    }
}