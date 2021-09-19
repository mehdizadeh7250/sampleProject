package com.example.baloot.alimehdizadeh.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baloot.alimehdizadeh.BR
import com.example.baloot.alimehdizadeh.R
import com.example.baloot.alimehdizadeh.databinding.FragmentEveryThingBinding
import com.example.baloot.alimehdizadeh.databinding.ItemNewsBinding
import com.example.baloot.alimehdizadeh.domain.model.remote.Article
import com.example.baloot.alimehdizadeh.presentation.base.BaseFragment
import com.example.baloot.alimehdizadeh.presentation.viewModel.EveryThingFragmentViewModel
import com.example.baloot.alimehdizadeh.utils.EndlessRecyclerOnScrollListener
import com.example.baloot.alimehdizadeh.utils.adapter.MyBindingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EveryThingFragment : BaseFragment<FragmentEveryThingBinding>() {

    val viewModel: EveryThingFragmentViewModel by viewModels()
    override fun getLayoutRes(): Int = R.layout.fragment_every_thing
    private var endlessRecyclerOnScrollListener: EndlessRecyclerOnScrollListener? = null
    private val articleList: ArrayList<Article> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvListAdapter = MyBindingAdapter<Article, ItemNewsBinding>(
            requireContext(),
            articleList,
            intArrayOf(R.layout.item_news),
            onBindViewHolder = { item, position, binder ->
                binder.setVariable(BR.vm, item)
                binder.lytRoot.setOnClickListener {
                    findNavController().navigate(
                        EveryThingFragmentDirections.navigateToNewsDetails(
                            item
                        )
                    )
                }
            }
        )
        val gridLayoutManager =
            GridLayoutManager(requireContext(), 2)
        endlessRecyclerOnScrollListener =
            object : EndlessRecyclerOnScrollListener(gridLayoutManager) {
                override fun onLoadMore(currentPage: Int) {
                    viewModel.nextContactUsPage(currentPage)
                }
            }
        binding.rvList.apply {

            adapter = rvListAdapter
            layoutManager = gridLayoutManager
            addOnScrollListener(endlessRecyclerOnScrollListener!!)
        }
        viewModel.everyThingData.observe(viewLifecycleOwner) { response ->
            if (endlessRecyclerOnScrollListener?.currentPage == 1) {

                response?.articles?.let { articleList.addAll(it) }
                rvListAdapter.notifyDataSetChanged()
                binding.rvList.scrollToPosition(0)
            } else {


                val size = articleList.size
                response?.articles?.let { articleList.addAll(it) }
                rvListAdapter.notifyItemRangeInserted(
                    size,
                    response?.articles?.size!!
                )
            }
            // "For requested too many results. Developer accounts are limited to a max of 100 results. You are trying to request results 180 to 200. Please upgrade to a paid plan if you need more results."
            endlessRecyclerOnScrollListener?.totalPages = 6


        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}