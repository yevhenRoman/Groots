package com.eugene.groots_test_task.survey.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.eugene.groots_test_task.DI
import com.eugene.groots_test_task.R
import com.google.android.material.snackbar.Snackbar

class WelcomeSurveyFragment : BaseSurveyFragment() {

    private lateinit var startSurveyButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_welcome_survey, container, false)
        startSurveyButton = v.findViewById(R.id.bStartSurvey)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startSurveyButton.setOnClickListener {
            DI.preferenceManager.clearAll() // Just dirty hack to clear old values
            findNavController().navigate(R.id.action_welcomeSurveyFragment_to_selectOneOptionSurveyFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        showSnackBarIfRequired()
    }

    private fun showSnackBarIfRequired() {
        if (arguments?.getInt(IS_SURVEY_ENDED) == null) return
        val snackBar = Snackbar
            .make(requireView(), "Thank you, your survey has been sent", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                findNavController().navigateUp()
            }

        snackBar.show()
    }

    companion object {
        const val IS_SURVEY_ENDED = "IS_SURVEY_ENDED"
    }
}