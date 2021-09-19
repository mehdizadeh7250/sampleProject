package com.example.baloot.alimehdizadeh.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.baloot.alimehdizadeh.R
import com.example.baloot.alimehdizadeh.databinding.FragmentMainBinding
import com.example.baloot.alimehdizadeh.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_main
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.home_container) as NavHostFragment
        val navController = navHostFragment.navController
        val menuItems = arrayOf(
            CbnMenuItem(
                R.drawable.ic_new_nav_settins,
                R.drawable.avd_new_nav_setting,
                R.id.profileFragment
            ),
            CbnMenuItem(
                R.drawable.ic_new_nav_level,
                R.drawable.avd_nav_level,
                R.id.fragmentEveryThing
            )

        )
        binding.bottomBar.setMenuItems(menuItems, 1)
        binding.bottomBar.setupWithNavController(navController)
    }

}