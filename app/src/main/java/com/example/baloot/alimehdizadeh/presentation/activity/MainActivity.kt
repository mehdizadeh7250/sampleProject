package com.example.baloot.alimehdizadeh.presentation.activity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.baloot.alimehdizadeh.R
import com.example.baloot.alimehdizadeh.databinding.ActivityMainBinding
import com.example.baloot.alimehdizadeh.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
    }

    override fun getLayoutRes(): Int = R.layout.activity_main
}