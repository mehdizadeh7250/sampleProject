package com.example.baloot.alimehdizadeh.presentation.fragment

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.DialogFragment
import com.example.baloot.alimehdizadeh.R
import com.example.baloot.alimehdizadeh.databinding.FragmentAboutMeBinding
import com.example.baloot.alimehdizadeh.presentation.base.BaseBottomSheetFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutMeFragment : BaseBottomSheetFragment<FragmentAboutMeBinding>() {
    var bottomSheetBehavior: BottomSheetBehavior<View>? = null
    override fun getLayoutId(): Int = R.layout.fragment_about_me
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            Handler(Looper.getMainLooper()).post {
                val bottomSheet =
                    (dialog as? BottomSheetDialog)?.findViewById<View>(R.id.design_bottom_sheet) as? FrameLayout
                bottomSheet?.let {
                    bottomSheetBehavior = BottomSheetBehavior.from(it)
                    bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                    bottomSheetBehavior?.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
                    binding.root.minimumHeight = Resources.getSystem().displayMetrics.heightPixels

                }
                bottomSheetBehavior?.addBottomSheetCallback(object :
                    BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        if (slideOffset >= 0) {
                            val color = (slideOffset * 255 / 1f)
                            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            dialog.window?.setBackgroundDrawable(
                                ColorDrawable(
                                    ColorUtils.setAlphaComponent(
                                        ContextCompat.getColor(
                                            activity!!,
                                            R.color.colorDark
                                        ), color.toInt()
                                    )
                                )
                            )
                        }
                    }

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                    }

                })
            }
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.window?.decorView?.setPadding(0, 0, 0, 0)
        return dialog
    }


    override fun onStart() {
        super.onStart()
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
    }
    companion object {
        fun newInstance()= AboutMeFragment()
    }

}