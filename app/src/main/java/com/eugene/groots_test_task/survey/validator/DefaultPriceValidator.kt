package com.eugene.groots_test_task.survey.validator

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale
import java.util.Currency

class DefaultPriceValidator(locale: Locale) : PriceValidator() {
    private val formatter = NumberFormat.getCurrencyInstance(locale) as DecimalFormat
    private val symbols: DecimalFormatSymbols = formatter.decimalFormatSymbols

    init {
        currencyLabel = Currency.getInstance(locale).currencyCode
        symbols.currencySymbol = ""
        formatter.decimalFormatSymbols = symbols
    }

    override fun validate(input: String): String {
        val cleanString: String = input.replace("""[,.]""".toRegex(), "")
        val parsed = cleanString.toDouble()

        // NumberFormat return value with space for currencySymbol, so need to delete it.
        return formatter.format((parsed / 100)).replace("\\s+".toRegex(), "")
    }
}