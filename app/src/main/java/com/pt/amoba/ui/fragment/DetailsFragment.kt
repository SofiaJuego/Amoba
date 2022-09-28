package com.pt.amoba.ui.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs
import com.pt.amoba.R
import com.pt.amoba.databinding.FragmentDetailsBinding

class DetailsFragment : BaseFragment() {

    private val detail by navArgs<DetailsFragmentArgs>()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        setDetailInView()

        binding.buttonViewMedical.setOnClickListener {

            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.not_available))
                .setMessage(getString(R.string.function_to_develop))
                .setPositiveButton(getString(R.string.ok)) { dialogInterface, d ->

                }.create().show()
        }

        binding.imageViewExit.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    //Set date detail
    @SuppressLint("SetTextI18n")
    private fun setDetailInView() {
        val nameComplete = detail.data.name + " " + detail.data.surname

        binding.imageProfile.text = nameComplete.filter { it.isUpperCase() }
        binding.tvNamePattient.text = nameComplete
        binding.textViewCi.text = "${getString(R.string.ci)}${detail.data.ci}"
        binding.textViewEmail.text = detail.data.email
        binding.textViewEdad.text = "${detail.data.age} ${getString(R.string.age)}"
        binding.textViewMonth.text = "${detail.data.month} ${getString(R.string.months)}"
        binding.textViewSexo.text = detail.data.sex
        binding.textViewAddress.text = detail.data.address
        binding.titlePhone.text = detail.data.phone.toString()
        binding.titleMobile.text = detail.data.mobile.toString()
    }
}