package com.pt.amoba.ui.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.pt.amoba.databinding.FragmentDetailsBinding

class DetailsFragment : BaseFragment() {

    private val detail by navArgs<DetailsFragmentArgs>()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        setDatainView()

        binding.imageViewExit.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    //Seteo los datos de detalle
    @SuppressLint("SetTextI18n")
    private fun setDatainView() {
        val nameComplete = detail.data.name + " " + detail.data.surname
        binding.imageProfile.text = nameComplete.filter { it.isUpperCase() }

        binding.tvNamePattient.text = nameComplete
        binding.textViewCi.text = "C.I.${detail.data.ci}"
        binding.textViewEmail.text = detail.data.email
        binding.textViewEdad.text = "${detail.data.age} años"
        binding.textViewEdad.text = "${detail.data.month} meses"
        binding.textViewSexo.text = detail.data.sex
        binding.textViewEdad.text = "${detail.data.age} años"
        binding.textViewAddress.text = detail.data.address
        binding.titlePhone.text = detail.data.phone.toString()
        binding.titleMobile.text = detail.data.mobile.toString()
    }
}