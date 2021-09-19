package com.example.baloot.alimehdizadeh.presentation.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    private var requestStartActivityCode = -1
    var onFragmentLauncher: ((resultCode: Int, data: Intent?, requestCode: Int) -> Unit)? = null
    lateinit var binding: T

    @LayoutRes
    abstract fun getLayoutRes(): Int

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            onFragmentLauncher?.invoke(result.resultCode, result.data, requestStartActivityCode)
        }

    fun startActivityForResult(requestCode: Int, intent: Intent) {
        requestStartActivityCode = requestCode
        startForResult.launch(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.executePendingBindings()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return binding.root
    }
    fun urlBrowserIntent(url: String, requestCode: Int? = null) {
        var url = url
        try {
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://$url"

            if (requestCode == null) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            } else {
                startActivityForResult(requestCode, Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}