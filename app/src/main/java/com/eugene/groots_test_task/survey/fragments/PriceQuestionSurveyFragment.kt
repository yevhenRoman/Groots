package com.eugene.groots_test_task.survey.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eugene.groots_test_task.DI
import com.eugene.groots_test_task.R
import com.eugene.groots_test_task.survey.factories.PriceQuestionSurveyViewModelFactory
import com.eugene.groots_test_task.survey.viewmodels.PriceQuestionSurveyViewModel
import java.util.*


class PriceQuestionSurveyFragment : BaseSurveyFragment() {
    private lateinit var vm: PriceQuestionSurveyViewModel
    private lateinit var price: EditText
    private lateinit var currency: TextView
    private lateinit var submitSurveyButton: Button
    private var hasFocus: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_price_question_survey, container, false)
        price = v.findViewById(R.id.etPrice)
        currency = v.findViewById(R.id.tvCurrency)
        submitSurveyButton = v.findViewById(R.id.bSubmitSurvey)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Hardcoded defaultLocale, but can be set dynamically
        val defaultLocale = Locale.FRANCE
        val factory = PriceQuestionSurveyViewModelFactory(defaultLocale, DI.surveyRepository)
        vm = ViewModelProvider(this, factory).get(PriceQuestionSurveyViewModel::class.java)
        observeValues()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInputPriceListener()
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
        super.onMultiWindowModeChanged(isInMultiWindowMode)
        if (hasFocus) {
            price.clearFocus()
            price.requestFocus()
            activity?.window?.setSoftInputMode(SOFT_INPUT_STATE_VISIBLE);
            price.setSelection(price.text.length)
        }
    }

    override fun onStart() {
        super.onStart()

        (activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
            price,
            InputMethodManager.SHOW_FORCED
        )
        activity?.window?.setSoftInputMode(SOFT_INPUT_STATE_VISIBLE)
        price.requestFocus()
    }

    private fun observeValues() {
        vm.getValidatedInput().observe(viewLifecycleOwner) {
            price.setText(it)
            price.setSelection(it.length)
        }

        vm.getCurrency().observe(viewLifecycleOwner) {
            currency.text = it
        }

        vm.getAction().observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                handleAction(it)
            }
        }
    }

    private fun handleAction(action: PriceQuestionSurveyViewModel.Action) = when(action) {
        PriceQuestionSurveyViewModel.Action.FinishSurvey -> {
            val bundle = bundleOf(WelcomeSurveyFragment.IS_SURVEY_ENDED to 1)
            findNavController().navigate(
                R.id.action_priceQuestionSurveyFragment_to_welcomeSurveyFragment,
                bundle
            )
        }
    }

    private fun setupInputPriceListener() {
        price.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    price.removeTextChangedListener(this)
                    vm.inputChanged(s.toString())
                    price.addTextChangedListener(this)
                }
            }
        })

        price.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if ((event?.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    vm.submitSurvey()
                    return true
                }
                return false
            }
        })

        submitSurveyButton.setOnClickListener {
            vm.submitSurvey()
        }

        price.run {
            setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    val imm =
                        activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
                    imm!!.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
                }
            }
        }
    }
}