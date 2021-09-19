package com.example.baloot.alimehdizadeh.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.baloot.alimehdizadeh.R
import com.example.baloot.alimehdizadeh.databinding.FragmentSplashBinding
import com.example.baloot.alimehdizadeh.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_splash
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.mainFragment)
        }, 5000)
    }
}