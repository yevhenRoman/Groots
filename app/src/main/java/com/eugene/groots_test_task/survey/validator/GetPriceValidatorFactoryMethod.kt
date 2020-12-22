package com.eugene.groots_test_task.survey.validator

import java.util.*

class GetPriceValidatorFactoryMethod {
    companion object {
        fun getPriceValidator(locale: Locale): PriceValidator {
            return DefaultPriceValidator(locale)
        }
    }
}