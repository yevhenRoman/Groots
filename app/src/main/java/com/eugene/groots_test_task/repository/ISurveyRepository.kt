package com.eugene.groots_test_task.repository

import com.eugene.groots_test_task.model.Option
import com.eugene.groots_test_task.model.PriceKey

/**
 * TODO: Divide on separate interface according to each screen if it's lots of methods in this interface.
 */
interface ISurveyRepository {
    /**
     * Question: How many hours did you have electricity yesterday?
     */
    fun getElectricityHours(): List<Option>

    /**
     * @param id of option
     */
    fun setElectricityHoursAnswer(id: Int)

    /**
     * @return id of option or null if value does not exist in storage.
     */
    fun getElectricityHoursAnswer(): Int?


    /**
     * @return price by key or null if value does not exist in storage.
     */
    fun getPriceFor(key: PriceKey): String?

    /**
     * Saves price by given key
     */
    fun setPriceFor(key: PriceKey, price: String)
}