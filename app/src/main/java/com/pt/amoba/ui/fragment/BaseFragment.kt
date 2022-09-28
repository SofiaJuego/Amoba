package com.pt.amoba.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.pt.amoba.R

open class BaseFragment : Fragment() {

    fun View.goToActionDetail() {
        Navigation.findNavController(this)
            .navigate(R.id.action_patientsFragment_to_detailsFragment)
    }
}