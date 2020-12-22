package com.eugene.groots_test_task.survey.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eugene.groots_test_task.DI
import com.eugene.groots_test_task.R
import com.eugene.groots_test_task.model.Option
import com.eugene.groots_test_task.survey.factories.SelectOneOptionSurveyViewModelFactory
import com.eugene.groots_test_task.survey.viewmodels.SelectOneOptionSurveyViewModel

class SelectOneOptionSurveyFragment : BaseSurveyFragment(), View.OnClickListener {
    private lateinit var optionsGroup: RadioGroup
    private lateinit var vm: SelectOneOptionSurveyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_select_one_option_survey, container, false)
        optionsGroup = v.findViewById(R.id.rgOptions)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOptions()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = SelectOneOptionSurveyViewModelFactory(DI.surveyRepository)
        vm = ViewModelProvider(this, factory).get(SelectOneOptionSurveyViewModel::class.java)
        vm.getAction().observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                handleAction(it)
            }
        }

        vm.options.forEach {
            addRadioButtons(it)
        }
    }

    override fun onStart() {
        super.onStart()
        vm.getSelectedOption()?.let {
            setRadioButtonSelectedById(it.id)
        }
    }

    override fun onClick(v: View?) {
        v?.id?.let { id ->
            vm.onOptionSelected(id)
        }
    }

    private fun initOptions() {
        optionsGroup.orientation = LinearLayout.VERTICAL
    }

    private fun addRadioButtons(option: Option) {
        val button = RadioButton(requireContext()).apply {
            id = option.id
            text = option.text
        }
        button.setOnClickListener(this)
        optionsGroup.addView(button)
    }

    private fun handleAction(action: SelectOneOptionSurveyViewModel.Action) = when (action) {
        SelectOneOptionSurveyViewModel.Action.NavigateToPriceQuestionSurvey -> {
            findNavController().navigate(R.id.action_selectOneOptionSurveyFragment_to_priceQuestionSurveyFragment)
        }
    }

    private fun setRadioButtonSelectedById(id: Int) {
        (optionsGroup.findViewById(id) as? RadioButton)?.isSelected = true
    }
}