package com.pt.amoba.ui.fragment

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pt.amoba.R
import com.pt.amoba.data.viewmodel.ViewModel

open class BaseFragment : Fragment() {

    private val viewModel: ViewModel by viewModels()

     fun showAlertDialogFunctionToDevelop (){
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.not_available))
            .setIcon(R.drawable.logo)
            .setMessage(getString(R.string.function_to_develop))
            .setPositiveButton(getString(R.string.ok)) { dialogInterface, d ->

            }.create().show()

    }
    fun showAlertDialogLogout() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.logout))
            .setMessage(getString(R.string.wantTologout))
            .setIcon(R.drawable.logo)
            .setCancelable(true)
            .setNegativeButton(getString(R.string.cancel)) { dialogInterface, d ->
                dialogInterface.cancel()
            }
            .setPositiveButton(getString(R.string.exit)) { dialogInterface, d ->
                viewModel.logout()
                activity?.finish()

            }.create().show()
    }


}