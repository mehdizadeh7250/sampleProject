package com.example.baloot.alimehdizadeh.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.baloot.alimehdizadeh.R
import com.example.baloot.alimehdizadeh.databinding.FragmentNewsDetailsBinding
import com.example.baloot.alimehdizadeh.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : BaseFragment<FragmentNewsDetailsBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_news_details
    val args: NewsDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = args.article
        binding.txtNewsLink.setOnClickListener {
            args.article?.url?.let { it1 -> urlBrowserIntent(it1, 10) }
        }
    }
}