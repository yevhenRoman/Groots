package com.eugene.groots_test_task.survey.validator

abstract class PriceValidator {
    var currencyLabel = ""
    protected set

    abstract fun validate(input: String): String
}