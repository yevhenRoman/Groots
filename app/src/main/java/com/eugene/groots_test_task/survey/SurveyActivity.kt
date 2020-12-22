package com.eugene.groots_test_task.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.eugene.groots_test_task.DI
import com.eugene.groots_test_task.R

class SurveyActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        DI.context = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        toolbar = findViewById(R.id.base_toolbar)
        initNavComponent()
    }

    private fun initNavComponent() {
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        setupWithNavController(toolbar, navController)
    }
}