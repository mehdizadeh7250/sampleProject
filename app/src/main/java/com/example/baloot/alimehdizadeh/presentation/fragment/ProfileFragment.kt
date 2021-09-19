package com.example.baloot.alimehdizadeh.presentation.fragment

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.baloot.alimehdizadeh.R
import com.example.baloot.alimehdizadeh.databinding.FragmentProfileBinding
import com.example.baloot.alimehdizadeh.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_profile
    val aboutMeFragment = AboutMeFragment()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load("https://avatars.githubusercontent.com/u/42169409?s=300")
            .apply(RequestOptions.centerCropTransform())
            .into(binding.imgProfile)
        binding.btnGithub.setOnClickListener {
            urlBrowserIntent("https://github.com/mehdizadeh7250", 10)
        }
        binding.btnLinkedin.setOnClickListener {
            urlBrowserIntent("https://www.linkedin.com/in/ali-mehdizadeh-4586a4111/", 10)
        }
        binding.btnAboutMe.setOnClickListener {
            aboutMeFragment.show(childFragmentManager, "BottomSheet")
        }
    }


}