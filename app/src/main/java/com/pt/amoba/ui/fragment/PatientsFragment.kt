package com.pt.amoba.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pt.amoba.R
import com.pt.amoba.adapter.PatientsAdapter
import com.pt.amoba.data.api.Patients
import com.pt.amoba.data.api.Response
import com.pt.amoba.data.viewmodel.ViewModel
import com.pt.amoba.databinding.FragmentPatientsListBinding

class PatientsFragment : BaseFragment(), Response {

    private lateinit var binding: FragmentPatientsListBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPatientsListBinding.inflate(layoutInflater, container, false)

        viewModel.getAllPatients(this)

        binding.bottomLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.logout))
                .setMessage("¿Desea cerrar sesión?")
                .setCancelable(true)
                .setNegativeButton("Cancelar") { dialogInterface, d ->
                    dialogInterface.cancel()
                }
                .setPositiveButton("Salir") { dialogInterface, d ->
                    viewModel.logout()
                    activity?.finish()

                }.create().show()

        }
        return binding.root
    }

    override fun onLoading() {
        //Loading
        showLoadingCase()

    }

    override fun onSucessfull(list: ArrayList<Patients>) {
        configListPatients(list)
    }

    override fun onError() {
        onResponseCase()
    }

    private fun configListPatients(list: ArrayList<Patients>) {
        onResponseCase()
        val adapter = PatientsAdapter(list)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }


    private fun showLoadingCase() {
        binding.apply {
            progressBarPatiens.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE

        }

    }


    private fun onResponseCase() {
        binding.apply {
            progressBarPatiens.visibility = View.GONE

            recyclerView.visibility = View.VISIBLE
        }

    }

}